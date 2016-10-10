package com.oak.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oak.entities.Alias;
import com.oak.entities.ForumTopics;
import com.oak.entities.ForumTopicsKey;
import com.oak.service.AliasService;
import com.oak.service.CounterService;
import com.oak.service.ForumTopicsService;
import com.oak.service.ImageService;
import com.oak.vo.ForumTopicsVO;

@RestController
public class ForumTopicsController {

	@Autowired
	ForumTopicsService forumTopicsService;

	@Autowired
	CounterService counterService;

	@Autowired
	AliasService aliasService;

	@Autowired
	ImageService imageService;

	@CrossOrigin
	@RequestMapping(value = "/forum_topics/{id}", produces = "application/json", method = RequestMethod.GET)
	public ForumTopicsVO getSticleById(@PathVariable String id)
			throws JsonProcessingException, ParseException {
		Alias alias = aliasService.getAliasById(id);

		ForumTopicsKey key = new ForumTopicsKey(alias.getCategory(),
				alias.getCreatedby(), alias.getCreatedon());
		ForumTopics forumTopics = forumTopicsService.getForumTopicsById(key);
		ForumTopicsVO forumTopicsVO = new ForumTopicsVO(forumTopics);

		return forumTopicsVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/forum_topics", produces = "application/json", method = RequestMethod.GET)
	public List<ForumTopicsVO> getForumTopics() throws JsonProcessingException {
		List<ForumTopics> forum_topics = forumTopicsService.getForumTopics();
		List<ForumTopicsVO> forumTopicsVO = new ArrayList<ForumTopicsVO>();
		for (ForumTopics article : forum_topics) {
			ForumTopicsVO vo = new ForumTopicsVO(article);
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
			long cnt = counterService.getCounterValue(forumTopicsVo.getId());
			forumTopicsVo.setPstCount(cnt);
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
			long cnt = counterService.getCounterValue(forumTopicsVO.getId());
			forumTopicsVO.setPstCount(cnt);
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
			articleVO.add(vo);
		}
		return articleVO;

	}

	@CrossOrigin
	@Secured("ROLE_forumtopics_write")
	@RequestMapping(value = "/forum_topics/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ForumTopicsVO> deleteRticle(
			@PathVariable("id") String id) {
		System.out.println("Fetching & Deleting User with id " + id);
		Alias alias = aliasService.getAliasById(id);

		ForumTopicsKey key = new ForumTopicsKey(alias.getCategory(),
				alias.getCreatedby(), alias.getCreatedon());
		forumTopicsService.deleteForumTopicsById(key);
		counterService.decrementCounter(key.getCategory());
		return new ResponseEntity<ForumTopicsVO>(HttpStatus.NO_CONTENT);
	}

	@CrossOrigin
	@Secured("ROLE_forumtopics_write")
	@RequestMapping(value = "/forum_topics", method = RequestMethod.POST)
	public ResponseEntity<Void> createForumTopics(
			@RequestParam("title") String title,
			@RequestParam("category") String category,
			@RequestParam(name = "displayImage", required = false) MultipartFile displayImage)
			throws ParseException {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = authentication.getName();

		ForumTopicsVO forumTopicsVO = new ForumTopicsVO();
		String id = null;
		try {
			byte[] imgbytes = displayImage.getBytes();
			if (imgbytes != null) {
				id = imageService.saveImage("blogimage",
						displayImage.getOriginalFilename(),
						displayImage.getSize(), displayImage.getBytes(), email);
			}
		} catch (IOException npe) {
			// do nothing
		}
		forumTopicsVO.setCategory(category);
		forumTopicsVO
				.setDisplayImage("http://www.ipledge2nigeria.com/service/image/" + id);
		forumTopicsVO.setTitle(title);

		Date dNow = new Date();

		forumTopicsVO.setCreatedOn(dNow.getTime());
		forumTopicsVO.setCreatedBy(email);
		forumTopicsService.createForumTopics(new ForumTopics(forumTopicsVO));
		counterService.incrementCounter(forumTopicsVO.getCategory());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@CrossOrigin
	@Secured("ROLE_forumtopics_write")
	@RequestMapping(value = "/forum_topics/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<ForumTopicsVO> updateForumTopics(
			@PathVariable("id") String articleID,
			@RequestBody ForumTopicsVO articleVO) throws ParseException {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = authentication.getName();

		System.out.println("Updating User " + articleID);
		Alias alias = aliasService.getAliasById(articleID);

		ForumTopicsKey key = new ForumTopicsKey(alias.getCategory(),
				alias.getCreatedby(), alias.getCreatedon());
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
		articleVO.setUpdatedBy(email);
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
