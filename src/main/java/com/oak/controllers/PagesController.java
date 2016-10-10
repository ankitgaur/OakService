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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import com.oak.entities.Page;
import com.oak.service.PagesService;
import com.oak.vo.PageVO;

@RestController
public class PagesController {

	@Autowired
	PagesService pagesService;

	@CrossOrigin
	@RequestMapping(value = "/pages", produces = "application/json", method = RequestMethod.GET)
	public List<PageVO> getAllPages() throws JsonParseException,
			JsonMappingException, IOException {
		List<Page> pages = pagesService.getPages();
		if (pages == null || pages.isEmpty()) {
			return null;
		}
		List<PageVO> pagesVO = new ArrayList<PageVO>();

		for (Page page : pages) {
			pagesVO.add(new PageVO(page));
		}
		return pagesVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/pages/{name}", produces = "application/json", method = RequestMethod.GET)
	public PageVO getPageById(@PathVariable("name") String id)
			throws JsonParseException, JsonMappingException, IOException {
		Page Page = pagesService.getPageById(id);
		if (Page == null) {
			return null;
		}
		return new PageVO(Page);
	}

	@CrossOrigin
	@Secured("ROLE_pages_write")
	@RequestMapping(value = "/pages", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createPage(@RequestBody PageVO PageVO,
			UriComponentsBuilder ucBuilder) throws JsonParseException,
			JsonMappingException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		
		PageVO.setCreatedby(email);

		pagesService.createPage(new Page(PageVO));
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@CrossOrigin
	@Secured("ROLE_pages_write")
	@RequestMapping(value = "/pages/{name}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<PageVO> updatePage(
			@PathVariable("name") String pageName, @RequestBody PageVO PagesVO)
			throws JsonGenerationException, JsonMappingException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		
		PagesVO.setUpdatedby(email);
		
		pagesService.updatePage(new Page(PagesVO));
		return new ResponseEntity<PageVO>(PagesVO, HttpStatus.OK);

	}

	@CrossOrigin
	@Secured("ROLE_pages_write")
	@RequestMapping(value = "/pages/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<PageVO> deletePage(
			@PathVariable("name") String pageName) {
		System.out.println("Fetching & Deleting Page with id " + pageName);

		Page Page = pagesService.getPageById(pageName);
		if (Page == null) {
			System.out.println("Unable to delete. Page with id " + pageName
					+ " not found");
			return new ResponseEntity<PageVO>(HttpStatus.NOT_FOUND);
		}
		pagesService.deletePageById(pageName);
		return new ResponseEntity<PageVO>(HttpStatus.NO_CONTENT);
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
