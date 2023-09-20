package com.generation.SaviOurFood.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.generation.SaviOurFood.model.Product;
import com.generation.SaviOurFood.repository.CategoryRepository;
import com.generation.SaviOurFood.repository.ProductRepository;
import com.generation.SaviOurFood.repository.UserRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){

        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id){

        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Product>> getByAllName(@PathVariable String name){

        return ResponseEntity.ok(productRepository.findAllByNameContainingIgnoreCase(name));
    }

    @PostMapping
    public ResponseEntity<Product> post(@Valid @RequestBody Product product){
        if(userRepository.existsById(product.getUser().getId())){
            if (categoryRepository.existsById(product.getCategory().getId())) {
                return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ueer not found!!");
    }

    @PutMapping
    public ResponseEntity<Product> put(@Valid @RequestBody Product product){
        if (productRepository.existsById(product.getId())) {
            return productRepository.findById(product.getId())
                .map(resposta -> ResponseEntity.ok(productRepository.save(product)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!!");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional <Product> product = productRepository.findById(id);
                if(product.isEmpty())
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                productRepository.deleteById(id);
    }
}
