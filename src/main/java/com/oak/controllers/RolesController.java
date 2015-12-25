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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.oak.entities.Roles;
import com.oak.service.RolesService;
import com.oak.vo.RolesVO;

@RestController
public class RolesController {

	@Autowired
	RolesService rolesService;

	@RequestMapping(value = "/roles", produces = "application/json", method = RequestMethod.GET)
	public List<RolesVO> getAllRoles() throws JsonParseException,
			JsonMappingException, IOException {
		List<Roles> roles = rolesService.getRoles();
		if (roles == null || roles.isEmpty()) {
			return null;
		}

		List<RolesVO> rolesVO = new ArrayList<RolesVO>();

		for (Roles role : roles) {
			rolesVO.add(new RolesVO(role));
		}

		return rolesVO;
	}

	@RequestMapping(value = "/roles/{roleName}", produces = "application/json", method = RequestMethod.GET)
	public RolesVO getRoleById(@PathVariable("roleName") String roleName)
			throws JsonParseException, JsonMappingException, IOException {
		Roles role = rolesService.getRoleById(roleName);
		if (role == null) {
			return null;
		}
		return new RolesVO(role);
	}

	@RequestMapping(value = "/roles", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createRole(@RequestBody RolesVO roleVO,
			UriComponentsBuilder ucBuilder) throws JsonParseException,
			JsonMappingException, IOException {

		rolesService.createRole(new Roles(roleVO));
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/roles/{roleName}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<RolesVO> updateRole(
			@PathVariable("roleName") String roleName,
			@RequestBody RolesVO roleVO) throws JsonGenerationException,
			JsonMappingException, IOException {
		rolesService.updateRoles(new Roles(roleVO));
		return new ResponseEntity<RolesVO>(roleVO, HttpStatus.OK);

	}

	@RequestMapping(value = "/roles/{roleName}", method = RequestMethod.DELETE)
	public ResponseEntity<RolesVO> deleteRole(
			@PathVariable("roleName") String roleName) {
		System.out.println("Fetching & Deleting User with id " + roleName);

		Roles role = rolesService.getRoleById(roleName);
		if (role == null) {
			System.out.println("Unable to delete. User with id " + roleName
					+ " not found");
			return new ResponseEntity<RolesVO>(HttpStatus.NOT_FOUND);
		}
		rolesService.deleteRolesById(roleName);
		return new ResponseEntity<RolesVO>(HttpStatus.NO_CONTENT);
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
