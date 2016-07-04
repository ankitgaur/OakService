package com.oak.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.cassandra.mapping.PrimaryKey;

import com.oak.config.OakConstants;
import com.oak.entities.Image;

public class ImageVO {

	@PrimaryKey
	private String id;
	private String name;	
	private Long kbsize;
	private String createdBy;
	private String createdOnStr;
	private Long createdOn;
	private String alias;
	
	public ImageVO(){
		
	}	

	public ImageVO(Image img){
		super();
		SimpleDateFormat sdf = new SimpleDateFormat(OakConstants.DATE_FORMAT);
		this.id = img.getAlias();
		this.name = img.getName();
		this.kbsize = img.getKbsize();
		this.createdBy = img.getKey().getCreatedBy();
		this.createdOn = img.getKey().getCreatedOn();
		this.alias = img.getAlias();
		if (createdOn != null) {
			this.createdOnStr = sdf.format(new Date(createdOn));
		}
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getKbsize() {
		return kbsize;
	}

	public void setKbsize(Long kbsize) {
		this.kbsize = kbsize;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedOnStr() {
		return createdOnStr;
	}

	public void setCreatedOnStr(String createdOnStr) {
		this.createdOnStr = createdOnStr;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
}
