package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.BlogEntry;
import com.oak.entities.BlogKey;
import com.oak.repositories.BlogEntryRepo;

@Service("blogEntryService")
public class BlogEntryService {

	@Autowired
	private BlogEntryRepo blogEntryRepo;
	
	public List<BlogEntry> getBlogEntries() {
		return blogEntryRepo.getBlogEntries();
	}

	public List<BlogEntry> getTopBlogEntriesByBlog(String category, int limit) {

		return blogEntryRepo.getTopBlogEntriesByCategory(category, limit);

	}

	public List<BlogEntry> getTopBlogEntriesByLimit(int limit) {
		
		return blogEntryRepo.getTopBlogEntriesByLimit(limit);

	}

	public BlogEntry getBlogEntryById(BlogKey id) {
		return blogEntryRepo.getBlogEntryById(id);
	}

	public void deleteBlogEntryById(BlogKey id) {
		
		blogEntryRepo.deleteBlogEntryById(id);;

	}

	public void createBlogEntry(BlogEntry blog) {

		blogEntryRepo.createBlogEntry(blog);

	}

	public void updateBlogEntry(BlogEntry blog) {

		blogEntryRepo.updateBlogEntry(blog);

	}

}
