$(function() {	
	
	$("#reachargebillform").validationEngine({
		onValidationComplete : function(form, valid) {
			if (valid == true) {
				var debtsCosts=$("#debtsCosts").val();
				var chargingAmount=$("#chargingAmount").val();
				if($("#chargingAmount").val()==""){
					$("#msg2").html("<font color='red'>续费金额不能为空！</font>");
					return false;
				}else{
					 if(parseFloat(chargingAmount)<parseFloat(debtsCosts)){
							$("#msg2").html("<font color='red'>续费金额应大于等于欠费金额！</font>");
							return false;
						}
				}
				formrenew(ctx + '/rest/userbill/renewMoney');	
				
			}
			return false;

		},//表单验证后的回调函数，form为提交的表单，valid为结果，验证通过则为true,验证失败则为false
		scroll : false,
		focusFirstField : false,
		maxErrorsPerField:1
	});
});

var serviceBillDataGride;
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


//input select 二级联动
function change() {
	if($('.bill').children()[0].checked) {
		$('#type').hide();
 		$('#b-t').hide();
		$('#searchApp').css('display', 'none').val('');
		$('#searchService').css('display', 'inline');
		$('#appDebts').css('display', 'none').val('');
		$('#serDebts').css('display', 'inline');
	}
 	 else {
 	
		$('#searchApp').css('display', 'inline');
		$('#searchService').css('display', 'none').val('');
		$('#appDebts').css('display', 'inline');
		$('#serDebts').css('display', 'none').val('');
		$('#type').show();
		$('#b-t').show();
	}

} 

//初始化页面显示搜索结果
$(document).ready(function(){
	$('#type').hide();
	$('#b-t').hide();
	$('#appDebts').hide();//smh 首次加载的时候 应该显示 云服务  的欠费查询
	
	//$('#serDebts').hide();
	$("#app").hide();
	$("#service").show();
	$("#adebts").hide();
	$("#sdebts").hide();
	$('#service_bill_list').datagrid({
		url: ctx + '/rest/userbill/serviceBill',
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
		queryParams:{
			'startTime':$("#begindate").datebox('getValue'),'endTime':$('#enddate').datebox('getValue')},
		dataType:'json',
		columns:[[
		          	{title:'账单日期',field:'createTime',align: 'center',width:250},
			        {title:'总费用（￥）',field:'totalCharge',align: 'center',width:250},
	    	
			        {title:'操作',field:'chargeMonth',width:250,align: 'center',
		        		formatter: function(value,row,index){
		        			return '<a style="color:blue" href="javascript:void(0);" class="dg-button" onclick="viewServiceInfo(\'' + value + '\', \''+ row.totalCharge +'\')" >查看详情</a>';	        		
		        		}	
		        	}
		        ]],
        singleSelect: true,
	    selectOnCheck: false,
	    checkOnSelect: false,
		pagination:true
	});
});
//应用账单搜索
function search_application(){
	$("#app").show();
	$("#service").hide();
	$("#adebts").hide();
	$("#sdebts").hide();
	$('#application_bill_list').datagrid({
		url: ctx + '/rest/userbill/applicationBill',
		method: 'get',
		width:800,
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
		queryParams:{'billType':$("#type").val(),
			'startTime':$("#begindate").datebox('getValue'),'endTime':$('#enddate').datebox('getValue')},
		dataType:'json',
        columns:[[
	        {title:'账单号',field:'billNumber',align: 'center',width:300},
	        {title:'分类',field:'billType',width:150,align: 'center',
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
	    	{title:'账单日期',field:'createdTime',align: 'center',width:150,
	        	formatter: function(value, row, index) {	
        		return getSmpFormatDateByLong(value, false);
	        	}
        	},
	    	{title:'账单费用(￥)',field:'billCosts',align: 'center',width:150},
	        {title:'操作',field:'row',width:150,align: 'center',
        		formatter: function(value,row,index){
	        		return '<a style="color:blue" href="javascript:void(0);" class="dg-button" onclick="viewAppInfo(\'' + row.billId + '\')" >查看详情</a>';
        		}	
        	}
        ]],
        singleSelect: true,
	    selectOnCheck: false,
	    checkOnSelect: false,
		pagination:true
	});
}
//服务账单搜索
function search_service(){
	$("#app").hide();
	$("#service").show();
	$("#adebts").hide();
	$("#sdebts").hide();
	$('#service_bill_list').datagrid({
		url: ctx + '/rest/userbill/serviceBill',
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
		queryParams:{
			'startTime':$("#begindate").datebox('getValue'),'endTime':$('#enddate').datebox('getValue')},
		dataType:'json',
		columns:[[
			    	{title:'账单日期',field:'createTime',align: 'center',width:150},
			        {title:'总费用（￥）',field:'totalCharge',align: 'center',width:150},
    	
			        {title:'操作',field:'chargeMonth',width:100,align: 'center',
		        		formatter: function(value,row,index){
		        			return '<a style="color:blue" href="javascript:void(0);" class="dg-button" onclick="viewServiceInfo(\'' + value + '\', \''+ row.totalCharge +'\')" >查看详情</a>';	        		
		        		}	
		        	}
		        ]],
        singleSelect: true,
	    selectOnCheck: false,
	    checkOnSelect: false,
		pagination:true
	});
}

function viewAppInfo(bId) {
	$.ajax({		
		url: ctx+ "/rest/userbill/applicationBillInfo/" + bId,
		success:function(data) {
			if (data.status == '200' && data.entity) {
				var app = data.entity;
				$("#app-billNumber").html(app.billNumber);
				$("#app-applicationNames").html(app.applicationNames);
				$("#app-createdTime").html(getSmpFormatDateByLong(app.createdTime,false));
				$("#app-billCosts").html(app.billCosts);
				if (app.startTime != undefined  && app.startTime !="") {
					$("#app-startTime").html(getSmpFormatDateByLong(app.startTime,false));
				}
				if (app.endTime != undefined  && app.endTime !="") {
					$("#app-endTime").html(getSmpFormatDateByLong(app.endTime,false));
				}
				var detilStr = "<tr><th>应用名称</th><th>应用价格</th></tr>";
				for ( var i=0; i<app.dataList.length ; i++) {
					detilStr += "<tr><td>" + app.dataList[i].appName + "</td><td>" + app.dataList[i].appPrice + "</td></tr>";

				}
				$("#app_bill_detil").html(detilStr);
				
				$('#appBillInfoModal').modal("show");
			}else{
			     if(data.errorMessage != null){
			         $("#errorInfo").html(data.errorMessage);
			         }
			         $("#errorModal").modal("show");
			         }

			
		}
	});
}
function viewServiceInfo(chargeMonth,totalCharge) {
	$.ajax({		
		url: ctx+ "/rest/userbill/serviceBillInfo/" + chargeMonth,
		success:function(data) {
			if (data.status == '200' && data.entity) {
				var bill = data.entity;		
				$("#ser-accountStart").html(bill[0].createTime);
				$("#ser-totalcharge").html(totalCharge);
				
				$("#ser-prodname").html(bill.prodName);
				var detilStr = "";
				for ( var i=0; i< bill.length ; i++) {
					detilStr += "<div class='w_50'><span style='display:inline-block; width:50%; float:left;'>产品名：</span><span style='display:inline-block; width:40%; float:left;'>" + bill[i].prodName + "</span> </div><div class='w_50'><span style='display:inline-block; width:50%; float:left;'>费用（￥）：</span><span style='display:inline-block; width:40%; float:left;'>" + bill[i].charge + "</span></div>";
				}
				$("#ser_bill_detil").html(detilStr);	
				$('#serBillInfoModal').modal("show");
			}else{
			     if(data.errorMessage != null){
			         $("#errorInfo").html(data.errorMessage);
			         }
			         $("#errorModal").modal("show");
			         }

			
		}
	});
}
//应用欠费查询
function search_app_debts(){
	$("#app").hide();
	$("#service").hide();
	$("#adebts").show();
	$("#sdebts").hide();
	$('#app_debts_bill_list').datagrid({
		url:ctx + '/rest/userbill/appdebtsRecord',
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
		queryParams:{},
		dataType:'json',
        columns:[[
	        {title:'账单号',field:'billNumber',align: 'center',width:300},
	        {title:'分类',field:'billType',width:150,align: 'center',
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
	        {title:'类型',field:'type',width:150,align: 'center',
	        	formatter: function(value){
	    			return '应用';
						}
	        },
	        {title:'账单日期',field:'createdTime',align: 'center',width:150,
	        	formatter: function(value, row, index) {	
        		return getSmpFormatDateByLong(value, false);
	        	}
	        },
	    	{title:'账单费用(￥)',field:'billCosts',align: 'center',width:150},
	        {title:'操作',field:'row',width:150,align: 'center',
        		formatter: function(value,row,index){
	        		return '<a style="color:blue" href="javascript:void(0);" class="dg-button" onclick="viewAppDebtsInfo(\'' + row.billId + '\', \''+ $('#aType').val()+'\')" >查看欠费详情</a>';
        		}	
        	}
        ]],
        singleSelect: true,
	    selectOnCheck: false,
	    checkOnSelect: false,
		pagination:true
	});
}

//服务欠费查询
function search_ser_debts(){
	$("#app").hide();
	$("#service").hide();
	$("#adebts").hide();
	$("#sdebts").show();
	serviceBillDataGride=$('#ser_debts_bill_list').datagrid({
		url:ctx + '/rest/userbill/serdebtsRecord',
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
		queryParams:{},
		dataType:'json',
        columns:[[
	        {title:'账单金额（￥）',field:'totalCharge',align: 'center',width:200},
	        {title:'欠费金额（￥）',field:'debtsCosts',align: 'center',width:200},
	        {title:'欠费日期',field:'createdTime',align: 'center',width:200,
	        	formatter: function(value, row, index) {
	        		if (value != undefined  && value !="") {
	        			return getSmpFormatDateByLong(value, false);
	        		}
	        	}
	        },
	        {title:'账单日期',field:'createDate',align: 'center',width:200},
	        {title:'欠费状态',field:'status',width:150,align: 'center',
	        	formatter : function(val){
	        		switch(val){
						case 0: return '未还';break;
						case 1: return '已还';break;
					}
				}
	        },
	        {title:'操作',field:'row',width:150,align: 'center',
        		formatter: function(value,row,index){
	        		//return '<a style="color:blue" href="javascript:void(0);" class="dg-button" onclick="viewSerDebtsInfo(\'' + row.serviceBillId + '\')" >查看欠费详情</a>';
        			//renewBilView(\'' + serviceBillId + '\')
//        			console.log(row.status);
        			var str = "";
        			var serviceBillId=row.serviceBillId;
        			str += '<a style="color:blue" href="javascript:void(0);" class="dg-button" onclick="viewSerDebtsInfo(\'' 
    					+ row.serviceBillId 
    					+ '\')" >查看详情</a>';
        			if(row.status == 0){
        				str += '<a style="margin-left: 20px;color:blue" href="javascript:void(0);" class="dg-button" onclick="renewBilView(\'' + serviceBillId + '\')" >续费</a>';
        			}
        			return str;
        			
        		}	
        	}
        ]],
        singleSelect: true,
	    selectOnCheck: false,
	    checkOnSelect: false,
		pagination:true
	});
}
function viewAppDebtsInfo(bId,ty) {
	$.ajax({		
		url: ctx+ "/rest/userbill/debtsRecordInfo/" + bId+"/"+ty,
		success:function(data) {
			if (data.status == '200' && data.entity) {
				var debts = data.entity;
				$("#de-billNumber").html(debts.applicationBill.billNumber);
				switch (debts.type){ 
				case 1 : $("#de-type").html('服务');  break; 
				case 2 : $("#de-type").html('应用'); break; 
				} 
				$("#de-debtsCosts").html(debts.debtsCosts);
				switch (debts.status){ 
				case 0 : $("#de-status").html('未还');  break; 
				case 1 : $("#de-status").html('已还'); break; 
				} 
				if (debts.createdTime != undefined  && debts.createdTime !="") {
					$("#de-createdTime").html(getSmpFormatDateByLong(debts.createdTime,false));
				}
				if (debts.paymentDate != undefined  && debts.paymentDate !="") {
					$("#de-paymentDate").html(getSmpFormatDateByLong(debts.paymentDate,false));
				}
				
				$('#debtsBillInfoModal').modal("show");
			}else{
			     if(data.errorMessage != null){
			         $("#errorInfo").html(data.errorMessage);
			         }
			         $("#errorModal").modal("show");
			         }

			
		}
	});
}

function viewSerDebtsInfo(bId) {
	$.ajax({		
		url: ctx+ "/rest/userbill/debtsRecordInfo/" + bId,
		success:function(data) {
			if (data.status == '200' && data.entity) {
				var debts = data.entity;
				$("#de-totalCharge").html(debts.totalCharge);
				$("#de-debtsCosts").html(debts.debtsCosts);
				if (debts.createdTime != undefined  && debts.createdTime !="") {
					$("#de-createdTime").html(getSmpFormatDateByLong(debts.createdTime,false));
				}
				$("#de-createDate").html(debts.createDate);
				switch (debts.status){ 
				case 0 : $("#de-status").html('未还');  break; 
				case 1 : $("#de-status").html('已还'); break; 
				} 
				if (debts.paymentDate != undefined  && debts.paymentDate !="") {
					$("#de-paymentDate").html(getSmpFormatDateByLong(debts.paymentDate,false));
				}
				var detilStr = "<tr><th>产品</th><th>费用</th></tr>";
				for ( var i=0; i< debts.dataList.length ; i++) {
					detilStr += "<tr><td>" + debts.dataList[i].prodName + "</td><td>" + debts.dataList[i].charge +"元"+ "</td></tr>";
				}
				$("#ser_debts_detil").html(detilStr);
				$('#debtsBillInfoModal').modal("show");
			}else{
			     if(data.errorMessage != null){
			         $("#errorInfo").html(data.errorMessage);
			         }
			         $("#errorModal").modal("show");
			         }

			
		}
	});
}
//续费 close-xufei-button
function renewBilView(serviceBillId) {
	var ur=ctx + '/rest/userbill/billrenewpage/'+serviceBillId;
	$.ajax({
		async:false,
		url:ur,
		method: 'get',
		success : function(data) {
			
			var debtsRecordEntityExt=data.entity;
			$("#userId").val(debtsRecordEntityExt.userId);
			$("#chargeMonth").val(debtsRecordEntityExt.chargeMonth);
			$("#chargeYear").val(debtsRecordEntityExt.chargeYear);
			$("#serviceBillId").val(debtsRecordEntityExt.serviceBillId);
			$("#bId").val(debtsRecordEntityExt.debtsId);
			$("#debtsCosts").val(debtsRecordEntityExt.debtsCosts);
			
			$("#bill-debtsCosts").html(debtsRecordEntityExt.debtsCosts+'元');
			
			
			$('#xufeiModal').modal("show");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown){
			  $("#errorInfo").html("与服务器连接失败！");
		}
	});
	
	
	
	}
/**
 * 账单续费 提交form
 * @param url
 */
function  formrenew(url){
	renewMoney(url);

}
/**
 * 云服务 续费 跳转到后台 js
 * @param url
 */
function renewMoney(url){
	$.ajax({	
		async:false,
		dataType : "html",
		type: 'post',
		url : url,
		data:$('#reachargebillform').serialize(),
		success : function(data) {
//			if (data.status != "200" && data.errorMessage != null) {
//				//centerMessage("提示", data.errorMessage);
//				divShow('wrong_tip',data.errorMessage);
//				return;
//			}
			$('#xufeiModal').modal("hide");
			
			serviceBillDataGride.datagrid("reload",{
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown){
			 $("#errorInfo").html("与服务器连接失败！");
	}
	});
}

$(".close-button").click(function() {
	$('.gf_ci_mask').hide();
	$('#appBillInfoModal').modal("hide");
	$('#serBillInfoModal').modal("hide");
	$('#debtsBillInfoModal').modal("hide");
	$('#xufeiModal').modal("hide");//关闭续费弹出框
});