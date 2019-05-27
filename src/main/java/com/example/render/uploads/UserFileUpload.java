package com.example.render.uploads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserFileUpload {

	private String imgpath="C:/Users/APIL/Documents/workspace-sts-3.9.2.RELEASE/render/src/main/resources/static/img/";
	
	public String fileUpload(MultipartFile file) throws IOException{
		if(file != null) {
			String[] type = file.getContentType().split("/");
			
			if(type[0].equalsIgnoreCase("image")) {
				String date = String.valueOf(new Date().getTime());
				String ext = file.getContentType().split("/")[1];
				long fsize = file.getSize();
				
				if(fsize < 1000*1000*100) {
					String newName = date+"."+ext;
					Files.copy(file.getInputStream(), Paths.get(imgpath+newName), StandardCopyOption.REPLACE_EXISTING);
					
					return newName;
				}
				
				
			}
		}
		
		return null;
	}
}
