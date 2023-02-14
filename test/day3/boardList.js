// 페이지 이동을 위한 함수 선언
const goToDetail = () => {
    console.log("선택한 id의 게시글 정보");

  }

// 검색
function button1Click(event){
    const keyword = edit1.value;
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
    var grid = event.args["grid"];
    var column = event.args["column"];
    var Index = event.args["Index"];
    var RecNo = event.args["RecNo"];
    
    console.log("게시글 id: " + grid1.getValue("post_id",RecNo));
    console.log("================================");
    console.log("선택한 id의 게시글 정보");
    console.log(grid1.getValue("post_id"));
    
    location.href = "http://localhost:8080/selectBoardTestDetail.do"
}
