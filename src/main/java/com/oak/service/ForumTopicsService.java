package com.oak.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oak.comparators.ForumTopicsComparator;
import com.oak.entities.ForumTopics;
import com.oak.entities.ForumTopicsKey;
import com.oak.repositories.ForumTopicsRepo;

@Service("forumTopicsService")
public class ForumTopicsService {

	@Autowired
	ForumTopicsRepo forumTopicsepo;

	public List<ForumTopics> getForumTopics() {

		return forumTopicsepo.getForumTopics();

	}

	public List<ForumTopics> getPopularForumTopicsByCategory(String category,
			int limit) {

		System.out.println("popular forum_topics by limit");
		List<ForumTopics> articles = forumTopicsepo
				.getTopForumTopicsByCategory(category, limit);
		Collections.sort(articles, new ForumTopicsComparator());
		return articles;
	}

	public List<ForumTopics> getTopForumTopicsByCategory(String category,
			int limit) {

		System.out.println("forum_topics by limit");
		return forumTopicsepo.getTopForumTopicsByCategory(category, limit);

	}

	public List<ForumTopics> getTopForumTopicsByLimit(int limit) {

		return forumTopicsepo.getTopForumTopicsByLimit(limit);

	}

	public ForumTopics getForumTopicsById(ForumTopicsKey articleID) {

		return forumTopicsepo.getForumTopicsById(articleID);
	}

	public void deleteForumTopicsById(ForumTopicsKey articleID) {

		forumTopicsepo.deleteForumTopicsById(articleID);
	}

	@Transactional
	public void createForumTopics(ForumTopics article) {

		forumTopicsepo.createForumTopics(article);

	}

	@Transactional
	public void updateForumTopics(ForumTopics article) {

		forumTopicsepo.updateForumTopics(article);
	}

}
