package com.example.project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/gallery")
public class AdminController {
	@Autowired
	private PhotoService photoServiceData;
	@Autowired
	private AdminService adminServiceData;
	@GetMapping("/panel")
	public String getPanel(Model model) {
	  model.addAttribute("list", photoServiceData.getPhotos());
	  model.addAttribute("logged", adminServiceData.isLogged());
	  return "panel";
	}
	@GetMapping("/delete/{index}")
	public String delete2(@PathVariable int index) throws IOException {
		if(adminServiceData.isLogged())
		{
			Photo photo = photoServiceData.getPhotoByIndex(index);
			if(photo!=null)
				Files.deleteIfExists(Paths.get(photoServiceData.getPhotoPath()+photo.getName()));
			boolean result = photoServiceData.deletePhotoByIndex(index);
		}
		return "redirect:/gallery/panel";
	}
	@PostMapping("/panel/login")
	public String login(String login, String password)
	{
		adminServiceData.login(login, password);
		return "redirect:/gallery/panel";
	}
	@PostMapping("/panel/logout")
	public String logout()
	{
		adminServiceData.logout();
		return "redirect:/gallery/panel";
	}
	@PostMapping("/panel/upload")
	public String fileUpload(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
		try {
			File fileToSave = new File(photoServiceData.getPhotoPath() + file.getOriginalFilename());
			file.transferTo(fileToSave);
			photoServiceData.addPhotoToList(fileToSave);
			
			
		} catch (IOException e) {}
		
	}return "redirect:/gallery/panel";}
}

