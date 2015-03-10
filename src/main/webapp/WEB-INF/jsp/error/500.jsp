<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<title>System Error</title>
<meta charset="UTF-8" />
</head>
<body bgcolor=#FFFFFF text=#000000>
	<table width=700 border=0 cellspacing=0 cellpadding=0 align=center>
		<tr>
			<td></td>
		</tr>
	</table>
	<table width=700 border=0 cellspacing=1 cellpadding=2 align=center
		bgcolor=#CCCCCC>
		<tr>
			<td bgcolor=A0B8C8 height=20>
				<div align=&quot;left&quot;>
					<font size=2><b> <font color=#FFFFFF>시스템 장애</font></b></font>
				</div>
			</td>
		</tr>
		<tr>
			<td bgcolor=#FFFFFF>
				<table width=95% border=0 cellspacing=0 cellpadding=0 align=center>
					<tr>
						<td><font size=2 color=#336699><br> <font
								color=#FF0000><b>해당 시스템에 문제가 있어서 페이지를 표시할 수
										없습니다. (Page Not Found Error)</b></font> <font color=#333366><br>
								<br>
								<br>
								<p>죄송합니다. 요청하신 페이지는 시스템에 문제가 있어서 표시할 수 없습니다.</font>
								<p>
									<font size=2 color=#333366>허용되지 않은 페이지에 접근을 시도하였거나 요청한
										페이지가 시스템내 존재하지 않습니다.</font>
								</p>
								<p>
									<font size=2 color=#333366><b> 
									<script type="text/javascript" src="http://jsgetip.appspot.com"></script>
									<script type="text/javascript">
										var today = new Date();
										document.write("접속 시각은 " + today.toLocaleString() + "<br><br>");
										document.write("접속 아이피는 "	+ ip() + "<br><br>");
										document.write("접속 시도한 URL은 " + document.location.href + "<br><br>");
									</script></b></font>
								</p>
								<p>
									<font size=2 color=#333366>연락 주실때 위의 굵은 글씨체로 된 정보와 하단의
										메시지ID를 함께 알려주시기 바랍니다.</font>
								</p> 
								<script type="text/javascript">
									dns = document.location.href;
									arrDns = dns.split("//");
									str = arrDns[0] + "//" + arrDns[1].substring(0, arrDns[1].indexOf("/"));
									document.write("<p><font size=2 color=#333366>접속한 메인 도메인 바로가기 : <b><a href="+str+">" + str + "</a></b></font></p>");
								</script></b></font>
							</p>
							<p>
								<font size=2 color=#333366><a href=javascript:history.back()><b>이전 페이지로 이동</b></a>도 가능합니다.</font>
							</p>
							<p>
								<font size=2 color=#999999><center>--------------------------------------------------------------------------------</center></font>
							</p>
							<p>
								<font size=2 color=#333366><center>
										<b>전산실 연락처 : webmaster@koti.re.kr</b>
									</center></font>
							</p> <font size=2 color=#FF0000><center>메시지 CODE : 500 Error</center> </font></td>
					</tr>
				</table> <br>
			</td>
		</tr>
	</table>
</body>
</html>
