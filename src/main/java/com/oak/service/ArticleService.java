package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Article;
import com.oak.entities.ArticleKey;
import com.oak.repositories.ArticleRepo;

@Service("articleService")
public class ArticleService {

	@Autowired
	ArticleRepo articleRepo;

	public List<Article> getArticles() {

		return articleRepo.getArticles();

	}

	public List<Article> getTopArticlesByCategory(String category, int limit) {

		System.out.println("articles by limit");
		return articleRepo.getTopArticlesByCategory(category, limit);

	}

	public List<Article> getTopArticlesByLimit(int limit) {

		return articleRepo.getTopArticlesByLimit(limit);

	}

	public Article getArticleById(ArticleKey articleID) {

		return articleRepo.getArticleById(articleID);
	}

	public void deleteArticleById(ArticleKey articleID) {

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

}
