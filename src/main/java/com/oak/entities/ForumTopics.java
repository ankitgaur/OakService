package com.oak.entities;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.ForumTopicsVO;

@Table(value = "forum_topics")
public class ForumTopics {

	@PrimaryKey
	private ForumTopicsKey pk;
	private String title;
	private String alias;
	private String displayImage;
	private Long updatedOn;
	private String updatedBy;
	private Integer rating;
	private Long hits;

	public ForumTopics() {

	}

	public ForumTopics(ForumTopicsVO forumTopicsVO) {
		super();
		ForumTopicsKey forumTopicsKey = new ForumTopicsKey();
		forumTopicsKey.setCategory(forumTopicsVO.getCategory());
		forumTopicsKey.setCreatedOn(forumTopicsVO.getCreatedOn());
		forumTopicsKey.setCreatedBy(forumTopicsVO.getCreatedBy());
		this.pk = forumTopicsKey;
		this.title = forumTopicsVO.getTitle();
		this.displayImage = forumTopicsVO.getDisplayImage();
		this.updatedOn = forumTopicsVO.getUpdatedOn();
		this.updatedBy = forumTopicsVO.getUpdatedBy();
		this.rating = forumTopicsVO.getRating();
		this.hits = forumTopicsVO.getHits();
	}

	public ForumTopicsKey getPk() {
		return pk;
	}

	public void setPk(ForumTopicsKey pk) {
		this.pk = pk;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDisplayImage() {
		return displayImage;
	}

	public void setDisplayImage(String displayImage) {
		this.displayImage = displayImage;
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
