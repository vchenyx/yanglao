package com.common.util;


import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadFile {
	
	//单一文件上传
	public static void uploadOneFile(HttpServletRequest request,MultipartFile myfile,String realPath){
		if(myfile.isEmpty())return;	
        try {
        	String filename=myfile.getOriginalFilename().substring(myfile.getOriginalFilename().lastIndexOf("/")+1).toLowerCase();
			FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, filename));
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
}
