package com.oak.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.oak.config.OakConstants;
import com.oak.entities.ForumPost;

public class ForumPostVO {

	private String id;
	private String topic;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	private Long updatedOn;
	private String title;
	private String content;
	private String displayImage;
	private Boolean approved;
	private String approvedBy;
	private Long approvedOn;
	private Long createdOn;
	private String createdBy;
	private String updatedBy;
	private Integer rating;
	private Long hits;
	private String createdOnStr;
	private String updatedOnStr;

	public ForumPostVO() {

	}

	public ForumPostVO(ForumPost forumPost) {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat(OakConstants.DATE_FORMAT);
		this.topic = forumPost.getPk().getTopic();
		this.updatedOn = forumPost.getUpdatedOn();
		this.title = forumPost.getTitle();
		this.content = forumPost.getContent();
		this.displayImage = forumPost.getDisplayImage();
		this.approved = forumPost.getApproved();
		this.approvedBy = forumPost.getApprovedBy();
		this.approvedOn = forumPost.getApprovedOn();
		this.createdOn = forumPost.getPk().getCreatedOn();
		this.createdBy = forumPost.getCreatedBy();
		this.updatedBy = forumPost.getUpdatedBy();
		this.rating = forumPost.getRating();
		this.hits = forumPost.getHits();
		this.createdOnStr = sdf.format(new Date(createdOn));
		this.updatedOnStr = sdf.format(new Date(updatedOn));
		this.setId(this.topic + "_" + this.createdOn);
	}

	public String getCategory() {
		return topic;
	}

	public void setCategory(String category) {
		this.topic = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDisplayImage() {
		return displayImage;
	}

	public void setDisplayImage(String displayImage) {
		this.displayImage = displayImage;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Long getApprovedOn() {
		return approvedOn;
	}

	public void setApprovedOn(Long approvedOn) {
		this.approvedOn = approvedOn;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Long getHits() {
		return hits;
	}

	public void setHits(Long hits) {
		this.hits = hits;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
