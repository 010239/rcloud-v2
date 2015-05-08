$(function(){
	(function(){
	  	var $aInput=$("ul.gf_login_ul li input");
		$aInput.bind({
	   		focus:function(){
				$(this).parent().addClass('active');
			},
			blur:function(){
				$(this).parent().removeClass('active');
			}
    	})
   })();   
})