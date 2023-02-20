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

import wai.com.service.UserGrpRelMngtService;

/**
 * @Class Name : UserGrpRelMngtController.java
 * @Description : UserGrpRelMngt Controller Class
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
public class UserGrpRelMngtController {

	/** userGrpRelMngtService */
	@Autowired
	private UserGrpRelMngtService userGrpRelMngtService;

	/**
	 * 유저그룹관계 등록
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/registUserGrpRel.do")
	@ResponseBody
	public Map<String, Object> registUserGrpRel(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			String user_grp_id = (String) paramMap.get("user_grp_id");
			if (user_grp_id == null || user_grp_id.equals("")) {
				map.put("errCd", -1);
				map.put("data", null);
				map.put("errMsg", "User Group ID is NULL");
				return map;
			}
			
			// 기연결된 사용자 삭제
			HashMap<String, Object> delMap = new HashMap<String, Object>();
	    	delMap.put("user_grp_id", user_grp_id);
			int delRs = userGrpRelMngtService.deleteUserGrpRelId(delMap);
			if (delRs > 0) {
	        	map.put("errCd", 0);
				map.put("data", true);
	        }
			
			// 사용자 연결 등록
			Map<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("user_grp_id",	user_grp_id);
			inMap.put("reg_user",		(String) paramMap.get("reg_user"));
			inMap.put("upd_user",		(String) paramMap.get("upd_user"));
			
			List<String> mgSeqs = (List<String>) paramMap.get("user_member");
			HashMap<String, Object> rsHm = new HashMap<String, Object>() ;
			if (mgSeqs.size() > 0) {
				for (String userMember : mgSeqs) {
		        	inMap.put("user_id", userMember);
					int rs = userGrpRelMngtService.registUserGrpRel(inMap);
					if (rs == 1) {
						rsHm.put("tran", true);
						map.put("errCd", 0);
						map.put("data", rsHm);				
					} else {
						procRs = false;
						errMsg = "Regist fail.";
						map.put("errCd", -1);
					}
		        }
			} else {
				rsHm.put("tran", true);
				map.put("errCd", 0);
				map.put("data", rsHm);
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
	 * 유저그룹관계 삭제
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteUserGrpRel.do")
	@ResponseBody
	public Map<String, Object> deleteUserGrpRel(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean procRs = true;
		String errMsg = "";
		try {
			String user_grp_id = (String) paramMap.get("user_grp_id");
			if (user_grp_id == null || user_grp_id.equals("")) {
				map.put("errCd", -1);
				map.put("data", null);
				map.put("errMsg", "User Group ID is NULL");
				return map;
			}
			
			Map<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("user_grp_id", user_grp_id);
			List<String> mgSeqs = (List<String>) paramMap.get("user_id");
	        for (String userId : mgSeqs) {
	        	inMap.put("user_id", userId);
				int rs = userGrpRelMngtService.deleteUserGrpRel(inMap);
				if (rs == 1) {
					map.put("errCd", 0);
					map.put("data", true);				
				} else {
					procRs = false;
					errMsg = "Regist fail.";
					map.put("errCd", -1);
				}
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
	 * 유저그룹관계 목록 조회
	 * @param 
	 * @param Map
	 * @return Map
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectUserGrpRelList.do")
	@ResponseBody
	public Map<String, Object> selectUserGrpRelList(@RequestBody Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
//			HashMap<String, Object> inMap = new HashMap<String, Object>();
//			inMap.put("user_grp_id",	(String) paramMap.get("user_grp_id"));
//			inMap.put("grp_name", 	 	(String) paramMap.get("grp_name"));		
//			inMap.put("desc", 			(String) paramMap.get("desc"));
			
			List<Map<String, Object>> outList = userGrpRelMngtService.selectUserGrpRelList(paramMap);
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