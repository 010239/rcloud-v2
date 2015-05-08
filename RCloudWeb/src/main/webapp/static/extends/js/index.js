(function (){

    //轮播图
    (function(){
        $('.gf_idx_slide').cycle({
            fx: 'scrollLeft',
            pager:'.gf_cycle_switch',
            speed: 500,
            pause:true,
            pauseOnPagerHover:true
        });

        $('.gf_cycle_switch a').html('');
    })();

    //云服务
    (function(){
        var num=$('.gf_sul_wrap ul').find('li').size();
        var ulWid=292*num;
        $('.gf_sul_wrap ul').width(ulWid);

        $('.gf_to_right').on('click',function (){
            $('.gf_sul_wrap ul').animate({"left":"-584px"},function (){
                $(this).find('li:eq(0)').insertAfter($('.gf_sul_wrap ul li:last'));
                $(this).css('left','-292px');
            });
        });

        $('.gf_to_left').on('click',function (){
            $('.gf_sul_wrap ul').animate({"left":"0"},function (){
                $(this).find('li:last').insertBefore($('.gf_sul_wrap ul li:first'));
                $(this).css('left','-292px');
            });
        });

    })();
	
	//首页公告弹层
//	(function(){
//		var $oContent= $('<h3>因平台升级部分服务暂停告知</h3><p>尊敬的用户您好：<br/>云平台将于 10月16日 00:00 至 06:00 进行系统更新，届时数据服务将停止更新，给您带来不便敬请谅解。</p><p class="date">2014年10月15日 08:00</p>');	
//		$('#box').append($oContent);
//		
//		$('.mask').show();
//		$('.notice_layer').show();
//		$('.close_icon').off().on('click',function(){
//			$('.mask').hide();
//			$('.notice_layer').hide();	
//		});
//	})()
    

    
    
   
    (function(){    	  	
    		 $.ajax({		
    			 url: ctx+ "rest/notice/getFirstNotice" ,
    			 method: 'get',
    			 success : function(data) {
    				 if (data.status != "200" && data.errorMessage != null) {
    				  
    				 }
    				 var firstNotice = data.entity;
    				 if( firstNotice!=null){
    				 if (firstNotice.noticeKey != null) {
    					 var publishTime = firstNotice.publishTime;
    					 var $oContent= $('<h3>'+firstNotice.showCut+'</h3><p>尊敬的用户：<br/>'+firstNotice.notice+'</p><p class="date">发布时间：' + publishTime + '</p>');	
    					 $('#box').append($oContent);
    				
    					 $('.mask').show();
    					 $('.notice_layer').show();
    					 $('.close_icon').off().on('click',function(){
    					 $('.mask').hide();
    					 $('.notice_layer').hide();	
    				});
    				 }
    				 }	 
    				 
    			
    			 },
    			 error : function(XMLHttpRequest, textStatus, errorThrown){
    				 centerMessage("提示", "与服务器连接失败！");
    			 }
    		 });
    	  })();

})();


