package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Incident;
import com.oak.entities.IncidentKey;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("incidentRepo")
public class IncidentRepo {

	@Autowired
	private OakCassandraTemplate oakCassendraTemplate;

	public List<Incident> getIncidents() {
		List<Incident> incidents = oakCassendraTemplate.findAll(Incident.class);
		return incidents;
	}

	public Incident getIncidentById(IncidentKey id) {
		Incident incident = oakCassendraTemplate.findById(id, Incident.class);
		return incident;
	}

	public void createIncident(Incident incident) {

		oakCassendraTemplate.create(incident, Incident.class);

	}

	public void updateIncident(Incident incident) {

		oakCassendraTemplate.update(incident, Incident.class);

	}

	public void deleteIncidentById(IncidentKey id) {

		oakCassendraTemplate.deleteById(id, Incident.class);

	}

}
