package me.hmine.ecom.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String id;
    private String name;
    private double price;
    private double quantity;
    private CategoryDTO categoryDTO;
}
