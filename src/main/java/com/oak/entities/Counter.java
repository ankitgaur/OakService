package com.oak.entities;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("counters")
public class Counter {
	
	@PrimaryKey
	private String name;
	private long val;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getVal() {
		return val;
	}
	public void setVal(long val) {
		this.val = val;
	}
	
	
	
}
