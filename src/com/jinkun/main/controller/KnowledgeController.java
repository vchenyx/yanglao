package com.jinkun.main.controller;

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
import com.jinkun.main.beans.KnowledgeBase;
import com.jinkun.main.beans.KnowledgeBaseInfo;
import com.jinkun.main.beans.MPensionStationInfo;
import com.jinkun.main.global.LocalTime;

/*
 * 操作知识库类型表
 * ZHUZHIYONG
 */
@Controller
@RequestMapping("/knowledge")
public class KnowledgeController {

	@Autowired
	private CommonService commonService;

	// 添加信息
	@SuppressWarnings("unchecked")
	@RequestMapping("/add.do")
	@ResponseBody
	public JSONObject add(KnowledgeBase knowledgeBase, HttpSession session) {
		MPensionStationInfo pension = (MPensionStationInfo) session
				.getAttribute("user");

		JSONObject json = new JSONObject();
		List<KnowledgeBase> findByHql = commonService.findByHql(
				KnowledgeBase.class,
				"name = '" + knowledgeBase.getName() + "'", null);
		knowledgeBase.setCreation(pension.getName());// 创建人
		knowledgeBase.setStateTime(LocalTime.getDateTime());// 创建时间
		knowledgeBase.setPensionId(pension.getId());// 机构ID
		try {
			if (findByHql.isEmpty()) {
				commonService.saveInfo(knowledgeBase);
				json.put("state", "success");
			} else {
				json.put("state", "exist");
			}

		} catch (Exception e) {
			json.put("state", "no");
		}
		return json;
	}

	// 查询信息集合
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/getList.do")
	@ResponseBody
	public List getList(HttpServletRequest request) {
		Integer currentpage = request.getParameter("currentpage") == null ? PageUtil.currentpage
				: Integer.parseInt(request.getParameter("currentpage").trim());// 当前页
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数

		String knowId = request.getParameter("knowId") == null ? "" : request
				.getParameter("knowId").trim();// 所属分类
		String creation = request.getParameter("creation") == null ? ""
				: request.getParameter("creation").trim();// 创建人

		String subwhere = " 1=1";
		if (!"".equals(knowId)) {
			subwhere += " and knowId like'%" + knowId + "%'";
		}
		if (!"".equals(creation)) {
			subwhere += " and creation like '%" + creation + "%'";
		}
		List<KnowledgeBase> projList = commonService
				.findByHqlPage(KnowledgeBase.class, subwhere, "id desc",
						currentpage, pagesize);

		return projList;
	}

	// 查询总条数
	@RequestMapping("/getCount.do")
	@ResponseBody
	public JSONObject getCount(HttpSession session, HttpServletRequest request) {
		Integer pagesize = request.getParameter("pagesize") == null ? PageUtil.pagesize
				: Integer.parseInt(request.getParameter("pagesize").trim());// 页行数

		String knowId = request.getParameter("knowId") == null ? "" : request
				.getParameter("knowId").trim();
		String creation = request.getParameter("creation") == null ? ""
				: request.getParameter("creation").trim();
		String subwhere = "1=1 ";
		if (!"".equals(knowId)) {
			subwhere += " and knowId like '%" + knowId + "%'";
		}
		if (!"".equals(creation)) {
			subwhere += " and creation like '%" + creation + "%'";
		}
		long records = commonService.getResultCount(KnowledgeBase.class,
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
			commonService.deleteData(KnowledgeBase.class, "id='" + id + "'");
			json.put("state", "success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	// 更新数据
	@RequestMapping("/update.do")
	@ResponseBody
	public JSONObject update(KnowledgeBase knowledgeBase) {

		JSONObject json = new JSONObject();
		try {
			commonService.updateDate(KnowledgeBase.class, new String[] {
					"name='" + knowledgeBase.getName() + "'",
					"knowId='" + knowledgeBase.getKnowId() + "'",
					"content='" + knowledgeBase.getContent() + "'",
					"readTimes='" + knowledgeBase.getReadTimes() + "'" },
					"id='" + knowledgeBase.getId() + "'");
			json.put("state", "success");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("state", "no");
		}
		return json;
	}

	// 获取类型名称
	@SuppressWarnings("unchecked")
	@RequestMapping("/findKnowId.do")
	@ResponseBody
	public JSONObject findKnowId() {

		JSONObject json = new JSONObject();
		List<KnowledgeBaseInfo> findByHql = commonService.findByHql(
				KnowledgeBaseInfo.class, "", null);
		json.put("findKnow", findByHql);
		return json;
	}
}
