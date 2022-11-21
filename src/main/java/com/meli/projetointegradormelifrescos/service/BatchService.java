package com.meli.projetointegradormelifrescos.service;
import com.meli.projetointegradormelifrescos.dto.BatchStockResDTO;
import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.model.Batch;
import com.meli.projetointegradormelifrescos.model.Section;
import com.meli.projetointegradormelifrescos.repository.BatchRepo;
import com.meli.projetointegradormelifrescos.repository.SectionRepo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class BatchService implements IBatchStockService {

    private final BatchRepo batchRepo;
    private final SectionRepo sectionRepo;

    public void saveBatch(Batch batch) {

        batchRepo.save(batch);
    }

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

    @Override
    public BatchStockResDTO getBatchStockByCategory(
        Integer numberOfDays,
        String categoryCode,
        String sortBy
    ) {
        Category category;

        try {
            category = Category.getCategoryByValue(categoryCode);
        } catch (Exception e) {
            throw new NotFoundException("Category not found");
        }

        List<Batch> batches = batchRepo.findAllByDueDateBetween(
            LocalDate.now(),
            LocalDate.now().plusDays(numberOfDays)
        );

        if (batches.isEmpty()) throw new NotFoundException("No batches found");

        List<Batch> filteredBatches = batches
            .stream()
            .filter(batch -> batch.getSection().getCategory().equals(category))
            .collect(Collectors.toList());

        if (filteredBatches.isEmpty()) throw new NotFoundException(
            "No batches found2"
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
}
