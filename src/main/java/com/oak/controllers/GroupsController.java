package com.oak.controllers;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.oak.entities.Groups;
import com.oak.service.GroupsService;
import com.oak.vo.GroupsVO;

@RestController
public class GroupsController {

	@Autowired
	GroupsService groupService;

	@CrossOrigin
	@RequestMapping(value = "/groups", produces = "application/json", method = RequestMethod.GET)
	public List<GroupsVO> getAllGroups() throws JsonParseException,
			JsonMappingException, IOException {
		List<Groups> groups = groupService.getGroups();
		if (groups == null || groups.isEmpty()) {
			return null;
		}

		List<GroupsVO> groupsVO = new ArrayList<GroupsVO>();

		for (Groups group : groups) {
			groupsVO.add(new GroupsVO(group));
		}

		return groupsVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/groups/{groupname}", produces = "application/json", method = RequestMethod.GET)
	public GroupsVO getGroupById(@PathVariable("groupname") String id)
			throws JsonParseException, JsonMappingException, IOException {
		Groups group = groupService.getGroupById(id);
		if (group == null) {
			return null;
		}
		return new GroupsVO(group);
	}

	@CrossOrigin
	@RequestMapping(value = "/groups", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createGroup(@RequestBody GroupsVO groupsVO,
			UriComponentsBuilder ucBuilder) throws JsonParseException,
			JsonMappingException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		groupsVO.setCreatedby(email);
		groupService.createGroup(new Groups(groupsVO));
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(value = "/groups/{groupname}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<GroupsVO> updateGroup(
			@PathVariable("groupname") String id, @RequestBody GroupsVO groupsVO)
			throws JsonGenerationException, JsonMappingException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		groupsVO.setUpdatedby(email);
		groupService.updateGroup(new Groups(groupsVO));
		return new ResponseEntity<GroupsVO>(groupsVO, HttpStatus.OK);

	}

	@CrossOrigin
	@RequestMapping(value = "/groups/{groupname}", method = RequestMethod.DELETE)
	public ResponseEntity<GroupsVO> deleteGroup(
			@PathVariable("groupname") String id) {
		System.out.println("Fetching & Deleting User with id " + id);

		Groups group = groupService.getGroupById(id);
		if (group == null) {
			System.out.println("Unable to delete. User with id " + id
					+ " not found");
			return new ResponseEntity<GroupsVO>(HttpStatus.NOT_FOUND);
		}
		groupService.deleteGroupById(id);
		return new ResponseEntity<GroupsVO>(HttpStatus.NO_CONTENT);
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
