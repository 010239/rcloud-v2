var publishDatagrid;
var buyDatagrid;
$(document).ready(function(){
	publishDatagrid = $('#publish-application-dg').datagrid({
	    url: ctx + '/rest/transaction/getTsatList',
	    method:'get',
	    loadFilter: function (data) {
	    	if (data.status == '200') {
	    		console.log(data.entity);
	    		return data.entity;
	    	}else{
	    		if(data.errorMessage != null){
		    		$("#errorInfo").html(data.errorMessage);
	    		}
	    		$("#errorModal").modal("show");
	    	}
	    },
	    queryParams:{
	    	transactionType:$("#transactionType").val(),
	    	moneyFlow:$("#moneyFlow").val(),
	    	beginTime:$("#beginTime").datebox("getValue"),
	    	endTime:$("#endTime").datebox("getValue")
	    },
	    rownuRmbers: false,
	    columns:[[
			{ field: 'recordId', title: '流水号',width:250,align: 'center' },
			{ field: 'dateShow', title: '日期',width:150 ,align: 'center'},
			{ field: 'transactionType', title: '动作',width:200,align: 'center' ,
	        	formatter : function(val){
					  switch(val){
						case 1:
							return '充值';break;
						case 2:
							return '应用订单支付';break;
						case 3:
							return '服务订单支付';break;
						case 4:
							return '应用账单扣款';break;
						case 5:
							return '服务账单扣款';break;
						case 6:
							return '应用账单收入';break;
						case 7:
							return '账户提现';break;
						case 8:
							return '欠费账单续费';break;
							
							   }
					  
						}
			},
			{ field: 'income',title: '收入（￥）',width:120,align: 'center',
				formatter:function(val,row){
					if(row.moneyFlow == 1 ){
						return row.transactionAmount;
					}
					return "";
				} },
			{ field: 'expend',title: '支出（￥）',width:120,align: 'center',
					formatter:function(val,row){
						if(row.moneyFlow == 0){
							return row.transactionAmount;
						}
						return "";
					} },
			{ field: 'operation',title: '操作',width:130,align: 'center',
				formatter:function(val,row){
                    return '<a href="javascript:void(0);" onclick="viewRecord(\''+row.recordId+'\')">查看详情</a>';  
				}
			}
	              ]],
		singleSelect: true,
	    selectOnCheck: false,
	    checkOnSelect: false,
		pagination:true
	});
	
});

function search(){
	$('#publish-application-dg').datagrid("load",{
    	transactionType:$("#transactionType").val(),
    	moneyFlow:$("#moneyFlow").val(),
    	beginTime:$("#beginTime").datebox("getValue"),
    	endTime:$("#endTime").datebox("getValue")
    });
}

function viewRecord(Id){
	$.ajax({		
		url: ctx+ "/rest/transaction/detail/" + Id,
		success:function(data) {
			if (data.status == '200' && data.entity) {
				var orderInfo = data.entity;

				$("#recordId").html(orderInfo.recordId);
				
				$("#dateShow").html(getFormatDateByLong(orderInfo.transactionTime,"yyyy-MM-dd"));
				$("#description").html(orderInfo.description);
				
				if(orderInfo.moneyFlow == 1 ){
					$("#income").html(orderInfo.transactionAmount);
				}else{
					$("#income").html("");
				}
				
				if(orderInfo.moneyFlow == 0){
					$("#expend").html(orderInfo.transactionAmount);
				}else{
					$("#expend").html("");
				}

				$('#serAppInfoModal').modal("show");
			} else {
				if (data.errorMessage != null) {
					$("#errorInfo").html(data.errorMessage);
				}
				$("#errorModal").modal("show");
			}		
		}
	});
}


$(".detail-button").click(function() {
	
	$('#serAppInfoModal').modal("hide");
	$("#recordId").html('');
	$("#dateShow").html('');
	$("#description").html(''); 
	$("#income").html('');
	$("#expend").html('');
	
});

