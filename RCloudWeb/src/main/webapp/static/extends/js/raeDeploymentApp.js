/**
 * shimeihua   公用部署到RAE js
 */

/**
 * 填充rae页面
 * @param applicationName
 * @param appVersion
 * @param detailDescription
 */
function fillRaeValue(entity,orderNumber,list){
	var appVersion=entity.appVersion;
	var applicationId=entity.applicationId;
	var detailDescription=entity.detailDescription;
	var applicationName=entity.applicationName;
	var developId=entity.providerId;
	var sele=$("#spaceid");
	var str="";
	if(list){
		for(var i=0;i<list.length;i++){
			var obj=list[i];
			str+='<option value="'+obj.spaceName+'">'+obj.spaceChname+'</option>';
			
		}
		sele.append(str);
	}
	//发布者的id
	$("#developId").val(developId);
	$("#orderNum").val(orderNumber);
	$("#appId").val(applicationId);
	$("#appName").val(applicationName);
	$("#version").val(appVersion);
	$("#remark").val(detailDescription);
	$("#errorText").html("");
	$("#dlg_createApplication").dialog("open");
}
/**
 * 部署应用rae 1
 */
function submit_createApplication(){
   if ($('#form_createApplicatioin').valid()) {
	   var result=$("#checkUrl").val();
	   var domainName=$("#domainNameaa").val();
	   var appName=$("#appName").val();
	   if(appName == ""){
			var html="<font  color=\"red\">应用名称不能为空!</font>";
			$("#domainNameapp").html("<font  color=\"red\" size=4><b>×</b></font>");
			$("#errorTextapp").html(html);
			return;
		}
	   $("#errorTextapp").html("");
	   $("#domainNameapp").html("<font  color=\"green\" size=4><b>√</b></font>");
	   if(domainName == ""){
			var html="<font  color=\"red\">域名不能为空!</font>";
			$("#errorTextdom").html(html);
			$("#domainNameStat").html("<font color=\"red\" size=4 ><b>×</b></font>");
			return;
		}
		if(result=="true")
		{
			var html="<font  color=\"red\">域名已经存在!</font>";
			$("#errorTextdom").html(html);
			return ;
		}else{
			$("#errorTextdom").html("");
			appName=$("#domainName").val();
		} 
		
		$("#diskName").val($("#diskValue").slider("getValue"));
		$("#ramName").val($("#ramValue").slider("getValue"));
	   
	   
	   
	   deploymentApp();
	}
}

function  close_createApplication(){
	$('#dlg_createApplication').dialog('close');
	window.location.href = ctx + "page/center/store";//进入云商店
}
/**
 * 部署应用rae 2
 */
function deploymentApp(){
	$.ajax({
		url:ctx + "rest/applicationpublish/deploymentApplication",
		data:$("#form_createApplicatioin").serialize(),
		method:"post",
		async:false,
		success:function(data) {
			if (data.status == '200') {
				var ent=data.entity;
				if(ent){
					$.messager.alert("提示", "应用部署成功!", "info", function () {
						window.location.href = ctx + "page/center/store";//进入云商店
					});
				}else{
					showOperMsg("部署服务失败！");
				}
			} else{
		     	if(data.errorMessage != null){
		     		var arr=data.errorMessage;
		     		arr=eval('('+arr+')');
		     		//解析
		     		var html="";
		     		if("10003"==arr.code){
		     			html="<font  color=\"red\">权限不足，请重新选择命名空间!</font>";
		     		}else if("210003"==arr.code){
		     			html="<font  color=\"red\">域名已经被占用!</font>";
		     		}else if("13131414"==arr.code){
		     			html="<font  color=\"red\">工作空间不可用!</font>";
		     		}else{
		     			html="<font  color=\"red\">服务器异常!</font>";
		     			alert(data.errorMessage);
		     		}
		     		$("#errorText").html(html);
		     	}
		     }
			
		}
		
	});
}
function checkdomain(obj)
{
	if(obj.value == ""){
		var html="<font  color=\"red\">域名不能为空!</font>";
		$("#errorTextdom").html(html);
		$("#domainNameStat").html("<font color=\"red\" size=4 ><b>×</b></font>");
		return;
	}else{
		var str = obj.value;
		if(/[\u4E00-\u9FA5]/g.test(str)){
			var htmls="<font  color=\"red\">域名不能为汉字，请重新输入...</font>";
			$("#errorTextdom").html(htmls);
			return;
		}else{
			$("#errorTextdom").html("");
		}
	}
	$.ajax({
		url:ctx + "rest/applicationpublish/checkdomain/"+obj.value+"/",
		success:function(data) {
			if (data.status == '200') {
				var result = data.entity;
				  if(result)
	                {
	                	var html="<font  color=\"red\">域名已经存在!</font>";
	                	$("#domainNameStat").html("<font  color=\"red\" size=4><b>×</b></font>");
						$("#errorTextdom").html(html);
						$("#checkUrl").val("true");
	                }else{
	                	//var html="<font  color=\"green\">"+obj.value+"域名可以使用!</font>";
	                	$("#domainNameStat").html("<font  color=\"green\" size=4><b>√</b></font>");
	                	$("#errorTextdom").html("");
	                	$("#checkUrl").val("false");
	                }
				
			} else{
			    if(data.errorMessage != null){
			    	var htmls="<font  color=\"red\">"+data.errorMessage+"</font>";
			        $("#errorTextdom").html(htmls);
			    }
			}
		}
	});
	
	
}
