/********************************************************************************
 * 기      능   	:  load
 * @param obj	: 
 * @return  		: 
 ********************************************************************************/
	 $(window).load(function(){
		 
	 });
 
/********************************************************************************
 * 기      능   	:  ready
 * @param obj	: 
 * @return  		: 
 ********************************************************************************/
	 $(document).ready(function(){
	
/********************************************************************************
* 기      능   	:	게시물 삭제
* @param obj 	: 
* @return  		: 
********************************************************************************/
		$('#boardDel').on('click',function(){
			if (!confirm("삭제 하시겠습니까?")) return false;
			$('#boardFrm').attr('action', baseUrl+'board/delete.do');
			$('#boardFrm').submit();
		});
	});

/********************************************************************************
 * 기      능   	:  페이징 처리
 * @param obj	:
 * @return  	:
 ********************************************************************************/
	 var fn_linkPage = function(pageIndex){
		 var f = document.boardFrm;
		 f.pageIndex.value = pageIndex;
		 f.action = baseUrl+"board/list.do";
		 f.submit();
	 };
	 
	 
	 
	 
	 

	// ### 파일객체 추가
	function FnAddFile(){
		// ### 파일 갯수를 설정한다. 화면 스크립트처리용.
		var curFileCnt = Number($("#fileSu").val());

		var fileStr = new StringBuffer();
		fileStr.append("<tr id='TrFile"+curFileCnt+"'> \n");
		fileStr.append("	<td height='20'> \n");
		fileStr.append("		<input type='file' id='ObjFile"+curFileCnt+"' name='ObjFile"+curFileCnt+"' /> \n");
		fileStr.append("		<span id='SpanBtn"+curFileCnt+"'> \n");
		fileStr.append("		<a href='#' id='BtnFile"+curFileCnt+"' onclick=FnDeleteFile('TrFile"+curFileCnt+"','"+curFileCnt+"')>[삭제]</a>\n");
		fileStr.append("		</span> \n");
		fileStr.append("	</td> \n");
		fileStr.append("</tr> \n");
		
		// ### 파일 객체추가.
		$("#fileArea").append(fileStr.toString());
		
		// ### 현재 파일 갯수를 설정한다.
		$("#fileSu").val(curFileCnt+1);
	}

	// ### 파일객체 삭제
	function FnDeleteFile(delId, curCnt){
		// ### 파일 갯수를 설정한다. 화면 스크립트처리용.
		var curFileCnt = Number($("#fileSu").val());
		
		// ### 테이블 로우 갯수
		//var tableRowCnt = $("#fileArea").attr('rows').length;
		var tableId = document.getElementById("fileArea");		
		var tableRowCnt= tableId.rows.length;
		
		// ### 삭제될 인덱스
		var delCnt = Number(curCnt);

		// ### 파일 객체 삭제.
		$("#"+delId).remove();
		
		for(var i=(delCnt+1); i<= tableRowCnt; i++){
			$("#SpanBtn"+(i)).html("<a href='#' id='BtnFile"+(i-1)+"' onclick=FnDeleteFile('TrFile"+(i-1)+"','"+(i-1)+"')>[삭제]</a>");
			//$("#SpanBtn"+(i)).attr("id"  , "SpanBtn"+(i-1) ); // 파일객체 아이디
			$("#ObjFile"+(i)).attr("name", "ObjFile"+(i-1) ); // 파일객체 명
			$("#ObjFile"+(i)).attr("id"  , "ObjFile"+(i-1) ); // 파일객체 아이디
			$("#TrFile"+(i)).attr("id"  , "TrFile"+(i-1) );   // 로우 아이디
		}

		// ### 다음 추가될 파일의 인덱스
		$("#fileSu").val((curFileCnt-1));

	}


