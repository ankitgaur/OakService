package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Blog;

@Repository("blogsRepo")
@Transactional
public class BlogsRepo {

	@Autowired
	private CassandraOperations cassandraTemplate;

	public List<Blog> getBlogs() {
		List<Blog> blogs = cassandraTemplate.select("Select * from blogs",
				Blog.class);
		return blogs;
	}

	public Blog getBlogsById(long id) {
		Blog blog = cassandraTemplate.selectOneById(Blog.class, id);
		return blog;
	}

	public void deleteBlogsById(long id) {

		cassandraTemplate.deleteById(Blog.class, id);

	}

	public void saveBlogs(Blog blog) {

		cassandraTemplate.insert(blog);

	}

	public void updateBlog(Blog blog) {

		cassandraTemplate.update(blog);

	}

	public boolean isBlogExist(Blog blog) {

		List<Blog> blogs = getBlogs();

		for (Blog blogTemp : blogs) {
			if (blogTemp.getBlog_id() == blog.getBlog_id()) {
				return true;
			}
		}
		return false;

	}

}
