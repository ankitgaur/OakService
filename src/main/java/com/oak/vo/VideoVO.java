package com.oak.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.oak.config.OakConstants;
import com.oak.entities.Video;

public class VideoVO {

	private String category;	
	private String title;
	private String intro;    
	private String videourl;
	private String videoimgurl;
	private boolean approved;
	private String approvedBy;
	private long approvedOn;
	private long createdOn;
	private long updatedOn;
	private String createdBy;
	private String updatedBy;
	private int rating;
	private long hits;
	private String createdOnDate;
	private String updatedOnDate;
	
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
		this.createdBy = video.getCreatedBy();
		this.updatedBy = video.getUpdatedBy();
		this.rating = video.getRating();
		this.hits = video.getHits();
		this.createdOnDate = sdf.format(new Date(createdOn));
		this.updatedOnDate = sdf.format(new Date(updatedOn));

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

	public long getApprovedOn() {
		return approvedOn;
	}

	public void setApprovedOn(long approvedOn) {
		this.approvedOn = approvedOn;
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	public long getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(long updatedOn) {
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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public long getHits() {
		return hits;
	}

	public void setHits(long hits) {
		this.hits = hits;
	}

	public String getCreatedOnDate() {
		return createdOnDate;
	}

	public void setCreatedOnDate(String createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

	public String getUpdatedOnDate() {
		return updatedOnDate;
	}

	public void setUpdatedOnDate(String updatedOnDate) {
		this.updatedOnDate = updatedOnDate;
	}	
}
