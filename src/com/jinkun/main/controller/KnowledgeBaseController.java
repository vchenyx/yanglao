package com.jinkun.main.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.service.CommonService;
import com.common.util.PageUtil;
import com.jinkun.main.beans.KnowledgeBaseInfo;
import com.jinkun.main.global.LocalTime;

/*
 * 操作知识库类型表
 * ZHUZHIYONG
 */
@Controller
@RequestMapping("/knowledgeBase")
public class KnowledgeBaseController {

	@Autowired
	private CommonService commonService;

	// 添加信息
	@SuppressWarnings("unchecked")
	@RequestMapping("/add.do")
	@ResponseBody
	public JSONObject add(int pId, String typeId, String name) {

		JSONObject json = new JSONObject();
		KnowledgeBaseInfo knowledgeBaseInfo = new KnowledgeBaseInfo();
		List<KnowledgeBaseInfo> findByHql = commonService.findByHql(
				KnowledgeBaseInfo.class, "name = '" + name + "'", null);
		knowledgeBaseInfo.setName(name);
		knowledgeBaseInfo.setCreationTime(LocalTime.getDateTime());

		try {
			if (typeId != "") {// 父类型不为空,添加二级类型名称
				if (findByHql.isEmpty()) {
					List<KnowledgeBaseInfo> list = commonService.findByHql(
							KnowledgeBaseInfo.class, "name='" + typeId + "'",
							null);
					knowledgeBaseInfo.setpId(list.get(0).getId());// 根据父类型名称获取父类型id
					commonService.saveInfo(knowledgeBaseInfo);// 添加子类型名称
					json.put("state", "success");
				} else {
					json.put("state", "exist");
				}
			} else if (pId == 1 && typeId == "") {// 选择二级且父类型名称为空,提示添加父类型
				json.put("state", "typeId");
			} else {
				if (findByHql.isEmpty()) {
					knowledgeBaseInfo.setpId(pId);
					commonService.saveInfo(knowledgeBaseInfo);// 添加父类型名称
					json.put("state", "success");
				} else {
					json.put("state", "exist");
				}
			}
		} catch (Exception e) {
			json.put("state", "no");
			e.printStackTrace();
		}
		return json;
	}

	// 查询信息集合
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/getList.do")
	@ResponseBody
	public List getList(HttpSession session, HttpServletRequest request) {
		Integer currentpage = request.getParameter("currentpage") == null ? PageUtil.currentpage
				: Integer.parseInt(request.getParameter("currentpage").trim());// 当前页
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数

		String name = request.getParameter("name") == null ? "" : request
				.getParameter("name").trim();// 类型名称

		String subwhere = " 1=1";
		if (!"".equals(name)) {
			subwhere += " and name like'%" + name + "%'";
		}

		List<KnowledgeBaseInfo> projList = commonService.findByHqlPage(
				KnowledgeBaseInfo.class, subwhere, "id desc", currentpage,
				pagesize);

		return projList;
	}

	// 查询总条数
	@RequestMapping("/getCount.do")
	@ResponseBody
	public JSONObject getCount(HttpSession session, HttpServletRequest request) {
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数

		String name = request.getParameter("name") == null ? "" : request
				.getParameter("name").trim();

		String subwhere = "1=1 ";
		if (!"".equals(name)) {
			subwhere += " and name like '%" + name + "%'";
		}

		long records = commonService.getResultCount(KnowledgeBaseInfo.class,
				subwhere);
		long pageCount = PageUtil.getPageCount(records, pagesize);
		JSONObject json = new JSONObject();
		json.put("pageCount", pageCount);
		return json;
	}

	// 删除单条数据
	@RequestMapping("/delete.do")
	@ResponseBody
	public JSONObject delete(String id) {

		JSONObject json = new JSONObject();
		try {
			commonService
					.deleteData(KnowledgeBaseInfo.class, "id='" + id + "'");
			json.put("state", "success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	// 更新数据
	@RequestMapping("/update.do")
	@ResponseBody
	public JSONObject update(int id, String name) {

		JSONObject json = new JSONObject();
		try {
			commonService.updateDate(KnowledgeBaseInfo.class, new String[] {
					"name='" + name + "'" }, "id='"
					+ id + "'");
			json.put("state", "success");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("state", "no");
		}
		return json;
	}

	// 为添加弹出层父类型名称赋值
	@SuppressWarnings({ "unchecked" })
	@RequestMapping("/getListName.do")
	@ResponseBody
	public JSONObject getListName(HttpSession session,
			HttpServletRequest request) {

		List<KnowledgeBaseInfo> List = commonService.findByHql(
				KnowledgeBaseInfo.class, "pId='0'", null);
		JSONObject json = new JSONObject();
		json.put("listName", List);
		return json;
	}
}
