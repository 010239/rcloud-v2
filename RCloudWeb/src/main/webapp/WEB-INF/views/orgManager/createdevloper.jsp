<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../../../static/include/default.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>机构开发者</title>

<link type="text/css" href="${ctx}/static/extends/css/error_hint.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${ctx}/static/lib/js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx}/static/lib/js/jquery.validationEngine.min.js"></script>
<script type="text/javascript"
	src="${ctx}/static/lib/js/jquery.placeholder.js"></script>
<script type="text/javascript">
	$(function() {	
			
			var hasRegisted = false;//默认没有注册
			$("#form").validationEngine({
				onValidationComplete : function(form, valid) {
					if (valid == true) {
						
						/* if($("#email").val()==""){
							$("#msg3").html("<font color='red'>邮箱不能为空！</font>");
							return false;
						}else {
							$("#msg3").html("");
						}
						if($("#name").val()==""){
							$("#msg0").html("<font color='red'>用户名不能为空！</font>");
							return false;
						}else {
							$("#msg0").html("");
						}
						if($("#pw1").val()==""){
							$("#msg1").html("<font color='red'>密码不能为空！</font>");
							return false;
						}
						if($("#pw1").val().length <6 ){
							$("#msg1").html("<font color='red'>密码不能小于6位！</font>");
							return false;
						}
						if($("#pw1").val()!="" && $("#pw1").val().length <=6 ){
							$("#msg1").html("");
						}
						if($("#pw2").val()==""){
							$("#msg2").html("<font color='red'>确认密码不能为空！</font>");
							return false;
						}
						
					    if($("#pw1").val() != $("#pw2").val()){
							$("#msg2").html("<font color='red'>两次输入密码不一致</font>！");
							return false;
						}
					    if($("#pw2").val()!="" && $("#pw1").val() == $("#pw2").val()){
							$("#msg2").html("");
						} */
						if(hasRegisted){
							$("#errorInfo").html("此用户名已经被注册！");
			    			$("#errorModal").modal("show");
							return;
						}
						document.form.submit();
						
					}
					return false;

				},//表单验证后的回调函数，form为提交的表单，valid为结果，验证通过则为true,验证失败则为false
				scroll : false,
				focusFirstField : false,
				maxErrorsPerField:1
			});
			
			$('#name').blur(function(){
				/* if($("#name").val()==""){
					$("#msg0").html("<font color='red'>用户名不能为空！</font>");
				}else{ */
				
				if($("#name").val()==""){
				//	$("#msg0").html("");
					return;					
				}
				$.ajax({
					url:'${ctx}/rest/checkUserName' ,
					data:$("#form").serialize(),
					success:function(result){ 
						if(result.status != '200'){
							hasRegisted = true;
							//$("#msg0").html("<font color='red'>此用户名已经被注册！</font>");
							$("#errorInfo").html("此用户名已经被注册！");
			    			$("#errorModal").modal("show");
						}else {
							hasRegisted = false;
					//		$("#msg0").html("恭喜您，此用户名可用！");
						}
					},
					error:function(data){ 
						$("#msg0").html("<font color='red'>系统错误，请稍后再试！</font>");
					}
				});
				
				//}
			});
			/*$('#email').blur(function(){
				if($("#email").val()==""){
					$("#msg3").html("<font color='red'>邮箱不能为空！</font>");
					return false;
				}else {
					$("#msg3").html("");
				}
			});
			$('#pw1').blur(function(){
				if($("#pw1").val()==""){
					$("#msg1").html("<font color='red'>密码不能为空！</font>");
					return false;
				}
				if($("#pw1").val().length <6 ){
					$("#msg1").html("<font color='red'>密码不能小于6位！</font>");
					return false;
				}
				if($("#pw1").val()!="" && $("#pw1").val().length >=6 ){
					$("#msg1").html("");
				}
			});
			$('#pw2').blur(function(){
				if($("#pw2").val()==""){
					$("#msg2").html("<font color='red'>确认密码不能为空！</font>");
					return false;
				}
				
			    if($("#pw1").val() != $("#pw2").val()){
					$("#msg2").html("<font color='red'>两次输入密码不一致</font>！");
					return false;
				}
			    if($("#pw2").val()!="" && $("#pw1").val() == $("#pw2").val()){
					$("#msg2").html("");
				}
			}); */
		
	});

</script>
<style type="text/css">
::-webkit-input-placeholder { /* WebKit browsers */
color: #999;
}
:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
color: #999;
}
::-moz-placeholder { /* Mozilla Firefox 19+ */
color: #999;
}
:-ms-input-placeholder { /* Internet Explorer 10+ */
color: #999;
} 
</style>
</head>
<body>
	<form id="form" method="post" action="${ctx}/page/org/savedevloper" >
	
    <dl>
        <dd class="gf_info_dd1">
            <dl>
                <dd>
                    <p>
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;登录名：</span>
                        <input type="text" name="userName" class="gf_info_comname validate[required,custom[nochina]]" placeholder="登录名" id="name" data-prompt-target='handle_name' data-prompt-position='inline'>
                        <span id="handle_name" class="import_tip"></span>
                        <span id="msg0"></span>
                    </p>
                    <p>
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱：</span>
                        <input name="userEmail"	type="email" class="gf_info_comname validate[required,custom[email]]" placeholder="邮箱" id="email" data-prompt-target='handle_email' data-prompt-position='inline'/>
                        <span id="handle_email" class="import_tip"></span>
                        <span id="msg3"></span>
                    </p>
                    <p>
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密码：</span>
                        <input name="password" type="password" class="gf_info_comname validate[required]" placeholder="密码" id="pw1" data-prompt-target='handle_pw1' data-prompt-position='inline'>
                        <span id="handle_pw1" class="import_tip"></span>
                        <span id="msg1"></span>
                    </p>
                    <p>
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认密码：</span>
                        <input name="repassword" type="password" class="gf_info_comname validate[required,equals[pw1]]" placeholder="确认密码" id="pw2" data-prompt-target='handle_pw2' data-prompt-position='inline'>
                        <span id="handle_pw2" class="import_tip"></span>
                    	<span id="msg2"></span>
                    </p>
                    <p>
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真实姓名：</span>
                        <input name="realName" type="text" class="gf_info_comname" placeholder="真实姓名">
                    </p>
                    <p>
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;单位名称：</span>
                        <input name="companyName" type="text" class="gf_info_comname" placeholder="单位名称">
                    </p>
                    <p>
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;固定电话：</span>
                        <input name="fixedPhone" type="text" class="gf_info_comname validate[custom[phone]]" placeholder="固定电话" data-prompt-target='handle_fixedPhone' data-prompt-position='inline'>
                        <span id="handle_fixedPhone" class="import_tip"></span>
                    </p>
                    <p>
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;移动电话：</span>
                        <input name="mobilePhone" type="text" class="gf_info_comname validate[custom[chinaHandPhone]]" placeholder="移动电话" data-prompt-target='handle_mobilePhone' data-prompt-position='inline'>
                        <span id="handle_mobilePhone" class="import_tip"></span>
                    </p>
                    <p>
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;所在地区：</span>
                        <input name="areaName" type="text" class="gf_info_comname" placeholder="所在地区">
                    </p>
                    <p>
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;街道地址：</span>
                        <input name="streatName" type="text" class="gf_info_comname" placeholder="街道地址">
                    </p>
                    <p>&nbsp;</p>
                    <p class="gf_ib_wrap">
                    <button type="submit" class="gf_info_btn">提交</button>
                    <button type="button" class="gf_info_btn" onclick="window.history.back()">返回</button>
                    </p>
                </dd>
            </dl>
        </dd>
    </dl>
	</form>
	
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
</body>
</html>
