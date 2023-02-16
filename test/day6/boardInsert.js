// 등록하기
function boardInsert(event) {
    const subject = $('#edit1').val();
    const reg_user = $('#edit2').val();
    const content = $('#textarea1').val();

    const form = {
        subject : subject,
        reg_user : reg_user,
        content : content
    };

    // 제목, 작성자, 내용 null 인정X
    if (subject == ""){            
        alert("제목을 입력해주세요.");
        $("#subject").focus();
        return;
    }
    if (reg_user == ""){            
        alert("이름을 입력해주세요.");
        $("#reg_user").focus();
        return;
    }
    if (content == ""){            
        alert("내용을 입력해주세요.");
        $("#content").focus();
        return;
    }
    
    const yn = confirm("게시글을 등록하시겠습니까?");        
        if(yn){
            $.ajax({    
               url      : "http://localhost:8080/selectBoardTestInsert.do",
               data     : JSON.stringify(form),
               contentType: "application/json; charset=UTF-8",
               dataType : "JSON",
               cache    : false,
               async    : true,
               type     : "POST",    
               success  : function(obj) {
                    insertBoardCallback(obj);                
                },           
               error    : function(xhr, status, error) {}
                
            });
        }
}

// 게시판 작성 콜백 함수
function insertBoardCallback(obj){
    if(obj != null){        
        console.log(obj);
        console.log(obj.data.tran);
        
        const result = obj.data.tran;
        
        if(result == true){                
            alert("게시글 등록을 성공하였습니다.");                
            // location.href="./boardList.html"                
        } else {                
            alert("게시글 등록을 실패하였습니다.");    
            return;
        }
    }
}

// 뒤로가기
function button2Click(event) {
    history.back();
}

// 첨부파일 업로드
function fileUpload(event) {
	
}