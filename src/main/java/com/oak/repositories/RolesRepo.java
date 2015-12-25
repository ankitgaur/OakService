package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Roles;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("rolesRepo")
public class RolesRepo {

	@Autowired
	private OakCassandraTemplate oakCassandraTemplate;

	public List<Roles> getRoles() {
		List<Roles> roles = oakCassandraTemplate.findAll(Roles.class);
		return roles;
	}

	public Roles getRoleById(String id) {
		Roles role = oakCassandraTemplate.findById(id, Roles.class);
		return role;
	}

	public void createRole(Roles role) {

		oakCassandraTemplate.create(role, Roles.class);

	}

	public void updateRoles(Roles role) {

		oakCassandraTemplate.update(role, Roles.class);

	}

	public void deleteRolesById(String id) {

		oakCassandraTemplate.deleteById(id, Roles.class);

	}

}
