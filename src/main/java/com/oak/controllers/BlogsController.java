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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oak.entities.Blog;
import com.oak.entities.BlogKey;
import com.oak.service.BlogService;
import com.oak.vo.BlogVO;

@RestController
public class BlogsController {

	@Autowired
	BlogService blogsService;

	@RequestMapping(value = "/blogs/{id}", produces = "application/json", method = RequestMethod.GET)
	public BlogVO getBlogByID(@PathVariable("id") String id)
			throws JsonProcessingException {
		String blogKey[] = id.split("_");
		BlogKey key = new BlogKey(blogKey[0], Long.parseLong(blogKey[1]));
		Blog blog = blogsService.getBlogsById(key);
		BlogVO blogVO = new BlogVO(blog);
		Date createDate = new Date(blog.getCreatedOn());
		Date updateDate = new Date(blog.getBlogKey().getUpdatedOn());
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"dd-MM-yyyy HH:mm:ss");
		String dateCreateText = dateFormatter.format(createDate);
		String updateCreateText = dateFormatter.format(updateDate);
		blogVO.setCreatedOnDate(dateCreateText);
		blogVO.setUpdatedOnDate(updateCreateText);
		return blogVO;

	}

	@RequestMapping(value = "/blogs", produces = "application/json", method = RequestMethod.GET)
	public List<BlogVO> getBlogs() throws JsonProcessingException {

		List<Blog> blogs = blogsService.getBlogs();

		List<BlogVO> blogVO = new ArrayList<BlogVO>();
		for (Blog article : blogs) {
			BlogVO vo = new BlogVO(article);
			Date createDate = new Date(article.getCreatedOn());
			Date updateDate = new Date(article.getBlogKey().getUpdatedOn());
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

	@RequestMapping(value = "/blogs/{category}/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<BlogVO> getBlogsByCategory(
			@PathVariable("category") String category,
			@PathVariable("limit") int limit) throws JsonProcessingException {

		List<Blog> blogs = blogsService.getTopBlogsByCategory(category, limit);

		List<BlogVO> blogVO = new ArrayList<BlogVO>();
		for (Blog article : blogs) {
			BlogVO vo = new BlogVO(article);
			Date createDate = new Date(article.getCreatedOn());
			Date updateDate = new Date(article.getBlogKey().getUpdatedOn());
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

	@RequestMapping(value = "/blogs/limit/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<BlogVO> getBlogsByLimit(@PathVariable("limit") int limit)
			throws JsonProcessingException {

		List<Blog> blogs = blogsService.getTopBlogsByLimit(limit);

		List<BlogVO> blogVO = new ArrayList<BlogVO>();
		for (Blog article : blogs) {
			BlogVO vo = new BlogVO(article);
			Date createDate = new Date(article.getCreatedOn());
			Date updateDate = new Date(article.getBlogKey().getUpdatedOn());
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

	@RequestMapping(value = "/blogs/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<BlogVO> deleteBlog(@PathVariable("id") String id) {
		System.out.println("Fetching & Deleting User with id " + id);
		String blogKey[] = id.split("_");
		BlogKey key = new BlogKey(blogKey[0], Long.parseLong(blogKey[1]));
		blogsService.deleteBlogsById(key);
		return new ResponseEntity<BlogVO>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/blogs", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createBlog(@RequestBody BlogVO blogVO)
			throws ParseException {

		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date dt = ft.parse(ft.format(dNow));
		blogVO.setCreatedOn(dt.getTime());
		blogVO.setUpdatedOn(dt.getTime());
		blogsService.createBlogs(new Blog(blogVO));
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/blogs/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<BlogVO> updateBlog(@PathVariable("id") String id,
			@RequestBody BlogVO blogVO) throws ParseException {

		System.out.println("Updating User " + id);
		String blogKey[] = id.split("_");
		BlogKey key = new BlogKey(blogKey[0], Long.parseLong(blogKey[1]));
		Blog currentBlog = blogsService.getBlogsById(key);
		if (currentBlog == null) {
			System.out.println("Blogs with id " + id + " not found");
		}
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date dt = ft.parse(ft.format(dNow));
		blogVO.setCreatedOn(currentBlog.getCreatedOn());
		blogVO.setUpdatedOn(dt.getTime());
		blogsService.updateBlog(new Blog(blogVO));
		return new ResponseEntity<BlogVO>(blogVO, HttpStatus.OK);

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