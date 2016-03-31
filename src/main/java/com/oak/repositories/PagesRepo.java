package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Pages;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("pagesRepo")
public class PagesRepo {

	@Autowired
	private OakCassandraTemplate oakCassandraTemplate;

	public List<Pages> getPages() {
		List<Pages> pages = oakCassandraTemplate.findAll(Pages.class);
		return pages;
	}

	public Pages getPagesById(String id) {
		Pages page = oakCassandraTemplate.findById(id, Pages.class);
		return page;
	}

	public void createPage(Pages page) {

		oakCassandraTemplate.create(page, Pages.class);

	}

	public void updatePage(Pages user) {

		oakCassandraTemplate.update(user, Pages.class);

	}

	public void deletePageById(String id) {

		oakCassandraTemplate.deleteById(id, Pages.class);

	}

}
