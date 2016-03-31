package com.oak.entities;

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
	private Long approvedOn;
	private Long updatedOn;
	private String createdBy;
	private String updatedBy;
	private Integer rating;
	private Long hits;
	private String intro;
	
	public Article() {

	}

	public Article(ArticleVO articleVO) {
		super();
		ArticleKey aqrticleKey = new ArticleKey();
		aqrticleKey.setCategory(articleVO.getCategory());
		aqrticleKey.setCreatedOn(articleVO.getCreatedOn());
		this.pk = aqrticleKey;
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
		this.intro = articleVO.getIntro();
	}

	public ArticleKey getPk() {
		return pk;
	}

	public void setPk(ArticleKey pk) {
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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

}
