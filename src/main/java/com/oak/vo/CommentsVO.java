package com.oak.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.oak.config.OakConstants;
import com.oak.entities.Comments;

public class CommentsVO {

	private String service;
	private String service_id;
	private String comment;
	private String createdby;
	private String updatedby;
	private Long createdon;
	private Long updatedon;
	private String createdOnStr;
	private String updatedOnStr;
	private String author;

	public CommentsVO() {

	}

	public CommentsVO(Comments comments) {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat(OakConstants.DATE_FORMAT);
		this.service = comments.getCommentsKey().getService();
		this.service_id = comments.getCommentsKey().getService_id();
		this.comment = comments.getComment();
		this.createdby = comments.getCreatedby();
		this.updatedby = comments.getUpdatedby();
		this.createdon = comments.getCommentsKey().getCreatedon();
		this.updatedon = comments.getUpdatedon();
		this.author = comments.getAuthor();
		if (createdon != null) {
			this.createdOnStr = sdf.format(new Date(createdon));
		}
		if (updatedon != null) {
			this.updatedOnStr = sdf.format(new Date(updatedon));
		}

	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getService() {
		return service;
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

	public void setService(String service) {
		this.service = service;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
