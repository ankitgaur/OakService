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
	private String alias;
	private String content;
	private String displayImage;
	private Boolean approved;
	private String approvedBy;
	private Long approvedOn;
	private Long updatedOn;
	private String author;
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
		aqrticleKey.setCreatedBy(articleVO.getCreatedBy());
		this.pk = aqrticleKey;
		this.title = articleVO.getTitle();
		this.content = articleVO.getContent();
		this.displayImage = articleVO.getDisplayImage();
		this.approved = articleVO.getApproved();
		this.approvedBy = articleVO.getApprovedBy();
		this.approvedOn = articleVO.getApprovedOn();
		this.updatedOn = articleVO.getUpdatedOn();		
		this.updatedBy = articleVO.getUpdatedBy();
		this.rating = articleVO.getRating();
		this.hits = articleVO.getHits();
		this.intro = articleVO.getIntro();
		this.author = articleVO.getAuthor();
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
