const urlParams = new URL(location.href).searchParams;
const post_id = urlParams.get('key');
console.log(post_id);

$.ajax({
    url: "http://localhost:8080/selectBoardTestDetail.do",
    type: "POST",
    data: JSON.stringify({"post_id": post_id}),
    contentType : "application/json",
    dataType: "json",
    success: function (data) {
        edit1.value = data.data.subject;
        edit2.value = data.data.upd_user;
        input10.value = data.data.views;
        textarea1.value = data.data.content;
        console.log(data.data);
    }
})

// 수정하기
function button3Click(event) {
    // insert
    const subject = $('#edit1').val();
    const upd_user = $('#edit2').val();
    const content = $('#textarea1').val();

    const form = {
        post_id : post_id,
        subject : subject,
        upd_user : upd_user,
        content : content
    };

    // 제목, 작성자, 내용 null 인정X
    if (subject == ""){            
        alert("제목을 입력해주세요.");
        $("#subject").focus();
        return;
    }
    if (upd_user == ""){            
        alert("이름을 입력해주세요.");
        $("#upd_user").focus();
        return;
    }
    if (content == ""){            
        alert("내용을 입력해주세요.");
        $("#content").focus();
        return;
    }

    const yn = confirm("게시글을 수정하시겠습니까?");        
        if(yn){
            $.ajax({    
               url      : "http://localhost:8080/selectBoardTestUpdate.do",
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

// 게시글로 돌아가기
function button1Click(event) {
    history.back();
}

// 게시판 작성 콜백 함수
function insertBoardCallback(obj){
    if(obj != null){    
        // console.log(obj);    
        // console.log(obj.data);    
        console.log(obj.data.tran);
        const result = obj.data.tran;
        
        if(result == true){                
            alert("게시글 수정을 성공하였습니다.");              
            location.href=`./boardDetail.html?key=${post_id}`;               
        } else {                
            alert("게시글 수정을 실패하였습니다.");    
            return;
        }
    }
}