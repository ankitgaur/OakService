package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.Blog;
import com.oak.entities.BlogKey;
import com.oak.repositories.BlogRepo;

@Service("blogService")
public class BlogService {

	@Autowired
	private BlogRepo blogRepo;
	
	public List<Blog> getBlogs() {
		return blogRepo.getBlogs();
	}

	public Blog getBlogById(BlogKey key) {
		return blogRepo.getBlogByID(key);
	}

	public void deleteBlogById(BlogKey id) {
		
		blogRepo.deleteBlogById(id);;

	}

	public void createBlog(Blog blog) {

		blogRepo.createBlog(blog);

	}

	public void updateBlog(Blog blog) {

		blogRepo.updateBlog(blog);

	}

}
