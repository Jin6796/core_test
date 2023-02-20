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

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @Class Name : MetaMngtDAO.java
 * @Description : MetaMngt DAO Class
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
public class MetaMngtDAO extends EgovAbstractDAO {

	@Autowired
	private SqlSession SqlSession;
	
	/** 메타기본 등록 */
	public int registMetaBase(Map<String, Object> map) throws Exception {
		return SqlSession.insert("wai.com.service.impl.metaMngtMapper.registMetaBase", map);
	}
	
	/** 메타기본 수정 */
	public int updateMetaBase(Map<String, Object> map) throws Exception {
		return SqlSession.update("wai.com.service.impl.metaMngtMapper.updateMetaBase", map);
	}
	
	/** 메타기본 삭제 */
	public int deleteMetaBase(Map<String, Object> map) throws Exception {
		return SqlSession.delete("wai.com.service.impl.metaMngtMapper.deleteMetaBase", map);
	}

	/** 메타기본 목록 조회 */
	public List<Map<String, Object>> selectMetaBaseList(Map<String, Object> map) throws Exception {
		return SqlSession.selectList("wai.com.service.impl.metaMngtMapper.selectMetaBaseList", map);
	}
	
}
