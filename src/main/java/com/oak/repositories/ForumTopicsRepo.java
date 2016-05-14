package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.ForumTopics;
import com.oak.entities.ForumTopicsKey;
import com.oak.utils.OakCassandraTemplate;

@Repository("forumTopicsRepo")
@Transactional
public class ForumTopicsRepo {

	@Autowired
	private OakCassandraTemplate oakCassendraTemplate;

	public List<ForumTopics> getForumTopics() {
		List<ForumTopics> articles = oakCassendraTemplate
				.findAll(ForumTopics.class);
		return articles;
	}

	public List<ForumTopics> getTopForumTopicsByCategory(String category,
			int limit) {
		String article_by_category_qry = "SELECT * FROM forum_topics WHERE category=";
		article_by_category_qry = article_by_category_qry + "'" + category
				+ "'" + " LIMIT " + limit;
		System.out.println("FORUMTOPICS_BY_CATEGORY_QRY ::: "
				+ article_by_category_qry);
		List<ForumTopics> articles = oakCassendraTemplate.findByPartitionKey(
				article_by_category_qry, ForumTopics.class);
		return articles;
	}

	public List<ForumTopics> getTopForumTopicsByLimit(int limit) {
		String article_by_limt_qry = "SELECT * FROM forum_topics LIMIT ";
		article_by_limt_qry = article_by_limt_qry + limit;
		System.out.println("forum_topics_BY_CATEGORY_QRY ::: "
				+ article_by_limt_qry);
		List<ForumTopics> articles = oakCassendraTemplate.findByLimit(
				article_by_limt_qry, ForumTopics.class);
		return articles;
	}

	public ForumTopics getForumTopicsById(ForumTopicsKey id) {
		ForumTopics article = oakCassendraTemplate.findById(id,
				ForumTopics.class);
		return article;
	}

	public void deleteForumTopicsById(ForumTopicsKey id) {

		oakCassendraTemplate.deleteById(id, ForumTopics.class);

	}

	public void createForumTopics(ForumTopics article) {

		oakCassendraTemplate.create(article, ForumTopics.class);

	}

	public void updateForumTopics(ForumTopics article) {

		oakCassendraTemplate.update(article, ForumTopics.class);

	}

}
