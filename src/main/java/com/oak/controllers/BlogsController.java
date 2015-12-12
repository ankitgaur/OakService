package com.oak.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oak.dto.Response;
import com.oak.entities.Blog;
import com.oak.repositories.BlogsRepo;

@RestController
public class BlogsController {

	@Autowired
	BlogsRepo blogsRepo;

	@RequestMapping(value = "/blogs/{id}", produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody
	String getBlogByID(@PathVariable("id") long ID)
			throws JsonProcessingException {

		Blog blogs = blogsRepo.getBlogsById(ID);
		Response response = new Response();
		response.setStatuscode("Long  " + ID);
		response.setStatusmsg("Received " + ID);
		response.setResult(blogs);
		return response.toString();

	}

	@RequestMapping(value = "/blogs", produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody
	String getBlogs() throws JsonProcessingException {
		List<Blog> blogs = blogsRepo.getBlogs();
		Response response = new Response();
		response.setResult(blogs);
		return response.toString();

	}

	@RequestMapping(value = "/blogs/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Blog> deleteUser(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting User with id " + id);

		Blog blog = blogsRepo.getBlogsById(id);
		if (blog == null) {
			System.out.println("Unable to delete. User with id " + id
					+ " not found");
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		}

		blogsRepo.deleteBlogsById(id);

		return new ResponseEntity<Blog>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/blogs", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createBlog(@RequestBody Blog blog,
			UriComponentsBuilder ucBuilder) {

		if (blogsRepo.isBlogExist(blog)) {
			System.out.println("A User with name " + blog.getBlog_id()
					+ " already exist");
		}
		blogsRepo.saveBlogs(blog);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/blogs/{id}")
				.buildAndExpand(blog.getBlog_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/blogs/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<Blog> updateUser(@PathVariable("id") long id,
			@RequestBody Blog blog) {

		System.out.println("Updating User " + id);
		Blog currentBlog = blogsRepo.getBlogsById(id);
		if (currentBlog == null) {
			System.out.println("Blogs with id " + id + " not found");
		}
		currentBlog.setTitle(blog.getTitle());
		currentBlog.setContent(blog.getContent());
		currentBlog.setCreatedBy(blog.getCreatedBy());
		blogsRepo.updateBlog(currentBlog);
		return new ResponseEntity<Blog>(currentBlog, HttpStatus.OK);

	}
}