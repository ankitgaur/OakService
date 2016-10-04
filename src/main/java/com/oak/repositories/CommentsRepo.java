package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Comments;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("commentsRepo")
public class CommentsRepo {

	@Autowired
	private OakCassandraTemplate oakCassandraTemplate;

	public List<Comments> getComments() {
		List<Comments> categories = oakCassandraTemplate
				.findAll(Comments.class);
		return categories;
	}

	public Comments getCommentsById(long id) {
		Comments comment = oakCassandraTemplate.findById(id, Comments.class);
		return comment;
	}

	public void createComments(Comments comment) {

		oakCassandraTemplate.create(comment, Comments.class);

	}

	public void updateComments(Comments comment) {

		oakCassandraTemplate.update(comment, Comments.class);

	}

	public void deleteCommentById(long id) {

		oakCassandraTemplate.deleteById(id, Comments.class);

	}

}
