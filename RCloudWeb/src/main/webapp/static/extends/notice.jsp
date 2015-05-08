<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<script src="${ctx}/static/extends/js/jquery_v1.10.2_min.js"></script>
<script type="text/javascript">
var ctx = "${ctx}/";
var currID = "yyq";
(function (){
    (function(){
	$.ajax({		
		url: ctx+ "rest/notice/showAllNotice" ,
		success:function(data) {
			if (data.status == '200' && data.entity ) {
				var totalNotice = data.entity;
				if (totalNotice !=  null) {	
					var detilNotice = "";
					for ( var i=0; i<totalNotice.length ; i++) {
						detilNotice += "<dl class='notice_dl'><dt><span>"+totalNotice[i].showCut+"</span><em>"+totalNotice[i].publishTime+"</em></dt><dd><p>"+totalNotice[i].notice+"</p></dd></dl>";
						
					}
					$("#detail").html(detilNotice);
				} else {
					
				}
				
			}
		},
	error : function(XMLHttpRequest, textStatus, errorThrown){
		centerMessage("提示", "与服务器连接失败！");
	}
	});
})();
})();
</script>
<title>网站公告</title>
</head>
<body class="gf_idx">
<%@ include file="../../static/include/top.jsp" %>
<%@ include file="../../static/include/home_top.jsp" %>
<!--content star-->
<div class="gf_wrapper_t cf">
	<div class="gf_help_left left">
    	<div class="gf_sq_llist">
        	<dl class="gf_sq_dlone">
            	<dt class="notice_dt"></dt>
                <dd class="leftmenu show on"><a href="javascript:;" class="notice">网站公告</a><span></span></dd>
            </dl>
            <dl>
            	<dt style="height:500px;"></dt>
            </dl>
        </div>
    </div>
   	  <div class="gf_notice_right left" id="detail">

 <!--  <dl class="notice_dl">
        	 <dt>
            	<span>因平台升级部分服务暂停通知</span>
                <em>2014年10月15日 08:00</em>
            </dt>
            <dd>
            	<p>用户您好：云平台将于 10月16日 00:00 至 06:00 进行系统更新，届时数据服务将停止更新，给您带来不便敬请谅解。云平台将于 10月16日 00:00 至 06:00 进行系统更新，届时数据服务将停止更新，给您带来不便敬请谅解。</p>
            </dd>
        </dl>
        <dl class="notice_dl">
        	<dt>
            	<span>因平台升级部分服务暂停通知</span>
                <em>2014年10月15日 08:00</em>
            </dt>
            <dd>
            	<p>用户您好：云平台将于 10月16日 00:00 至 06:00 进行系统更新，届时数据服务将停止更新，给您带来不便敬请谅解。云平台将于 10月16日 00:00 至 06:00 进行系统更新，届时数据服务将停止更新，给您带来不便敬请谅解。</p>
            </dd>
        </dl>
        <dl class="notice_dl">
        	<dt>
            	<span>因平台升级部分服务暂停通知</span>
                <em>2014年10月15日 08:00</em>
            </dt>
            <dd>
            	<p>用户您好：云平台将于 10月16日 00:00 至 06:00 进行系统更新，届时数据服务将停止更新，给您带来不便敬请谅解。云平台将于 10月16日 00:00 至 06:00 进行系统更新，届时数据服务将停止更新，给您带来不便敬请谅解。</p>
            </dd>
        </dl> -->
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
