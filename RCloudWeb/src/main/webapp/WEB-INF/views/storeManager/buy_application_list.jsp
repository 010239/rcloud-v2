<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>
		<jsp:include page="../../../static/include/include.jsp"></jsp:include>
		<script type="text/javascript" src="../../../static/extends/js/bootstrap.datagrid.plugin.js"></script>
		<script type="text/javascript">var rcloudweb = "${ctx}/";</script>
		<script type="text/javascript">
		$(document).ready(function(){
			//加载表格数据
			$("#bsdatagrid").bsdatagrid("load", {
				//是否需要分页
				pagination: "#bspagination",
				page: 1,
				rowTotal: 10,
				//获取表格数据
				url: rcloudweb+"rest/applicationpublish",
				cols: [
				        {"logoPath":"应用logo"},
				      	{"applicationName":"应用名称"},
				      	{"createdTime":"购买时间"},
				      	{"dealCount":"卖方"},
				      	{"operation":"操作", }
				      ]
				});
				$("#reload").bind("click", function(){
					$("#bsdatagrid").bsdatagrid("reload");
				});
			});
		</script>
	</head>
	<body>
		<table id="bsdatagrid" class="table table-hover">
		</table>
		<div style="width:100%;" align="center">
			<ul id="bspagination" class="pagination"></ul>
		</div>
	</body>

</html>