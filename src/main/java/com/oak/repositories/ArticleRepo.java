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
	
	public List<Article> getArticles(){
		List<Article> articles = cassandraTemplate.select("Select * from articles", Article.class);
		return articles;
	}
	
	public List<Article> getArticleById(String id){
		List<Article> articles = cassandraTemplate.select("Select * from articles where id = "+id, Article.class);
		return articles;
	}

}
