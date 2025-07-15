package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired private UserRepository userRepository;

	@Transactional
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}

	@Transactional
	public User getUser(Long id) {
		return this.userRepository.findById(id).orElse(null);
	}
	
	public Iterable<User> getAllUser(){
		return this.userRepository.findAll();
	}
}
