package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.States;
import com.oak.repositories.StateRepo;

@Service("stateService")
public class StateService {

	@Autowired
	StateRepo statesRepo;

	public List<States> getStates() {
		return statesRepo.getStates();
	}

	public States getStateById(long id) {
		return statesRepo.getStateById(id);
	}

	public void createState(States state) {

		statesRepo.createState(state);

	}

	public void updateState(States state) {

		statesRepo.updateState(state);

	}

	public void deleteStateById(long id) {

		statesRepo.deleteStateById(id);

	}

}
