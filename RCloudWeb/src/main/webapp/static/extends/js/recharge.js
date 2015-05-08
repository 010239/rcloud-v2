$("#recharge-button").click(function() {
	$.ajax({
		url:ctx + "/portal/recharge",
		data:$("#recharge-form").serialize(),
		method:"post",
		success:function(data) {
			if (data.status == "200") {
				alert(data.entity + "账户充值成功！");
			} else {
				alert(data.entity + "账户充值失败！");
			}
		}
	});
});