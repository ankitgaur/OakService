package com.oak.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.oak.entities.ForumPost;
import com.oak.entities.ForumPostKey;
import com.oak.service.ForumPostService;
import com.oak.vo.ForumPostVO;

@RestController
public class ForumPostController {

	@Autowired
	ForumPostService forumPostService;

	@CrossOrigin
	@RequestMapping(value = "/articles/{id}", produces = "application/json", method = RequestMethod.GET)
	public ForumPostVO getSticleById(@PathVariable String id)
			throws JsonProcessingException, ParseException {
		String articleKey[] = id.split("_");
		ForumPostKey key = new ForumPostKey(articleKey[0],
				Long.parseLong(articleKey[1]));
		ForumPost forumPost = forumPostService.getForumPostById(key);
		ForumPostVO articleVO = new ForumPostVO(forumPost);
		Date createDate = new Date(forumPost.getPk().getCreatedOn());
		Date updateDate = new Date(forumPost.getUpdatedOn());
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"dd-MM-yyyy HH:mm:ss");
		String dateCreateText = dateFormatter.format(createDate);
		String updateCreateText = dateFormatter.format(updateDate);
		articleVO.setCreatedOnStr(dateCreateText);
		articleVO.setUpdatedOnStr(updateCreateText);
		return articleVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/articles", produces = "application/json", method = RequestMethod.GET)
	public List<ForumPostVO> getForumPost() throws JsonProcessingException {
		List<ForumPost> articles = forumPostService.getForumPosts();
		List<ForumPostVO> articleVO = new ArrayList<ForumPostVO>();
		for (ForumPost article : articles) {
			ForumPostVO vo = new ForumPostVO(article);
			Date createDate = new Date(article.getPk().getCreatedOn());
			Date updateDate = new Date(article.getUpdatedOn());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String dateCreateText = dateFormatter.format(createDate);
			String updateCreateText = dateFormatter.format(updateDate);
			vo.setCreatedOnStr(dateCreateText);
			vo.setUpdatedOnStr(updateCreateText);
			articleVO.add(vo);
		}
		return articleVO;

	}

	@CrossOrigin
	@RequestMapping(value = "/popular_articles/{category}/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<ForumPostVO> getPopularForumPostByCategory(
			@PathVariable("category") String category,
			@PathVariable("limit") int limit) throws JsonProcessingException {

		List<ForumPost> articles = forumPostService
				.getPopularForumPostsByCategory(category, limit);
		List<ForumPostVO> articleVO = new ArrayList<ForumPostVO>();
		for (ForumPost article : articles) {
			ForumPostVO vo = new ForumPostVO(article);
			Date createDate = new Date(article.getPk().getCreatedOn());
			Date updateDate = new Date(article.getUpdatedOn());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String dateCreateText = dateFormatter.format(createDate);
			String updateCreateText = dateFormatter.format(updateDate);
			vo.setCreatedOnStr(dateCreateText);
			vo.setUpdatedOnStr(updateCreateText);
			articleVO.add(vo);
		}
		return articleVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/articles/{category}/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<ForumPostVO> getForumPostByCategory(
			@PathVariable("category") String category,
			@PathVariable("limit") int limit) throws JsonProcessingException {

		List<ForumPost> articles = forumPostService.getTopForumPostsByCategory(
				category, limit);
		List<ForumPostVO> articleVO = new ArrayList<ForumPostVO>();
		for (ForumPost article : articles) {
			ForumPostVO vo = new ForumPostVO(article);
			Date createDate = new Date(article.getPk().getCreatedOn());
			Date updateDate = new Date(article.getUpdatedOn());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String dateCreateText = dateFormatter.format(createDate);
			String updateCreateText = dateFormatter.format(updateDate);
			vo.setCreatedOnStr(dateCreateText);
			vo.setUpdatedOnStr(updateCreateText);
			articleVO.add(vo);
		}
		return articleVO;

	}

	@CrossOrigin
	@RequestMapping(value = "/articles/limit/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<ForumPostVO> getForumPostByLimit(
			@PathVariable("limit") int limit) throws JsonProcessingException {

		List<ForumPost> articles = forumPostService
				.getTopForumPostsByLimit(limit);
		List<ForumPostVO> articleVO = new ArrayList<ForumPostVO>();
		for (ForumPost article : articles) {
			ForumPostVO vo = new ForumPostVO(article);
			Date createDate = new Date(article.getPk().getCreatedOn());
			Date updateDate = new Date(article.getUpdatedOn());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String dateCreateText = dateFormatter.format(createDate);
			String updateCreateText = dateFormatter.format(updateDate);
			vo.setCreatedOnStr(dateCreateText);
			vo.setUpdatedOnStr(updateCreateText);
			articleVO.add(vo);
		}
		return articleVO;

	}

	@CrossOrigin
	@RequestMapping(value = "/articles/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ForumPostVO> deleteRticle(
			@PathVariable("id") String id) {
		System.out.println("Fetching & Deleting User with id " + id);
		String articleKey[] = id.split("_");
		ForumPostKey key = new ForumPostKey(articleKey[0],
				Long.parseLong(articleKey[1]));
		forumPostService.deleteForumPostById(key);
		return new ResponseEntity<ForumPostVO>(HttpStatus.NO_CONTENT);
	}

	@CrossOrigin
	@RequestMapping(value = "/articles", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createForumPost(
			@RequestBody ForumPostVO articleVO) throws ParseException {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date dt = ft.parse(ft.format(dNow));
		articleVO.setCreatedOn(dt.getTime());
		articleVO.setUpdatedOn(dt.getTime());
		forumPostService.createForumPost(new ForumPost(articleVO));
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(value = "/articles/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<ForumPostVO> updateForumPost(
			@PathVariable("id") String articleID,
			@RequestBody ForumPostVO articleVO) throws ParseException {

		System.out.println("Updating User " + articleID);
		String articleKey[] = articleID.split("_");
		ForumPostKey key = new ForumPostKey(articleKey[0],
				Long.parseLong(articleKey[1]));
		ForumPost article = forumPostService.getForumPostById(key);
		if (article == null) {
			System.out.println("ForumPost with id " + articleID + " not found");
			return new ResponseEntity<ForumPostVO>(articleVO,
					HttpStatus.BAD_REQUEST);
		}
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date dt = ft.parse(ft.format(dNow));
		articleVO.setCreatedOn(article.getPk().getCreatedOn());
		articleVO.setUpdatedOn(dt.getTime());
		forumPostService.updateForumPost(new ForumPost(articleVO));
		return new ResponseEntity<ForumPostVO>(articleVO, HttpStatus.OK);

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
