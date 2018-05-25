package com.example.project;

import javax.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//@EnableConfigurationProperties
@ConfigurationProperties(prefix="my")
public class GlobalProperties {
	
	@NotNull private String imagePath;
	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}
	public String getImagePath()
	{
		return imagePath;
	}

}