package com.oak.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oak.entities.Jwt;
import com.oak.entities.User;
import com.oak.exceptions.JwtTokenMissingException;
import com.oak.repositories.JwtRepo;
import com.oak.service.UsersService;
import com.oak.utils.JWTUtil;
import com.oak.vo.LoginVO;

import io.jsonwebtoken.impl.Base64Codec;

@RestController
public class LoginController {

	@Autowired
	UsersService usersService;

	@Autowired
	JwtRepo jwtRepo;

	@CrossOrigin
	@RequestMapping(value = "/whoami", produces = "application/json", method = RequestMethod.POST)
	public LoginVO whoami(HttpServletRequest request, HttpServletResponse response) {

		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			System.out.println("Where i expected");
			throw new JwtTokenMissingException("No JWT token found in request headers");
		}

		String token = header.substring(7);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
	
		User user = usersService.getUserById(email);

		LoginVO lvo = new LoginVO();
		lvo.setName(user.getName());
		lvo.setUsername(user.getUsername());
		lvo.setGroups(user.getGroups());
		lvo.setToken(token);

		return lvo;

	}

	@CrossOrigin
	@RequestMapping(value = "/login", produces = "application/json", method = RequestMethod.GET)
	public LoginVO login(HttpServletRequest request, HttpServletResponse response) {

		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("Basic ")) {
			return null;
		}

		String encrypted = header.substring(6);

		String decrypted = Base64Codec.BASE64.decodeToString(encrypted);

		String[] credentials = decrypted.split(":");

		String email = credentials[0].trim();
		String passwd = credentials[1].trim();

		User user = usersService.getUserById(email);

		if (user != null && user.isActivated() && user.getPassword().equals(passwd)) {

			String ip = request.getRemoteAddr();
			String token = JWTUtil.generateToken(user, ip);

			LoginVO lvo = new LoginVO();
			lvo.setName(user.getName());
			lvo.setUsername(user.getUsername());
			lvo.setGroups(user.getGroups());
			lvo.setToken(token);

			Jwt jwt = new Jwt();
			jwt.setEmail(email);
			jwt.setJwt(token);

			jwtRepo.createJwt(jwt);

			return lvo;
		}

		return null;
	}

	@CrossOrigin
	@RequestMapping(value = "/logoff", produces = "application/json", method = RequestMethod.GET)
	public String logoff(HttpServletRequest request, HttpServletResponse response) {

		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			return null;
		}

		String token = header.substring(7);

		jwtRepo.deleteToken(token.trim());

		return "{\"status\":\"loggedoff\"}";
	}

}
