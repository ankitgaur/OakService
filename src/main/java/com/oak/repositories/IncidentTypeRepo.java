package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import com.oak.entities.IncidentType;

@Repository("incidentTypeRepo")
public class IncidentTypeRepo {

	@Autowired
	private CassandraOperations cassandraTemplate;

	public List<IncidentType> getIncidentTypes() {
		List<IncidentType> IncidentTypes = cassandraTemplate.select(
				"Select * from incident_types", IncidentType.class);
		return IncidentTypes;
	}

	public IncidentType getIncidentTypesById(long id) {
		IncidentType IncidentType = cassandraTemplate.selectOneById(
				IncidentType.class, id);
		return IncidentType;
	}

	public void deleteIncidentType(long id) {

		cassandraTemplate.deleteById(IncidentType.class, id);

	}

	public void createIncidentType(IncidentType incidentType) {

		cassandraTemplate.insert(incidentType);

	}

	public void updateIncidentType(IncidentType incidentType) {

		cassandraTemplate.update(incidentType);

	}
}
