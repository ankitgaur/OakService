package com.oak.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oak.utils.OakCassandraTemplate;

@Repository("countersRepo")
public class CountersRepo {

	@Autowired
	private OakCassandraTemplate oakCassendraTemplate;
	
	public void increment(String counterName){		
		String sql="update oak.counters set val=val+1 where name='"+counterName+"'";
		oakCassendraTemplate.executeQuery(sql);		
	}
	
    public void decrement(String counterName){		
		String sql="update oak.counters set val=val-1 where name='"+counterName+"'";
		oakCassendraTemplate.executeQuery(sql);		
	}
}
