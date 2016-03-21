package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.ArticleCategory;
import com.oak.repositories.ArticleCategoryRepo;

@Service("articleCategoryService")
public class ArticleCategoryService {

	@Autowired
	ArticleCategoryRepo articleCategoryRepo;

	public List<ArticleCategory> getArticleCategories() {
		return articleCategoryRepo.getArticleCategories();
	}

	public ArticleCategory getArticleCategoryById(long id) {
		return articleCategoryRepo.getArticleCategoryById(id);
	}

	public void createArticleCategory(ArticleCategory state) {

		articleCategoryRepo.createArticleCategory(state);

	}

	public void updateArticleCategory(ArticleCategory state) {

		articleCategoryRepo.updateArticleCategory(state);

	}

	public void deleteArticleCategoryById(long id) {

		articleCategoryRepo.deleteArticleCategoryById(id);

	}

}
