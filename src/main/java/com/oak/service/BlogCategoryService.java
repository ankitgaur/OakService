package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.BlogCategory;
import com.oak.repositories.BlogCategoryRepo;

@Service("blogCategoryService")
public class BlogCategoryService {

	@Autowired
	BlogCategoryRepo blogCategoryRepo;

	public List<BlogCategory> getBlogCategories() {
		return blogCategoryRepo.getBlogCategories();
	}

	public BlogCategory getBlogCategoryById(long id) {
		return blogCategoryRepo.getBlogCategoryById(id);
	}

	public void createBlogCategory(BlogCategory state) {

		blogCategoryRepo.createBlogCategory(state);

	}

	public void updateBlogCategory(BlogCategory state) {

		blogCategoryRepo.updateBlogCategory(state);

	}

	public void deleteBlogCategoryById(long id) {

		blogCategoryRepo.deleteBlogCategoryById(id);

	}

}
