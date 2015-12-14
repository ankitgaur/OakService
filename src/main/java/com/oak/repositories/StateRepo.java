package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.States;

@Transactional
@Repository("stateRepo")
public class StateRepo {

	@Autowired
	private CassandraOperations cassandraTemplate;

	public List<States> getStates() {
		List<States> states = cassandraTemplate.select("Select * from states",
				States.class);
		return states;
	}

	public States getStateById(long id) {
		States state = cassandraTemplate.selectOneById(States.class, id);
		return state;
	}

	public void createState(States state) {

		cassandraTemplate.insert(state);

	}

	public void updateState(States state) {

		cassandraTemplate.update(state);

	}

	public void deleteStateById(long id) {

		cassandraTemplate.deleteById(States.class, id);

	}

}
