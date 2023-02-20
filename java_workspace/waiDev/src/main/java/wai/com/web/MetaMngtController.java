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

import wai.com.service.MetaMngtService;

/**
 * @Class Name : MetaMngtController.java
 * @Description : MetaMngt Controller Class
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
public class MetaMngtController {

	/** metaMngtService */
	@Autowired
	private MetaMngtService metaMngtService;

	/**
	 * 메타기본 등록
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/registMetaBase.do")
	@ResponseBody
	public Map<String, Object> registMetaBase(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			String cur_date = format.format(date);
			String meta_name = (String) paramMap.get("meta_name");
			if (meta_name == null || meta_name.equals("")) {
				map.put("errCd", -1);
				map.put("data", null);
				map.put("errMsg", "Meta Name is NULL");
				return map;
			}
			
			Map<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("cur_date", 	cur_date);
			inMap.put("meta_name",	meta_name);
			inMap.put("meta_desc",	(String) paramMap.get("meta_desc"));
			inMap.put("use_yn",	 	(String) paramMap.get("use_yn"));
//			inMap.put("data_type",	(String) paramMap.get("data_type"));
			inMap.put("data_size",	(String) paramMap.get("data_size"));
			inMap.put("reg_user", 	(String) paramMap.get("reg_user"));
			inMap.put("upd_user", 	(String) paramMap.get("upd_user"));
			
			int rs = metaMngtService.registMetaBase(inMap);
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
	 * 메타기본 수정
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateMetaBase.do")
	@ResponseBody
	public Map<String, Object> updateMetaBase(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			Map<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("meta_id",	(String) paramMap.get("meta_id"));
			inMap.put("meta_name",	(String) paramMap.get("meta_name"));
			inMap.put("meta_desc",	(String) paramMap.get("meta_desc"));
			inMap.put("use_yn",	 	(String) paramMap.get("use_yn"));
//			inMap.put("data_type",	(String) paramMap.get("data_type"));
			inMap.put("data_size",	(String) paramMap.get("data_size"));
			inMap.put("upd_user", 	(String) paramMap.get("upd_user"));
			
			int rs = metaMngtService.updateMetaBase(inMap);
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
	 * 메타기본 삭제
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteMetaBase.do")
	@ResponseBody
	public Map<String, Object> deleteMetaBase(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			boolean result = true;
			List<String> mgSeqs = (List<String>) paramMap.get("meta_id");
	        for (String metaId : mgSeqs) {
	        	HashMap<String, Object> inMap = new HashMap<String, Object>();
	        	inMap.put("meta_id", metaId);
	        	
	        	// 메타 삭제 및 해당 메타에 연결된 코드삭제
				int rs = metaMngtService.deleteMetaBase(inMap);
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
	 * 메타기본 목록 조회
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectMetaBaseList.do")
	@ResponseBody
	public Map<String, Object> selectMetaBaseList(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HashMap<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("meta_id",	(String) paramMap.get("meta_id"));
			inMap.put("meta_name",	(String) paramMap.get("meta_name"));
			
			List<Map<String, Object>> outList = metaMngtService.selectMetaBaseList(inMap);
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