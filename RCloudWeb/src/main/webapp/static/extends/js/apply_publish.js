(function(){
//	$("input.gf_yyfb_next1").click(function(){
//		$(this).parents('.gf_yyfb_stepsbox').hide();
//		$(this).parents('.gf_yyfb_stepsbox').next('.gf_yyfb_stepsbox').show();
//	});
	
	$.ajax({
		url:ctx + "common/applicationType/list",
		success:function(data) {
			if (data.status == '200') {
				var typeList = data.entity;
				var optionHtml = '';
				for (var i = 0; i < typeList.length; i++) {
					optionHtml += '<option value="' + typeList[i].typeId + '">' + typeList[i].typeName + '</option>';
				}
				$("#application-type").append($(optionHtml));
			} else {
				if(data.errorMessage != null){
				    $("#errorInfo").html(data.errorMessage);
				}
				$("#errorModal").modal("show");
			}
			
		}
	});
	
})();

$(document).ready(function() {
	$('#uri-form').validate();
});

$("#first-button").click(function() {
	
	var type=$("#publicType").val();
	var isWarSu=$("#isWarSu").val();
	if ($('#uri-form').valid()) {
		if("1"==type){//0:预部署    1：后部署
			$("#uri-input").val($("#uri-inputafter").val());
			if("success"==isWarSu){//上传成功
				firststepfun();
			}else{
				showOperMsg("war包未上传成功！");
			}
		}else if("0"==type){
			$("#uri-input").val($("#uri-inputbefore").val());
			firststepfun();
		}else{
			$("#uri-input").val($("#uri-inputout").val());
			firststepfun();
		}
	} 
});

function firststepfun(){
	document.getElementById("first-button").disabled=true;
	$("#firs-step").hide();
	$("#uri-value").val($("#uri-input").val());
	$("#isWebs").val($("#isallow").val());
	$("#deployTypes").val($("#publicType").val());
	
	$("#filewarTypes").val($("#filetypes").val());
	
	
	$("#second-step").show();
	$('#publish_application_form').validate();
}
/**
 * 发布应用 1
 */
$("#submit_button").click(function() {
	if ($('#publish_application_form').valid()) {
		
			shelPublish();
		
	
		
	}
	
});
/**
 *发布应用 2 
 */
function shelPublish(){
	$.ajax({
		url:ctx + "rest/applicationpublish/insertApplication",
		data:$("#publish_application_form").serialize(),
		method:"post",
		success:function(data) {
			if (data.status != '200') {
				if(data.errorMessage != null){
		     		$("#errorInfo").html(data.errorMessage);
		     	}
		    	$("#errorModal").modal("show");
			} else {
				$("#second-step").hide();
				$("#third-step").show();
			}
		}
		
	});
}
/**
 * 部署类型 改变事件
 */
function publishTypeChange(){
	var type=$("#publicType").val();

	if("0"==type){//0:预部署    1：后部署 3.外部访问
		document.getElementById("divbefore").style.display="block";
		document.getElementById("divout").style.display="none";
		document.getElementById("divafter").style.display="none";
	}else if("1"==type){
		document.getElementById("divbefore").style.display="none";
		document.getElementById("divout").style.display="none";
		document.getElementById("divafter").style.display="block";
	}else{
		document.getElementById("divbefore").style.display="none";
		document.getElementById("divout").style.display="block";
		document.getElementById("divafter").style.display="none";
	}

}
/**
 * 是否允许下载应用包
 */
function allowDownloadAPP(){
	var isallowDownload=document.getElementById("isallowDownload").checked;
	if(isallowDownload){//是否允许下载应用包(0-不允许，1-允许)
		$("#isallow").val("1");
	}else{
		$("#isallow").val("0");
		
	}
}
/**
 * 上传war包
 */
	function uploadsubwar(){
		var fileurl=$("#fileurl").val();
		var FileExtend = ".zip,.war";
		if(fileurl==""){
			showOperMsg("请选择要上传的war包！");
		}else if(FileExtend.indexOf(fileurl.substring(fileurl.lastIndexOf('.')).toLowerCase())==-1){
			showOperMsg("不能上传此类型的应用![支持应用格式为.zip/.war]！");
			
		}else{
			Load("上传应用包");
			 $.ajaxFileUpload({  
		         url:ctx + "uploadVersion/upload", 
		         data : {"key" : "file"}, 
		         secureuri:false,  
		         fileElementId:"file",  
		         dataType: 'json',
		         success: function (data, status){  
		        		if (data.status == '200') {
							if(data.errorMessage != null && ""!=data.errorMessage){
					     		$("#errorInfo").html(data.errorMessage);
					     		$("#errorModal").modal("show");
					     	}
							dispalyLoad();
							var str=data.entity;//上传架包成功后，返回的地址
							$("#isWarSu").val("success");
							$("#filetypes").val(fileurl);
							
							showOperMsg("war包上传成功！");
						} 
		         },  
		         error: function (data, status, e){
		         }  
		    	});
		}
	
	
	 }
function retvalue(){
	$("#fileurl").val($("#file").val());
}