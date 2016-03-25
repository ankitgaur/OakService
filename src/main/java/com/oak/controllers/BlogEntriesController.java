package com.oak.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oak.entities.BlogEntry;
import com.oak.entities.BlogEntryKey;
import com.oak.service.BlogEntryService;
import com.oak.vo.BlogEntryVO;

@RestController
public class BlogEntriesController {

	@Autowired
	BlogEntryService blogEntryService;

	@CrossOrigin
	@RequestMapping(value = "/blog_entries/{id}", produces = "application/json", method = RequestMethod.GET)
	public BlogEntryVO getBlogEntryByID(@PathVariable("id") String id)
			throws JsonProcessingException {

		String blogKey[] = id.split("_");
		BlogEntryKey key = new BlogEntryKey(blogKey[0],
				Long.parseLong(blogKey[1]));

		BlogEntry blog = blogEntryService.getBlogEntryById(key);
		BlogEntryVO blogVO = new BlogEntryVO(blog);
		Date updateDate = new Date(blog.getUpdatedOn());
		Date createDate = new Date(blog.getBlogKey().getCreatedOn());
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"dd-MM-yyyy HH:mm:ss");
		String dateCreateText = dateFormatter.format(createDate);
		String updateCreateText = dateFormatter.format(updateDate);
		blogVO.setCreatedOnDate(dateCreateText);
		blogVO.setUpdatedOnDate(updateCreateText);
		return blogVO;

	}

	// TODO : update code to get blog entries for a blog
	@CrossOrigin
	@RequestMapping(value = "/blog_entries/blogs/{blog}", produces = "application/json", method = RequestMethod.GET)
	public List<BlogEntryVO> getBlogEntriesForBlog(
			@PathVariable("blog") String blog) throws JsonProcessingException {

		int limit = 500;
		List<BlogEntry> blogs = blogEntryService.getTopBlogEntriesByBlog(blog,
				limit);

		List<BlogEntryVO> blogVO = new ArrayList<BlogEntryVO>();
		for (BlogEntry blogEntry : blogs) {
			BlogEntryVO vo = new BlogEntryVO(blogEntry);
			Date updateDate = new Date(blogEntry.getUpdatedOn());
			Date createDate = new Date(blogEntry.getBlogKey().getCreatedOn());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String dateCreateText = dateFormatter.format(createDate);
			String updateCreateText = dateFormatter.format(updateDate);
			vo.setCreatedOnDate(dateCreateText);
			vo.setUpdatedOnDate(updateCreateText);
			blogVO.add(vo);
		}

		return blogVO;

	}

	@CrossOrigin
	@RequestMapping(value = "/popular_blog_entries", produces = "application/json", method = RequestMethod.GET)
	public List<BlogEntryVO> getPopularBlogEntries() throws JsonProcessingException {
		
		List<BlogEntry> blogs = blogEntryService
				.getBlogsByPopularity();

		List<BlogEntryVO> blogVO = new ArrayList<BlogEntryVO>();
		for (BlogEntry blog : blogs) {
			BlogEntryVO vo = new BlogEntryVO(blog);
			Date updateDate = new Date(blog.getUpdatedOn());
			Date createDate = new Date(blog.getBlogKey().getCreatedOn());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String dateCreateText = dateFormatter.format(createDate);
			String updateCreateText = dateFormatter.format(updateDate);
			vo.setCreatedOnDate(dateCreateText);
			vo.setUpdatedOnDate(updateCreateText);
			blogVO.add(vo);
		}

		return blogVO;

	}
	
	@CrossOrigin
	@RequestMapping(value = "/blog_entries", produces = "application/json", method = RequestMethod.GET)
	public List<BlogEntryVO> getBlogEntries() throws JsonProcessingException {

		int limit = 100;
		List<BlogEntry> blogs = blogEntryService
				.getTopBlogEntriesByLimit(limit);

		List<BlogEntryVO> blogVO = new ArrayList<BlogEntryVO>();
		for (BlogEntry blog : blogs) {
			BlogEntryVO vo = new BlogEntryVO(blog);
			Date updateDate = new Date(blog.getUpdatedOn());
			Date createDate = new Date(blog.getBlogKey().getCreatedOn());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String dateCreateText = dateFormatter.format(createDate);
			String updateCreateText = dateFormatter.format(updateDate);
			vo.setCreatedOnDate(dateCreateText);
			vo.setUpdatedOnDate(updateCreateText);
			blogVO.add(vo);
		}

		return blogVO;

	}

	@CrossOrigin
	@RequestMapping(value = "/blog_entries/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<BlogEntryVO> deleteBlogEntry(
			@PathVariable("id") String id) {
		System.out.println("Fetching & Deleting User with id " + id);
		String blogKey[] = id.split("_");
		BlogEntryKey key = new BlogEntryKey(blogKey[0],
				Long.parseLong(blogKey[1]));
		blogEntryService.deleteBlogEntryById(key);
		return new ResponseEntity<BlogEntryVO>(HttpStatus.NO_CONTENT);
	}

	@CrossOrigin
	@RequestMapping(value = "/blog_entries", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createBlogEntry(@RequestBody BlogEntryVO blogVO)
			throws ParseException {

		Date dNow = new Date();
		/*
		 * SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		 * Date dt = ft.parse(ft.format(dNow));
		 */
		blogVO.setCreatedOn(dNow.getTime());
		blogVO.setUpdatedOn(dNow.getTime());
		blogEntryService.createBlogEntry(new BlogEntry(blogVO));
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(value = "/blog_entries/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<BlogEntryVO> updateBlogEntry(
			@PathVariable("id") String id, @RequestBody BlogEntryVO blogVO)
			throws ParseException {

		System.out.println("Updating User " + id);
		String blogKey[] = id.split("_");
		BlogEntryKey key = new BlogEntryKey(blogKey[0],
				Long.parseLong(blogKey[1]));
		BlogEntry currentBlog = blogEntryService.getBlogEntryById(key);
		if (currentBlog == null) {
			System.out.println("Blogs with id " + id + " not found");
		}
		Date dNow = new Date();
		/*
		 * SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		 * Date dt = ft.parse(ft.format(dNow));
		 */
		blogVO.setCreatedOn(currentBlog.getBlogKey().getCreatedOn());
		blogVO.setUpdatedOn(dNow.getTime());
		blogEntryService.updateBlogEntry(new BlogEntry(blogVO));
		return new ResponseEntity<BlogEntryVO>(blogVO, HttpStatus.OK);

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