package com.generation.SaviOurFood.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.generation.SaviOurFood.model.Product;
import com.generation.SaviOurFood.repository.ProductRepository;
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

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll(){

        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id){

        return productRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Product>> getByAllName(@PathVariable String name){

        return ResponseEntity.ok(productRepository.findAllByProductContainingIgnoreCase(name));
    }

    @PostMapping
    public ResponseEntity<Product> post(@Valid @RequestBody Product product){

        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
    }

    @PutMapping
    public ResponseEntity<Product> put(@Valid @RequestBody Product product){

        return productRepository.findById(product.getId())
                .map(resposta -> ResponseEntity.ok(productRepository.save(product)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
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
