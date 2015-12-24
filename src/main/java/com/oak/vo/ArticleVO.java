package com.oak.vo;

import java.util.Date;

import com.oak.entities.Article;

public class ArticleVO {

	private String category;
	private Long updatedOn;
	private String title;
	private String content;
	private String displayImage;
	private Boolean approved;
	private String approvedBy;
	private Date approvedOn;
	private Long createdOn;
	private String createdBy;
	private String updatedBy;
	private Integer rating;
	private Long hits;
	private String createdOnDate;
	private String updatedOnDate;

	public ArticleVO() {

	}

	public ArticleVO(Article article) {
		super();
		this.category = article.getPk().getCategory();
		this.updatedOn = article.getPk().getUpdatedOn();
		this.title = article.getTitle();
		this.content = article.getContent();
		this.displayImage = article.getDisplayImage();
		this.approved = article.getApproved();
		this.approvedBy = article.getApprovedBy();
		this.approvedOn = article.getApprovedOn();
		this.createdOn = article.getCreatedOn();
		this.createdBy = article.getCreatedBy();
		this.updatedBy = article.getUpdatedBy();
		this.rating = article.getRating();
		this.hits = article.getHits();
		this.createdOnDate = createdOnDate;
		this.updatedOnDate = updatedOnDate;

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

	public Date getApprovedOn() {
		return approvedOn;
	}

	public void setApprovedOn(Date approvedOn) {
		this.approvedOn = approvedOn;
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

	public String getCreatedOnDate() {
		return createdOnDate;
	}

	public void setCreatedOnDate(String createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

	public String getUpdatedOnDate() {
		return updatedOnDate;
	}

	public void setUpdatedOnDate(String updatedOnDate) {
		this.updatedOnDate = updatedOnDate;
	}

}
