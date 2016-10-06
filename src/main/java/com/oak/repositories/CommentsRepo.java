package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Comments;
import com.oak.entities.CommentsKey;
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

	public List<Comments> getCommentsByServiceId(String service,String id) {
		
		String blogs_by_category_qry = "SELECT * FROM comments WHERE service='"+service+"' and service_id='"+id+"'";

		System.out.println("BLOGS_BY_CATEGORY_QRY ::: " + blogs_by_category_qry);
		List<Comments> comments = oakCassandraTemplate.findByPartitionKey(blogs_by_category_qry, Comments.class);
		return comments;
		
	}

	public void createComments(Comments comment) {

		oakCassandraTemplate.create(comment, Comments.class);

	}

	public void updateComments(Comments comment) {

		oakCassandraTemplate.update(comment, Comments.class);

	}

	public void deleteCommentById(CommentsKey key) {

		oakCassandraTemplate.deleteById(key, Comments.class);

	}

	public Comments getCommentById(CommentsKey key) {
		// TODO Auto-generated method stub
		return null;
	}

}
