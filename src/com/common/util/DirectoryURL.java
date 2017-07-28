//package com.common.util;
//
//import java.io.File;
//
//import javax.servlet.http.HttpServletRequest;
//
//public class DirectoryURL {
//	
//	private final static String people_url="people";
//	private final static String inout_url="inout";
//	private final static String model_file_url = "modelfile";
//	
//	private final static String safety_file_url = "safety";
//
//	
///*
// * 	命名规则：	1.人员进出报表：/people/inout/
// * 			2.安措费表:/safety/project_id/
//*/
//	
//	//人员进出报表路径
//	public final static String peopleInoutUrl(String projectId){
//		return storeURL(getPeopleUrl()+getInoutUrl()+prefixURL(projectId));
//	}
//	
//	//安措费报表路径
//	public final static String safetyUrl(String projectId){
//		return storeURL(getSafetyFileUrl()+prefixURL(projectId));
//	}
//	
//	//下载文件路径
//	public final static String downFileUrl(){
//		return storeURL(getModelFileUrl());
//	}
//	
//	
//	//创建文件
//	public final static String creatDirectory(HttpServletRequest request,String storeurl){
//		String path=request.getServletContext().getRealPath(storeURL(storeurl));
//		File file=new File(path);
//		if(!file.exists()) {
//			file.mkdirs();
//		}
//		return path;
//	}
//	
//	//加上前后缀
//	private final static String storeURL(String storeurl){
//		if(storeurl.charAt(0)!='/')storeurl="/"+storeurl;
//		if(storeurl.charAt(storeurl.length()-1)!='/')storeurl+="/";
//		return storeurl;
//	}
//	//只加前缀
//	private final static String prefixURL(String storeurl){
//		if(storeurl.charAt(0)!='/')storeurl="/"+storeurl;
//		if(storeurl.charAt(storeurl.length()-1)=='/')storeurl=storeurl.substring(0,storeurl.length()-1);
//		return storeurl;
//	}
//
//	public static String getPeopleUrl() {
//		return prefixURL(people_url);
//	}
//
//	public static String getInoutUrl() {
//		return prefixURL(inout_url);
//	}
//
//	public static String getModelFileUrl() {
//		return prefixURL(model_file_url);
//	}
//
//	public static String getSafetyFileUrl() {
//		return prefixURL(safety_file_url);
//	}
//
//	
//}
