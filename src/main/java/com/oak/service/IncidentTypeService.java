package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.IncidentType;
import com.oak.repositories.IncidentTypeRepo;

@Service("incidentTypeService")
public class IncidentTypeService {

	@Autowired
	IncidentTypeRepo incidentTypeRepo;

	public List<IncidentType> getIncidentTypes() {
		return incidentTypeRepo.getIncidentTypes();
	}

	public IncidentType getIncidentTypesById(long id) {
		return incidentTypeRepo.getIncidentTypesById(id);
	}

	public void deleteIncidentType(long id) {

		incidentTypeRepo.deleteIncidentType(id);

	}

	public void createIncidentType(IncidentType incidentType) {

		incidentTypeRepo.createIncidentType(incidentType);

	}

	public void updateIncidentType(IncidentType incidentType) {

		incidentTypeRepo.updateIncidentType(incidentType);

	}
}
