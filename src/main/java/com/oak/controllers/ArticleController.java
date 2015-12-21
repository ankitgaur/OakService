package com.oak.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.oak.entities.Article;
import com.oak.entities.ArticleKey;
import com.oak.service.ArticleService;
import com.oak.vo.ArticleVO;

@RestController
public class ArticleController {

	@Autowired
	ArticleService articleService;

	@RequestMapping(value = "/articles/{id}", produces = "application/json", method = RequestMethod.GET)
	public ArticleVO getBasicJob(@PathVariable String id)
			throws JsonProcessingException {

		String articleKey[] = id.split("_");
		ArticleKey key = new ArticleKey();
		key.setCategory(articleKey[0]);
		Date date = new Date(Long.parseLong(articleKey[1]));
		key.setUpdatedOn(date);
		Article article = articleService.getArticleById(key);
		return new ArticleVO(article);
	}

	@RequestMapping(value = "/articles", produces = "application/json", method = RequestMethod.GET)
	public List<ArticleVO> getArticle() throws JsonProcessingException {
		List<Article> articles = articleService.getArticles();

		List<ArticleVO> articleVO = new ArrayList<ArticleVO>();
		for (Article article : articles) {
			articleVO.add(new ArticleVO(article));
		}
		return articleVO;

	}

	@RequestMapping(value = "/articles/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ArticleVO> deleteBlog(@PathVariable("id") String id) {
		System.out.println("Fetching & Deleting User with id " + id);

		String articleKey[] = id.split("_");
		ArticleKey key = new ArticleKey();
		key.setCategory(articleKey[0]);
		Date date = new Date(Long.parseLong(articleKey[1]));
		key.setUpdatedOn(date);
		articleService.deleteArticleById(key);

		return new ResponseEntity<ArticleVO>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/articles", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createArticle(@RequestBody ArticleVO articleVO) {

		articleVO.setCreatedOn(new Date());
		articleVO.setUpdatedOn(new Date());
		articleService.createArticle(new Article(articleVO));
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/articles/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<ArticleVO> updateArticle(
			@PathVariable("id") String id, @RequestBody ArticleVO articleVO) {

		System.out.println("Updating User " + id);
		String articleKey[] = id.split("_");
		ArticleKey key = new ArticleKey();
		key.setCategory(articleKey[0]);
		Date date = new Date(Long.parseLong(articleKey[1]));
		key.setUpdatedOn(date);
		Article article = articleService.getArticleById(key);
		if (article == null) {
			System.out.println("Article with id " + id + " not found");
			return new ResponseEntity<ArticleVO>(articleVO,
					HttpStatus.BAD_REQUEST);
		}
		article.setTitle(articleVO.getTitle());
		article.setContent(articleVO.getContent());
		article.setCreatedBy(articleVO.getCreatedBy());
		article.setUpdatedBy(articleVO.getUpdatedBy());
		articleService.updateArticle(article);
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
