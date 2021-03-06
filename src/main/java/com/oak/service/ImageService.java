package com.oak.service;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oak.entities.Alias;
import com.oak.entities.Image;
import com.oak.entities.ImageKey;
import com.oak.repositories.ImageRepo;
import com.oak.vo.ImageVO;

@Service("imageService")
public class ImageService {

	@Autowired
	ImageRepo imageRepo;
	
	@Autowired
	AliasService aliasService;

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

		long createdOn = new Date().getTime();
		ImageKey key = new ImageKey();
		key.setCreatedOn(createdOn);
		key.setPrefix(prefix);
		key.setCreatedBy(createdBy);

		Image image = new Image();
		image.setKey(key);
		image.setName(name);
		image.setKbsize(size);
		ByteBuffer buf = ByteBuffer.wrap(content);
		image.setImg(buf);
		
		UUID uuid = UUID.randomUUID();
		Alias alias = new Alias();
		alias.setId(uuid.toString());
		alias.setCategory(prefix);
		alias.setCreatedby(createdBy);
		alias.setCreatedon(createdOn);
		aliasService.createAlias(alias);
			
		image.setAlias(alias.getId());
		
		imageRepo.createImage(image);

		return alias.getId();
	}

	public byte[] getImage(String id) {
		
		Alias alias = aliasService.getAliasById(id);
				
		ImageKey key = new ImageKey();
		key.setPrefix(alias.getCategory());
		key.setCreatedOn(alias.getCreatedon());
		key.setCreatedBy(alias.getCreatedby());
				
		Image img = imageRepo.getImage(key);
		ByteBuffer buf = img.getImg();
		byte[] bytes = new byte[buf.remaining()];
		buf.get(bytes, 0, bytes.length);

		return bytes;
	}
}
