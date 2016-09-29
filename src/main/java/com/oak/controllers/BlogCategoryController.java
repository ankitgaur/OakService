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

import com.oak.entities.BlogCategory;
import com.oak.service.BlogCategoryService;
import com.oak.service.ImageService;
import com.oak.vo.BlogCategoryVO;

@RestController
public class BlogCategoryController {

	@Autowired
	BlogCategoryService blogCategoryService;

	@Autowired
	ImageService imageService;

	@CrossOrigin
	@RequestMapping(value = "/blog_categories", produces = "application/json", method = RequestMethod.GET)
	public List<BlogCategoryVO> getAllBlogCategories()
			throws JsonParseException, JsonMappingException, IOException {
		List<BlogCategory> categories = blogCategoryService.getBlogCategories();
		if (categories == null || categories.isEmpty()) {
			return null;
		}

		List<BlogCategoryVO> categoriesVO = new ArrayList<BlogCategoryVO>();

		for (BlogCategory category : categories) {
			categoriesVO.add(new BlogCategoryVO(category));
		}

		return categoriesVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/blog_categories/{id}", produces = "application/json", method = RequestMethod.GET)
	public BlogCategoryVO getBlogCategoryById(@PathVariable("id") long id)
			throws JsonParseException, JsonMappingException, IOException {
		BlogCategory category = blogCategoryService.getBlogCategoryById(id);
		if (category == null) {
			return null;
		}
		return new BlogCategoryVO(category);
	}

	@CrossOrigin
	@RequestMapping(value = "/blog_categories", method = RequestMethod.POST)
	public ResponseEntity<Void> createBlogCategory(
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam(name = "displayImage", required = false) MultipartFile displayImage)
			throws JsonParseException, JsonMappingException, IOException {

		System.out.println(name);
		System.out.println(description);
		System.out.println(displayImage);
		System.out.println();
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = authentication.getName();
		String id = null;
		try {
			byte[] imgbytes = displayImage.getBytes();
			if (imgbytes != null) {
				id = imageService.saveImage("blogimage",
						displayImage.getOriginalFilename(),
						displayImage.getSize(), displayImage.getBytes(), email);
			}
		} catch (NullPointerException npe) {
			// do nothing
		}
		BlogCategoryVO categoryVO = new BlogCategoryVO();
		categoryVO.setId(new Date().getTime());
		categoryVO.setCreatedby(email);
		categoryVO.setCreatedon(new Date().getTime());
		categoryVO.setName(name);
		categoryVO.setDescription(description);
		categoryVO.setDisplayimage("http://dev.insodel.com:6767/image/" + id);

		blogCategoryService.createBlogCategory(new BlogCategory(categoryVO));
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(value = "/blog_categories/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<BlogCategoryVO> updateBlogCategory(
			@PathVariable("id") long id, @RequestBody BlogCategoryVO categoryVO)
			throws JsonGenerationException, JsonMappingException, IOException {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = authentication.getName();

		categoryVO.setUpdatedby(email);
		categoryVO.setUpdatedon(new Date().getTime());
		blogCategoryService.updateBlogCategory(new BlogCategory(categoryVO));
		return new ResponseEntity<BlogCategoryVO>(categoryVO, HttpStatus.OK);

	}

	@CrossOrigin
	@RequestMapping(value = "/blog_categories/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<BlogCategoryVO> deleteBlogCategory(
			@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Blog Category with id " + id);

		BlogCategory category = blogCategoryService.getBlogCategoryById(id);
		if (category == null) {
			System.out.println("Unable to delete. Blog Category with id " + id
					+ " not found");
			return new ResponseEntity<BlogCategoryVO>(HttpStatus.NOT_FOUND);
		}
		blogCategoryService.deleteBlogCategoryById(id);
		return new ResponseEntity<BlogCategoryVO>(HttpStatus.NO_CONTENT);
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
