package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Incident;

@Transactional
@Repository("incidentRepo")
public class IncidentRepo {

	@Autowired
	private CassandraOperations cassandraTemplate;

	public List<Incident> getIncidents() {
		List<Incident> incidents = cassandraTemplate.select(
				"Select * from incidents", Incident.class);
		return incidents;
	}

	public Incident getIncidentById(long id) {
		Incident incident = cassandraTemplate.selectOneById(Incident.class, id);
		return incident;
	}

	public void createIncident(Incident incident) {

		cassandraTemplate.insert(incident);

	}

	public void updateIncident(Incident incident) {

		cassandraTemplate.update(incident);

	}

	public void deleteIncidentById(long id) {

		cassandraTemplate.deleteById(Incident.class, id);

	}

}
