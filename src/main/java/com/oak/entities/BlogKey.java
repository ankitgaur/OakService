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

	@PrimaryKeyColumn(name = "category", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String category;

	@PrimaryKeyColumn(name = "updatedOn", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
	private Long updatedOn;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
	}

	public BlogKey() {
		super();
	}

	public BlogKey(String category, Long updatedOn) {
		super();
		this.category = category;
		this.updatedOn = updatedOn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((updatedOn == null) ? 0 : updatedOn.hashCode());
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
		if (updatedOn == null) {
			if (blogsKey.updatedOn != null)
				return false;
		} else if (!updatedOn.equals(blogsKey.updatedOn))
			return false;
		if (category == null) {
			if (blogsKey.category != null)
				return false;
		} else if (!category.equals(blogsKey.category))
			return false;
		return true;
	}

}