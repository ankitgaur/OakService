package com.oak.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oak.entities.Counter;
import com.oak.utils.OakCassandraTemplate;

@Repository("countersRepo")
public class CountersRepo {

	@Autowired
	private OakCassandraTemplate oakCassendraTemplate;
	
	public void createCounter(String name){
		
		Counter counter = new Counter();
		counter.setName(name);
		counter.setVal(0);
		oakCassendraTemplate.create(counter, Counter.class);
	}
	
	public Counter getCounterValue(String name){
		Counter counter = oakCassendraTemplate.findById(name, Counter.class);
		return counter;
	}
	
	public void increment(String counterName){		
		String sql="update oak.counters set val=val+1 where name='"+counterName+"'";
		oakCassendraTemplate.executeQuery(sql);		
	}
	
    public void decrement(String counterName){		
		String sql="update oak.counters set val=val-1 where name='"+counterName+"'";
		oakCassendraTemplate.executeQuery(sql);		
	}
}
