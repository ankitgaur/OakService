package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.Comments;
import com.oak.entities.CommentsKey;
import com.oak.repositories.CommentsRepo;

@Service("commentsService")
public class CommentsService {

	@Autowired
	private CommentsRepo commentsRepo;

	public List<Comments> getComments() {
		return commentsRepo.getComments();
	}

	public Comments getCommentById(CommentsKey key) {
		return commentsRepo.getCommentById(key);
	}

	public List<Comments> getCommentsByServiceId(String service,String id) {
		return commentsRepo.getCommentsByServiceId(service,id);
	}

	public void createComments(Comments comment) {

		commentsRepo.createComments(comment);

	}

	public void updateComments(Comments comment) {

		commentsRepo.updateComments(comment);

	}

	public void deleteCommentById(CommentsKey key) {

		commentsRepo.deleteCommentById(key);

	}
}
