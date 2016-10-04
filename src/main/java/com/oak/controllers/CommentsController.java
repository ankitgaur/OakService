package com.oak.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oak.entities.Comments;
import com.oak.service.CommentsService;
import com.oak.vo.CommentsVO;

@RestController
public class CommentsController {

	@Autowired
	CommentsService commentsService;

	@CrossOrigin
	@RequestMapping(value = "/comments", produces = "application/json", method = RequestMethod.GET)
	public List<CommentsVO> getAllComments() throws JsonParseException,
			JsonMappingException, IOException {
		List<Comments> comments = commentsService.getComments();
		if (comments == null || comments.isEmpty()) {
			return null;
		}

		List<CommentsVO> commentsVO = new ArrayList<CommentsVO>();
		return commentsVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/comments/{id}", produces = "application/json", method = RequestMethod.GET)
	public CommentsVO getCommentsById(@PathVariable("id") long id)
			throws JsonParseException, JsonMappingException, IOException {
		Comments category = commentsService.getCommentsById(id);
		if (category == null) {
			return null;
		}
		return new CommentsVO(category);
	}

	@CrossOrigin
	@RequestMapping(value = "/comments", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createComments(
			@RequestBody CommentsVO commentsVO) throws JsonParseException,
			JsonMappingException, IOException {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = authentication.getName();
		commentsVO.setCreatedby("test");
		commentsVO.setCreatedon(new Date().getTime());
		commentsVO.setCreatedby(email);
		commentsService.createComments(new Comments(commentsVO));
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(value = "/comments/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<CommentsVO> updateComments(
			@PathVariable("id") long id, @RequestBody CommentsVO categoryVO)
			throws JsonGenerationException, JsonMappingException, IOException {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = authentication.getName();
		categoryVO.setUpdatedby("test");
		categoryVO.setUpdatedon(new Date().getTime());
		categoryVO.setUpdatedby(email);
		commentsService.updateComments(new Comments(categoryVO));
		return new ResponseEntity<CommentsVO>(categoryVO, HttpStatus.OK);

	}

	@CrossOrigin
	@RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CommentsVO> deleteComments(@PathVariable("id") long id) {

		Comments category = commentsService.getCommentsById(id);
		if (category == null) {
			System.out.println("Unable to delete. Forum Category with id " + id
					+ " not found");
			return new ResponseEntity<CommentsVO>(HttpStatus.NOT_FOUND);
		}
		commentsService.deleteCommentById(id);
		return new ResponseEntity<CommentsVO>(HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody
	String handleException(Exception ex) {
		if (ex.getMessage().contains("UNIQUE KEY"))
			return "The submitted item already exists.";
		else
			System.out.println(this.getClass() + ": need handleException for: "
					+ ex.getMessage());
		return ex.getMessage();
	}
}
