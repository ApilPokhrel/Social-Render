package com.example.render.uploads;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service()
public interface ComparisionFileUploadInterface {
	public CompletableFuture<String[]> uploadFile1(MultipartFile file1) throws IOException;
	public CompletableFuture<String[]> uploadFile2(MultipartFile file2) throws IOException;
	
}
