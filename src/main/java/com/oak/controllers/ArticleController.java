package com.oak.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oak.dto.Response;
import com.oak.entities.Article;
import com.oak.repositories.ArticleRepo;

@RestController
public class ArticleController {

	@Autowired
	ArticleRepo articleRepo;

	@RequestMapping(value = "/article/{id}", produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody
	String getBasicJob(@PathVariable Long id) throws JsonProcessingException {

		List<Article> articles = articleRepo.getArticleById(id.toString());

		Response response = new Response();
		response.setStatuscode("" + id);
		response.setStatusmsg("received " + id);
		response.setResult(articles);

		return response.toString();
	}

}
