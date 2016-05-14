package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.ForumPost;
import com.oak.entities.ForumPostKey;
import com.oak.utils.OakCassandraTemplate;

@Repository("forumPostRepo")
@Transactional
public class ForumPostRepo {

	@Autowired
	private OakCassandraTemplate oakCassendraTemplate;

	public List<ForumPost> getForumPost() {
		List<ForumPost> articles = oakCassendraTemplate
				.findAll(ForumPost.class);
		return articles;
	}

	public List<ForumPost> getTopForumPostByCategory(String category, int limit) {
		String forum_by_category_qry = "SELECT * FROM forum_posts WHERE category=";
		forum_by_category_qry = forum_by_category_qry + "'" + category + "'"
				+ " LIMIT " + limit;
		System.out.println("ARTICLE_BY_CATEGORY_QRY ::: "
				+ forum_by_category_qry);
		List<ForumPost> articles = oakCassendraTemplate.findByPartitionKey(
				forum_by_category_qry, ForumPost.class);
		return articles;
	}

	public List<ForumPost> getTopForumPostByLimit(int limit) {
		String article_by_limt_qry = "SELECT * FROM forum_posts LIMIT ";
		article_by_limt_qry = article_by_limt_qry + limit;
		System.out.println("ForumPost_BY_CATEGORY_QRY ::: "
				+ article_by_limt_qry);
		List<ForumPost> articles = oakCassendraTemplate.findByLimit(
				article_by_limt_qry, ForumPost.class);
		return articles;
	}

	public ForumPost getForumPostById(ForumPostKey id) {
		ForumPost article = oakCassendraTemplate.findById(id, ForumPost.class);
		return article;
	}

	public void deleteForumPostById(ForumPostKey id) {

		oakCassendraTemplate.deleteById(id, ForumPost.class);

	}

	public void createForumPost(ForumPost article) {

		oakCassendraTemplate.create(article, ForumPost.class);

	}

	public void updateForumPost(ForumPost article) {

		oakCassendraTemplate.update(article, ForumPost.class);

	}

}
