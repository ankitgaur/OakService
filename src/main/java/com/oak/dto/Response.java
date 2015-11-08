package com.oak.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Response {
	
	private String statuscode;
	private String statusmsg;
	
	public String getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}
	public String getStatusmsg() {
		return statusmsg;
	}
	public void setStatusmsg(String statusmsg) {
		this.statusmsg = statusmsg;
	}
	@Override
	public String toString() {
		String resp = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			resp =  mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}
		return resp;
	}
	
	
	
}
