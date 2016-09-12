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

import com.oak.entities.States;
import com.oak.service.StateService;
import com.oak.vo.StatesVO;

@RestController
public class StateController {

	@Autowired
	StateService stateService;

	@CrossOrigin
	@RequestMapping(value = "/states", produces = "application/json", method = RequestMethod.GET)
	public List<StatesVO> getAllStates() throws JsonParseException,
			JsonMappingException, IOException {
		List<States> states = stateService.getStates();
		if (states == null || states.isEmpty()) {
			return null;
		}

		List<StatesVO> statesVO = new ArrayList<StatesVO>();

		for (States state : states) {
			statesVO.add(new StatesVO(state));
		}

		return statesVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/states/{stateId}", produces = "application/json", method = RequestMethod.GET)
	public StatesVO getStateById(@PathVariable("stateId") String id)
			throws JsonParseException, JsonMappingException, IOException {
		States state = stateService.getStateById(id);
		if (state == null) {
			return null;
		}
		return new StatesVO(state);
	}

	@CrossOrigin
	@RequestMapping(value = "/states/bulk", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createStateBulk(@RequestBody List<StatesVO> stateVOs,
			UriComponentsBuilder ucBuilder) throws JsonParseException,
			JsonMappingException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();

		for(StatesVO stateVO : stateVOs){
			stateVO.setCreatedby(email);
			stateService.createState(new States(stateVO));
		}
				
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/states", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createState(@RequestBody StatesVO stateVO,
			UriComponentsBuilder ucBuilder) throws JsonParseException,
			JsonMappingException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();

		stateVO.setCreatedby(email);
		stateService.createState(new States(stateVO));
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(value = "/states/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<StatesVO> updateStates(@PathVariable("id") String id,
			@RequestBody StatesVO stateVO) throws JsonGenerationException,
			JsonMappingException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		stateVO.setUpdatedby(email);
		stateService.updateState(new States(stateVO));
		return new ResponseEntity<StatesVO>(stateVO, HttpStatus.OK);

	}

	@CrossOrigin
	@RequestMapping(value = "/states/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<StatesVO> deleteState(@PathVariable("id") String id) {
		System.out.println("Fetching & Deleting User with id " + id);

		States state = stateService.getStateById(id);
		if (state == null) {
			System.out.println("Unable to delete. User with id " + id
					+ " not found");
			return new ResponseEntity<StatesVO>(HttpStatus.NOT_FOUND);
		}
		stateService.deleteStateById(id);
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
