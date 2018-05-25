package com.example.project;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
	private List<Photo> photos;
	int index = 1;
	@Autowired
	private GlobalProperties global;

	@PostConstruct
	public void setUpClass() throws IOException {
		photos = new ArrayList<>();
		File folder = new File(getPhotoPath());
		File[] listOfFiles = folder.listFiles();
	
		if (listOfFiles != null) {
			for (File file : listOfFiles) {
				addPhotoToList(file);
			}
		}
	}
	public void addPhotoToList(File file) {
		BasicFileAttributes attributes;
		BufferedImage img;
		try {
			img = ImageIO.read(file);
			attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			FileTime creationTime = attributes.creationTime();
			long creation = creationTime.toMillis();
			photos.add(new Photo(index++, file.getName(),
					img.getHeight() + "x" + img.getWidth(),
					(int) file.length(), 
					creation));
		} catch (IOException e) {}
	}

	public String getPhotoPath() {
		return global.getImagePath();
	}

	public List<Photo> getPhotos() {
		return photos;
	}
	public Photo getPhotoByIndex(int index) {
		for (Photo photo : photos) {
			if (photo.getIndex() == index)
				return photo;
		}
		return null;
	}
	public List<String> getPaths()
	{
		List<String> paths = new ArrayList<>();
		for(Photo a: photos)
		{
			paths.add(global.getImagePath() + a.getName());
		}
		return paths;
	}
	public boolean deletePhotoByIndex(int index) {
		int photoIndex = -1;
		for (Photo photo : photos) {
			if (photo.getIndex() == index) {
				photoIndex = photos.indexOf(photo);
				break;
			}
		}
		if (photoIndex > -1) {
			photos.remove(photoIndex);
		} else {
			return false;
		}
		return true;
	}
}