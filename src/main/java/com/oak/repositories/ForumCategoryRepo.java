package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.ForumCategory;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("forumCategoryRepo")
public class ForumCategoryRepo {

	@Autowired
	private OakCassandraTemplate oakCassandraTemplate;

	public List<ForumCategory> getForumCategories() {
		List<ForumCategory> categories = oakCassandraTemplate
				.findAll(ForumCategory.class);
		return categories;
	}

	public ForumCategory getForumCategoryById(long id) {
		ForumCategory category = oakCassandraTemplate.findById(id,
				ForumCategory.class);
		return category;
	}

	public void createForumCategory(ForumCategory category) {

		oakCassandraTemplate.create(category, ForumCategory.class);

	}

	public void updateForumCategory(ForumCategory category) {

		oakCassandraTemplate.update(category, ForumCategory.class);

	}

	public void deleteForumCategoryById(long id) {

		oakCassandraTemplate.deleteById(id, ForumCategory.class);

	}

}
