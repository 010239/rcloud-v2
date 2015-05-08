<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../../../static/include/default.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Grid Template for Bootstrap</title>

<script type="text/javascript">
$(document).ready(function(){
	var serviceId=$("#serviceId").val();
	var orderId=$("#orderId").val();
	
	$('#user_list').datagrid({
		singleSelect:true,
		fitColumns:true,
		rownumbers: true,
		url:rcloudweb+'rest/cloudservice/getInclusivePriceList/'+serviceId,
		method: 'get',
		idField: 'id',
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
				  {field:'id', title: 'deptId' , hidden: true, width:1},
		          {field:'chname', title: '套餐类型' ,  width:200},
		          {field:'row1', title: '路由数' , width:300, formatter: function(value,row,index){
						return 50;
					}},
		          {field:'row2', title: '内存' , width:250, formatter: function(value,row,index){
						return 2048;
					}},
		          {field:'row3', title: '硬盘' , width:350, formatter: function(value,row,index){
						return 200;
					}},
		          {field:'price', title: '单价' , width:350},
		          {field:"row", width:350, title: '操作',align: 'center', formatter: function(value,row,index){
						return "<a style='color:blue' href='"+rcloudweb+"page/cloudservice/selectInclusivePrice/" + row.id + "/" + row.chname + "/" + orderId + "' > 选择</a>";
						}
			      },
		]]
	});
});
</script>
</head>
<body>
	<input type="hidden" id="serviceId" value="${serviceId}" />
	<input type="hidden" id="orderId" value="${orderId}" />
	<table id="user_list" data-options="fit:true"></table>
</body>
</html>
