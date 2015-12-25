package com.oak.entities;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.GroupsVO;

@Table(value = "groups")
public class Groups {

	@PrimaryKey
	private String name;
	private String roles;
	private String createdby;
	private String updatedby;
	private Long createdon;
	private Long updateon;

	public Groups() {

	}

	public Groups(GroupsVO groupsVO) {
		super();
		this.name = groupsVO.getName();
		this.roles = groupsVO.getRoles();
		this.createdby = groupsVO.getCreatedby();
		this.updatedby = groupsVO.getUpdatedby();
		this.createdon = groupsVO.getCreatedon();
		this.updateon = groupsVO.getUpdateon();
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
