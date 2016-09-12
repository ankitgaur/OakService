package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.States;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("stateRepo")
public class StateRepo {

	@Autowired
	private OakCassandraTemplate oakCassandraTemplate;

	public List<States> getStates() {
		List<States> states = oakCassandraTemplate.findAll(States.class);
		return states;
	}

	public States getStateById(String id) {
		States state = oakCassandraTemplate.findById(id, States.class);
		return state;
	}

	public void createState(States state) {

		oakCassandraTemplate.create(state, States.class);

	}

	public void updateState(States state) {

		oakCassandraTemplate.update(state, States.class);

	}

	public void deleteStateById(String id) {

		oakCassandraTemplate.deleteById(id, States.class);

	}

}
