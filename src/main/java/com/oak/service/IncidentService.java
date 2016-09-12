package com.oak.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.constants.incidents.IncidentStatisticsConstants;
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
	
	@Autowired
	IncidentStatisticsService incidentStatisticsService;

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

	public void createIncident(Incident incident) throws JsonParseException, JsonMappingException, IOException {

		UUID uuid = UUID.randomUUID();
		Alias alias = new Alias();
		alias.setId(uuid.toString());
		alias.setCategory(incident.getIncidentKey().getIncidentType());
		alias.setCreatedby(incident.getIncidentKey().getCreatedBy());
		alias.setCreatedon(incident.getIncidentKey().getCreatedOn());
		aliasService.createAlias(alias);
			
		incident.setAlias(alias.getId());
		incidentRepo.createIncident(incident);

		//get category 
				String cat = null;
				if (incident.getQuestions() != null
						&& !incident.getQuestions().isEmpty()) {
					Map<String, String> map = new HashMap<String, String>();
					ObjectMapper mapper = new ObjectMapper();
					map = mapper.readValue(incident.getQuestions(),
							new TypeReference<HashMap<String, String>>() {
							});
					cat = map.get(IncidentStatisticsConstants.getKeyByType(incident.getType()));
				}
				
				//update stats
				incidentStatisticsService.incrementStatsCounter(incident.getType(), cat, incident.getState(), incident.getGovt());
		
	}

	public void updateIncident(Incident incident) {

		incidentRepo.updateIncident(incident);

	}

	public void deleteIncidentById(IncidentKey id) throws JsonParseException, JsonMappingException, IOException {

		Incident incident = getIncidentById(id);
		incidentRepo.deleteIncidentById(id);
		
				
		//get category 
		String cat = null;
		if (incident.getQuestions() != null
				&& !incident.getQuestions().isEmpty()) {
			Map<String, String> map = new HashMap<String, String>();
			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(incident.getQuestions(),
					new TypeReference<HashMap<String, String>>() {
					});
			cat = map.get(IncidentStatisticsConstants.getKeyByType(incident.getType()));
		}
		
		//update stats
		incidentStatisticsService.decrementStatsCounter(incident.getType(), cat, incident.getState(), incident.getGovt());
	}
}

