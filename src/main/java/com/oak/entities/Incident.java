package com.oak.entities;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.IncidentVO;

@Table("incidents")
public class Incident {

	@PrimaryKey
	private IncidentKey incidentKey;
	private String type;
	private String image;
	private String alias;
	private String state;
	private String govt;
	private String category;
	private String description;
	private String questions;
	private String status;
	private String author;
	private Long reportDate;
	

	public Incident() {

	}

	public Incident(IncidentVO incidentVO) throws JsonGenerationException, JsonMappingException, IOException {
		super();
		IncidentKey key = new IncidentKey();
		key.setCreatedOn(incidentVO.getCreatedOn());
		key.setIncidentType(incidentVO.getType());
		key.setCreatedBy(incidentVO.getCreatedBy());
		this.incidentKey = key;
		this.type = incidentVO.getType();
		this.state = incidentVO.getState();
		this.govt = incidentVO.getGovt();
		this.description = incidentVO.getDescription();
		this.status = incidentVO.getStatus();
		this.reportDate = incidentVO.getReportDate();
		this.author = incidentVO.getAuthor();
		//this.image = incidentVO.get;
		if (incidentVO.getQuestions() != null
				&& !incidentVO.getQuestions().isEmpty()) {

			ObjectWriter writer = new ObjectMapper().writer();
			questions = writer.writeValueAsString(incidentVO.getQuestions());
		}
		
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
}
