package com.oak.service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oak.comparators.ForumPostComparator;
import com.oak.entities.Alias;
import com.oak.entities.ForumPost;
import com.oak.entities.ForumPostKey;
import com.oak.repositories.ForumPostRepo;

@Service("forumPostService")
public class ForumPostService {

	@Autowired
	ForumPostRepo forumPostRepo;
	
	@Autowired
	AliasService aliasService;

	public List<ForumPost> getForumPosts() {

		return forumPostRepo.getForumPost();

	}

	public List<ForumPost> getPopularForumPostsByCategory(String category,
			int limit) {

		System.out.println("popular articles by limit");
		List<ForumPost> forumPosts = forumPostRepo.getTopForumPostByCategory(
				category, limit);
		Collections.sort(forumPosts, new ForumPostComparator());
		return forumPosts;
	}

	public List<ForumPost> getTopForumPostsByCategory(String category, int limit) {

		System.out.println("articles by limit");
		return forumPostRepo.getTopForumPostByCategory(category, limit);

	}

	public List<ForumPost> getTopForumPostsByLimit(int limit) {

		return forumPostRepo.getTopForumPostByLimit(limit);

	}

	public ForumPost getForumPostById(ForumPostKey articleID) {

		return forumPostRepo.getForumPostById(articleID);
	}

	public void deleteForumPostById(ForumPostKey articleID) {

		forumPostRepo.deleteForumPostById(articleID);
	}

	@Transactional
	public void createForumPost(ForumPost article) {

		UUID uuid = UUID.randomUUID();
		Alias alias = new Alias();
		alias.setId(uuid.toString());
		alias.setCategory(article.getPk().getTopic());
		alias.setCreatedby(article.getPk().getCreatedBy());
		alias.setCreatedon(article.getPk().getCreatedOn());
		aliasService.createAlias(alias);
			
		article.setAlias(alias.getId());
		forumPostRepo.createForumPost(article);

	}

	@Transactional
	public void updateForumPost(ForumPost article) {

		forumPostRepo.updateForumPost(article);
	}

}
