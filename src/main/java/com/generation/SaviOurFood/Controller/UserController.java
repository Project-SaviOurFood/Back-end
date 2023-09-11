package com.generation.SaviOurFood.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.SaviOurFood.Model.User;
import com.generation.SaviOurFood.Repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/all")
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
	
	/*@PostMapping
	public ResponseEntity<User>post(@Valid @RequestBody User user){
		if (userRepository.existsById(user.getTema().getId()))
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(userRepository.save(user));
		*/
    }
    /*  @PutMapping
      public ResponseEntity<User>put(@Valid @RequestBody User user){
    	  if (userRepository.existsById(user.getId())) {
    		  if (temaRepository.existsById(getTema().getId()))
    			  return ResponseEntity.status(HttpStatus.OK)
    					  .body(userRepository.save(user));
    		  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tema não existe!",null);
    	  }
    	  return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	  */
     }
     
     /*
     @ResponseStatus(HttpStatus.NO_CONTENT)
     @DeleteMapping("/{id}")
     public void delete (@PathVariable Long id) {
    	 Optional<User>user=userRepository.findById(id);
    	 if(user.isEmpty())
    		 throw new ResponseStatusException(HttpStaus.NOT_FOUND);
    	 userRepository.deleteAllById(id);
    	 */
     

}
