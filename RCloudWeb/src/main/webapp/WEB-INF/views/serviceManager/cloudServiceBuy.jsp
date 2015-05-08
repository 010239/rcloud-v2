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
.gf_wrap_t{width:980px;margin:0 auto;}
.gf_title_t{height:30px;line-height:30px;font-size:16px;border-bottom:1px solid #6ac8ba;font-family:"微软雅黑";color:#2c3d39;padding-top:12px;}
.gf_chos_buy{width:100%;padding-top:12px}
.gf_chos_buy dd{width:658px;padding-left:22px;line-height:20px;padding-bottom:10px}
.gf_chos_buy dd h3{color:#666;height:23px;font-size:14px;}
.gf_chos_buy dd span{display:block;color:#a2a2a2}
.gf_chos_buy dd p{padding:4px 0}
.gf_chos_buy dd p span{width:66px;height:23px;border:1px solid #85d1c6;position:relative;display:inline-block;color:#666;text-align:center;line-height:23px;cursor:pointer}
.gf_chos_buy dd p span em{position:absolute;right:0;bottom:0;background:url(${ctx}/static/extends/images/icon/xfbg.png) no-repeat;width:15px;height:17px;display:none;}
.gf_chos_buy dd p span.active em{display:block}
.gf_chos_buy .buy_apay,.osel_t a{display:block;width:134px;height:35px;line-height:35px;text-align:center;color:#fff;font-size:18px;font-family:"微软雅黑";background:none repeat scroll 0% 0% #02CF0C;margin-top:9px}
.gf_table_tl{border-top:1px solid #b2b2b2;border-left:1px solid #b2b2b2;width:806px;text-align:center;margin:15px 0 19px 22px;}
.gf_table_tl th,.gf_table_tl td{height:29px;border-right:1px solid #b2b2b2;border-bottom:1px solid #b2b2b2;text-align:center}
.gf_table_tl thead tr{background:#eaf1f1}
.gf_ci_mng .gf_chos_buy dt{height:0;line-height:0;border:none;padding:0}
.gf_bordb{border-bottom:1px solid #eaeaea}
.orange{color:#f87727}
</style>
<script type="text/javascript">

$(document).ready(function(){
	

	
	var oPrice=$("i.a_price");	
	var oSingle_price=$("i.single_price");	
	var oRide=$("p.month_t span[class=active]").attr('price')*$("p.level_t span[class=active]").attr('price')
	$("#totalPrice").val(oRide);
	$("#serviceInclusivePrice").val($("p.level_t span[class=active]").attr('lb'));
	$("#inclusivePriceId").val($("p.level_t span[class=active]").attr('fwid'));
	$("#servicePrice").val($("p.level_t span[class=active]").attr('price'));
	$("#totalMonth").val($("p.month_t span[class=active]").attr('price'));
	
	oPrice.html(oRide);
	oSingle_price.html($("p.level_t span[class=active]").attr('price'));
	$("dl.gf_chos_buy p span").click(function(){				   
		$(this).addClass('active').siblings('span').removeClass('active');			
		oPrice.html($(this).attr('price')*$(this).parent().siblings('p').find('span[class=active]').attr('price'));
		
		
		$("#totalPrice").val(0);
		$("#serviceInclusivePrice").val($("p.level_t span[class=active]").text());
		$("#inclusivePriceId").val($("p.level_t span[class=active]").attr('fwid'));
		$("#servicePrice").val($("p.level_t span[class=active]").attr('price'));
		$("#totalMonth").val($("p.month_t span[class=active]").attr('price'));
	 })	
	$("p.level_t span").click(function(){
    	oSingle_price.html($(this).attr('price'));
    })
    
    
	var item = $('input[name=payPattern1]:checked').val();   
	
	$("#payPattern").val(item);

  })
	</script>
</head>
<body class="gf_ci">
<%@ include file="../include/top.jsp" %>
<%@ include file="../include/user_top.jsp" %>
<dl class="gf_ci_order gf_ci_pw gf_ci_mt24 gf_ci_bg gf_ci_mng">
    <dt class="cf">
        <span class="left">服务开通</span>
    </dt>
    <dd class="gf_bordb">
        <div class="gf_wrap_t">
            <dl class="gf_chos_buy">
                <dt></dt>
                <dd>
                   <!-- <h3>预审系统</h3> --> 
                   
                    <div style="display:none ">
                    <span>金额：<i class="orange a_price"></i><i class="orange">元</i></span></div>
                    <span>单价：<i class="orange single_price"></i><i class="orange">元</i></span>
                    <div style="display:none ">
                    <span>购买月数：</span>
                    <p class="month_t">
                        <span id="xzyf"  price=6>六个月<em></em></span>
                        <span price=9>九个月<em></em></span>
                        <span price=12>一年<em></em></span>
                        <span price=24>两年<em></em></span>
                        <span id="wqx"  class="active"  price=0>无期限<em></em></span>
                    </p>
                    </div>
                    <span>套餐类型：</span>
                    <p class="level_t">
                    
                    <c:forEach var="list" items="${clusivePricelist }"  varStatus="status">
                        <c:choose> 
						  <c:when test="${status.index==0 }">   
						    <span class="active" price="${list.price }"  lb="${list.description }" fwid="${list.id }">${list.chname }<em></em></span>
						  </c:when> 
						  <c:otherwise>   
						    <span price="${list.price }"  lb="${list.description }" fwid="${list.id }">${list.chname }<em></em></span>
						  </c:otherwise> 
						</c:choose> 
                    </c:forEach>
                        
                    </p>
                    <div style="display:none ">
                    <span>支付类型</span>
                    <p class="month_t">
                    	<label>后付费<input type="radio" name="payPattern1" value="2"  checked="checked" ></label>
                        <label>预付费<input type="radio" name="payPattern1" value="1"></label>
                        
                    </p>
                    </div>
                    <form action="${ctx}/page/cloudservicePlatform/cloudServiceBuy"   method="post">
                    <input type="hidden" id="servicePrice" name="servicePrice" value="">
                     <input type="hidden" id="inclusivePriceId" name="inclusivePriceId" value="">
                    <input type="hidden" id="serviceInclusivePrice" name="serviceInclusivePrice" value="">
                   <input type="hidden" id="totalPrice" name="totalPrice" value="">
                   <input type="hidden" id="totalMonth" name="totalMonth" value="">
                   <input type="hidden" id="payPattern" name="payPattern" value="">
                   <input type="hidden" name="serviceId" value="${serviceId}" />
                   
                        <input class="buy_apay" type="submit" value="开通" /> 
                    </form>
                </dd>
            </dl>
            <table class="gf_table_tl">
                <colgroup>
                    <col width="164"/>
                    <col width="94"/>
                    <col width="214"/>
                    <col width="114"/>
                    <col width="214"/>
                </colgroup>
                <thead>
                    <tr>
                        <th>套餐类型</th>
                        <th>空间</th>
                        <th>MySQL数据库</th>
                        <th>流量/月</th>
                        <th>价格（元/月）</th>
                    </tr>
                </thead>
                <tbody>
                
                    <c:forEach var="list" items="${clusivePricelist }"  varStatus="status">
                    <tr>
                        <td>${list.chname }</td>
                        <td>100M</td>
                        <td>50M</td>
                        <td>5G</td>
                        <td>${list.price }元</td>
                    </tr>
                    </c:forEach>
                    
                </tbody>
            </table>
        </div>
    </dd>
</dl>
<div class="gf_footer gf_ci_mt24">
    <div class="gf_company_imform">
        <p>版权所有 © 2013-2014 中软国际公司   京ICP备14011895号-1</p>
        <p>电话：+86 010 888482388 转 6326 传真：+86 010 8286 2809 邮箱：rcloud_support@chinasofti.com</p>
    </div>
</div>
</body>
</html>

