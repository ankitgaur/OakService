package com.oak.vo;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
	private String abbr;
	private String currGovt;
	private List<String> govts;
	private String createdby;
	private String updatedby;
	private Long createdOn;
	private Long updatedOn;
	private String createdOnStr;
	private String updatedOnStr;

	public StatesVO() {

	}

	public StatesVO(States state) throws JsonParseException,
			JsonMappingException, IOException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		id = state.getId();
		name = state.getName();
		currGovt = state.getCurrGovt();
		createdby = state.getCreatedby();
		updatedby = state.getUpdatedby();
		createdOn = state.getCreatedon();
		updatedOn = state.getUpdatedon();
		abbr = state.getAbbr();
		if(state.getCreatedon()!=null){
			this.createdOnStr = sdf.format(new Date(state.getCreatedon()));
		}
		if(state.getUpdatedon()!=null){
			this.updatedOnStr = sdf.format(new Date(state.getUpdatedon()));
		}
		
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

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public Long getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getCreatedOnStr() {
		return createdOnStr;
	}

	public void setCreatedOnStr(String createdOnStr) {
		this.createdOnStr = createdOnStr;
	}

	public String getUpdatedOnStr() {
		return updatedOnStr;
	}

	public void setUpdatedOnStr(String updatedOnStr) {
		this.updatedOnStr = updatedOnStr;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}	
	
}
