<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>注册</title>
<%@ include file="../include/include.jsp" %>
<link type="text/css" href="${ctx}/static/extends/css/error_hint.css" rel="stylesheet"/>
<style type="text/css">
::-webkit-input-placeholder { /* WebKit browsers */
    color:    #999;
}
:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
    color:    #999;
}
::-moz-placeholder { /* Mozilla Firefox 19+ */
    color:    #999;
}
:-ms-input-placeholder { /* Internet Explorer 10+ */
    color:    #999;
}
</style>
<script type="text/javascript" src="${ctx}/static/lib/js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/lib/js/jquery.validationEngine.min.js"></script>
<script type="text/javascript" src="${ctx}/static/lib/js/jquery.placeholder.js"></script>
<script>
$(function(){
	
	$('#pwd1').keyup(function(e) {
	    var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
	    var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
	    var enoughRegex = new RegExp("(?=.{6,}).*", "g");
	    $("#pwd1_l").removeClass();	
	    if (strongRegex.test($(this).val())) {
	    	$("#pwd1_l").addClass("gf_leval3");
	    } else if (mediumRegex.test($(this).val())) {
	    	$("#pwd1_l").addClass("gf_leval2");
	    } else {
	    	$("#pwd1_l").removeClass();
	    }
	    return true;
	});
	
	$('#pwd2').keyup(function(e) {
	    var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
	    var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
	    var enoughRegex = new RegExp("(?=.{6,}).*", "g");
	    $("#pwd2_l").removeClass();		
	    if (strongRegex.test($(this).val())) {
	    	$("#pwd2_l").addClass("gf_leval3");
	    } else if (mediumRegex.test($(this).val())) {
	    	$("#pwd2_l").addClass("gf_leval2");
	    } else {
	    	$("#pwd2_l").removeClass();	
	    }
	    return true;
	});
	
	
	//tab
	$('.gf_list_tab dl').click(function(){
		$('.gf_list_tab dl').removeClass('gf_tab_on');
		$(this).addClass('gf_tab_on');
		$('.gf_regis_ul1').removeClass('gf_on');
		$('.gf_regis_ul1').eq($(this).index()).addClass('gf_on');
	});
	
	(function(){
		
		$('#emailDevlpoer').blur(function(){
			
			if($('#emailDevlpoer').val() == '') return;
			$.ajax({
				url:'${ctx}/rest/checkemail',
				data:$("#form1").serialize(),
				success:function(result){ 
					if(result.status != '200'){
						//alert("此邮箱已注册1");
						$("#errorInfo").html("此邮箱已注册");
		    			$("#errorModal").modal("show");
					}
				},
				error:function(data){ 
					//alert('系统错误，请稍后再试。');
					$("#errorInfo").html("系统错误，请稍后再试。");
	    			$("#errorModal").modal("show");
				}
			});
		});
		
		$('#emailOrg').blur(function(){
			if($('#emailOrg').val() == '')return;
			$.ajax({
				url:'${ctx}/rest/checkemail',
				data:$("#form2").serialize(),
				success:function(result){ 
					if(result.status != '200'){
						//alert("此邮箱已注册");
						$("#errorInfo").html("此邮箱已注册");
		    			$("#errorModal").modal("show");
					}
				},
				error:function(data){ 
					//alert('系统错误，请稍后再试。');
					$("#errorInfo").html("系统错误，请稍后再试。");
	    			$("#errorModal").modal("show");
				}
			});
		});

		$("#form1").validationEngine(
				{
				onValidationComplete :function(form,valid){
					if(valid==true){
						if($("#pwd1").val() != $("#pwd1_1").val()){
							//alert("密码不一致");
							$("#errorInfo").html("密码不一致");
			    			$("#errorModal").modal("show");
							return false;
						}
						$("#submit_btn1").attr("disabled", true); //不可重复提交
						var url="${ctx}/rest/sendEmail";
						$.getJSON(url, $("#form1").serialize() , function(result) {
							if(result.status == '200'){
								$("#submit1").attr("disabled", false); 
								$('.gf_tab_lmx1 .gf_regis_li1').hide();
								$('.gf_tab_lmx1 .gf_regis_li2').show();
							}
						});
					}
					return false;
					
				},//表单验证后的回调函数，form为提交的表单，valid为结果，验证通过则为true,验证失败则为false
				scroll:false,
				focusFirstField:false,
				maxErrorsPerField:1
		});
			
		$("#form1_2").validationEngine(
				{
				onValidationComplete :function(form,valid){
					if(valid==true){
						var urlcheck = "${ctx}/rest/checkEmailcode";
						$.getJSON(urlcheck, { emailcode: $("#code1").val() }, function(result){
							if(result.status == '200'){
								$("#waitModal").modal("show");
								$("#submit1").attr("disabled", true); 
								var url="${ctx}/rest/personRegist";
								$.getJSON(url, $("#form1").serialize() , function(result) {
									if(result.status == '200'){
										$("#waitModal").modal("hide");
										$('.gf_tab_lmx1 .gf_regis_li2').hide();
										$('.gf_tab_lmx1 .gf_regis_li3').show();	
									}else{
										//alert("注册失败");
										$("#waitModal").modal("hide");
										$("#errorInfo").html("注册失败");
						    			$("#errorModal").modal("show");
									}
								});
							}else{
								//alert(result.entity);
								$("#errorInfo").html(result.entity);
				    			$("#errorModal").modal("show");
							}
						});
					}
					return false;
					
				},//表单验证后的回调函数，form为提交的表单，valid为结果，验证通过则为true,验证失败则为false
				scroll:false,
				focusFirstField:false,
				maxErrorsPerField:1
		});
		
		$("#form2").validationEngine(
				{
				onValidationComplete :function(form,valid){
					if(valid==true){
						
						if($("#pwd2").val() != $("#pwd2_1").val()){
							//alert("两次密码不一致");
							$("#errorInfo").html("密码不一致");
			    			$("#errorModal").modal("show");
							return false;
						}
						
						$("#submit2").attr("disabled", true); //不可重复提交
						
						var url="${ctx}/rest/sendEmail";
						$.getJSON(url, $("#form2").serialize() , function(result) {
							
							if(result.status == '200'){
								$("#submit2").attr("disabled", false); 
								$('.gf_tab_lmx2 .gf_regis_li1').hide();
								$('.gf_tab_lmx2 .gf_regis_li2').show();
							}
						});
					}
					return false;
					
				},//表单验证后的回调函数，form为提交的表单，valid为结果，验证通过则为true,验证失败则为false
				scroll:false,
				focusFirstField:false,
				maxErrorsPerField:1
		});
		
		$("#form2_2").validationEngine(
				{
				onValidationComplete :function(form,valid){
					if(valid==true){
						var urlcheck = "${ctx}/rest/checkEmailcode";
						$.getJSON(urlcheck, { emailcode: $("#code2").val() }, function(result){
							if(result.status == '200'){
								$("#waitModal").modal("show");
								$("#submit2").attr("disabled", true); 
								var url="${ctx}/rest/orgRegist";
								$.post(url,$("#form2").serialize(),function(result){
									if(result.status == '200'){
										$("#waitModal").modal("hide");
										$('.gf_tab_lmx2 .gf_regis_li2').hide();
										$('.gf_tab_lmx2 .gf_regis_li3').show();
									}else{
										//alert("注册失败");
										$("#waitModal").modal("hide");
										$("#errorInfo").html("注册失败");
						    			$("#errorModal").modal("show");
									}
								 });
								
							}else{
								$("#errorInfo").html(result.entity);
				    			$("#errorModal").modal("show");
							}
						});
					}
					return false; //不提交表单
				},//表单验证后的回调函数，form为提交的表单，valid为结果，验证通过则为true,验证失败则为false
				scroll:false,
				focusFirstField:false,
				maxErrorsPerField:1
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
        <p class="right"><a href="${ctx}/index.jsp">Rcloud首页</a>|<a href="${ctx}/static/extends/rae_help_service.jsp">帮助中心</a></p>
    </div>
</div>
<!--content start-->
<div class="gf_regist_warp">
	<div class="gf_reg_content">
    	<h1><i>注册<span class="gf_blue">Rcloud</span>云账号</i><em>我已经注册，现在就<a href="login.jsp"  class="gf_blue">登录</a></em></h1>
    	<div class="gf_list_tab cf gf_regis_borer_none">
            	<dl class="cf left gf_tab_off gf_tab_on ">
                    <dd><a href="#gf_list_title1">用户注册</a></dd>
                </dl>
                <dl class="cf left gf_tab_off">
                    <dd><a href="#gf_list_title2">机构注册</a></dd>
                </dl>
        </div>
        <div class="gf_register_tab">
        	<ul class="gf_regis_ul1 gf_tab_lmx1 gf_on">
                <li class="gf_regis_li1 gf_on">
                    <div class="gf_reg_img ">
                        <img src="images/bg/reg_img1.png"/>
                        <ul class="gf_reg_num cf">
                            <li class="gf_on">填写注册信息</li>
                            <li>验证账号信息</li>
                            <li>注册成功</li>
                        </ul>
                    </div>
                    <div class="gf_regis_imform ">
                        <form id="form1"> 
                            <dl class="cf">
                                <dt><span>*</span>电子邮箱:</dt>
                                <!--input class="suc"  验证成功-->
                                <!--input class="err"  验证失败-->
                                <dd>
                                	<input id="emailDevlpoer" class="validate[required,custom[email]]" name="email" type="text" placeholder="电子邮箱作为用户名" data-prompt-target='handle_email' data-prompt-position='inline' />
                                    <span id="handle_email" class="import_tip"></span>
                                </dd>
                            </dl>
                            <dl class="cf gf_pass_leval">
                                <dt><span>*</span>登录密码:</dt>
                                <dd>
                                	<input class="validate[required,minSize[6],maxSize[16]]" type="password" name="password" id="pwd1" data-prompt-target='handle_psw' data-prompt-position='inline'/>
                                    <span id="handle_psw" class="import_tip"></span>
                                </dd>
                                <!-- div class="" 弱-->
                                <!-- div class="gf_leval2" 中-->
                                <!-- div class="gf_leval3" 强-->
                                <div id="pwd1_l">
                                    <em>弱</em><span>中</span><i>强</i>
                                </div>
                            </dl>
                            <dl class="cf">
                                <dt><span>*</span>确认登录密码:</dt>
                                <dd>
                                	<input class="validate[required,minSize[6],maxSize[16]]" type="password" name="repassword" id="pwd1_1" data-prompt-target='handle_psw2' data-prompt-position='inline'/>
                                    <span id="handle_psw2" class="import_tip"></span>
                                </dd>
                            </dl>
                            <dl class="cf gf_reg_btn">
                                <dt></dt>
                                <dd><input id="submit_btn1" type="submit" value="立即注册" class="gf_regis_user_btn"/></dd>
                            </dl>
                            <dl class="cf gf_reg_xieyi">
                                <dt></dt>
                                <!-- <dd><a href="#">《Rcloud云平台网站协议》</a></dd> -->
                            </dl>
                       </form>
                    </div>
                </li>
                <li class="gf_regis_li2">
                    <div class="gf_reg_img gf_padd_b34">
                        <img src="images/bg/reg_img2.png"/>
                        <ul class="gf_reg_num cf">
                            <li>填写注册信息</li>
                            <li  class="gf_on">验证账号信息</li>
                            <li>注册成功</li>
                        </ul>
                    </div>
                    <div  class="gf_regis_imform gf_padd_bottom150">
                    	<form id="form1_2">
	                        <p>验证码已发送至你的邮箱，请登录您的邮箱确认</p>
	                        <dl class="cf">
	                            <dt><span>*</span>验证码:</dt>
	                            <!--input class="suc"  验证成功-->
	                            <!--input class="err"  验证失败-->
	                            <dd><input class="validate[required]" id="code1" name="emailcode" type="text" placeholder="" data-prompt-target='handle_yzm' data-prompt-position='inline'/><!--<b>不能为空</b>--><span id="handle_yzm" class="import_tip" ></span></dd>
	                         </dl>
	                         <dl class="cf gf_reg_btn">
	                            <dt></dt>
	                            <dd><input id="submit1" type="submit" value="点击下一步" class="gf_regis_btn2"/></dd>
	                        </dl>
                        </form>
                    </div>
                </li>
                <li class="gf_regis_li3">
                    <div class="gf_reg_img gf_padd_b34">
                        <img src="images/bg/reg_img3.png"/>
                        <ul class="gf_reg_num cf">
                            <li>填写注册信息</li>
                            <li>验证账号信息</li>
                            <li  class="gf_on">注册成功</li>
                        </ul>
                    </div>
                    <div  class="gf_regis_imform gf_padd_bottom150">
                        <p >您已成功注册，跳转至<a  class="gf_col_red" href="login.jsp">登录</a>页面</p>
                    </div>	
                </li>
            </ul>
            <ul class="gf_regis_ul1 gf_tab_lmx2">
            	<li class="gf_regis_li1 gf_on">
                    <div class="gf_reg_img gf_padd_b34">
                        <img src="images/bg/reg_img1.png"/>
                        <ul class="gf_reg_num cf">
                            <li class="gf_on">填写注册信息</li>
                            <li>验证账号信息</li>
                            <li>提交审批</li>
                        </ul>
                    </div>
                    <div class="gf_regis_imform ">
                        <form id="form2">
                            <dl class="cf">
                                <dt class="gf_regis_t_lmx">机构信息</dt>
                            </dl>
                            <dl class="cf">
                                <dt><span>*</span>机构名称:</dt>
                                <!--input class="suc"  验证成功-->
                                <!--input class="err"  验证失败-->
                                <dd>
                                	<input class="validate[required,maxSize[30],custom[orgName]]" name="orgname"  type="text" placeholder="" data-prompt-target='handle_name' data-prompt-position='inline'/>
                                    <span id="handle_name" class="import_tip" ></span>
                                </dd>
                               
                            </dl>
                            <dl class="cf">
                                <dt><span>*</span>机构描述:</dt>
                                <!--input class="suc"  验证成功-->
                                <!--input class="err"  验证失败-->
                                <dd>
                                	<input class="validate[required]" name="orgdesc"  type="text" placeholder="" data-prompt-target='handle_des' data-prompt-position='inline'/>
                                    <span id="handle_des" class="import_tip" ></span>
                                </dd>
                               
                            </dl>
                            <dl class="cf">
                                <dt class="gf_regis_t_lmx">机构管理员信息</dt>
                            </dl>
                            <dl class="cf">
                                <dt><span>*</span>电子邮箱:</dt>
                                <!--input class="suc"  验证成功-->
                                <!--input class="err"  验证失败-->
                                <dd>
                                	<input id="emailOrg" class="validate[required,custom[email]]" name="email"  type="text" placeholder="" data-prompt-target='handle_email2' data-prompt-position='inline'/>
                                     <span id="handle_email2" class="import_tip" ></span>
                                </dd>
                               
                            </dl>
                            <dl class="cf gf_pass_leval">
                                <dt><span>*</span>登录密码:</dt>
                                <dd>
                                	<input class="validate[required,minSize[6],maxSize[16]]" name="password"  type="password" id="pwd2" data-prompt-target='handle_c_psw' data-prompt-position='inline' />
                                    <span id="handle_c_psw" class="import_tip" ></span>
                                </dd>
                                <!-- div class="" 弱-->
                                <!-- div class="gf_leval2" 中-->
                                <!-- div class="gf_leval3" 强-->
                                <div id="pwd2_l">
                                    <em>弱</em><span>中</span><i>强</i>
                                </div>
                            </dl>
                            <dl class="cf">
                                <dt><span>*</span>确认登录密码:</dt>
                                <dd>
                                	<input class="validate[required,minSize[6],maxSize[16]]" name="repassword" type="password" id="pwd2_1" data-prompt-target='handle_c_psw2' data-prompt-position='inline'/>
                                    <span id="handle_c_psw2" class="import_tip"></span>
                                </dd>
                            </dl>
                            <dl class="cf gf_reg_btn">
                                <dt></dt>
                                <dd><input id="submit2" type="submit" value="立即注册" class="gf_regis_corp_btn"/></dd>
                            </dl>
                            <dl class="cf gf_reg_xieyi">
                                <dt></dt>
                                <!-- dd><a href="#">《Rcloud云平台网站协议》</a></dd -->
                            </dl>
                        </form>
                    </div>
                </li>
                <li class="gf_regis_li2">
                    <div class="gf_reg_img gf_padd_b34">
                        <img src="images/bg/reg_img2.png"/>
                        <ul class="gf_reg_num cf">
                            <li>填写注册信息</li>
                            <li  class="gf_on">验证账号信息</li>
                            <li>提交审批</li>
                        </ul>
                    </div>
                    <div  class="gf_regis_imform gf_padd_bottom150">
                    	<form id="form2_2">
	                        <p>验证码已发送至你的邮箱，请登录您的邮箱确认</p>
	                        <dl class="cf">
	                            <dt><span>*</span>验证码:</dt>
	                            <!--input class="suc"  验证成功-->
	                            <!--input class="err"  验证失败-->
	                            <dd>
                                	<input id="code2" class="validate[required]" type="text" placeholder="" data-prompt-target='handle_c_yzm' data-prompt-position='inline' />
                                    <span id="handle_c_yzm" class="import_tip"></span>
                                </dd>
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
                            <li>填写注册信息</li>
                            <li>验证账号信息</li>
                            <li  class="gf_on">提交审批</li>
                        </ul>
                    </div>
                    <div  class="gf_regis_imform gf_padd_bottom150">
                        <p >您的注册信息已经提交审批，请耐心等待!</p>
                    </div>	
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="gf_footer">
<%@ include file="../include/home_buttom.jsp" %>
</div>
<!--content end-->

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

<div class="modal fade" id="waitModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
      
        <!-- <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button> -->
        <h4 class="modal-title" id="myModalLabel">提示</h4>
      </div>
      <div class="modal-body">
        	<img alt="正在提交，请稍等" src="images/loading.gif">正在提交，请稍等.......
      </div>
      <!-- <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div> -->
    </div>
  </div>
</div>

</body>
</html>
