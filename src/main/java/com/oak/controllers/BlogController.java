package com.oak.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oak.entities.Alias;
import com.oak.entities.Blog;
import com.oak.entities.BlogKey;
import com.oak.entities.User;
import com.oak.service.AliasService;
import com.oak.service.BlogService;
import com.oak.service.CounterService;
import com.oak.service.ImageService;
import com.oak.service.UsersService;
import com.oak.vo.BlogCountVO;
import com.oak.vo.BlogVO;

@RestController
public class BlogController {

	@Autowired
	BlogService blogService;

	@Autowired
	CounterService counterService;

	@Autowired
	AliasService aliasService;

	@Autowired
	ImageService imageService;
	
	@Autowired
	UsersService userService;

	@CrossOrigin
	@RequestMapping(value = "/blogs", produces = "application/json", method = RequestMethod.GET)
	public List<BlogVO> getAllBlog() throws JsonParseException, JsonMappingException, IOException {
		List<Blog> blogs = blogService.getBlogs();
		if (blogs == null || blogs.isEmpty()) {
			return null;
		}
		List<BlogVO> blogsVO = new ArrayList<BlogVO>();

		for (Blog blog : blogs) {
			blogsVO.add(new BlogVO(blog));
		}

		return blogsVO;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/myblogs", produces = "application/json", method = RequestMethod.GET)
	public List<BlogVO> getBlogsForUser() throws JsonParseException, JsonMappingException, IOException {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		
		User user = userService.getUserById(email);
		
		if(user.getGroups().contains("author") || user.getGroups().contains("admin")){
			
			return getAllBlog();
		}
		
		List<Blog> blogs = blogService.getBlogsForUser(email);
		if (blogs == null || blogs.isEmpty()) {
			return null;
		}
		List<BlogVO> blogsVO = new ArrayList<BlogVO>();

		for (Blog blog : blogs) {
			blogsVO.add(new BlogVO(blog));
		}

		return blogsVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/blogcounts", produces = "application/json", method = RequestMethod.GET)
	public List<BlogCountVO> getAllBlogCounts() throws JsonParseException, JsonMappingException, IOException {
		List<Blog> blogs = blogService.getBlogs();
		if (blogs == null || blogs.isEmpty()) {
			return null;
		}
		List<BlogCountVO> blogsVO = new ArrayList<BlogCountVO>();

		for (Blog blog : blogs) {

			BlogCountVO bcvo = new BlogCountVO(blog);
			bcvo.setCount(counterService.getCounterValue(blog.getAlias() + "_count"));
			blogsVO.add(bcvo);
		}

		return blogsVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/blogs/{blogId}", produces = "application/json", method = RequestMethod.GET)
	public BlogVO getStateById(@PathVariable("blogId") String blogId)
			throws JsonParseException, JsonMappingException, IOException {

		Alias alias = aliasService.getAliasById(blogId);
		BlogKey key = new BlogKey(alias.getCategory(), alias.getCreatedby(), alias.getCreatedon());

		Blog blog = blogService.getBlogById(key);
		BlogVO blogVO = new BlogVO(blog);

		return blogVO;
	}

	@CrossOrigin
	@Secured("ROLE_blog_write")
	@RequestMapping(value = "/blogs", method = RequestMethod.POST)
	public ResponseEntity<Void> createBlog(@RequestParam("category") String category,
			@RequestParam("title") String title, @RequestParam("description") String description,
			@RequestParam(name = "displayImage", required = false) MultipartFile displayImage)
			throws JsonParseException, JsonMappingException, IOException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();

		String id = null;
		try {
			byte[] imgbytes = displayImage.getBytes();
			if (imgbytes != null) {
				id = imageService.saveImage("blogimage", displayImage.getOriginalFilename(), displayImage.getSize(),
						displayImage.getBytes(), email);
			}
		} catch (NullPointerException npe) {
			//do nothing
		}

		BlogKey blogKey = new BlogKey(category, email, new Date().getTime());

		Blog blog = new Blog();
		blog.setBlogKey(blogKey);

		blog.setTitle(title);
		blog.setDescription(description);
		if (id != null) {
			blog.setDisplayimage("http://www.ipledge2nigeria.com/service/" + id);
		}

		blogService.createBlog(blog);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@CrossOrigin
	@Secured("ROLE_blog_write")
	@RequestMapping(value = "/blogs/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<BlogVO> updateBlog(@PathVariable("id") String id, @RequestBody BlogVO blogVO)
			throws JsonGenerationException, JsonMappingException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		String blogKey[] = id.split("_");
		blogVO.setCategory(blogKey[0]);
		blogVO.setCreatedOn(Long.parseLong(blogKey[1]));
		blogVO.setUpdatedon(new Date().getTime());
		blogVO.setUpdatedby(email);
		blogService.updateBlog(new Blog(blogVO));
		return new ResponseEntity<BlogVO>(blogVO, HttpStatus.OK);

	}

	@CrossOrigin
	@Secured("ROLE_blog_write")
	@RequestMapping(value = "/blogs/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<BlogVO> deleteBlog(@PathVariable("id") String id) {
		System.out.println("Fetching & Deleting Blog with id " + id);
		Alias alias = aliasService.getAliasById(id);
		BlogKey key = new BlogKey(alias.getCategory(), alias.getCreatedby(), alias.getCreatedon());
		blogService.deleteBlogById(key);
		return new ResponseEntity<BlogVO>(HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody String handleException(Exception ex) {
		if (ex.getMessage().contains("UNIQUE KEY"))
			return "The submitted item already exists.";
		else
			System.out.println(this.getClass() + ": need handleException for: " + ex.getMessage());
		return ex.getMessage();
	}

}
