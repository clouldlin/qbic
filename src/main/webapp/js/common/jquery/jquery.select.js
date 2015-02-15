
(function($) {
	
  //select option 생성 삭제
  $.fn.emptySelect = function() {
    return this.each(function(){
      if (this.tagName=='SELECT') this.options.length = 0;
    });
  };

  
  //select option 생성
  $.fn.loadSelect = function(optionsDataArray) {
    return this.emptySelect().each(function(){
      if (this.tagName=='SELECT') {
        var selectElement = this;
        var option = new Option('선택','');
        if ($.browser.msie) {
            selectElement.add(option);
          }
          else {
            selectElement.add(option,null);
          }
        $.each(optionsDataArray,function(index,optionData){
          option = new Option(optionData.administAreaNm,optionData.administAreaCodeSe);
          if ($.browser.msie) {
            selectElement.add(option,selectElement.options[null]);
          }
          else {
            selectElement.add(option,null);
          }
        });
        
      }
    });
  };
  
	//select option 생성
	  $.fn.unwkLoadSelect = function(optionsDataArray) {
	    return this.emptySelect().each(function(){
	      if (this.tagName=='SELECT') {
	        var selectElement = this;
	        var option = new Option('99999999','99999999');
	        if ($.browser.msie) {
	            selectElement.add(option);
	          }
	          else {
	            selectElement.add(option,null);
	          }
	        $.each(optionsDataArray,function(index,optionData){
	          option = new Option(optionData.administAreaNm,optionData.administAreaCodeSe);
	          if ($.browser.msie) { 
	            selectElement.add(option,selectElement.options[null]);
	          }
	          else {
	            selectElement.add(option,null);
	          }
	        });
	        
	      }
	    });
	  };
  
	  
	  
  $.fn.loadSelectDong = function(optionsDataArray) {
	    return this.emptySelect().each(function(){
	      if (this.tagName=='SELECT') {
	        var selectElement = this;
	        $.each(optionsDataArray,function(index,optionData){
	          var option = new Option(optionData.adstrdNmshortNm,optionData.legalAdstrdCodeNo);
	          if ($.browser.msie) {
	            selectElement.add(option,selectElement.options[null]);
	          }
	          else {
	            selectElement.add(option,null);
	          }
	        });
	      }
	    });
	  };
})(jQuery);