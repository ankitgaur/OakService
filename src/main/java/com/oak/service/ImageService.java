package com.oak.service;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.Image;
import com.oak.entities.ImageKey;
import com.oak.repositories.ImageRepo;
import com.oak.vo.ImageVO;

@Service("imageService")
public class ImageService {

	@Autowired
	ImageRepo imageRepo;

	public List<ImageVO> getImagesForPrefix(String prefix) {
		List<ImageVO> imgvos = new ArrayList<ImageVO>();

		List<Image> imgs = imageRepo.getImagesForPrefix(prefix, 100);

		for (Image img : imgs) {
			imgvos.add(new ImageVO(img));
		}

		return imgvos;
	}

	public List<String> getPrefixesForUser(String user){
		return imageRepo.getPrefixForUser(user);
	}
	
	public List<ImageVO> getRecentImages() {
		
		List<ImageVO> imgvos = new ArrayList<ImageVO>();

		List<Image> imgs = imageRepo.getRecentImages(100);

		for (Image img : imgs) {
			imgvos.add(new ImageVO(img));
		}

		return imgvos;
	}

	public String saveImage(String prefix, String name, long size,
			byte[] content, String createdBy) {

		ImageKey key = new ImageKey();
		key.setCreatedOn(new Date().getTime());
		key.setPrefix(prefix);
		

		String id = key.getPrefix() + "_" + key.getCreatedOn();

		Image image = new Image();
		image.setKey(key);
		image.setName(name);
		image.setKbsize(size);
		ByteBuffer buf = ByteBuffer.wrap(content);
		image.setImg(buf);
		image.setCreatedBy(createdBy);
		imageRepo.createImage(image);

		return id;
	}

	public byte[] getImage(String id) {
		
		String []arr = id.split("_");
		
		ImageKey key = new ImageKey();
		key.setPrefix(arr[0]);
		key.setCreatedOn(Long.parseLong(arr[1]));
		
		
		Image img = imageRepo.getImage(key);
		ByteBuffer buf = img.getImg();
		byte[] bytes = new byte[buf.remaining()];
		buf.get(bytes, 0, bytes.length);

		return bytes;
	}
}
