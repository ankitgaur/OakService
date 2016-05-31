package com.oak.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oak.entities.Video;
import com.oak.entities.VideoKey;
import com.oak.service.VideoService;
import com.oak.vo.VideoVO;

@RestController
public class VideoController {

	@Autowired
	VideoService videoService;

	@CrossOrigin
	@RequestMapping(value = "/videos/{id}", produces = "application/json", method = RequestMethod.GET)
	public VideoVO getSticleById(@PathVariable String id)
			throws JsonProcessingException, ParseException {
		String videoKey[] = id.split("_");
		VideoKey key = new VideoKey(videoKey[0],
				Long.parseLong(videoKey[1]));
		Video video = videoService.getVideoById(key);
		VideoVO videoVO = new VideoVO(video);
		Date createDate = new Date(video.getUpdatedOn());
		Date updateDate = new Date(video.getPk().getCreatedOn());
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"dd-MM-yyyy HH:mm:ss");
		String dateCreateText = dateFormatter.format(createDate);
		String updateCreateText = dateFormatter.format(updateDate);
		videoVO.setCreatedOnDate(dateCreateText);
		videoVO.setUpdatedOnDate(updateCreateText);
		return videoVO;
	}

	@CrossOrigin
	@RequestMapping(value = "/videos", produces = "application/json", method = RequestMethod.GET)
	public List<VideoVO> getVideo() throws JsonProcessingException {
		List<Video> videos = videoService.getVideos();
		List<VideoVO> videoVO = new ArrayList<VideoVO>();
		for (Video video : videos) {
			VideoVO vo = new VideoVO(video);
			Date createDate = new Date(video.getUpdatedOn());
			Date updateDate = new Date(video.getPk().getCreatedOn());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String dateCreateText = dateFormatter.format(createDate);
			String updateCreateText = dateFormatter.format(updateDate);
			vo.setCreatedOnDate(dateCreateText);
			vo.setUpdatedOnDate(updateCreateText);
			videoVO.add(vo);
		}
		return videoVO;

	}

	@CrossOrigin
	@RequestMapping(value = "/videos/{category}/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<VideoVO> getVideoByCategory(
			@PathVariable("category") String category,
			@PathVariable("limit") int limit) throws JsonProcessingException {

		List<Video> videos = videoService.getTopVideosByCategory(
				category, limit);
		List<VideoVO> videoVO = new ArrayList<VideoVO>();
		for (Video video : videos) {
			VideoVO vo = new VideoVO(video);
			Date createDate = new Date(video.getUpdatedOn());
			Date updateDate = new Date(video.getPk().getCreatedOn());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String dateCreateText = dateFormatter.format(createDate);
			String updateCreateText = dateFormatter.format(updateDate);
			vo.setCreatedOnDate(dateCreateText);
			vo.setUpdatedOnDate(updateCreateText);
			videoVO.add(vo);
		}
		return videoVO;

	}

	@CrossOrigin
	@RequestMapping(value = "/videos/limit/{limit}", produces = "application/json", method = RequestMethod.GET)
	public List<VideoVO> getVideoByLimit(@PathVariable("limit") int limit)
			throws JsonProcessingException {

		List<Video> videos = videoService.getTopVideosByLimit(limit);
		List<VideoVO> videoVO = new ArrayList<VideoVO>();
		for (Video video : videos) {
			VideoVO vo = new VideoVO(video);
			Date createDate = new Date(video.getUpdatedOn());
			Date updateDate = new Date(video.getPk().getCreatedOn());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			String dateCreateText = dateFormatter.format(createDate);
			String updateCreateText = dateFormatter.format(updateDate);
			vo.setCreatedOnDate(dateCreateText);
			vo.setUpdatedOnDate(updateCreateText);
			videoVO.add(vo);
		}
		return videoVO;

	}

	@CrossOrigin
	@RequestMapping(value = "/videos/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<VideoVO> deleteRticle(@PathVariable("id") String id) {
		System.out.println("Fetching & Deleting User with id " + id);
		String videoKey[] = id.split("_");
		VideoKey key = new VideoKey(videoKey[0],
				Long.parseLong(videoKey[1]));
		videoService.deleteVideoById(key);
		return new ResponseEntity<VideoVO>(HttpStatus.NO_CONTENT);
	}

	@CrossOrigin
	@RequestMapping(value = "/videos", consumes = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Void> createVideo(@RequestBody VideoVO videoVO)
			throws ParseException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date dt = ft.parse(ft.format(dNow));
		videoVO.setCreatedOn(dt.getTime());
		videoVO.setUpdatedOn(dt.getTime());
		videoVO.setCreatedBy(email);
		videoService.createVideo(new Video(videoVO));
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(value = "/videos/{id}", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<VideoVO> updateVideo(
			@PathVariable("id") String videoID,
			@RequestBody VideoVO videoVO) throws ParseException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		
		System.out.println("Updating User " + videoID);
		String videoKey[] = videoID.split("_");
		VideoKey key = new VideoKey(videoKey[0],
				Long.parseLong(videoKey[1]));
		Video video = videoService.getVideoById(key);
		if (video == null) {
			System.out.println("Video with id " + videoID + " not found");
			return new ResponseEntity<VideoVO>(videoVO,
					HttpStatus.BAD_REQUEST);
		}
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date dt = ft.parse(ft.format(dNow));
		videoVO.setCreatedOn(video.getUpdatedOn());
		videoVO.setUpdatedOn(dt.getTime());
		videoVO.setUpdatedBy(email);
		videoService.updateVideo(new Video(videoVO));
		return new ResponseEntity<VideoVO>(videoVO, HttpStatus.OK);

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
