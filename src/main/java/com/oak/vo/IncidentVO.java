package com.oak.vo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.oak.entities.Incident;

public class IncidentVO {

	private String id;
	private String type;
	private String state;
	private String govt;
	private String description;
	private Map<String, String> questions;
	private String status;
	private Long reportDate;
	private String createdBy;
	private Long createdOn;
	private String createdOnStr;
	private String reportDateStr;

	public String getCreatedOnStr() {
		return createdOnStr;
	}

	public void setCreatedOnStr(String createdOnStr) {
		this.createdOnStr = createdOnStr;
	}

	public String getReportDateStr() {
		return reportDateStr;
	}

	public void setReportDateStr(String reportDateStr) {
		this.reportDateStr = reportDateStr;
	}

	public IncidentVO() {

	}

	public IncidentVO(Incident incident) throws JsonGenerationException,
			JsonMappingException, IOException {
		id = incident.getIncidentKey().getIncidentType() + "_"
				+ incident.getIncidentKey().getCreatedOn();
		type = incident.getType();

		state = incident.getState();
		govt = incident.getGovt();
		description = incident.getDescription();
		status = incident.getStatus();
		reportDate = incident.getReportDate();
		createdBy = incident.getCreatedBy();
		createdOn = incident.getIncidentKey().getCreatedOn();

		if (incident.getQuestions() != null
				&& !incident.getQuestions().isEmpty()) {
			Map<String, String> map = new HashMap<String, String>();
			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(incident.getQuestions(),
					new TypeReference<HashMap<String, String>>() {
					});
			questions = map;
		}

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getGovt() {
		return govt;
	}

	public void setGovt(String govt) {
		this.govt = govt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getQuestions() {
		return questions;
	}

	public void setQuestions(Map<String, String> questions) {
		this.questions = questions;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getReportDate() {
		return reportDate;
	}

	public void setReportDate(long reportDate) {
		this.reportDate = reportDate;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

}
