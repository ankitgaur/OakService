package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.User;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("usersRepo")
public class UsersRepo {

	@Autowired
	private OakCassandraTemplate oakCassandraTemplate;

	public List<User> getUsers() {
		List<User> users = oakCassandraTemplate.findAll(User.class);
		return users;
	}

	public User getUsersById(String id) {
		User user = oakCassandraTemplate.findById(id, User.class);
		return user;
	}

	public void createUser(User user) {

		oakCassandraTemplate.create(user, User.class);

	}

	public void updateUser(User user) {

		oakCassandraTemplate.update(user, User.class);

	}

	public void deleteUserById(String id) {

		oakCassandraTemplate.deleteById(id, User.class);

	}

}
