package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Blog;
import com.oak.entities.BlogKey;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("blogRepo")
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

	public void deleteBlogById(BlogKey key) {

		oakCassandraTemplate.deleteById(key, Blog.class);

	}

	public List<Blog> getBlogsForUser(String user) {
		String blogs_by_user_qry = "SELECT * FROM blogs WHERE createdby=";
		blogs_by_user_qry = blogs_by_user_qry + "'" + user + "'"
				+ " allow filtering";
		System.out
				.println("BLOGS_FOR_USER_QRY ::: " + blogs_by_user_qry);
		List<Blog> blogs = oakCassandraTemplate.findByPartitionKey(
				blogs_by_user_qry, Blog.class);
		return blogs;
	}

}
