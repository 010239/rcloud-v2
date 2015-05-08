<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/lib/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/lib/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/extends/css/style.css">
<script type="text/javascript" src="${ctx}/static/lib/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/lib/js/bootstrap.min.js"></script>
<style>
.order_dd{ padding:20px;}
.import{padding-bottom:16px; overflow:hidden;}
.w_50{ float:left;width:50%;}
.import span,.import em{line-height:30px;color:#696969;font-size:14px;}
.import span{float:left;height:32px;width:180px;text-align:right;padding-right:10px;}
.import em{min-height:32px;margin-left:180px; display:block; word-break:break-all; word-wrap:break-word;}
.refer{padding-top:20px; text-align:center;}
.btn1{width:150px;height:34px;line-height:34px;text-align:center;color:#fff;background:#02cf0c;border:1px solid #02cf0c;border-radius:2px;font-size:14px;}
</style>
</head>
<body>
<div class="zl_list1">
		
		<dl class="gf_ci_order gf_ci_pw gf_ci_mt24 gf_ci_bg gf_ci_mng">
        <div style="float:right;margin-top:10px;height: 50px;"><input class="btn1" type="button" value="返回" onclick="window.history.back()" />&nbsp;&nbsp;</div>
    <dt>
        <span class="left">套餐详情</span>
    </dt>
    <dd class="order_dd">
    	<div class="import">
            <div class="w_50">
                <span>服务名称：</span>
                <em>${entity.description}</em>
            </div>
            <div class="w_50">
                <span>套餐类型：</span>
                <em>${entity.chname}</em>
            </div>
        </div>
        <div class="import">
            <div class="w_50">
                <span>路由数：</span>
                <em></em>
            </div>
            <div class="w_50">
                <span>内存：</span>
                <em>
                </em>
            </div>
        </div>
        <div class="import">
            <div class="w_50">
                <span>硬盘：</span>
                <em>
                </em>
            </div>
            <div class="w_50">
                <span>单价：</span>
                <em>${entity.price}</em>
            </div>
        </div>


    </dd>
</dl>
		
		
		
		
		
		
		
		
		
		
		
		
		
</div>


</body>
</html>









