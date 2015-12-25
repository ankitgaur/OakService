package com.oak.vo;

import com.oak.entities.Roles;

public class RolesVO {

	private String name;
	private String createdby;
	private String updatedby;
	private Long createdon;
	private Long updateon;
	private String createdOnStr;
	private String updateOnStr;

	public RolesVO() {
		super();
	}

	public RolesVO(Roles role) {
		super();
		this.name = role.getName();
		this.createdby = role.getCreatedby();
		this.updatedby = role.getUpdatedby();
		this.createdon = role.getCreatedon();
		this.updateon = role.getUpdateon();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
