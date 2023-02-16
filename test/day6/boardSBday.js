// 뒤로가기
function button1Click(event) {
	history.back();
}

// 날짜 선택
function selectDate(event){
    let date = event.args["date"];
    // console.log("선택한 날짜: ", date);
    $.ajax({
        type : "POST",
        url  : "http://localhost:8080/BoardTestSBday.do",
        data : JSON.stringify({"reg_date" : date}),
        contentType: "application/json",
        dataType : "json",
        success : function(result){
            // console.log("result.data: ", result.data);
            // console.log("length: ", result.data.length);
            grid1.setData(result.data);
            $('input[name=inputValue]').attr('value', result.data.length+" 개");
        }, 
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("에러 발생")
         }
    })

    $.ajax({
        type : "POST",
        url  : "http://localhost:8080/BoardTestSBdaySelect.do",
        data : JSON.stringify({"reg_date" : date}),
        contentType: "application/json",
        dataType : "json",
        success : function(result){
            grid2.setData(result.data);
        }, 
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("에러 발생")
         }
    })
}

