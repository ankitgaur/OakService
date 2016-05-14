package com.oak.entities;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.ForumPostVO;

@Table(value = "forum_post")
public class ForumPost {

	/*
	 * @PrimaryKey private Long id;
	 */

	@PrimaryKey
	private ForumPostKey pk;
	private Boolean approved;
	private String approvedBy;
	private Long approvedOn;
	private Long updatedOn;
	private String content;
	private String createdBy;
	private String displayImage;
	private Integer rating;
	private String title;
	private String updatedBy;
	private Long hits;

	public ForumPost() {

	}

	public ForumPost(ForumPostVO articleVO) {
		super();
		ForumPostKey forumPostKey = new ForumPostKey();
		forumPostKey.setTopic(articleVO.getCategory());
		forumPostKey.setCreatedOn(articleVO.getCreatedOn());
		this.pk = forumPostKey;
		this.title = articleVO.getTitle();
		this.content = articleVO.getContent();
		this.displayImage = articleVO.getDisplayImage();
		this.approved = articleVO.getApproved();
		this.approvedBy = articleVO.getApprovedBy();
		this.approvedOn = articleVO.getApprovedOn();
		this.updatedOn = articleVO.getUpdatedOn();
		this.createdBy = articleVO.getCreatedBy();
		this.updatedBy = articleVO.getUpdatedBy();
		this.rating = articleVO.getRating();
		this.hits = articleVO.getHits();
	}

	public ForumPostKey getPk() {
		return pk;
	}

	public void setPk(ForumPostKey pk) {
		this.pk = pk;
	}

	public Long getApprovedOn() {
		return approvedOn;
	}

	public void setApprovedOn(Long approvedOn) {
		this.approvedOn = approvedOn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDisplayImage() {
		return displayImage;
	}

	public void setDisplayImage(String displayImage) {
		this.displayImage = displayImage;
	}

	public Boolean getApproved() {
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

	public String getCreatedBy() {
		return createdBy;
	}

	public Long getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
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

}
