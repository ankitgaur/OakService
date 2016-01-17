package com.oak.entities;

import com.oak.vo.PlacementVO;

public class Placement {

	private String id;
	private String title;
	private String intro;
	private String img;
	private String link;
	private String createdby;
	private String updatedby;
	private Long createdon;
	private Long updatedon;

	public Placement() {

	}

	public Placement(PlacementVO placementVO) {
		this.id = placementVO.getId();
		this.title = placementVO.getTitle();
		this.intro = placementVO.getIntro();
		this.img = placementVO.getImg();
		this.link = placementVO.getLink();
		this.createdby = placementVO.getCreatedBy();
		this.createdon = placementVO.getCreatedOn();
		this.updatedon = placementVO.getUpdatedOn();
		this.updatedby = placementVO.getUpdatedBy();
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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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

	public Long getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Long createdon) {
		this.createdon = createdon;
	}

	public Long getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Long updatedon) {
		this.updatedon = updatedon;
	}

}
