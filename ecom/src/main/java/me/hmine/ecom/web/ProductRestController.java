package me.hmine.ecom.web;


import lombok.AllArgsConstructor;
import me.hmine.ecom.DTOS.ProductDTO;
import me.hmine.ecom.entities.Product;
import me.hmine.ecom.repositories.ProductRepository;
import me.hmine.ecom.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ProductRestController {
    private ProductService prodServ;

    @GetMapping("/products")
    public List<ProductDTO> products(){
        return prodServ.products();
    }

    @GetMapping("/product/{id}")
    public ProductDTO product(@PathVariable(name = "id") String id){
        return prodServ.getProduct(id);
    }

    @PostMapping("/products")
    public ProductDTO saveProduct(@RequestBody ProductDTO prod){
        return prodServ.save(prod);
    }

    @PutMapping("/products/{id}")
    public ProductDTO updateProduct(@RequestBody ProductDTO prodDTO , @PathVariable(name = "id") String id){
        prodDTO.setId(id);
        return prodServ.updateProduct(prodDTO);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@RequestBody Product prod , @PathVariable(name = "id") String id){
        prodServ.deleteProd(id);
    }



}
