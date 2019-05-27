package com.example.render.uploads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.render.entity.statics.StaticsFile;


@Service
public class StaticsFileUpload {

	private String imgpath="C:/Users/APIL/Documents/workspace-sts-3.9.2.RELEASE/render/src/main/resources/static/img/";
	
	public ArrayList<StaticsFile> multipleUploads(MultipartFile[] files)throws IOException {
		ArrayList<StaticsFile> fileArray = new ArrayList<StaticsFile>();
        StaticsFile sf = new StaticsFile();
        
		if(files[0].isEmpty()) {
		String[] type = files[0].getContentType().split("/");
		
		System.out.print("type is "+type);
		if(type[0].equalsIgnoreCase("image")) {
		for(MultipartFile file: files) {
			String date = String.valueOf(new Date().getTime());
			String ext = file.getContentType().split("/")[1];
			long fsize = file.getSize();
			
			if(fsize < 1000*1000*100) {
				String newName = date+"."+ext;
				Files.copy(file.getInputStream(), Paths.get(imgpath+newName), StandardCopyOption.REPLACE_EXISTING);
				
				sf.setFile(newName);
				sf.setFiletype("image");
				fileArray.add(sf);
				
				return fileArray;

			}
			
		}
		}
		
		}
		return null;
		}
		
	
       public ArrayList<StaticsFile> uploadFile2(MultipartFile file2)throws IOException {
    	   ArrayList<StaticsFile> fileArray = new ArrayList<StaticsFile>();
           StaticsFile sf = new StaticsFile();
		if(file2.isEmpty()) {
			String filename = file2.getOriginalFilename();
			String context = file2.getContentType();
			String ext = FilenameUtils.getExtension(filename);
			String date = String.valueOf(new Date().getTime());
			String newName = date+"."+ext;
			String[] contxt = context.split("/");
			if((contxt[0].equalsIgnoreCase("video")) || (contxt[0].equalsIgnoreCase("application"))){
				long fsize = file2.getSize();
				if(fsize < 1000*1000*450) {
			Files.copy(file2.getInputStream(), Paths.get(imgpath+newName), StandardCopyOption.REPLACE_EXISTING);
			String newFilename = newName;
			String type = contxt[0];
			
			
//			String[] filelist = {newFilename, type};
			
			
		sf.setFile(newFilename);
		sf.setFiletype("video");
		
		fileArray.add(sf);
		return fileArray;
				}
			}
		}
		return null;
		
	}	
	
}
