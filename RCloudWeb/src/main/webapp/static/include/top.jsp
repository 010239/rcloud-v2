<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="theme" value="blue" />
<link rel="stylesheet" href="${ctx}/static/lib/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/static/lib/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${ctx}/static/extends/css/style.css">
<script src="${ctx}/static/extends/js/jquery_v1.10.2_min.js"></script>
<script type="text/javascript" src="${ctx}/static/lib/easyui/1.3.6/jquery.easyui.min.js"></script>
<script type="text/javascript">
var ctx = "${ctx}/";
function userLogin(){
	var currenturl = window.location.href;
	currenturl = encodeURIComponent(currenturl);
	location.href = "${ctx}/static/extends/login.jsp?currenturl=" + currenturl;
}
function userLogout(){
	var currenturl = window.location.href;
	currenturl = encodeURIComponent(currenturl);
	location.href = "${ctx}/page/logout?currenturl=" + currenturl;
}
$(document).ready(function(){
	$.ajax({		
		url: ctx+ "rest/notice/countTodayNotice" ,
		success:function(data) {
			if (data.status == '200'  ) {
				var countToday = data.entity;
				if (countToday !=  0) {
					$("#notice-count").html(countToday);	
					$("#notice-count-s").html(countToday);	
				} else {
					$("#notice-count").html("0");		
					$("#notice-count-s").html("0");	
				}
				
			}else{
			     if(data.errorMessage != null){
			         $("#errorInfo").html(data.errorMessage);
			         }
			         $("#errorModal").modal("show");
			         }
		},
	error : function(XMLHttpRequest, textStatus, errorThrown){
		centerMessage("提示", "与服务器连接失败！");
	}
	});
});

</script>
<div class="gf_idx_topbar">
	<c:if test="${empty user_info }">
	    <p class="gf_idx_pwn gf_idx_ar">
	        <a href="javascript:;" onclick="userLogin();">登录<i class="gf_sprite"></i></a>
	        <a href="${ctx}/static/extends/register.jsp">注册<i class="gf_sprite"></i></a>
          <a title='公告' href="${ctx}/static/extends/notice.jsp" class="msg_icon" id="notice-url"><span id="notice-count"></span></a>
	    </p>
	</c:if>
	<c:if test="${not empty user_info }">
	    <p class="gf_idx_pwn gf_idx_ar">
	        <a href="${ctx}/page/center">${user_info.userName } ,您好<i class="gf_sprite"></i></a>
	        <a href="javascript:;" onclick="userLogout();">注销<i class="gf_sprite"></i></a>
	        <a href="${ctx}/page/center">用户中心<i class="gf_sprite"></i></a>
	        <a title='公告' href="${ctx}/static/extends/notice.jsp" class="msg_icon" ><span id="notice-count-s"></span></a>
	    </p>
    </c:if>
</div>


