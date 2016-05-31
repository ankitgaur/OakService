package com.oak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.Counter;
import com.oak.repositories.CountersRepo;

@Service("counterService")
public class CounterService {

	@Autowired
	private CountersRepo countersRepo;

	public long getCounterValue(String name) {
		Counter counter = countersRepo.getCounterValue(name);
		if (counter != null) {
			return counter.getVal();
		}
		return 0;
	}

	public void incrementCounter(String name) {
		countersRepo.increment(name);
	}

	public void decrementCounter(String name) {
		countersRepo.decrement(name);
	}
}