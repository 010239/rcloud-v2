<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ include file="../../../static/include/default.jsp" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>个人中心</title>
<script type="text/javascript">
	var ctx = "${ctx}";
</script>
<style type="text/css">
	.dg-button {
		margin-right: 10px;
	}
	
	.app_mod_info, .update_app_mod_info {
		position: fixed;
		background: #fff;
		font-size: 14px;
		padding-bottom: 30px;
		display: none;
	}
	
	.gf_yyfb_tarea {
		margin-top: 20px;
	}
	
	.app_input {
		height: 30px;
		line-height: 30px\9;
		width: 298px;
		border: 1px solid #7b8793;
		border-radius: 4px;
		background: none;
		padding: 0 4px;
	}
</style>
</head>
<body>
<%@ include file="../include/top.jsp" %>
<%@ include file="../include/user_top.jsp" %>
<dl class="gf_ci_order gf_ci_pw gf_ci_mt24 gf_ci_bg gf_ci_mng">
    <dt class="cf js_ci_dt">
        <span class="left">订单管理</span>
        <a class="gf_pub_newapp" href="javascript:;">发布新应用</a>
        <i class="gf_sprite right on"></i>
    </dt>
    <dd>
        <dl class="gf_cd_dddt cf">
            <dt class="cf">
                <span id="publish-list-button" class="first left">已发布应用</span>
                <span id="buy-list-button" class="left off">已经购买应用</span>
            </dt>
         </dl>
    <dd>
 </dl> 
 <div id="publish-application-div">
 	<table id="publish-application-dg"></table>
 </div>
 <div id="buy-application-div">
 	<table id="buy-application-dg"></table>
 </div>

<!-- 弹层 -->
<div class="gf_ci_mask"></div>
<!-- 应用详情 -->
<form class="app_mod_info gf_ci_pw" action="">
    <dl>
        <dt class="gf_info_dt1">应用详情</dt>
        <dd class="gf_info_dd1">
            <dl>
                <dd>
                    <p>
                        <span>应用名称：</span>
                        <span id="app-name"></span>
                    </p>
                    <p>
                        <span>应用描述：</span>
                        <span id="app-description"></span>
                    </p>
                    <p>
                        <span>试用地址：</span>
                        <span id="app-address"></span>
                    </p>
                    <p>
                        <span>服务费：</span>
                        <span id="app-maintenance"></span>
                    </p>
                    <p>
                        <span>联系人：</span>
                        <span id="app-concat-person"></span>
                    </p>
                    <p>
                        <span>联系电话：</span>
                        <span id="app-phone"></span>
                    </p>
                    <p class="gf_ib_wrap"><input id="app-detail-button" class="gf_info_btn" type="button" value="关闭" /></p>
                </dd>
            </dl>
        </dd>
        
    </dl>
</form>
<!-- 修改应用 -->
<form id="update_app_form" class="update_app_mod_info gf_ci_pw" action="">
    <dl>
        <dt class="gf_info_dt1">应用详情</dt>
        <dd class="gf_info_dd1">
            <dl>
                <dd>
                    <p>
                        <span>应用名称：</span>
                        <input type="text" name="applicationName" class="app_input" id="applicationName"/>
                        <input type="hidden" name="applicationId" id="applicationId"/>
                    </p>
                    <p>
                        <span>应用简述：</span>
                        <textarea class="gf_yyfb_tarea" name="briefDescription" id="briefDescription"></textarea>
                    </p>
                    <p>
                        <span>应用描述：</span>
                        <textarea class="gf_yyfb_tarea" name="detailDescription" id="detailDescription"></textarea>
                    </p>
                    <p>
                        <span>试用地址：</span>
                        <input type="text" name="subDomain" class="app_input" id="subDomain"/>
                    </p>
                    <p>
                        <span>服务费：</span>
                        <input type="text" name="maintenanceCosts" class="app_input" id="maintenanceCosts"/>
                    </p>
                    <p>
                        <span>联系人：</span>
                        <input type="text" name="contactPerson" class="app_input" id="contactPerson"/>
                    </p>
                    <p>
                        <span>联系电话：</span>
                        <input type="text" name="contactPhone" class="app_input" id="contactPhone"/>
                    </p>
                    <p class="gf_ib_wrap"><input id="app-update-button" class="gf_info_btn" type="button" value="提交" /></p>
                </dd>
            </dl>
        </dd>
        
    </dl>
</form>
<!-- 弹层 -->
</body>
<script src="${ctx}/static/extends/js/individual_center.js"></script>
<script src="${ctx}/static/extends/js/wind_shop.js"></script>
</html>