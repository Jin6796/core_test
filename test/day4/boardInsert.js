// 등록하기
function boardInsert(event) {
    // const post_id = $('#post_id').val();
    const subject = $('#subject').val();
    const reg_user = $('#reg_user').val();
    const content = $('#content').val();

    const form = {
        post_id : $('#post_id').val(),
        subject : $('#subject').val(),
        reg_user : $('#reg_user').val(),
        content : $('#content').val()
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

    console.log("얍");
}

// 게시판 작성 콜백 함수
function insertBoardCallback(obj){
    if(obj != null){        
        const result = obj.result;
        
        if(result == "SUCCESS"){                
            alert("게시글 등록을 성공하였습니다.");                
            location.href="./boardList.html"                
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