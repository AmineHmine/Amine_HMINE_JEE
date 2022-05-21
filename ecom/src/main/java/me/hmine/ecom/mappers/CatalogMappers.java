package me.hmine.ecom.mappers;

import com.fasterxml.jackson.databind.util.BeanUtil;
import me.hmine.ecom.DTOS.CategoryDTO;
import me.hmine.ecom.DTOS.ProductDTO;
import me.hmine.ecom.entities.Category;
import me.hmine.ecom.entities.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CatalogMappers {
    public ProductDTO fromProduct(Product prod){
        ProductDTO pdt=new ProductDTO();
        BeanUtils.copyProperties(prod,pdt);
        pdt.setCategoryDTO(fromCategory(prod.getCategory()));
        return pdt;
    }

    public CategoryDTO fromCategory(Category cat){
        CategoryDTO catDTO=new CategoryDTO();
        BeanUtils.copyProperties(cat,catDTO);
        return catDTO;
    }

    public Product fromProductDTO(ProductDTO prodDTO){
        Product pdt=new Product();
        BeanUtils.copyProperties(prodDTO,pdt);
        pdt.setCategory(fromCategoryDTO(prodDTO.getCategoryDTO()));
        return pdt;
    }

    public Category fromCategoryDTO(CategoryDTO catDTO){
        Category cat=new Category();
        BeanUtils.copyProperties(catDTO,cat);
        return cat;
    }

}
