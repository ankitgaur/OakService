package com.oak.controllers;

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
import com.oak.entities.ForumPost;
import com.oak.entities.ForumPostKey;
import com.oak.entities.User;
import com.oak.service.AliasService;
import com.oak.service.CounterService;
import com.oak.service.ForumPostService;
import com.oak.service.ImageService;
import com.oak.service.UsersService;
import com.oak.vo.ForumPostVO;

@RestController
public class ForumPostController {

	@Autowired
	ForumPostService forumPostService;

	@Autowired
	CounterService counterService;

	@Autowired
	UsersService usersService;

	@Autowired
	AliasService aliasService;

	@Autowired
	ImageService imageService;

	@CrossOrigin
	@RequestMapping(value = "/forum_post/{id}", produces = "application/json", method = RequestMethod.GET)
	public ForumPostVO getForumPostById(@PathVariable String id)
			throws JsonProcessingException, ParseException {

		Alias alias = aliasService.getAliasById(id);

		ForumPostKey key = new ForumPostKey(alias.getCategory(),
				alias.getCreatedby(), alias.getCreatedon());

		ForumPost forumPost = forumPostService.getForumPostById(key);
		ForumPostVO forumPostVO = new ForumPostVO(forumPost);
		return forumPostVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/forum_post", produces = "application/json", method = RequestMethod.GET)
	public List<ForumPostVO> getForumPost() throws JsonProcessingException {
		List<ForumPost> forumPostList = forumPostService.getForumPosts();
		List<ForumPostVO> articleVO = new ArrayList<ForumPostVO>();
		for (ForumPost forumPost : forumPostList) {
			ForumPostVO vo = new ForumPostVO(forumPost);
			articleVO.add(vo);
		}
		return articleVO;

	}

	@CrossOrigin
	@RequestMapping(value = "/popular_forum_post/{category}/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<ForumPostVO> getPopularForumPostByCategory(
			@PathVariable("category") String category,
			@PathVariable("limit") int limit) throws JsonProcessingException {

		List<ForumPost> forumPostList = forumPostService
				.getPopularForumPostsByCategory(category, limit);
		List<ForumPostVO> forumPostVoList = new ArrayList<ForumPostVO>();
		for (ForumPost forumPost : forumPostList) {
			ForumPostVO forumPostVo = new ForumPostVO(forumPost);
			forumPostVoList.add(forumPostVo);
		}
		return forumPostVoList;
	}

	@CrossOrigin
	@RequestMapping(value = "/forum_post/{category}/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<ForumPostVO> getForumPostByCategory(
			@PathVariable("category") String category,
			@PathVariable("limit") int limit) throws JsonProcessingException {

		List<ForumPost> forumPostList = forumPostService
				.getTopForumPostsByCategory(category, limit);
		List<ForumPostVO> forumPostVoList = new ArrayList<ForumPostVO>();
		for (ForumPost forumPost : forumPostList) {
			ForumPostVO vo = new ForumPostVO(forumPost);
			forumPostVoList.add(vo);
		}
		return forumPostVoList;

	}

	@CrossOrigin
	@RequestMapping(value = "/forum_post/limit/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<ForumPostVO> getForumPostByLimit(
			@PathVariable("limit") int limit) throws JsonProcessingException {

		List<ForumPost> forumPostList = forumPostService
				.getTopForumPostsByLimit(limit);
		List<ForumPostVO> forumPostVoList = new ArrayList<ForumPostVO>();
		for (ForumPost article : forumPostList) {
			ForumPostVO forumPostVO = new ForumPostVO(article);
			forumPostVoList.add(forumPostVO);
		}
		return forumPostVoList;

	}

	@CrossOrigin
	@Secured("ROLE_forumpost_write")
	@RequestMapping(value = "/forum_post/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ForumPostVO> deleteRticle(
			@PathVariable("id") String id) {
		System.out.println("Fetching & Deleting User with id " + id);
		Alias alias = aliasService.getAliasById(id);

		ForumPostKey key = new ForumPostKey(alias.getCategory(),
				alias.getCreatedby(), alias.getCreatedon());
		forumPostService.deleteForumPostById(key);
		counterService.decrementCounter(alias.getCategory());
		return new ResponseEntity<ForumPostVO>(HttpStatus.NO_CONTENT);
	}

	@CrossOrigin
	@Secured("ROLE_forumpost_write")
	@RequestMapping(value = "/forum_post", method = RequestMethod.POST)
	public ResponseEntity<Void> createForumPost(
			@RequestParam("topic") String topic,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam(name = "displayImage", required = false) MultipartFile displayImage)
			throws ParseException {

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
		} catch (Exception npe) {
			// do nothing
		}
		ForumPostVO forumPostVO = new ForumPostVO();
		User user = usersService.getUserById(email);
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date dt = ft.parse(ft.format(dNow));
		forumPostVO.setCreatedOn(dt.getTime());
		forumPostVO.setUpdatedOn(dt.getTime());
		forumPostVO.setCreatedBy(email);
		forumPostVO.setAuthor(user.getUsername());
		forumPostVO.setTitle(title);
		forumPostVO.setTopic(topic);
		forumPostVO.setDisplayImage("http://www.ipledge2nigeria.com/service/" + id);
		forumPostVO.setContent(content);
		forumPostService.createForumPost(new ForumPost(forumPostVO));
		counterService.incrementCounter(forumPostVO.getTopic());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@CrossOrigin
	@Secured("ROLE_forumpost_write")
	@RequestMapping(value = "/forum_post/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<ForumPostVO> updateForumPost(
			@PathVariable("id") String postID,
			@RequestBody ForumPostVO forumPostVO) throws ParseException {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = authentication.getName();

		System.out.println("Updating User " + postID);
		Alias alias = aliasService.getAliasById(postID);

		ForumPostKey key = new ForumPostKey(alias.getCategory(),
				alias.getCreatedby(), alias.getCreatedon());
		ForumPost forumPost = forumPostService.getForumPostById(key);
		if (forumPost == null) {
			System.out.println("ForumPost with id " + postID + " not found");
			return new ResponseEntity<ForumPostVO>(forumPostVO,
					HttpStatus.BAD_REQUEST);
		}
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date dt = ft.parse(ft.format(dNow));
		forumPostVO.setCreatedOn(forumPost.getPk().getCreatedOn());
		forumPostVO.setUpdatedOn(dt.getTime());
		forumPostVO.setUpdatedBy(email);
		forumPostService.updateForumPost(new ForumPost(forumPostVO));
		return new ResponseEntity<ForumPostVO>(forumPostVO, HttpStatus.OK);

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
