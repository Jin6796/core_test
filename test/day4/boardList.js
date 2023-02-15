// 마우스 클릭 검색
function searchClick(event){
    $.ajax({
        type : "POST",
        url : "http://localhost:8080/selectBoardTest.do",
        data : JSON.stringify({"subject": edit1.value}), 
        contentType : "application/json",
        dataType : "json", 
        success : function(result) {
            console.log("======> ", result, result.data);
            grid1.setData(result.data);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("에러 발생")
         }
     });
}

// 엔터키 검색
function searchPress(event) {
    $.ajax({
        type : "POST",
        url : "http://localhost:8080/selectBoardTest.do",
        data : JSON.stringify({"subject": edit1.value}), 
        contentType : "application/json",
        dataType : "json", 
        success : function(result) {
            console.log("======> ", result, result.data);
            grid1.setData(result.data);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("에러 발생")
         }
     });
}

/* 
// 그리드 내에 열 클릭 시 post_id 값 받아오기
function grid1Recordclick(event) {
    var grid = event.args["grid"];
    var column = event.args["column"];
    var RecNo = event.args["RecNo"];
    var ColumnIndex = event.args["ColumnIndex"];
    
    console.log(grid1.getValue("post_id",RecNo));

}
*/

// 더블 클릭 시 post_id 값 받아오기
function grid1Celldblclick(event) {
    const grid = event.args["grid"];
    const column = event.args["column"];
    const Index = event.args["Index"];
    const RecNo = event.args["RecNo"];
    const post_id = grid1.getValue("post_id", RecNo);
    
    console.log("게시글 id: " + post_id);
    
    // location.href = "http://localhost:8080/selectBoardTestDetail.do"
    location.href=`./boardDetail.html?key=${post_id}`;
}

// 글 작성하기 버튼
function button2Click(event) {
    location.href="./boardInsert.html"
}