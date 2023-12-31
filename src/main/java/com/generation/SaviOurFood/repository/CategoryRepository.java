package com.generation.SaviOurFood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.SaviOurFood.model.Category;

public interface CategoryRepository extends JpaRepository <Category, Long>{
	
	public List <Category> findAllByDescriptionContainingIgnoreCase(@Param("description") String description);

}
