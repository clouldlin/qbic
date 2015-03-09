<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/inc/taglib.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/board/board.js"></script>
<div class="sec">
    <ul class="content_nav floatR">
        <li><img src="../images/common/home_icon.gif" width="21" height="10" alt="홈" /></li>
        <li>자료실</li>
		<li class="recent">공개 자료실</li>
    </ul>
    <h3>공개 자료실</h3>
    
    <form name="boardFrm" id="boardFrm" method="post" enctype="multipart/form-data">
	    <table class="board_write_a" summary="기사자료 입력 내용입니다.">
	        <caption>공개 자료실 입력</caption>
	        <colgroup>
	            <col width="120" />
	            <col />
	        </colgroup>
	                <tbody>
				<!--             
				<tr>
	                <th scope="row" class="line_bottom"><label for="name">작성자</label></th>
	                <td class="line_bottom title"><input type="text" id="name" value="" class="txt_box3" style="width:100px;" /></td>
	            </tr> 
	            -->
	            <tr>
	                <th scope="row"><label for="title">제목</label></th>
	                <td><input type="text" id="title" name="title" value="" class="txt_box3" style="width:580px;" /></td>
	            </tr>
	            <tr>
	            	<th scope="row"><label for="name2">내용</label></th>
	                <td colspan="2">
	                    <textarea name="txt_content" id="txt_content" class="scroll_wrap"></textarea>
	                </td>
	            </tr>
	            <tr>
	                <th scope="row">
	                	<a id="FILE_ADD"></a>
		                <label for="name3">
		                	첨부파일&nbsp;[<a id="btn_fileAdd" href="#FILE_ADD" onclick="fn_addFile();">추가</a>]
		                	<input type="hidden" id="fileNumbers" name="fileNumbers" value="0" />
		                </label>
	                </th>
	                <td><table border="0" id="fileArea"></table></td>
	            </tr> 
	        </tbody>
	    </table>
	</form>

    <!-- btn -->
    <div class="btn_wrap_c">
        <a href="#none" id="boardWrite" class="btn_left"><img src="${pageContext.request.contextPath}/images/common/btn/btn_register.jpg" style="width: 84px; height: 40px;" alt="등록" /></a>
        <a href="${pageContext.request.contextPath }/board/list.do" class="btn_left"><img src="${pageContext.request.contextPath}/images/common/btn/btn_cancel.jpg" style="width: 84px; height: 40px;" alt="취소" /></a>
    </div>
</div>    