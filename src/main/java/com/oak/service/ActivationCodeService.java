package com.oak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.ActivationCode;
import com.oak.repositories.ActivationCodeRepo;

@Service("activationCodeService")
public class ActivationCodeService {

	@Autowired
	ActivationCodeRepo activationCodeRepo;

	

	public ActivationCode getActivationCodeId(String id) {
		return activationCodeRepo.getAliasById(id);
	}

	public String createActivationCode(ActivationCode code) {		
		activationCodeRepo.createAlias(code);
		return code.getEmail();
	}

}
