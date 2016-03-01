package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.BlogCategory;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("blogCategoryRepo")
public class BlogCategoryRepo {

	@Autowired
	private OakCassandraTemplate oakCassandraTemplate;

	public List<BlogCategory> getBlogCategories() {
		List<BlogCategory> categories = oakCassandraTemplate.findAll(BlogCategory.class);
		return categories;
	}

	public BlogCategory getBlogCategoryById(long id) {
		BlogCategory category = oakCassandraTemplate.findById(id, BlogCategory.class);
		return category;
	}

	public void createBlogCategory(BlogCategory category) {

		oakCassandraTemplate.create(category, BlogCategory.class);

	}

	public void updateBlogCategory(BlogCategory category) {

		oakCassandraTemplate.update(category, BlogCategory.class);

	}

	public void deleteBlogCategoryById(long id) {

		oakCassandraTemplate.deleteById(id, BlogCategory.class);

	}

}
