<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>账单管理</title>
<%@ include file="../../../static/include/default.jsp" %>
<link type="text/css" href="${ctx}/static/extends/css/error_hint.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${ctx}/static/lib/js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx}/static/lib/js/jquery.validationEngine.min.js"></script>
<script type="text/javascript"
	src="${ctx}/static/lib/js/jquery.placeholder.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}"; 
</script>
<style>
.gf_ci_pw{
	width:100%;
	padding:0 20px;
} 
.app_bill_info, .ser_bill_info, .debts_bill_info {
		display: block !important;
		background: #fff;
		font-size: 14px;
	}
.app_bill_info dl, .ser_bill_info dl, .debts_bill_info dl {
		border:0px;
	}
.gf_ci_bg{border:solid 1px #ccc;padding-bottom:10px;}
.import span {
    float: left;
    height: 32px;
    padding-right: 10px;
    text-align: right;
    width: 360px;
}
.app_buy_order_info,.app_sale_order_info, .ser_order_info {
		display: block !important;
		background: #fff;
		font-size: 14px;
	}
.app_buy_order_info dl, .app_sale_order_info dl, .ser_order_info dl {
		border:0px;
	}

</style>
</head>
<body  >

<div class="gf_ci_ifr gf_ci_pw" style="display:block;">
<div style="margin-top:5px;margin-bottom:5px;margin-right:5px;" align="right" class="bill">
	<input type="radio" name="bill" value="1" onclick=change() checked id="sType" >云服务
	<input type="radio" name="bill" value="2" onclick=change()  id="aType" >云应用
	&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;
	<span id="b-t">账单类型：</span>
	<select name="billType" id="type">
		<option value="1" selected>月账单</option>
		<option value="2">季度账单</option>
		<option value="3">年账单</option>
	</select>
	&nbsp;&nbsp;&nbsp;
	开始日期：
	<input class="easyui-datebox"  id="begindate" 
		data-options="formatter:myformatter,parser:myparser"/>
	结束日期：
	<input class="easyui-datebox"  id="enddate" 
		data-options="formatter:myformatter,parser:myparser"/>
	<button class="btn btn-primary" data-toggle="button" onclick="search_application()" id="searchApp" style="display:none">账单查询</button>
	<button class="btn btn-primary" data-toggle="button" onclick="search_service()"  
			id="searchService" >账单查询</button>
	<button class="btn btn-primary" data-toggle="button" onclick="search_app_debts()" style="color:#FF7256" id="appDebts">欠费查询</button>
	<button class="btn btn-primary" data-toggle="button" onclick="search_ser_debts()" style="color:#FF7256" id="serDebts">欠费查询</button>
</div>
<div style="height:505px;">
		<div id="app" style="height:500px"><table id="application_bill_list"  data-options="fit:true"></table></div>
		<div id="service" style="height:500px"><table id="service_bill_list"  data-options="fit:true"></table></div>
		<div id="adebts" style="height:500px"><table id="app_debts_bill_list"  data-options="fit:true"></table></div>
		<div id="sdebts" style="height:500px"><table id="ser_debts_bill_list"  data-options="fit:true"></table></div>
</div>
</div>
<!-- 售出应用详情 -->
<div class="modal fade" id="appBillInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width:900px;">
    <div class="modal-content">
		<div class="app_bill_info gf_ci_pw" >
		<dl class="gf_ci_order gf_ci_pw gf_ci_mt24 gf_ci_bg gf_ci_mng">
		    <dt class="cf">
		        <span class="left">云应用账单详情</span>
		    </dt>
		    <dd>
		        <dl class="gf_cd_dddt cf">
		            <dd class="gf_ci_sorder" style="height:310px;">
		            	<ul class="cf gf_bill_detail">
		                	<li class="cf left">
		                    	<h3>账单号：</h3>
		                        <div><span id ="app-billNumber"></span></div>
		                    </li>
		                    <li class="cf left">
		                    	<h3>涉及服务：</h3>
		                        <div><span id ="app-applicationNames"></span></div>
		                    </li>
		                    <li class="cf left">
		                    	<h3>账单日期：</h3>
		                        <div><span id ="app-createdTime"></span></div>
		                    </li>
		                    <li class="cf left">
		                    	<h3>账单费用(人民币)：</h3>
		                        <div><span id ="app-billCosts"></span></div>
		                    </li>
		                    <li class="cf left">
		                    	<h3>账单周期起：</h3>
		                        <div><span id ="app-startTime"></span></div>
		                    </li>
		                    <li class="cf left">
		                    	<h3>账单周期止：</h3>
		                        <div><span id ="app-endTime"></span></div>
		                    </li>
		                </ul>
		                <h2 class="gf_bill_service">服务费用明细列表</h2>
		            </dd>
					<!--<a class="gf_pub_newapp gf_bill_down" href="javascript:;">下载</a>-->   
		        </dl>
		        <p>
					<table class="table" id="app_bill_detil">
					</table>
		        </p>
		         <p class="refer" align="center"><input class="close-button gf_info_btn" type="button" value="关闭" /></p>
		    </dd>
		</dl>
		</div>
    </div>
  </div>
</div>
<!-- 云服务账单详情 -->
<div class="modal fade" id="serBillInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width:900px;">
    <div class="modal-content">
		<div class="ser_order_info gf_ci_pw" >
		<dl class="gf_ci_order gf_ci_pw  gf_ci_bg gf_ci_mng">
		    <dt >
		        <span class="left">云服务账单详情</span>
		    </dt>
		      <dd class="order_dd">
		    	<div class="import">
		              <div class="w_50">
		                <span style="display:inline-block; width:50%; float:left;">账单日期：</span>
		               <span id ="ser-accountStart" style="display:inline-block;width:40%;"></span>
		            </div>
		          <div class="w_50">
		                <span style="display:inline-block; width:50%; float:left;">总费用(￥)：</span>
		                <span id ="ser-totalcharge" style="display:inline-block; width:40%;"></span>
		            </div>
		        </div>
		        
		       <span class="left">所购产品及其费用明细</span><hr>
		
		        <div class="import" id="ser_bill_detil"> </div>
		      
		         <p class="refer" align="center"><input class="close-button gf_info_btn" type="button" value="关闭" /></p>   
		    </dd>
		</dl>
	
    </div>
  </div>
</div>
</div>
<!-- 云服务欠费详情 -->
<div class="modal fade" id="debtsBillInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width:900px;">
    <div class="modal-content">
		<div class="debts_bill_info  gf_ci_pw" >
		<dl class="gf_ci_order gf_ci_pw gf_ci_mt24 gf_ci_bg gf_ci_mng">
		    <dt class="cf">
		        <span class="left">欠费详情</span>
		    </dt>
		     <dd class="order_dd">
		    	<div class="import">
		            <div class="w_50">
		                <span style="display:inline-block; width:50%; float:left;">账单金额（￥）：</span>
		                <span id ="de-totalCharge" style="display:inline-block;width:40%;"></span>
		            </div>
		            <div class="w_50">
		                <span style="display:inline-block; width:50%; float:left;">欠费金额（￥）：</span>
		                <span id ="de-debtsCosts" style="display:inline-block;width:40%;"></span>
		            </div>
		        </div>
		        <div class="import">
		            <div class="w_50">
		                <span style="display:inline-block; width:50%; float:left;">欠费日期：</span>
		                <span id ="de-createdTime" style="display:inline-block;width:40%;"></span>
		            </div>
		            <div class="w_50">
		                <span style="display:inline-block; width:50%; float:left;">账单日期：</span>
		                <span id ="de-createDate" style="display:inline-block;width:40%;"></span>
		            </div>
		        </div>
		        <div class="import">
		            <div class="w_50">
		                <span style="display:inline-block; width:50%; float:left;">欠费状态：</span>
		                <span id ="de-status" style="display:inline-block;width:40%;"></span>
		            </div>
		            <div class="w_50">
		                <span style="display:inline-block; width:50%; float:left;">补交日期：</span>
		                <span id ="de-paymentDate" style="display:inline-block;width:40%;"></span>
		            </div>
		        </div>
		          <h2 class="gf_bill_service">费用明细列表</h2>
		
		 <!--   <dd>
		        <dl class="gf_cd_dddt cf">
		        
		            <dd class="gf_ci_sorder">
		            	<ul class="cf gf_bill_detail">
		                	<li class="cf left">
		                    	<h3>账单金额（￥）：</h3>
		                        <div><span id ="de-totalCharge"></span></div>
		                    </li>
		                    <li class="cf left">
		                    	<h3>欠费金额（￥）：</h3>
		                        <div><span id ="de-debtsCosts"></span></div>
		                    </li>
		                    <li class="cf left">
		                    	<h3>欠费日期：</h3>
		                        <div><span id ="de-createdTime"></span></div>
		                    </li>
		                    <li class="cf left">
		                    	<h3>账单日期：</h3>
		                        <div><span id ="de-createDate"></span></div>
		                    </li>
		                    <li class="cf left">
		                    	<h3>欠费状态：</h3>
		                        <div><span id ="de-status"></span></div>
		                    </li>
		                    <li class="cf left">
		                    	<h3>补交日期：</h3>
		                        <div><span id ="de-paymentDate"></span></div>
		                    </li>
		                </ul>
		                  <h2 class="gf_bill_service">费用明细列表</h2>
		            </dd>
		        </dl>  -->
		         <p>
					<table class="table" id="ser_debts_detil">
					</table>
		        </p>
		         <p class="refer" align="center"><input class="close-button gf_info_btn" type="button" value="关闭" /></p>   
		    </dd>
		</dl>
		</div>
    </div>
  </div>
</div>
<!-- 云服务  续费页面 -->
<div class="modal fade" id="xufeiModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width:900px;">
    <div class="modal-content">
		<div class="debts_bill_info  gf_ci_pw" >
		<dl class="gf_ci_order gf_ci_pw gf_ci_mt24 gf_ci_bg gf_ci_mng">
		    <dt class="cf">
		        <span class="left">云服务续费</span>
		    </dt>
		     <dd class="order_dd">
		     <!-- onsubmit="formrenew('${ctx}/rest/userbill/renewMoney')" -->
		    		<form  id="reachargebillform" name="reachargebillform" method="post" action="">
	
				 <input type="hidden" id="userId" name="userId" />
				 <input type="hidden" id="chargeMonth" name="chargeMonth" />
				 <input type="hidden" id="chargeYear" name="chargeYear"/>
				 <input type="hidden" id="serviceBillId" name="serviceBillId"/>
				 <input type="hidden" id="bId" name="bId"/>
				 <input type="hidden" id="debtsCosts"/>
				  <div class="import">
		            <div style="width:100%;">
		                <span>用户：</span>
		                <span id ="de-status" style="width:180px;">${user_info.userName }</span>
		            </div>
		        </div>
		          <div class="import">
		            <div style="width:100%;">
		                <span>欠费金额（￥）：</span>
		                <span style="width:180px;" id="bill-debtsCosts"/>元
		            </div>
		        </div>
		         <div class="import">
		            <div  style="width:100%;">
		                <span>续费金额：</span>
		               <span style="width:180px;"><input id="chargingAmount" name="debtsCosts" class="easyui-numberbox textbox boss-input gf_pay_mount_ipt" style="width:132px"
                        	data-options="required:true,min:0,precision:0,novalidate:true" >&nbsp;元</span>
                        	<span id="msg2" style="width:210px;" ></span>
		            </div>
		        </div>
          
				<!-- <div class="boss-form-line form-last-line">
					<span><a id="submit-button" href="javascript:void(0);" class="close-button gf_info_btn" style="height: 30px; " >提交</a></span>
				</div> -->
			 <p  align="center"> <span class="refer"><input class="gf_info_btn" type="submit"  value="提交" /> </span>
			 <span class="refer"> <input class="close-button gf_info_btn" type="button" value="关闭" />  </span>
			 
			 </p> 
			</form>
		
		          
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
<script src="${ctx}/static/extends/js/userBill.js"></script>
</html>