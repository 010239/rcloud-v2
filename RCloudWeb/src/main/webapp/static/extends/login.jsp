<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>登录</title>

<%@ include file="../include/include.jsp" %>
<script type="text/javascript">
	$(function(){
		$("#currenturl").val(getQueryString("currenturl"));
		//bootstrap中button默认没有事件
		$("#dl").removeAttr("disabled");
		
		//默认第一次不显示校验码
		$("#showcode").hide();
		$("#code").val("1111");
		//初始化回车键（Enter）事件，按下Enter键，执行登录方法
	    $(document).keydown(function(event){
	    	var keycode = event.which;
		    if (keycode == 13) {
		    	login();
		    }
	    });
		
	    $('#randImg').click(function(){
			var timestamp = (new Date()).valueOf();  
			$('#randImg').attr('src',$(this).attr('src')+'?'+timestamp);
		});
	});
	
	function getQueryString(name) {
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	    var r = window.location.search.substr(1).match(reg);
	    if (r != null) return unescape(r[2]); return null;
	}
	
	//登录方法
	function login(){
		var url="${ctx}/rest/login";
		
		if($("#code").val() == ""){
			
			//$("#errorInfo").html("验证码不能为空");
			//$("#errorModal").modal("show");
			$("#err_m").html("验证码不能为空");
			$("#err_m").show();
			return false;
		}
		
		
		$.ajax({ 
			type: "post", 
			url: url, 
			data: $("#form").serialize(),
			//dataType: "json", 
			//contentType: "application/json; charset=utf-8", 
			success: function (result) {
				if(result.status == '200'){
					if(result.entity.currenturl != ""){
						window.location.href = result.entity.currenturl;
					}else{
						window.location.href = "${ctx}/page/center";
					}
					//window.location.href = result.entity.currenturl;
				}else if(result.status == '501'){
					$("#code").val("");
					$("#showcode").show();
					$('#randImg').click();
					if(result.errorCode == '101'){
			    		//$("#errorInfo").html("用户名或密码错！");
			    		$("#err_m").html("用户名或密码错！");
					}else if(result.errorCode == '911'){
			    		$("#err_m").html("用户名或密码错！");
					}
					else if (result.errorCode == '102') {
			    		//$("#errorInfo").html("认证错误,请联系云平台管理员！");
			    		$("#err_m").html("认证错误,请联系云平台管理员！");
					}else if (result.errorCode == '103') {
						//$("#errorInfo").html("验证码错误！");
						$("#err_m").html("验证码错误！");
					}else{
						//$("#errorInfo").html(result.errorCode);
						$("#err_m").html(result.errorCode);
					}
	    			//$("#errorModal").modal("show");
	    			$("#err_m").show();
				}else{
					
	    			$("#err_m").html("服务器错误，请联系管理员");
	    			$("#err_m").show();
					
					$("#code").val("");
					$("#showcode").show();
					$('#randImg').click();
				}
			} 
	   });
		
		
		/* $.getJSON(url, $("#form").serialize(),function(result) {
			alert(result.status);
			if(result.status == '200'){
				if(result.entity.currenturl != ""){
					window.location.href = result.entity.currenturl;
				}else{
					window.location.href = "${ctx}/page/center";
				}
				//window.location.href = result.entity.currenturl;
			}else if(result.status == '501'){
				$("#code").val("");
				$("#showcode").show();
				$('#randImg').click();
				if(result.errorCode == '101'){
		    		//$("#errorInfo").html("用户名或密码错！");
		    		$("#err_m").html("用户不存在！");
				}else if(result.errorCode == '911'){
		    		$("#err_m").html("用户名或密码错！");
				}
				else if (result.errorCode == '102') {
		    		//$("#errorInfo").html("认证错误,请联系云平台管理员！");
		    		$("#err_m").html("认证错误,请联系云平台管理员！");
				}else if (result.errorCode == '103') {
					//$("#errorInfo").html("验证码错误！");
					$("#err_m").html("验证码错误！");
				}else{
					//$("#errorInfo").html(result.errorCode);
					$("#err_m").html(result.errorCode);
				}
    			//$("#errorModal").modal("show");
    			$("#err_m").show();
			}else{
				
    			$("#err_m").html("服务器错误，请联系管理员");
    			$("#err_m").show();
				
				$("#code").val("");
				$("#showcode").show();
				$('#randImg').click();
			}
		}); */
		
	}
	
</script>

<style type="text/css">
	.err_m {
		color: red;
    	padding-right: 15px;
    	padding-bottom:10px;
	    display: none;
	    line-height: 18px;
	    padding-left: 20px;
	    text-align: left;
	    vertical-align: middle;
	}
</style>

</head>
<body>
<div class="gf_header_t">
	<div class="gf_header_box">
    	<h1 class="left"><a href="${ctx}/"><img src="images/logo.png" alt="logo" /></a></h1>
        <h2 class="left">账号登录</h2>
        <p class="right"><a href="${ctx}/">Rcloud首页</a>|<a href="${ctx}/static/extends/rae_help_service.jsp">帮助中心</a></p>
    </div>
</div>
<div class="gf_wrapper_t cf h_562">
	<div class="gf_lpic_t left"></div>
    <div class="gf_login_t left">
    	<form id="form" method="post">
            <ul class="gf_login_ul">
            	<span id="err_m" class="err_m">错误</span>
                <li class="gf_name_t">
                    <span></span>
                    <input type="text" placeholder="用户名" name="username" id="username" value=""/>
                </li>
                <li class="gf_pas_t">
                    <span></span>
                    <input type="password" placeholder="密码" name="password" value=""/>
                </li>
                <li class="gf_pas_t" id="showcode">
                	<span></span>
                	<input type="text" placeholder="校验码" name="code" id="code" style="width: 150px"/>
                	<input type="hidden" name="hidecode" id="hidecode">
                	<input type="hidden" name="currenturl" id="currenturl">
                	<img id="randImg" src="${ctx}/randImage/getRandImage" alt="请输入校验码" style="float: right;margin-right:7px;margin-top: 9px;"/>
                </li>
            </ul>
            <input class="gf_login_btn" type="button" value="登录" id="dl" onclick="login()"/>
            <p class="gf_tips"><!-- <a class="left" href="forget.jsp">忘记密码？</a> --><a class="right" href="register.jsp">免费注册</a></p>
        </form>		
    </div>
</div>
<div class="gf_footer">   
	<p class="gf_bg_t"></p>
    <div class="gf_company_imform">
    	<p>版权所有 © 2013-2014 中软国际公司   京ICP备14011895号-1</p>
        <p>电话：+86 010 888482388 转 6326 传真：+86 010 8286 2809 邮箱：rcloud_support@chinasofti.com</p>
    </div>
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
<script type="text/javascript" src="js/login.js"></script>
</body>
</html>
