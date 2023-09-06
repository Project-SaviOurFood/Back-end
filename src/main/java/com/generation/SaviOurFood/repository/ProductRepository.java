package com.generation.SaviOurFood.repository;

import com.generation.SaviOurFood.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    public List<Product> findAllByProductContainingIgnoreCase(@Param("name") String name);


}
