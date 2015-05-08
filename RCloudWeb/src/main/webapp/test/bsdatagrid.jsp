<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Grid Template for Bootstrap</title>
<link href="static/lib/css/bootstrap.min.css" rel="stylesheet">
<!--
<link href="static/lib/css/bootstrap-theme.min.css" rel="stylesheet">
<script type="text/javascript" src="static/lib/js/bootstrap.min.js"></script>
-->
<script type="text/javascript" src="static/lib/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="static/extends/js/bootstrap.datagrid.plugin.js"></script>
<!-- 
<script type="text/javascript" src="static/extends/js/bootstrap.pagination.plugin.js"></script>
 -->
<script type="text/javascript">
	$(document).ready(function(){
		//加载表格数据
		$("#bsdatagrid").bsdatagrid("load", {
			//是否需要分页
			pagination: "#bspagination",
			page: 1,
			rowTotal: 20,
			//获取表格数据
			url: "rest/application/pagination",
			cols: [
			      	{"applicationId":"applicationId","formatter":function(row){
			      		return "<a href='#' >" + row + "</a>" ;
			      	}},
			      	{"applicationName":"applicationName"},
			      	{"createdTime":"createdTime"},
			      	{"shelveTime":"shelveTime"},
			      	{"logoPath":"logoPath"},
			      	{"status":"status"},
			      	{"providerId":"providerId"},
			      	{"markDelete":"markDelete"},
			      	{"maintenanceCosts":"maintenanceCosts"},
			      	{"subDomain":"subDomain"},
			      	{"applicationType":"applicationType"},
			      	{"baseApp":"baseApp"},
			      	{"appVersion":"appVersion"},
			      	{"publisher.userName":"名称"}
			      ]
		});
		$("#reload").bind("click", function(){
			$("#bsdatagrid").bsdatagrid("reload");
		});
	});
</script>
</head>
<body>
	<button id="reload" type="button" class="btn btn-success">刷新</button>
	<table id="bsdatagrid" class="table table-hover">
	</table>
	<div style="width:100%;" align="center">
		<ul id="bspagination" class="pagination"></ul>
	</div>
</body>
</html>
