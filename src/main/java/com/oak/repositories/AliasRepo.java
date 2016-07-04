package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Alias;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("aliasRepo")
public class AliasRepo {

	@Autowired
	private OakCassandraTemplate oakCassandraTemplate;

	public List<Alias> getAliases() {
		List<Alias> Aliass = oakCassandraTemplate.findAll(Alias.class);
		return Aliass;
	}

	public Alias getAliasById(String id) {
		Alias Alias = oakCassandraTemplate.findById(id, Alias.class);
		return Alias;
	}

	public void createAlias(Alias alias) {

		oakCassandraTemplate.create(alias, Alias.class);

	}

	public void updateAlias(Alias alias) {

		oakCassandraTemplate.update(alias, Alias.class);

	}

	public void deleteAliasById(String id) {

		oakCassandraTemplate.deleteById(id, Alias.class);

	}

}
