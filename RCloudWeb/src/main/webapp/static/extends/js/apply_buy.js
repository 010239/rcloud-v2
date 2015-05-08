//续费：
(function(){
	var oPrice=$("i.a_price");	
	var oSingle_price=$("i.single_price");	
	var oRide=$("p.month_t span[class=active]").attr('price')*$("p.level_t span[class=active]").attr('price')
	oPrice.html(oRide);
	oSingle_price.html($("p.level_t span[class=active]").attr('price'));
	$("dl.gf_chos_buy p span").click(function(){				   
		$(this).addClass('active').siblings('span').removeClass('active');			
		oPrice.html($(this).attr('price')*$(this).parent().siblings('p').find('span[class=active]').attr('price'));
	 })	
	$("p.level_t span").click(function(){
    	oSingle_price.html($(this).attr('price'));
    })
  })();