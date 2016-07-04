package com.oak.entities;

import java.io.Serializable;

import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class BlogPostKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKeyColumn(name = "blog", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String blog;

	@PrimaryKeyColumn(name = "createdby", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String createdBy;
	
	@PrimaryKeyColumn(name = "createdOn", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
	private Long createdOn;

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
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

	public BlogPostKey() {
		super();
	}

	public BlogPostKey(String category, String createdBy, Long createdOn) {
		super();
		this.blog = category;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result
				+ ((blog == null) ? 0 : blog.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogPostKey blogsKey = (BlogPostKey) obj;
		if (createdOn == null) {
			if (blogsKey.createdOn != null)
				return false;
		} else if (!createdOn.equals(blogsKey.createdOn))
			return false;
		if (blog == null) {
			if (blogsKey.blog != null)
				return false;
		} else if (!blog.equals(blogsKey.blog))
			return false;
		return true;
	}

}
