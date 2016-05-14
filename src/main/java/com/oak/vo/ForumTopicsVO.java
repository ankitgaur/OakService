package com.oak.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.oak.entities.ForumTopics;

public class ForumTopicsVO {

	private String id;
	private String category;
	private Long updatedOn;
	private String title;
	private String intro;
	private String displayImage;
	private Long createdOn;
	private String createdBy;
	private String updatedBy;
	private Integer rating;
	private Long hits;
	private String createdOnStr;
	private String updatedOnStr;

	public ForumTopicsVO() {

	}

	public ForumTopicsVO(ForumTopics article) {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		this.category = article.getPk().getCategory();
		this.updatedOn = article.getUpdatedOn();
		this.title = article.getTitle();
		this.displayImage = article.getDisplayImage();
		this.createdOn = article.getPk().getCreatedOn();
		this.createdBy = article.getCreatedBy();
		this.updatedBy = article.getUpdatedBy();
		this.rating = article.getRating();
		this.hits = article.getHits();
		this.createdOnStr = sdf.format(new Date(createdOn));
		this.updatedOnStr = sdf.format(new Date(updatedOn));
		this.intro = article.getIntro();
		this.setId(this.category + "_" + this.createdOn);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	/*
	 * public String getContent() { return content; }
	 * 
	 * public void setContent(String content) { this.content = content; }
	 */
	public String getDisplayImage() {
		return displayImage;
	}

	public void setDisplayImage(String displayImage) {
		this.displayImage = displayImage;
	}

	public String getCreatedBy() {
		return createdBy;
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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
