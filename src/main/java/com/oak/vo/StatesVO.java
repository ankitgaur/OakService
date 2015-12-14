package com.oak.vo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.oak.entities.States;

public class StatesVO {

	private long id;
	private String name;
	private String currGovt;
	private List<String> govts;
	private String createdBy;
	private String updatedBy;
	private Date createdOn;
	private Date updatedOn;

	public StatesVO() {

	}

	public StatesVO(States state) throws JsonParseException,
			JsonMappingException, IOException {

		id = state.getId();
		name = state.getName();
		currGovt = state.getCurrGovt();
		createdBy = state.getCreatedBy();
		updatedBy = state.getUpdatedBy();
		createdOn = state.getCreatedOn();
		updatedOn = state.getUpdatedOn();

		if (state.getGovts() != null && !state.getGovts().isEmpty()) {
			List<String> cats = new ArrayList<String>();
			ObjectMapper statemapper = new ObjectMapper();
			cats = statemapper.readValue(state.getGovts(),
					new TypeReference<ArrayList<String>>() {
					});
			govts = cats;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrGovt() {
		return currGovt;
	}

	public void setCurrGovt(String currGovt) {
		this.currGovt = currGovt;
	}

	public List<String> getGovts() {
		return govts;
	}

	public void setGovts(List<String> govts) {
		this.govts = govts;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}
