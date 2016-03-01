package com.oak.entities;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.BlogCategoryVO;

@Table("blog_categories")
public class BlogCategory {

	@PrimaryKey
	private long id;
	private String name;
	private long createdon;
	private String description;
	private String createdby;
	private String updatedby;
	private long updatedon;
	private String displayimage;

	public BlogCategory() {

	}

	public BlogCategory(BlogCategoryVO categoryVO) {
		this.id = categoryVO.getId();
		this.name = categoryVO.getName();
		this.createdon = categoryVO.getCreatedon();
		this.description = categoryVO.getDescription();
		this.createdby = categoryVO.getCreatedby();
		this.updatedby = categoryVO.getUpdatedby();
		this.updatedon = categoryVO.getUpdatedon();
		this.displayimage = categoryVO.getDisplayimage();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCreatedon() {
		return createdon;
	}

	public void setCreatedon(long createdon) {
		this.createdon = createdon;
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

}
