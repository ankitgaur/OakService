package com.oak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.Alias;
import com.oak.repositories.AliasRepo;

@Service("aliasService")
public class AliasService {

	@Autowired
	AliasRepo aliasRepo;

	

	public Alias getAliasById(String id) {
		return aliasRepo.getAliasById(id);
	}

	public String createAlias(Alias alias) {		
		aliasRepo.createAlias(alias);
		return alias.getId();
	}

}
