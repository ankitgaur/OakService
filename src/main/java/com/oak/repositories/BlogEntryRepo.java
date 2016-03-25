package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.BlogEntry;
import com.oak.entities.BlogEntryKey;
import com.oak.utils.OakCassandraTemplate;

@Repository("blogEntryRepo")
@Transactional
public class BlogEntryRepo {

	@Autowired
	private OakCassandraTemplate oakCassendraTemplate;

	@Autowired
	private CountersRepo countersRepo;

	public List<BlogEntry> getBlogEntries() {
		//List<BlogEntry> blogs = oakCassendraTemplate.findAll(BlogEntry.class);
		//return blogs;
		List<BlogEntry> posts = getTopBlogEntriesByLimit(100);
		return posts;
	}

	public void incrementHits(String blog, long createdOn) {
		
		BlogEntryKey id = new BlogEntryKey();
		id.setBlog(blog);
		id.setCreatedOn(createdOn);

		long hits = getBlogEntryById(id).getHits();
		long hitsnew = hits + 1;

		String sql = "update oak.blog_entries set hits=" + hitsnew
				+ " where blog='" + blog + "' and createdon='" + createdOn
				+ "'";
		oakCassendraTemplate.executeQuery(sql);
	}

	public List<BlogEntry> getTopBlogEntriesByCategory(String category,
			int limit) {
		String blogs_by_category_qry = "SELECT * FROM blog_entries WHERE category=";
		blogs_by_category_qry = blogs_by_category_qry + "'" + category + "'"
				+ " LIMIT " + limit;
		System.out
				.println("BLOGS_BY_CATEGORY_QRY ::: " + blogs_by_category_qry);
		List<BlogEntry> blogs = oakCassendraTemplate.findByPartitionKey(
				blogs_by_category_qry, BlogEntry.class);
		return blogs;
	}

	public List<BlogEntry> getTopBlogEntriesByLimit(int limit) {
		String blogs_by_limit_qry = "SELECT * FROM blog_entries LIMIT ";
		blogs_by_limit_qry = blogs_by_limit_qry + limit;
		System.out.println("BLOGS_BY_CATEGORY_QRY ::: " + blogs_by_limit_qry);
		List<BlogEntry> articles = oakCassendraTemplate.findByLimit(
				blogs_by_limit_qry, BlogEntry.class);
		return articles;
	}

	public BlogEntry getBlogEntryById(BlogEntryKey id) {
		BlogEntry blog = oakCassendraTemplate.findById(id, BlogEntry.class);
		return blog;
	}

	public void deleteBlogEntryById(BlogEntryKey id) {

		oakCassendraTemplate.deleteById(id, BlogEntry.class);

	}

	public void createBlogEntry(BlogEntry blog) {

		oakCassendraTemplate.create(blog, BlogEntry.class);
		countersRepo.increment(blog.getBlogKey().getBlog() + "_count");

	}

	public void updateBlogEntry(BlogEntry blog) {

		oakCassendraTemplate.update(blog, BlogEntry.class);

	}

}
