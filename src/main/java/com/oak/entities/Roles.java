package com.oak.entities;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.RolesVO;

@Table(value = "roles")
public class Roles {

	@PrimaryKey
	private String name;
	private String createdby;
	private String updatedby;
	private Long createdon;
	private Long updateon;

	public Roles() {

	}

	public Roles(RolesVO rolesVO) {
		super();
		this.name = rolesVO.getName();
		this.createdby = rolesVO.getCreatedby();
		this.updatedby = rolesVO.getUpdatedby();
		this.createdon = rolesVO.getCreatedon();
		this.updateon = rolesVO.getUpdateon();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
