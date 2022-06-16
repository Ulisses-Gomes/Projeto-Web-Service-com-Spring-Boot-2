package com.educandoweb.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repostory;
	
	public User save(User obj) {
		return repostory.save(obj);
	}
	
	public List<User> findAll(){
		return repostory.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repostory.findById(id);
		return obj.get();
		
	}
}
