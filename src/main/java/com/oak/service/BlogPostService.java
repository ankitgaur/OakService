package com.oak.service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.comparators.BlogEntryComparator;
import com.oak.entities.Alias;
import com.oak.entities.BlogPost;
import com.oak.entities.BlogPostKey;
import com.oak.repositories.BlogPostRepo;

@Service("blogEntryService")
public class BlogPostService {

	@Autowired
	private BlogPostRepo blogEntryRepo;
	
	@Autowired
	AliasService aliasService;
	
	public List<BlogPost> getBlogEntries() {
		return blogEntryRepo.getBlogEntries();
	}

	public List<BlogPost> getBlogsByPopularity(){
		
		List<BlogPost> blogs = getBlogEntries();
		Collections.sort(blogs,new BlogEntryComparator());
		
		return blogs;
	}
	
	public List<BlogPost> getTopBlogEntriesByBlog(String category, int limit) {

		return blogEntryRepo.getTopBlogEntriesByCategory(category, limit);

	}

	public List<BlogPost> getTopBlogEntriesByLimit(int limit) {
		
		return blogEntryRepo.getTopBlogEntriesByLimit(limit);

	}

	public BlogPost getBlogEntryById(BlogPostKey id) {
		return blogEntryRepo.getBlogEntryById(id);
	}

	public void deleteBlogEntryById(BlogPostKey id) {
		
		blogEntryRepo.deleteBlogEntryById(id);;

	}

	public void createBlogEntry(BlogPost blog) {

		UUID uuid = UUID.randomUUID();
		Alias alias = new Alias();
		alias.setId(uuid.toString());
		alias.setCategory(blog.getBlogKey().getBlog());
		alias.setCreatedby(blog.getBlogKey().getCreatedBy());
		alias.setCreatedon(blog.getBlogKey().getCreatedOn());
		aliasService.createAlias(alias);
			
		blog.setAlias(alias.getId());
		blogEntryRepo.createBlogEntry(blog);

	}

	public void updateBlogEntry(BlogPost blog) {

		blogEntryRepo.updateBlogEntry(blog);

	}

}
