package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.Pages;
import com.oak.repositories.PagesRepo;

@Service("pagesService")
public class PagesService {

	@Autowired
	PagesRepo pagesRepo;

	public List<Pages> getPages() {
		return pagesRepo.getPages();
	}

	public Pages getPageById(String id) {
		return pagesRepo.getPagesById(id);
	}

	public void createPage(Pages page) {

		pagesRepo.createPage(page);

	}

	public void updatePage(Pages page) {

		pagesRepo.updatePage(page);

	}

	public void deletePageById(String id) {

		pagesRepo.deletePageById(id);

	}

}
