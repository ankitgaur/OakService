package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Blog;
import com.oak.entities.BlogCategory;
import com.oak.entities.BlogKey;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("blogsRepo")
public class BlogRepo {

	@Autowired
	private OakCassandraTemplate oakCassandraTemplate;

	public List<Blog> getBlogs() {
		List<Blog> blogs = oakCassandraTemplate.findAll(Blog.class);
		return blogs;
	}

	public Blog getBlogByID(BlogKey key) {
		Blog blog = oakCassandraTemplate.findById(key, Blog.class);
		return blog;
	}

	public void createBlog(Blog blog) {

		oakCassandraTemplate.create(blog, Blog.class);

	}

	public void updateBlog(Blog blog) {

		oakCassandraTemplate.update(blog, Blog.class);

	}

	public void deleteBlogCategoryById(BlogKey key) {

		oakCassandraTemplate.deleteById(key, Blog.class);

	}

}
