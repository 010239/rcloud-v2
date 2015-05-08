//导航-->子导航
$(function(){
	(function(){
		$('.nav li').mouseover(function(){
			//alert(2)
			$('.nav li').removeClass('hover');
			$(this).addClass('hover');
		}).click(function(){
			$(this).children('.sub_nav').slideUp().stop().slideDown();
		}).mouseout(function(){
			$('.nav li').removeClass('hover');
			$('.nav li').siblings().children('.sub_nav').stop().slideUp();
		});
		
		$('.sub_nav').mousemove(function(){
			$(this).stop().slideDown();
		}).mouseout(function(){
			$(this).stop().slideUp();
			$('.nav li').removeClass('hover');
		});

	})()	
})


$(function(){
	//向左滚动
	(function(){
		var timer;
		var num=1;
		$('ol li').mouseover(function(){
			var myindex=$(this).index()
			$('.all ul').stop().animate({left:-myindex*1310},500)
			$(this).addClass('current').siblings().removeClass();
			num=myindex+1;
		})
		
		function autoplay(){
			//alert('ok')
			if(num==3){
				num=0;
			}
			$('.all ul').stop().animate({left:-num*1310},500)
			$('ol li').eq(num).addClass('current').siblings().removeClass();
			num++
			if(num >= 3){
				num=0;
			}
		}
		timer=setInterval(autoplay,3000);
		$('#id div').mouseover(function(){
			clearInterval(timer)
		}).mouseout(function(){
			timer=setInterval(autoplay,3000);
		})		
	})()	
})

//商品循环
$(function(){
	(function(){
		var num=1;
		var myindex=$('.slides_container .tab-pannel').index()+1;
		var index=$('.slides_container .tab-pannel').length;
		
		$('.btn-prev').click(function(){	
			
			$('.slides_container .div_box').stop().animate({left:-myindex*1150},500);
			if(num==index){
				num=0;
			}
			$('.slides_container .div_box').stop().animate({left:-num*1150},500)
			num++
			if(num >= index){
				num=0;
			}
		})
		$('.btn-next').click(function(){			
			$('.slides_container .div_box').stop().animate({left:myindex*1150},500);
			if(num==index){
				num=0;
			}
			$('.slides_container .div_box').stop().animate({left:-num*1150},500)
			num++
			if(num >= index){
				num=0;
			}
		})

		$('.product a').mousemove(function(){
			if($('.product a').className != 'hover'){
				$('.tab-pannel .product a').removeClass('hover');
				$(this).addClass('hover');
				$(this).parent('.product').siblings('.product').children('a').removeClass('hover');
			}
			
		});
	})()
})

//搜索弹框
$(function(){
	(function(){
		var $input = $("input.search_txt");
		var $span = $("span.search_font");
		$span.off().on("click",function(){
			//alert(1)
			$(this).hide();
			$input.focus();
		})
		/*$span.click(function(){
			//alert(1)
			$(this).hide();
			$input.focus();
		})*/
		
		$input.off().on("focus",function(){
			//alert(2);
			$span.hide();	 					
		}).on("blur",function(){
			if($(this).val()==""){
				$span.show();
			}	
		});
	})()
})

//成功案例
$(function(){
	(function(){
		$('.case_detail li').mouseover(function(){
			$(this).children('a:first').addClass('hover');
			$(this).children('a.search').css('display','block');
			$(this).siblings('li').children('a').removeClass('hover');
			$(this).siblings().children('a.search').css('display','none');	
		}).mouseout(function(){
			$(this).children('a:first').removeClass('hover');
			$(this).children('a.search').css('display','none');
		});
	})()
})
	