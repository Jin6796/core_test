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

import wai.com.service.MetaCodeMngtService;

/**
 * @Class Name : MetaCodeMngtController.java
 * @Description : MetaCodeMngt Controller Class
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
public class MetaCodeMngtController {

	/** metaCodeMngtService */
	@Autowired
	private MetaCodeMngtService metaCodeMngtService;

	/**
	 * 메타코드기본 등록
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/registMetaCode.do")
	@ResponseBody
	public Map<String, Object> registMetaCode(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			String code_id_name = (String) paramMap.get("code_id_name");
			if (code_id_name == null || code_id_name.equals("")) {
				map.put("errCd", -1);
				map.put("data", null);
				map.put("errMsg", "Code ID Name is NULL");
				return map;
			}
			
			HashMap<String, Object> getId = new HashMap<String, Object>();
			List<Map<String, Object>> outList = metaCodeMngtService.selectMetaCodeId(getId);
			Map<String, Object> rsMap = outList.get(0);
			String code_id = (String) rsMap.get("CODE_ID");
			
			boolean result = true;
			List<String> code = (List<String>) paramMap.get("code");
			List<String> code_name = (List<String>) paramMap.get("code_name");
			for (int i=0; i<code.size(); i++) {
				HashMap<String, Object> inMap = new HashMap<String, Object>();
				inMap.put("code", code.get(i));
				inMap.put("code_name", code_name.get(i));
				
				inMap.put("code_id", 	  code_id);
				inMap.put("code_id_name", code_id_name);
				inMap.put("reg_user",  	  (String) paramMap.get("reg_user"));
				inMap.put("upd_user",  	  (String) paramMap.get("upd_user"));
				
				int rs = metaCodeMngtService.registMetaCode(inMap);
				if (rs < 1) { result = false; }
			}
			
			if (result == true) {
				HashMap<String, Object> rsHm = new HashMap<String, Object>();
				rsHm.put("tran", true);
	        	map.put("errCd", 0);
				map.put("data", rsHm);	        	
	        } else {
	        	procRs = false;
				errMsg = "Regist fail.";
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
	 * 메타코드기본 수정
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateMetaCode.do")
	@ResponseBody
	public Map<String, Object> updateMetaCode(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			String code_id = (String) paramMap.get("code_id");
			if (code_id == null || code_id.equals("")) {
				map.put("errCd", -1);
				map.put("data", null);
				map.put("errMsg", "Code ID is NULL");
				return map;
			}
			
			// 기코드삭제
			HashMap<String, Object> delMap = new HashMap<String, Object>();
	    	delMap.put("code_id", code_id);
			int delRs = metaCodeMngtService.deleteMetaCode(delMap);
			if (delRs > 0) {
	        	map.put("errCd", 0);
				map.put("data", true);
	        }
			
			// 재등록
			boolean result = true;
			List<String> code = (List<String>) paramMap.get("code");
			List<String> code_name = (List<String>) paramMap.get("code_name");
			for (int i=0; i<code.size(); i++) {
				HashMap<String, Object> inMap = new HashMap<String, Object>();
				inMap.put("code", code.get(i));
				inMap.put("code_name", code_name.get(i));
				
				inMap.put("code_id",	  (String) paramMap.get("code_id"));
				inMap.put("code_id_name", (String) paramMap.get("code_id_name"));
				inMap.put("reg_user", 	  (String) paramMap.get("reg_user"));
				inMap.put("upd_user", 	  (String) paramMap.get("upd_user"));
				
				int rs = metaCodeMngtService.registMetaCode(inMap);
				if (rs < 1) { result = false; }
			}
			
			if (result == true) {
				HashMap<String, Object> rsHm = new HashMap<String, Object>();
				rsHm.put("tran", true);
	        	map.put("errCd", 0);
				map.put("data", rsHm);	        	
	        } else {
	        	procRs = false;
				errMsg = "Update fail.";
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
	 * 메타코드기본 삭제
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteMetaCode.do")
	@ResponseBody
	public Map<String, Object> deleteMetaCode(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			boolean result = true;
			List<String> mgSeqs = (List<String>) paramMap.get("code_id");
	        for (String codeId : mgSeqs) {
	        	HashMap<String, Object> inMap = new HashMap<String, Object>();
	        	inMap.put("code_id", codeId);
	        	
	        	// 코드 삭제
				int rs = metaCodeMngtService.deleteMetaCode(inMap);
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
	 * 메타코드기본 목록 조회
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectMetaCodeList.do")
	@ResponseBody
	public Map<String, Object> selectMetaCodeList(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HashMap<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("code_id",	(String) paramMap.get("code_id"));
			inMap.put("code_name",	(String) paramMap.get("code_name"));
			
			HashMap<String, Object> rsHm = new HashMap<String, Object>();
			List<Map<String, Object>> cdList = metaCodeMngtService.selectMetaCodeList(inMap);
			List<Map<String, Object>> dtList = metaCodeMngtService.selectMetaCodeDetailList(inMap);
			rsHm.put("codeIdList", cdList);
			rsHm.put("codeDtList", dtList);
			map.put("errCd", 0);
			map.put("data", rsHm);
		}
		catch (Exception e) {
			e.printStackTrace();
			map.put("errCd", -1);
			map.put("errMsg", "Transaction Error");
			map.put("data", null);
		}
		return map;
	}
	
	/**
	 * 메타코드기본 목록 상세 조회
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectMetaCodeDetailList.do")
	@ResponseBody
	public Map<String, Object> selectMetaCodeDetailList(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HashMap<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("code_id",	(String) paramMap.get("code_id"));
			inMap.put("code_name",	(String) paramMap.get("code_name"));
			
			List<Map<String, Object>> outList = metaCodeMngtService.selectMetaCodeDetailList(inMap);
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