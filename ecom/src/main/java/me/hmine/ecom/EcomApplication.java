package me.hmine.ecom;

import me.hmine.ecom.entities.Category;
import me.hmine.ecom.entities.Product;
import me.hmine.ecom.repositories.CategoryRepository;
import me.hmine.ecom.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EcomApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcomApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(ProductRepository prodRepo, CategoryRepository catRepo){
        return args -> {
            Stream.of("phones","laptop","gaming","printer").forEach(name->{
                Category cat = new Category();
                cat.setName(name);
                catRepo.save(cat);
            });

            catRepo.findAll().forEach(cat->{
                for (int i = 1; i < 6; i++) {
                    Product product=new Product();
                    product.setId(UUID.randomUUID().toString());
                    product.setName(cat.getName()+i);
                    product.setQuantity(1+Math.random()*50);
                    product.setPrice(100+Math.random()*5000);
                    product.setCategory(cat);
                    prodRepo.save(product);
                }
            });
        };
    }
}
