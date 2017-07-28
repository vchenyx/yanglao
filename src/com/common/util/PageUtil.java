package com.common.util;

public class PageUtil {
	
	public static final Integer currentpage = 1;  //默认当前页
	public static final Integer pagesize = 30;    //默认页行数
	public static final Integer morepage = 3;    //默认省略页
	
	/**
	 * 获取页数
	 * @param records 总数
	 * @param pagesize 页行数 
	 * @return
	 */
	public static Integer getPageCount(int records,int pagesize){
		if(records % pagesize == 0){
			return records/pagesize;
		}else{
			return records/pagesize + 1;
		}
	}
	/**
	 * 获取页数
	 * @param records 总数
	 * @param pagesize 页行数 
	 * @return
	 */
	public static long getPageCount(long records,long pagesize){
		if(records % pagesize == 0){
			return records/pagesize;
		}else{
			return records/pagesize + 1;
		}
	}
	
	/**
	 * 获取分页
	 * @param records 总记录数
	 * @param currentpage 当前页
	 * @param pagecount 总页数
	 * @param morepage 省略页数
	 * @return
	 */
	public static String getPageUtil(int records,int currentpage,int pagecount,int morepage){
		StringBuffer buf = new StringBuffer();
		buf.append("<ul>\n");
		buf.append("当前共有"+records+"条记录，当前第"+currentpage+"/"+pagecount+"页\n");
		buf.append("<li>");
		if(currentpage > 1){
			buf.append("<a href=\"javascript:;\" onclick=\"javascript:gotoPage("+(currentpage-1)+");\"><span class=\"sign_click\"><</span></a>\n");
		}else{
			buf.append("<span class=\"sign\"><</span>\n");
		}
		if(currentpage - morepage > 1){
			buf.append("<a href=\"javascript:;\" onclick=\"javascript:gotoPage(1);\"><span class=\"number_click\">1</span></a>\n");
			buf.append("...\n");
			for(int i=currentpage-morepage;i<currentpage;i++){
				buf.append("<a href=\"javascript:;\" onclick=\"javascript:gotoPage("+i+");\"><span class=\"number_click\">"+i+"</span></a>\n");
			}
			buf.append("<span class=\"number\">"+currentpage+"</span></a>\n");
		}else{
			for(int i=1;i<currentpage;i++){
				buf.append("<a href=\"javascript:;\" onclick=\"javascript:gotoPage("+i+");\"><span class=\"number_click\">"+i+"</span></a>\n");
			}
			buf.append("<span class=\"number\">"+currentpage+"</span>\n");
		}
		if(currentpage + morepage + 1 < pagecount){
			for(int i = currentpage + 1 ; i <= currentpage + morepage ; i++){
				buf.append("<a href=\"javascript:;\" onclick=\"javascript:gotoPage("+i+");\"><span class=\"number_click\">"+i+"</span></a>\n");
			}
			buf.append("...\n");
			buf.append("<a href=\"javascript:;\" onclick=\"javascript:gotoPage("+pagecount+");\"><span class=\"number_click\">"+pagecount+"</span></a>\n");
		}else{
			for(int i = currentpage + 1 ; i <= pagecount ; i++){
				buf.append("<a href=\"javascript:;\" onclick=\"javascript:gotoPage("+i+");\"><span class=\"number_click\">"+i+"</span></a>\n");
			}
		}
		if(currentpage < pagecount){
			buf.append("<a href=\"javascript:;\" onclick=\"javascript:gotoPage("+(currentpage+1)+");\"><span class=\"sign_click\">></span></a>\n");
		}else{
			buf.append("<span class=\"sign\">></span></a>\n");
		}
		buf.append("</li>\n");
		buf.append("</ul>\n");
		return buf.toString();
		
	}
}
