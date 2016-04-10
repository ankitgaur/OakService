package com.oak.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.oak.entities.Section;
import com.oak.service.SectionsService;
import com.oak.vo.SectionVO;

@RestController
public class SectionsController {

	@Autowired
	SectionsService sectionsService;

	@CrossOrigin
	@RequestMapping(value = "/sections", produces = "application/json", method = RequestMethod.GET)
	public List<SectionVO> getAllSections() throws JsonParseException,
			JsonMappingException, IOException {
		List<Section> sections = sectionsService.getSections();
		if (sections == null || sections.isEmpty()) {
			return null;
		}
		List<SectionVO> sectionsVO = new ArrayList<SectionVO>();

		for (Section section : sections) {
			sectionsVO.add(new SectionVO(section));
		}
		return sectionsVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/sections/{id}", produces = "application/json", method = RequestMethod.GET)
	public SectionVO getSectionById(@PathVariable("id") String id)
			throws JsonParseException, JsonMappingException, IOException {
		Section Section = sectionsService.getSectionById(id);
		if (Section == null) {
			return null;
		}
		return new SectionVO(Section);
	}

	@CrossOrigin
	@RequestMapping(value = "/sections", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createSection(@RequestBody SectionVO SectionVO,
			UriComponentsBuilder ucBuilder) throws JsonParseException,
			JsonMappingException, IOException {

		sectionsService.createSection(new Section(SectionVO));
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(value = "/sections/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<SectionVO> updateSection(
			@PathVariable("id") String id, @RequestBody SectionVO SectionsVO)
			throws JsonGenerationException, JsonMappingException, IOException {
		sectionsService.updateSection(new Section(SectionsVO));
		return new ResponseEntity<SectionVO>(SectionsVO, HttpStatus.OK);

	}

	@CrossOrigin
	@RequestMapping(value = "/sections/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<SectionVO> deleteSection(
			@PathVariable("id") String id) {
		System.out.println("Fetching & Deleting Section with id " + id);

		Section Section = sectionsService.getSectionById(id);
		if (Section == null) {
			System.out.println("Unable to delete. Section with id " + id
					+ " not found");
			return new ResponseEntity<SectionVO>(HttpStatus.NOT_FOUND);
		}
		sectionsService.deleteSectionById(id);
		return new ResponseEntity<SectionVO>(HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody
	String handleException(Exception ex) {
		if (ex.getMessage().contains("UNIQUE KEY"))
			return "The submitted item already exists.";
		else
			System.out.println(this.getClass() + ": need handleException for: "
					+ ex.getMessage());
		return ex.getMessage();
	}
}
