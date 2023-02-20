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

import wai.com.service.MetaCodeRelMngtService;

/**
 * @Class Name : MetaCodeRelMngtController.java
 * @Description : MetaCodeRelMngt Controller Class
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
public class MetaCodeRelMngtController {

	/** metaCodeRelMngtService */
	@Autowired
	private MetaCodeRelMngtService metaCodeRelMngtService;

	/**
	 * 메타코드관계 등록
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/registMetaCodeRel.do")
	@ResponseBody
	public Map<String, Object> registMetaCodeRel(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			String meta_id = (String) paramMap.get("meta_id");
			if (meta_id == null || meta_id.equals("")) {
				map.put("errCd", -1);
				map.put("data", null);
				map.put("errMsg", "Meta ID is NULL");
				return map;
			}
			
			// 기연결된 코드 삭제
			HashMap<String, Object> delMap = new HashMap<String, Object>();
	    	delMap.put("meta_id", meta_id);
			int delRs = metaCodeRelMngtService.deleteMetaCodeRelId(delMap);
			if (delRs > 0) {
	        	map.put("errCd", 0);
				map.put("data", true);
	        }
			
			Map<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("meta_id",	meta_id);
			inMap.put("code_id",	(String) paramMap.get("code_id"));
//			inMap.put("code",		(String) paramMap.get("code"));
			inMap.put("reg_user", 	(String) paramMap.get("reg_user"));
			inMap.put("upd_user", 	(String) paramMap.get("upd_user"));
			
			int rs = metaCodeRelMngtService.registMetaCodeRel(inMap);
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
	 * 메타코드관계 삭제
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteMetaCodeRel.do")
	@ResponseBody
	public Map<String, Object> deleteMetaCodeRel(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			String meta_id = (String) paramMap.get("meta_id");
			if (meta_id == null || meta_id.equals("")) {
				map.put("errCd", -1);
				map.put("data", null);
				map.put("errMsg", "Meta ID is NULL");
				return map;
			}
			
			Map<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("meta_id", meta_id);
			inMap.put("code_id", (String) paramMap.get("code_id"));
			int rs = metaCodeRelMngtService.deleteMetaCodeRel(inMap);
			map.put("result", "success");
			map.put("data", rs);
			if (rs == 1) {
				map.put("errCd", 0);
				map.put("data", true);				
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
	 * 메타코드관계 목록 조회
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectMetaCodeRelList.do")
	@ResponseBody
	public Map<String, Object> selectMetaCodeList(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HashMap<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("meta_id",	(String) paramMap.get("meta_id"));
			inMap.put("meta_name",	(String) paramMap.get("meta_name"));
			
			List<Map<String, Object>> outList = metaCodeRelMngtService.selectMetaCodeRelList(inMap);
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