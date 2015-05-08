<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<%@ include file="../../../static/include/default.jsp" %>
<script type="text/javascript">
var currID = "service";
</script>
<title>云服务</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/lib/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/lib/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/extends/css/style.css">
<script type="text/javascript" src="${ctx}/static/lib/js/jquery-1.11.1.min.js"></script>
<style>
.gf_yfw_listt .gf_img_wrap img{
	width:54px;
}

</style>
</head>
<body>
<%@ include file="../include/top.jsp" %>
<%@ include file="../include/user_top.jsp" %>
<dl class="gf_ci_order gf_ci_pw gf_ci_mt24 gf_ci_bg gf_ci_mng">
    <dt class="cf js_ci_dt">
        <span class="left">已开通</span>
        <i class="gf_sprite right on"></i>
    </dt>
    <dd>
		<ul class="gf_yfw_listt cf">
		<c:forEach var="listOpen" items="${listOpen }">
		<c:if test="${!empty listOpen }">
	       <li class="gf_bdrb_no">
                <div class="gf_nl_top cf">
                    <div class="gf_img_wrap left">
                        <a href="javascript:;">
                            <img src="${ctx}/${listOpen.logoPath }" alt="photo" />
                        </a>
                    </div>
                    <div class="gf_nlt_right left">
                        <p class="gf_l1"><a href="javascript:;">${listOpen.serviceName }-${listOpen.serviceDescription }</a></p>
     					<!-- <p class="gf_date_t"><strong>到期日:</strong><b><fmt:formatDate value="${listOpen.expireDate}" pattern="yyyy-MM-dd"/> </b></p> -->
                    	<p  class="gf_date_t"><strong>状态：</strong><b>
                    		<c:choose>
                    			<c:when test="${listOpen.status == 0 }">
                    				暂停
                    			</c:when>
                    			<c:when test="${listOpen.status == 1 }">
                    				有效
                    			</c:when>
                    		</c:choose>
                    	</b></p>
                    </div>
                </div>
                <div class="gf_nl_bot gf_handlet">
                    <span>|</span>
                    <c:if test="${listOpen.status == 0 }">
                    	<font color="red">该服务已暂停！</font>
                    </c:if>
                    <c:if test="${listOpen.status == 1 }">
                    	<a href="${listOpen.manageUrl }" target="view_window">进入</a>
                    </c:if>
                    <span>|</span>
                </div>
            </li>
        </c:if>
		</c:forEach>
		
            
        </ul>
    </dd>
</dl>
<dl class="gf_ci_order gf_ci_pw gf_ci_mt24 gf_ci_bg gf_ci_mng">
    <dt class="cf js_ci_dt">
        <span class="left">未开通</span>
        <i class="gf_sprite right on"></i>
    </dt>
    <dd>
		<ul class="gf_yfw_listt gf_yfw_wkt cf">
		<c:forEach var="listClose" items="${listClose }">
		<c:if test="${!empty listClose && xs == 1}">
            <li class="gf_bdrb_no">
                <div class="gf_nl_top cf">
                    <div class="gf_img_wrap left">
                        <a href="javascript:;">
                            <img src="${ctx}/${listClose.logoPath }" alt="photo" />
                        </a>
                    </div>
                    <div class="gf_nlt_right left">
                        <p class="gf_l1"><a href="javascript:;">${listClose.serviceName }-${listClose.chname }</a></p>
                        <p class="cf gf_handlet">
                            <a href="${ctx}/page/cloudservicePlatform/cloudServiceBuyForPage/${listClose.serviceId}">开通</a><span>|</span>
                            <a href="${ctx}/${listClose.showDetailUrl }">详情</a>
                        </p>
                    </div>
                </div>
            </li>
         </c:if>
		</c:forEach>
        </ul>
    </dd>
</dl>
<div class="gf_footer gf_ci_mt24">
<%@ include file="../../../static/include/buttom.jsp" %>
</div>
<script src="${ctx}/static/extends/js/individual_center.js"></script>
</body>
</html>

