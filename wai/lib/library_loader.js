(() => {
    const params = scriptQuery();
    const libUse = $wai.default.libUse ?? "N";
    if(libUse.toUpperCase() == "Y"){
        const xmlhttp = new XMLHttpRequest();
        xmlhttp.open("GET", `${$wai.default.libPath}/libInfo.json`, false);
        xmlhttp.send();
        if (xmlhttp.readyState === 4 && xmlhttp.status == "200") {
            let content = xmlhttp.responseText;
            let libInfo = JSON.parse(content).INFO;
            for(let i in libInfo){
                let libId = libInfo[i].LIBRARY_ID;
                if(libId == params.libId){
                    //let autoLoadYn = libInfo[i].AUTO_LOAD_YN;
                    let arrOrgJqKey = Object.keys($.prototype);
                    let fileInfo = libInfo[i].FILE;
                    document.write('<!--wai.library.load.start-->');
                    for(let j in fileInfo){
                        if(fileInfo[j].IMPORT_YN == 'Y'){
                            let sFilePath = "/lib/" + libId + "/" + fileInfo[j].FILE_NAME;
                            let sFileExt = getExtensionOfFilename(fileInfo[j].FILE_NAME);
                            if(sFileExt == ".js") {
                                if(document.querySelector(`script[src="${sFilePath}"]`) == null){
                                    document.write(`<script type='text/javascript' src='${sFilePath}'></script>`);
                                    const scriptElem = document.querySelector(`script[src="${sFilePath}"]`);
                                    scriptElem.addEventListener("load", function(event) {
                                        let objArg = {
                                            orgJqKey : arrOrgJqKey
                                        };
                                        scriptLoaded(libInfo[i], objArg);
                                    });
                                }
                            }
                            else if(sFileExt == ".css") {
                                if(document.querySelector(`link[href="${sFilePath}"]`) == null){
                                    document.write(`<link rel='stylesheet' type='text/css' href='${sFilePath}'>`);
                                }
                            }
                        }
                    }
                    document.write('<!--wai.library.load.end-->');
                    break;
                }
            }
        }

        function scriptLoaded(libInfo, objArg){
            //console.log("scriptLoaded --> ", libInfo, objArg, Object.keys($.prototype));
            if(Object.keys(parent?.parent ?? {}).length > 1){
                let arrJqKey = [];
                if(["03", "04"].includes(libInfo.LIBRARY_TYPE_ID)) { // 03 : jQuery Plug-in, 04 : jQuery Based Library
                    const orgKeySize = objArg.orgJqKey.length;
                    const curKeySize = Object.keys($.prototype).length;
                    if(orgKeySize < curKeySize){
                        arrJqKey = Object.keys($.prototype).slice(orgKeySize - curKeySize);
                    }
                }
                let arrUseComp = (libInfo.USE_COMP ?? '').split(', ');
                arrJqKey = arrJqKey.filter(key => arrUseComp.includes(key));
                if(parent?.parent?.addLibComp !== undefined && parent?.parent?.$libComponentList !== undefined){
                    parent?.parent?.addLibComp(libInfo, arrJqKey);
                }
            }
        }
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

    function getExtensionOfFilename(filename) {

        var _fileLen = filename.length;

        /**
         * lastIndexOf('.')
         * 뒤에서부터 '.'의 위치를 찾기위한 함수
         * 검색 문자의 위치를 반환한다.
         * 파일 이름에 '.'이 포함되는 경우가 있기 때문에 lastIndexOf() 사용
         */
        var _lastDot = filename.lastIndexOf('.');

        // 확장자 명만 추출한 후 소문자로 변경
        var _fileExt = filename.substring(_lastDot, _fileLen).toLowerCase();

        return _fileExt;
    }

})();