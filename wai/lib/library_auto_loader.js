(() => {
    const libUse = $wai.default.libUse ?? "N";
    if(libUse.toUpperCase() == "Y"){
        const xmlhttp = new XMLHttpRequest();
        xmlhttp.open("GET", `${$wai.default.libPath}/libInfo.json`, false);
        xmlhttp.send();
        if (xmlhttp.readyState === 4 && xmlhttp.status == "200") {
            content = xmlhttp.responseText;
            var libInfo = JSON.parse(content).INFO;
            document.write("<!--wai.library.autoLoad.start-->");
            for(var i in libInfo){
                if(libInfo[i].AUTO_LOAD_YN == "Y"){
                    const filePath = `/wai/lib/library_loader.js?libId=${libInfo[i].LIBRARY_ID}`;
                    let scriptElem = document.querySelector(`script[src="${filePath}"]`);
                    if(scriptElem == null){
                        document.write(`<script type='text/javascript' src='${filePath}'></script>`);
                    }
                }
            }
            document.write("<!--wai.library.autoLoad.end-->");
        }
    }
})();