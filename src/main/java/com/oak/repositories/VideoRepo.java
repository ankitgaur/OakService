package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Video;
import com.oak.entities.VideoKey;
import com.oak.utils.OakCassandraTemplate;

@Repository("videoRepo")
@Transactional
public class VideoRepo {

	@Autowired
	private OakCassandraTemplate oakCassendraTemplate;

	public List<Video> getVideos() {
		List<Video> videos = oakCassendraTemplate.findAll(Video.class);
		return videos;
	}

	public List<Video> getTopVideosByCategory(String category, int limit) {
		String video_by_category_qry = "SELECT * FROM videos WHERE category=";
		video_by_category_qry = video_by_category_qry + "'" + category
				+ "'" + " LIMIT " + limit;
		System.out.println("ARTICLE_BY_CATEGORY_QRY ::: "
				+ video_by_category_qry);
		List<Video> videos = oakCassendraTemplate.findByPartitionKey(
				video_by_category_qry, Video.class);
		return videos;
	}

	public List<Video> getTopVideosByLimit(int limit) {
		String video_by_limt_qry = "SELECT * FROM videos LIMIT ";
		video_by_limt_qry = video_by_limt_qry + limit;
		System.out.println("ARTICLE_BY_CATEGORY_QRY ::: "
				+ video_by_limt_qry);
		List<Video> videos = oakCassendraTemplate.findByLimit(
				video_by_limt_qry, Video.class);
		return videos;
	}

	public Video getVideoById(VideoKey id) {
		Video video = oakCassendraTemplate.findById(id, Video.class);
		return video;
	}

	public void deleteVideoById(VideoKey id) {

		oakCassendraTemplate.deleteById(id, Video.class);

	}

	public void createVideo(Video video) {

		oakCassendraTemplate.create(video, Video.class);

	}

	public void updateVideo(Video video) {

		oakCassendraTemplate.update(video, Video.class);

	}

}
