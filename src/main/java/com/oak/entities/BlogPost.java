package com.oak.entities;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.oak.vo.BlogPostVO;

@Table("blog_posts")
public class BlogPost {

	@PrimaryKey
	private BlogPostKey blogKey;
	private String blogname;
	private String title;
	private String alias;
	private String content;
	private String displayImage;
	private Boolean approved;
	private String approvedBy;
	private Long approvedOn;
	private Long updatedOn;	
	private String updatedBy;
	private Integer rating;
	private String author;
	private Long hits;

	public BlogPost() {
		super();
	}

	public BlogPost(BlogPostVO blogVO) {
		super();
		BlogPostKey key = new BlogPostKey();
		key.setBlog(blogVO.getBlog());
		key.setCreatedOn(blogVO.getCreatedOn());
		key.setCreatedBy(blogVO.getCreatedBy());
		this.blogKey = key;
		this.title = blogVO.getTitle();
		this.content = blogVO.getContent();
		this.displayImage = blogVO.getDisplayImage();
		this.approved = blogVO.getApproved();
		this.approvedBy = blogVO.getApprovedBy();
		this.approvedOn = blogVO.getApprovedOn();
		this.updatedOn = blogVO.getUpdatedOn();		
		this.updatedBy = blogVO.getUpdatedBy();
		this.rating = blogVO.getRating();
		this.blogname = blogVO.getBlogname();
		this.author = blogVO.getAuthor();
	}

	public BlogPostKey getBlogKey() {
		return blogKey;
	}

	public void setBlogKey(BlogPostKey blogKey) {
		this.blogKey = blogKey;
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

	public String getBlogname() {
		return blogname;
	}

	public void setBlogname(String blogname) {
		this.blogname = blogname;
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
