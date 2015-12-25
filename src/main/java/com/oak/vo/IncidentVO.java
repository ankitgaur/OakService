package com.oak.vo;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.oak.entities.Incident;

public class IncidentVO {

	private String incidentType;
	private Long createdOn;
	private String type;
	private String category;
	private String image;
	private String state;
	private String govt;
	private String description;
	private String questions;
	private String status;
	private Long reportDate;
	private String createdBy;

	private String createdOnDate;
	private String reportDateStr;

	public IncidentVO() {
		super();
	}

	public IncidentVO(Incident incident) throws JsonGenerationException,
			JsonMappingException, IOException {

		super();
		this.incidentType = incident.getIncidentKey().getIncidentType();
		this.createdOn = incident.getIncidentKey().getCreatedOn();
		this.type = incident.getType();
		this.state = incident.getState();
		this.govt = incident.getGovt();
		this.description = incident.getDescription();
		this.status = incident.getStatus();
		this.reportDate = incident.getReportDate();
		this.createdBy = incident.getCreatedBy();
		this.image = incident.getImage();
		this.questions = incident.getQuestions();
		this.category = incident.getCategory();

	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(String incidentType) {
		this.incidentType = incidentType;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
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

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getReportDate() {
		return reportDate;
	}

	public void setReportDate(Long reportDate) {
		this.reportDate = reportDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedOnDate() {
		return createdOnDate;
	}

	public void setCreatedOnDate(String createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

	public String getReportDateStr() {
		return reportDateStr;
	}

	public void setReportDateStr(String reportDateStr) {
		this.reportDateStr = reportDateStr;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
