package com.example.render.uploads;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.render.entity.statics.StaticsFile;



@Service
public class AWSStaticFileUpload {

	
	@Autowired
	private AmazonS3 s3client;
	
	
	
	@Value("${amazon.s3.static.bucket}")
	private String bucketName;
	
	
	public ArrayList<StaticsFile> multipleUploads(MultipartFile[] files)throws IOException {
		ArrayList<StaticsFile> fileArray = new ArrayList<StaticsFile>();
        StaticsFile sf = new StaticsFile();
        
		if(!files[0].isEmpty()) {
		String[] type = files[0].getContentType().split("/");
		
		System.out.print("type is "+type);
		if(type[0].equalsIgnoreCase("image")) {
			int	fileLength;
			if(files.length <= 4) {
				fileLength = files.length;
			}
			
			else {
				fileLength = 4;
			}
			
			int i = 0;

		while( i < fileLength) {
			System.out.println("index going on"+ i);
//			String ext = files[i].getContentType().split("/")[1];
			long fsize = files[i].getSize();
			
			if(fsize < 1000*1000*100) {
				try {
					String newName = generateFileName(files[i]);
					 InputStream is = new  BufferedInputStream(files[i].getInputStream());

					uploadFileTos3bucket(newName, is);
					sf.setFile(newName);
					sf.setFiletype("image");
					fileArray.add(sf);
					
				} catch (AmazonServiceException ase) {
			           System.out.println("amazon service exception "+ase);

		        } catch (AmazonClientException ace) {
		           System.out.println("amazon client exception "+ace);
		        }
				
				

			}
			i++;
			
		}
			
		
		return fileArray;

		}
		
		}
		return null;
		}
		
	
       public ArrayList<StaticsFile> uploadFile2(MultipartFile file2)throws IOException {
    	   ArrayList<StaticsFile> fileArray = new ArrayList<StaticsFile>();
           StaticsFile sf = new StaticsFile();
		if(!file2.isEmpty()) {
			String filename = file2.getOriginalFilename();
			String context = file2.getContentType();
			String ext = FilenameUtils.getExtension(filename);
//			String date = String.valueOf(new Date().getTime());
			String[] contxt = context.split("/");
			if((contxt[0].equalsIgnoreCase("video")) || (contxt[0].equalsIgnoreCase("application"))){
				long fsize = file2.getSize();
				if(fsize < 1000*1000*450) {
					try {
//						File file = convertMultiPartToFile(file2);
						String newName = generateFileName(file2);
						 InputStream is = new  BufferedInputStream(file2.getInputStream());

						uploadFileTos3bucket(newName, is);
						
						sf.setFile(newName);
						sf.setFiletype("video");
						
						fileArray.add(sf);
					} catch (AmazonServiceException ase) {
				           System.out.println("amazon service exception "+ase);

			        } catch (AmazonClientException ace) {
			           System.out.println("amazon client exception "+ace);
			        }
					
			
			
//			String[] filelist = {newFilename, type};
			
			
		
		return fileArray;
				}
			}
		}
		return null;
		
	}	
	
       
       
       
       private String generateFileName(MultipartFile multiPart) {
    		 UUID uuid = UUID.randomUUID();
    		 String filename = multiPart.getOriginalFilename();
    			String ext = FilenameUtils.getExtension(filename);
    			String newName = uuid.toString()+"_"+new Date().getTime()+"."+ext;
    			
    			return newName;
    		}
    		
    		
    		private void uploadFileTos3bucket(String fileName, InputStream is) throws IOException {
    			ObjectMetadata meta = new ObjectMetadata();
    			byte[] byteArray =  new byte[is.available()];
    			 System.out.println("data lenght is "+ byteArray.length);
    			meta.setContentLength(byteArray.length);
    			s3client.putObject(new PutObjectRequest(bucketName, fileName, is, meta).withCannedAcl(CannedAccessControlList.PublicRead));

    		}
	
	
}
