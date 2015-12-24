package com.oak.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public ArticleVO getSticleById(@PathVariable String id)
			throws JsonProcessingException, ParseException {
		String articleKey[] = id.split("_");
		ArticleKey key = new ArticleKey();
		key.setCategory(articleKey[0]);
		key.setUpdatedOn(Long.parseLong(articleKey[1]));
		Article article = articleService.getArticleById(key);
		ArticleVO articleVO = new ArticleVO(article);
		Date createDate = new Date(article.getCreatedOn());
		Date updateDate = new Date(article.getPk().getUpdatedOn());
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"dd-MM-yyyy HH:mm:ss");
		String dateCreateText = dateFormatter.format(createDate);
		String updateCreateText = dateFormatter.format(updateDate);
		articleVO.setCreatedOnDate(dateCreateText);
		articleVO.setUpdatedOnDate(updateCreateText);
		return articleVO;
	}

	@RequestMapping(value = "/articles", produces = "application/json", method = RequestMethod.GET)
	public List<ArticleVO> getArticle() throws JsonProcessingException {
		List<Article> articles = articleService.getArticles();
		List<ArticleVO> articleVO = new ArrayList<ArticleVO>();
		for (Article article : articles) {
			ArticleVO vo = new ArticleVO(article);
			Date createDate = new Date(article.getCreatedOn());
			Date updateDate = new Date(article.getPk().getUpdatedOn());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String dateCreateText = dateFormatter.format(createDate);
			String updateCreateText = dateFormatter.format(updateDate);
			vo.setCreatedOnDate(dateCreateText);
			vo.setUpdatedOnDate(updateCreateText);
			articleVO.add(vo);
		}
		return articleVO;

	}

	@RequestMapping(value = "/articles/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ArticleVO> deleteRticle(@PathVariable("id") String id) {
		System.out.println("Fetching & Deleting User with id " + id);
		String articleKey[] = id.split("_");
		ArticleKey key = new ArticleKey();
		key.setCategory(articleKey[0]);
		key.setUpdatedOn(Long.parseLong(articleKey[1]));
		articleService.deleteArticleById(key);
		return new ResponseEntity<ArticleVO>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/articles", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createArticle(@RequestBody ArticleVO articleVO)
			throws ParseException {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date dt = ft.parse(ft.format(dNow));
		articleVO.setCreatedOn(dt.getTime());
		articleVO.setUpdatedOn(dt.getTime());
		articleService.createArticle(new Article(articleVO));
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/articles/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<ArticleVO> updateArticle(
			@PathVariable("id") String id, @RequestBody ArticleVO articleVO)
			throws ParseException {

		System.out.println("Updating User " + id);
		String articleKey[] = id.split("_");
		ArticleKey key = new ArticleKey();
		key.setCategory(articleKey[0]);
		key.setUpdatedOn(Long.parseLong(articleKey[1]));
		Article article = articleService.getArticleById(key);
		if (article == null) {
			System.out.println("Article with id " + id + " not found");
			return new ResponseEntity<ArticleVO>(articleVO,
					HttpStatus.BAD_REQUEST);
		}
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date dt = ft.parse(ft.format(dNow));
		articleVO.setCreatedOn(article.getCreatedOn());
		System.out.println("article.getCreatedOn() " + article.getCreatedOn());
		System.out.println("dt.getTime() " + dt.getTime());
		articleVO.setUpdatedOn(dt.getTime());
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
