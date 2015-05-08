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
    <dt>
        <span class="left">云服务订单详情</span>
        <div style="float:right;margin-top:4px;height: 50px;"><input class="btn1" type="button" value="返回" onclick="window.history.back()" />&nbsp;&nbsp;</div>
    </dt>
    <dd class="order_dd">
    	<div class="import">
            <div class="w_50">
                <span>订单号：</span>
                <em>${order.orderNum}</em>
            </div>
            <div class="w_50">
                <span>云服务：</span>
                <em>${order.description}</em>
            </div>
        </div>
        <div class="import">
            <div class="w_50">
                <span>服务套餐：</span>
                <em>${order.chname}</em>
            </div>
            <div class="w_50">
                <span>类型：</span>
                <em>
                <c:if test="${order.type==1}">一次付清</c:if>
                <c:if test="${order.type==2}">按月支付</c:if>
                </em>
            </div>
        </div>
        <div class="import">
            <div class="w_50">
                <span>状态：</span>
                <em>
                <c:if test="${order.status==0}">暂停</c:if>
                <c:if test="${order.status==1}">有效</c:if>
                <c:if test="${order.status==2}">结束</c:if>
                <c:if test="${order.status==3}">撤销</c:if>
                </em>
            </div>
            <div class="w_50">
                <span>截止日期：</span>
                <em>${order.endTime}</em>
            </div>
        </div>
        <div class="import">
            <div class="w_50">
                <span>单价：</span>
                <em>${order.unitPrice}元</em>
            </div>
            <div class="w_50">
                <span>总费用：</span>
                <em>${order.totalPrice}元</em>
            </div>
        </div>
        <div class="import">
            <span>购买期限：</span>
            <em>${order.deadline}月</em>
        </div>
        <div class="import">
            <span>订单说明：</span>
            <em>${order.des}</em>
        </div>


    </dd>
</dl>
		
		
		
		
		
</div>


</body>
</html>









