
//smh 试用应用
$("#buy-button").click(function() {
	document.getElementById("buy-button").disabled=true;
	$.ajax({
		url:ctx + "page/application/buyapp",
		data:$("#purchase-app-form").serialize(),
		method:"post",
		success:function(data) {
			if (data.status == '200') {
				var entity=data.entity.applicationEntity;
				var payPattern=data.entity.payPattern;
				if (payPattern == 0) {
					//smh 预支付--跳转到支付页面 （  此中情况 需要支付  支付完成 调用接口  传递参数 applicationId)
					var applicationId=entity.applicationId;
					var maintenanceCosts=data.entity.maintenanceCosts;
					document.getElementById("buy-button").disabled=false;
					window.location.href = ctx + "page/userPayoff/payoff?applicationId="+applicationId+"&maintenanceCosts="+maintenanceCosts;
				} else {//后支付
					if("1"==entity.deployType){//后部署， 弹出部署页面
						if(data.entity.openIsRae){
							var orderNum=data.entity.orderNum;
							var lists=data.entity.lists;
							fillRaeValue(entity,orderNum,lists);
						}else{
							showOperMsg("请开通云引擎服务！");
						}
						
					}else{
						window.location.href = ctx + "page/center/store";//进入云商店
					}
					
				}
			} else{
		     	if(data.errorMessage != null){
		     		$("#errorInfo").html(data.errorMessage);
		     	}
		    	 $("#errorModal").modal("show");
		     }
			
		}
		
	});
});







