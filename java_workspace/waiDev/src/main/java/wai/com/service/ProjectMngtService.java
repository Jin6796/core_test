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
 * @Class Name : ProjectMngtService.java
 * @Description : ProjectMngtService Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2022.05.31           최초생성
 *
 * @author 코아뱅크 기술연구소
 * @since 2022.05.31
 * @version 1.0
 * @see
 *
 *  Copyright (C) by COREBANK All right reserved.
 */
public interface ProjectMngtService {
	
	/** 프로젝트기본 등록 */
	int registProjectBase(Map<String, Object> map) throws Exception;
	
	/** 프로젝트기본 수정 */
	int updateProjectBase(Map<String, Object> map) throws Exception;
	
	/** 프로젝트기본 삭제 */
	int deleteProjectBase(Map<String, Object> map) throws Exception;

	/** 프로젝트기본 목록 조회 */
	List<Map<String, Object>> selectProjectBaseList(Map<String, Object> map) throws Exception;
	
}
