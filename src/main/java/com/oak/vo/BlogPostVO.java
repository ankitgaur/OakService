package com.oak.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.oak.config.OakConstants;
import com.oak.entities.BlogPost;

public class BlogPostVO {

	private String blog;
	private String blogname;
	private String title;
	private String content;
	private String displayImage;
	private Boolean approved;
	private String approvedBy;
	private Long approvedOn;
	private Long createdOn;
	private Long updatedOn;
	private String createdBy;
	private String author;
	private String updatedBy;
	private Integer rating;
	private Long hits;
	private String createdOnStr;
	private String updatedOnStr;
	private String id;

	public BlogPostVO(BlogPost blog) {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat(OakConstants.DATE_FORMAT);
		this.blog = blog.getBlogKey().getBlog();
		this.updatedOn = blog.getUpdatedOn();
		this.title = blog.getTitle();
		this.content = blog.getContent();
		this.displayImage = blog.getDisplayImage();
		this.approved = blog.getApproved();
		this.approvedBy = blog.getApprovedBy();
		this.setAuthor(blog.getAuthor());
		this.approvedOn = blog.getApprovedOn();
		this.createdOn = blog.getBlogKey().getCreatedOn();
		this.createdBy = blog.getBlogKey().getCreatedBy();
		this.updatedBy = blog.getUpdatedBy();
		this.rating = blog.getRating();
		this.blogname = blog.getBlogname();
		// this.hits = blog.getHits();
		if (createdOn != null) {
			this.createdOnStr = sdf.format(new Date(createdOn));
		}
		if (updatedOn != null) {
			this.updatedOnStr = sdf.format(new Date(updatedOn));
		}
		
		this.id = blog.getAlias();
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BlogPostVO() {

	}

	public String getBlogname() {
		return blogname;
	}

	public void setBlogname(String blogname) {
		this.blogname = blogname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
