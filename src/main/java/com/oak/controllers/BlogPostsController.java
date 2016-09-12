package com.oak.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.oak.entities.Alias;
import com.oak.entities.BlogPost;
import com.oak.entities.BlogPostKey;
import com.oak.entities.User;
import com.oak.service.AliasService;
import com.oak.service.BlogPostService;
import com.oak.service.UsersService;
import com.oak.vo.BlogPostVO;

@RestController
public class BlogPostsController {

	@Autowired
	BlogPostService blogEntryService;
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	AliasService aliasService;
	
	@CrossOrigin
	@RequestMapping(value = "/blog_entries/{id}", produces = "application/json", method = RequestMethod.GET)
	public BlogPostVO getBlogEntryByID(@PathVariable("id") String id)
			throws JsonProcessingException {
		
		Alias alias = aliasService.getAliasById(id);		
		BlogPostKey key = new BlogPostKey(alias.getCategory(), alias.getCreatedby(),alias.getCreatedon());

		BlogPost blog = blogEntryService.getBlogEntryById(key);
		BlogPostVO blogVO = new BlogPostVO(blog);
		
		return blogVO;

	}

	// TODO : update code to get blog entries for a blog
	@CrossOrigin
	@RequestMapping(value = "/blog_entries/blogs/{blog}", produces = "application/json", method = RequestMethod.GET)
	public List<BlogPostVO> getBlogEntriesForBlog(
			@PathVariable("blog") String blog) throws JsonProcessingException {

		int limit = 500;
		List<BlogPost> blogs = blogEntryService.getTopBlogEntriesByBlog(blog,
				limit);

		List<BlogPostVO> blogVO = new ArrayList<BlogPostVO>();
		for (BlogPost blogEntry : blogs) {
			BlogPostVO vo = new BlogPostVO(blogEntry);			
			blogVO.add(vo);
		}

		return blogVO;

	}

	@CrossOrigin
	@RequestMapping(value = "/popular_blog_entries", produces = "application/json", method = RequestMethod.GET)
	public List<BlogPostVO> getPopularBlogEntries()
			throws JsonProcessingException {

		List<BlogPost> blogs = blogEntryService.getBlogsByPopularity();

		List<BlogPostVO> blogVO = new ArrayList<BlogPostVO>();
		for (BlogPost blog : blogs) {
			BlogPostVO vo = new BlogPostVO(blog);			
			blogVO.add(vo);
		}

		return blogVO;

	}
	
	@CrossOrigin
	@RequestMapping(value = "/blog_entries", produces = "application/json", method = RequestMethod.GET)
	public List<BlogPostVO> getBlogEntries() throws JsonProcessingException {

		int limit = 100;
		List<BlogPost> blogs = blogEntryService
				.getTopBlogEntriesByLimit(limit);

		List<BlogPostVO> blogVO = new ArrayList<BlogPostVO>();
		for (BlogPost blog : blogs) {
			BlogPostVO vo = new BlogPostVO(blog);
			blogVO.add(vo);
		}

		return blogVO;

	}

	@CrossOrigin
	@RequestMapping(value = "/blog_entries/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<BlogPostVO> deleteBlogEntry(
			@PathVariable("id") String id) {
		System.out.println("Fetching & Deleting User with id " + id);
		Alias alias = aliasService.getAliasById(id);		
		BlogPostKey key = new BlogPostKey(alias.getCategory(), alias.getCreatedby(),alias.getCreatedon());
		
		blogEntryService.deleteBlogEntryById(key);
		return new ResponseEntity<BlogPostVO>(HttpStatus.NO_CONTENT);
	}

	@CrossOrigin
	@RequestMapping(value = "/blog_entries", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createBlogEntry(@RequestBody BlogPostVO blogVO)
			throws ParseException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		User user = usersService.getUserById(email);
		Date dNow = new Date();
		/*
		 * SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		 * Date dt = ft.parse(ft.format(dNow));
		 */
		blogVO.setCreatedOn(dNow.getTime());
		blogVO.setCreatedBy(email);
		blogVO.setAuthor(user.getUsername());
		
		blogVO.getBlog();
		blogEntryService.createBlogEntry(new BlogPost(blogVO));		
		
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(value = "/blog_entries/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<BlogPostVO> updateBlogEntry(
			@PathVariable("id") String id, @RequestBody BlogPostVO blogVO)
			throws ParseException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		
		System.out.println("Updating User " + id);
		Alias alias = aliasService.getAliasById(id);		
		BlogPostKey key = new BlogPostKey(alias.getCategory(), alias.getCreatedby(),alias.getCreatedon());
		BlogPost currentBlog = blogEntryService.getBlogEntryById(key);
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
		blogVO.setUpdatedBy(email);
		blogEntryService.updateBlogEntry(new BlogPost(blogVO));
		return new ResponseEntity<BlogPostVO>(blogVO, HttpStatus.OK);

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