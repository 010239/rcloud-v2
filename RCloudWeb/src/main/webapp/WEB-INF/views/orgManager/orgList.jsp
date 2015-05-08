<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../../../static/include/default.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>组织管理</title>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#user_list')
								.datagrid(
										{
											url : '${ctx}/rest/org/spaceinfo',
											method : 'get',
											singleSelect : true,
											fit : true,
											fitColumns : true,
											rownumbers : true,
											//idField: 'orderId',
											loadFilter : function(data) {
												if (data.status == '200') {
													console.log(data.entity);
													return data.entity;
												} else {
													if (data.errorMessage != null) {
														$.messager.alert("异常消息",data.errorMessage);
														//$("#errorInfo")
																//.html(
																		//data.errorMessage);
													}
													//$("#errorModal").modal(
															//"show");
												}
											},
											columns : [ [
													{
														field : 'organization_chname',
														title : '机构名称',
														width : 200
													},
													{
														field : 'space_chname',
														title : '工作空间',
														width : 200
													},
													{
														field : 'countDev',
														title : '开发者（人）',
														width : 150
													},
													{
														field : "row1",
														title : '操作',
														width : 250,
														align : 'center',
														formatter : function(
																value, row,
																index) {		
															return "<a style='color:blue' href='${ctx}/page/org/bingdevloper/"+row.space_id+"'>绑定开发者 </a>";
													
														}
													},

											] ]

										});

					});
</script>
</head>
<body>
	<div style="margin-top: 5px; margin-bottom: 5px; margin-right: 5px;"
		align="right">
		<button class="btn btn-primary" data-toggle="button"
			onclick="window.location.href='${ctx}/page/org/createspace'">工作空间注册</button>
		<button class="btn btn-primary" data-toggle="button"
			onclick="window.location.href='${ctx}/page/org/createdevloper'">机构开发者注册</button>
	</div>
	<div style="height: 525px">
		<div id="publish-application-div" style="height: 500px">
			<table id="user_list"></table>
		</div>
	</div>
</body>
</html>
