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
    <table class="board_view_a" summary="기사자료 내용입니다.">
        <caption>공개 자료실 내용</caption>
        <colgroup>
            <col width="70" />
            <col />
            <col width="70" />
            <col width="80" />
        </colgroup>
        <thead>
            <tr>
                <th scope="row" class="line_bottom">제목</th>
                <td colspan="3" class="line_bottom line_title">${detailView.title}</td>
            </tr>
			<!--             
			<tr>
                <th scope="row" >작성자</th>
                <td colspan="3">홍길동</td>
            </tr> 
            -->
            <tr>
                <th scope="row" >작성일</th>
                <td>${detailView.regDate}</td>
                <th scope="row" >조회수</th>
                <td class="txt_center">${detailView.hitCount}</td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td title="내용" colspan="4">
                    <div class="board_view_suj">${detailView.content}</div>
                </td>
            </tr>
        </tbody>
    </table>
    
    <form name="boardFrm" id="boardFrm" method="post">
    	<input type="hidden" id="seq" name="seq" value="${detailView.seq}"/>
    </form>
    
    <div class="btn_wrap_c">
        <div  class="btn_left">
            <a href="http://localhost:9999/qbic/board/update.do"><img src="../images/common/btn/btn_modify.jpg" alt="수정" /></a>
            <a href="#" id="boardDel"><img src="../images/common/btn/btn_del.jpg" alt="삭제" /></a>
        </div>
        <div  class="btn_right">
        	<!-- <a href="#none"><img src="../images/common/btn/btn_reply.jpg" alt="답글달기" /></a> -->
            <a href="${pageContext.request.contextPath }/board/list.do"><img src="../images/common/btn/btn_list.jpg" alt="목록" /></a>
        </div>
    </div>
	
	<!--     
	<div class="reply_wrap">
       <table class="board_reply_a" summary="답글 입력 내용입니다.">
         <caption>답글 입력</caption>
         <colgroup>
             <col width="70" />
             <col />
         </colgroup>
         <thead>
             <tr>
                 <th scope="row"><label for="name1">댓글</label></th>
                 <td><input type="text" id="name1" value="" class="txt_box3" style="width:610px;" /></td>
             </tr>
         </thead>
         <tbody>
             <tr>
                 <td colspan="2">
                     <textarea name="txt_content" id="txt_content" class="scroll_wrap"> </textarea>
                 </td>
             </tr>
         </tbody>
    	</table>
    	<div class="btn_right_reply">
        	<a href="#none"><img src="../images/common/btn/btn_reply_ok.jpg" alt="답글달기" width="70px;"/></a>
        </div>
    </div> 
    -->
</div>    