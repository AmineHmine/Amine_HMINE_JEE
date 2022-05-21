package me.hmine.ecom.services;

import me.hmine.ecom.DTOS.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO save(ProductDTO prodDTO);

    List<ProductDTO> products();

    ProductDTO getProduct(String idDto);

    void deleteProd(String id);

    ProductDTO updateProduct(ProductDTO productDTO);
}
