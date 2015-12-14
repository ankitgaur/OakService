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

import com.oak.entities.States;
import com.oak.repositories.StateRepo;
import com.oak.vo.StatesVO;

@RestController
public class StateController {

	@Autowired
	StateRepo statesRepo;

	@RequestMapping(value = "/states", produces = "application/json", method = RequestMethod.GET)
	public List<StatesVO> getAllStates() throws JsonParseException,
			JsonMappingException, IOException {
		List<States> states = statesRepo.getStates();
		if (states == null || states.isEmpty()) {
			return null;
		}

		List<StatesVO> statesVO = new ArrayList<StatesVO>();

		for (States state : states) {
			statesVO.add(new StatesVO(state));
		}

		return statesVO;
	}

	@RequestMapping(value = "/states/{stateId}", produces = "application/json", method = RequestMethod.GET)
	public StatesVO getStateById(@PathVariable("stateId") long id)
			throws JsonParseException, JsonMappingException, IOException {
		States state = statesRepo.getStateById(id);
		if (state == null) {
			return null;
		}
		return new StatesVO(state);
	}

	@RequestMapping(value = "/states", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createBlog(@RequestBody StatesVO stateVO,
			UriComponentsBuilder ucBuilder) throws JsonParseException,
			JsonMappingException, IOException {

		statesRepo.createState(new States(stateVO));
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/Article/{id}")
				.buildAndExpand(stateVO.getCreatedBy()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/states/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<StatesVO> updateStates(@PathVariable("id") long id,
			@RequestBody StatesVO stateVO) throws JsonGenerationException,
			JsonMappingException, IOException {
		statesRepo.updateState(new States(stateVO));
		return new ResponseEntity<StatesVO>(stateVO, HttpStatus.OK);

	}

	@RequestMapping(value = "/states/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<StatesVO> deleteBlog(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting User with id " + id);

		States state = statesRepo.getStateById(id);
		if (state == null) {
			System.out.println("Unable to delete. User with id " + id
					+ " not found");
			return new ResponseEntity<StatesVO>(HttpStatus.NOT_FOUND);
		}
		statesRepo.deleteStateById(id);
		return new ResponseEntity<StatesVO>(HttpStatus.NO_CONTENT);
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