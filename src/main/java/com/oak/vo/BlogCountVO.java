package com.oak.vo;

import com.oak.entities.Blog;

public class BlogCountVO {

	private String title;
	private String displayimage;
	private String id;
	private long count;

	public BlogCountVO() {

	}

	public BlogCountVO(Blog blog) {
		this.title = blog.getTitle();
		this.displayimage = blog.getDisplayimage();
		this.id = blog.getBlogKey().getCategory()
				+ "_"+blog.getBlogKey().getCreatedOn();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDisplayimage() {
		return displayimage;
	}

	public void setDisplayimage(String displayimage) {
		this.displayimage = displayimage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
