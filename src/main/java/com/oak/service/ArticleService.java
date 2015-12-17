package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Article;
import com.oak.repositories.ArticleRepo;

@Service("articleService")
public class ArticleService {

	@Autowired
	ArticleRepo articleRepo;

	public List<Article> getArticles() {

		return articleRepo.getArticles();

	}

	public Article getArticleById(long articleID) {

		return articleRepo.getArticleById(articleID);
	}

	public void deleteArticleById(long articleID) {

		articleRepo.deleteArticleById(articleID);
	}

	@Transactional
	public void createArticle(Article article) {

		articleRepo.createArticle(article);

	}

	@Transactional
	public void updateArticle(Article article) {

		articleRepo.updateArticle(article);
	}

	public boolean isArticleExist(Article article) {

		List<Article> articles = getArticles();

		for (Article articleTmp : articles) {
			if (articleTmp.getId() == article.getId()) {
				return true;
			}
		}
		return false;
	}

}
