/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package wai.com.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wai.com.service.TemplateMngtService;

/**
 * @Class Name : TemplateMngtController.java
 * @Description : TemplateMngt Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2022.06.02           최초생성
 *
 * @author 코아뱅크 기술연구소
 * @since 2022.06.02
 * @version 1.0
 * @see
 *
 *  Copyright (C) by COREBANK All right reserved.
 */

@Controller
public class TemplateMngtController {

	/** templateMngtService */
	@Autowired
	private TemplateMngtService templateMngtService;

	/**
	 * 템플릿기본 등록
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/registTemplateBase.do")
	@ResponseBody
	public Map<String, Object> registTemplateBase(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			Map<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("temp_seq",	(String) paramMap.get("temp_seq"));
			inMap.put("temp_name",	(String) paramMap.get("temp_name"));
			inMap.put("temp_cont",	(String) paramMap.get("temp_cont"));
			inMap.put("temp_desc",	(String) paramMap.get("temp_desc"));
			inMap.put("del_yn",	 	(String) paramMap.get("del_yn"));
			inMap.put("del_date",	(String) paramMap.get("del_date"));
			inMap.put("reg_user", 	(String) paramMap.get("reg_user"));
			inMap.put("upd_user", 	(String) paramMap.get("upd_user"));
			
			int rs = templateMngtService.registTemplateBase(inMap);
			if (rs == 1) {
				HashMap<String, Object> rsHm = new HashMap<String, Object>();
				rsHm.put("regist", true);
				map.put("errCd", 0);
				map.put("data", rsHm);				
			} else {
				procRs = false;
				errMsg = "Regist fail.";
				map.put("errCd", -1);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			procRs = false;
			errMsg = "Transaction Error";
		} finally {
			if (!procRs) {
				map.put("errCd", -1);
				map.put("errMsg", errMsg);
				map.put("data", null);
			}
		}
		return map;
	}

	/**
	 * 템플릿기본 수정
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateTemplateBase.do")
	@ResponseBody
	public Map<String, Object> updateTemplateBase(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			Map<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("temp_seq",	(String) paramMap.get("temp_seq"));
			inMap.put("temp_name",	(String) paramMap.get("temp_name"));
			inMap.put("temp_cont",	(String) paramMap.get("temp_cont"));
			inMap.put("temp_desc",	(String) paramMap.get("temp_desc"));
			inMap.put("del_yn",	 	(String) paramMap.get("del_yn"));
			inMap.put("del_date",	(String) paramMap.get("del_date"));
			inMap.put("upd_user", 	(String) paramMap.get("upd_user"));
			
			int rs = templateMngtService.updateTemplateBase(inMap);
			if (rs == 1) {
				HashMap<String, Object> rsHm = new HashMap<String, Object>();
				rsHm.put("update", true);
				map.put("errCd", 0);
				map.put("data", rsHm);				
			} else {
				procRs = false;
				errMsg = "Update fail.";
				map.put("errCd", -1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			procRs = false;
			errMsg = "Transaction Error";
		} finally {
			if (!procRs) {
				map.put("errCd", -1);
				map.put("errMsg", errMsg);
				map.put("data", null);
			}
		}
		return map;
	}
	
	/**
	 * 템플릿기본 삭제
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteTemplateBase.do")
	@ResponseBody
	public Map<String, Object> deleteTemplateBase(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			int rs = templateMngtService.deleteTemplateBase(paramMap);
			map.put("result", "success");
			map.put("data", rs);
			if (rs == 1) {
				HashMap<String, Object> rsHm = new HashMap<String, Object>();
				rsHm.put("delete", true);
				map.put("errCd", 0);
				map.put("data", rsHm);				
			} else {
				procRs = false;
				errMsg = "delete fail.";
				map.put("errCd", -1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			procRs = false;
			errMsg = "Transaction Error";
		} finally {
			if (!procRs) {
				map.put("errCd", -1);
				map.put("errMsg", errMsg);
				map.put("data", null);
			}
		}
		return map;
	}

	/**
	 * 템플릿기본 목록 조회
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectTemplateBaseList.do")
	@ResponseBody
	public Map<String, Object> selectTemplateBaseList(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HashMap<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("temp_seq",	(String) paramMap.get("temp_seq"));
			inMap.put("temp_name",	(String) paramMap.get("temp_name"));
			
			List<Map<String, Object>> outList = templateMngtService.selectTemplateBaseList(inMap);
			map.put("errCd", 0);
			map.put("data", outList);
		}
		catch (Exception e) {
			e.printStackTrace();
			map.put("errCd", -1);
			map.put("errMsg", "Transaction Error");
			map.put("data", null);
		}
		return map;
	}
	
}