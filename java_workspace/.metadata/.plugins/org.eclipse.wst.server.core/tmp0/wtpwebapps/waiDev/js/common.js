// 페이지 로딩(onload)
function onLoadCommon() {
	//if (!localStorage.getItem('login_id')) location.replace("/waiDev/login.html");
    $("#navigation").load("/waiDev/navigation.html");
    $("#topBar").load("/waiDev/topBar.html");
    $("#commonModal").load("/waiDev/modal.html");
}

// 관리자 여부
function checkAdmin() {
	return localStorage.admin_yn == "Y" ? true : false;
}

// 로그인 사용자
function getLoginUser() {
	return localStorage.getItem('login_id');
}

//사용자 로그아웃
function userLogout(page) {
	page.href = "/waiDev/login.html";
	localStorage.setItem("login_id", "");
}

// 서비스 호출
function svcCall(url, data, callback) {
	$.ajax({
        type : "POST",            	 // HTTP method type(GET, POST)
        url : "/" + url + ".do",     // 컨트롤러에서 대기중인 URL 주소
        data : JSON.stringify(data), // JSON 데이터
        contentType : "application/json",
        dataType : "json",
        success : function(result){
        	// result.errCd: 0(거래성공), -1(서비스오류)
        	// result.errMsg: 에러메시지
        	// result.data: 거래성공 시 리턴 데이터
        	if (result.errCd > -1) { // success
        		callback(result.data);
        	}
        	else { // error
        		console.error("[서비스오류]", result.errMsg);
        		callback(result.data);
        	}
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("[서버오류] 시스템팀에 문의해주세요.");
        }
    });
}

// group by json array
function groupBy(xs, key) {
	return xs.reduce(function(rv, x) {
		(rv[x[key]] = rv[x[key]] || []).push(x);
		return rv;
	}, {});
}

// combo/select data
var common_combo_data = {
	program_type : [
		{CODE: "CM", CODE_NM: "공통모듈"},
		{CODE: "CP", CODE_NM: "공용화면(부분화면)"},
		{CODE: "WB", CODE_NM: "전용브라우저"},
		{CODE: "BF", CODE_NM: "일반파일"}
	],
	program_status : [
		{CODE: "R", CODE_NM: "등록"},
		{CODE: "C", CODE_NM: "체크인 (커밋)"},
		{CODE: "U", CODE_NM: "체크아웃 (업데이트)"},
		{CODE: "O", CODE_NM: "운영중"}
	],
	lang_dv : [
		{CODE: "KR", CODE_NM: "한국어"},
		{CODE: "EN", CODE_NM: "영어"},
		{CODE: "CN", CODE_NM: "중국어"},
		{CODE: "JP", CODE_NM: "일본어"},
		{CODE: "IN", CODE_NM: "인도네시아어"}
	],
	auth_type : [
		{CODE:"M", CODE_NM:"메뉴"},
    	{CODE:"D", CODE_NM:"디렉토리"},
    	{CODE:"P", CODE_NM:"프로그램 (파일)"}
    ]
};


