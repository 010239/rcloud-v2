//input select 二级联动
function change() {
	if($('.order').children()[0].checked) {
		$('#applicationOrder').css('display', 'none').val('');
		$('#serviceOrder').css('display', 'inline');
		$('#searchApp').css('display', 'none').val('');
		$('#searchService').css('display', 'inline');
		$('#appStatus').css('display', 'none').val('');
		$('#serStatus').css('display', 'inline');
		
	}
 	 else {
 		$('#applicationOrder').css('display', 'inline');
		$('#serviceOrder').css('display', 'none').val('');
		$('#searchApp').css('display', 'inline');
		$('#searchService').css('display', 'none').val('');
		$('#appStatus').css('display', 'inline');
		$('#serStatus').css('display', 'none').val('');
	}

} 

//获取日期组件并转换
function myformatter(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + "-" +(m<10? ('0'+m):m)+ '-' +(d<10?('0'+d):d);
}
function myparser(s) {
	if(!s)
		return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if(!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m-1,d);
	}else{
		return new Date();
	}
}
//初始化页面显示搜索结果
$(document).ready(function(){
	$('#applicationOrder').css('display', 'none').val('');
	$('#serviceOrder').css('display', 'inline');
	$('#searchApp').css('display', 'none').val('');
	$('#searchService').css('display', 'inline');
	$('#appStatus').css('display', 'none').val('');
	$('#serStatus').css('display', 'inline');
	$("#service").show();
	$("#app_sale").hide();
	$("#app_buy").hide();
	$('#service_order_list').datagrid({
		url:ctx +'/rest/userorder/businessOrder',
		method: 'get',
		width:900,
		height:500,  
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
		queryParams:{'status':$("#serStatus").val(),'orderType':$("#serviceOrder").val(),
			'startTime':$("#begindate").datebox('getValue'),'endTime':$('#enddate').datebox('getValue')},
		dataType:'json',
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
			        {title:'操作',field:'businessOrderId',width:250,align: 'center',
		        		formatter: function(value,row,index){  			      		
		        			return '<a style="color:blue" href="javascript:void(0);" class="dg-button" onclick="viewServiceInfo(\'' + value + '\')" >查看详情</a>';        		
		        		}	
		        	}
		        ]],
        singleSelect: true,
	    selectOnCheck: false,
	    checkOnSelect: false,
		pagination:true,
	});
});
//应用订单搜索
	function search_application(){
		if ($('#applicationOrder').val() =="1") {
			$("#service").hide();
			$("#app_sale").show();
			$("#app_buy").hide();
			$('#application_sale_order_list').datagrid({
				url: ctx + '/rest/userorder/applicationOrder',
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
				queryParams:{'orderStatus':$("#appStatus").val(),'orderType':$('#applicationOrder').val(),
					'startTime':$("#begindate").datebox('getValue'),'endTime':$('#enddate').datebox('getValue')},
				dataType:'json',
		        columns:[[
			        {title:'订单号',field:'orderNumber',align: 'center',width:300},
			        {title:'买方',field:'user',width:150,align: 'center',
		        		formatter: function(value,row,index){	
		        			return value.userName ;
		        		}
			        },
			        {title:'应用',field:'application',width:150,align: 'center',
			        	formatter: function(value,row,index){	
		        			return value.chname ;
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
			        {title:'操作',field:'number',width:150,align: 'center',
		        		formatter: function(value,row,index){
		        			return '<a href="javascript:void(0);" class="dg-button"  style="color:blue;"  onclick="viewAppInfo(\'' + row.orderNumber + '\', \''+ $('#applicationOrder').val()+'\')"  >查看详情</a>';
		        		}	
		        	}
		        ]],
		    
		        singleSelect: true,
			    selectOnCheck: false,
			    checkOnSelect: false,
				pagination:true,
				onLoadSuccess: function() {
				}
			});
		}
		if ($('#applicationOrder').val() =="2") {
			$("#service").hide();
			$("#app_sale").hide();
			$("#app_buy").show();
			$('#application_buy_order_list').datagrid({
				url: ctx + '/rest/userorder/applicationOrder',
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
				queryParams:{'orderStatus':$("#appStatus").val(),'orderType':$('#applicationOrder').val(),
					'startTime':$("#begindate").datebox('getValue'),'endTime':$('#enddate').datebox('getValue')},
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
			        {title:'操作',field:'number',width:150,align: 'center',
		        		formatter: function(value,row,index){
		        			return '<a href="javascript:void(0);" class="dg-button"  style="color:blue;"  onclick="viewAppInfo(\'' + row.orderNumber + '\', \''+ $('#applicationOrder').val()+'\')"  >查看详情</a>';
		        		}	
		        	}
		        ]],
		    
		        singleSelect: true,
			    selectOnCheck: false,
			    checkOnSelect: false,
				pagination:true,
				onLoadSuccess: function() {
				}
			});
		}
}
//云服务订单搜索
function search_service(){
	$("#service").show();
	$("#app_sale").hide();
	$("#app_buy").hide();
	$('#service_order_list').datagrid({
		url:ctx +'/rest/userorder/businessOrder',
		method: 'get',
		width:900,
		height:500,  
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
		queryParams:{'status':$("#serStatus").val(),'orderType':$("#serviceOrder").val(),
			'startTime':$("#begindate").datebox('getValue'),'endTime':$('#enddate').datebox('getValue')},
		dataType:'json',
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
			        {title:'操作',field:'businessOrderId',width:250,align: 'center',
		        		formatter: function(value,row,index){  			      		
		        			return '<a style="color:blue" href="javascript:void(0);" class="dg-button" onclick="viewServiceInfo(\'' + value + '\')" >查看详情</a>';        		
		        		}	
		        	}
		        ]],
        singleSelect: true,
	    selectOnCheck: false,
	    checkOnSelect: false,
		pagination:true,
	});
}


function viewServiceInfo(businessOrderId) {
	$.ajax({		
		url: ctx+ "/rest/userorder/businessOrderInfo/" + businessOrderId,
		success:function(data) {
			if (data.status == '200' && data.entity) {
				var orderInfo = data.entity;
				
//				$("#ser-serviceName").html(orderInfo.chname);
				$("#ser-prodName").html(orderInfo.prodName);
				$("#ser-pkgName").html(orderInfo.pkgName);
				switch (orderInfo.businessOrderType){ 
				case 0 : $("#ser-type").html('固定费率');  break; 
				case 1 : $("#ser-type").html('非固定费率'); break; 
				} 
				switch(orderInfo.status){
				case 0: $("#ser-status").html('暂停');  break;
				case 1: $("#ser-status").html('有效');  break;
				case 2: $("#ser-status").html('结束');  break;
				case 3: $("#ser-status").html('撤销');  break;
				}
				if ( orderInfo.createTime != null ) {
					$("#ser-createTime").html(getSmpFormatDateByLong(orderInfo.createTime,false));
				}		
				switch (orderInfo.payPattern){ 
				case 1 : $("#ser-payPattern").html('按季度支付');  break; 
				case 2 : $("#ser-payPattern").html('按月支付'); break; 
				} 
				if ( orderInfo.cancelTime != null ) {
					$("#ser-cancelTime").html(getSmpFormatDateByLong(orderInfo.cancelTime,false));
				}			
				$("#ser-cancelReason").html(orderInfo.cancelReason);
				
			

				$('#serAppInfoModal').modal("show");
			}else{
			     if(data.errorMessage != null){
			         $("#errorInfo").html(data.errorMessage);
			         }
			         $("#errorModal").modal("show");
			         }		
		}
	});
}


function viewAppInfo(oNumber,oType) {
	$.ajax({		
		url: ctx+ "/rest/userorder/applicationOrderInfo/" + oNumber+"/" + oType,
		success:function(data) {
			if (data.status == '200' && data.entity) {
				var app = data.entity;
				if ( oType == "1") {
					$("#ap-orderNumber").html(app.orderNumber);
					$("#ap-applicationName").html(app.application.chname);
					$("#ap-userName").html(app.user.userName);
					switch (app.orderStatus){ 
					case 1 : $("#ap-orderStatus").html('服务中');  break; 
					case 2 : $("#ap-orderStatus").html('暂停'); break; 
					case 3 : $("#ap-orderStatus").html('结束'); break; 
					case 4 : $("#ap-orderStatus").html('撤销'); break; 
					} 
					$("#ap-maintenanceCosts").html(app.maintenanceCosts);
					if (app.endTime != undefined && app.endTime !="") {
						$("#ap-endTime").html(getSmpFormatDateByLong(app.endTime,false));
					}
					if (app.createdTime != undefined && app.createdTime !="") {
						$("#ap-createdTime").html(getSmpFormatDateByLong(app.createdTime,false));
					}
					$("#ap-orderdescription").html(app.orderdescription);

					$('#saleAppInfoModal').modal("show");
				}
				if ( oType == "2") {
					$("#app-orderNumber").html(app.orderNumber);
					$("#app-applicationName").html(app.application.chname);
					$("#app-userName").html(app.user.userName);
					switch (app.orderStatus){ 
					case 1 : $("#app-orderStatus").html('服务中');  break; 
					case 2 : $("#app-orderStatus").html('暂停'); break; 
					case 3 : $("#app-orderStatus").html('结束'); break; 
					case 4 : $("#app-orderStatus").html('撤销'); break; 
					} 
					$("#app-maintenanceCosts").html(app.maintenanceCosts);
					if (app.endTime != undefined && app.endTime !="") {
						$("#app-endTime").html(getSmpFormatDateByLong(app.endTime,false));
					}
					if (app.createdTime != undefined && app.createdTime !="") {
						$("#app-createdTime").html(getSmpFormatDateByLong(app.createdTime,false));
					}
					$("#app-orderdescription").html(app.orderdescription);

				    	$("#buyAppInfoModal").modal("show");
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



$(".detail-button").click(function() {
	$('#saleAppInfoModal').modal("hide");
	$("#buyAppInfoModal").modal("hide");
	$("#ap-orderNumber").html('');
	$("#ap-applicationName").html('');
	$("#ap-userName").html('');
	$("#ap-orderStatus").html(''); 
	$("#ap-maintenanceCosts").html('');
	$("#ap-endTime").html('');
	$("#ap-createdTime").html('');
	$("#ap-orderdescription").html('');
	
	$('#serAppInfoModal').modal("hide");
	$("#ser-prodName").html('');
	$("#ser-pkgName").html('');
	$("#ser-type").html(''); 
	$("#ser-status").html('');
	$("#ser-createTime").html('');
	$("#ser-payPattern").html(''); 
	$("#ser-cancelTime").html('');		
	$("#ser-cancelReason").html('');
	
	
});