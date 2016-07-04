package com.oak.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oak.entities.Image;
import com.oak.entities.ImageKey;
import com.oak.utils.OakCassandraTemplate;

@Repository("imageRepo")
public class ImageRepo {

	@Autowired
	private OakCassandraTemplate oakCassendraTemplate;

	public List<String> getPrefixForUser(String user) {
		String prefixes_for_user = "Select distinct prefix from images";
		List<String> prefixes = oakCassendraTemplate.findByPartitionKey(
				prefixes_for_user, String.class);
		return prefixes;
	}

	public List<Image> getImagesForPrefix(String prefix, int limit) {
		String images_by_prefix = "SELECT prefix,alias,name,kbsize,createdby,createdon FROM images WHERE prefix = '"
				+ prefix + "' LIMIT " + limit;

		List<Image> images = oakCassendraTemplate.findByPartitionKey(
				images_by_prefix, Image.class);
		return images;
	}

	public List<Image> getRecentImages(int limit) {
		String images_by_prefix = "SELECT prefix,alias,name,kbsize,createdby,createdon FROM images LIMIT "
				+ limit;

		List<Image> images = oakCassendraTemplate.findByLimit(
				images_by_prefix, Image.class);
		return images;
	}

	public void createImage(Image image) {
		oakCassendraTemplate.create(image, Image.class);
	}

	public Image getImage(ImageKey key) {
		Image image = oakCassendraTemplate.findById(key, Image.class);
		return image;
	}

}
