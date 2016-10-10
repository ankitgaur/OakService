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
import org.springframework.security.access.annotation.Secured;
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
import com.oak.entities.CommentsKey;
import com.oak.entities.User;
import com.oak.service.AliasService;
import com.oak.service.CommentsService;
import com.oak.service.UsersService;
import com.oak.vo.CommentsVO;

@RestController
public class CommentsController {

	@Autowired
	CommentsService commentsService;

	@Autowired
	AliasService aliasService;
	
	@Autowired
	UsersService usersService;

	@CrossOrigin
	@RequestMapping(value = "/comments", produces = "application/json", method = RequestMethod.GET)
	public List<CommentsVO> getAllComments() throws JsonParseException, JsonMappingException, IOException {
		List<Comments> comments = commentsService.getComments();
	
		List<CommentsVO> commentsVO = new ArrayList<CommentsVO>();

		if (comments != null) {

			for (Comments comment : comments) {
				commentsVO.add(new CommentsVO(comment));
			}

		}

		return commentsVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/comments/{service}/{id}", produces = "application/json", method = RequestMethod.GET)
	public List<CommentsVO> getCommentsByServiceId(@PathVariable("service") String service, @PathVariable("id") String id)
			throws JsonParseException, JsonMappingException, IOException {

		List<CommentsVO> cvos = new ArrayList<CommentsVO>();

		List<Comments> comments = commentsService.getCommentsByServiceId(service, id);
		if (comments != null) {

			for (Comments comment : comments) {
				cvos.add(new CommentsVO(comment));
			}

		}
		return cvos;
	}

	@CrossOrigin
	@Secured("ROLE_comments_write")
	@RequestMapping(value = "/comments", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createComments(@RequestBody CommentsVO commentsVO)
			throws JsonParseException, JsonMappingException, IOException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		
		User user = usersService.getUserById(email);
		
		if(user!=null){
			commentsVO.setAuthor(user.getUsername());
		} 
		
		commentsVO.setCreatedby(email);
		commentsVO.setCreatedon(new Date().getTime());
		
		commentsService.createComments(new Comments(commentsVO));
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@CrossOrigin
	@Secured("ROLE_comments_write")
	@RequestMapping(value = "/comments", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<CommentsVO> updateComments(@RequestBody CommentsVO categoryVO)
			throws JsonGenerationException, JsonMappingException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		
		//TODO: Only user who created should be able to update
		categoryVO.setUpdatedby("test");
		categoryVO.setUpdatedon(new Date().getTime());
		categoryVO.setUpdatedby(email);
		commentsService.updateComments(new Comments(categoryVO));
		return new ResponseEntity<CommentsVO>(categoryVO, HttpStatus.OK);

	}

	@CrossOrigin
	@Secured("ROLE_comments_write")
	@RequestMapping(value = "/comments/{service}/{service_id}/{createdon}", method = RequestMethod.DELETE)
	public ResponseEntity<CommentsVO> deleteComments(@PathVariable("service") String service,
			@PathVariable("service_id") String service_id,
			@PathVariable("createdon") long createdon) {
		//TODO: Only user who created should be able to update
		CommentsKey key = new CommentsKey(service,service_id,createdon);
	
		commentsService.deleteCommentById(key);
		return new ResponseEntity<CommentsVO>(HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody String handleException(Exception ex) {
		if (ex.getMessage().contains("UNIQUE KEY"))
			return "The submitted item already exists.";
		else
			System.out.println(this.getClass() + ": need handleException for: " + ex.getMessage());
		return ex.getMessage();
	}
}
