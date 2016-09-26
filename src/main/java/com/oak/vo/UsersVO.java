package com.oak.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.oak.config.OakConstants;
import com.oak.entities.User;

public class UsersVO {

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
	private String createdOnStr;
	private String updatedOnStr;
	private Long createdOn;
	private Long updatedOn;
	private String grecaptcharesponse;

	public UsersVO() {
		super();
	}

	public UsersVO(User user) {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat(OakConstants.DATE_FORMAT);
		this.email = user.getEmail();
		this.name = user.getName();
		this.username = user.getUsername();
		//this.password = user.getPassword();
		this.groups = user.getGroups();
		this.activated = user.isActivated();
		this.forgotpassword = user.isForgotpassword();
		this.sendemail = user.isSendemail();
		this.createdby = user.getCreatedby();
		this.updatedby = user.getUpdatedby();
		this.createdOn = user.getCreatedon();
		this.updatedOn = user.getUpdatedon();
		if(user.getCreatedon()!=null){
			this.createdOnStr = sdf.format(new Date(user.getCreatedon()));
		}
		if(user.getUpdatedon()!=null){
			this.updatedOnStr = sdf.format(new Date(user.getUpdatedon()));
		}
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

	public String getGrecaptcharesponse() {
		return grecaptcharesponse;
	}

	public void setGrecaptcharesponse(String grecaptcharesponse) {
		this.grecaptcharesponse = grecaptcharesponse;
	}

	
}
