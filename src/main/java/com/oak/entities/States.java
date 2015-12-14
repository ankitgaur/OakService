package com.oak.entities;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.StatesVO;

@Table("states")
public class States {

	@PrimaryKey
	private long id;
	private String name;
	private String currGovt;
	private String govts;
	private String createdBy;
	private String updatedBy;
	private Date createdOn;
	private Date updatedOn;

	public States() {

	}

	public States(StatesVO stateVO) throws JsonGenerationException,
			JsonMappingException, IOException {

		id = stateVO.getId();
		name = stateVO.getName();
		currGovt = stateVO.getCurrGovt();

		if (stateVO.getGovts() != null && !stateVO.getGovts().isEmpty()) {

			ObjectWriter writer = new ObjectMapper().writer();
			govts = writer.writeValueAsString(stateVO.getGovts());
		}

		createdBy = stateVO.getCreatedBy();
		createdOn = stateVO.getCreatedOn();
		updatedBy = stateVO.getUpdatedBy();
		updatedOn = stateVO.getUpdatedOn();
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

	public String getGovts() {
		return govts;
	}

	public void setGovts(String govts) {
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
