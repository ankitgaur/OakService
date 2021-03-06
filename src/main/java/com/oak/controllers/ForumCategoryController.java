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

import com.oak.entities.ForumCategory;
import com.oak.service.CounterService;
import com.oak.service.ForumCategoryService;
import com.oak.service.ImageService;
import com.oak.vo.ForumCategoryVO;

@RestController
public class ForumCategoryController {

	@Autowired
	ForumCategoryService forumCategoryService;

	@Autowired
	CounterService counterService;

	@Autowired
	ImageService imageService;

	@CrossOrigin
	@RequestMapping(value = "/forum_categories", produces = "application/json", method = RequestMethod.GET)
	public List<ForumCategoryVO> getAllForumCategories()
			throws JsonParseException, JsonMappingException, IOException {
		List<ForumCategory> categories = forumCategoryService
				.getForumCategories();
		if (categories == null || categories.isEmpty()) {
			return null;
		}

		List<ForumCategoryVO> categoriesVO = new ArrayList<ForumCategoryVO>();

		for (ForumCategory category : categories) {
			long cnt = counterService.getCounterValue(category.getName());
			ForumCategoryVO vo = new ForumCategoryVO(category);
			vo.setTopicCount(cnt);
			categoriesVO.add(vo);
		}

		return categoriesVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/forum_categories/{id}", produces = "application/json", method = RequestMethod.GET)
	public ForumCategoryVO getForumCategoryById(@PathVariable("id") long id)
			throws JsonParseException, JsonMappingException, IOException {
		ForumCategory category = forumCategoryService.getForumCategoryById(id);
		if (category == null) {
			return null;
		}
		return new ForumCategoryVO(category);
	}

	@CrossOrigin
	@Secured("ROLE_forumcategory_write")
	@RequestMapping(value = "/forum_categories", method = RequestMethod.POST)
	public ResponseEntity<Void> createForumCategory(
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam(name = "displayImage", required = false) MultipartFile displayImage)
			throws JsonParseException, JsonMappingException, IOException {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = authentication.getName();

		ForumCategoryVO categoryVO = new ForumCategoryVO();

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
		categoryVO.setName(name);
		categoryVO.setDisplayimage("http://www.ipledge2nigeria.com/service/image/" + id);
		categoryVO.setDescription(description);
		categoryVO.setId(new Date().getTime());
		// TODO : Change to the name of logged in User
		categoryVO.setCreatedby("test");
		categoryVO.setCreatedon(new Date().getTime());
		categoryVO.setCreatedby(email);
		forumCategoryService.createForumCategory(new ForumCategory(categoryVO));
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@CrossOrigin
	@Secured("ROLE_forumcategory_write")
	@RequestMapping(value = "/forum_categories/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<ForumCategoryVO> updateForumCategory(
			@PathVariable("id") long id, @RequestBody ForumCategoryVO categoryVO)
			throws JsonGenerationException, JsonMappingException, IOException {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = authentication.getName();
		// TODO : Change to the name of logged in User
		categoryVO.setUpdatedby("test");
		categoryVO.setUpdatedon(new Date().getTime());
		categoryVO.setUpdatedby(email);
		forumCategoryService.updateForumCategory(new ForumCategory(categoryVO));
		return new ResponseEntity<ForumCategoryVO>(categoryVO, HttpStatus.OK);

	}

	@CrossOrigin
	@Secured("ROLE_forumcategory_write")
	@RequestMapping(value = "/forum_categories/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ForumCategoryVO> deleteForumCategory(
			@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Forum Category with id " + id);

		ForumCategory category = forumCategoryService.getForumCategoryById(id);
		if (category == null) {
			System.out.println("Unable to delete. Forum Category with id " + id
					+ " not found");
			return new ResponseEntity<ForumCategoryVO>(HttpStatus.NOT_FOUND);
		}
		forumCategoryService.deleteForumCategoryById(id);
		return new ResponseEntity<ForumCategoryVO>(HttpStatus.NO_CONTENT);
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
