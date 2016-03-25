package com.oak.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oak.service.ImageService;
import com.oak.vo.ImageVO;

@RestController
public class ImageController {

	@Autowired
	ImageService imageService;

	@CrossOrigin
	@RequestMapping(value = "/images", method = RequestMethod.GET)
	public List<ImageVO> getRecentImages() throws IOException {
	
		List<ImageVO> images = imageService.getRecentImages();
		return images;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/images/prefixes", method = RequestMethod.GET)
	public List<String> getPrefixes() throws IOException {
	
		//TODO : Get username from session
		List<String> prefixes = imageService.getPrefixesForUser("test");
		return prefixes;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/images/{prefix}", method = RequestMethod.GET)
	public List<ImageVO> getImagesForPrefix(@PathVariable String prefix) throws IOException {

		List<ImageVO> images = imageService.getImagesForPrefix(prefix);
		return images;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/images/{prefix}", method = RequestMethod.POST)
	public String uploadImage(@RequestParam("file") MultipartFile image,
			@PathVariable String prefix) throws IOException {

		//System.out.println(image.getSize() + image.getOriginalFilename());
		//System.out.println(Arrays.toString(image.getBytes()));	
				
		//TODO : Get username from session
		String id = imageService.saveImage(prefix, image.getOriginalFilename(),
				image.getSize(), image.getBytes(),"test");
		return id;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/image/{id:.+}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] downloadPicture(@PathVariable String id) throws IOException {
		byte[] content = imageService.getImage(id);		
		return content;
	}

}
