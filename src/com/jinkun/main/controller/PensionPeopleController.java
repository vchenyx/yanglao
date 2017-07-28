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
import com.common.util.global.ResponseBean;
import com.common.util.img.ImageUrl;
import com.common.util.img.ImageUtils;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.beans.MUserInfo;
import com.jinkun.main.beans.PensionPeopleInfo;
import com.jinkun.main.service.RegisterService;

@Controller
@RequestMapping("/pensionPeople")
public class PensionPeopleController {

	@Autowired
	private CommonService commonService;
	@Autowired
	private RegisterService registerService;
	
	
	@RequestMapping("/deleteUser.do")
	@ResponseBody
	public JSONObject deleteUser(HttpSession session,Integer id) {
		
		PensionPeopleInfo findByIntegerId =(PensionPeopleInfo) commonService.findByIntegerId(PensionPeopleInfo.class, id);
		String muserId = findByIntegerId.getUserId();
		if(muserId !=null){
			commonService.deleteData(PensionPeopleInfo.class, "id ='"+id+"'");
		}
		JSONObject json = new JSONObject();
		commonService.deleteData(PensionPeopleInfo.class, "id ="+id);
		return json;
	}
	
	
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
	}
	// 添加人员
	@RequestMapping("/addUser.do")
	@ResponseBody
	public JSONObject addUser(HttpSession session,String peopleName,
			Integer sex, String id , String idCard,String cellphone,String duty,Integer state,String dept,String age,String urlImg, HttpServletRequest request) throws Exception {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer userType = Integer.parseInt(session.getAttribute("userType").toString());
		Integer ids = pension.getId();
		JSONObject json = new JSONObject();
		if(id == null){
			try {
				List<PensionPeopleInfo> findByHql = commonService.findByHql(PensionPeopleInfo.class, " cellphone ='"+cellphone+"'", null);
				if(findByHql.size() >0 ){
					json.put("state", "exist");
					return json; 
				}
				MUserInfo muser = new MUserInfo();
				if(state == 1){
					boolean notExist = registerService.isNotExist(cellphone);
				if(notExist){
					muser.setName(peopleName);
					muser.setCreateTime(new Date());
					muser.setAccount(cellphone);
					muser.setPwd(MD5.md5Encode(ConfigureFile.default_pwd));
					if (userType == 51) {
						muser.setUserType(510);
					}
					if (userType == 52) {
						muser.setUserType(520);
					}
					commonService.saveInfo(muser);
				}
				}
				PensionPeopleInfo user = new PensionPeopleInfo();
				user.setAge(Integer.parseInt(age));
				user.setPensionId(ids);
				user.setCreateTime(new Date().getTime());
				user.setDutyMs(duty);
				user.setIdCard(idCard);
				user.setImgUrl(urlImg);
				user.setStationId(pension.getId());
				user.setPensionId(pension.getPid());
				user.setName(peopleName);
				user.setSex(sex);
				user.setCellphone(cellphone.trim());
				user.setUserId(muser.getId());
				commonService.saveInfo(user);
			} catch (Exception e) {
				// TODO: handle exception
				json.put("state", "error");
			}
		}else{
			String subwhere ="  id <> "+id+" and  cellphone = "+cellphone+"";
			List<PensionPeopleInfo> usertemp = commonService.findByHql(PensionPeopleInfo.class, subwhere, "");
			if(usertemp.size() >0 ){
				json.put("state", "exist");
				return json; 
			}
			List<PensionPeopleInfo> upUser = commonService.findByHql(PensionPeopleInfo.class, " id ="+id, null);
			PensionPeopleInfo pensionPeopleInfo = upUser.get(0);
			pensionPeopleInfo.setAge(Integer.parseInt(age));
			pensionPeopleInfo.setStationId(pension.getId());
			pensionPeopleInfo.setCreateTime(new Date().getTime());
			pensionPeopleInfo.setDutyMs(duty);
			pensionPeopleInfo.setIdCard(idCard);
			pensionPeopleInfo.setImgUrl(urlImg);
			pensionPeopleInfo.setPensionId(pension.getPid());
			pensionPeopleInfo.setName(peopleName);
			pensionPeopleInfo.setSex(sex);
			pensionPeopleInfo.setCellphone(cellphone.trim());
			commonService.updateInfo(pensionPeopleInfo);
		}
			json.put("state", "success");
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getUserList.do")
	@ResponseBody
	public String getUserList(HttpSession session,HttpServletRequest request){
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer userType = Integer.parseInt(session.getAttribute("userType").toString());
		
		Integer id = pension.getId();
		Integer currentpage = request.getParameter("currentpage") == null ? PageUtil.currentpage
				: Integer.parseInt(request.getParameter("currentpage").trim());// 当前页
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		
		String people_name = request.getParameter("people_name") == null?"":request.getParameter("people_name").trim();
		String cellphone = request.getParameter("cellphone") == null?"":request.getParameter("cellphone").trim();
		String subwhere =" 1=1";
			if (userType == 5) {
				subwhere +=" and pensionId ="+id;
			} else {
				subwhere +=" and stationId ="+id;
			}
		if(!"".equals(people_name)){
			subwhere += " and peopleName like '%"+people_name+"%'";
		}
		if(!"".equals(cellphone)){
			subwhere += " and cellphone like '%"+cellphone+"'";
		}
		List<PensionPeopleInfo> userList = commonService.findByHqlPage(PensionPeopleInfo.class, subwhere, "id desc", currentpage, pagesize);
		JSONObject json = new JSONObject();
		json.put("userList", userList);
		return json.toString();
	}
	/*
	 * 查询人员总数
	 * */
	@RequestMapping("/GetUserCountID.do")
	@ResponseBody
	public JSONObject selsectUserCountID(HttpSession session,HttpServletRequest request){
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer id = pension.getId();
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数
		
		String people_name = request.getParameter("people_name") == null?"":request.getParameter("people_name").trim();
		String cellphone = request.getParameter("cellphone") == null?"":request.getParameter("cellphone").trim();
		
		
		String subwhere =" 1=1";
			subwhere +=" and pensionId ="+id;
		if(!"".equals(people_name)){
			subwhere += " and peopleName like '%"+people_name+"%'";
		}
		if(!"".equals(cellphone)){
			subwhere += " and cellphone like '%"+cellphone+"'";
		}
		long records = commonService.getResultCount(PensionPeopleInfo.class, subwhere);
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
		List<PensionPeopleInfo> findByHql = commonService.findByHql(PensionPeopleInfo.class, "id = "+id, null);
		json.put("list", findByHql);
		return json;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/findBycolumn.do")
	@ResponseBody
	public JSONObject findBycolumn(HttpSession session) throws Exception {
		MPensionStationInfo pension = (MPensionStationInfo) session.getAttribute("user");
		Integer id = pension.getId();
		JSONObject json = new JSONObject();
		List<MPensionStationInfo> findByHqlGetColumn = commonService.findByHqlGetColumn(MPensionStationInfo.class, " id <> "+id+"", null, " id,name,stationType");
		json.put("list", findByHqlGetColumn);
		return json;
	}
	
}