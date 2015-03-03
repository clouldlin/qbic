/**
 * 
 */

$(document).ready(function(){
	
	  
	/********************************************************************************
	* 기      능   	: 달력
	* @param obj 	: 
	* @return  		: result
	********************************************************************************/
	$(".datepicker").datepicker({
		showOn : "both", // 버튼과 텍스트 필드 모두 캘린더를 보여준다.
		buttonImage : baseUrl + "images/sub/ico_week.gif", // 버튼 // 이미지
		buttonImageOnly : true, // 버튼에 있는 이미지만 표시한다.
		changeMonth : true, // 월을 바꿀수 있는 셀렉트 박스를 표시한다.
		changeYear : true, // 년을 바꿀 수 있는 셀렉트 박스를 표시한다.
		minDate : '-100y', // 현재날짜로부터 30년이전까지 년을 표시한다.
		nextText : '다음 달', // next 아이콘의 툴팁.
		prevText : '이전 달', // prev 아이콘의 툴팁.
		numberOfMonths : [ 1, 1 ], // 한번에 얼마나 많은 월을
									// 표시할것인가. [2,3] 일
									// 경우, 2(행) x 3(열) =
									// 6개의 월을 표시한다.
		// stepMonths: 3, // next, prev 버튼을 클릭했을때 얼마나 많은
		// 월을 이동하여 표시하는가.
		yearRange : 'c-30:c+5', // 년도 선택 셀렉트박스를 현재 년도에서
								// 이전, 이후로 얼마의 범위를
								// 표시할것인가.
		// showButtonPanel: true, // 캘린더 하단에 버튼 패널을
		// 표시한다.
		// currentText: '오늘 날짜' , // 오늘 날짜로 이동하는 버튼 패널
		closeText : '닫기', // 닫기 버튼 패널
		dateFormat : "yy-mm-dd", // 텍스트 필드에 입력되는 날짜
									// 형식.
		showAnim : "slideDown", // 애니메이션을 적용한다.
		showMonthAfterYear : true, // 월, 년순의 셀렉트 박스를
									// 년,월 순으로 바꿔준다.
		dayNamesMin : [ '월', '화', '수', '목', '금', '토', '일' ], // 요일의 한글 형식.
		monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ] // 월의 한글 형식.

	});
	
	$('.datepicker').datepicker().keydown(function(event) {
	 if (event.which === $.ui.keyCode.ENTER) {
		        event.preventDefault();
		   }
	});

	
	/********************************************************************************
	* 기      능   	: validate
	* @param obj 	: 
	* @return  		: result
	********************************************************************************/
	/*jQuery.validator.setDefaults({		//alert 창 형식
	 onkeyup:false,
	 onclick:false,
	 onfocusout:false,
	 showErrors:function(errorMap, errorList){
	 var caption = $(errorList[0].element).attr('caption') || $(errorList[0].element).attr('name');
	 alert('[' + caption + ']' + errorList[0].message);
	 }
	 });*/
	$.extend(jQuery.validator.messages, {	//메시지 정의
		required: '필수 입력 사항입니다.',
        minlength: '{0}글자 이상 입력해주세요.',
        maxlength: '{0}자 이상  입력할 수 업습니다.',
        email: '이메일 형식에 맞게 입력해주세요.',
        url: 'URL 형식에 맞게 입력해주세요.',
        number : '숫자 형식만 입력 가능 합니다.'
   });
});

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
  
	
  /* =========================================================================*/

  /* =========================================================================
   * 
   * LPAD
   *  
   * =========================================================================
   */
 	  function lPad(value,totalLen,strReplace){

 			var strAdd = "";
 			var diffLen = totalLen - value.length;
 			for(var i=0;i<diffLen;i++){
 				strAdd += strReplace;
 			}
 			return strAdd+value;
 		}
  /* =========================================================================*/
  
  /* =========================================================================
   * 
   * RPAD
   *  
   * =========================================================================
   */
  	  function rPad(value,totalLen,strReplace){

  			var strAdd = "";
  			var diffLen = totalLen - value.length;
  			for(var i=0;i<diffLen;i++){
  				strAdd += strReplace;
  			}
  			return value+strAdd;
  		}
   /* =========================================================================*/
  
  /* =========================================================================
   * 
   * RPAD
   *  
   * =========================================================================
   */
  	  $.fn.numeric_check = function(){
  		  	
  		  var az = "abcdefghijklmnopqrstuvwxyz";	
  		  az += az.toUpperCase();	
  		 
  		  
  		  
  		  var p = {nchars: az};
  		  this.each(function(index){
  			 $(this).bind('keypress',function(){

  			 });
  		  });
  			
  		}
   /* =========================================================================*/

  /* =========================================================================
   * 
   * 날짜포맷
   *  
   * =========================================================================
   */
  	  function fn_GetTimeFormat(value, flag){
  		  
  		  if(value.indexOf('-') > 0)
  		  {
  			  return value;
  		  }
  		  if (flag == "yyyy-MM-dd")
  			  return value.substring(0, 4) + "-" + value.substring(4, 6) + "-" + value.substring(6, 8);
  		  else
  			  return value;
  	  }
  /* =========================================================================
   * 
   * 공백값을 문자'0' 값으로 리턴
   *  
   * =========================================================================
   */
  	  function emptyToZeroString(value){
  		  if (lPad(rPad(value)) == "")
  			  return "0";
  		  else
  			  return value;
  	  }
  
  /* =========================================================================
   * 
   * 천단위 콤마
   *  
   * =========================================================================
   */
  	function thouSandComma(n) {
  		var reg = /(^[+-]?\d+)(\d{3})/;   // 정규식
  		n += '';                          // 숫자를 문자열로 변환
  		 
  		while (reg.test(n))
  			n = n.replace(reg, '$1' + ',' + '$2');
  		 
  		return n;
  	}

    /* =========================================================================
     * 
     * 시스템 시간 세팅
     *  
     * =========================================================================
     */
    function getTimeStamp(flag) {
    	var d = new Date();
    	var s = '';
    	if (flag == "yyyyMMdd_HHmmss"){
  	      s = leadingZeros(d.getFullYear(), 4) + '' +
  	        leadingZeros(d.getMonth() + 1, 2) + '' +
  	        leadingZeros(d.getDate(), 2) + '_' +
  	        leadingZeros(d.getHours(), 2) + '' +
  	        leadingZeros(d.getMinutes(), 2) + '' +
  	        leadingZeros(d.getSeconds(), 2);
    	}
    	else{
	      s = leadingZeros(d.getFullYear(), 4) + '-' +
  	        leadingZeros(d.getMonth() + 1, 2) + '-' +
  	        leadingZeros(d.getDate(), 2) + ' ' +
  	        leadingZeros(d.getHours(), 2) + ':' +
  	        leadingZeros(d.getMinutes(), 2) + ':' +
  	        leadingZeros(d.getSeconds(), 2);
	    }
    	return s;
    }

    /* =========================================================================
     * 
     * 숫자의 자리수 를 0으로 채우기
     *  
     * =========================================================================
     */
    function leadingZeros(n, digits) {
      var zero = '';
      n = n.toString();
      if (n.length < digits) {
        for (i = 0; i < digits - n.length; i++)
          zero += '0';
      }
      return zero + n;
    }
  	
  	/* =========================================================================
     * 
     * 해당 ID의 공백 체크
     *  
     * =========================================================================
     */
  	function fn_formValChk(id){
	 	var val_str = $('#'+id).val();
	 	if(val_str == null || val_str.length <= 0 || val_str == '')
 		{
	 		return true;
 		}
	 	else
	 	{
	 		return false;
	 	}
  	};
  	
  	/* =========================================================================
     * 
     * StringBuffer 사용하기
     *  
     * =========================================================================
     */
  	var StringBuffer = function() {
  	    this.buffer = new Array();
  	};
  	StringBuffer.prototype.append = function(str) {
  	    this.buffer[this.buffer.length] = str;
  	};
  	StringBuffer.prototype.toString = function() {
  	    return this.buffer.join("");
  	};
  	
  	/********************************************************************************
	* 기      능   	: ID/PASS 찾기
	* @param obj 	: 
	* @return  		: result
	********************************************************************************/
  	 findIdPw = function() {
	        DC_window = window.open('', 'DCURWindow', 'width=754,height=490,scrollbars=yes' );

	        if(DC_window == null){
	            alert(" ※ 윈도우 XP SP2 또는 인터넷 익스플로러 7 사용자일 경우에는 \n    화면 상단에 있는 팝업 차단 알림줄을 클릭하여 팝업을 허용해 주시기 바랍니다. \n\n※ MSN,야후,구글 팝업 차단 툴바가 설치된 경우 팝업허용을 해주시기 바랍니다.");
	        }

	        document.commFrm.action = 'https://www.nibr.go.kr/datacenter/home/member/mb01003p.jsp?sel_site_sno=10';
	        document.commFrm.target = 'DCURWindow';
	        document.commFrm.submit();
	    };
	  
 
  	