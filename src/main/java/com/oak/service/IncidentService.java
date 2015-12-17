package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.Incident;
import com.oak.repositories.IncidentRepo;

@Service("incidentService")
public class IncidentService {

	@Autowired
	IncidentRepo incidentRepo;

	public List<Incident> getIncidents() {
		return incidentRepo.getIncidents();
	}

	public Incident getIncidentById(long id) {
		return incidentRepo.getIncidentById(id);
	}

	public void createIncident(Incident incident) {

		incidentRepo.createIncident(incident);

	}

	public void updateIncident(Incident incident) {

		incidentRepo.updateIncident(incident);

	}

	public void deleteIncidentById(long id) {

		incidentRepo.deleteIncidentById(id);

	}
}
