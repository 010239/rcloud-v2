<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="theme" value="blue" />
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
	location.href = "${ctx}/page/logout?currenturl="+currenturl;
}
</script>
<div class="gf_idx_topbar">
	<c:if test="${empty user_info }">
	    <p class="gf_idx_pwn gf_idx_ar">
	        <a href="javascript:;" onclick="userLogin();">登录<i class="gf_sprite"></i></a>
	        <a href="${ctx}/static/extends/register.jsp">注册<i class="gf_sprite"></i></a>
	    </p>
	</c:if>
	<c:if test="${not empty user_info }">
	    <p class="gf_idx_pwn gf_idx_ar">
	        <a href="javascript:;">${user_info.userName } ,您好<i class="gf_sprite"></i></a>
	        <a href="javascript:;" onclick="userLogout();">注销<i class="gf_sprite"></i></a>
	        <a href="${ctx}/">回到首页<i class="gf_sprite"></i></a>
	    </p>
    </c:if>
</div>
<!-- Modal -->
<div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">提示</h4>
      </div>
      <div id="errorInfo" class="modal-body">
        	请求或响应发生错误！
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>

