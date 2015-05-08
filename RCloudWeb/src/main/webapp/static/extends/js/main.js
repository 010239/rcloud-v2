// JavaScript Document
$(function(){
//aside_nav高度
	$('.aside').css('height',$(window).height()-40+'px');
	$(window).on('resize',function(){
		$('.aside').css('height',$(window).height()-40+'px');
		$("iframe.my_iframe").css('height',$(window).height()-60+'px');
	})
	
// 侧导航
	$('.aside_nav .li1>span').click(function(){
		
			$('.aside_nav .li1').removeClass('on');
			$(this).parent('.li1').addClass('on');
			$(this).next('ol').addClass('dis');
			$(this).parent('.li1').siblings().children('ol').removeClass('dis');

	});	
	$('.aside_nav .li1.on>span').click(function(){
			$(this).parent('.li1').removeClass('on');
	});
	$('.aside_nav ol li span').click(function(){
			if($(this).attr('class')=='close'){
				$('.aside_nav ol li span').addClass('close');
				$(this).removeClass('close').addClass('open');
				$(this).parent().siblings().children('span').removeClass('open').addClass('close');;
				$(this).next('ul').addClass('dis');
				$(this).parent().siblings().children('ul').removeClass('dis');
			}else{
				$(this).removeClass('open').addClass('close');
				$(this).siblings().addClass('close');
				$(this).next('ul').removeClass('dis');
			}
	});
	
	$('.aside_nav  ol li ul li').click(function(){
			$('.aside_nav  ol li ul li').removeClass('active');
			$(this).addClass('active');	
	});
//点击首页当前选中无背景

$('a.home').off().on('click',function(){
	$('.ul1 .li1 ol li ul li').removeClass('active');	
});
	
//打开收起
	(function(){
		var $right=$('div.right_wrapper');
		$('a.pack_up').off().on('click',function(){
			$('div.aside').hide();
			$('div.packUp').show();
			$right.css('marginLeft','56px');
		});
		$('a.pack_close').off().on('click',function(){
			$('div.aside').show();
			$('div.packUp').hide();
			$right.css('marginLeft','230px');
		});
	})();
	
//改变iframe的高度
	(function(){
		var $iframe=$("iframe.my_iframe");
		$iframe.css('height',$(window).height()-60+'px');	
	})();
//头部个人资料收缩展开
	(function(){
		var $oDiv=$('div.info');
		var $oA=$oDiv.children('a');

		$oDiv.off().on('click',function(){
			if($oDiv.hasClass('hover')){
				$oDiv.removeClass('hover');
				$oA.removeClass('down');
			}else{
				$oDiv.addClass('hover');
				$oA.addClass('down');
			}
		});
		$oDiv.on('mouseleave',function(){	
			setTimeout(function(){
				$oDiv.removeClass('hover');
				$oA.removeClass('down');
			},500);
		})
	})();
	
})