<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../../../static/include/default.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Grid Template for Bootstrap</title>

<script type="text/javascript">
var role = "${role}";
var userId = "${userId}";

var accessKeyDataGrid = null;
var serviceDataGrid = null;
$(document).ready(function(){
		reloadAccessKey();
});
</script>
</head>
<body>
	<div style="margin-top: 5px; margin-bottom: 5px; margin-right: 5px;"
		align="right">
		<button class="btn btn-primary" data-toggle="button"
			onclick="reloadAccessKey()">服务凭证列表</button>
	    <button class="btn btn-primary" data-toggle="button"
			onclick="createAccessKey()">生成服务凭证</button>
	</div>
	<div style="height: 525px">		
		<div id="access-key-div" style="height: 500px; display: hidden;">
			<table id="access_key_list" data-options="fit:true"></table>
		</div>
	</div>
</body>

<script type="text/javascript">
    function reloadAccessKey() {
    	$("#publish-application-div").hide();
    	$("#access-key-div").show();
    	
    	if (accessKeyDataGrid == null) {
    		accessKeyDataGrid = $('#access_key_list').datagrid({
        		url:rcloudweb+'rest/cloudservice/credential/list',
        		method: 'get',
        		singleSelect:true,
        		fitColumns:true,
        		rownumbers: true,
        		idField: 'serviceName',
        		loadFilter: function (data) {
        	    	if (data.status == '200') {
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
        	        {field:'accessKeyId',title:'服务凭证',width:200},
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
        		     {field:'creator',title:'创建人',width:200},
                	{field:"row", width:250, title: '操作',align: 'center', formatter: function(value,row,index){
                		var operation = "<a style='color:blue' href='"+rcloudweb+"page/cloudservice/getServiceCredentialInfo/" + row.accessKeyId + "/" + row.accessKeySecret + "' > 查看</a>&nbsp&nbsp";
                		
                		if (row.status == 0 || row.status == 2 || (row.status == 1 && role == '400003' && row.userId != userId)) {
                			return opration;
                		} else {
                			return operation + '<a style="color:blue" href="javascript:void(0);" onClick="cancelCredential(\'' + row.accessKeyId + '\')" > 撤销</a>';
                		}
                		
                	}
                		
        	     },
        	        	
                ]]
        	
        	
        	});
    	} else {
    		accessKeyDataGrid.datagrid('reload');  
    	}
    	
    	
    }
    
    function createAccessKey() {
    	$.ajax({
    		url:rcloudweb + "rest/cloudservice/createServiceCredential",
    		method:"get",
    		success:function(data) {
    			if (data.status == '200') {
    				$("#publish-application-div").hide();
    		    	$("#access-key-div").show();
    		    	accessKeyDataGrid.datagrid('reload'); 
    			} else {
    				$.messager.alert("异常消息",data.errorMessage);
    			}
    		}
    	});
    }
    
    function cancelCredential(credentialId) {
    	$.ajax({
    		url:rcloudweb + "rest/cloudservice/CancelServiceCredentia/" + credentialId,
    		method:"get",
    		success:function(data) {
    			if (data.status == '200') {
    				$("#publish-application-div").hide();
    		    	$("#access-key-div").show();
    		    	accessKeyDataGrid.datagrid('reload'); 
    			} else {
    				$.messager.alert("异常消息",data.errorMessage);
    			}
    		}
    	});
    }
</script>
</html>
