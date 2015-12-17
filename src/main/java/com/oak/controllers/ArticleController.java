package com.oak.controllers;

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
import com.oak.service.ArticleService;

@RestController
public class ArticleController {

	@Autowired
	ArticleService articleService;

	@RequestMapping(value = "/articles/{id}", produces = "application/json", method = RequestMethod.GET)
	public Article getBasicJob(@PathVariable Long id)
			throws JsonProcessingException {

		Article article = articleService.getArticleById(id);

		return article;
	}

	@RequestMapping(value = "/articles", produces = "application/json", method = RequestMethod.GET)
	public List<Article> getArticle() throws JsonProcessingException {
		List<Article> articles = articleService.getArticles();
		return articles;

	}

	@RequestMapping(value = "/articles/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Article> deleteBlog(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting User with id " + id);

		Article article = articleService.getArticleById(id);
		if (article == null) {
			System.out.println("Unable to delete. User with id " + id
					+ " not found");
			return new ResponseEntity<Article>(HttpStatus.NOT_FOUND);
		}

		articleService.deleteArticleById(id);

		return new ResponseEntity<Article>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/articles", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createBlog(@RequestBody Article article) {

		if (articleService.isArticleExist(article)) {
			System.out.println("A User with name " + article.getId()
					+ " already exist");
		}
		articleService.createArticle(article);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/articles/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<Article> updateBlog(@PathVariable("id") long id,
			@RequestBody Article article) {

		System.out.println("Updating User " + id);
		Article currentArticle = articleService.getArticleById(id);
		if (currentArticle == null) {
			System.out.println("Article with id " + id + " not found");
		}
		currentArticle.setTitle(article.getTitle());
		currentArticle.setContent(article.getContent());
		currentArticle.setCreatedBy(article.getCreatedBy());
		articleService.updateArticle(currentArticle);
		return new ResponseEntity<Article>(currentArticle, HttpStatus.OK);

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
