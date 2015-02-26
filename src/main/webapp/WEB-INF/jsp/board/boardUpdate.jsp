<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/inc/taglib.jsp"%>
<div class="sec">
    <ul class="content_nav floatR">
        <li><img src="../images/common/home_icon.gif" width="21" height="10" alt="홈" /></li>
        <li>자료실</li>
		<li class="recent">공개 자료실</li>
    </ul>
    <h3>공개 자료실</h3>
    <table class="board_write_a" summary="기사자료 입력 내용입니다.">
        <caption>공개 자료실 입력</caption>
        <colgroup>
            <col width="70" />
            <col />
        </colgroup>
        <thead>
			<!--             
			<tr>
                <th scope="row" class="line_bottom"><label for="name">작성자</label></th>
                <td class="line_bottom title"><input type="text" id="name" value="" class="txt_box3" style="width:100px;" /></td>
            </tr> 
            -->
            <tr>
                <th scope="row"><label for="name1">제목</label></th>
                <td><input type="text" id="name1" value="" class="txt_box3" style="width:610px;" /></td>
            </tr>
            <!-- 
            <tr>
                <th scope="row"><label for="name2">비밀번호</label></th>
                <td><input type="password" id="name2" value="" class="txt_box3" style="width:100px;" /><span>(4자리 이하)</span></td>
            </tr> 
            -->
        </thead>
        <tbody>
            <tr>
                <td colspan="2">
                    <textarea name="txt_content" id="txt_content" class="scroll_wrap"> </textarea>
                </td>
            </tr>
        </tbody>
    </table>

    <!-- btn -->
    <div class="btn_wrap_c">
        <a href="#none" class="btn_left"><img src="../images/common/btn/btn_register.jpg" style="width: 84px; height: 40px;" alt="등록" /></a>
        <a href="#none" class="btn_left"><img src="../images/common/btn/btn_cancle.jpg" style="width: 84px; height: 40px;" alt="취소" /></a>
    </div>
</div>    