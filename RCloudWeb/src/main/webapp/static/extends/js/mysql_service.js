(function(){
	var $aDd=$("div.gf_sq_llist dl dd");
	if($aDd.hasClass('show')){
		var $oShow=$("div.gf_sq_llist dl dd.show");
		$oShow.nextAll('dd').css('border-color','#d7d7d7');
		$oShow.parent().nextAll('dl').find('dd').css('border-color','#d7d7d7');
		$oShow.parent().nextAll('dl').find('dt').css('border-color','#d7d7d7');
	}   
	$aDd.click(function(){
		$(this).addClass('show').siblings('dd').removeClass('show');
		$(this).parent('dl').siblings('dl').find('dd').removeClass('show');
		$(this).css('border-color','#149dd4');
		$(this).nextAll('dd').css('border-color','#d7d7d7');
		$(this).parent().nextAll('dl').find('dd').css('border-color','#d7d7d7');
		$(this).parent().nextAll('dl').find('dt').css('border-color','#d7d7d7');
		$(this).prevAll('dd').css('border-color','#149dd4');
		$(this).prevAll('dt').css('border-color','#149dd4');
		$(this).parent().prevAll('dl').find('dd').css('border-color','#149dd4');
		$(this).parent().prevAll('dl').find('dt').css('border-color','#149dd4');
	});
})();