package com.example.render.uploads;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;


@Service
public class AWSComparisionFileUpload implements ComparisionFileUploadInterface{

	
	
	
	@Autowired
	private AmazonS3 s3client;
	
	
	
	@Value("${amazon.s3.comparision.bucket}")
	private String bucketName;
	
	
	
	
	@Override
	@Async
	public CompletableFuture<String[]> uploadFile1(MultipartFile file1) throws IOException {
		
		System.out.println("first one is running");
		
		if(!file1.isEmpty()) {
			String filename = null;
			String context = file1.getContentType();
			
		
			String[] contxt = context.split("/");
			System.out.println(contxt);
			
			if((contxt[0].equalsIgnoreCase("image")) || (contxt[0].equalsIgnoreCase("video")) || (contxt[0].equalsIgnoreCase("application"))){
				long fsize = file1.getSize();
				if(fsize < 1000*1000*450) {
					try {
//						File file = convertMultiPartToFile(file1);
						 filename = generateFileName(file1);
						 InputStream is = new  BufferedInputStream(file1.getInputStream());

						uploadFileTos3bucket(filename, is);
					} catch (AmazonServiceException ase) {
				           System.out.println("amazon service exception "+ase);

			        } catch (AmazonClientException ace) {
			           System.out.println("amazon client exception "+ace);
			        }
				String type = contxt[0];
				
				
				String[] filelist = {filename, type};
				System.out.println("file1 "+filename);
				
				return CompletableFuture.completedFuture(filelist);
				}
				
				
			}
		}
		return null;
	}


 @Override
 @Async
	public CompletableFuture<String[]> uploadFile2(MultipartFile file2) throws IOException {
		System.out.println("second one is running");

		if(!file2.isEmpty()) {
			String filename = null;
			String context = file2.getContentType();
			
			String[] contxt = context.split("/");
			if((contxt[0].equalsIgnoreCase("image")) || (contxt[0].equalsIgnoreCase("video")) || (contxt[0].equalsIgnoreCase("application"))){
				long fsize = file2.getSize();
				if(fsize < 1000*1000*450) {

					try {
//						File file = convertMultiPartToFile(file2);
						 filename = generateFileName(file2);
						 InputStream is = new  BufferedInputStream(file2.getInputStream());

						uploadFileTos3bucket(filename, is);
					} catch (AmazonServiceException ase) {
				           System.out.println("amazon service exception "+ase);

			        } catch (AmazonClientException ace) {
			           System.out.println("amazon client exception "+ace);
			        }

			String type = contxt[0];
			String[] filelist = {filename, type};
			
			System.out.println("file2 "+filename);

			return CompletableFuture.completedFuture(filelist);
				}
			}
		}
		return null;
		
	}
	
 private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
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
