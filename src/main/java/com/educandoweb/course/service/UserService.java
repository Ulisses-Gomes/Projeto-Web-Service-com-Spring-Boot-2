package com.educandoweb.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.service.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repostory;

	public List<User> findAll() {
		return repostory.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = repostory.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User save(User obj) {
		return repostory.save(obj);
	}
	
	public void delete(Long id) {
		repostory.deleteById(id);
	}
	
	public User update(Long id, User obj) {
		User entity = repostory.getReferenceById(id);
		updateData(entity, obj);
		return repostory.save(entity);
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}

	

	
}
