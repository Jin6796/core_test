// 목록으로 돌아가기
function button1Click(event) {
    history.back();
}

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
        input5.value = data.data.subject;
        input6.value = data.data.reg_user;
        input8.value = data.data.reg_date;
        input10.value = data.data.views;
        input12.value = data.data.upd_user;
        input14.value = data.data.upd_date;
        textarea1.value = data.data.content;
        console.log(data.data);
        console.log("통신 성공");
        // const reg_date_trans = new Date((data.reg_date) * 1000);
        // input8.value = reg_date_trans;
        // console.log(input8.value);
    
    }
})

// 수정하기
function button3Click(event) {
	location.href=`./boardUD.html?key=${post_id}`;
}

// 삭제하기
function button2Click(event) {
	const yn = confirm("게시글을 삭제하시겠습니까?");     
        // if(yn){
        //     alert("지금은 삭제 안되지롱 우하하");
        // }   
        if(yn){
            $.ajax({    
               url      : "http://localhost:8080/selectBoardTestDelete.do",
            //    data     : JSON.stringify(form),
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