

function serviceCall_test(){
	// $wai.classes.serviceCallById("test");
	var sServiceId = "test";
	var sUrl = "https://uitool.org:8443/serviceCallTest";
	var objInput = {inData1 : "TEST_input1"};
	var objOutput = {outData1 : "TEST_output1", outData2 : "TEST_output2"};
	var objArg = {};
	var objOpt = {type : "POST", async : "false", timeout : "5000", dataType : "JSON", charset : "UTF-8", headers : {}};
	var fnCallback = null;
	var fnBeforeCall = null;
	var fnAfterCall = null;
	$wai.classes.serviceCall(sServiceId, sUrl, objInput, objOutput, objArg, objOpt, fnCallback, fnBeforeCall, fnAfterCall);
}

function button1Click(event) {
	serviceCall_test();
}

