package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.Blog;
import com.oak.entities.BlogKey;
import com.oak.repositories.BlogsRepo;

@Service("blogsService")
public class BlogService {

	@Autowired
	private BlogsRepo blogsRepo;

	public List<Blog> getBlogs() {
		return blogsRepo.getBlogs();
	}

	public Blog getBlogsById(BlogKey id) {
		return blogsRepo.getBlogsById(id);
	}

	public void deleteBlogsById(BlogKey id) {

		blogsRepo.deleteBlogsById(id);

	}

	public void createBlogs(Blog blog) {

		blogsRepo.createBlogs(blog);

	}

	public void updateBlog(Blog blog) {

		blogsRepo.updateBlog(blog);

	}

}
