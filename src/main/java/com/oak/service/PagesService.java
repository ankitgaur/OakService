package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.Page;
import com.oak.repositories.PagesRepo;

@Service("pagesService")
public class PagesService {

	@Autowired
	PagesRepo pagesRepo;

	public List<Page> getPages() {
		return pagesRepo.getPages();
	}

	public Page getPageById(String id) {
		return pagesRepo.getPagesById(id);
	}

	public void createPage(Page page) {

		pagesRepo.createPage(page);

	}

	public void updatePage(Page page) {

		pagesRepo.updatePage(page);

	}

	public void deletePageById(String id) {

		pagesRepo.deletePageById(id);

	}

}
