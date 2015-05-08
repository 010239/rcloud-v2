(function (){
	var info = $("#errorInfo").val();
	if (info != "" && info != null) {
		//centerMessage("提示", data.errorMessage);
		$("#errorInfo").html(info);
		$("#errorModal").modal("show");
	}
    //管理
    $('.js_ci_dt').on('click',function (){
        if(!this.onOff){
            $(this).parent('dt').next('dd').hide();
            $(this).removeClass('on').addClass('off');
            this.onOff=true;
        }else{
            $(this).parent('dt').next('dd').show();
            $(this).removeClass('off').addClass('on');
            this.onOff=false;
        }

        return false;
    });
   var b=true;
   $('.gf_cd_dddt dt span').on('click',function (){
        $(this).parents('.gf_cd_dddt').find('dd').hide().eq($(this).index()).show();
        if(!b)return;
        b=false;
        if($(this).index()==1)
        {
        	buytab2();
        }
        $(this).removeClass('off').siblings().addClass('off');
    });

    //尾部iframe高度自适应
//    (function(){
//        $('.gf_js_iframe').on('click',function (){
//            var $html=$(this).parent().next().find('a').html();
//            var $iframe=$(".gf_iframe");
//            var $href=$(this).attr('href');
//
//            $('dl.gf_ci_mng').hide();
//            $('.gf_ci_ifr').show();
//            $('.js_ifr_bread').show().find('span').html($html);
//            $iframe.css("height",300);
//            
//            $iframe.attr('src',$href);
//            $iframe.off().on("load",function(){
//                $(this).css("height",$(this).contents().find("body").height());
//            });
//            setInterval(function(){
//                $iframe.trigger("load");
//            },200);
//        });
//	})();

    $('.gf_js_set').on('click',function (){
    	$("#modifyUserInfoModal").modal("show");
    });

    //修改安全信息弹层
    $('.gf_safe_js').on('click',function (){
       	$("#modifySafeInfoModal").modal("show");
    });
    $('.gf_ci_mask').on('click',function (){
        $('.gf_user_safe').hide();
    });
    $('.gf_safe_mod').on('click',function (){
        $(this).parent('.gf_safe_mod_wrap').next('.gf_safe_form').show();
    });
    


    //用户名浮层

    $('.gf_js_lmd').hover(
		function(){
			$('.gf_js_lmd').addClass('on');
			$('.gf_user_lmx').show();
			$('.gf_i_tri').addClass('on');
            $('.gf_ci_long').hide();
		},
		function(){
			$('.gf_js_lmd').removeClass('on');
			$('.gf_user_lmx').hide();
			$('.gf_i_tri').removeClass('on');
            $('.gf_ci_long').show();
		}
	);
	$('.gf_user_lmx').hover(function(){
		$('.gf_i_tri').addClass('on');
			$('.gf_js_lmd').addClass('on');
			$('.gf_user_lmx').show();
		},function(){
			$('.gf_js_lmd').removeClass('on');
			$('.gf_i_tri').removeClass('on');
			$('.gf_user_lmx').hide();
		}
	);

    $('.gf_user_lmx').hover(function (){
        $('.gf_ci_long').hide();
    },function (){
        $('.gf_ci_long').show();
    });
	
	
	//消息弹层20141017

/*	$('.blue_link').on('click',function (){
    	$("#msg_2").modal("show");
    });*/
	$('.msg_btn3').on('click',function (){
		mymessge();
    	//$("#msg").modal("show");
    });

})();
/**
 *执行 删除消息
 */
function messageDelete(){
	var ids=getSelectMessageKeys();
	if (ids == "") {
		alert("请选择要删除的消息！");
	} else {
		$.messager.confirm("确认删除", "确认要删除消息吗？",function(r){
		if(r){
			document.getElementById('deid').disabled=true;
				$.ajax({
					url: ctx + 'rest/usermessage/messagedelete/' + ids,
					method: 'get',
					success : function(data) {
						if (data.status != "200" && data.errorMessage != null) {
							$("#errorInfo").html(data.errorMessage);
							$("#errorModal").modal("show");
						}
						mymessge();
						document.getElementById('deid').disabled=false;
					
					},
					error : function(XMLHttpRequest, textStatus, errorThrown){
						$("#errorInfo").html("与服务器连接失败！");
						$("#errorModal").modal("show");
					}
				});	
			  }
		 });
	}

}
/**
 * 执行  标记为已读
 */
function markMessageRead(){
	var ids=getSelectMessageKeys();
	if (ids == "") {
		alert("请选择要标记的消息！");
	}else{
		document.getElementById('duid').disabled=true;
		$.ajax({
			url: ctx + 'rest/usermessage/messagemarkread/' + ids,
			method: 'get',
			success : function(data) {
				if (data.status != "200" && data.errorMessage != null) {
					//centerMessage("提示", data.errorMessage);
					$("#errorInfo").html(data.errorMessage);
					$("#errorModal").modal("show");
				}
				mymessge();
				document.getElementById('duid').disabled=false;
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				$("#errorInfo").html("与服务器连接失败！");
				$("#errorModal").modal("show");
			}
		});
	}
}
/**
 * 查看我的消息
 * @param messagekey
 */
function messageread(messagekey){
	$.ajax({
		url: ctx + 'rest/usermessage/messageread/' + messagekey,
		method: 'get',
		success : function(data) {
			if (data.status != "200" && data.errorMessage != null) {
				//centerMessage("提示", data.errorMessage);
				$("#errorInfo").html(data.errorMessage);
				$("#errorModal").modal("show");
			}
			var message=data.entity;
			var dendate=getSmpFormatDateByLong(message.sendTime,true);
			$("#messagetitle").html(message.sender+"&nbsp;&nbsp;"+dendate+"&nbsp;&nbsp;发送");//<!-- 系统管理员 10月16日 16:10 发送 -->
			$("#messageContext").html(message.content);
			toReadView();
		
		},
		error : function(XMLHttpRequest, textStatus, errorThrown){
			$("#errorInfo").html("与服务器连接失败！");
			$("#errorModal").modal("show");
		}
	});
}
/**
 * 刷新
 */
function resfush(){
	document.getElementById('shuaid').disabled=true;
	mymessge();
	document.getElementById('shuaid').disabled=false;
}
function changeState(){
	 var allmessage=document.getElementById('allmessage');
	 var seleChange=document.getElementById('seleChange').value;
	 if("allmessage"==seleChange){
		 allmessage.value="allmessage"; 
	 }else{
		 allmessage.value="unmessage"; 
	 }
	 mymessge("1");
}
function mymessge(curPage){
	 $("#mymessageTab tbody").html("");
	 document.getElementById('selectAll').checked=false; 
		if(curPage==null || "undefined"==curPage){
			curPage=$("#currentPage").val();
			if(curPage==""){
				curPage=1;
			}
			
		}
		var readFlag=document.getElementById('allmessage').value;
		
	var ul=ctx + 'rest/usermessage/mymessage/'+curPage+'?readFlag='+readFlag;
	$.ajax({	
		async:false,
		url: ul,
		method: 'get',
		success:function(data) {
			if (data.status == '200' && data.entity) {
				var app = data.entity;
				var rows=app.rows;
				var currentPage=app.currentPage;
				if(	$("#currentPage")!=null){
					$("#currentPage").val(currentPage);
				}
				
			if(rows&&rows.length>0){
				var datastr="";

				for(var i=0;i<rows.length;i++){
					var status=rows[i].status;
					if(status){
						datastr  += "<tr>";
					}else{
						datastr  += "<tr class='msg_fb'>";
					}
					
					datastr  += "<td class='check'><input type='checkbox' name='mess' value='"+rows[i].messageKey+"'/></td>";
					if(rows[i].sender.length > 18){
						datastr  += " <td>"+rows[i].sender.substr(0,18)+"...</td>";
					}else{
						datastr  += " <td>"+rows[i].sender+"</td>";
					}
					//${fn:substring("xxxx",0,5)}
					var ccont=rows[i].content;
					if(ccont&&ccont.length>41){
						ccont=ccont.substr(0,41) ;
					}
					var dendate=getSmpFormatDateByLong(rows[i].sendTime,true);
					datastr  += " <td>"+ccont+"</td>";
					datastr  += " <td class='msg_gray'>"+dendate+"</td>";
					datastr  += "<td><a class='blue_link' href='javascript:void(0);' onclick='messageread(\""+rows[i].messageKey+"\")'>查看详情&gt;</a></td>";
					datastr  += " </tr>";
				}
				$("#mymessageTab").append(datastr);
				
				initMessagePage(app);
			}else{
				 $("#divId").html("");
				$("#divId").append("<span style='color:red'>&nbsp;&nbsp;&nbsp;&nbsp;暂无消息</span>");
			}
				
			}else{
			     if(data.errorMessage != null){
			         $("#errorInfo").html(data.errorMessage);
			         }
			         $("#errorModal").modal("show");
			         }

		}
	});
	
}
/**
 * 我的消息 初始化 分页信息
 * @param pagination
 */
function initMessagePage(pagination) {
	var $content = $("#message_pagination");
	var start = 0;
	var end = 0;
	
	if (pagination.totalPage <= 5) {
		start = 1;
		end = pagination.totalPage;
	} else if (pagination.currentPage <= 3) {
		start = 1;
		end = 5;
	} else if (pagination.totalPage - pagination.currentPage <= 3) {
		end = pagination.totalPage;
		start = pagination.totalPage - 4;
	} else {
		start = pagination.currentPage - 2;
		end = pagination.currentPage + 2;
	}
	
	var pageItem = '';
	for (var i = start; i <= end; i++) {
		if (i == pagination.currentPage) {
			pageItem += '<a href="javascript:;" class="on" onclick="mymessge('+i+')">' + i + '</a>';
		} else {
			pageItem += '<a href="javascript:;" onclick="mymessge('+i+')">' + i + '</a>';
		}
	}
	var pagelast='';
	if(pagination.currentPage>1){
		var last=parseInt(pagination.currentPage)-1;
		pagelast='<a href="javascript:;" onclick="mymessge('+last+')">上一页</a>';// 当当前页不是第一页的时候，存在上一页
	}
	var nextpage='';
	if(pagination.currentPage<pagination.totalPage){
		var next=parseInt(pagination.currentPage)+1;
		nextpage='<a href="javascript:;"  onclick="mymessge('+next+')">下一页</a>';
	}
	var pageHtml = '<a href="javascript:;" onclick="mymessge(1)">首页</a>' +
	    pagelast +
        pageItem +
        nextpage +
        '<a href="javascript:;" onclick="mymessge('+pagination.totalPage+')">尾页</a>' +
        '<span class="m_span">共' + pagination.total + '条消息</span></div>';
	
	$content.html(pageHtml);
}
/**
 * 全选
 */
function selectAll(){
	var checkIs = document.getElementById('selectAll'); 
	 var m = document.getElementsByName('mess');  
	    var l = m.length;  
	    for (var i = 0; i < l; i++){
	    	if(checkIs.checked==true){
	    		m[i].checked=true;
	    	}else{
	    		m[i].checked=false;
	    	}
	    	
	    }
	
}
/**
 * 获取选中的ids
 */
function getSelectMessageKeys(){
	 var m = document.getElementsByName('mess');  
	    var l = m.length;  
	    var ids="";
	    for (var i = 0; i < l; i++){
	    	if(m[i].checked==true){
	    		ids += m[i].value+",";
	    	}
	    	
	    }
	    return ids;
}
function toReadView(){
	//$("#msg").modal("hide");
	$("#msg_2").modal("show");
	
}
/**
 * 点击关闭我的消息时候  如果更细了未读消息的状态 那么首页的条数也要发生变化
 */
function closeMsg(){
	$.ajax({
		async:false,
		url: ctx + 'rest/usermessage/getunreadmessagecount',
		method: 'get',
		success : function(data) {
			if (data.status != "200" && data.errorMessage != null) {
				//centerMessage("提示", data.errorMessage);
				$("#errorInfo").html(data.errorMessage);
				$("#errorModal").modal("show");
			}
			$("#msg_num").html(data.entity);
		
		},
		error : function(XMLHttpRequest, textStatus, errorThrown){
			$("#errorInfo").html("与服务器连接失败！");
			$("#errorModal").modal("show");
		}
	});
}
