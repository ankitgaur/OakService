package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Article;
import com.oak.entities.ArticleKey;
import com.oak.utils.OakCassandraTemplate;

@Repository("articleRepo")
@Transactional
public class ArticleRepo {

	@Autowired
	private OakCassandraTemplate oakCassendraTemplate;

	public List<Article> getArticles() {
		List<Article> articles = oakCassendraTemplate.findAll(Article.class);
		return articles;
	}

	public Article getArticleById(ArticleKey id) {
		Article article = oakCassendraTemplate.findById(id, Article.class);
		return article;
	}

	public void deleteArticleById(ArticleKey id) {

		oakCassendraTemplate.deleteById(id, Article.class);

	}

	public void createArticle(Article article) {

		oakCassendraTemplate.create(article, Article.class);

	}

	public void updateArticle(Article article) {

		oakCassendraTemplate.update(article, Article.class);

	}

}
