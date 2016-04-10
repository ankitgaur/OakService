package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.Section;
import com.oak.entities.SectionKey;
import com.oak.repositories.SectionsRepo;

@Service("sectionsService")
public class SectionsService {

	@Autowired
	SectionsRepo sectionsRepo;

	public List<Section> getSections() {
		return sectionsRepo.getSections();
	}

	public Section getSectionById(String id) {
		
		SectionKey key = new SectionKey();
		String []keyarr = id.split("_"); 		
		key.setPage(keyarr[0]);
		key.setName(keyarr[1]);
		
		return sectionsRepo.getSectionsById(key);
	}

	public void createSection(Section section) {

		sectionsRepo.createSection(section);

	}

	public void updateSection(Section section) {

		sectionsRepo.updateSection(section);

	}

	public void deleteSectionById(String id) {

		SectionKey key = new SectionKey();
		String []keyarr = id.split("_"); 		
		key.setPage(keyarr[0]);
		key.setName(keyarr[1]);
		
		sectionsRepo.deleteSectionById(key);

	}

}
