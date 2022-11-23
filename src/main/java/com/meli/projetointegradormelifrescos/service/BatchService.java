package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.BatchStockResDTO;
import com.meli.projetointegradormelifrescos.dto.WarehouseCountDTO;
import com.meli.projetointegradormelifrescos.dto.WarehouseStockDTO;
import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.exception.ListIsEmptyException;
import com.meli.projetointegradormelifrescos.model.Batch;
import com.meli.projetointegradormelifrescos.model.Section;
import com.meli.projetointegradormelifrescos.repository.BatchRepo;
import com.meli.projetointegradormelifrescos.repository.SectionRepo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class BatchService implements IBatchService {

    @Autowired
    BatchRepo batchRepo;

    @Autowired
    SectionRepo sectionRepo;

    /**
     * Filter batches by category/section when needed and sort them
     * @param batches List of batches
     * @param categoryCode Category code
     * @param sortBy Can be "asc" or "desc"
     * @return BatchStockResDTO
     */
    private BatchStockResDTO filterBatches(
        List<Batch> batches,
        String categoryCode,
        String sortBy
    ) {
        if (batches.isEmpty()) throw new NotFoundException("No batches found");

        List<Batch> filteredBatches = new ArrayList<>(batches);

        if (!categoryCode.equalsIgnoreCase("all")) {
            Category category;

            try {
                category = Category.getCategoryByValue(categoryCode);
            } catch (Exception e) {
                throw new NotFoundException("Category not found");
            }

            filteredBatches =
                batches
                    .stream()
                    .filter(batch ->
                        batch.getSection().getCategory().equals(category)
                    )
                    .collect(Collectors.toList());
        }

        if (filteredBatches.isEmpty()) throw new NotFoundException(
            "No batches found"
        );

        if (sortBy.equalsIgnoreCase("asc")) {
            filteredBatches.sort(Comparator.comparing(Batch::getDueDate));
        } else if (sortBy.equalsIgnoreCase("desc")) {
            filteredBatches.sort(
                Comparator.comparing(Batch::getDueDate).reversed()
            );
        } else {
            throw new NotFoundException("Sort by not found");
        }

        return new BatchStockResDTO(filteredBatches);
    }

    public void saveBatch(Batch batch) {
        batchRepo.save(batch);
    }

    /**
     *   @author Igor Fernandes, Gustavo Dolzan e Leonardo Correia
     *   @return BatchStockResDTO
     */
    @Override
    public BatchStockResDTO getBatchStockBySection(
        Integer numberOfDays,
        Long sectionId
    ) {
        Section section = sectionRepo
            .findById(sectionId)
            .orElseThrow(() -> new NotFoundException("Section not found"));

        if (section.getInboundOrders().isEmpty()) throw new NotFoundException(
            "Section without inbound orders"
        );

        List<Batch> batches = new ArrayList<>();

        section
            .getInboundOrders()
            .forEach(inboundOrder -> batches.addAll(inboundOrder.getBatches()));

        List<Batch> filteredBatches = batches
            .stream()
            .filter(batch ->
                batch.getDueDate().isAfter(LocalDate.now()) &&
                batch
                    .getDueDate()
                    .isBefore(batch.getDueDate().plusDays(numberOfDays))
            )
            .collect(Collectors.toList());

        if (filteredBatches.isEmpty()) throw new NotFoundException(
            "No batches found"
        );

        return new BatchStockResDTO(filteredBatches);
    }

    /**
     *   @author Igor Fernandes, Gustavo Dolzan e Leonardo Correia
     *   @return BatchStockResDTO
     */
    @Override
    public BatchStockResDTO getBatchStockByCategory(
        Integer numberOfDays,
        String categoryCode,
        String sortBy
    ) {
        return filterBatches(
            batchRepo.findAllByDueDateBetween(
                LocalDate.now(),
                LocalDate.now().plusDays(numberOfDays)
            ),
            categoryCode,
            sortBy
        );
    }

    /**
     * Get expired batches filtered or not by category
     * @param dueDate Due date of the batch
     * @param category Category code, default is "all"
     * @param sortBy Can be "asc" or "desc", default is "asc"
     * @return BatchStockResDTO
     */
    @Override
    public BatchStockResDTO getExpiredBatches(
        LocalDate dueDate,
        String category,
        String sortBy
    ) {
        return filterBatches(
            batchRepo.findAllByDueDateBefore(dueDate),
            category,
            sortBy
        );
    }

    /**
     *   message Verifica os produtos por section
     *   @author Thaíssa Carrafa
     *   @return WarehouseStockDTO
     */
    public WarehouseStockDTO countStocksByProductId(Long productId) {
        List<Batch> batches = batchRepo.findBatchByProductId(productId);

        if (batches.isEmpty()) {
            throw new ListIsEmptyException(
                "Este produto não foi encontrado em nenhum armazém."
            );
        }

        List<WarehouseCountDTO> warehouseCountDTOList = new ArrayList<WarehouseCountDTO>();

        batches.forEach(batch ->
            warehouseCountDTOList.add(
                new WarehouseCountDTO(
                    batch.getWarehouse().getCode(),
                    batch.getProductQuantity()
                )
            )
        );

        WarehouseStockDTO warehouseStockDTO = new WarehouseStockDTO();
        warehouseStockDTO.setProductId(
            batches.stream().findFirst().get().getProductId()
        );
        warehouseStockDTO.setWarehouses(warehouseCountDTOList);

        return warehouseStockDTO;
    }

    /**
     *   message Verifica os produtos por section
     *   @author Thaissa Carrafa
     *   @return BatchDTO
     */
    @Override
    public BatchDTO productsBySection(Long productId) {
        List<Batch> batches = batchRepo.findSectionByProductId(productId);

        List<BatchDTO> batchDTOList = new ArrayList<>();

        batches.forEach(batch -> batchDTOList.add(new BatchDTO(batch)));

        BatchDTO batchDTO = new BatchDTO();
        batchDTO.setProductId(
            batches.stream().findFirst().get().getProductId()
        );
        batchDTO.setDueDate(batches.stream().findFirst().get().getDueDate());
        batchDTO.setBatchNumber(
            batches.stream().findFirst().get().getBatchNumber()
        );
        batchDTO.setProductQuantity(
            batches.stream().findFirst().get().getProductQuantity()
        );

        return batchDTO;
    }
}
