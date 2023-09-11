package com.generation.SaviOurFood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.SaviOurFood.model.User;

public interface UserRepository extends JpaRepository <User ,Long>{
	public List <User>findAllByNameContainingIgnoreCase(@Param("name")String name);

}
