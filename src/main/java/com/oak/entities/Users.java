package com.oak.entities;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.UsersVO;

@Table(value = "users")
public class Users {

	@PrimaryKey
	private String email;
	private String name;
	private String username;
	private String password;
	private String groups;
	private boolean activated;
	private boolean forgotpassword;
	private boolean sendemail;
	private String createdby;
	private String updatedby;
	private Long createdon;
	private Long updatedon;

	public Users() {
		super();
	}

	public Users(UsersVO usersVO) {
		super();
		this.email = usersVO.getEmail();
		this.name = usersVO.getName();
		this.username = usersVO.getUsername();
		this.password = usersVO.getPassword();
		this.groups = usersVO.getGroups();
		this.activated = usersVO.isActivated();
		this.forgotpassword = usersVO.isForgotpassword();
		this.sendemail = usersVO.isSendemail();
		this.createdby = usersVO.getCreatedby();
		this.updatedby = usersVO.getUpdatedby();
		this.createdon = usersVO.getCreatedOn();
		this.updatedon = usersVO.getUpdatedOn();
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

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public boolean isForgotpassword() {
		return forgotpassword;
	}

	public void setForgotpassword(boolean forgotpassword) {
		this.forgotpassword = forgotpassword;
	}

	public boolean isSendemail() {
		return sendemail;
	}

	public void setSendemail(boolean sendemail) {
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

	public Long getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Long updatedon) {
		this.updatedon = updatedon;
	}	

}
