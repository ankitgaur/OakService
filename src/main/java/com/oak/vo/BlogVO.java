package com.oak.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.oak.config.OakConstants;
import com.oak.entities.Blog;

public class BlogVO {

	private String category;
	private String title;
	private String blogHash;
	private long createdOn;
	private String description;
	private String createdby;
	private String updatedby;
	private long updatedon;
	private String displayimage;
	private long hits;
	private long rating;
	private String createdOnStr;
	private String updatedOnStr;
	private String id;

	public BlogVO() {

	}

	public BlogVO(Blog blog) {
		SimpleDateFormat sdf = new SimpleDateFormat(OakConstants.DATE_FORMAT);
		this.category = blog.getBlogKey().getCategory();
		this.title = blog.getTitle();
		this.createdOn = blog.getBlogKey().getCreatedOn();
		this.description = blog.getDescription();
		this.createdby = blog.getCreatedby();
		this.updatedby = blog.getUpdatedby();
		this.updatedon = blog.getUpdatedon();
		this.displayimage = blog.getDisplayimage();
		this.hits = blog.getHits();
		this.rating = blog.getRating();
		this.blogHash = blog.getBlogHash();
		this.createdOnStr = sdf.format(new Date(createdOn));
		this.updatedOnStr = sdf.format(new Date(updatedon));
		this.id = blog.getBlogKey().getCategory()
				+ "_"+blog.getBlogKey().getCreatedOn();
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getHits() {
		return hits;
	}

	public void setHits(long hits) {
		this.hits = hits;
	}

	public long getRating() {
		return rating;
	}

	public void setRating(long rating) {
		this.rating = rating;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public long getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(long updatedon) {
		this.updatedon = updatedon;
	}

	public String getDisplayimage() {
		return displayimage;
	}

	public void setDisplayimage(String displayimage) {
		this.displayimage = displayimage;
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

	public String getBlogHash() {
		return blogHash;
	}

	public void setBlogHash(String blogHash) {
		this.blogHash = blogHash;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
