package com.oak.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oak.dto.Response;
import com.oak.entities.Article;
import com.oak.repositories.ArticleRepo;

@RestController
public class ArticleController {

	@Autowired
	ArticleRepo articleRepo;

	@RequestMapping(value = "/articles/{id}", produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody
	String getBasicJob(@PathVariable Long id) throws JsonProcessingException {

		Article article = articleRepo.getArticleById(id);

		Response response = new Response();
		response.setStatuscode("" + id);
		response.setStatusmsg("received " + id);
		response.setResult(article);
		return response.toString();
	}

	@RequestMapping(value = "/articles", produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody
	String getArticle() throws JsonProcessingException {
		List<Article> articles = articleRepo.getArticles();
		Response response = new Response();
		response.setResult(articles);
		return response.toString();

	}

	@RequestMapping(value = "/articles/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Article> deleteBlog(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting User with id " + id);

		Article article = articleRepo.getArticleById(id);
		if (article == null) {
			System.out.println("Unable to delete. User with id " + id
					+ " not found");
			return new ResponseEntity<Article>(HttpStatus.NOT_FOUND);
		}

		articleRepo.deleteArticleById(id);

		return new ResponseEntity<Article>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/articles", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createBlog(@RequestBody Article article,
			UriComponentsBuilder ucBuilder) {

		if (articleRepo.isArticleExist(article)) {
			System.out.println("A User with name " + article.getId()
					+ " already exist");
		}
		articleRepo.saveArticle(article);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/Article/{id}")
				.buildAndExpand(article.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/articles/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<Article> updateBlog(@PathVariable("id") long id,
			@RequestBody Article article) {

		System.out.println("Updating User " + id);
		Article currentArticle = articleRepo.getArticleById(id);
		if (currentArticle == null) {
			System.out.println("Article with id " + id + " not found");
		}
		currentArticle.setTitle(article.getTitle());
		currentArticle.setContent(article.getContent());
		currentArticle.setCreatedBy(article.getCreatedBy());
		articleRepo.updateArticle(currentArticle);
		return new ResponseEntity<Article>(currentArticle, HttpStatus.OK);

	}

}
