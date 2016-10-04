package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.Comments;
import com.oak.repositories.CommentsRepo;

@Service("commentsService")
public class CommentsService {

	@Autowired
	private CommentsRepo commentsRepo;

	public List<Comments> getComments() {
		return commentsRepo.getComments();
	}

	public Comments getCommentsById(long id) {
		return commentsRepo.getCommentsById(id);
	}

	public void createComments(Comments comment) {

		commentsRepo.createComments(comment);

	}

	public void updateComments(Comments comment) {

		commentsRepo.updateComments(comment);

	}

	public void deleteCommentById(long id) {

		commentsRepo.deleteCommentById(id);

	}
}
