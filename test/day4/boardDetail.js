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
        textarea1.value = data.data.content;
        console.log(data.data);
        console.log("통신 성공");
        // const reg_date_trans = new Date((data.reg_date) * 1000);
        // input8.value = reg_date_trans;
        // console.log(input8.value);
    
    }
})