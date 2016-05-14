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
import com.oak.entities.ForumTopics;
import com.oak.entities.ForumTopicsKey;
import com.oak.service.ForumTopicsService;
import com.oak.vo.ForumTopicsVO;

@RestController
public class ForumTopicsController {

	@Autowired
	ForumTopicsService forumTopicsService;

	@CrossOrigin
	@RequestMapping(value = "/forum_topics/{id}", produces = "application/json", method = RequestMethod.GET)
	public ForumTopicsVO getSticleById(@PathVariable String id)
			throws JsonProcessingException, ParseException {
		String articleKey[] = id.split("_");
		ForumTopicsKey key = new ForumTopicsKey(articleKey[0],
				Long.parseLong(articleKey[1]));
		ForumTopics forumTopics = forumTopicsService.getForumTopicsById(key);
		ForumTopicsVO forumTopicsVO = new ForumTopicsVO(forumTopics);
		Date createDate = new Date(forumTopics.getPk().getCreatedOn());
		Date updateDate = new Date(forumTopics.getUpdatedOn());
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"dd-MM-yyyy HH:mm:ss");
		String dateCreateText = dateFormatter.format(createDate);
		String updateCreateText = dateFormatter.format(updateDate);
		forumTopicsVO.setCreatedOnStr(dateCreateText);
		forumTopicsVO.setUpdatedOnStr(updateCreateText);
		return forumTopicsVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/forum_topics", produces = "application/json", method = RequestMethod.GET)
	public List<ForumTopicsVO> getForumTopics() throws JsonProcessingException {
		List<ForumTopics> forum_topics = forumTopicsService.getForumTopics();
		List<ForumTopicsVO> forumTopicsVO = new ArrayList<ForumTopicsVO>();
		for (ForumTopics article : forum_topics) {
			ForumTopicsVO vo = new ForumTopicsVO(article);
			Date createDate = new Date(article.getPk().getCreatedOn());
			Date updateDate = new Date(article.getUpdatedOn());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String dateCreateText = dateFormatter.format(createDate);
			String updateCreateText = dateFormatter.format(updateDate);
			vo.setCreatedOnStr(dateCreateText);
			vo.setUpdatedOnStr(updateCreateText);
			forumTopicsVO.add(vo);
		}
		return forumTopicsVO;

	}

	@CrossOrigin
	@RequestMapping(value = "/popular_forum_topics/{category}/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<ForumTopicsVO> getPopularForumTopicsByCategory(
			@PathVariable("category") String category,
			@PathVariable("limit") int limit) throws JsonProcessingException {

		List<ForumTopics> forum_topics = forumTopicsService
				.getPopularForumTopicsByCategory(category, limit);
		List<ForumTopicsVO> forumTopicsVoList = new ArrayList<ForumTopicsVO>();
		for (ForumTopics article : forum_topics) {
			ForumTopicsVO forumTopicsVo = new ForumTopicsVO(article);
			Date createDate = new Date(article.getPk().getCreatedOn());
			Date updateDate = new Date(article.getUpdatedOn());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String dateCreateText = dateFormatter.format(createDate);
			String updateCreateText = dateFormatter.format(updateDate);
			forumTopicsVo.setCreatedOnStr(dateCreateText);
			forumTopicsVo.setUpdatedOnStr(updateCreateText);
			forumTopicsVoList.add(forumTopicsVo);
		}
		return forumTopicsVoList;
	}

	@CrossOrigin
	@RequestMapping(value = "/forum_topics/{category}/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<ForumTopicsVO> getForumTopicsByCategory(
			@PathVariable("category") String category,
			@PathVariable("limit") int limit) throws JsonProcessingException {

		List<ForumTopics> forum_topics = forumTopicsService
				.getTopForumTopicsByCategory(category, limit);
		List<ForumTopicsVO> forumTopicsListVO = new ArrayList<ForumTopicsVO>();
		for (ForumTopics article : forum_topics) {
			ForumTopicsVO forumTopicsVO = new ForumTopicsVO(article);
			Date createDate = new Date(article.getPk().getCreatedOn());
			Date updateDate = new Date(article.getUpdatedOn());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String dateCreateText = dateFormatter.format(createDate);
			String updateCreateText = dateFormatter.format(updateDate);
			forumTopicsVO.setCreatedOnStr(dateCreateText);
			forumTopicsVO.setUpdatedOnStr(updateCreateText);
			forumTopicsListVO.add(forumTopicsVO);
		}
		return forumTopicsListVO;

	}

	@CrossOrigin
	@RequestMapping(value = "/forum_topics/limit/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<ForumTopicsVO> getForumTopicsByLimit(
			@PathVariable("limit") int limit) throws JsonProcessingException {

		List<ForumTopics> forum_topics = forumTopicsService
				.getTopForumTopicsByLimit(limit);
		List<ForumTopicsVO> articleVO = new ArrayList<ForumTopicsVO>();
		for (ForumTopics article : forum_topics) {
			ForumTopicsVO vo = new ForumTopicsVO(article);
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
	@RequestMapping(value = "/forum_topics/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ForumTopicsVO> deleteRticle(
			@PathVariable("id") String id) {
		System.out.println("Fetching & Deleting User with id " + id);
		String articleKey[] = id.split("_");
		ForumTopicsKey key = new ForumTopicsKey(articleKey[0],
				Long.parseLong(articleKey[1]));
		forumTopicsService.deleteForumTopicsById(key);
		return new ResponseEntity<ForumTopicsVO>(HttpStatus.NO_CONTENT);
	}

	@CrossOrigin
	@RequestMapping(value = "/forum_topics", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createForumTopics(
			@RequestBody ForumTopicsVO articleVO) throws ParseException {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date dt = ft.parse(ft.format(dNow));
		articleVO.setCreatedOn(dt.getTime());
		articleVO.setUpdatedOn(dt.getTime());
		forumTopicsService.createForumTopics(new ForumTopics(articleVO));
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(value = "/forum_topics/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<ForumTopicsVO> updateForumTopics(
			@PathVariable("id") String articleID,
			@RequestBody ForumTopicsVO articleVO) throws ParseException {

		System.out.println("Updating User " + articleID);
		String articleKey[] = articleID.split("_");
		ForumTopicsKey key = new ForumTopicsKey(articleKey[0],
				Long.parseLong(articleKey[1]));
		ForumTopics article = forumTopicsService.getForumTopicsById(key);
		if (article == null) {
			System.out.println("ForumTopics with id " + articleID
					+ " not found");
			return new ResponseEntity<ForumTopicsVO>(articleVO,
					HttpStatus.BAD_REQUEST);
		}
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date dt = ft.parse(ft.format(dNow));
		articleVO.setCreatedOn(article.getPk().getCreatedOn());
		articleVO.setUpdatedOn(dt.getTime());
		forumTopicsService.updateForumTopics(new ForumTopics(articleVO));
		return new ResponseEntity<ForumTopicsVO>(articleVO, HttpStatus.OK);

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
