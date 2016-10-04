package com.oak.entities;

public class CommentsKey {

	public CommentsKey(String service, String service_id) {
		super();
		this.service = service;
		this.service_id = service_id;
	}

	public CommentsKey() {
	}

	private String service;
	private String service_id;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

}
