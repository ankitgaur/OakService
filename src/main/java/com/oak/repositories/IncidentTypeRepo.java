package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.IncidentType;
import com.oak.utils.OakCassandraTemplate;

@Repository("incidentTypeRepo")
@Transactional
public class IncidentTypeRepo {

	@Autowired
	private OakCassandraTemplate oakCassendraTemplate;

	public List<IncidentType> getIncidentTypes() {
		List<IncidentType> IncidentTypes = oakCassendraTemplate
				.findAll(IncidentType.class);
		return IncidentTypes;
	}

	public IncidentType getIncidentTypesById(long id) {
		IncidentType IncidentType = oakCassendraTemplate.findById(id,
				IncidentType.class);
		return IncidentType;
	}

	public void deleteIncidentType(long id) {

		oakCassendraTemplate.deleteById(id, IncidentType.class);

	}

	public void createIncidentType(IncidentType incidentType) {

		oakCassendraTemplate.create(incidentType, IncidentType.class);

	}

	public void updateIncidentType(IncidentType incidentType) {

		oakCassendraTemplate.update(incidentType, IncidentType.class);

	}
}
