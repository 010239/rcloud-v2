<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>订单管理</title>
<%@ include file="../../../static/include/default.jsp" %>
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
<div style="margin-top:5px;margin-bottom:5px;margin-right:5px;" align="right" class="order">
			<input type="radio" name="order" value="云服务订单" onclick=change() checked>云服务
			<input type="radio" name="order" value="云应用订单" onclick=change() >云应用		
			&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;
			&nbsp;
			订购状态：
			<select name="orderStatus" id="appStatus">
				<option value="1" selected>服务中</option>
				<option value="2">暂停</option>
				<option value="3">结束</option>
				<option value="4">撤销</option>
				<option value="">所有状态</option>
			</select>
			<select name="status" id="serStatus"  style="display:none">
				<option value="0">暂停</option>
				<option value="1" >有效</option>
				<option value="2">结束</option>
				<option value="3">撤销</option>
				<option value="" selected>所有状态</option>
			</select>
			订购类型：
			<select name="orderType" id="applicationOrder">
				<option value="1" selected>售出应用</option>
				<option value="2">购买应用</option>
			</select>
			<select name="payPattern" id="serviceOrder"  style="display:none">
				<option value="0" >固定费率</option>
				<option value="1">非固定费率</option>
				<option value="" selected>所有类型</option>
			</select>
			开始日期：
			<input class="easyui-datebox"  id="begindate" 
			data-options="formatter:myformatter,parser:myparser"/>
			结束日期：
			<input class="easyui-datebox"  id="enddate" 
			data-options="formatter:myformatter,parser:myparser"/>
			<button class="btn btn-primary" data-toggle="button" onclick="search_application()" id="searchApp">查询</button>
			<button class="btn btn-primary" data-toggle="button" onclick="search_service()"  
				id="searchService" style="display:none">查询</button>
</div>
<div style="height:505px">
		<div id="app_sale" style="height:500px"><table id="application_sale_order_list"  data-options="fit:true"></table></div>
		<div id="app_buy" style="height:500px"><table id="application_buy_order_list"  data-options="fit:true"></table></div>
		<div id="service" style="height:500px"><table id="service_order_list"  data-options="fit:true"></table></div>
</div>


<!-- 购买应用详情 -->
<div class="modal fade" id="buyAppInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width:900px;">
    <div class="modal-content">
		<div class="app_buy_order_info  gf_ci_pw" >
			<dl class="gf_ci_app gf_ci_pw  gf_ci_bg gf_ci_mng">
		    <dt>
		        <span class="left">云应用订购详情-购买应用</span>
		    </dt>
		    <dd class="order_dd">
		    	<div class="import">
		            <div class="w_50">
		                <span>订单号：</span>
		                <span id ="app-orderNumber"></span>
		            </div>
		            <div class="w_50">
		                <span>云应用：</span>
		                <span id ="app-applicationName"></span>
		            </div>
		        </div>
		        <div class="import">
		            <div class="w_50">
		                <span>卖方：</span>
		                <span id ="app-userName"></span>
		            </div>
		            <div class="w_50">
		                <span>状态：</span>
		                <span id ="app-orderStatus"></span>
		            </div>
		        </div>
		        <div class="import">
		            <div class="w_50">
		                <span>运维费：</span>
		                <span id ="app-maintenanceCosts"></span>
		            </div>
		            <div class="w_50">
		                <span>截止日期：</span>
		                <span id ="app-endTime"></span>
		            </div>
		        </div>
		        <div class="import">
		            <div class="w_50">
		                <span>签单日期：</span>
		                <span id ="app-createdTime"></span>
		            </div>
		        </div>      
		        <div class="import">
		            <span>订单说明：</span>
		            <span id ="app-orderdescription"></span>
		        </div> 
		         <p class="refer" align="center"><input class="detail-button gf_info_btn" type="button" value="关闭" /></p>   
		    </dd>
		</dl>
		</div>
    </div>
  </div>
</div>
<!-- 售出应用详情 -->
<div class="modal fade" id="saleAppInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width:900px;">
    <div class="modal-content">
		<div class="app_sale_order_info gf_ci_pw" >
			<dl class="gf_ci_app gf_ci_pw  gf_ci_bg gf_ci_mng">
		    <dt>
		        <span class="left">云应用订购-售出应用</span>
		    </dt>
		    <dd >
		    	<div class="import">
		            <div class="w_50">
		                <span>订单号：</span>
		                <span id ="ap-orderNumber"></span>
		            </div>
		            <div class="w_50">
		                <span>云应用：</span>
		                <span id ="ap-applicationName"></span>
		            </div>
		        </div>
		        <div class="import">
		            <div class="w_50">
		                <span>买方：</span>
		                <span id ="ap-userName"></span>
		            </div>
		            <div class="w_50">
		                <span>状态：</span>
		                <span id ="ap-orderStatus"></span>
		            </div>
		        </div>
		        <div class="import">
		            <div class="w_50">
		                <span>运维费：</span>
		                <span id ="ap-maintenanceCosts"></span>
		            </div>
		            <div class="w_50">
		                <span>截止日期：</span>
		                <span id ="ap-endTime"></span>
		            </div>
		        </div>
		        <div class="import">
		            <div class="w_50">
		                <span>签单日期：</span>
		                <span id ="ap-createdTime"></span>
		            </div>
		        </div>      
		        <div class="import">
		            <span>订单说明：</span>
		            <span id ="ap-orderdescription"></span>
		        </div> 
		        <p class="refer" align="center"><input class="detail-button gf_info_btn" type="button" value="关闭" /></p>   
		    </dd>
		</dl>
		</div>
    </div>
   </div>
</div>
<!-- 服务详情 -->
<div class="modal fade" id="serAppInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width:900px;">
    <div class="modal-content">
		<div class="ser_order_info gf_ci_pw" >
			<dl class="gf_ci_order gf_ci_pw  gf_ci_bg gf_ci_mng">
		    <dt>
		        <span class="left">云服务订购详情</span>
		    </dt>
		    <dd class="order_dd">
		    	<div class="import">
		           
		             <div class="w_50">
		                <span>产品名称：</span>
		                <span id ="ser-prodName"></span>
		            </div>
		            <div class="w_50">
		                <span>资费包：</span>
		                <span id ="ser-pkgName"></span>
		            </div>
		        </div>
		        <div class="import">
		            
		            <div class="w_50">
		                <span>订购类型：</span>
		                <span id ="ser-type"></span>
		            </div>
		             <div class="w_50">
		                <span>订购状态：</span>
		                <span id ="ser-status"></span>
		            </div>
		        </div>
		        <div class="import">
		           
		            <div class="w_50">
		                <span>签单日期:</span>
		                <span id ="ser-createTime"></span>
		            </div>
		             <div class="w_50">
		                <span>支付方式:</span>
		                <span id ="ser-payPattern"></span>
		            </div>
		        </div>
		        <div class="import">
		           
		            <div class="w_50">
		                <span>取消日期:</span>
		                <span id ="ser-cancelTime"></span>
		            </div>
		        </div>
		        <div class="import">
		            <span>取消理由:</span>
		            <span id ="ser-cancelReason"></span>
		        </div>
		   
		          <p class="refer" align="center"><input class="detail-button gf_info_btn" type="button" value="关闭" /></p>
		  </dd>
		</dl>
		</div>
    </div>
  </div>
</div>
<div class="gf_ci_ifr gf_ci_pw gf_ci_mt24">
    <iframe class="gf_iframe" width="100%" frameborder="0" name="gf_ici" src="" scrolling="no"></iframe>
</div>
</body>
<script src="${ctx}/static/extends/js/dateUtils.js"></script>
<script src="${ctx}/static/extends/js/individual_center.js"></script>
<script src="${ctx}/static/extends/js/userOrder.js"></script>
</html>