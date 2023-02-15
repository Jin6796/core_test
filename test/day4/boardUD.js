function boardDetail(){
    var RecNo = event.args["RecNo"];
    const post_id = grid1.getValue("post_id", RecNo);
     $.ajax({
         type : "POST",
         url : "http://localhost:8080/selectBoardTest.do?post_id="+post_id,
         data : JSON.stringify({"subject": edit01.value}), 
         //{grid1.getValue("post_id",RecNo)}
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