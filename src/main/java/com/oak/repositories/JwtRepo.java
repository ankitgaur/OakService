package com.oak.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Jwt;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("jwtRepo")
public class JwtRepo {

	@Autowired
	private OakCassandraTemplate oakCassandraTemplate;

	public Jwt findByToken(String token) {
		Jwt jwt = oakCassandraTemplate.findById(token, Jwt.class);
		return jwt;
	}

	public void createJwt(Jwt jwt) {

		oakCassandraTemplate.create(jwt, Jwt.class);

	}
	
	public void deleteToken(String id) {

		oakCassandraTemplate.deleteById(id, Jwt.class);

	}

}
