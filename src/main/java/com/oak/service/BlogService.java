package com.oak.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.Alias;
import com.oak.entities.Blog;
import com.oak.entities.BlogKey;
import com.oak.repositories.BlogRepo;

@Service("blogService")
public class BlogService {

	@Autowired
	private BlogRepo blogRepo;
	
	@Autowired
	AliasService aliasService;
	
	public List<Blog> getBlogs() {
		return blogRepo.getBlogs();
	}

	public Blog getBlogById(BlogKey key) {
		return blogRepo.getBlogByID(key);
	}

	public void deleteBlogById(BlogKey id) {
		
		blogRepo.deleteBlogById(id);;

	}

	public void createBlog(Blog blog) {

		UUID uuid = UUID.randomUUID();
		Alias alias = new Alias();
		alias.setId(uuid.toString());
		alias.setCategory(blog.getBlogKey().getCategory());
		alias.setCreatedby(blog.getBlogKey().getCreatedby());
		alias.setCreatedon(blog.getBlogKey().getCreatedOn());
		aliasService.createAlias(alias);
			
		blog.setAlias(alias.getId());
		blogRepo.createBlog(blog);

	}

	public void updateBlog(Blog blog) {

		blogRepo.updateBlog(blog);

	}

	public List<Blog> getBlogsForUser(String email) {
		
		return blogRepo.getBlogsForUser(email);
	}

}
