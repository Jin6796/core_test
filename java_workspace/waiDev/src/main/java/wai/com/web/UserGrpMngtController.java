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

import wai.com.service.UserGrpMngtService;

/**
 * @Class Name : UserGrpMngtController.java
 * @Description : UserGrpMngt Controller Class
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
 * Copyright (C) by COREBANK All right reserved.
 */

@Controller
public class UserGrpMngtController {

	/** UserGrpMngtController */
	@Autowired
	private UserGrpMngtService userGrpMngtService;

	/**
	 * 사용자 그룹 등록
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/userGrpRegist.do")
	@ResponseBody
	public Map<String, Object> userGrpReg(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			String cur_date = format.format(date);
			String user_grp_name = (String) paramMap.get("user_grp_name");
			if (user_grp_name == null || user_grp_name.equals("")) {
				map.put("errCd", -1);
				map.put("data", null);
				map.put("errMsg", "User Group Name is NULL");
				return map;
			}
			
			Map<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("cur_date", 		cur_date);
			inMap.put("user_grp_name",	user_grp_name);
			inMap.put("grp_desc",		(String) paramMap.get("grp_desc"));
			inMap.put("reg_user",		(String) paramMap.get("reg_user"));
			inMap.put("upd_user",		(String) paramMap.get("upd_user"));
			
			int rs = userGrpMngtService.registUserGrp(inMap);
			if (rs == 1) {
				HashMap<String, Object> rsHm = new HashMap<String, Object>() ;
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
			map.put("errCd", -1);
			map.put("errMsg", "Transaction Error");
			map.put("data", null);
		}
		return map;
	}

	/**
	 * 사용자 그룹 수정
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/userGrpUpdate.do")
	@ResponseBody
	public Map<String, Object> userGrpUpd(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			String user_grp_id = (String) paramMap.get("user_grp_id");
			String user_grp_name = (String) paramMap.get("user_grp_name");
			if (user_grp_name == null || user_grp_name.equals("")) {
				map.put("errCd", -1);
				map.put("data", null);
				map.put("errMsg", "User Group Name is NULL");
				return map;
			}
			
			Map<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("user_grp_id", 	user_grp_id);
			inMap.put("user_grp_name", 	user_grp_name);
			inMap.put("grp_desc", 		(String) paramMap.get("grp_desc"));
			inMap.put("upd_user", 		(String) paramMap.get("upd_user"));
			
			int rs = userGrpMngtService.updateUserGrp(inMap);
			if (rs == 1) {
				HashMap<String, Object> rsHm = new HashMap<String, Object>() ;
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
	 * 사용자 그룹 삭제
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/userGrpDelete.do")
	@ResponseBody
	public Map<String, Object> userGrpDel(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			boolean result = true;
			List<String> mgSeqs = (List<String>) paramMap.get("user_grp_id");
	        for (String userGrpId : mgSeqs) {
	        	HashMap<String, Object> inMap = new HashMap<String, Object>();
	        	inMap.put("user_grp_id", userGrpId);
	        	
	        	// 그룹 삭제 및 해당 그룹에 연결된 사용자삭제
				int rs = userGrpMngtService.deleteUserGrp(inMap);
				if (rs < 1) { result = false; }
	        }
			
	        if (result == true) {
	        	map.put("errCd", 0);
				map.put("data", result);	        	
	        } else {
	        	procRs = false;
				errMsg = "Delete fail.";
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
	 * 사용자 그룹 목록 조회
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/userGrpListInquiry.do")
	@ResponseBody
	public Map<String, Object> userGrpListInq(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> outList = userGrpMngtService.selectUserGrpList(paramMap);
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