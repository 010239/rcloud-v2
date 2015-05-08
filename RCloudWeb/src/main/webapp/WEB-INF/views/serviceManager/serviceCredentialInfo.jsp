<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../../../static/include/default.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Grid Template for Bootstrap</title>
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
	
	<dl class="gf_ci_order gf_ci_pw gf_ci_mt24 gf_ci_bg gf_ci_mng">
    <dt>
        <span class="left">云服务凭证详情</span>
        <div style="float:right;margin-top:4px;height: 50px;"><input class="btn1" type="button" value="返回" onclick="showCredentialList()" />&nbsp;&nbsp;</div>
    </dt>
    <dd class="order_dd">
    	<div class="import">
            <div class="w_50">
                <span>access-key-id：</span>
                <em>${keyId}</em>
            </div>
        </div>
        <div class="import">
                <span>access-key-secret：</span>
                <em>${keySecret}</em>
            </div>
        </div>

    </dd>
</dl>
	
</body>

<script type="text/javascript">
    function showCredentialList() {
    	location.href= rcloudweb + "page/cloudservice/?showList=credential"
    }

</script>
</html>
