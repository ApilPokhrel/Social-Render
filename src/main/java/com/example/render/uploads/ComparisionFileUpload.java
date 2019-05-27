package com.example.render.uploads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.jni.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ComparisionFileUpload {
	private String imgpath="C:/Users/APIL/Documents/workspace-sts-3.9.2.RELEASE/render/src/main/resources/static/img/";
	
	public String[] uploadFile1(MultipartFile file1) throws IOException {
		if(file1.isEmpty()) {
			String filename = file1.getOriginalFilename();
			String context = file1.getContentType();
			String ext = FilenameUtils.getExtension(filename);
			String date = String.valueOf(new Date().getTime());
			String newName = date+"."+ext;
			String[] contxt = context.split("/");
			System.out.println(contxt);

			String[] filelist = getStrings(file1, newName, contxt);
			if (filelist != null) return filelist;
		}
		return null;
	}

	private String[] getStrings(MultipartFile file1, String newName, String[] contxt) throws IOException {
		if((contxt[0].equalsIgnoreCase("image")) || (contxt[0].equalsIgnoreCase("video")) || (contxt[0].equalsIgnoreCase("application"))){
            long fsize = file1.getSize();
            if(fsize < 1000*1000*450) {
            Files.copy(file1.getInputStream(), Paths.get(imgpath+newName), StandardCopyOption.REPLACE_EXISTING);
            String newFilename = newName;
            String type = contxt[0];


            String[] filelist = {newFilename, type};


            return filelist;
            }


        }
		return null;
	}


	public String[] uploadFile2(MultipartFile file2)throws IOException {
		
		if(file2.isEmpty()) {
			String filename = file2.getOriginalFilename();
			String context = file2.getContentType();
			String ext = FilenameUtils.getExtension(filename);
			String date = String.valueOf(new Date().getTime());
			String newName = date+"."+ext;
			String[] contxt = context.split("/");
			String[] filelist = getStrings(file2, newName, contxt);
			if (filelist != null) return filelist;
		}
		return null;
		
	}
}
