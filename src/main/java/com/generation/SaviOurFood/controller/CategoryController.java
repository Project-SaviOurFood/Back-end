package com.generation.SaviOurFood.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.generation.SaviOurFood.model.Category;
import com.generation.SaviOurFood.repository.CategoryRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping
	public ResponseEntity<List<Category>> getAll(){
		return ResponseEntity.ok(categoryRepository.findAll());		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoy(@PathVariable Long id){
		return categoryRepository.findById(id)
				.map(ResponseEntity::ok) 
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/description/{description}")
	public ResponseEntity<List<Category>> getDescription(@PathVariable String description){
		return ResponseEntity.ok(categoryRepository.findAllByDescriptionContainingIgnoreCase(description));		
	}
	
	@PostMapping
	public ResponseEntity<Category> postCategory(@Valid @RequestBody Category category){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryRepository.save(category));	
	}
	
	@PutMapping
	public ResponseEntity<Category> putCategory(@Valid @RequestBody Category category){
		return categoryRepository.findById(category.getId())
				.map(response -> ResponseEntity.status(HttpStatus.OK)
						.body(categoryRepository.save(category)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable Long id){
		Optional<Category> category = categoryRepository.findById(id); 
		
		if (category.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not exists", null);
		
		categoryRepository.deleteById(id);
	}
}











