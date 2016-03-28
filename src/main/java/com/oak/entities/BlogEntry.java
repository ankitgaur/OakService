package com.oak.entities;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.BlogEntryVO;

@Table("blog_entries")
public class BlogEntry {

	@PrimaryKey
	private BlogEntryKey blogKey;
	private String title;
	private String content;
	private String displayImage;
	private Boolean approved;
	private String approvedBy;
	private Long approvedOn;
	private Long updatedOn;
	private String createdBy;
	private String updatedBy;
	private Integer rating;
	private Long hits;

	public BlogEntry() {
		super();
	}

	public BlogEntry(BlogEntryVO blogVO) {
		super();
		BlogEntryKey key = new BlogEntryKey();
		key.setBlog(blogVO.getBlog());
		key.setCreatedOn(blogVO.getCreatedOn());
		this.blogKey = key;
		this.title = blogVO.getTitle();
		this.content = blogVO.getContent();
		this.displayImage = blogVO.getDisplayImage();
		this.approved = blogVO.getApproved();
		this.approvedBy = blogVO.getApprovedBy();
		this.approvedOn = blogVO.getApprovedOn();
		this.updatedOn = blogVO.getUpdatedOn();
		this.createdBy = blogVO.getCreatedBy();
		this.updatedBy = blogVO.getUpdatedBy();
		this.rating = blogVO.getRating();
		
	}

	public BlogEntryKey getBlogKey() {
		return blogKey;
	}

	public void setBlogKey(BlogEntryKey blogKey) {
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

}
