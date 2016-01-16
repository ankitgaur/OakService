package com.oak.controllers;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oak.service.ImageService;

@RestController
public class ImageController {

	@Autowired
	ImageService imageService;

	@RequestMapping(value = "/image/{prefix}", method = RequestMethod.POST)
	public String uploadImage(@RequestParam("file") MultipartFile image,
			@PathVariable String prefix) throws IOException {

		System.out.println(image.getSize() + image.getOriginalFilename());
		System.out.println(Arrays.toString(image.getBytes()));
		
		int end = image.getOriginalFilename().indexOf(".");
		String fname = image.getOriginalFilename().substring(0,end);
		
		String id = prefix + "_" + fname;
		imageService.saveImage(id, image.getOriginalFilename(),
				image.getSize(), image.getBytes());
		return id;
	}
	
	@RequestMapping(value = "/image/{id:.+}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] downloadPicture(@PathVariable String id) throws IOException {
		byte[] content = imageService.getImage(id);
		System.out.println(Arrays.toString(content));
		return content;
	}

}
