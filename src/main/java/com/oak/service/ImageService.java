package com.oak.service;

import java.nio.ByteBuffer;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.Image;
import com.oak.repositories.ImageRepo;

@Service("imageService")
public class ImageService {
	
	@Autowired
	ImageRepo imageRepo;
	
	public String saveImage(String id, String name, long size,byte[] content){
		
		Image image = new Image();
		image.setId(id);
		image.setName(name);
		image.setKbsize(size);
		//TODO : Get username from session
		image.setCreatedBy("imgctrl");
		ByteBuffer buf = ByteBuffer.wrap(content);
		image.setImg(buf);
		image.setCreatedOn(new Date().getTime());
		imageRepo.createImage(image);
		
		return id;		
	}	
	
	public byte[] getImage(String id){
		Image img = imageRepo.getImageById(id);
		ByteBuffer buf = img.getImg();
		byte[] bytes = new byte[buf.remaining()];
		buf.get(bytes, 0, bytes.length);
			
		return bytes;
	}
}
