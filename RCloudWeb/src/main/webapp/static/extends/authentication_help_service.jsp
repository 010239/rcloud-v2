<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<script type="text/javascript">
var currID = "jqfw";
var currID_t ="case_5";
</script>
<title>帮助</title>
</head>
<body class="gf_idx">
<%@ include file="../../static/include/top.jsp" %>
<%@ include file="../../static/include/home_top.jsp" %>
<!--content star-->
<div class="gf_wrapper_t cf">
	<%@ include file="../../static/include/help_menu.jsp" %>
    <div class="gf_help_right left">
    	<dl>
        	<dt><span class="gf_help_bps">RCloud RAA产品白皮书</span><a target="_blank" href="download/safe/sso.pdf"></a></dt>
            <dd>
            	<p>云身份认证及应用鉴权平台(下文简称RAA云认证鉴权平台)，是构建云平台的重要支持能力，为集约化管理和集成的云应用提供统一身份、统一认证、SSO、应用鉴权的平台云服务及相关配套管理工具。</p>
                <span><a target="_blank" href="download/safe/sso_shouce.html">查看全文</a></span>
            </dd>
        </dl>
        <dl>
        	<dt><span class="gf_help_kfsc">RCloud RAA用户开发手册</span><a target="_blank" href="download/safe/api.pdf"></a></dt>
            <dd>
            	
            	<p>云认证鉴权及单点登录平台采用标准的OAuth2，因此可以采用开源的OAuth2客户端(请参考OAuth2官方)获取访问令牌后调用获取用户信息的接口(/api/user)来获取当前用户
平台也提供了辅助类封装上面说的过程来简化客户的调用</p>
                <span><a target="_blank" href="download/safe/api_shouce.html">查看全文</a></span>
            </dd>
        </dl>
        <dl>
        	<dt><span class="gf_help_sysc">RCloud RAA用户使用手册</span><a target="_blank" href="download/raa/raa.pdf"></a></dt>
            <dd>
            	<p>云身份认证及应用鉴权平台(下文简称RAA云认证鉴权平台)，是构建云平台的重要支持能力，为集约化管理和集成的云应用提供统一身份、统一认证、SSO、应用鉴权的平台云服务及相关配套管理工具。</p>
                <span><a target="_blank" href="download/raa/raa.htm">查看全文</a></span>
            </dd>
        </dl>
    </div>
</div>
<!--content end-->
<div class="gf_footer">
<%@ include file="../../static/include/home_buttom.jsp" %>
<%@ include file="../../static/include/buttom.jsp" %>
</div>
<script src="js/common.js"></script>
</body>
</html>
