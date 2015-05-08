<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>关系数据库服务</title>
<link rel="stylesheet" href="${ctx}/static/extends/css/price.css">
</head>
<body class="gf_idx">
    	<h3 class="gf_sq_tit">
        	<span>服务资源价目表</span>
        </h3>
        <div class="mgt20">
         	<table class="gf_sq_table">
                <colgroup>
                    <col width="116">
                    <col width="138">
                    <col width="466">
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
                        <td>请求数</td>
                        <td>1.5元/百万次</td>
                    </tr>
                    <tr>
                        <td>磁盘用量</td>
                        <td>0.05元/GB/天</td>
                    </tr>
                </tbody>
            </table>	
        </div>
        <p class="gf_sq_ptit mgt20">MySql等级配额</p>
		<div class="mgt35">
         	<table class="gf_sq_table">
                <colgroup>
                    <col width="89">
                    <col width="180">
                    <col width="84">
                    <col width="84">
                    <col width="86">
                    <col width="83">
                    <col width="110">
                </colgroup>
                <thead>
                    <tr>
                        <th>配额大类</th>
                        <th>名称</th>
                        <th>普通型</th>
                        <th>经济型</th>
                        <th>标准型</th>
                        <th>高级型</th>
                        <th>合作伙伴</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>应用</td>
                        <td>应用数量</td>
                        <td>10个</td>
                        <td>15个</td>
                        <td>30个</td>
                        <td>50个</td>
                        <td>100个</td>
                    </tr>
                    <tr>
                        <td>MySQl</td>
                        <td>单应用数量</td>
                        <td>5GB</td>
                        <td>20GB</td>
                        <td>100GB</td>
                        <td>1TB</td>
                        <td>无限制</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>并发连接数</td>
                        <td>20个</td>
                        <td>50个</td>
                        <td>100个</td>
                        <td>200个</td>
                        <td>无限制</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>慢查询/分钟</td>
                        <td>10次</td>
                        <td>20次</td>
                        <td>40次</td>
                        <td>80次</td>
                        <td>100次</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>慢查询扫描行之和/分钟</td>
                        <td>1000000</td>
                        <td>2000000</td>
                        <td>4000000</td>
                        <td>8000000</td>
                        <td>10000000</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>慢查询累计执行时间/分钟</td>
                        <td>60秒</td>
                        <td>100秒</td>
                        <td>150秒</td>
                        <td>250秒</td>
                        <td>300秒</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>请求/分钟</td>
                        <td>200000次</td>
                        <td>250000次</td>
                        <td>300000次</td>
                        <td>400000次</td>
                        <td>600000次</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>MySQl执行总时间/分钟</td>
                        <td>1200秒</td>
                        <td>1400秒</td>
                        <td>1600秒</td>
                        <td>2000秒</td>
                        <td>2000秒</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>流入流量/分钟</td>
                        <td>1GB</td>
                        <td>1.4GB</td>
                        <td>1.5GB</td>
                        <td>1.7GB</td>
                        <td>2GB</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>流入流量/分钟</td>
                        <td>1GB</td>
                        <td>1.4GB</td>
                        <td>1.5GB</td>
                        <td>1.7GB</td>
                        <td>2GB</td>
                    </tr>
                </tbody>
            </table>	
        </div>
    </div>    
</div>
</body>
</html>
