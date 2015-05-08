//模拟radio
(function (){
    $('.gf_pay_ul').on('click','li',function (){
        $('.gf_pay_ul').find('i').removeClass('on');
        $(this).find('i').addClass('on');
    });
})();

//smh 试用应用
$("#submitzhifuid").click(function() {
	if ($("#rechargeMoneyFormIDzz").form('enableValidation').form('validate')) {
		var moneyAmount=$("#moneyAmount").val();
		var amountmoneyid=$("#amountmoneyid").val();
	
		if($("#moneyAmount").val()==""){
			$("#msg2").html("<font color='red'>支付金额不能为空！</font>");
			return;
		}else if(parseFloat(amountmoneyid)<parseFloat(moneyAmount)){
					$("#msg2").html("<font color='red'>金额不足,请充值!</font>");
					return;
		}else{
			document.getElementById("submitzhifuid").disabled=true;
			zhifumoney(ctx + 'rest/userconsole/defrayMoney');	
		}
	}
});
	/**
	 * 支付金额
	 */


function zhifumoney(url){
	$.ajax({	
		async:false,
		type: 'post',
		url : url,
		data:$('#rechargeMoneyFormIDzz').serialize(),
		success : function(data) {
				var entity=data.entity.applicationEntity;
			var openRaePreFlag=data.entity.openRaePreFlag;
			var orderNumber=data.entity.orderNum;
		   if (data.status != "200" && data.errorMessage != null) {
			   $("#errorInfo").html(data.errorMessage);
			   $("#errorModal").modal("show");
				return;
			}
			if("1"==entity.deployType){//后部署， 弹出部署页面
				if(openRaePreFlag){
					var lists=data.entity.lists;
					fillRaeValue(entity,orderNumber,lists);
				}else{
					showOperMsg("请开通云引擎服务！");
				}
				
			}else{
				//预部署 
				window.location.href = ctx + "page/center/store";
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown){
			 $("#errorInfo").html("与服务器连接失败！");
			   $("#errorModal").modal("show");
	}
	});
	

	
}

	

	
