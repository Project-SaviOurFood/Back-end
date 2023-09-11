package com.generation.SaviOurFood.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.SaviOurFood.Model.User;

public interface UserRepository extends JpaRepository <User ,Long>{
	public List <User>findAllByNameContainingIgnoreCase(@Param("name")String name);

}
