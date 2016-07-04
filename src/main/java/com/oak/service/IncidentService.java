package com.oak.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.Alias;
import com.oak.entities.Incident;
import com.oak.entities.IncidentKey;
import com.oak.repositories.IncidentRepo;

@Service("incidentService")
public class IncidentService {

	@Autowired
	IncidentRepo incidentRepo;
	
	@Autowired
	AliasService aliasService;

	public List<Incident> getIncidents() {
		return incidentRepo.getIncidents();
	}

	public List<Incident> getTopIncidentsByIncidentsTypes(String incidentype,
			int limit) {

		return incidentRepo.getTopIncidentsByIncidentsTypes(incidentype, limit);

	}

	public List<Incident> getTopIncidentsByLimit(int limit) {

		return incidentRepo.getTopIncidentsByLimit(limit);

	}
	
	public List<Incident> getIncidentsAfterId(long createdOn, int limit) {

		return incidentRepo.getIncidentsAfterId(createdOn, limit);

	}

	public Incident getIncidentById(IncidentKey id) {
		return incidentRepo.getIncidentById(id);
	}

	public void createIncident(Incident incident) {

		UUID uuid = UUID.randomUUID();
		Alias alias = new Alias();
		alias.setId(uuid.toString());
		alias.setCategory(incident.getIncidentKey().getIncidentType());
		alias.setCreatedby(incident.getIncidentKey().getCreatedBy());
		alias.setCreatedon(incident.getIncidentKey().getCreatedOn());
		aliasService.createAlias(alias);
			
		incident.setAlias(alias.getId());
		incidentRepo.createIncident(incident);

	}

	public void updateIncident(Incident incident) {

		incidentRepo.updateIncident(incident);

	}

	public void deleteIncidentById(IncidentKey id) {

		incidentRepo.deleteIncidentById(id);

	}
}
