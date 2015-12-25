package com.oak.entities;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.BlogVO;

@Table("blogs")
public class Blog {

	@PrimaryKey
	private BlogKey blogKey;
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

	public Blog() {
		super();
	}

	public Blog(BlogVO blogVO) {
		super();
		BlogKey key = new BlogKey();
		key.setCategory(blogVO.getCategory());
		key.setUpdatedOn(blogVO.getUpdatedOn());
		this.blogKey = key;
		this.title = blogVO.getTitle();
		this.content = blogVO.getContent();
		this.displayImage = blogVO.getDisplayImage();
		this.approved = blogVO.getApproved();
		this.approvedBy = blogVO.getApprovedBy();
		this.approvedOn = blogVO.getApprovedOn();
		this.createdOn = blogVO.getCreatedOn();
		this.createdBy = blogVO.getCreatedBy();
		this.updatedBy = blogVO.getUpdatedBy();
		this.rating = blogVO.getRating();
		this.hits = blogVO.getHits();
	}

	public BlogKey getBlogKey() {
		return blogKey;
	}

	public void setBlogKey(BlogKey blogKey) {
		this.blogKey = blogKey;
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

}
