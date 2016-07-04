package com.oak.entities;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.VideoVO;

@Table(value = "videos")
public class Video {

	/*
	 * @PrimaryKey private Long id;
	 */

	@PrimaryKey
	private VideoKey pk;
	private String title;
	private String intro;    
	private String alias;
	private String videourl;
	private String videoimgurl;
	private Long hits;    
    private Integer rating;
    private Boolean approved;
	private String approvedBy;
	private Long approvedOn;
	private Long updatedOn;	
	private String author;
	private String updatedBy;
  
	public Video() {

	}

	public Video(VideoVO videoVO) {
		super();
		VideoKey videoKey = new VideoKey();
		videoKey.setCategory(videoVO.getCategory());
		videoKey.setCreatedOn(videoVO.getCreatedOn());
		videoKey.setCreatedBy(videoVO.getCreatedBy());
		this.pk = videoKey;
		this.title = videoVO.getTitle();
		this.intro = videoVO.getIntro();
		this.videourl = videoVO.getVideourl();
		this.videoimgurl = videoVO.getVideoimgurl();
		this.approved = videoVO.getApproved();
		this.approvedBy = videoVO.getApprovedBy();
		this.approvedOn = videoVO.getApprovedOn();
		this.updatedOn = videoVO.getUpdatedOn();
		this.updatedBy = videoVO.getUpdatedBy();
		this.rating = videoVO.getRating();
		this.hits = videoVO.getHits();
		this.author = videoVO.getAuthor();
	}

	public VideoKey getPk() {
		return pk;
	}

	public void setPk(VideoKey pk) {
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

	public String getVideourl() {
		return videourl;
	}

	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}

	public String getVideoimgurl() {
		return videoimgurl;
	}

	public void setVideoimgurl(String videoimgurl) {
		this.videoimgurl = videoimgurl;
	}

	public Long getHits() {
		return hits;
	}

	public void setHits(Long hits) {
		this.hits = hits;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Boolean isApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Long getApprovedOn() {
		return approvedOn;
	}

	public void setApprovedOn(Long approvedOn) {
		this.approvedOn = approvedOn;
	}

	public Long getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
