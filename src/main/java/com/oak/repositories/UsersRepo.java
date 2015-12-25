package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Users;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("usersRepo")
public class UsersRepo {

	@Autowired
	private OakCassandraTemplate oakCassandraTemplate;

	public List<Users> getUsers() {
		List<Users> users = oakCassandraTemplate.findAll(Users.class);
		return users;
	}

	public Users getUsersById(String id) {
		Users user = oakCassandraTemplate.findById(id, Users.class);
		return user;
	}

	public void createUser(Users user) {

		oakCassandraTemplate.create(user, Users.class);

	}

	public void updateUser(Users user) {

		oakCassandraTemplate.update(user, Users.class);

	}

	public void deleteUserById(String id) {

		oakCassandraTemplate.deleteById(id, Users.class);

	}

}
