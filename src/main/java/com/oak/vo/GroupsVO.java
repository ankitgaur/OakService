package com.oak.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.oak.config.OakConstants;
import com.oak.entities.Groups;

public class GroupsVO {

	private String name;
	private String roles;
	private String createdby;
	private String updatedby;
	private Long createdon;
	private Long updateon;
	private String createdOnStr;
	private String updateOnStr;

	public GroupsVO() {

	}

	public GroupsVO(Groups group) {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat(OakConstants.DATE_FORMAT);
		this.name = group.getName();
		this.roles = group.getRoles();
		this.createdby = group.getCreatedby();
		this.updatedby = group.getUpdatedby();
		this.createdon = group.getCreatedon();
		this.updateon = group.getUpdateon();
		if (createdon != null) {
			this.createdOnStr = sdf.format(new Date(createdon));
		}
		if(updateon != null) {
			this.updateOnStr = sdf.format(new Date(updateon));
		}
	}

	public String getCreatedOnStr() {
		return createdOnStr;
	}

	public void setCreatedOnStr(String createdOnStr) {
		this.createdOnStr = createdOnStr;
	}

	public String getUpdateOnStr() {
		return updateOnStr;
	}

	public void setUpdateOnStr(String updateOnStr) {
		this.updateOnStr = updateOnStr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
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

	public Long getUpdateon() {
		return updateon;
	}

	public void setUpdateon(Long updateon) {
		this.updateon = updateon;
	}

}
