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

import com.oak.entities.IncidentType;
import com.oak.repositories.IncidentTypeRepo;

@RestController
public class IncidentTypeController {

	@Autowired
	IncidentTypeRepo incidentTypeRepo;

	@RequestMapping(value = "/incidentTypes", produces = "application/json", method = RequestMethod.GET)
	public List<IncidentType> getAllIncidentTypes() throws JsonParseException,
			JsonMappingException, IOException {
		List<IncidentType> types = incidentTypeRepo.getIncidentTypes();
		if (types == null || types.isEmpty()) {
			return null;
		}
		return types;
	}

	@RequestMapping(value = "/incidentTypes/{incidentId}", produces = "application/json", method = RequestMethod.GET)
	public IncidentType getIncidentTypeById(@PathVariable("incidentId") long id)
			throws JsonParseException, JsonMappingException, IOException {
		IncidentType type = incidentTypeRepo.getIncidentTypesById(id);
		if (type == null) {
			return null;
		}
		return type;
	}

	@RequestMapping(value = "/incidentTypes", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createIncidentType(
			@RequestBody IncidentType typeVO) throws JsonGenerationException,
			JsonMappingException, IOException {
		incidentTypeRepo.createIncidentType(typeVO);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/incidentTypes", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<IncidentType> updateIncidentType(
			@RequestBody IncidentType typeVO) throws JsonGenerationException,
			JsonMappingException, IOException {
		incidentTypeRepo.updateIncidentType(typeVO);
		return new ResponseEntity<IncidentType>(typeVO, HttpStatus.OK);
	}

	@RequestMapping(value = "/incidentTypes/{id}", produces = "application/json", method = RequestMethod.DELETE)
	public void deleteIncidentType(@PathVariable long id) {
		incidentTypeRepo.deleteIncidentType(id);
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
