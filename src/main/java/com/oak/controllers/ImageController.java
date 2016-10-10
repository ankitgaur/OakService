package com.oak.controllers;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

		// TODO : Get username from session
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
	@Secured("ROLE_image_write")
	@RequestMapping(value = "/images/{prefix}", method = RequestMethod.POST)
	public String uploadImage(@RequestParam("file") MultipartFile image, @PathVariable String prefix)
			throws IOException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		// System.out.println(image.getSize() + image.getOriginalFilename());
		// System.out.println(Arrays.toString(image.getBytes()));

		// TODO : Get username from session
		String id = imageService.saveImage(prefix, image.getOriginalFilename(), image.getSize(), image.getBytes(),
				email);
		return id;
	}

	@CrossOrigin // (methods={RequestMethod.POST,RequestMethod.OPTIONS,RequestMethod.GET})
	@Secured("ROLE_image_write")
	@RequestMapping(value = "/ckimages/{prefix}", method = RequestMethod.POST)
	public String uploadCKImage(@RequestParam("upload") MultipartFile image, @PathVariable String prefix,
			HttpServletRequest request) throws IOException {

		String funcNum = request.getParameter("CKEditorFuncNum");

		// System.out.println(image.getSize() + image.getOriginalFilename());
		// System.out.println(Arrays.toString(image.getBytes()));

		String fname = image.getOriginalFilename();
		// TODO : Get username from session
		String id = imageService.saveImage(prefix, fname, image.getSize(), image.getBytes(), "test");

		String imgurl = "http://www.ipledge2nigeria.com/service/image/" + id;

		String message = "Image was uploaded successfully";
		String resp = "<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(" + funcNum + ", '"
				+ imgurl.toString().trim() + "', '" + message + "');</script>";

		return resp;
	}

	@CrossOrigin
	@RequestMapping(value = "/image/{id:.+}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] downloadPicture(@PathVariable String id) throws IOException {
		byte[] content = imageService.getImage(id);
		return content;
	}

	@CrossOrigin // (methods={RequestMethod.POST,RequestMethod.OPTIONS,RequestMethod.GET})
	@Secured("ROLE_image_read")
	@RequestMapping(value = "/ckimages", method = RequestMethod.GET)
	public String browseImages(HttpServletRequest request) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		System.out.println(authentication.getName());
		for(GrantedAuthority auth:authorities){
			System.out.println(auth.toString());
		}
		
		List<ImageVO> images = imageService.getRecentImages();
		
		String funcNum = request.getParameter("CKEditorFuncNum");
		
		StringBuilder htmlBuilder = new StringBuilder("<html><head>");

		htmlBuilder.append("<style> div { font-size: 0; }");

		htmlBuilder.append(
				" a { font-size: 16px; display: inline-block; margin-bottom: 8px; width: calc(50% - 4px); margin-right: 8px;}");

		htmlBuilder.append(" a:nth-of-type(2n) { margin-right: 0; }");

		htmlBuilder.append(
				" @media screen and (min-width: 50em) { a { width: calc(25% - 6px); } a:nth-of-type(2n) { margin-right: 8px; } a:nth-of-type(4n) { margin-right: 0; } }");

		htmlBuilder.append("</style><script>");

		htmlBuilder.append("function returnFileUrl(fileUrl) { var funcNum = ");
		htmlBuilder.append(funcNum);

		htmlBuilder.append("; window.opener.CKEDITOR.tools.callFunction( funcNum, fileUrl ); window.close();}");
		htmlBuilder.append("</script></head><body>");

		String imgurl = "http://www.ipledge2nigeria.com/service/image/";

		for (ImageVO img : images) {
			String thisimgurl = imgurl + img.getId();
			htmlBuilder.append("<div><a onclick='returnFileUrl(\"" + thisimgurl + "\")'><figure><img src='" + thisimgurl
					+ "' style='width: 300px;'></figure></a></div>");

		}

		htmlBuilder.append("</body></html>");
		return  htmlBuilder.toString();
	}

}
