package me.hmine.ecom.repositories;

import me.hmine.ecom.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
