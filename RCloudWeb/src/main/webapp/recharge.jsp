<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账户充值</title>
<link rel="stylesheet" href="${ctx}/static/lib/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/static/lib/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${ctx}/static/extends/css/style.css">
<script src="${ctx}/static/extends/js/jquery_v1.10.2_min.js"></script>
<script type="text/javascript" src="${ctx}/static/extends/js/jquery.validate.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
</script>
</head>
<body>
<div class="gf_regist_warp">
	<div class="gf_reg_content">
    	<h3 class="gf_yyfb_t">账号充值</h3>
		<form action="" id="recharge-form">
			<div class="gf_regis_imform gf_yyfb_steps">
	            <dl class="cf">
	                <dt>充值账号：</dt>
	                <dd><input id="uri-input" type="text" placeholder="" name="userName" data-rule-required="true" data-rule-url="true"/></dd>
	            </dl>
	            <dl class="cf">
	                <dt>充值金额：</dt>
	                <dd><input id="uri-input" type="text" placeholder="" name="money" data-rule-required="true" data-rule-url="true"/>元</dd>
	            </dl>
	            <dl class="cf gf_reg_btn">
	                <dt></dt>
	                <dd><input id="recharge-button" class="gf_yyfb_next1" type="button" value="充值"/></dd>
	            </dl>
	        </div>
		
		</form>
	</div>
</div>
</body>
<script type="text/javascript" src="${ctx}/static/extends/js/recharge.js"></script>
</html>