package com.generation.SaviOurFood.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.SaviOurFood.model.User;

import javax.swing.text.html.Option;

public interface UserRepository extends JpaRepository <User ,Long>{
	 List <User>findAllByNameContainingIgnoreCase(@Param("name")String name);
	 Optional<User> findByEmail(String user);

}
