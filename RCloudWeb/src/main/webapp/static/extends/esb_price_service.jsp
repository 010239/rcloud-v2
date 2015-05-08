<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>云ESB服务</title>
<link rel="stylesheet" href="${ctx}/static/extends/css/price.css">
<script type="text/javascript">
var currID_t ="case_4";
</script>
</head>
<body>
		<h3 class="gf_sq_tit">
        	<span>RESB 服务价格</span>
        </h3> 
        <!--  
        <div class="mgt35">
         	<table class="gf_sq_table">
                <colgroup>
                    <col width="89">
                    <col width="180">
                    <col width="84">
                </colgroup>
                <thead>
                    <tr>
                        <th>服务名</th>
                        <th>资源类型</th>
                        <th>人民币价格</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td rowspan="2">HTTP [1]</td>
                        <td>流入流量</td>
                        <td>1元/GB</td> 	 	
                    </tr>
                    <tr>
                    	<td>流出流量</td>
                    	<td>1.5元/GB</td>
                    </tr>
                    <tr>
                        <td rowspan="2">HTTPS</td>
                        <td>流入流量</td>
                        <td>2元/GB</td> 	 	
                    </tr>
                    <tr>
                    	<td>流出流量</td>
                    	<td>3元/GB</td>
                    </tr> 
                </tbody>
            </table>	
        </div>
        <div class="mgt35">
         	<table class="gf_sq_table">
                <colgroup>
                    <col width="10">
                    <col width="90">
                </colgroup>
                <tbody>
                    <tr>
                        <td>[1]</td>
                        <td>PHP和Python应用的HTTP服务按此价格计算，Java的价格见下表。</td> 	 	
                    </tr>

                </tbody>
            </table>	
        </div>
        <p class="gf_sq_ptit mgt20"><strong>Java HTTP服务价格</strong></p>-->
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
                       	<td>RCloud平台上的云服务总线产品</td>
                        <td>9000元/月</td> 	
                    </tr>
                </tbody>
            </table>	
        </div>
</body>
</html>

