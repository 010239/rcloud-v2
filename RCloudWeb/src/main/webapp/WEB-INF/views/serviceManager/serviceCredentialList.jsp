<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../../../static/include/default.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Grid Template for Bootstrap</title>

<script type="text/javascript">
$(document).ready(function(){
	var orderId=$("#orderId").val();
	var serviceId=$("#serviceId").val();
	$('#user_list').datagrid({
		url:rcloudweb+'rest/cloudservice/credential/list',
		method: 'get',
		singleSelect:true,
		fitColumns:true,
		rownumbers: true,
		idField: 'serviceName',
		loadFilter: function (data) {
	    	if (data.status == '200') {
	    		console.log("Service Credential");
	    		console.log(data.entity);
	    		return data.entity;
	    	}else{
	    		if(data.errorMessage != null){
	    			 $.messager.alert("异常消息",data.errorMessage);
		    		//$("#errorInfo").html(data.errorMessage);
	    		}
	    		//$("#errorModal").modal("show");
	    	}
	   },
        columns:[[
	        {field:'keyID',title:'服务凭证',width:200},
	        {field:'serviceName',title:'服务',width:200},
        	{field:'chname',title:'套餐',width:150},
        	{field:'orderNum',title:'订单号',width:150},
	       	{title:'凭证状态',field:'status',width:80,formatter : function(val){
				  switch(val){
					case 0:
						return '未激活';break;
					case 1:
						return '有效';break;
					case 2:
						return '失效';break;
					case 3:
						return '注销';break;
						   }
					}
		     },
        	
        	{field:"row", width:250, title: '操作',align: 'center', formatter: function(value,row,index){
        		
        		  switch(row.status){
					case 0:
						return "<a style='color:blue' href='"+rcloudweb+"page/cloudservice/getServiceCredentialInfo/" + row.keyID + "/" + row.keySecret + "' > 查看</a>&nbsp&nbsp";
						break;
					case 1:
						return "<a style='color:blue' href='"+rcloudweb+"page/cloudservice/getServiceCredentialInfo/" + row.keyID + "/" + row.keySecret + "' > 查看</a>&nbsp&nbsp"+
						"<a style='color:blue' href='"+rcloudweb+"page/cloudservice/CancelServiceCredentia/" + row.keyID +  "/" + orderId + "/" + serviceId + "' > 撤销</a>";
						break;
					case 2:
						return "<a style='color:blue' href='"+rcloudweb+"page/cloudservice/getServiceCredentialInfo/" + row.keyID + "/" + row.keySecret + "' > 查看</a>&nbsp&nbsp";
						break;
					
					}}
        		
	          },
	        	
        ]]
	
	
	});
	
	

});
</script>
</head>
<body>
	<input type="hidden" id="orderId" value="${orderId}" />
	<input type="hidden" id="serviceId" value="${serviceId}" />
	<table id="user_list" data-options="fit:true"></table>
</body>
</html>
