<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ include file="../../../static/include/default.jsp" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<script type="text/javascript" src="${ctx}/static/extends/js/dateUtils.js"></script>
<title>个人中心</title>
<script type="text/javascript">
	var ctx = "${ctx}";
</script>
<style>
body{TEXT-ALIGN: center;}
#center{ 
	MARGIN-RIGHT: auto;
	MARGIN-LEFT: auto;
	vertical-align:middle;
	line-height:50px;
}
.gf_ci_pw{
	width:100%;
	padding:0 20px;
} 
.app_buy_order_info,.app_sale_order_info, .ser_order_info {
		display: block !important;
		background: #fff;
		font-size: 14px;
	}
.app_buy_order_info dl, .app_sale_order_info dl, .ser_order_info dl {
		border:0px;
	}
.gf_ci_bg{border:solid 1px #ccc;padding-bottom:10px;}
</style>
</head>
<body>
<div style="margin-top:5px;margin-bottom:5px;margin-right:5px;" align="right">
	交易类型：
	<select name="transactionType" id="transactionType">
		<option value="">所有类型</option>
		<option value="1">充值</option>
		<option value="2">应用订单支付</option>
		<option value="3">服务订单支付</option>
		<option value="4">应用账单扣款</option>
		<option value="5">服务账单扣款</option>
		<option value="6">应用账单收入</option>
		<option value="7">账户提现</option>
		<option value="8">欠费账单续费</option>
	</select>
           资金流向：
    <select name="moneyFlow" id="moneyFlow">
		<option value="">所有类型</option>
		<option value="0">流出</option>
		<option value="1">流入</option>
	</select>
          开始日期：
    <input id="beginTime" type="text" class="easyui-datebox" name="beginTime"/>
          结束日期：
    <input id="endTime" type="text" class="easyui-datebox" name="endTime"/>
    <input type="button" class="btn btn-primary" value="查询" onclick="search();"/>
</div>
<div style="height:505px">
    	<div id="publish-application-div" style="height:500px" >
			<table id="publish-application-dg" data-options="fit:true"></table>
        </div>
    </div>
</div>

<!-- 订单详情 -->
<div class="modal fade" id="serAppInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width:900px;">
    <div class="modal-content">
		<div class="ser_order_info gf_ci_pw" >
			<dl class="gf_ci_order gf_ci_pw  gf_ci_bg gf_ci_mng">
		    <dt>
		        <span class="left">详情</span>
		    </dt>
		    <dd class="order_dd">
		    	<div class="import">
		             <div class="w_50">
		                <span style="width:25%;">流水号:</span>
		                <span id ="recordId" style="width:75%;text-align:center;"></span>
		            </div>
		            <div class="w_50">
		                <span style="width:25%;">日期:</span>
		                <span id ="dateShow" style="width:75%;text-align:center;"></span>
		            </div>
		        </div>
		        <div class="import">
		            <div class="w_50">
		                <span style="width:25%;">描述:</span>
		                <span id ="description" style="width:75%;text-align:center;"></span>
		            </div>
		             <div class="w_50">
		                <span style="width:25%;">收入（￥）:</span>
		                <span id ="income" style="width:75%;text-align:center;"></span>
		            </div>
		        </div>
		        <div class="import">
		            <div class="w_50">
		                <span style="width:25%;display:inline-block; ">支出（￥）:</span>
		                <span id ="expend" style="width:75%;text-align:center;"></span>
		            </div>
		            <div class="w_50">
		                <span style="width:25%;"></span>
		                <span  style="width:75%;"></span>
		            </div>
		        </div>
		   		<p class="refer" align="center"><input class="detail-button gf_info_btn" type="button" value="关闭" /></p>
		  </dd>
		</dl>
		</div>
    </div>
  </div>
</div>

</body>
<script src="${ctx}/static/extends/js/individual_center.js"></script>
<script src="${ctx}/static/extends/js/transaction_record_list.js"></script>
</html>