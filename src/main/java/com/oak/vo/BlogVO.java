package com.oak.vo;

import com.oak.entities.Blog;

public class BlogVO {

	private String category;
	private String title;
	private String content;
	private String displayImage;
	private Boolean approved;
	private String approvedBy;
	private Long approvedOn;
	private Long createdOn;
	private Long updatedOn;
	private String createdBy;
	private String updatedBy;
	private Integer rating;
	private Long hits;
	private String createdOnDate;
	private String updatedOnDate;

	public BlogVO() {

	}

	public BlogVO(Blog blog) {
		super();
		this.category = blog.getBlogKey().getCategory();
		this.updatedOn = blog.getBlogKey().getUpdatedOn();
		this.title = blog.getTitle();
		this.content = blog.getContent();
		this.displayImage = blog.getDisplayImage();
		this.approved = blog.getApproved();
		this.approvedBy = blog.getApprovedBy();
		this.approvedOn = blog.getApprovedOn();
		this.createdOn = blog.getCreatedOn();
		this.createdBy = blog.getCreatedBy();
		this.updatedBy = blog.getUpdatedBy();
		this.rating = blog.getRating();
		this.hits = blog.getHits();
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

	public Long getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getCreatedBy() {
		return createdBy;
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
