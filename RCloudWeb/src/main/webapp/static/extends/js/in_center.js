$(document).ready(function(){
	$('#msg_btn').on('click',function (){
		document.getElementById('allmessage').value="unmessage";
		document.getElementById('seleChange').value="unmessage";
		mymessge();
		$("#msg").modal("show");
		
    	//$("#msg").modal("show");
    });
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
	
	
	var nowTime = new Date().getTime(); //时间戳
	
	loadServiceOrderList();
	loadServiceBillList();
	$("#currentPage").val(1);
	function loadServiceOrderList(){
		$('#service_order_list').datagrid({
			url:ctx +'rest/userorder/businessOrder?date='+nowTime,
			method: 'get',
			fit:true,
			fitColumns:true,
			rownumbers: false,
			loadFilter: function (data) {
		    	if (data.status == '200') {
		    		return data.entity;
		    	}else{
		    		if(data.errorMessage != null){
			    		$("#errorInfo").html(data.errorMessage);
		    		}
		    		$("#errorModal").modal("show");
		    	}
		   },
			 columns:[[
				     
				        {title:'产品名称',field:'prodName',width:150,align: 'center'},
				        {title:'资费包',field:'pkgName',align: 'center',width:150},
				        {title:'订购类型',field:'businessOrderType',width:150,align: 'center',
				        	formatter : function(val){
								  switch(val){
									case 0:
										return '固定费率';break;
									case 1:
										return '非固定费率';break;
									}
				        	}
				        },
				    	{title:'签单日期',field:'createTime',align: 'center',width:150,
				        	formatter: function(value, row, index) {
				        		if ( value != null ) {
				        			return getSmpFormatDateByLong(value, false);
				        		}
			        		
				        	}
			        	},
			        	{title:'支付方式',field:'payPattern',align: 'center',width:150,
			        		formatter : function(val){
								  switch(val){
									case 1:
										return '按季度支付';break;
									case 2:
										return '按月支付';break;
									}
								  
				        	}
				    	},
				    	{title:'订购状态',field:'status',align: 'center',width:150,
			        		formatter : function(val){
								  switch(val){
									case 0:
										return '暂停';break;
									case 1:
										return '有效';break;
									case 2:
										return '结束';break;
									case 3:
										return '撤销';break;
									}
								  
				        	}
				    	},
			        ]],
	        singleSelect: true,
		    selectOnCheck: false,
		    checkOnSelect: false,
	
		});
	}
	
	//云服务订单
	$("#service_order").bind("click",function(){
		loadServiceOrderList();
	});
	
	//云应用订单
	$("#app_order").bind("click",function(){
		$('#application_order_list').datagrid({
			url: ctx + 'rest/userorder/applicationOrder?date='+nowTime,
			method: 'get',
			fit:true,
			fitColumns:true,
			rownumbers: false,
			loadFilter: function (data) {
			    	if (data.status == '200') {
			    		return data.entity;
			    	}else{
			    		if(data.errorMessage != null){
				    		$("#errorInfo").html(data.errorMessage);
			    		}
			    		$("#errorModal").modal("show");
			    	}
			   },
			queryParams:{'orderType':'2'},
			dataType:'json',
	        columns:[[
		        {title:'应用名',field:'application',width:150,align: 'center',
		        	formatter: function(value,row,index){	
	        			return value.chname ;
	        		}	
		        },
		        {title:'购买日期',field:'createdTime',align: 'center',width:150,
		        	formatter: function(value, row, index) {
		        		if ( value != null ) {
		        			return getSmpFormatDateByLong(value, false);
		        		}
	        		
		        	}
	        	},
		        {title:'卖方',field:'user',width:150,align: 'center',
	        		formatter: function(value,row,index){	
	        			return value.userName ;
	        		}
		        },			   
		    	{title:'状态',field:'orderStatus',align: 'center',width:150,formatter : function(val){
					  switch(val){
						case 1:
							return '服务中';break;
						case 2:
							return '暂停';break;
						case 3:
							return '结束';break;
						case 4:
							return '撤销';break;
							   }
						}
			     },
	        ]],
			singleSelect: true,
		    selectOnCheck: false,
		    checkOnSelect: false,
			onLoadSuccess: function() {
				
			}
		});
	});
	
	
	
	function loadServiceBillList(){
		$('#service_bill_list').datagrid({
			url:ctx +'rest/userbill/serviceBill?date='+nowTime,
		    method:'get',
		    fitColumns: true,
		    fit:true,
		    loadFilter: function (data) {
		    	if (data.status == '200') {
		    		return data.entity;
		    	}else{
		    		if(data.errorMessage != null){
			    		$("#errorInfo").html(data.errorMessage);
		    		}
		    		$("#errorModal").modal("show");
		    	}
		    },
		    rownumbers: false,
		    columns:[[			      
				        {title:'账单日期',field:'createTime',align: 'center',width:250},
				        {title:'总费用（￥）',field:'totalCharge',align: 'center',width:250}
			        ]],
			singleSelect: true,
		    selectOnCheck: false,
		    checkOnSelect: false
		});
	}
	
		//云服务账单
	$("#service_bill").bind("click",function(){
		loadServiceBillList();
	});
		
		//云应用账单
	$("#app_bill").bind("click",function(){
			$('#application_bill_list').datagrid({
				url:ctx +'rest/userbill/applicationBill?date='+nowTime,
			    method:'get',
			    fitColumns: true,
			    fit:true,
			    loadFilter: function (data) {
			    	if (data.status == '200') {
			    		return data.entity;
			    	}else{
			    		if(data.errorMessage != null){
				    		$("#errorInfo").html(data.errorMessage);
			    		}
			    		$("#errorModal").modal("show");
			    	}
			    },
			    rownumbers: false,
			    columns:[[
			  	        {title:'账单号',field:'billNumber',align: 'center',width:300},
			  	        {title:'分类',field:'billType',width:250,align: 'center',
			  	        	formatter : function(val){
			  					  switch(val){
			  						case 1:
			  							return '月账单';break;
			  						case 2:
			  							return '季度账单';break;
			  						case 3:
			  							return '年账单';break;
			  							   }
			  						}
			  	        },
			  	        {title:'类型',field:'$("#type").val()',width:150,align: 'center',formatter: function(value){
			      			return '应用';
			      			}
			  	        },
			  	    	{title:'账单日期',field:'createdTime',align: 'center',width:150},
			  	    	{title:'账单费用(￥)',field:'billCosts',align: 'center',width:150},
			          ]],
				singleSelect: true,
			    selectOnCheck: false,
			    checkOnSelect: false,
				onLoadSuccess: function() {
					
				}
			});
	});
	
	
	
	
	
	$('#userInfoForm').validate();
	
	$("#userInfoSub").bind("click", function(){
		$("#userInfoSub").css({'backgroundColor':'#ccc','cursor':'default'}).val('提交中');
		if($('#userInfoForm').valid()){
		}
	});
	
	$('#yxform').validate();
	
	$("#yxformSub").bind("click", function(){
		if($('#yxform').valid()){
		}
	});
	
	$('#mmform').validate();
	
	$("#mmformSub").bind("click", function(){
		
		if($('#mmform').valid()){
			if($("#password1").val() != $("#password2").val()){
				$("#msg0").html("<font color='red'>两次密码不一致!</font>");
				return false;
			} else {				
				$("#msg0").html("");
				if ( checkPassWord()) {  //原密码正确
						$("#msgPass").html("");
				    } else {  
				    	$("#msgPass").html("<font color='red'>原密码输入有误!</font>");
						return false;			    					
				    }
			}
		}
	});
	
	$('#sjform').validate();
	
	$("#sjformSub").bind("click", function(){
		if($('#sjform').valid()){
		}
	});
	
	
	
	
	$("#gbmm").bind("click", function(){
		$(this).parents('.gf_safe_form').hide();
	});
	$("#gbsj").bind("click", function(){
		$(this).parents('.gf_safe_form').hide();
	});
	$("#gbyx").bind("click", function(){
		$(this).parents('.gf_safe_form').hide();
	});
	
	
	
	
	
	$("#fssjyzf").bind("click", function(){
		  $.post("${ctx}/rest/userconsole/createValiPhone/",{bindingPhone:$('#mobilePhone').val()},
		  function(data){
		  },
		  "json");//这里返回的类型有：json,html,xml,text
		  
	});
	
	$("#fsyxyzf").bind("click", function(){
		  $.post("${ctx}/rest/userconsole/createValiMail/",{userEmail:$('#userEmail').val()},
				  function(data){
				  },
				  "json");//这里返回的类型有：json,html,xml,text
	});
	
	
//	msgList();
	
});

$("#close-user-info").click(function() {
	$('#realName').val('');
	$('#companyName').val('');
	$('#areaName').val('');
	$('#streatName').val('');
	$('#fixedPhone').val('');
	$('.modal').hide();
	location.reload();
	});

var markIsLogin ;

$("#order").click(function() {
	$('#aa span').html('订购管理');

	 var $html=$(this).parent().next().find('a').html();
	    var $iframe=$(".gf_iframe");
	    $('dl.gf_ci_mng').hide();
	    $('.gf_ci_ifr').show();
	    $('.js_ifr_bread').show().find('span').html($html);
	    $iframe.css("height",300);
    
    if ( checkIsLogin()) {  //未登录
		location.href = ctx+"static/extends/login.jsp" ;
	} else {  //已登录
		
		$("#frametemp").attr("src",ctx+"page/userOrder");
		
	}
   
    $iframe.off().on("load",function(){
        $(this).css("height",$(this).contents().find("body").height());
    });
    setInterval(function(){
        $iframe.trigger("load");
    },200);
    
	});

$("#bill").click(function() {
	$('#aa span').html('账单管理');

	var $html=$(this).parent().next().find('a').html();
    var $iframe=$(".gf_iframe");
    $('dl.gf_ci_mng').hide();
    $('.gf_ci_ifr').show();
    $('.js_ifr_bread').show().find('span').html($html);
    $iframe.css("height",300);

    if ( checkIsLogin()) {  //未登录
    	location.href = ctx+"static/extends/login.jsp" ;
    } else {  //已登录
	
    	$("#frametemp").attr("src",ctx+"page/userBill");
	
    }

    $iframe.off().on("load",function(){
    	$(this).css("height",$(this).contents().find("body").height());
    });
    setInterval(function(){
    	$iframe.trigger("load");
    },200);
	});

$("#deal").click(function() {
	$('#aa span').html('交易管理');
	var $html=$(this).parent().next().find('a').html();
    var $iframe=$(".gf_iframe");
    $('dl.gf_ci_mng').hide();
    $('.gf_ci_ifr').show();
    $('.js_ifr_bread').show().find('span').html($html);
    $iframe.css("height",300);

    if ( checkIsLogin()) {  //未登录
	location.href = ctx+"static/extends/login.jsp" ;
    } else {  //已登录
	
	$("#frametemp").attr("src",ctx+"page/transaction/findTsatList");
	
    }

    $iframe.off().on("load",function(){
    	$(this).css("height",$(this).contents().find("body").height());
    });
    setInterval(function(){
    	$iframe.trigger("load");
    },200);
	});

$("#ss").click(function() {
	$('#aa span').html('服务管理');
	var $html=$(this).parent().next().find('a').html();
    var $iframe=$(".gf_iframe");
    $('dl.gf_ci_mng').hide();
    $('.gf_ci_ifr').show();
    $('.js_ifr_bread').show().find('span').html($html);
    $iframe.css("height",300);

    if ( checkIsLogin()) {  //未登录
	location.href = ctx+"static/extends/login.jsp" ;
    } else {  //已登录
	
	$("#frametemp").attr("src",ctx+"page/cloudservice/");
	
    }

    $iframe.off().on("load",function(){
    	$(this).css("height",$(this).contents().find("body").height());
    });
    setInterval(function(){
    	$iframe.trigger("load");
    },200);
	});

$("#org").click(function() {
	$('#aa span').html('机构管理');
	var $html=$(this).parent().next().find('a').html();
    var $iframe=$(".gf_iframe");
    $('dl.gf_ci_mng').hide();
    $('.gf_ci_ifr').show();
    $('.js_ifr_bread').show().find('span').html($html);
    $iframe.css("height",300);

    if ( checkIsLogin()) {  //未登录
	location.href = ctx+"static/extends/login.jsp" ;
    } else {  //已登录
	
	$("#frametemp").attr("src",ctx+"page/org/spacepage");
	
    }

    $iframe.off().on("load",function(){
    	$(this).css("height",$(this).contents().find("body").height());
    });
    setInterval(function(){
    	$iframe.trigger("load");
    },200);
	});

function checkIsLogin() {
	var bol = false ; 
	$.ajax({		
		url: ctx+ "rest/checkIsLogin" ,
		async:false,
		success:function(data) {
			if (data.status == '200'  ) {
				var c = data.entity;
				
				if ( c == '0') {
					
				} else {
					bol = true;
				}		
			}else{
			     if(data.errorMessage != null){
			         $("#errorInfo").html(data.errorMessage);
			         }
			         $("#errorModal").modal("show");
			         }
		},
	error : function(XMLHttpRequest, textStatus, errorThrown){
		centerMessage("提示", "与服务器连接失败！");
	}
	});
	return bol;
}
function fnWait(oBtn){
	$("#"+oBtn).css({'backgroundColor':'#ccc','cursor':'default'}).val('提交中');
}
//function iframeOn() {
//	 var $html=$(this).parent().next().find('a').html();
//	    var $iframe=$(".gf_iframe");
////	    var $href=$(this).attr('href');
//
//	    $('dl.gf_ci_mng').hide();
//	    $('.gf_ci_ifr').show();
//	    $('.js_ifr_bread').show().find('span').html($html);
//	    $iframe.css("height",300);
//}
//function iframeOff() {
//	 $iframe.off().on("load",function(){
//	        $(this).css("height",$(this).contents().find("body").height());
//	    });
//	    setInterval(function(){
//	        $iframe.trigger("load");
//	    },200);
//}
function checkPassWord() {
	var isRight = true ; 
	$.ajax({		
		url: ctx+ "rest/userconsole/checkPassword" ,
		async:false,
		type: 'post',
		data:{password:$("#oldPassword").val(),},
		success:function(data) {
			if (data.status == '200'  ) {
				var pass = data.entity;
				
				if ( pass == '1') {
					isRight = false;
				} 	
			}else{
			     if(data.errorMessage != null){
			         $("#errorInfo").html(data.errorMessage);
			         }
			         $("#errorModal").modal("show");
			         }
		},
	error : function(XMLHttpRequest, textStatus, errorThrown){
		centerMessage("提示", "与服务器连接失败！");
	}
	});
	return isRight;
}