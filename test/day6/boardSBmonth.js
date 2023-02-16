// 뒤로가기
function button1Click(event) {
	history.back();
}

// 달력 선택 
function calendar1Select(event) {
	let date = event.args["date"];
    let date2 = date.getFullYear() +
	'-' + ( (date.getMonth()+1) < 9 ? "0" + (date.getMonth()+1) : (date.getMonth()+1) )+
	'-' + ( (date.getDate()) < 9 ? "0" + (date.getDate()) : (date.getDate()) );
    console.log("선택한 날짜: " + date2);

	$.ajax({
        type : "POST",
        url  : "http://localhost:8080/BoardTestSBmonth.do",
        data : JSON.stringify({"reg_date" : date}),
        contentType: "application/json",
        dataType : "json",
        success : function(result){
            console.log("result.data: ", result.data);
            grid1.setData(result.data);
        }, 
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("에러 발생")
         }
    })

	$.ajax({
        type : "POST",
        url  : "http://localhost:8080/BoardTestSBmonthCNT.do",
        data : JSON.stringify({"reg_date" : date}),
        contentType: "application/json",
        dataType : "json",
        success : function(result){
            console.log("result.data: ", result.data);
            grid3.setData(result.data);

        }, 
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert("에러 발생")
         }
    })


    $.ajax({
        type : "POST",
        url  : "http://localhost:8080/BoardTestSBmonthSelect.do",
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

