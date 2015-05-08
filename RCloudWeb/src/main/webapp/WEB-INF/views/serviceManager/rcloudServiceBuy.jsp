<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../../static/include/default.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>云服务</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/static/lib/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/lib/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/extends/css/style.css">
<script type="text/javascript" src="${ctx}/static/lib/js/jquery-1.11.1.min.js"></script>
<style>
.gf_ci_pw{
	width:980px;margin:0 auto;
} 
.app_buy_order_info {
		display: block !important;
		background: #fff;
		font-size: 14px;
	}
.app_buy_order_info dl {
		border:0px;
	}
.orange{color:#f87727}

.w_50{ width:100%;}
.import span,.import em{line-height:30px;color:#696969;font-size:14px;}
.import span{height:32px;width:360px;text-align:right;padding-right:10px;}
</style>

<!--  <script type="text/javascript">

$(document).ready(function(){
		
	var oSingle_price=$("i.single_price");	
	
	$("#prodId").val($("p.level_t span[class=active]").attr('fwid'));
	$("#pkgId").val($("p.level_t span[class=active]").attr('pkg'));
	
	oSingle_price.html($("p.level_t span[class=active]").attr('price'));
	
	$("dl.gf_chos_buy p span").click(function(){				   
		$(this).addClass('active').siblings('span').removeClass('active');				
		$("#prodId").val($("p.level_t span[class=active]").attr('fwid'));
		$("#pkgId").val($("p.level_t span[class=active]").attr('pkg'));
	 })	;
	
	$("p.level_t span").click(function(){
    	oSingle_price.html($(this).attr('price'));
    });

});
	</script>-->
<script type="text/javascript">
	var currID = "service";
	function Wait(){
		$("#serviceSub").css({'backgroundColor':'#ccc','cursor':'default'}).val('提交中');
	}
</script>
</head>
<body class="gf_ci">
<%@ include file="../include/top.jsp" %>
<%@ include file="../include/user_top.jsp" %>

	<div class="app_buy_order_info  gf_ci_pw" >
			<!--  <dl class="gf_ci_app gf_ci_pw  gf_ci_bg gf_ci_mng">
		    <dt>
		        <span class="left">服务开通</span>
		    </dt>
		    
		    <dd class="order_dd"> 
		    	<br>
		    	<div class="import">
		      		 <div class="w_50">
		                <span>产品名称：</span>
		                 <span style="height:32px;width:360px;text-align:left;padding-left:50px;">${clusivePricelist.prodName }</span>
		            </div>
		      	</div>
		        <div class="import">
		            <div class="w_50">
		                <span >产品描述：</span>
		                 <span style="height:32px;width:360px;text-align:left;padding-left:50px;">${clusivePricelist.description }</span>
		            </div>
		        </div>     
		        <div class="import">
		            <div class="w_50"  >
		                <span>价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格：</span>
		                <span style="height:32px;width:360px;text-align:left;padding-left:50px;">
		                	<i class="orange single_price">${clusivePricelist.remarks }</i><i class="orange">&nbsp;元</i>
		                </span>
		            </div>
		           
		        </div> 
		         -->
		<h3 class="gf_sq_tit">
        	<span class="left">服务开通</span>
        </h3>         
		  <br>
		  <div class="mgt35">
         	<table class="gf_sq_table" style="margin-left:80px;">
                 <colgroup>
                    <col width="180">
                    <col width="600">
                </colgroup>
                <tbody>
                  
                    <tr>
                        <td>产品名称：</td>
                        <td>${clusivePricelist.prodName }</td>
                    </tr>
                   
                     <tr>
                        <td>价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格：</td>
                        <td><i class="orange single_price">${clusivePricelist.remarks }</i><i class="orange">&nbsp;元/月</i></td> 	
                    </tr>
                     <tr>
                        <td>产品描述：</td>
                        <td>${clusivePricelist.description }</td> 	
                    </tr>
                </tbody>
            </table>	
        </div>  
		         
		         <br><br>
		          <form action="${ctx}/page/cloudservicePlatform/rcloudServiceBuy"   method="post">

						<input type="hidden" id="pkgId" name="pkgId" value="${clusivePricelist.pkgId }">
                   		<input type="hidden" id="prodId" name="prodId" value="${clusivePricelist.prodId }" />
                   		<input type="hidden" id="prodId" name="prodName" value="${clusivePricelist.prodName }" />
                   		<input type="hidden" id="prodId" name="serviceId" value="${serviceId }" />      
                       <p class="refer" align="center">
                        <input class="detail-button gf_info_btn" type="submit" onclick="Wait()" value="开通" id="serviceSub" /> 
                   		</p>
                   </form>
                   <br><br>
		          
		</div>

<div class="gf_footer gf_ci_mt24">
    <div class="gf_company_imform">
        <p>版权所有 © 2013-2014 中软国际公司   京ICP备14011895号-1</p>
        <p>电话：+86 010 888482388 转 6326 传真：+86 010 8286 2809 邮箱：rcloud_support@chinasofti.com</p>
    </div>
</div>
</body>
</html>

