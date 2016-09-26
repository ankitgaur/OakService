package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.User;
import com.oak.repositories.UsersRepo;

@Service("usersService")
public class UsersService {

	@Autowired
	UsersRepo userRepo;

	public List<User> getUsers() {
		return userRepo.getUsers();
	}

	public User getUserById(String id) {
		return userRepo.getUsersById(id);
	}

	public void createUser(User user) {

		userRepo.createUser(user);

	}

	public void updateUser(User user) {

		userRepo.updateUser(user);

	}
	
	public void activateUser(String id){
		
		User user = getUserById(id);
		user.setActivated(true);
		updateUser(user);
		
	}

	public void deleteUserById(String id) {

		userRepo.deleteUserById(id);

	}

}
