package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Groups;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("groupsRepo")
public class GroupsRepo {

	@Autowired
	private OakCassandraTemplate oakCassandraTemplate;

	public List<Groups> getGroups() {
		List<Groups> groups = oakCassandraTemplate.findAll(Groups.class);
		return groups;
	}

	public Groups getGroupById(String id) {
		Groups group = oakCassandraTemplate.findById(id, Groups.class);
		return group;
	}

	public void createGroup(Groups group) {
		oakCassandraTemplate.create(group, Groups.class);

	}

	public void updateGroup(Groups group) {

		oakCassandraTemplate.update(group, Groups.class);

	}

	public void deleteGroupById(String id) {

		oakCassandraTemplate.deleteById(id, Groups.class);

	}

}
