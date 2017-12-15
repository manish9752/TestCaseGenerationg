package com.assignment.TDDTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadJunitTestHelper  {

	public  static  MultipartFile getMultipartData(String  path,String name,String originalFileName,String contentType,byte[] content ) {
	
		try {
		    content = Files.readAllBytes(Paths.get(path));
		} catch (final IOException e) {
		}
		MultipartFile result = new MockMultipartFile(name, originalFileName, contentType, content);
		return result;
	}

	

	 

	 

}
