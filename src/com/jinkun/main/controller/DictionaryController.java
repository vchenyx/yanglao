package com.jinkun.main.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.service.CommonService;
import com.jinkun.main.beans.SDictionary;
import com.jinkun.main.service.DictionaryService;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@RequestMapping("/saveDictionary.do")
	@ResponseBody
	public String saveDictionary(SDictionary dic) {
		dic.setAddTime(new Date().getTime());
		Integer id = dictionaryService.saveDictionary(dic);
		return "{\"flag\": " + id + "}";
	}
}
