package com.oak.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oak.entities.Image;
import com.oak.utils.OakCassandraTemplate;

@Repository("imageRepo")
public class ImageRepo {

	@Autowired
	private OakCassandraTemplate oakCassendraTemplate;
	
	public void createImage(Image image) {

		oakCassendraTemplate.create(image, Image.class);

	}
	
	public Image getImageById(String id) {
		Image image = oakCassendraTemplate.findById(id, Image.class);
		return image;
	}
	
}
