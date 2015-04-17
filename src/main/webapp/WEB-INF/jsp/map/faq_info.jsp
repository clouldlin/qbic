<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java" %> 
<%@ include file="/WEB-INF/jsp/common/inc/taglib.jsp"%>
<c:import url="/WEB-INF/jsp/common/inc/map/common.jsp" />
<style>
		<!--
			/* First Level UL List */
			#accordion
			{
				margin:0;
				padding:0;	
				list-style:none;
			}
			
				#accordion li
				{
					width:733px;
					border:1px solid #ddd;
				}

				#accordion .q_list
				{
					margin-bottom:11px;
				}
			
				#accordion li a 
				{
					display: block;
					width: 733px;
					height: 44px;	
					text-indent:-999em;
					outline:none;
				}

				#accordion .heavy a 
				{
					display: block;
					width: 733px;
					height: 58px;	
					text-indent:-999em;
					outline:none;
				}
				
				/* Using CSS Sprite for menu item */
				#accordion li a.q1 
				{
					background:url(../images/map/faq/q_list.png) no-repeat 0px 0px;	
				}

				#accordion li a.q1:hover, .q1Over 
				{
					background:url(../images/map/faq/q_list.png) no-repeat -734px 0px !important;	
				}
				
				#accordion li a.q2 
				{
					background:url(../images/map/faq/q_list.png) no-repeat 0px -58px;	
				}

				#accordion li a.q2:hover, .q2Over 
				{
					background:url(../images/map/faq/q_list.png) no-repeat -734px -58px !important;	
				}
				
				#accordion li a.q3 
				{
					background:url(../images/map/faq/q_list.png) no-repeat 0px -116px;	
				}

				#accordion li a.q3:hover, .q3Over
				{
					background:url(../images/map/faq/q_list.png) no-repeat -734px -116px !important;	
				}
				#accordion li a.q4
				{
					background:url(../images/map/faq/q_list.png) no-repeat 0px -174px;	
				}

				#accordion li a.q4:hover, .q4Over
				{
					background:url(../images/map/faq/q_list.png) no-repeat -734px -174px !important;	
				}
				#accordion li a.q5
				{
					background:url(../images/map/faq/q_list.png) no-repeat 0px -232px;	
				}

				#accordion li a.q5:hover, .q5Over
				{
					background:url(../images/map/faq/q_list.png) no-repeat -734px -232px !important;	
				}
				#accordion li a.q6
				{
					background:url(../images/map/faq/q_list.png) no-repeat 0px -303px;	
				}

				#accordion li a.q6:hover, .q6Over
				{
					background:url(../images/map/faq/q_list.png) no-repeat -734px -303px !important;	
				}
				#accordion li a.q7
				{
					background:url(../images/map/faq/q_list.png) no-repeat 0px -361px;	
				}

				#accordion li a.q7:hover, .q7Over
				{
					background:url(../images/map/faq/q_list.png) no-repeat -734px -361px !important;	
				}
				#accordion li a.q8
				{
					background:url(../images/map/faq/q_list.png) no-repeat 0px -419px;	
				}

				#accordion li a.q8:hover, .q8Over
				{
					background:url(../images/map/faq/q_list.png) no-repeat -734px -419px !important;	
				}
				#accordion li a.q9
				{
					background:url(../images/map/faq/q_list.png) no-repeat 0px -477px;	
				}

				#accordion li a.q9:hover, .q9Over
				{
					background:url(../images/map/faq/q_list.png) no-repeat -734px -477px !important;	
				}
				#accordion li a.q10
				{
					background:url(../images/map/faq/q_list.png) no-repeat 0px -535px;	
				}

				#accordion li a.q10:hover, .q10Over
				{
					background:url(../images/map/faq/q_list.png) no-repeat -734px -535px !important;	
				}
				#accordion li a.q11
				{
					background:url(../images/map/faq/q_list.png) no-repeat 0px -594px;	
				}

				#accordion li a.q11:hover, .q11Over
				{
					background:url(../images/map/faq/q_list.png) no-repeat -734px -594px !important;	
				}
				#accordion li a.q12
				{
					background:url(../images/map/faq/q_list.png) no-repeat 0px -651px;	
				}

				#accordion li a.q12:hover, .q12Over
				{
					background:url(../images/map/faq/q_list.png) no-repeat -734px -651px !important;	
				}
				#accordion li a.q13
				{
					background:url(../images/map/faq/q_list.png) no-repeat 0px -709px;	
				}

				#accordion li a.q13:hover, .q13Over
				{
					background:url(../images/map/faq/q_list.png) no-repeat -734px -709px !important;	
				}
				
				
				/* Second Level UL List*/
				#accordion ul
				{
					width:733px;
					margin:0;
					padding:0;
					display:none;	
				}
				
					#accordion ul li
					{
					}

					#accordion li .answer
					{
					background:#efefef;
					border:0px;
					}
					
					/* styling of submenu item */
					#accordion ul li a
					{
						width:733px;
						text-indent:0;
						color:#ccc;
						text-decoration:none;
					}

					/* remove border bottom of the last item */
					#accordion ul li a.last
					{
						border-bottom: none;
					}	
		//-->			
		</style>

<script type="text/javascript">
$(document).ready(function ()
{
	$('#accordion a.item').click(function ()
	{
		//slideup or hide all the Submenu
		$('#accordion li').children('ul').slideUp('fast');	
		//remove all the "Over" class, so that the arrow reset to default
		$('#accordion a.item').each(function ()
		{
			if ($(this).attr('rel')!='')
			{
				$(this).removeClass($(this).attr('rel') + 'Over');	
			}
		});
		
		//show the selected submenu
		$(this).siblings('ul').slideDown('fast');
		
		//add "Over" class, so that the arrow pointing down
		$(this).addClass($(this).attr('rel') + 'Over');			

		return false;

	});

});	
</script>
</head>
<body class="jui"> 
<!-- wrap start -->
<div id="content_map">
	<!-- header start -->
	<div id="header_map">
		<c:import url="/WEB-INF/jsp/common/inc/map/header.jsp" />
	</div>
	<c:import url="/WEB-INF/jsp/common/inc/map/login.jsp" />

	<div class="content_area">
		<ul id="accordion">
			<li class="q_list">
				<a href="#" class="item q1" rel="q1">Popular Post</a>
				<ul>
					<li class="answer"><img src="${pageContext.request.contextPath}/images/map/faq/1.png"></li>
				</ul>
			</li>
			<li class="q_list">
				<a href="#" class="item q2" rel="q2">Popular Post</a>
				<ul>
					<li class="answer"><img src="${pageContext.request.contextPath}/images/map/faq/2.png"></li>
				</ul>
			</li>
			<li class="q_list">
				<a href="#" class="item q3" rel="q3">Popular Post</a>
				<ul>
					<li class="answer"><img src="${pageContext.request.contextPath}/images/map/faq/3.png"></li>
				</ul>
			</li>
			<li class="q_list">
				<a href="#" class="item q4" rel="q4">Popular Post</a>
				<ul>
					<li class="answer"><img src="${pageContext.request.contextPath}/images/map/faq/4.png"></li>
				</ul>
			</li>
			<li class="q_list heavy">
				<a href="#" class="item q5" rel="q5">Popular Post</a>
				<ul>
					<li class="answer"><img src="${pageContext.request.contextPath}/images/map/faq/5.png"></li>
				</ul>
			</li>
			<li class="q_list">
				<a href="#" class="item q6" rel="q6">Popular Post</a>
				<ul>
					<li class="answer"><img src="${pageContext.request.contextPath}/images/map/faq/6.png"></li>
				</ul>
			</li>
			<li class="q_list">
				<a href="#" class="item q7" rel="q7">Popular Post</a>
				<ul>
					<li class="answer"><img src="${pageContext.request.contextPath}/images/map/faq/7.png"></li>
				</ul>
			</li>
			<li class="q_list">
				<a href="#" class="item q8" rel="q8">Popular Post</a>
				<ul>
					<li class="answer"><img src="${pageContext.request.contextPath}/images/map/faq/8.png"></li>
				</ul>
			</li>
			<li class="q_list">
				<a href="#" class="item q9" rel="q9">Popular Post</a>
				<ul>
					<li class="answer"><img src="${pageContext.request.contextPath}/images/map/faq/9.png"></li>
				</ul>
			</li>
			<li class="q_list">
				<a href="#" class="item q10" rel="q10">Popular Post</a>
				<ul>
					<li class="answer"><img src="${pageContext.request.contextPath}/images/map/faq/10.png"></li>
				</ul>
			</li>
			<li class="q_list">
				<a href="#" class="item q11" rel="q11">Popular Post</a>
				<ul>
					<li class="answer"><img src="${pageContext.request.contextPath}/images/map/faq/11.png"></li>
				</ul>
			</li>
			<li class="q_list">
				<a href="#" class="item q12" rel="q12">Popular Post</a>
				<ul>
					<li class="answer"><img src="${pageContext.request.contextPath}/images/map/faq/12.png"></li>
				</ul>
			</li>
			<li class="q_list heavy">
				<a href="#" class="item q13" rel="q13">Popular Post</a>
				<ul>
					<li class="answer"><img src="${pageContext.request.contextPath}/images/map/faq/13.png"></li>
				</ul>
			</li>
		</ul>
	</div>
</div>
	
<!-- footer start -->
<div id="footer">
	<c:import url="/WEB-INF/jsp/common/inc/map/footer.jsp" />
</div>
<!-- footer end -->
<!-- wrap end -->
</body>
</html>