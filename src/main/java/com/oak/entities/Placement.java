package com.oak.entities;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.PlacementVO;

@Table("placements")
public class Placement {

	@PrimaryKey
	private PlacementKey pk;
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
		
		PlacementKey pkey = new PlacementKey();
		String section = placementVO.getSection();
		int pos = placementVO.getPosition();
		
		pkey.setSection(section);
		pkey.setPosition(pos);
		
		this.pk = pkey;
		this.title = placementVO.getTitle();
		this.intro = placementVO.getIntro();
		this.img = placementVO.getImg();
		this.link = placementVO.getLink();
		this.createdby = placementVO.getCreatedBy();
		this.createdon = placementVO.getCreatedOn();
		this.updatedon = placementVO.getUpdatedOn();
		this.updatedby = placementVO.getUpdatedBy();
	}
	
	public PlacementKey getPk() {
		return pk;
	}

	public void setPk(PlacementKey pk) {
		this.pk = pk;
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
