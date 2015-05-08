$(document).ready(function(){
	$('.js_nav_a').removeClass('active');
	if(typeof currID_t === 'undefined'){
		
	}else{
		$("#" + currID_t).addClass('active');
	}
});
//菜单
$('.js_nav_a').on('mouseover',function (){
	$(this).addClass('on').next('.gf_nav_hide').show();
});

$('.gf_nav_awrap').on('mouseleave',function (){
	$(this).find('.js_nav_a').removeClass('on')
	.next('.gf_nav_hide').hide();
});


