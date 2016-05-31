package com.oak.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.oak.config.OakConstants;
import com.oak.entities.Page;

public class PageVO {

	private String name;
	private String link;
	private String parent;
	private String createdby;
	private String updatedby;
	private String createdOnStr;
	private String updatedOnStr;
	private Long createdOn;
	private Long updatedOn;

	public PageVO() {
		super();
	}

	public PageVO(Page page) {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat(OakConstants.DATE_FORMAT);
		this.name = page.getName();
		this.link = page.getLink();
		this.parent = page.getParent();
		this.createdby = page.getCreatedby();
		this.updatedby = page.getUpdatedby();
		this.createdOn = page.getCreatedon();
		this.updatedOn = page.getUpdatedon();
		if (page.getCreatedon() != null) {
			this.createdOnStr = sdf.format(new Date(page.getCreatedon()));
		}
		if (page.getUpdatedon() != null) {
			this.updatedOnStr = sdf.format(new Date(page.getUpdatedon()));
		}
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

}
