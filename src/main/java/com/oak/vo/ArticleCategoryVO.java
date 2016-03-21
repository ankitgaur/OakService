package com.oak.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.cassandra.mapping.PrimaryKey;

import com.oak.entities.ArticleCategory;

public class ArticleCategoryVO {

	@PrimaryKey
	private long id;
	private String name;
	private long createdon;
	private String description;
	private String createdby;
	private String updatedby;
	private long updatedon;
	private String displayimage;
	private String createdOnStr;
	private String updatedOnStr;

	public ArticleCategoryVO(){
		
	}
	
	public ArticleCategoryVO(ArticleCategory category) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		this.id = category.getId();
		this.name = category.getName();
		this.createdon = category.getCreatedon();
		this.description = category.getDescription();
		this.createdby = category.getCreatedby();
		this.updatedby = category.getUpdatedby();
		this.updatedon = category.getUpdatedon();
		this.displayimage = category.getDisplayimage();
		this.createdOnStr = sdf.format(new Date(createdon));
		this.updatedOnStr = sdf.format(new Date(updatedon));
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

}
