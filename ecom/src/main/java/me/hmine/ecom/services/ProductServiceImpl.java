package me.hmine.ecom.services;


import lombok.AllArgsConstructor;
import me.hmine.ecom.DTOS.ProductDTO;
import me.hmine.ecom.entities.Product;
import me.hmine.ecom.mappers.CatalogMappers;
import me.hmine.ecom.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository prodRepo;
    private CatalogMappers catalogMappers;

    @Override
    public ProductDTO save(ProductDTO prodDTO){
        Product prod = catalogMappers.fromProductDTO(prodDTO);
        prod.setId(UUID.randomUUID().toString());
        Product saverProd = prodRepo.save(prod);
        return catalogMappers.fromProduct(saverProd);
    }

    @Override
    public List<ProductDTO> products(){
        List<Product> prods = prodRepo.findAll();
        return prods.stream().map(p->catalogMappers.fromProduct(p)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProduct(String idDto){
        Product prod = prodRepo.findById(idDto).orElseThrow(()->new RuntimeException("prod not found"));
        ProductDTO productDTO=catalogMappers.fromProduct(prod);
        return productDTO;
    }

    @Override
    public void deleteProd(String id) {
        prodRepo.deleteById(id);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product prod = catalogMappers.fromProductDTO(productDTO);
        Product saved = prodRepo.save(prod);
        return catalogMappers.fromProduct(saved);
    }
}
