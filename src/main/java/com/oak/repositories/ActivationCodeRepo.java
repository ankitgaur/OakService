package com.oak.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.ActivationCode;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("activationCodeRepo")
public class ActivationCodeRepo {

	@Autowired
	private OakCassandraTemplate oakCassandraTemplate;

	public ActivationCode getAliasById(String id) {
		ActivationCode code = oakCassandraTemplate.findById(id, ActivationCode.class);
		return code;
	}

	public void createAlias(ActivationCode code) {

		oakCassandraTemplate.create(code, ActivationCode.class);

	}

}
