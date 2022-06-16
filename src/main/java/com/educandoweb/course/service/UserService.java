package com.educandoweb.course.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.service.exceptions.DatabaseException;
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
		try {
			repostory.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public User update(Long id, User obj) {
		try {
			User entity = repostory.getReferenceById(id);
			updateData(entity, obj);
			return repostory.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}

}
