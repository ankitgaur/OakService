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

import com.oak.entities.ArticleCategory;
import com.oak.service.ArticleCategoryService;
import com.oak.service.ImageService;
import com.oak.vo.ArticleCategoryVO;

@RestController
public class ArticleCategoryController {

	@Autowired
	ArticleCategoryService articleCategoryService;

	@Autowired
	ImageService imageService;

	@CrossOrigin
	@RequestMapping(value = "/article_categories", produces = "application/json", method = RequestMethod.GET)
	public List<ArticleCategoryVO> getAllArticleCategories()
			throws JsonParseException, JsonMappingException, IOException {
		List<ArticleCategory> categories = articleCategoryService
				.getArticleCategories();
		if (categories == null || categories.isEmpty()) {
			return null;
		}

		List<ArticleCategoryVO> categoriesVO = new ArrayList<ArticleCategoryVO>();

		for (ArticleCategory category : categories) {
			categoriesVO.add(new ArticleCategoryVO(category));
		}

		return categoriesVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/article_categories/{id}", produces = "application/json", method = RequestMethod.GET)
	public ArticleCategoryVO getArticleCategoryById(@PathVariable("id") long id)
			throws JsonParseException, JsonMappingException, IOException {
		ArticleCategory category = articleCategoryService
				.getArticleCategoryById(id);
		if (category == null) {
			return null;
		}
		return new ArticleCategoryVO(category);
	}

	@CrossOrigin
	@Secured("ROLE_articlecategory_write")
	@RequestMapping(value = "/article_categories", method = RequestMethod.POST)
	public ResponseEntity<Void> createArticleCategory(
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam(name = "displayImage", required = false) MultipartFile displayImage)
			throws JsonParseException, JsonMappingException, IOException {

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
		ArticleCategoryVO categoryVO = new ArticleCategoryVO();
		categoryVO.setId(new Date().getTime());
		categoryVO.setCreatedby(email);
		categoryVO.setCreatedon(new Date().getTime());
		categoryVO.setName(name);
		categoryVO.setDescription(description);
		categoryVO.setDisplayimage("http://www.ipledge2nigeria.com/service/image/" + id);
		articleCategoryService.createArticleCategory(new ArticleCategory(
				categoryVO));
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@CrossOrigin
	@Secured("ROLE_articlecategory_write")
	@RequestMapping(value = "/article_categories/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<ArticleCategoryVO> updateArticleCategory(
			@PathVariable("id") long id,
			@RequestBody ArticleCategoryVO categoryVO)
			throws JsonGenerationException, JsonMappingException, IOException {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = authentication.getName();

		categoryVO.setUpdatedby(email);
		categoryVO.setUpdatedon(new Date().getTime());
		articleCategoryService.updateArticleCategory(new ArticleCategory(
				categoryVO));
		return new ResponseEntity<ArticleCategoryVO>(categoryVO, HttpStatus.OK);

	}

	@CrossOrigin
	@Secured("ROLE_articlecategory_write")
	@RequestMapping(value = "/article_categories/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ArticleCategoryVO> deleteArticleCategory(
			@PathVariable("id") long id) {
		System.out
				.println("Fetching & Deleting Article Category with id " + id);

		ArticleCategory category = articleCategoryService
				.getArticleCategoryById(id);
		if (category == null) {
			System.out.println("Unable to delete. Article Category with id "
					+ id + " not found");
			return new ResponseEntity<ArticleCategoryVO>(HttpStatus.NOT_FOUND);
		}
		articleCategoryService.deleteArticleCategoryById(id);
		return new ResponseEntity<ArticleCategoryVO>(HttpStatus.NO_CONTENT);
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
