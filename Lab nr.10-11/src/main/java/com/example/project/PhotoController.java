package com.example.project;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/gallery")
public class PhotoController {

	@Autowired
	private PhotoService photoServiceData;

	@GetMapping("/pictures")
	@ResponseBody
	public List<Photo> index() {
		return photoServiceData.getPhotos();
	}
	@GetMapping("/upload")
	public String html() {
		return "upload";
	}
	@PostMapping("/upload")
	public ResultMessage fileUpload(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return new ResultMessage(false);
		}
		try {
			File fileToSave = new File(photoServiceData.getPhotoPath() + file.getOriginalFilename());
			file.transferTo(fileToSave);
			photoServiceData.addPhotoToList(fileToSave);
		} catch (IOException e) {
			return new ResultMessage(false);
		}
		return new ResultMessage(true);
	}
	@DeleteMapping("/picture/{index}")
	@ResponseBody
	public ResultMessage delete(@PathVariable int index) throws IOException {
		Photo photo = photoServiceData.getPhotoByIndex(index);
		if(photo!=null)
			Files.deleteIfExists(Paths.get(photoServiceData.getPhotoPath()+photo.getName()));
		boolean result = photoServiceData.deletePhotoByIndex(index);
		return new ResultMessage(result);
	}
	@GetMapping(value = "/picture/{index}", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public ResponseEntity<InputStreamResource> getImage(@PathVariable int index) throws IOException {
		Photo photo = photoServiceData.getPhotoByIndex(index);
		if (photo != null) {
			File file = new File(photoServiceData.getPhotoPath() + photo.getName());
			InputStream image = new FileInputStream(file);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(image));
		}
		return null;
	}
	////////////////////////////
	////////////////////////////
	////////////////////////////
	@GetMapping("/")
	public String getGallery(Model model) {
	  model.addAttribute("list", photoServiceData.getPhotos());
	  return "gallery";
	}
	
}