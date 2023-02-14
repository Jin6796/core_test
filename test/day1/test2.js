function searchBook(title){
    const target = codecombo1.value;
    const search = edit2.value;
    $.ajax({
        method: "GET",
        url: "https://dapi.kakao.com/v3/search/book?target="+target+"&page=1&size=20&query="+search,
        headers: {'Authorization': 'KakaoAK 3ed7f53994018c83b7fdb62c9fa9d179'},
        success : function(data){
            // alert(target);
            // alert(search);
            console.log("---> ", data);
            grid1.setData(data.documents);
        },
        error : function(xhr, code){
            alert("검색어를 입력해주세요");
        }
    });
}

function button1Click(event) {
	searchBook();
}
