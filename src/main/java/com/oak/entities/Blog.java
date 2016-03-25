package com.oak.entities;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.BlogVO;

@Table("blogs")
public class Blog {

	@PrimaryKey
	private BlogKey blogKey;
	private String title;
	private String blogHash;
	private String description;
	private String createdby;
	private String updatedby;
	private long updatedon;
	private String displayimage;
	private long rating;
	private long hits;

	public Blog() {

	}

	public Blog(BlogVO blogVO) {
		BlogKey key = new BlogKey();
		key.setCategory(blogVO.getCategory());
		key.setCreatedOn(blogVO.getCreatedOn());
		this.blogKey = key;
		this.title = blogVO.getTitle();
		this.description = blogVO.getDescription();
		this.createdby = blogVO.getCreatedby();
		this.updatedby = blogVO.getUpdatedby();
		this.updatedon = blogVO.getUpdatedon();
		this.displayimage = blogVO.getDisplayimage();
		this.rating = blogVO.getRating();

		if (blogVO.getBlogHash() != null && !blogVO.getBlogHash().isEmpty()) {
			this.blogHash = blogVO.getBlogHash();
		} else {
			// this.blogHash = HashGeneratorUtil.generateMD5(this.title.trim());
			this.blogHash = "" + this.title.trim().hashCode();
		}
	}

	public String generateId(){
		String id = this.getBlogKey().getCategory()
				+ "_"+this.getBlogKey().getCreatedOn();
		return id;
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

	public long getRating() {
		return rating;
	}

	public void setRating(long rating) {
		this.rating = rating;
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

	public String getBlogHash() {
		return blogHash;
	}

	public void setBlogHash(String blogHash) {
		this.blogHash = blogHash;
	}

	public long getHits() {
		return hits;
	}

	public void setHits(long hits) {
		this.hits = hits;
	}

}
