/********************************************************************************
* 기      능   	: Ajax 데이터 return 
* @param obj 	: 
* @return  		: result
********************************************************************************/
  function requestData( url, paramsJson, asyncHandler, lodingHandler ) {
		var asyncYn = false;
		if (asyncHandler != null) asyncYn = true;
		var result = null;
		if(paramsJson == "undefined" || paramsJson == null ){
			paramsJson = null;
		} 
		var  xhr = $.ajax({
			  url: url,
			  async: asyncYn, 
			  type:'post',
			  data: paramsJson ,
			  dataType: "json",
			  success: function(transport,status,xhr) {
					result = transport;
			  },
			  error: function(transport,status,xhr) {
				  //alert(url+'\n'+'시스템 관리자에게 문의 바랍니다.');
			  },
			  complete: function(transport,status,xhr) {}
			});
		return result;
	}
  	