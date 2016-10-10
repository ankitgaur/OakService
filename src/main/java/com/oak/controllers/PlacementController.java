package com.oak.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oak.entities.Placement;
import com.oak.service.ImageService;
import com.oak.service.PlacementService;
import com.oak.vo.PlacementVO;

@RestController
public class PlacementController {

	@Autowired
	PlacementService placementService;

	@Autowired
	ImageService imageService;

	@CrossOrigin
	@RequestMapping(value = "/placements/{id}", produces = "application/json", method = RequestMethod.GET)
	public PlacementVO getPlacementById(@PathVariable String id)
			throws JsonProcessingException, ParseException {

		Placement placement = placementService.getPlacementById(id);
		if (null != placement) {
			PlacementVO placementVO = new PlacementVO(placement);
			return placementVO;
		} else {
			return null;
		}

	}

	@CrossOrigin
	@RequestMapping(value = "/placements/section/{id}", produces = "application/json", method = RequestMethod.GET)
	public List<PlacementVO> getPlacementForSection(@PathVariable String id)
			throws JsonProcessingException {
		List<Placement> placements = placementService.findBySection(id);
		List<PlacementVO> placementVO = new ArrayList<PlacementVO>();
		for (Placement placement : placements) {
			PlacementVO vo = new PlacementVO(placement);
			placementVO.add(vo);
		}
		return placementVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/placements", produces = "application/json", method = RequestMethod.GET)
	public List<PlacementVO> getPlacement() throws JsonProcessingException {
		List<Placement> placements = placementService.getPlacements();
		List<PlacementVO> placementVO = new ArrayList<PlacementVO>();
		for (Placement placement : placements) {
			PlacementVO vo = new PlacementVO(placement);
			placementVO.add(vo);
		}
		return placementVO;

	}

	@CrossOrigin
	@Secured("ROLE_placement_write")
	@RequestMapping(value = "/placements/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<PlacementVO> deleteRticle(
			@PathVariable("id") String id) {
		System.out.println("Fetching & Deleting User with id " + id);

		placementService.deletePlacementById(id);
		return new ResponseEntity<PlacementVO>(HttpStatus.NO_CONTENT);
	}

	@CrossOrigin
	@Secured("ROLE_placement_write")
	@RequestMapping(value = "/placements", method = RequestMethod.POST)
	public ResponseEntity<Void> createPlacement(
			@RequestParam("page") String page,
			@RequestParam("section") String section,
			@RequestParam("position") String position,
			@RequestParam("title") String title,
			@RequestParam("link") String link,
			@RequestParam("intro") String intro,
			@RequestParam(name = "image", required = false) MultipartFile image)
			throws ParseException, IOException {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = authentication.getName();
		PlacementVO placementVO = new PlacementVO();
		placementVO.setPage(page);
		placementVO.setSection(section);
		placementVO.setPosition(Integer.valueOf(position));
		placementVO.setTitle(title);
		placementVO.setIntro(intro);
		String id = null;
		try {
			byte[] imgbytes = image.getBytes();
			if (imgbytes != null) {
				id = imageService.saveImage("blogimage",
						image.getOriginalFilename(), image.getSize(),
						image.getBytes(), email);
			}
		} catch (NullPointerException npe) {
			// do nothing
		}
		if (id != null)
			placementVO.setImg("http://www.ipledge2nigeria.com/service/image/" + id);

		Placement placement = new Placement(placementVO);
		// TODO: Get user name from session
		placement.setCreatedby("plcmntctrl");
		placement.setCreatedon(new Date().getTime());
		placement.setCreatedby(email);

		placementService.createPlacement(placement);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@CrossOrigin
	@Secured("ROLE_placement_write")
	@RequestMapping(value = "/placements/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<PlacementVO> updatePlacement(
			@PathVariable("id") String placementID,
			@RequestBody PlacementVO placementVO) throws ParseException {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = authentication.getName();
		Placement placement = new Placement(placementVO);
		placement.setCreatedby("plcmntctrl");
		placement.setCreatedon(new Date().getTime());
		placement.setUpdatedby(email);
		placementService.updatePlacement(placement);
		return new ResponseEntity<PlacementVO>(placementVO, HttpStatus.OK);

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
