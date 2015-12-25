package com.oak.vo;

import com.oak.entities.Users;

public class UsersVO {

	private String email;
	private String name;
	private String username;
	private String password;
	private String groups;
	private String activated;
	private String forgotpassword;
	private String sendemail;
	private String createdby;
	private String updatedby;
	private Long createdon;
	private Long updateon;
	private String createdOnStr;
	private String updateOnStr;

	public UsersVO() {
		super();
	}

	public UsersVO(Users user) {
		super();
		this.email = user.getEmail();
		this.name = user.getName();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.groups = user.getGroups();
		this.activated = user.getActivated();
		this.forgotpassword = user.getForgotpassword();
		this.sendemail = user.getSendemail();
		this.createdby = user.getCreatedby();
		this.updatedby = user.getUpdatedby();
		this.createdon = user.getCreatedon();
		this.updateon = user.getUpdateon();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getActivated() {
		return activated;
	}

	public void setActivated(String activated) {
		this.activated = activated;
	}

	public String getForgotpassword() {
		return forgotpassword;
	}

	public void setForgotpassword(String forgotpassword) {
		this.forgotpassword = forgotpassword;
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

	public String getSendemail() {
		return sendemail;
	}

	public void setSendemail(String sendemail) {
		this.sendemail = sendemail;
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
