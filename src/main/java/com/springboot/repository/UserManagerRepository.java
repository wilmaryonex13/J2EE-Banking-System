package com.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.entities.UserManager;

@Repository
public interface UserManagerRepository extends CrudRepository<UserManager, Integer>{
	public List<UserManager> findAllByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
	public List<UserManager> findAllByOrderByFirstNameAsc();
}
