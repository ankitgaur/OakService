package com.oak.entities;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("incident_statistics")
public class IncidentStatistics {

	@PrimaryKey
	private IncidentStatisticsKey key;
	private Long val;
	
	public IncidentStatisticsKey getKey() {
		return key;
	}
	public void setKey(IncidentStatisticsKey key) {
		this.key = key;
	}
	public Long getVal() {
		return val;
	}
	public void setVal(Long val) {
		this.val = val;
	}
	@Override
	public String toString() {
		return key.getName()+","+key.getType()+","+key.getCat()+","+key.getState()+","+key.getGovt()+","+val;
	}
	
	
		
}
