package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.ArticleCategory;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("articleCategoryRepo")
public class ArticleCategoryRepo {

	@Autowired
	private OakCassandraTemplate oakCassandraTemplate;

	public List<ArticleCategory> getArticleCategories() {
		List<ArticleCategory> categories = oakCassandraTemplate.findAll(ArticleCategory.class);
		return categories;
	}

	public ArticleCategory getArticleCategoryById(long id) {
		ArticleCategory category = oakCassandraTemplate.findById(id, ArticleCategory.class);
		return category;
	}

	public void createArticleCategory(ArticleCategory category) {

		oakCassandraTemplate.create(category, ArticleCategory.class);

	}

	public void updateArticleCategory(ArticleCategory category) {

		oakCassandraTemplate.update(category, ArticleCategory.class);

	}

	public void deleteArticleCategoryById(long id) {

		oakCassandraTemplate.deleteById(id, ArticleCategory.class);

	}

}
