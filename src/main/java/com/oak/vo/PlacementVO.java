package com.oak.vo;

import com.oak.entities.Placement;
import com.oak.entities.PlacementKey;

public class PlacementVO {

	private String id;
	private String page;
	private String section;
	private int position;
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

		PlacementKey pk = placement.getPk();
		this.page = pk.getPage();
		this.section = pk.getSection();
		this.position = pk.getPosition();
		this.title = placement.getTitle();
		this.img = placement.getImg();
		this.link = placement.getLink();
		this.intro = placement.getIntro();
		this.createdBy = placement.getCreatedby();
		this.updatedBy = placement.getUpdatedby();

		this.createdOn = placement.getCreatedon();
		this.updatedOn = placement.getUpdatedon();
		this.id = this.page + "_" + this.section + "_" + this.position;

		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		 * this.createdOnStr = sdf.format(new Date(placement.getCreatedon()));
		 * this.updatedOnStr = sdf.format(new Date(placement.getUpdatedon()));
		 */
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
