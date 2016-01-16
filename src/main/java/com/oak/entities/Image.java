package com.oak.entities;

import java.nio.ByteBuffer;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table(value = "images")
public class Image {

	@PrimaryKey
	private String id;
	private String name;
	private ByteBuffer img;
	private Long kbsize;
	private Long createdOn;
	private String createdBy;
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
	
	public ByteBuffer getImg() {
		return img;
	}
	public void setImg(ByteBuffer img) {
		this.img = img;
	}
	public Long getKbsize() {
		return kbsize;
	}
	public void setKbsize(Long kbsize) {
		this.kbsize = kbsize;
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
}
