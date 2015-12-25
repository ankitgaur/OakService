package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.Groups;
import com.oak.repositories.GroupsRepo;

@Service("groupsService")
public class GroupsService {

	@Autowired
	GroupsRepo groupsRepo;

	public List<Groups> getGroups() {
		return groupsRepo.getGroups();
	}

	public Groups getGroupById(String id) {
		return groupsRepo.getGroupById(id);
	}

	public void createGroup(Groups group) {
		groupsRepo.createGroup(group);

	}

	public void updateGroup(Groups group) {

		groupsRepo.updateGroup(group);

	}

	public void deleteGroupById(String id) {

		groupsRepo.deleteGroupById(id);

	}

}
