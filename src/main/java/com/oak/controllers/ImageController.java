package com.oak.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oak.service.ImageService;
import com.oak.vo.ImageUploadResponse;
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
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		//System.out.println(image.getSize() + image.getOriginalFilename());
		//System.out.println(Arrays.toString(image.getBytes()));	
				
		//TODO : Get username from session
		String id = imageService.saveImage(prefix, image.getOriginalFilename(),
				image.getSize(), image.getBytes(),email);
		return id;
	}
	
	@CrossOrigin//(methods={RequestMethod.POST,RequestMethod.OPTIONS,RequestMethod.GET})
	@RequestMapping(value = "/ckimages/{prefix}", method = RequestMethod.POST)
	public String uploadCKImage(@RequestParam("upload") MultipartFile image,
			@PathVariable String prefix) throws IOException {

		//System.out.println(image.getSize() + image.getOriginalFilename());
		//System.out.println(Arrays.toString(image.getBytes()));	
		
		String fname = image.getOriginalFilename();
		//TODO : Get username from session
		String id = imageService.saveImage(prefix, fname,
				image.getSize(), image.getBytes(),"test");
		ImageUploadResponse response = new ImageUploadResponse();
		response.setUploaded(1);
		response.setFilename(fname);
		response.setUrl("http://www.ipledge2nieria.com/service/image/"+id);
		return "http://www.ipledge2nieria.com/service/image/"+id;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/image/{id:.+}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] downloadPicture(@PathVariable String id) throws IOException {
		byte[] content = imageService.getImage(id);		
		return content;
	}

}
