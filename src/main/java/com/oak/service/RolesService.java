package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.Roles;
import com.oak.repositories.RolesRepo;

@Service("rolesService")
public class RolesService {

	@Autowired
	RolesRepo rolesRepo;

	public List<Roles> getRoles() {
		return rolesRepo.getRoles();
	}

	public Roles getRoleById(String id) {
		return rolesRepo.getRoleById(id);
	}

	public void createRole(Roles role) {

		rolesRepo.createRole(role);

	}

	public void updateRoles(Roles role) {

		rolesRepo.updateRoles(role);

	}

	public void deleteRolesById(String id) {

		rolesRepo.deleteRolesById(id);

	}

}
