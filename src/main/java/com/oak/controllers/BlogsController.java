package com.oak.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oak.entities.Blog;
import com.oak.service.BlogService;

@RestController
public class BlogsController {

	@Autowired
	BlogService blogsService;

	@RequestMapping(value = "/blogs/{id}", produces = "application/json", method = RequestMethod.GET)
	public Blog getBlogByID(@PathVariable("id") long ID)
			throws JsonProcessingException {
		Blog blogs = blogsService.getBlogsById(ID);
		return blogs;

	}

	@RequestMapping(value = "/blogs", produces = "application/json", method = RequestMethod.GET)
	public List<Blog> getBlogs() throws JsonProcessingException {
		List<Blog> blogs = blogsService.getBlogs();
		return blogs;

	}

	@RequestMapping(value = "/blogs/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Blog> deleteBlog(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting User with id " + id);

		Blog blog = blogsService.getBlogsById(id);
		if (blog == null) {
			System.out.println("Unable to delete. User with id " + id
					+ " not found");
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		}

		blogsService.deleteBlogsById(id);

		return new ResponseEntity<Blog>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/blogs", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createBlog(@RequestBody Blog blog,
			UriComponentsBuilder ucBuilder) {

		if (blogsService.isBlogExist(blog)) {
			System.out.println("A User with name " + blog.getBlog_id()
					+ " already exist");
		}
		blogsService.createBlogs(blog);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/blogs/{id}")
				.buildAndExpand(blog.getBlog_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/blogs/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<Blog> updateBlog(@PathVariable("id") long id,
			@RequestBody Blog blog) {

		System.out.println("Updating User " + id);
		Blog currentBlog = blogsService.getBlogsById(id);
		if (currentBlog == null) {
			System.out.println("Blogs with id " + id + " not found");
		}
		currentBlog.setTitle(blog.getTitle());
		currentBlog.setContent(blog.getContent());
		currentBlog.setCreatedBy(blog.getCreatedBy());
		blogsService.updateBlog(currentBlog);
		return new ResponseEntity<Blog>(currentBlog, HttpStatus.OK);

	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody
	String handleException(Exception ex) {
		if (ex.getMessage().contains("UNIQUE KEY"))
			return "The submitted item already exists.";
		else
			System.out.println(this.getClass() + ": need handleException for: "
					+ ex.getMessage());
		return ex.getMessage();
	}
}