package com.oak.entities;

import java.io.IOException;

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
	private String name;
	private String abbr;
	private String currGovt;
	private String govts;
	private String createdby;
	private String updatedby;
	private Long createdon;
	private Long updatedon;

	public States() {

	}

	public States(StatesVO stateVO) throws JsonGenerationException,
			JsonMappingException, IOException {

		name = stateVO.getName();
		currGovt = stateVO.getCurrGovt();

		if (stateVO.getGovts() != null && !stateVO.getGovts().isEmpty()) {

			ObjectWriter writer = new ObjectMapper().writer();
			govts = writer.writeValueAsString(stateVO.getGovts());
		}

		createdby = stateVO.getCreatedby();
		createdon = stateVO.getCreatedOn();
		updatedby = stateVO.getUpdatedby();
		updatedon = stateVO.getUpdatedOn();
		abbr = stateVO.getAbbr();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	
	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
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

	public Long getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Long createdon) {
		this.createdon = createdon;
	}

	public Long getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Long updatedon) {
		this.updatedon = updatedon;
	}

}
