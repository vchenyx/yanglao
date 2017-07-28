package com.jinkun.main.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.common.service.CommonService;
import com.common.util.CommonUtil;
import com.common.util.MD5;
import com.common.util.PageUtil;
import com.common.util.global.ConfigureFile;
import com.common.util.img.ImageUrl;
import com.common.util.img.ImageUtils;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.beans.MUserInfo;
import com.jinkun.main.beans.PensionPeopleInfo;
import com.jinkun.main.service.RegisterService;

@Controller
@RequestMapping("/department")
public class DeparementController {

	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/deleteDepartmentById.do")
	@ResponseBody
	public JSONObject deleteDepartmentById(HttpSession session,Integer id) {
		
		MPensionStationInfo findByIntegerId =(MPensionStationInfo) commonService.findByIntegerId(MPensionStationInfo.class, id);
		String muserId = findByIntegerId.getUserId();
		if(muserId !=null){
			commonService.deleteData(MUserInfo.class, "id ='"+muserId+"'");
		}
		JSONObject json = new JSONObject();
		commonService.deleteData(MPensionStationInfo.class, "id ="+id);
		return json;
	}
	
	/*
	@RequestMapping("/uploadImg.do")
	@ResponseBody
	public JSONObject uploadLineContentImg(MultipartFile uploadFile, HttpServletRequest request){
		String path = request.getServletContext().getRealPath(ImageUrl.home_slide_img_url);
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		String fileName = uploadFile.getOriginalFilename();
		fileName = CommonUtil.getMd5Str(CommonUtil.creatTooken())+fileName.substring(fileName.lastIndexOf("."));
		// 获取图片路径
		file = new File(path,fileName);
		JSONObject json = new JSONObject();
		try {
			uploadFile.transferTo(file);
			//   压缩图片大小
			BufferedImage bi = ImageIO.read(new File(file.getPath()));
			int srcWidth = bi.getWidth(); // 源图宽度
			int srcHeight = bi.getHeight(); // 源图高度
			ImageUtils.scale2(file.getPath(), file.getPath(), 259, 514, true);
			ImageUtils.cut(file.getPath(), file.getPath(),0,0,514,259);
			//添加数据库
		} catch (Exception e) {
			json.put("state", "error");
			return json;
		}	
		json.put("imageUrl", ImageUrl.home_slide_img_url+fileName);
		return json;
	}*/
	@RequestMapping("/addDepartment.do")
	@ResponseBody
	public JSONObject addDepartment(HttpSession session,Integer state,Integer id ,String name,String address,String linkName,String linkPhone,Long creationTime,String email, HttpServletRequest request) throws Exception {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer ids = pension.getId();
		JSONObject json = new JSONObject();
		Integer userType=0;
		if(id == null){
			if(state == 1){
				userType =51;
			}else{
				userType =52;
			}
			try {
				List<MPensionStationInfo> findByHql = commonService.findByHql(MUserInfo.class, " account ='"+linkPhone+"' and userType ="+userType+"", null);
				List<MPensionStationInfo> list = commonService.findByHql(MPensionStationInfo.class, " name ='"+name+"'  or  linkPhone ='"+linkPhone+"'", null);
				if(findByHql.size() >0 ){
					json.put("state", "exist");
					return json; 
				}
				if(list.size() >0 ){
					json.put("state", "ssss");
					return json; 
				}
				MUserInfo mu = new MUserInfo();
				mu.setAccount(linkPhone);
				mu.setCreateTime(new Date());
				mu.setName(linkName);
				mu.setPwd(MD5.md5Encode(ConfigureFile.default_pwd));
				mu.setUserType(userType);
				commonService.saveInfo(mu);
				MPensionStationInfo  mps =  new MPensionStationInfo();
				mps.setUserId(mu.getId());
				mps.setName(name);
				mps.setLinkName(linkName);
				mps.setLinkPhone(linkPhone);
				mps.setCreationTime(creationTime);
				mps.setAddress(address);
				if(!email.equals("")){
					mps.setEmail(email);
				}
				if(userType != 0){
				mps.setStationType(userType);
				}
				mps.setPid(ids);
				commonService.saveInfo(mps);
			} catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
				json.put("state", "error");
			}
		}else{
			List<MPensionStationInfo> list = commonService.findByHql(MPensionStationInfo.class, " id <> "+id+" and name ='"+name+"'  and  linkPhone ='"+linkPhone+"'", null);
			MPensionStationInfo findByIntegerId =(MPensionStationInfo) commonService.findByIntegerId(MPensionStationInfo.class, id);
			String userId = findByIntegerId.getUserId();
			String subwhere ="  id <> '"+userId+"'  and  account = "+linkPhone+" and userType ="+userType+"";
			List<MUserInfo> usertemp = commonService.findByHql(MUserInfo.class, subwhere, "");
			if(usertemp.size() >0 ){
				json.put("state", "exist");
				return json; 
			}if(list.size() >0 ){
				json.put("state", "exist");
				return json; 
			}
			MPensionStationInfo upUser =(MPensionStationInfo) commonService.findByIntegerId(MPensionStationInfo.class, id);
			upUser.setName(name);
			upUser.setLinkName(linkName);
			upUser.setLinkPhone(linkPhone);
			upUser.setCreationTime(creationTime);
			upUser.setAddress(address);
			upUser.setEmail(email);
			upUser.setPid(ids);
			MUserInfo mus =(MUserInfo) commonService.findByStringId(MUserInfo.class, upUser.getUserId());
			MUserInfo mu = new MUserInfo();
			mus.setAccount(linkPhone);
			commonService.updateInfo(mu);
			commonService.updateInfo(upUser);
		}
			json.put("state", "success");
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getList.do")
	@ResponseBody
	public String getUserList(HttpSession session,HttpServletRequest request){
		Integer currentpage = request.getParameter("currentpage") == null ? PageUtil.currentpage
				: Integer.parseInt(request.getParameter("currentpage").trim());// 当前页
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		
		String name = request.getParameter("name") == null?"":request.getParameter("name").trim();
		String cellphone = request.getParameter("cellphone") == null?"":request.getParameter("cellphone").trim();
		String subwhere =" 1=1";
		if(!"".equals(name)){
			subwhere += " and name like '%"+name+"%'";
		}
		if(!"".equals(cellphone)){
			subwhere += " and linkPhone like '%"+cellphone+"'";
		}
		List<MPensionStationInfo> userList = commonService.findByHqlPage(MPensionStationInfo.class, subwhere, "id desc", currentpage, pagesize);
		JSONObject json = new JSONObject();
		json.put("userList", userList);
		return json.toString();
	}
	/*
	 * 查询人员总数
	 * */
	@RequestMapping("/GetCountID.do")
	@ResponseBody
	public JSONObject selsectUserCountID(HttpSession session,HttpServletRequest request){
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		
		String name = request.getParameter("name") == null?"":request.getParameter("name").trim();
		String cellphone = request.getParameter("cellphone") == null?"":request.getParameter("cellphone").trim();
		
		
		String subwhere =" 1=1";
		if(!"".equals(name)){
			subwhere += " and peopleName like '%"+name+"%'";
		}
		if(!"".equals(cellphone)){
			subwhere += " and linkPhone like '%"+cellphone+"'";
		}
		long records = commonService.getResultCount(MPensionStationInfo.class, subwhere);
		long pageCount = PageUtil.getPageCount(records, pagesize);
		JSONObject json = new JSONObject();
		json.put("pageCount", pageCount);
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/findById.do")
	@ResponseBody
	public JSONObject findById(HttpSession session,Integer id) throws Exception {
		JSONObject json = new JSONObject();
		List<MPensionStationInfo> findByHql = commonService.findByHql(MPensionStationInfo.class, "id = "+id, null);
		json.put("list", findByHql);
		return json;
	}
	
	
}