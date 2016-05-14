package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.ForumCategory;
import com.oak.repositories.ForumCategoryRepo;

@Service("forumCategoryService")
public class ForumCategoryService {

	@Autowired
	ForumCategoryRepo forumCategoryRepo;

	public List<ForumCategory> getForumCategories() {
		return forumCategoryRepo.getForumCategories();
	}

	public ForumCategory getForumCategoryById(long id) {
		return forumCategoryRepo.getForumCategoryById(id);
	}

	public void createForumCategory(ForumCategory state) {

		forumCategoryRepo.createForumCategory(state);

	}

	public void updateForumCategory(ForumCategory state) {

		forumCategoryRepo.updateForumCategory(state);

	}

	public void deleteForumCategoryById(long id) {

		forumCategoryRepo.deleteForumCategoryById(id);

	}

}
