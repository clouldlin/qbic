 /********************************************************************************
 * 기      능   	:  페이징 처리
 * @param obj	:
 * @return  	:
 ********************************************************************************/
 var fn_linkPage = function(pageIndex){
	 var f = document.boardFrm;
	 f.pageIndex.value = pageIndex;
	 f.action = baseUrl+"admin/list.do";
	 f.submit();
 };

