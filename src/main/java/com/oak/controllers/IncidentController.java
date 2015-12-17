package com.oak.controllers;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oak.entities.Incident;
import com.oak.service.IncidentService;

@RestController
public class IncidentController {

	@Autowired
	IncidentService incidentService;

	@RequestMapping(value = "/incidents", produces = "application/json", method = RequestMethod.GET)
	public List<Incident> getIncidents() throws JsonGenerationException,
			JsonMappingException, IOException {
		List<Incident> incidents = incidentService.getIncidents();
		if (incidents == null || incidents.isEmpty()) {
			return null;
		}

		return incidents;
	}

	@RequestMapping(value = "/incidents/{incidentID}", produces = "application/json", method = RequestMethod.GET)
	public Incident getIncidentById(@PathVariable("incidentID") long incidentID)
			throws JsonParseException, JsonMappingException, IOException {
		Incident incident = incidentService.getIncidentById(incidentID);
		if (incident == null) {
			return null;
		}
		return incident;
	}

	@RequestMapping(value = "/incidents", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createIncident(@RequestBody Incident incident)
			throws JsonGenerationException, JsonMappingException, IOException {
		incidentService.createIncident(incident);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/incidents", consumes = "application/json", produces = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<Incident> updateIncident(
			@RequestBody Incident incident) throws JsonGenerationException,
			JsonMappingException, IOException {
		incidentService.updateIncident(incident);
		return new ResponseEntity<Incident>(incident, HttpStatus.OK);
	}

	@RequestMapping(value = "/incidents/{id}", produces = "application/json", method = RequestMethod.DELETE)
	public void deleteIncidentType(@PathVariable long id) {
		incidentService.deleteIncidentById(id);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody
	String handleException(Exception ex) {
		if (ex.getMessage().contains("UNIQUE KEY"))
			return "The submitted item already exists.";
		else
			System.out.println(this.getClass() + ": need handleException for: "
					+ ex.getMessage());
		return ex.getMessage();
	}
}
