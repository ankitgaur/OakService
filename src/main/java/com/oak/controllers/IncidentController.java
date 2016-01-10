package com.oak.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oak.entities.Incident;
import com.oak.entities.IncidentKey;
import com.oak.service.IncidentService;
import com.oak.vo.IncidentVO;

@RestController
public class IncidentController {

	@Autowired
	IncidentService incidentService;

	@CrossOrigin
	@RequestMapping(value = "/incidents", produces = "application/json", method = RequestMethod.GET)
	public List<IncidentVO> getIncidents() throws JsonGenerationException,
			JsonMappingException, IOException {
		List<Incident> incidents = incidentService.getIncidents();

		List<IncidentVO> incidentVO = new ArrayList<IncidentVO>();
		for (Incident incident : incidents) {
			IncidentVO vo = new IncidentVO(incident);
			Date createOn = new Date(incident.getIncidentKey().getCreatedOn());
			Date reportDate = new Date(incident.getReportDate());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String createDateText = dateFormatter.format(createOn);
			String reportDateText = dateFormatter.format(reportDate);
			vo.setCreatedOnStr(createDateText);
			vo.setReportDateStr(reportDateText);
			incidentVO.add(vo);
		}

		return incidentVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/incidents/{incidentype}/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<IncidentVO> getTopIncidentsByIncTypes(
			@PathVariable("incidentype") String incidentype,
			@PathVariable("limit") int limit) throws JsonGenerationException,
			JsonMappingException, IOException {
		List<Incident> incidents = incidentService
				.getTopIncidentsByIncidentsTypes(incidentype, limit);

		List<IncidentVO> incidentVO = new ArrayList<IncidentVO>();
		for (Incident incident : incidents) {
			IncidentVO vo = new IncidentVO(incident);
			Date createOn = new Date(incident.getIncidentKey().getCreatedOn());
			Date reportDate = new Date(incident.getReportDate());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String createDateText = dateFormatter.format(createOn);
			String reportDateText = dateFormatter.format(reportDate);
			vo.setCreatedOnStr(createDateText);
			vo.setReportDateStr(reportDateText);
			incidentVO.add(vo);
		}

		return incidentVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/incidents/limit/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<IncidentVO> getTopIncidentsByLimit(
			@PathVariable("limit") int limit) throws JsonGenerationException,
			JsonMappingException, IOException {
		List<Incident> incidents = incidentService
				.getTopIncidentsByLimit(limit);

		List<IncidentVO> incidentVO = new ArrayList<IncidentVO>();
		for (Incident incident : incidents) {
			IncidentVO vo = new IncidentVO(incident);
			Date createOn = new Date(incident.getIncidentKey().getCreatedOn());
			Date reportDate = new Date(incident.getReportDate());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String createDateText = dateFormatter.format(createOn);
			String reportDateText = dateFormatter.format(reportDate);
			vo.setCreatedOnStr(createDateText);
			vo.setReportDateStr(reportDateText);
			incidentVO.add(vo);
		}

		return incidentVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/incidents/{incidentID}", produces = "application/json", method = RequestMethod.GET)
	public IncidentVO getIncidentById(
			@PathVariable("incidentID") String incidentID)
			throws JsonParseException, JsonMappingException, IOException {

		String incidentKey[] = incidentID.split("_");
		IncidentKey key = new IncidentKey(incidentKey[0],
				Long.parseLong(incidentKey[1]));
		Incident incident = incidentService.getIncidentById(key);
		if (incident == null) {
			return null;
		}
		IncidentVO incidentVO = new IncidentVO(incident);
		Date createOn = new Date(incident.getIncidentKey().getCreatedOn());
		Date reportDate = new Date(incident.getReportDate());
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"dd-MM-yyyy HH:mm:ss");
		String createDateText = dateFormatter.format(createOn);
		String reportDateText = dateFormatter.format(reportDate);
		incidentVO.setCreatedOnStr(createDateText);
		incidentVO.setReportDateStr(reportDateText);

		return incidentVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/incidents", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createIncident(
			@RequestBody IncidentVO incidentVO) throws JsonGenerationException,
			JsonMappingException, IOException, ParseException {

		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date dt = ft.parse(ft.format(dNow));
		incidentVO.setCreatedOn(dt.getTime());
		incidentVO.setReportDate(dt.getTime());
		incidentService.createIncident(new Incident(incidentVO));
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(value = "/incidents/{id}", consumes = "application/json", produces = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<IncidentVO> updateIncident(
			@RequestBody IncidentVO IncidentVO, @PathVariable("id") String id)
			throws JsonGenerationException, JsonMappingException, IOException {

		System.out.println("Updating User " + id);
		String incidentKey[] = id.split("_");
		IncidentKey key = new IncidentKey(incidentKey[0],
				Long.parseLong(incidentKey[1]));
		Incident incident = incidentService.getIncidentById(key);
		if (incident == null) {
			return null;
		}
		IncidentVO.setCreatedOn(Long.parseLong(incidentKey[1]));
		IncidentVO.setType(incidentKey[0]);
		incidentService.updateIncident(new Incident(IncidentVO));
		return new ResponseEntity<IncidentVO>(IncidentVO, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/incidents/{incidentID}", produces = "application/json", method = RequestMethod.DELETE)
	public void deleteIncidentType(@PathVariable("incidentID") String incidentID) {
		String incidentKey[] = incidentID.split("_");
		IncidentKey key = new IncidentKey(incidentKey[0],
				Long.parseLong(incidentKey[1]));
		incidentService.deleteIncidentById(key);
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
