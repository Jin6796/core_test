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

import wai.com.service.ProgramGrpMngtService;

/**
 * @Class Name : ProgramGrpMngtController.java
 * @Description : ProgramGrpMngt Controller Class
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
public class ProgramGrpMngtController {

	/** programGrpMngtService */
	@Autowired
	private ProgramGrpMngtService programGrpMngtService;

	/**
	 * 프로그램유형 등록
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/registProgramGrp.do")
	@ResponseBody
	public Map<String, Object> registProgramGrp(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			Map<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("project_id",		 (String) paramMap.get("project_id"));
			inMap.put("program_path", 	 (String) paramMap.get("program_path"));		
			inMap.put("user_grp_id", 	 (String) paramMap.get("user_grp_id"));
			inMap.put("reg_user", 		 (String) paramMap.get("reg_user"));
			inMap.put("upd_user", 		 (String) paramMap.get("upd_user"));		
					
			int rs = programGrpMngtService.registProgramGrp(inMap);
			if (rs == 1) {
				HashMap<String, Object> rsHm = new HashMap<String, Object>() ;
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
	 * 프로그램유형 수정
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateProgramGrp.do")
	@ResponseBody
	public Map<String, Object> updateProgramGrp(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			Map<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("project_id",		 (String) paramMap.get("project_id"));
			inMap.put("program_path", 	 (String) paramMap.get("program_path"));
			inMap.put("user_grp_id", 	 (String) paramMap.get("user_grp_id"));
			inMap.put("upd_user", 		 (String) paramMap.get("upd_user"));
			
			int rs = programGrpMngtService.updateProgramGrp(inMap);
			if (rs == 1) {
				HashMap<String, Object> rsHm = new HashMap<String, Object>() ;
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
	 * 프로그램유형 삭제
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteProgramGrp.do")
	@ResponseBody
	public Map<String, Object> deleteProgramGrp(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			int rs = programGrpMngtService.deleteProgramGrp(paramMap);
			if (rs == 1) {
				HashMap<String, Object> rsHm = new HashMap<String, Object>() ;
				rsHm.put("delete", true);
				map.put("errCd", 0);
				map.put("data", rsHm);
			} else {
				procRs = false;
				errMsg = "Delete fail.";
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
	 * 프로그램유형 목록 조회
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectProgramGrpList.do")
	@ResponseBody
	public Map<String, Object> selectProgramGrpList(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HashMap<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("project_id",		 (String) paramMap.get("project_id"));
			inMap.put("program_path", 	 (String) paramMap.get("program_path"));
			inMap.put("user_grp_id", 	 (String) paramMap.get("user_grp_id"));
			
			List<Map<String, Object>> outList = programGrpMngtService.selectProgramGrpList(inMap);
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