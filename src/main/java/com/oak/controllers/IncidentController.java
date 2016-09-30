package com.oak.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.oak.entities.Alias;
import com.oak.entities.Incident;
import com.oak.entities.IncidentKey;
import com.oak.entities.User;
import com.oak.service.AliasService;
import com.oak.service.ImageService;
import com.oak.service.IncidentService;
import com.oak.service.UsersService;
import com.oak.vo.IncidentVO;

@RestController
public class IncidentController {

	@Autowired
	IncidentService incidentService;

	@Autowired
	UsersService usersService;

	@Autowired
	AliasService aliasService;

	@Autowired
	ImageService imageService;

	@CrossOrigin
	@RequestMapping(value = "/incidents", produces = "application/json", method = RequestMethod.GET)
	public List<IncidentVO> getIncidents() throws JsonGenerationException,
			JsonMappingException, IOException {
		List<Incident> incidents = incidentService.getIncidents();

		List<IncidentVO> incidentVO = new ArrayList<IncidentVO>();
		for (Incident incident : incidents) {
			IncidentVO vo = new IncidentVO(incident);
			Date createOn = new Date(incident.getIncidentKey().getCreatedOn());
			Date reportDate = new Date(incident.getReportDate());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String createDateText = dateFormatter.format(createOn);
			String reportDateText = dateFormatter.format(reportDate);
			vo.setCreatedOnStr(createDateText);
			vo.setReportDateStr(reportDateText);
			incidentVO.add(vo);
		}

		return incidentVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/incidents/{incidentype}/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<IncidentVO> getTopIncidentsByIncTypes(
			@PathVariable("incidentype") String incidentype,
			@PathVariable("limit") int limit) throws JsonGenerationException,
			JsonMappingException, IOException {
		List<Incident> incidents = incidentService
				.getTopIncidentsByIncidentsTypes(incidentype, limit);

		List<IncidentVO> incidentVO = new ArrayList<IncidentVO>();
		for (Incident incident : incidents) {
			IncidentVO vo = new IncidentVO(incident);
			Date createOn = new Date(incident.getIncidentKey().getCreatedOn());
			Date reportDate = new Date(incident.getReportDate());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String createDateText = dateFormatter.format(createOn);
			String reportDateText = dateFormatter.format(reportDate);
			vo.setCreatedOnStr(createDateText);
			vo.setReportDateStr(reportDateText);
			incidentVO.add(vo);
		}

		return incidentVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/incidents/page/{createdOn}", produces = "application/json", method = RequestMethod.GET)
	public List<IncidentVO> getTopIncidentsByPage(
			@PathVariable("createdOn") long createdOn)
			throws JsonGenerationException, JsonMappingException, IOException {
		int limit = 20;
		List<Incident> incidents = incidentService.getIncidentsAfterId(
				createdOn, limit);

		List<IncidentVO> incidentVO = new ArrayList<IncidentVO>();
		for (Incident incident : incidents) {
			IncidentVO vo = new IncidentVO(incident);
			Date createOn = new Date(incident.getIncidentKey().getCreatedOn());
			Date reportDate = new Date(incident.getReportDate());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String createDateText = dateFormatter.format(createOn);
			String reportDateText = dateFormatter.format(reportDate);
			vo.setCreatedOnStr(createDateText);
			vo.setReportDateStr(reportDateText);
			incidentVO.add(vo);
		}

		return incidentVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/incidents/limit/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<IncidentVO> getTopIncidentsByLimit(
			@PathVariable("limit") int limit) throws JsonGenerationException,
			JsonMappingException, IOException {
		List<Incident> incidents = incidentService
				.getTopIncidentsByLimit(limit);

		List<IncidentVO> incidentVO = new ArrayList<IncidentVO>();
		for (Incident incident : incidents) {
			IncidentVO vo = new IncidentVO(incident);
			Date createOn = new Date(incident.getIncidentKey().getCreatedOn());
			Date reportDate = new Date(incident.getReportDate());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String createDateText = dateFormatter.format(createOn);
			String reportDateText = dateFormatter.format(reportDate);
			vo.setCreatedOnStr(createDateText);
			vo.setReportDateStr(reportDateText);
			incidentVO.add(vo);
		}

		return incidentVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/incidents/{incidentID}", produces = "application/json", method = RequestMethod.GET)
	public IncidentVO getIncidentById(
			@PathVariable("incidentID") String incidentID)
			throws JsonParseException, JsonMappingException, IOException {

		Alias alias = aliasService.getAliasById(incidentID);
		IncidentKey key = new IncidentKey(alias.getCategory(),
				alias.getCreatedby(), alias.getCreatedon());
		Incident incident = incidentService.getIncidentById(key);
		if (incident == null) {
			return null;
		}
		IncidentVO incidentVO = new IncidentVO(incident);
		Date createOn = new Date(incident.getIncidentKey().getCreatedOn());
		Date reportDate = new Date(incident.getReportDate());
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"dd-MM-yyyy HH:mm:ss");
		String createDateText = dateFormatter.format(createOn);
		String reportDateText = dateFormatter.format(reportDate);
		incidentVO.setCreatedOnStr(createDateText);
		incidentVO.setReportDateStr(reportDateText);

		return incidentVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/incidents/bulk", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createIncidentBulk(
			@RequestBody List<IncidentVO> incidentVOs)
			throws JsonGenerationException, JsonMappingException, IOException,
			ParseException {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = authentication.getName();
		User user = usersService.getUserById(email);

		for (IncidentVO incidentVO : incidentVOs) {
			incidentVO.setCreatedBy(email);
			incidentVO.setAuthor(user.getUsername());
			incidentService.createIncident(new Incident(incidentVO));
		}

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(value = "/incidents", method = RequestMethod.POST)
	public IncidentVO createIncident(
			@RequestParam("type") String type,
			@RequestParam("state") String state,
			@RequestParam("govt") String govt,
			@RequestParam(name = "displayImage", required = false) MultipartFile displayImage,
			@RequestParam("category") String category,
			@RequestParam("description") String description,
			@RequestParam("questions") String questions,
			@RequestParam("status") String status)
			throws JsonGenerationException, JsonMappingException, IOException,
			ParseException {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = authentication.getName();
		String id = null;
		try {
			byte[] imgbytes = displayImage.getBytes();
			if (imgbytes != null) {
				id = imageService.saveImage("blogimage",
						displayImage.getOriginalFilename(),
						displayImage.getSize(), displayImage.getBytes(), email);
			}
		} catch (NullPointerException npe) {
			// do nothing
		}

		IncidentVO incidentVO = new IncidentVO();
		User user = usersService.getUserById(email);
		Date dNow = new Date();
		incidentVO.setCreatedOn(dNow.getTime());
		incidentVO.setReportDate(dNow.getTime());
		incidentVO.setCreatedBy(email);
		incidentVO.setAuthor(user.getUsername());
		incidentVO.setType(type);
		incidentVO.setState(state);
		incidentVO.setGovt(govt);
		incidentVO.setDescription(description);
		incidentVO.setStatus(status);
		Map<String, String> questionsMap = new HashMap<String, String>();
		/*
		 * String[] pairs = questions.split(","); for (int i = 0; i <
		 * pairs.length; i++) { String pair = pairs[i]; String[] keyValue =
		 * pair.split(":"); if (keyValue.length >= 1)
		 * questionsMap.put(keyValue[0], keyValue[1]); }
		 */
		incidentVO.setQuestions(questionsMap);
		if (id != null)
			incidentVO.setImage("http://dev.insodel.com:6767/image/" + id);
		Incident incident = new Incident(incidentVO);

		incidentService.createIncident(incident);
		return new IncidentVO(incident);
	}

	@CrossOrigin
	@RequestMapping(value = "/incidents/image/{id}", method = RequestMethod.POST)
	public String uploadImage(@RequestParam("file") MultipartFile image,
			@PathVariable String id) throws IOException {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = authentication.getName();

		Alias alias = aliasService.getAliasById(id);
		IncidentKey key = new IncidentKey(alias.getCategory(),
				alias.getCreatedby(), alias.getCreatedon());
		Incident incident = incidentService.getIncidentById(key);

		String prefix = email;
		// System.out.println(image.getSize() + image.getOriginalFilename());
		// System.out.println(Arrays.toString(image.getBytes()));

		// TODO : Get username from session
		String iid = imageService.saveImage(prefix,
				image.getOriginalFilename(), image.getSize(), image.getBytes(),
				email);

		incident.setImage(iid);
		incidentService.updateIncident(incident);

		return id;
	}

	@CrossOrigin
	@RequestMapping(value = "/incidents/{id}", consumes = "application/json", produces = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<IncidentVO> updateIncident(
			@RequestBody IncidentVO incidentVO, @PathVariable("id") String id)
			throws JsonGenerationException, JsonMappingException, IOException {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = authentication.getName();

		System.out.println("Updating User " + id);
		Alias alias = aliasService.getAliasById(id);
		IncidentKey key = new IncidentKey(alias.getCategory(),
				alias.getCreatedby(), alias.getCreatedon());
		Incident incident = incidentService.getIncidentById(key);
		if (incident == null) {
			return null;
		}
		// incidentVO.setUpdatedOn(alias.getCreatedon());
		// incidentVO.setCreatedBy(alias.getCreatedby());
		// incidentVO.setType(alias.getCategory());
		incidentService.updateIncident(new Incident(incidentVO));
		return new ResponseEntity<IncidentVO>(incidentVO, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/incidents/{incidentID}", produces = "application/json", method = RequestMethod.DELETE)
	public void deleteIncidentType(@PathVariable("incidentID") String incidentID)
			throws JsonParseException, JsonMappingException, IOException {
		Alias alias = aliasService.getAliasById(incidentID);
		IncidentKey key = new IncidentKey(alias.getCategory(),
				alias.getCreatedby(), alias.getCreatedon());
		incidentService.deleteIncidentById(key);
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
