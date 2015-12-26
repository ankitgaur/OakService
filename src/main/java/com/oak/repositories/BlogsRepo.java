package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Blog;
import com.oak.entities.BlogKey;
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

	public List<Blog> getTopBlogsByCategory(String category, int limit) {
		String blogs_by_category_qry = "SELECT * FROM blogs WHERE category=";
		blogs_by_category_qry = blogs_by_category_qry + "'" + category + "'"
				+ " LIMIT " + limit;
		System.out.println("ARTICLE_BY_CATEGORY_QRY ::: "
				+ blogs_by_category_qry);
		List<Blog> blogs = oakCassendraTemplate.findByPartitionKey(
				blogs_by_category_qry, Blog.class);
		return blogs;
	}

	public List<Blog> getTopBlogsByLimit(int limit) {
		String blogs_by_limit_qry = "SELECT * FROM blogs LIMIT ";
		blogs_by_limit_qry = blogs_by_limit_qry + limit;
		System.out.println("ARTICLE_BY_CATEGORY_QRY ::: "
				+ blogs_by_limit_qry);
		List<Blog> articles = oakCassendraTemplate.findByLimit(
				blogs_by_limit_qry, Blog.class);
		return articles;
	}

	public Blog getBlogsById(BlogKey id) {
		Blog blog = oakCassendraTemplate.findById(id, Blog.class);
		return blog;
	}

	public void deleteBlogsById(BlogKey id) {

		oakCassendraTemplate.deleteById(id, Blog.class);

	}

	public void createBlogs(Blog blog) {

		oakCassendraTemplate.create(blog, Blog.class);

	}

	public void updateBlog(Blog blog) {

		oakCassendraTemplate.update(blog, Blog.class);

	}

}
