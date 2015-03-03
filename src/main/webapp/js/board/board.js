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
	 
 /********************************************************************************
  * 기      능   	:  파일객체 추가
  * @param obj	:
  * @return  	:
  ********************************************************************************/
	var fn_addFile = function(){
		
		var curFileCnt = Number($("#fileNumbers").val());

		var fileStr = new StringBuffer();
		fileStr.append("<tr id='trFile_"+curFileCnt+"'> \n");
		fileStr.append("	<td height='20'> \n");
		fileStr.append("		<input type='file' id='file_"+curFileCnt+"' name='file_"+curFileCnt+"' /> \n");
		fileStr.append("		<span id='spanBtn_"+curFileCnt+"'> \n");
		fileStr.append("			<a href='#' id='btnFile_"+curFileCnt+"' onclick=fn_deleteFile('trFile_"+curFileCnt+"','"+curFileCnt+"')>[삭제]</a>\n");
		fileStr.append("		</span> \n");
		fileStr.append("	</td> \n");
		fileStr.append("</tr> \n");
		
		$("#fileArea").append(fileStr.toString());
		
		$("#fileNumbers").val(curFileCnt+1);
	};
	
 /********************************************************************************
  * 기      능   	:  파일객체 삭제
  * @param obj	:
  * @return  	:
  ********************************************************************************/	

	var fn_deleteFile = function(delId, curCnt){ 
		var curFileCnt = Number($("#fileNumbers").val());
		
		var tableId = document.getElementById("fileArea");		
		var tableRowCnt= tableId.rows.length;
		
		var delCnt = Number(curCnt);

		$("#"+delId).remove();
		
		for(var i=(delCnt+1); i<= tableRowCnt; i++){
			$("#spanBtn_"+(i)).html("<a href='#' id='btnFile_"+(i-1)+"' onclick=fn_deleteFile('trFile_"+(i-1)+"','"+(i-1)+"')>[삭제]</a>");
			$("#spanBtn_"+(i)).attr("id"  , "spanBtn_"+(i-1) ); // 파일객체 아이디
			$("#file_"+(i)).attr("name", "file_"+(i-1) ); // 파일객체 명
			$("#file_"+(i)).attr("id"  , "file_"+(i-1) ); // 파일객체 아이디
			$("#trFile_"+(i)).attr("id"  , "trFile_"+(i-1) );   // 로우 아이디
		}

		$("#fileNumbers").val((curFileCnt-1));

	};
