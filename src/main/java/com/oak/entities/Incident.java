package com.oak.entities;

import java.util.Date;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("incidents")
public class Incident {

	@PrimaryKey
	private long id;
	private String type;
	private String image;
	private String state;
	private String govt;
	private String description;
	private String questions;
	private String status;
	private Date reportDate;
	private String createdBy;
	private Date createdOn;

	public Incident() {

	}

	public Incident(long id, String type, String image, String state,
			String govt, String description, String questions, String status,
			Date reportDate, String createdBy, Date createdOn) {
		super();
		this.id = id;
		this.type = type;
		this.image = image;
		this.state = state;
		this.govt = govt;
		this.description = description;
		this.questions = questions;
		this.status = status;
		this.reportDate = reportDate;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
