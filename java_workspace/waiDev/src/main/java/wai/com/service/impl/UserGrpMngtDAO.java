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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @Class Name : UserGrpMngtDAO.java
 * @Description : UserGrpMngt DAO Class
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
public class UserGrpMngtDAO extends EgovAbstractDAO {

	@Autowired
	private SqlSession SqlSession;
	
	/** 사용자 그룹 등록 */
	public int registUserGrp(Map<String, Object> map) throws Exception {
//		sqlLog("registUserGrp", map);
		return SqlSession.insert("wai.com.service.impl.userGrpMngtMapper.registUserGrp", map);
	}
	
	/** 사용자 그룹 수정 */
	public int updateUserGrp(Map<String, Object> map) throws Exception {
//		sqlLog("updateUserGrp", map);
		return SqlSession.insert("wai.com.service.impl.userGrpMngtMapper.updateUserGrp", map);
	}
	
	/** 사용자 그룹 삭제 */
	public int deleteUserGrp(Map<String, Object> map) throws Exception {
//		sqlLog("deleteUserGrp", map);
		return SqlSession.delete("wai.com.service.impl.userGrpMngtMapper.deleteUserGrp", map);
	}
	
	/** 사용자 그룹 목록 조회 */
	public List<Map<String, Object>> selectUserGrpList(Map<String, Object> map) throws Exception {
//		sqlLog("selectUserGrpList", map);
		return SqlSession.selectList("wai.com.service.impl.userGrpMngtMapper.selectUserGrpList", map);
	}
	
	private void sqlLog(String service, Map<String, Object> map) throws Exception {
		String sql = SqlSession.getConfiguration().getMappedStatement("wai.com.service.impl.userGrpMngtMapper."+service).getBoundSql(map).getSql();
        List<ParameterMapping> parameterMappings = SqlSession.getConfiguration().getMappedStatement("wai.com.service.impl.userGrpMngtMapper."+service).getBoundSql(map).getParameterMappings();
        for (ParameterMapping parameterMapping : parameterMappings) {
            String param = (String) map.get(parameterMapping.getProperty());
            sql = sql.replaceFirst("\\?", "'" + param + "'");
        }
        System.out.println("[" + service + "] : " + sql);
	}
}
