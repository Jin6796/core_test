(function(){
    const params = scriptQuery();
    const bWaiIDE = params?.isWaiIDE ?? 'N';

    let waiScript = '/wai/waiRuntime.js';
    let topPage   = (window.parent.location.pathname+'').split("/").pop();
    try{
        topPage   = (window.top.location.pathname+'').split("/").pop();
    }catch(e){};
    
    let curPage   = (window.location.pathname+'').split("/").pop();
    //let isWaiIDE  = (topPage == 'waiIDE.html')||(topPage == 'waiHtmlEditorMain.html');
    let isWaiIDE  = (topPage == 'waiHtmlEditor.html')||(topPage == 'waiHtmlEditorMain.html');
    if(bWaiIDE == 'Y'){
        isWaiIDE = true;
    }

    //document.write('<!--wai.load.start--><script type="text/javascript" src="'+waiScript+'">'+"</script>\n");
	document.write('<!--waipage.load.start--><script type="text/javascript" src="'+waiScript+'">'+"</script><!--waipage.load.end-->\n");
    if (isWaiIDE) {
        // is Editing on Y[wai] IDE

    } else {
        // is Running
        
        let pageScript = './'+curPage.split(".")[0]+'.js';
        document.write('<script type="text/javascript" src="'+pageScript+'">\n'+"</script>");
    }

    function scriptQuery() {
        var script = document.getElementsByTagName('script');
        script = script[script.length-1].src
            .replace(/^[^\?]+\?/, '')
            .replace(/#.+$/, '')
            .split('&');
        var queries = {}
            , query;
        while(script.length){
            query = script.shift().split('=');
            queries[query[0]] = query[1];
        }
        return queries;
    }
})();