package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Page;
import com.oak.utils.OakCassandraTemplate;

@Transactional
@Repository("pagesRepo")
public class PagesRepo {

	@Autowired
	private OakCassandraTemplate oakCassandraTemplate;

	public List<Page> getPages() {
		List<Page> pages = oakCassandraTemplate.findAll(Page.class);
		return pages;
	}

	public Page getPagesById(String id) {
		Page page = oakCassandraTemplate.findById(id, Page.class);
		return page;
	}

	public void createPage(Page page) {

		oakCassandraTemplate.create(page, Page.class);

	}

	public void updatePage(Page user) {

		oakCassandraTemplate.update(user, Page.class);

	}

	public void deletePageById(String id) {

		oakCassandraTemplate.deleteById(id, Page.class);

	}

}
