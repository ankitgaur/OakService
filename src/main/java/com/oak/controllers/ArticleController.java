package com.oak.controllers;

import java.io.IOException;
import java.text.ParseException;
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
import com.oak.entities.Article;
import com.oak.entities.ArticleKey;
import com.oak.entities.User;
import com.oak.service.AliasService;
import com.oak.service.ArticleService;
import com.oak.service.ImageService;
import com.oak.service.UsersService;
import com.oak.vo.ArticleVO;

@RestController
public class ArticleController {

	@Autowired
	ArticleService articleService;

	@Autowired
	UsersService usersService;

	@Autowired
	AliasService aliasService;

	@Autowired
	ImageService imageService;

	@CrossOrigin
	@RequestMapping(value = "/articles/{id}", produces = "application/json", method = RequestMethod.GET)
	public ArticleVO getSticleById(@PathVariable String id)
			throws JsonProcessingException, ParseException {
		Alias alias = aliasService.getAliasById(id);
		ArticleKey key = new ArticleKey(alias.getCategory(),
				alias.getCreatedby(), alias.getCreatedon());
		Article article = articleService.getArticleById(key);
		ArticleVO articleVO = new ArticleVO(article);
		return articleVO;
	}

	@CrossOrigin
	//@Secured({"ROLE_REGULAR_USER","ROLE_ADMIN"})
	@RequestMapping(value = "/articles", produces = "application/json", method = RequestMethod.GET)
	public List<ArticleVO> getArticle() throws JsonProcessingException {
		List<Article> articles = articleService.getArticles();
		List<ArticleVO> articleVO = new ArrayList<ArticleVO>();
		for (Article article : articles) {
			ArticleVO vo = new ArticleVO(article);
			articleVO.add(vo);
		}
		return articleVO;

	}

	@CrossOrigin
	@RequestMapping(value = "/popular_articles/{category}/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<ArticleVO> getPopularArticleByCategory(
			@PathVariable("category") String category,
			@PathVariable("limit") int limit) throws JsonProcessingException {

		List<Article> articles = articleService.getPopularArticlesByCategory(
				category, limit);
		List<ArticleVO> articleVO = new ArrayList<ArticleVO>();
		for (Article article : articles) {
			ArticleVO vo = new ArticleVO(article);
			articleVO.add(vo);
		}
		return articleVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/articles/{category}/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<ArticleVO> getArticleByCategory(
			@PathVariable("category") String category,
			@PathVariable("limit") int limit) throws JsonProcessingException {

		List<Article> articles = articleService.getTopArticlesByCategory(
				category, limit);
		List<ArticleVO> articleVO = new ArrayList<ArticleVO>();
		for (Article article : articles) {
			ArticleVO vo = new ArticleVO(article);
			articleVO.add(vo);
		}
		return articleVO;

	}

	@CrossOrigin
	@RequestMapping(value = "/articles/limit/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<ArticleVO> getArticleByLimit(@PathVariable("limit") int limit)
			throws JsonProcessingException {

		List<Article> articles = articleService.getTopArticlesByLimit(limit);
		List<ArticleVO> articleVO = new ArrayList<ArticleVO>();
		for (Article article : articles) {
			ArticleVO vo = new ArticleVO(article);
			articleVO.add(vo);
		}
		return articleVO;

	}

	@CrossOrigin
	@Secured("ROLE_article_write")
	@RequestMapping(value = "/articles/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ArticleVO> deleteRticle(@PathVariable("id") String id) {
		System.out.println("Fetching & Deleting User with id " + id);
		Alias alias = aliasService.getAliasById(id);
		ArticleKey key = new ArticleKey(alias.getCategory(),
				alias.getCreatedby(), alias.getCreatedon());
		articleService.deleteArticleById(key);
		return new ResponseEntity<ArticleVO>(HttpStatus.NO_CONTENT);
	}

	@CrossOrigin
	@Secured("ROLE_article_write")
	@RequestMapping(value = "/articles", method = RequestMethod.POST)
	public ResponseEntity<Void> createArticle(
			@RequestParam("category") String category,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("intro") String intro,
			@RequestParam(name = "displayImage", required = false) MultipartFile displayImage)
			throws ParseException, IOException {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = authentication.getName();
		User user = usersService.getUserById(email);

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

		Date dt = new Date();
		ArticleVO articleVO = new ArticleVO();
		articleVO.setCreatedOn(dt.getTime());
		articleVO.setCreatedBy(email);
		articleVO.setAuthor(user.getUsername());
		articleVO.setIntro(intro);
		articleVO.setTitle(title);
		articleVO.setDisplayImage("http://www.ipledge2nigeria.com/service/image/" + id);
		articleVO.setCategory(category);
		articleVO.setContent(content);

		articleService.createArticle(new Article(articleVO));
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@CrossOrigin
	@Secured("ROLE_article_write")
	@RequestMapping(value = "/articles/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<ArticleVO> updateArticle(
			@PathVariable("id") String articleID,
			@RequestBody ArticleVO articleVO) throws ParseException {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = authentication.getName();

		Alias alias = aliasService.getAliasById(articleID);
		ArticleKey key = new ArticleKey(alias.getCategory(),
				alias.getCreatedby(), alias.getCreatedon());
		Article article = articleService.getArticleById(key);
		if (article == null) {
			System.out.println("Article with id " + articleID + " not found");
			return new ResponseEntity<ArticleVO>(articleVO,
					HttpStatus.BAD_REQUEST);
		}
		Date dt = new Date();
		articleVO.setUpdatedOn(dt.getTime());
		articleVO.setUpdatedBy(email);
		articleService.updateArticle(new Article(articleVO));
		return new ResponseEntity<ArticleVO>(articleVO, HttpStatus.OK);

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
