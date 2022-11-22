package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.AnnoucementDTO;
import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.exception.ListIsEmptyException;
import com.meli.projetointegradormelifrescos.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.model.Announcement;
import com.meli.projetointegradormelifrescos.repository.AnnoucementRepo;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementService implements IAnnoucementService {

    @Autowired
    private AnnoucementRepo annoucementRepo;

    /***
     *   message  método responsável por consultar todos os produtos
     *   @author Thaíssa Carrafa
     *  @return List<AnnouncementDTO>
     */

    public List<AnnoucementDTO> listAllProducts() {
        List<AnnoucementDTO> allProducts = annoucementRepo
            .findAll()
            .stream()
            .map(announcement ->
                new AnnoucementDTO(
                    announcement.getId(),
                    announcement.getName(),
                    announcement.getPrice(),
                    announcement.getCategory(),
                    announcement.getDescription()
                )
            )
            .collect(Collectors.toList());

        if (allProducts.isEmpty()) {
            throw new ListIsEmptyException("Nenhum produto cadastrado");
        }
        return allProducts;
    }

    /***
     *
     * @param category método responsável por consultar todos os produtos de determinada categoria
     * @author Thaíssa Carrafa
     * @return
     */
    public List<Announcement> findAllByCategory(Category category) {
        List<Announcement> products = annoucementRepo
            .findAll()
            .stream()
            .filter(product ->
                Objects.equals(
                    product.getCategory().getName(),
                    category.getName()
                )
            )
            .collect(Collectors.toList());

        if (products.isEmpty()) {
            throw new NotFoundException("No products found");
        }
        return products;
    }
}
