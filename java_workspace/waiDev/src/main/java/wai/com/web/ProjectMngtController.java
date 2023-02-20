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

import java.text.SimpleDateFormat;
import java.util.Date;
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

import wai.com.service.ProjectMngtService;

/**
 * @Class Name : ProjectMngtController.java
 * @Description : ProjectMngt Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2022.05.31           최초생성
 *
 * @author 코아뱅크 기술연구소
 * @since 2022.05.31
 * @version 1.0
 * @see
 *
 *  Copyright (C) by COREBANK All right reserved.
 */

@Controller
public class ProjectMngtController {

	/** projectMngtService */
	@Autowired
	private ProjectMngtService projectMngtService;

	/**
	 * 프로젝트기본 등록
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/registProjectBase.do")
	@ResponseBody
	public Map<String, Object> registProjectBase(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			String cur_date = format.format(date);
			String project_name = (String) paramMap.get("project_name");
			if (project_name == null || project_name.equals("")) {
				map.put("errCd", -1);
				map.put("data", null);
				map.put("errMsg", "Project Name is NULL");
				return map;
			}
			
			Map<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("cur_date", 		cur_date);
			inMap.put("project_name",	project_name);
			inMap.put("project_desc",	(String) paramMap.get("project_desc"));
			inMap.put("use_yn",			(String) paramMap.get("use_yn"));
			inMap.put("reg_user",		(String) paramMap.get("reg_user"));
			inMap.put("upd_user",		(String) paramMap.get("upd_user"));
			
			int rs = projectMngtService.registProjectBase(inMap);
			if (rs == 1) {
				HashMap<String, Object> rsHm = new HashMap<String, Object>();
				rsHm.put("tran", true);
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
	 * 프로젝트기본 수정
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateProjectBase.do")
	@ResponseBody
	public Map<String, Object> updateProjectBase(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			Map<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("project_id",		 (String) paramMap.get("project_id"));
			inMap.put("project_name", 	 (String) paramMap.get("project_name"));
			inMap.put("project_desc", 	 (String) paramMap.get("project_desc"));
			inMap.put("use_yn", 		 (String) paramMap.get("use_yn"));
			inMap.put("upd_user", 		 (String) paramMap.get("upd_user"));
			
			int rs = projectMngtService.updateProjectBase(inMap);
			if (rs == 1) {
				HashMap<String, Object> rsHm = new HashMap<String, Object>();
				rsHm.put("tran", true);
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
	 * 프로젝트기본 삭제
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteProjectBase.do")
	@ResponseBody
	public Map<String, Object> deleteProjectBase(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			boolean result = true;
			List<String> mgSeqs = (List<String>) paramMap.get("project_id");
			HashMap<String, Object> inMap = new HashMap<String, Object>();
	        for (String projectId : mgSeqs) {
	        	inMap.put("project_id", projectId);
	        	
	        	// 프로젝트 삭제 및 해당 프로젝트에 연결된 그룹삭제
				int rs = projectMngtService.deleteProjectBase(inMap);
				if (rs < 1) { result = false; }
	        }
			
	        if (result == true) {
	        	map.put("errCd", 0);
				map.put("data", result);	        	
	        } else {
	        	procRs = false;
				errMsg = "Delete fail.";
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
	 * 프로젝트기본 목록 조회
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectProjectBaseList.do")
	@ResponseBody
	public Map<String, Object> selectProjectBaseList(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HashMap<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("project_id",		 (String) paramMap.get("project_id"));
			inMap.put("project_name", 	 (String) paramMap.get("project_name"));
			List<Map<String, Object>> outList = projectMngtService.selectProjectBaseList(inMap);
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