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
package wai.com.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wai.com.service.UserGrpMngtService;

/**
 * @Class Name : UserGrpMngtServiceImpl.java
 * @Description : UserGrpMngt Service Implement Class
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

@Repository
public class UserGrpMngtServiceImpl implements UserGrpMngtService	 {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserGrpMngtServiceImpl.class);

	@Autowired
	private UserGrpMngtDAO userGrpMngtDAO;

	/** 사용자 그룹 등록 */
	@Override
	public int registUserGrp(Map<String, Object> map) throws Exception {
		return userGrpMngtDAO.registUserGrp(map);
	}
	
	/** 사용자 그룹 수정  */
	@Override
	public int updateUserGrp(Map<String, Object> map) throws Exception {
		return userGrpMngtDAO.updateUserGrp(map);
	}
	
	/** 사용자 그룹 삭제 */
	@Override
	public int deleteUserGrp(Map<String, Object> map) throws Exception {
		return userGrpMngtDAO.deleteUserGrp(map);
	}
	
	/** 사용자 그룹 목록 조회 */
	@Override
	public List<Map<String, Object>> selectUserGrpList(Map<String, Object> map) throws Exception {
		return userGrpMngtDAO.selectUserGrpList(map);
	}
}
