<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>数据缓存服务</title>
<link rel="stylesheet" href="${ctx}/static/extends/css/price.css">
<script type="text/javascript">
var currID_t ="case_4";
</script>
</head>
<body>
		<h3 class="gf_sq_tit">
        	<span>RMQ 服务价格</span>
        </h3>
    
    <div class="mgt35">
         	<table class="gf_sq_table">
                 <colgroup>
                    <col width="120">
                    <col width="374">
                    <col width="110">
                </colgroup>
                <thead>
                    <tr>
                        <th>资费类型</th>
                        <th>描述</th>
                        <th>价格</th>
                    </tr>
                </thead>
                <tbody>
                  
                    <tr>
                        <td>按月支付</td>
                       	<td>RCloud平台上的云消息队列产品</td>
                        <td>1800元/月</td>
                    </tr>

                </tbody>
            </table>	
        </div>
</body>
</html>
