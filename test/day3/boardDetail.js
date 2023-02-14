function boardDetail(){

     $.ajax({
         type : "POST",
         url : "http://localhost:8080/selectBoardTest.do",
         data : JSON.stringify({"subject": edit01.value}), 
         contentType : "application/json",
         dataType : "json", 
         success : function(result) {
             var RecNo = event.args["RecNo"];
             textarea1.getValue("content",RecNo)
         },
         error : function(XMLHttpRequest, textStatus, errorThrown){
             alert("에러 발생")
          }
      });

 }

 boardDetail();