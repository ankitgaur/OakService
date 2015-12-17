package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Blog;
import com.oak.utils.OakCassandraTemplate;

@Repository("blogsRepo")
@Transactional
public class BlogsRepo {

	@Autowired
	private OakCassandraTemplate oakCassendraTemplate;

	public List<Blog> getBlogs() {
		List<Blog> blogs = oakCassendraTemplate.findAll(Blog.class);
		return blogs;
	}

	public Blog getBlogsById(long id) {
		Blog blog = oakCassendraTemplate.findById(id, Blog.class);
		return blog;
	}

	public void deleteBlogsById(long id) {

		oakCassendraTemplate.deleteById(id, Blog.class);

	}

	public void createBlogs(Blog blog) {

		oakCassendraTemplate.create(blog, Blog.class);

	}

	public void updateBlog(Blog blog) {

		oakCassendraTemplate.update(blog, Blog.class);

	}

}
