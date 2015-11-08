package com.oak.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oak.dto.Response;

@RestController
public class ArticleController {

	//@RolesAllowed(value = { "ADMIN", "FILEMOVER_READ" })
	@RequestMapping(value = "/article/{id}", produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody
	String getBasicJob(@PathVariable Long id) throws JsonProcessingException {

		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//username = authentication.getName();

		/*for (GrantedAuthority auth : authentication.getAuthorities()) {
			if (auth.getAuthority().equals("ROLE_ADMIN")) {
				isAdmin = true;
			}
		}*/
		Response response = new Response();
		response.setStatuscode(""+id);
		response.setStatusmsg("received "+id);
		
		return response.toString();
	}
	
}
