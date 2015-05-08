<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../../../static/include/default.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>组织管理</title>

<link type="text/css" href="${ctx}/static/extends/css/error_hint.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${ctx}/static/lib/js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx}/static/lib/js/jquery.validationEngine.min.js"></script>
<script type="text/javascript"
	src="${ctx}/static/lib/js/jquery.placeholder.js"></script>
<script type="text/javascript">
	$(function() {
		$("#form").validationEngine({
			onValidationComplete : function(form, valid) {
				if (valid == true) {
					document.form.submit();
				}
				return false;

			},//表单验证后的回调函数，form为提交的表单，valid为结果，验证通过则为true,验证失败则为false
			scroll : false,
			focusFirstField : false
		});
		
		
		$.get("${ctx}/rest/org/checkorg/${spaceID}",function(data){
			if (data.status == '200'){
				var mydata = data.entity;
				for (var i = 0; i < mydata.length; i++) {
					$("#"+mydata[i].userId).attr('checked',true)
				}
			}else{
				     if(data.errorMessage != null){
				            //$("#errorInfo").html(data.errorMessage);
				    	 $.messager.alert("异常消息",data.errorMessage);
				     }
				     //$("#errorModal").modal("show");
				     
			}
			
		});
		
		
	})
</script>
<style type="text/css">
.mess {
	padding-top: 70px ;
	padding-left: 290px;
	padding-right: auto;
}
.mess p{
	color:red;
	font-size:24px;
	font-family:"Microsoft Yahei";
}
</style>
</head>
<body>
<c:if test="${empty list }">
	<div class="mess"><p>请您先注册机构开发者,然后再绑定!</p></div>
	<div style="width:100% ;padding-top:180px" align="center" >
		<button type="button" class="gf_info_btn" onclick="window.history.back()">返回</button>
	</div>
</c:if>
<c:if test="${not empty list }">
	<form id="form" method="post" action="${ctx}/page/org/bingdevloper">
		<input type="hidden" name="spaceId" value="${spaceID}">
		<div style="height:480px;overflow-x:hidden;overflow-y:auto;">
			<table class="table table-hover">
			    <tr>
			        <th></th>
			        <th>姓名</th>
			        <th>邮箱</th>
			        <th>所属空间</th>
			    </tr>
				<c:forEach items="${list}" var="obj">
					<tr>
					    <td>
					        <input type="checkbox" id="${obj.user_id}" name="userId" value="${obj.user_id}">
					    </td>
					    <td>
					        ${obj.real_name }
					    </td>
					    <td>
					        ${obj.user_email }
					    </td>
					    <td>
					        ${obj.space_chname }
					    </td>
					</tr>
				</c:forEach>
			</table>			
		</div>
		<div style="width:100%" align="center">
			<button type="submit" class="gf_info_btn">提交</button>&nbsp;&nbsp;
			<button type="button" class="gf_info_btn" onclick="window.history.back()">返回</button>
		</div>
	</form>
</c:if>
</body>
</html>
