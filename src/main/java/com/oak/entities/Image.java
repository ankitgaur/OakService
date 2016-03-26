package com.oak.entities;

import java.nio.ByteBuffer;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table(value = "images")
public class Image {

	@PrimaryKey
	private ImageKey key;
	private String name;
	private ByteBuffer img;
	private Long kbsize;	
	private String createdBy;
			
	
	public ImageKey getKey() {
		return key;
	}
	public void setKey(ImageKey key) {
		this.key = key;
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
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
		
}
