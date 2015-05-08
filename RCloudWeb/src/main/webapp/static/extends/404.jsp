<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>错误页面</title>
</head>
<body>
<%@ include file="../include/top.jsp" %>
<div class="gf_err">
    <div class="gf_err_404">
        <p class="gf_err_p1">${errMsg }</p>
        <p class="gf_err_p2"><a href="javascript:history.go(-1);">返回</a></p>
    </div>
</div>
<div class="gf_footer">
    <div class="gf_err_bg1">
        <div class="gf_err_bg2"></div>
    </div>
<%@ include file="../include/buttom.jsp" %>
</div>
<script type="text/javascript" src="js/jquery.cycle.lite.js"></script>
<script type="text/javascript" src="js/jquery.cycle.all.js"></script>
<script src="js/common.js"></script>
<script src="js/store.js"></script>
</body>
</html>

