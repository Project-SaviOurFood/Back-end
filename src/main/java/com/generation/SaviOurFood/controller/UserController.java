package com.generation.SaviOurFood.controller;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.generation.SaviOurFood.model.User;
import com.generation.SaviOurFood.repository.UserRepository;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		return ResponseEntity.ok(userRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id) {
		return userRepository.findById(id).map(ResponseEntity::ok)
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<User>> getByName(@PathVariable String name) {
		return ResponseEntity.ok(userRepository.findAllByNameContainingIgnoreCase(name));

	}
	
	@PostMapping
	public ResponseEntity<User>post(@Valid @RequestBody User user){
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(userRepository.save(user));
    }
    @PutMapping
      public ResponseEntity<User>put(@Valid @RequestBody User user){
    	  if (userRepository.existsById(user.getId())) {
    			  return ResponseEntity.status(HttpStatus.OK)
    					  .body(userRepository.save(user));
    	  }
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not Exists!",null);
		}
     

     @ResponseStatus(HttpStatus.NO_CONTENT)
     @DeleteMapping("/{id}")
     public void delete (@PathVariable Long id) {
			 Optional<User> user = userRepository.findById(id);
			 if (user.isEmpty())
				 throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			 userRepository.deleteById(id);

		 }

}
