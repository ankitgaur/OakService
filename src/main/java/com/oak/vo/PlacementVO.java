package com.oak.vo;

import com.oak.entities.Placement;

public class PlacementVO {

	private String id;
	private String title;
	private String img;
	private String link;
	private String intro;
	private String createdBy;
	private String updatedBy;
	private String createdOnStr;
	private String updatedOnStr;
	private Long createdOn;
	private Long updatedOn;

	public PlacementVO() {

	}

	public PlacementVO(Placement placement) {

		this.id = placement.getId();
		this.title = placement.getTitle();
		this.img = placement.getImg();
		this.link = placement.getLink();
		this.intro = placement.getIntro();
		this.createdBy = placement.getCreatedby();
		this.updatedBy = placement.getUpdatedby();

		this.createdOn = placement.getCreatedon();
		this.updatedOn = placement.getUpdatedon();

		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		 * this.createdOnStr = sdf.format(new Date(placement.getCreatedon()));
		 * this.updatedOnStr = sdf.format(new Date(placement.getUpdatedon()));
		 */
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public Long getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
	}

}
