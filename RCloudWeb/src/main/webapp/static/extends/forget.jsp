<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>忘记密码</title>
<%@ include file="../include/include.jsp" %>
<link type="text/css" href="${ctx}/static/extends/css/error_hint.css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/static/lib/js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/lib/js/jquery.validationEngine.min.js"></script>
<script type="text/javascript" src="${ctx}/static/lib/js/jquery.placeholder.js"></script>
<script>

function isNull(exp){
	if (!exp && typeof(exp)!="undefined" && exp!=0)
	{
	    return true;
	}
	return false;
}

function sendAuth(){
	var select = $("#selectauth");
	$.get("${ctx}/rest/sendEmail?email="+select.val()+"",null,function(result) {
		if(result.status == '200'){
			alert("验证码已发送，请注意查收");
		}else{
			alert("服务器出错!请联系管理员");
		}
	},"json");
}

$(function(){
	(function(){
		
		//修改密码
		//$('.gf_tab_lmx1 .gf_regis_user_btn').click(function(){
			//$('.gf_tab_lmx1 .gf_regis_li1').hide();
			//$('.gf_tab_lmx1 .gf_regis_li2').show();
			
			
			/* var url="${ctx}/checkout";
			$.getJSON(url, $("#form1").serialize() , function(result) {
				if(result.status == '200'){
					var dataEntity = result.entity;
					$('.gf_tab_lmx1 .gf_regis_li1').hide();
					$('.gf_tab_lmx1 .gf_regis_li2').show();
					
					$("#backusername").val(dataEntity.userEmail);
					var select = $("#selectauth");
					select.empty();
					if(!isNull(dataEntity.userEmail)){
						select.append("<option value='"+dataEntity.userEmail+"'>"+"邮箱"+dataEntity.userEmail+"</option>");
					}
					
					if(!isNull(dataEntity.mobilePhone)){
						select.append("<option value='"+dataEntity.mobilePhone+"'>"+"手机"+dataEntity.mobilePhone+"</option>");
					}
					
				}else{
					alert("用户名不存在!");
				}
			}); */
		//})
		
		
		$("#form1").validationEngine(
				{
				onValidationComplete :function(form,valid){
					if(valid==true){
						
						var url="${ctx}/rest/checkout";
						$.getJSON(url, $("#form1").serialize() , function(result) {
							if(result.status == '200'){
								var dataEntity = result.entity;
								$('.gf_tab_lmx1 .gf_regis_li1').hide();
								$('.gf_tab_lmx1 .gf_regis_li2').show();
								
								$("#backusername").val(dataEntity.userEmail);
								var select = $("#selectauth");
								select.empty();
								if(!isNull(dataEntity.userEmail)){
									select.append("<option value='"+dataEntity.userEmail+"'>"+"邮箱"+dataEntity.userEmail+"</option>");
								}
								
								if(!isNull(dataEntity.mobilePhone)){
									select.append("<option value='"+dataEntity.mobilePhone+"'>"+"手机"+dataEntity.mobilePhone+"</option>");
								}
								
							}else{
								alert("用户名不存在!");
							}
						});
					}
					return false;
					
				},//表单验证后的回调函数，form为提交的表单，valid为结果，验证通过则为true,验证失败则为false
				scroll:false,
				focusFirstField:false
		});
		
		
		/* $('.gf_tab_lmx1 .gf_regis_btn2').click(function(){
			//$('.gf_tab_lmx1 .gf_regis_li2').hide();
			//$('.gf_tab_lmx1 .gf_regis_li3').show();
			
			var url="${ctx}/modifypwd";
			$.post(url,$("#form2").serialize(),function(result){
				if(result.status == '200'){
					$('.gf_tab_lmx1 .gf_regis_li2').hide();
					$('.gf_tab_lmx1 .gf_regis_li3').show();
				}else{
					alert("注册失败");
				}
			 });
		}); */
		
		$("#form2").validationEngine(
				{
				onValidationComplete :function(form,valid){
					if(valid==true){
						
						if($("#pwd1").val() != $("#pwd1_1").val()){
							alert("两次密码不一致");
							return false;
						}
						
						var url="${ctx}/rest/modifypwd";
						$.post(url,$("#form2").serialize(),function(result){
							if(result.status == '200'){
								$('.gf_tab_lmx1 .gf_regis_li2').hide();
								$('.gf_tab_lmx1 .gf_regis_li3').show();
							}else{
								alert("注册失败");
							}
						 });
					}
					return false;
					
				},//表单验证后的回调函数，form为提交的表单，valid为结果，验证通过则为true,验证失败则为false
				scroll:false,
				focusFirstField:false
		});
		
		
		
	})()
});
</script>
</head>
<body>
<div class="gf_header_t">
	<div class="gf_header_box">
    	<h1 class="left"><a href="${ctx}/index.jsp"><img src="images/logo.png" alt="logo" /></a></h1>
        <h2 class="left">账号注册</h2>
        <p class="right"><a href="${ctx}/index.jsp">Rcloud首页</a>|<a href="javascript:;">服务平台首页</a>|<a href="javascript:;">帮助中心</a></p>
    </div>
</div>
<!--content start-->
<div class="gf_regist_warp">
	<div class="gf_reg_content">
    	<h1><i>找回<span class="gf_blue">Rcloud</span>云账号密码</i><em>我已经找回，现在就<a href="login.jsp"  class="gf_blue">登录</a></em></h1>
        <div class="gf_register_tab">
        	<ul class="gf_regis_ul1 gf_tab_lmx1 gf_on">
                <li class="gf_regis_li1 gf_on">
                    <div class="gf_reg_img ">
                        <img src="images/bg/reg_img1.png"/>
                        <ul class="gf_reg_num cf">
                            <li class="gf_on">填写已注册的用户名</li>
                            <li>修改密码</li>
                            <li>修改密码成功</li>
                        </ul>
                    </div>
                    <div class="gf_regis_imform ">
                        <form id="form1">
                            <dl class="cf">
                                <dt><span>*</span>请输入注册时用户名:</dt>
                                <!--input class="suc"  验证成功-->
                                <!--input class="err"  验证失败-->
                                <dd><input  name="username" type="text" placeholder="电子邮箱作为用户名"/></dd>
                            </dl>
                            <dl class="cf gf_reg_btn">
                                <dt></dt>
                                <dd><input type="submit" value="点击下一步" class="gf_regis_user_btn"/></dd>
                            </dl>
                        </form>
                    </div>
                </li>
                <li class="gf_regis_li2">
                    <div class="gf_reg_img gf_padd_b34">
                        <img src="images/bg/reg_img2.png"/>
                        <ul class="gf_reg_num cf">
                            <li>填写已注册的用户名</li>
                            <li  class="gf_on">修改密码</li>
                            <li>修改密码成功</li>
                        </ul>
                    </div>
                    <div  class="gf_regis_imform gf_padd_bottom150">
                    	<!--  
                        <p>验证码已发送至你的邮箱，请登录您的邮箱确认</p>
                        -->
                        <form id="form2">
                        <input type="hidden" name="username" id="backusername">
                        <dl class="cf">
                            <dt><span>*</span>选择验证方式:</dt>
                            <!--input class="suc"  验证成功-->
                            <!--input class="err"  验证失败-->
                            <dd>
                            	<select id="selectauth">
                            		<option>手机号15801017657</option>
                            		<option>邮箱fhfhjad@163.com</option>
                            	</select>
                            	<a style="text-decoration: underline;cursor: pointer;" onclick="sendAuth()">点击发送</a>
							</dd>
                         </dl>
                         <dl class="cf">
                            <dt><span>*</span>验证码:</dt>
                            <!--input class="suc"  验证成功-->
                            <!--input class="err"  验证失败-->
                            <dd><input name="authcode" class="validate[required]" type="text" placeholder=""/></dd>
                         </dl>
                         
                         <dl class="cf gf_pass_leval">
                              <dt><span>*</span>新密码:</dt>
                              <dd><input id="pwd1"  name="password" class="validate[required]" type="password"  /></dd>
                              <!-- div class="" 弱-->
                              <!-- div class="gf_leval2" 中-->
                              <!-- div class="gf_leval3" 强-->
                              <!--  
                              <div class="gf_leval2">
                                  <em>弱</em><span>中</span><i>强</i>
                              </div>
                              -->
                          </dl>
                          <dl class="cf">
                              <dt><span>*</span>确认密码:</dt>
                              <dd><input id="pwd1_1" name="repassword" class="validate[required]" type="password"/></dd>
                          </dl>
                         
                         <dl class="cf gf_reg_btn">
                            <dt></dt>
                            <dd><input type="submit" value="点击下一步" class="gf_regis_btn2"/></dd>
                        </dl>
                        </form>
                    </div>
                </li>
                <li class="gf_regis_li3">
                    <div class="gf_reg_img gf_padd_b34">
                        <img src="images/bg/reg_img3.png"/>
                        <ul class="gf_reg_num cf">
                            <li>填写已注册的用户名</li>
                            <li>修改密码</li>
                            <li  class="gf_on">修改密码成功</li>
                        </ul>
                    </div>
                    <div  class="gf_regis_imform gf_padd_bottom150">
                        <p >您已成功修改密码，跳转至<a  class="gf_col_red" href="login.jsp">登录</a>页面</p>
                    </div>	
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="gf_footer">
<%@ include file="../../static/include/buttom.jsp" %>
</div>
<!--content end-->
</body>
</html>
