package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import com.oak.entities.Article;

@Repository("articleRepo")
public class ArticleRepo {

	@Autowired
	private CassandraOperations cassandraTemplate;

	public List<Article> getArticles() {
		List<Article> articles = cassandraTemplate.select(
				"Select * from articles", Article.class);
		return articles;
	}

	public Article getArticleById(long id) {
		Article article = cassandraTemplate.selectOneById(Article.class, id);
		return article;
	}

	public void deleteArticleById(long id) {

		cassandraTemplate.deleteById(Article.class, id);

	}

	public void saveArticle(Article article) {

		cassandraTemplate.insert(article);

	}

	public void updateArticle(Article article) {

		cassandraTemplate.update(article);

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
