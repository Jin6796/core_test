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

import java.util.ArrayList;
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

import wai.com.service.ProgramMngtService;

/**
 * @Class Name : ProgramMngtController.java
 * @Description : ProgramMngt Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2022.05.30           최초생성
 *
 * @author 코아뱅크 기술연구소
 * @since 2022.05.30
 * @version 1.0
 * @see
 *
 *  Copyright (C) by COREBANK All right reserved.
 */

@Controller
public class ProgramMngtController {

	/** programMngtService */
	@Autowired
	private ProgramMngtService programMngtService;

	/**
	 * 프로그램기본 등록
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/registProgramBase.do")
	@ResponseBody
	public Map<String, Object> registProgramBase(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			String ver = (String) paramMap.get("program_ver");
			int to = Integer.parseInt(ver) + 1;
			String program_ver = String.format("%03d", to);
			
			Map<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("project_id",		 (String) paramMap.get("project_id"));
			inMap.put("program_path", 	 (String) paramMap.get("program_path"));
			inMap.put("program_name", 	 (String) paramMap.get("program_name"));
			inMap.put("program_type_id", (String) paramMap.get("program_type_id"));
			inMap.put("desc", 			 (String) paramMap.get("program_desc"));
			inMap.put("status", 		 (String) paramMap.get("status"));
			inMap.put("program_ver", 	 program_ver);
			inMap.put("del_yn", 		 (String) paramMap.get("del_yn"));
			inMap.put("del_date", 		 (String) paramMap.get("del_date"));
			inMap.put("reg_user", 		 (String) paramMap.get("reg_user"));
			inMap.put("upd_user", 		 (String) paramMap.get("upd_user"));
			inMap.put("op_apply_date",   (String) paramMap.get("op_apply_date"));
			
			int rs = programMngtService.registProgramBase(inMap);
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
	 * 프로그램기본 수정
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateProgramBase.do")
	@ResponseBody
	public Map<String, Object> updateProgramBase(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			Map<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("project_id",		 (String) paramMap.get("project_id"));
			inMap.put("program_path", 	 (String) paramMap.get("program_path"));
			inMap.put("program_name", 	 (String) paramMap.get("program_name"));
			inMap.put("program_type_id", (String) paramMap.get("program_type_id"));
			inMap.put("desc", 			 (String) paramMap.get("desc"));
			inMap.put("status", 		 (String) paramMap.get("status"));
			inMap.put("use_lang", 		 (String) paramMap.get("use_lang"));
			inMap.put("program_ver", 	 (String) paramMap.get("program_ver"));
			inMap.put("del_yn", 		 (String) paramMap.get("del_yn"));
			inMap.put("del_date", 		 (String) paramMap.get("del_date"));
			inMap.put("upd_user", 		 (String) paramMap.get("upd_user"));
			inMap.put("op_apply_date",   (String) paramMap.get("op_apply_date"));
			
			int rs = programMngtService.updateProgramBase(inMap);
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
	 * 프로그램기본 삭제
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteProgramBase.do")
	@ResponseBody
	public Map<String, Object> deleteProgramBase(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			int rs = programMngtService.deleteProgramBase(paramMap);
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
	 * 프로그램기본 목록 조회
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectProgramBaseList.do")
	@ResponseBody
	public Map<String, Object> selectProgramBaseList(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HashMap<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("project_id",		 (String) paramMap.get("project_id"));
			inMap.put("program_type_id", (String) paramMap.get("program_type_id"));
			inMap.put("program_name",	 (String) paramMap.get("program_name"));
			List<Map<String, Object>> outList = programMngtService.selectProgramBaseList(inMap);
			
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
	
	
	
	
	
	
	
	
	
	
//	
//	@RequestMapping(value = "/selectTestList.do")
//	public void selectTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		try {
//			System.out.println("--------ok---------");
//			DataControl dataControl = new DataControl(request);
//            DataSetList inDataSetList = dataControl.getRequestData();
//
//            DataSet dataset1 = inDataSetList.get("inData1");
//            String input_val = dataset1.getString(0, "subject");
//
//            String key = programMngtService.selectPostId();
//            
//            inMap.put('POST_ID', key);
//            inMap.put('SUBJECT', input_val);
//            
//            System.out.println("------> " + input_val);
//            
//            HashMap<String, Object> inMap = new HashMap<String, Object>();
//			inMap.put("meta_name",	input_val);
//			List<Map<String, Object>> outList = programMngtService.selectTestList(inMap);
//			
//			DataSetList outDataSetList = dataControl.getResponseData();
//            DataSet outData1 = outDataSetList.get("outData1");
//            
//            List<Map<String, Object>> outList = new ArrayList<Map<String,Object>>();
//            outList.add(inMap);
//            
//            outData1.setData(outList);
//            
//            dataControl.sendReponseData(response);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
	
	
}