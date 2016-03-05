package com.oak.entities;

import java.io.Serializable;

import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class BlogKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKeyColumn(name = "blog", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String category;

	@PrimaryKeyColumn(name = "createdOn", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
	private Long createdOn;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public BlogKey() {
		super();
	}

	public BlogKey(String category, Long createdOn) {
		super();
		this.category = category;
		this.createdOn = createdOn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
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
		BlogKey blogsKey = (BlogKey) obj;
		if (createdOn == null) {
			if (blogsKey.createdOn != null)
				return false;
		} else if (!createdOn.equals(blogsKey.createdOn))
			return false;
		if (category == null) {
			if (blogsKey.category != null)
				return false;
		} else if (!category.equals(blogsKey.category))
			return false;
		return true;
	}

}
