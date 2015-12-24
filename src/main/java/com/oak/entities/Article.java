package com.oak.entities;

import java.util.Date;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.ArticleVO;

@Table(value = "articles")
public class Article {

	/*
	 * @PrimaryKey private Long id;
	 */

	@PrimaryKey
	private ArticleKey pk;
	private String title;
	private String content;
	private String displayImage;
	private Boolean approved;
	private String approvedBy;
	private Date approvedOn;
	private Long createdOn;
	private String createdBy;
	private String updatedBy;
	private Integer rating;
	private Long hits;

	public Article() {

	}

	public Article(ArticleVO articleVO) {
		super();
		ArticleKey aqrticleKey = new ArticleKey();
		aqrticleKey.setCategory(articleVO.getCategory());
		aqrticleKey.setUpdatedOn(articleVO.getUpdatedOn());
		this.pk = aqrticleKey;
		this.title = articleVO.getTitle();
		this.content = articleVO.getContent();
		this.displayImage = articleVO.getDisplayImage();
		this.approved = articleVO.getApproved();
		this.approvedBy = articleVO.getApprovedBy();
		this.approvedOn = articleVO.getApprovedOn();
		this.createdOn = articleVO.getCreatedOn();
		this.createdBy = articleVO.getCreatedBy();
		this.updatedBy = articleVO.getUpdatedBy();
		this.rating = articleVO.getRating();
		this.hits = articleVO.getHits();
	}

	public ArticleKey getPk() {
		return pk;
	}

	public void setPk(ArticleKey pk) {
		this.pk = pk;
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

	public Date getApprovedOn() {
		return approvedOn;
	}

	public void setApprovedOn(Date approvedOn) {
		this.approvedOn = approvedOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
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
