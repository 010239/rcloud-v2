$(document).ready(function(){
	$.ajax({
		url: ctx + 'rest/cloudservicePlatform/cloudServiceBuyCheck/'+$("#serviceId").val(),
		async:false,
		success: function(data){
			if (data.status == '200') {
				if(data.entity=='true'){
					$("#servicebuy").html('');
				}
			}			
		}
	});
});