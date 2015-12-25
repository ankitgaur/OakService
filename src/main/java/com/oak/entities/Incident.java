package com.oak.entities;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.IncidentVO;

@Table("incidents")
public class Incident {

	@PrimaryKey
	private IncidentKey incidentKey;
	private String type;
	private String image;
	private String state;
	private String govt;
	private String category;
	private String description;
	private String questions;
	private String status;
	private Long reportDate;
	private String createdBy;

	public Incident() {

	}

	public Incident(IncidentVO incidentVO) {
		super();
		IncidentKey key = new IncidentKey();
		key.setCreatedOn(incidentVO.getCreatedOn());
		key.setIncidentType(incidentVO.getIncidentType());
		this.incidentKey = key;
		this.type = incidentVO.getType();
		this.state = incidentVO.getState();
		this.govt = incidentVO.getGovt();
		this.description = incidentVO.getDescription();
		this.status = incidentVO.getStatus();
		this.reportDate = incidentVO.getReportDate();
		this.createdBy = incidentVO.getCreatedBy();
		this.image = incidentVO.getImage();
		this.questions = incidentVO.getQuestions();
		this.category = incidentVO.getCategory();
	}

	public IncidentKey getIncidentKey() {
		return incidentKey;
	}

	public void setIncidentKey(IncidentKey incidentKey) {
		this.incidentKey = incidentKey;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getReportDate() {
		return reportDate;
	}

	public void setReportDate(Long reportDate) {
		this.reportDate = reportDate;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
