package com.oak.service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oak.comparators.ArticleComparator;
import com.oak.entities.Alias;
import com.oak.entities.Article;
import com.oak.entities.ArticleKey;
import com.oak.repositories.ArticleRepo;

@Service("articleService")
public class ArticleService {

	@Autowired
	ArticleRepo articleRepo;
	
	@Autowired
	AliasService aliasService;

	public List<Article> getArticles() {

		return articleRepo.getArticles();

	}

	public List<Article> getPopularArticlesByCategory(String category, int limit) {

		System.out.println("popular articles by limit");
		List<Article> articles = articleRepo.getTopArticlesByCategory(category, limit);
		Collections.sort(articles,new ArticleComparator());
		return articles;
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
		
		UUID uuid = UUID.randomUUID();
		Alias alias = new Alias();
		alias.setId(uuid.toString());
		alias.setCategory(article.getPk().getCategory());
		alias.setCreatedby(article.getPk().getCreatedBy());
		alias.setCreatedon(article.getPk().getCreatedOn());
		aliasService.createAlias(alias);
			
		article.setAlias(alias.getId());

		articleRepo.createArticle(article);

	}

	@Transactional
	public void updateArticle(Article article) {

		articleRepo.updateArticle(article);
	}

}
