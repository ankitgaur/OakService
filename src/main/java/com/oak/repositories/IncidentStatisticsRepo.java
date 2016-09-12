package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oak.entities.IncidentStatistics;
import com.oak.entities.IncidentStatisticsKey;
import com.oak.utils.OakCassandraTemplate;

@Repository("incidentStatisticsRepo")
public class IncidentStatisticsRepo {

	
	//TODO : Batch Update functions
	
	@Autowired
	private OakCassandraTemplate oakCassendraTemplate;
	
	public List<IncidentStatistics> getStatistics(String name){
		
		String sql = "SELECT * FROM incident_statistics WHERE name='"+name+"'";
		List<IncidentStatistics> stats = oakCassendraTemplate.findByPartitionKey(sql, IncidentStatistics.class);
		
		return stats;
	}
	
	public List<IncidentStatistics> getStatisticsForState(String name, String state){
		
		String sql = "SELECT * FROM incident_statistics WHERE name='"+name+"' and state='"+state+ "' ALLOW FILTERING";
		List<IncidentStatistics> stats = oakCassendraTemplate.findByPartitionKey(sql, IncidentStatistics.class);
		
		return stats;
	}
	
	public void increment(IncidentStatisticsKey key){		
		String sql="update incident_statistics set val=val+1 where name='"+key.getName()+"'" 
												+  " and type='"+key.getType()+"'"
												+  " and cat='"+key.getCat()+"'"
												+  " and state='"+key.getState()+"'"
												+  " and govt='"+key.getGovt()+"'";
		oakCassendraTemplate.executeQuery(sql);		
	}
	
    public void decrement(IncidentStatisticsKey key){		
    	String sql="update incident_statistics set val=val-1 where name='"+key.getName()+"'" 
				+  " and type='"+key.getType()+"'"
				+  " and cat='"+key.getCat()+"'"
				+  " and state='"+key.getState()+"'"
				+  " and govt='"+key.getGovt()+"'";
    	oakCassendraTemplate.executeQuery(sql);			
	}

	public List<IncidentStatistics> getStatisticsForStateAndType(String name, String type,
			String state) {
		String sql = "SELECT * FROM incident_statistics WHERE name='"+name+"' and type='"+type+"' and state='"+state+ "' ALLOW FILTERING";
		List<IncidentStatistics> stats = oakCassendraTemplate.findByPartitionKey(sql, IncidentStatistics.class);
		
		return stats;
	}

}
