package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.BlogPost;
import com.oak.entities.BlogPostKey;
import com.oak.utils.OakCassandraTemplate;

@Repository("blogEntryRepo")
@Transactional
public class BlogPostRepo {

	@Autowired
	private OakCassandraTemplate oakCassendraTemplate;

	@Autowired
	private CountersRepo countersRepo;

	public List<BlogPost> getBlogEntries() {
		//List<BlogEntry> blogs = oakCassendraTemplate.findAll(BlogEntry.class);
		//return blogs;
		List<BlogPost> posts = getTopBlogEntriesByLimit(100);
		return posts;
	}

	public void incrementHits(String blog, long createdOn) {
		
		BlogPostKey id = new BlogPostKey();
		id.setBlog(blog);
		id.setCreatedOn(createdOn);

		long hits = getBlogEntryById(id).getHits();
		long hitsnew = hits + 1;

		String sql = "update oak.blog_posts set hits=" + hitsnew
				+ " where blog='" + blog + "' and createdon='" + createdOn
				+ "'";
		oakCassendraTemplate.executeQuery(sql);
	}

	public List<BlogPost> getTopBlogEntriesByCategory(String category,
			int limit) {
		String blogs_by_category_qry = "SELECT * FROM blog_posts WHERE blog=";
		blogs_by_category_qry = blogs_by_category_qry + "'" + category + "'"
				+ " LIMIT " + limit;
		System.out
				.println("BLOGS_BY_CATEGORY_QRY ::: " + blogs_by_category_qry);
		List<BlogPost> blogs = oakCassendraTemplate.findByPartitionKey(
				blogs_by_category_qry, BlogPost.class);
		return blogs;
	}

	public List<BlogPost> getTopBlogEntriesByLimit(int limit) {
		String blogs_by_limit_qry = "SELECT * FROM blog_posts LIMIT ";
		blogs_by_limit_qry = blogs_by_limit_qry + limit;
		System.out.println("BLOGS_BY_CATEGORY_QRY ::: " + blogs_by_limit_qry);
		List<BlogPost> articles = oakCassendraTemplate.findByLimit(
				blogs_by_limit_qry, BlogPost.class);
		return articles;
	}

	public BlogPost getBlogEntryById(BlogPostKey id) {
		BlogPost blog = oakCassendraTemplate.findById(id, BlogPost.class);
		return blog;
	}

	public void deleteBlogEntryById(BlogPostKey id) {

		oakCassendraTemplate.deleteById(id, BlogPost.class);

	}

	public void createBlogEntry(BlogPost blog) {

		oakCassendraTemplate.create(blog, BlogPost.class);
		countersRepo.increment(blog.getBlogKey().getBlog() + "_count");

	}

	public void updateBlogEntry(BlogPost blog) {

		oakCassendraTemplate.update(blog, BlogPost.class);

	}

	public List<BlogPost> getTopBlogEntriesByUser(String user, int limit) {
		String blogs_by_category_qry = "SELECT * FROM blog_posts WHERE createdby=";
		blogs_by_category_qry = blogs_by_category_qry + "'" + user + "'"
				+ " LIMIT " + limit+" allow filtering";
		System.out
				.println("BLOGS_BY_CATEGORY_QRY ::: " + blogs_by_category_qry);
		List<BlogPost> blogs = oakCassendraTemplate.findByPartitionKey(
				blogs_by_category_qry, BlogPost.class);
		return blogs;
	}

}
