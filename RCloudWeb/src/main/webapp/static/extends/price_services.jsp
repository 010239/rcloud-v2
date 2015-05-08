<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>资费</title>
</head>
<body class="gf_idx">
<%@ include file="../../static/include/top.jsp" %>
<%@ include file="../../static/include/home_top.jsp" %>
<!--content star-->
<div class="gf_wrapper_t cf">
	<div class="gf_sq_left left">
    	<div class="gf_sq_llist">
        	<dl class="gf_sq_dlone">
            	<dt>云应用引擎</dt>
                <dd class="show"><a class="yyq"  href="rae_price_service.jsp" target="iframepage">RCloud 云应用引擎</a><span></span></dd>
            </dl>
            <dl>
            	<dt>云门户服务</dt>
                <dd><a class="kfpt" href="appdev_price_service.jsp" target="iframepage">RCloud 云门户服务</a><span></span></dd>
            </dl>
            <dl>
            	<dt>云业务流程服务</dt>
                <dd><a class="ywlc"  href="bpm_price_service.jsp" target="iframepage">RCloud 云业务流程服务</a><span></span></dd>
            </dl>
            <dl>
            	<dt>云消息队列服务</dt>
                <!-- <dd><a class="sjfw1" href="mysql_price_service.jsp" target="iframepage">关系数据库服务</a><span></span></dd> --> 
                <dd><a class="sjfw2" href="redis_price_service.jsp" target="iframepage">RCloud 云消息队列服务</a><span></span></dd>
            </dl>
            <!-- <dl>
            	<dt>云数据交换服务</dt>
                <dd><a class="sjjh" href="ei_price_service.jsp" target="iframepage">RCloud 云数据交换服务</a><span></span></dd>
            </dl> -->
            <dl>
            	<dt>云企业服务总线</dt>
                <dd><a class="esbfw" href="esb_price_service.jsp" target="iframepage">RCloud 云企业服务总线</a><span></span></dd>
            </dl>
            <dl>
            	<dt>云认证鉴权服务</dt>
                <dd><a class="jqfw" href="raa_price.jsp" target="iframepage">RCloud 云认证鉴权服务</a><span></span></dd>
                <!-- <dd><a class="dddlfw" href="javascript:;">RCloud单点登录服务</a><span></span></dd> -->
            </dl>
            <dl>
            	<dt></dt>
            </dl>
        </div>
    </div>
    <div class="gf_sq_right left rae_left">
    	<iframe src="rae_price_service.jsp" frameborder="0" id="iframepage" name="iframepage" scrolling="no" width="100%"  onLoad="iFrameHeight()"></iframe> 
    </div>  
<script type="text/javascript" language="javascript">   
function iFrameHeight() {   
	var ifm= document.getElementById("iframepage");   
	var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;   
	if(ifm != null && subWeb != null) {
	   ifm.height = subWeb.body.scrollHeight;
	}   
}   
</script>  
</div>
<!--content end-->
<div class="gf_footer">
<%@ include file="../../static/include/home_buttom.jsp" %>
<%@ include file="../../static/include/buttom.jsp" %>
</div>
<script src="js/common.js"></script>
<script type="text/javascript" src="js/mysql_service.js"></script>
<script type="text/javascript" >
var currID_t ="case_4";
</script>
</body>
</html>
