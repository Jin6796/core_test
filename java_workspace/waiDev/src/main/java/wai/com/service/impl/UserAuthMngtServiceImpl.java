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

import wai.com.service.UserAuthMngtService;

/**
 * @Class Name : UserAuthMngtServiceImpl.java
 * @Description : UserAuthMngtService Implement Class
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

@Repository
public class UserAuthMngtServiceImpl implements UserAuthMngtService	 {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthMngtServiceImpl.class);

	@Autowired
	private UserAuthMngtDAO userAuthMngtDAO;
	
	/** 유저권한 등록 */
	@Override
	public int registUserAuth(Map<String, Object> map) throws Exception {
		return userAuthMngtDAO.registUserAuth(map);
	}

	/** 유저권한 수정 */
	@Override
	public int updateUserAuth(Map<String, Object> map) throws Exception {
		return userAuthMngtDAO.updateUserAuth(map);
	}
	
	/** 유저권한 삭제 */
	@Override
	public int deleteUserAuth(Map<String, Object> map) throws Exception {
		return userAuthMngtDAO.deleteUserAuth(map);
	}

	/** 유저권한 목록 조회 */
	@Override
	public List<Map<String, Object>> selectUserAuthList(Map<String, Object> map) throws Exception {
		return userAuthMngtDAO.selectUserAuthList(map);
	}
	
}
