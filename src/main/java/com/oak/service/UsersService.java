package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.Users;
import com.oak.repositories.UsersRepo;

@Service("usersService")
public class UsersService {

	@Autowired
	UsersRepo userRepo;

	public List<Users> getUsers() {
		return userRepo.getUsers();
	}

	public Users getUserById(String id) {
		return userRepo.getUsersById(id);
	}

	public void createUser(Users user) {

		userRepo.createUser(user);

	}

	public void updateUser(Users user) {

		userRepo.updateUser(user);

	}

	public void deleteUserById(String id) {

		userRepo.deleteUserById(id);

	}

}
