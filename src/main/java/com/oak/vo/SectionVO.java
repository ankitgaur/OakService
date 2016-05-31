package com.oak.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.oak.config.OakConstants;
import com.oak.entities.Section;

public class SectionVO {

	private String id;
	private String name;
	private String page;
	private String createdby;
	private String updatedby;
	private String createdOnStr;
	private String updatedOnStr;
	private Long createdOn;
	private Long updatedOn;

	public SectionVO() {
		super();
	}

	public SectionVO(Section section) {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat(OakConstants.DATE_FORMAT);
		this.name = section.getKey().getName();
		this.page = section.getKey().getPage();
		this.createdby = section.getCreatedby();
		this.updatedby = section.getUpdatedby();
		this.createdOn = section.getCreatedon();
		this.updatedOn = section.getUpdatedon();
		this.id = this.page+"_"+this.name;
		if (section.getCreatedon() != null) {
			this.createdOnStr = sdf.format(new Date(section.getCreatedon()));
		}
		if (section.getUpdatedon() != null) {
			this.updatedOnStr = sdf.format(new Date(section.getUpdatedon()));
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
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

}
