<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java"%>
<%@ include file="/WEB-INF/jsp/common/inc/taglib.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/board/board.js"></script>
<div class="sec">
	<ul class="content_nav floatR">
		<li><img src="../images/common/home_icon.gif" width="21" height="10" alt="홈"  style="vertical-align: middle;"/></li>
		<li>자료실</li>
		<li class="recent">공개 자료실</li>
	</ul>
	<h3>공개 자료실</h3>
	<div class="board_wrap">
		<div class="board_wrap_left relative">
			<a href="${pageContext.request.contextPath }/common/page.do?page=board/boardUpdate.jsp"><img src="${pageContext.request.contextPath }/images/common/btn/btn_write.gif" alt="글쓰기"></a>
		</div>
		<div class="board_wrap_right ">
			<fieldset class="board_search">
				<legend>검색</legend>
				<select name="" title="검색분류 선택" style="width: 80px; height: 23px;">
					<option value="">전체</option>
				</select> 
				<input type="text" title="검색어 입력" class="txt_box3" value="" />
				<input type="image" src="${pageContext.request.contextPath }/images/common/btn/btn_a_search.gif" alt="검색" />
			</fieldset>
		</div>
	</div>

	<form name="boardFrm" id="boardFrm" method="post" action="">
		<input type="hidden" name="pageIndex" id="pageIndex" />
	</form>
	
	<table class="board_list_a" summary="공지사항 내용입니다.">
		<caption>공개 자료실</caption>
		<colgroup>
			<col width="65" />
			<col />
			<col width="70" />
			<col width="100" />
			<col width="70" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col">글번호</th>
				<th scope="col">제목</th>
				<th scope="col">첨부파일</th>
				<th scope="col">등록일</th>
				<th scope="col">조회수</th>
			</tr>
		</thead>
		<tbody>
	        <c:forEach var="list" items="${boardList}" varStatus="status">
				<tr id="boardList_${status.index}">
					<td><c:out value="${listTotal + 1 - ((commonVO.pageIndex - 1) * commonVO.pageSize + status.count)}"/></td>
					<td class="txt_left"><a href="http://localhost:9999/qbic/board/detail.do?seq=${list.seq}&pageIndex=${pageIndex}&txt_search=${txt_search}&keyword=${keyword}">${list.title}</a></td>
					<td><img src="${pageContext.request.contextPath }/images/common/btn/btn_file.gif" alt="다운로드"></td>
					<td>${list.regDate}</td>
					<td class="line_none">${list.hitCount}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty boardList}">
				<tr>
					<td colspan="5" class="line_none">검색결과가 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
	</table>
		
	<div class="paginate" style="padding-top:50px;">
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_linkPage" />
	</div>
</div>