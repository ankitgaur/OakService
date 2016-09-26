package com.oak.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

import com.oak.entities.ActivationCode;
import com.oak.entities.User;
import com.oak.service.ActivationCodeService;
import com.oak.service.UsersService;
import com.oak.utils.MailUtil;
import com.oak.vo.UsersVO;

@RestController
public class UsersController {

	@Autowired
	UsersService userService;

	@Autowired
	ActivationCodeService activationCodeService;

	@CrossOrigin
	@RequestMapping(value = "/users", produces = "application/json", method = RequestMethod.GET)
	public List<UsersVO> getAllRoles() throws JsonParseException, JsonMappingException, IOException {
		List<User> roles = userService.getUsers();
		if (roles == null || roles.isEmpty()) {
			return null;
		}
		List<UsersVO> usersVO = new ArrayList<UsersVO>();

		for (User role : roles) {
			usersVO.add(new UsersVO(role));
		}
		return usersVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/users/{emialID:.+}", produces = "application/json", method = RequestMethod.GET)
	public UsersVO getRoleById(@PathVariable("emialID") String id)
			throws JsonParseException, JsonMappingException, IOException {
		User user = userService.getUserById(id);
		if (user == null) {
			return null;
		}
		return new UsersVO(user);
	}

	@CrossOrigin
	@RequestMapping(value = "/users", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createRole(@RequestBody UsersVO userVO, UriComponentsBuilder ucBuilder)
			throws JsonParseException, JsonMappingException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		userVO.setCreatedby(email);

		// TODO recaptcha logic
		if (userVO.getGrecaptcharesponse() != null && !userVO.getGrecaptcharesponse().isEmpty()) {
			userService.createUser(new User(userVO));
			HttpHeaders headers = new HttpHeaders();

			UUID uuid = UUID.randomUUID();
			ActivationCode code = new ActivationCode();
			code.setCode(uuid.toString());
			code.setEmail(userVO.getEmail());

			activationCodeService.createActivationCode(code);

			String sub = "Your Ipledge2nigeria.com account is created - Activation required";
			String activationUrl = "http://dev.insodel.com:6767/activate/" + code.getEmail() + "/" + code.getCode();

			String mailbody = "Hi,<br>Thank you for joining. Please click the below link to activate your account :- <br><a href='"
					+ activationUrl + "'>" + activationUrl + "</a>";

			MailUtil.sendMail(userVO.getEmail(), sub, mailbody);

			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);

	}

	@CrossOrigin
	@RequestMapping(value = "/users/{emialID:.+}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<UsersVO> updateRole(@PathVariable("emialID") String emailID, @RequestBody UsersVO usersVO)
			throws JsonGenerationException, JsonMappingException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		usersVO.setUpdatedby(email);
		userService.updateUser(new User(usersVO));
		return new ResponseEntity<UsersVO>(usersVO, HttpStatus.OK);

	}

	@CrossOrigin
	@RequestMapping(value = "/users/{emialID:.+}", method = RequestMethod.DELETE)
	public ResponseEntity<UsersVO> deleteRole(@PathVariable("emialID") String emailID) {
		System.out.println("Fetching & Deleting User with id " + emailID);

		User user = userService.getUserById(emailID);
		if (user == null) {
			System.out.println("Unable to delete. User with id " + emailID + " not found");
			return new ResponseEntity<UsersVO>(HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(emailID);
		return new ResponseEntity<UsersVO>(HttpStatus.NO_CONTENT);
	}

	@CrossOrigin
	@RequestMapping(value = "/activate/{emailID:.+}/{code}", method = RequestMethod.GET)
	public ResponseEntity<UsersVO> activateUser(@PathVariable("emailID") String emailID,
			@PathVariable("code") String code) throws URISyntaxException {
		
		ActivationCode ac = activationCodeService.getActivationCodeId(emailID);
		System.out.println("Activating "+emailID+" "+code);
		if(ac.getCode().equals(code)){
			userService.activateUser(emailID);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(new URI("http://dev.insodel.com:8080/oak"));
			return new ResponseEntity<UsersVO>(headers, HttpStatus.FOUND);
		}
		
		return new ResponseEntity<UsersVO>(HttpStatus.BAD_REQUEST);
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
