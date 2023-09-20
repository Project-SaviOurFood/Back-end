package com.generation.SaviOurFood.controller;

import java.util.List;
import java.util.Optional;

import com.generation.SaviOurFood.model.UserLogin;
import com.generation.SaviOurFood.service.UserService;
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

	@Autowired
	private UserService userService;


	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		return ResponseEntity.ok(userRepository.findAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id) {
		return userRepository.findById(id).map(reposta -> ResponseEntity.ok(reposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}

	@GetMapping("/user/{user}")
	public ResponseEntity<List<User>> getByName(@PathVariable String name) {
		return ResponseEntity.ok(userRepository.findAllByNameContainingIgnoreCase(name));

	}

	@PostMapping("/login")
	public ResponseEntity<UserLogin> login(@Valid @RequestBody Optional<UserLogin> userLogin) {

		return userService.authenticationUser(userLogin)
				.map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@Valid @RequestBody User user) {

		return userService.registerUser(user)
				.map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}

	@PutMapping
	public ResponseEntity<User> update(@Valid @RequestBody User user) {
		return userService.updateUser(user)
				.map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		userRepository.deleteById(id);
	}
}

	/*@PostMapping
	public ResponseEntity<User>post(@Valid @RequestBody User user){
		if (userRepository.existsById(user.getTema().getId()))
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(userRepository.save(user));

    } */

    /*  @PutMapping
      public ResponseEntity<User>put(@Valid @RequestBody User user){
    	  if (userRepository.existsById(user.getId())) {
    		  if (temaRepository.existsById(getTema().getId()))
    			  return ResponseEntity.status(HttpStatus.OK)
    					  .body(userRepository.save(user));
    		  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tema não existe!",null);
    	  }
    	  return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

     }*/

