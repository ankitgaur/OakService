package com.oak.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.oak.entities.BlogEntry;

public class BlogEntryVO {

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
	private String createdOnStr;
	private String updatedOnStr;

	public BlogEntryVO() {

	}

	public BlogEntryVO(BlogEntry blog) {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		this.category = blog.getBlogKey().getBlog();
		this.updatedOn = blog.getUpdatedOn();
		this.title = blog.getTitle();
		this.content = blog.getContent();
		this.displayImage = blog.getDisplayImage();
		this.approved = blog.getApproved();
		this.approvedBy = blog.getApprovedBy();
		this.approvedOn = blog.getApprovedOn();
		this.createdOn = blog.getBlogKey().getCreatedOn();
		this.createdBy = blog.getCreatedBy();
		this.updatedBy = blog.getUpdatedBy();
		this.rating = blog.getRating();
		this.hits = blog.getHits();
		this.createdOnStr = sdf.format(new Date(createdOn));
		this.updatedOnStr = sdf.format(new Date(updatedOn));
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
		return createdOnStr;
	}

	public void setCreatedOnDate(String createdOnDate) {
		this.createdOnStr = createdOnDate;
	}

	public String getUpdatedOnDate() {
		return updatedOnStr;
	}

	public void setUpdatedOnDate(String updatedOnDate) {
		this.updatedOnStr = updatedOnDate;
	}

}
