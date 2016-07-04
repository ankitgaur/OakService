package com.oak.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Alias;
import com.oak.entities.Video;
import com.oak.entities.VideoKey;
import com.oak.repositories.VideoRepo;

@Service("videoService")
public class VideoService {

	@Autowired
	VideoRepo videoRepo;
	
	@Autowired
	AliasService aliasService;

	public List<Video> getVideos() {

		return videoRepo.getVideos();

	}

	public List<Video> getTopVideosByCategory(String category, int limit) {

		System.out.println("videos by limit");
		return videoRepo.getTopVideosByCategory(category, limit);

	}

	public List<Video> getTopVideosByLimit(int limit) {

		return videoRepo.getTopVideosByLimit(limit);

	}

	public Video getVideoById(VideoKey videoID) {

		return videoRepo.getVideoById(videoID);
	}

	public void deleteVideoById(VideoKey videoID) {

		videoRepo.deleteVideoById(videoID);
	}

	@Transactional
	public void createVideo(Video video) {

		UUID uuid = UUID.randomUUID();
		Alias alias = new Alias();
		alias.setId(uuid.toString());
		alias.setCategory(video.getPk().getCategory());
		alias.setCreatedby(video.getPk().getCreatedBy());
		alias.setCreatedon(video.getPk().getCreatedOn());
		aliasService.createAlias(alias);
			
		video.setAlias(alias.getId());
		videoRepo.createVideo(video);

	}

	@Transactional
	public void updateVideo(Video video) {

		videoRepo.updateVideo(video);
	}

}
