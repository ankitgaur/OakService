package com.oak.vo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.oak.config.OakConstants;
import com.oak.entities.Incident;

public class IncidentVO {

	private String id;
	private String type;
	private String image;
	private String state;
	private String govt;
	private String description;
	private Map<String, String> questions;
	private String status;
	private Long reportDate;
	private String createdBy;	
	private String author;	
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
		id = incident.getAlias();
		type = incident.getType();
		image=incident.getImage();
		state = incident.getState();
		govt = incident.getGovt();
		description = incident.getDescription();
		status = incident.getStatus();
		reportDate = incident.getReportDate();
		createdBy = incident.getIncidentKey().getCreatedBy();
		createdOn = incident.getIncidentKey().getCreatedOn();
		author = incident.getAuthor();
		SimpleDateFormat sdf = new SimpleDateFormat(OakConstants.DATE_FORMAT);
		if (incident.getQuestions() != null
				&& !incident.getQuestions().isEmpty()) {
			Map<String, String> map = new HashMap<String, String>();
			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(incident.getQuestions(),
					new TypeReference<HashMap<String, String>>() {
					});
			questions = map;
		}
		
		if (createdOn != null) {
			this.createdOnStr = sdf.format(new Date(createdOn));
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

	public void setReportDate(Long reportDate) {
		this.reportDate = reportDate;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
