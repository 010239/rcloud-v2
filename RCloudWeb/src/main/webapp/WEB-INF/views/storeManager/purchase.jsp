<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>应用购买</title>
<%@ include file="../../../static/include/default.jsp" %>
<script type="text/javascript" src="${ctx}/static/extends/js/common/common.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
	var currID = "store";
</script>
</head>
<body>
<%@ include file="../include/top.jsp" %>
<%@ include file="../include/user_top.jsp" %>
<dl class="gf_ci_order gf_ci_pw gf_ci_mt24 gf_ci_bg gf_ci_mng">
    <dt>
        <span class="left">云应用订单</span>
    </dt>
    <dd class="order_dd">
    	<form id="purchase-app-form" action="${ctx}/page/application/buy" method="post">
	    	<div class="import">
	            <div class="w_50">
	                <span>应用名称：</span>
	                <em>${application.chname }</em>
	            </div>
	            <div class="w_50">
	                <span>服务费：</span>
	                <em>${application.maintenanceCosts } 元/月</em>
	            </div>
	        </div>
	        <div class="import">
	            <div class="w_50">
	                <span>付费类型：</span>
	                <em>
	                	<select class="order_sel" name="payPattern">
	                    	<option value='0'>预付费</option>
	                        <option value="1">后付费</option>
	                    </select>
	                </em>
	            </div>
	            <div class="w_50">
	                <span>购买期限：</span>
	                <em>
	                	<select class="order_sel" name="dueTime">
	                    	<option value='12'>1年</option>
	                        <option value='24'>2年</option>
	                        <option value='36'>3年</option>
	                    </select>
	                </em>
	            </div>
	        </div>
	        <div class="import">
	            <span>订单说明：</span>
	            <em><textarea class="order_txt" name="orderDescription"></textarea></em>
	        </div>
	        <input type="hidden" name="applicationId" value="${application.applicationId }">
	        
	      </form>  
        	
          <p class="refer"><input type="button" value="确认并购买" class="btn1" id="buy-button"></p>
        

    </dd>
</dl>
<%@ include file="../../../static/include/raeDeployment.jsp" %>

<%@ include file="../../../static/include/buttom.jsp" %>
<script src="${ctx }/static/extends/js/purchase.js"></script>
<%-- <script src="${ctx }/static/extends/js/deploymentApp.js"></script> --%>
</body>
</html>


<%--smh 试用应用 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ include file="../../../static/include/default.jsp" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>应用购买</title>
<script type="text/javascript">
	var ctx = "${ctx}";
	var currID = "store";
</script>
</head>
<body>
<%@ include file="../include/top.jsp" %>
<%@ include file="../include/user_top.jsp" %>
<dl class="gf_ci_order gf_ci_pw gf_ci_mt24 gf_ci_bg gf_ci_mng">
    <dt>
        <span class="left">云应用订单</span>
    </dt>
    <dd class="order_dd">
    	<form id="purchase-app-form" action="${ctx}/page/application/buy" method="post">
	    	<div class="import">
	            <div class="w_50">
	                <span>应用名称：</span>
	                <em>${application.chname }</em>
	                 <input type="text" name="applicationName" value="${application.applicationName }"/>
	                 <input type="text" name="subDomain" value="${application.subDomain}"/>
	            </div>
	            <div class="w_50">
	                <span>服务费：</span>
	                <em>${application.maintenanceCosts } 元/月</em>
	            </div>
	        </div>
	        <div class="import">
	            <div class="w_50">
	                <span>付费类型：</span>
	                <em>
	                	<select class="order_sel" name="payPattern">
	                    	<option value='0'>预付费</option>
	                        <option value="1">后付费</option>
	                    </select>
	                </em>
	            </div>
	            <div class="w_50">
	                <span>购买期限：</span>
	                <em>
	                	<select class="order_sel" name="dueTime">
	                    	<option value='12'>1年</option>
	                        <option value='24'>2年</option>
	                        <option value='36'>3年</option>
	                    </select>
	                </em>
	            </div>
	        </div>
	        <div class="import">
	            <span>订单说明：</span>
	            <em><textarea class="order_txt" name="orderDescription"></textarea></em>
	        </div>
	        <input type="hidden" name="applicationId" value="${application.applicationId }">
	        
	      </form>  
        	
          <p class="refer"><input type="button" value="确认并购买" class="btn1" id="buy-button"></p>
        

    </dd>
</dl>
<%@ include file="../../../static/include/buttom.jsp" %>
<script src="${ctx }/static/extends/js/purchase.js"></script>
</body>
</html> --%>