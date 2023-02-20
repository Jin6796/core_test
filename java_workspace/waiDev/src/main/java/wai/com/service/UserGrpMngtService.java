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
package wai.com.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : UserGrpMngtService.java
 * @Description : UserGrpMngt Service Class
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
public interface UserGrpMngtService {
	
	/** 사용자 그룹 등록 */
	public int registUserGrp(Map<String, Object> map) throws Exception;
	
	/** 사용자 그룹 수정 */
	public int updateUserGrp(Map<String, Object> map) throws Exception;
	
	/** 사용자 그룹 삭제 */
	public int deleteUserGrp(Map<String, Object> map) throws Exception;
	
	/** 사용자 그룹 목록 조회 */
	List<Map<String, Object>> selectUserGrpList(Map<String, Object> map) throws Exception;
}
