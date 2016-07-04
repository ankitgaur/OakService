package com.oak.entities;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.BlogVO;

@Table("blogs")
public class Blog {

	@PrimaryKey
	private BlogKey blogKey;
	private String title;
	private String alias;
	private String description;
	private String updatedby;
	private Long updatedon;
	private String displayimage;
	private Long rating;
	private Long hits;

	public Blog() {

	}

	public Blog(BlogVO blogVO) {
		BlogKey key = new BlogKey();
		key.setCategory(blogVO.getCategory());
		key.setCreatedOn(blogVO.getCreatedOn());
		key.setCreatedby(blogVO.getCreatedby());
		this.blogKey = key;
		this.title = blogVO.getTitle();
		this.description = blogVO.getDescription();
		this.updatedby = blogVO.getUpdatedby();
		this.updatedon = blogVO.getUpdatedon();
		this.displayimage = blogVO.getDisplayimage();
		this.rating = blogVO.getRating();

	}

	public String generateId() {
		String id = this.getBlogKey().getCategory() + "_"
				+ this.getBlogKey().getCreatedOn();
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

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public Long getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Long updatedon) {
		this.updatedon = updatedon;
	}

	public String getDisplayimage() {
		return displayimage;
	}

	public void setDisplayimage(String displayimage) {
		this.displayimage = displayimage;
	}

	public Long getHits() {
		return hits;
	}

	public void setHits(Long hits) {
		this.hits = hits;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
