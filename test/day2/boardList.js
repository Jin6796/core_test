function button1Click(event){
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


 