package com.oak.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.oak.config.OakConstants;
import com.oak.entities.Video;

public class VideoVO {

	private String id;
	private String category;	
	private String title;
	private String intro;    
	private String videourl;
	private String videoimgurl;
	private boolean approved;
	private String approvedBy;
	private Long approvedOn;
	private Long createdOn;
	private Long updatedOn;
	private String createdBy;
	private String author;
	private String updatedBy;
	private Integer rating;
	private Long hits;
	private String createdOnStr;
	private String updatedOnStr;
	
	public VideoVO() {

	}

	public VideoVO(Video video) {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat(OakConstants.DATE_FORMAT);
		this.category = video.getPk().getCategory();
		this.createdOn = video.getPk().getCreatedOn();
		this.title = video.getTitle();
		this.intro = video.getIntro();    
		this.videourl = video.getVideourl();
		this.videoimgurl = video.getVideoimgurl();
		this.approved = video.isApproved();
		this.approvedBy = video.getApprovedBy();
		this.approvedOn = video.getApprovedOn();
		this.updatedOn = video.getUpdatedOn();
		this.createdBy = video.getPk().getCreatedBy();
		this.author = video.getAuthor();
		this.updatedBy = video.getUpdatedBy();
		this.rating = video.getRating();
		this.hits = video.getHits();
		this.id = video.getAlias();
		if(createdOn!=null){
			this.createdOnStr = sdf.format(new Date(createdOn));
		}
		if(updatedOn!=null){
			this.updatedOnStr = sdf.format(new Date(updatedOn));
		}

	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public boolean getApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
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

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Long getHits() {
		return hits;
	}

	public void setHits(Long hits) {
		this.hits = hits;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
