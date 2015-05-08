<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<!doctype html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<%@ include file="../../static/include/default.jsp" %>
<title>个人中心</title>
<script type="text/javascript">
var ctx = "${ctx}";
var currID = "home";

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
<body class="gf_ci">
<%@ include file="include/top.jsp" %>
<%@ include file="include/user_top.jsp" %>
<div class="gf_ci_pw gf_ic_neck cf gf_ci_mt24">
    <div class="gf_neck_l left">
        <div class="left gf_neck_left">
            <div class="gf_nl_top cf">
                <div class="gf_img_wrap left">
                    <a href="javascript:;">
                        <img src="${ctx}/static/extends/images/upload/gf_ci_photo.jpg" alt="photo" />
                    </a>
                </div>
                <div class="gf_nlt_right left">
                    <p class="gf_l1">HI&nbsp;,&nbsp;
                    
                    	<c:if test="${fn:length(user.realName)>5}">
							${fn:substring(user.realName, 0, 5)}...
						</c:if>
						<c:if test="${fn:length(user.realName)<=5}">
							${user.realName}
						</c:if>
             
                    </p>
                    <p>${user.email}</p>
                    <p class="cf">
                    	<a href="javascript:;" id="msg_btn">我的消息<span id="msg_num" class="msg_num">${unreadMesCount}</span></a>
                    </p>
                </div>
            </div>
            <div class="gf_nl_bot">
                <a class="gf_js_set" href="javascript:;" >修改个人资料</a>
                <span>|</span>
                <a class="gf_safe_js" href="javascript:;">修改安全信息</a>
            </div>
        </div>
        <div class="left gf_neck_right">
            <p>账户余额:</p>
            <p>
                <span>${user.money}</span>元
            </p>
            <c:if test="${permissionMap['300002'] == 1}">
	            <p class="align_c">
	               <a href="${ctx}/userPay/home"> <button> 充值</button></a>
	            </p>
            </c:if>
        </div>
    </div>
    <div class="gf_neck_r right">
        <div class="left">
            <p>
            	<c:if test="${permissionMap['300002'] == 1}">
	                <!-- <a class="gf_js_iframe" href="${ctx}/page/userOrder" target="gf_ici" id="order"> -->
	                <a class="gf_js_iframe" href="javascript:;" id="order">
	                    <img src="${ctx}/static/extends/images/gf_ci_1.jpg" alt="" />
	                </a>
                </c:if>
            	<c:if test="${permissionMap['300002'] == 0}">
	                    <img src="${ctx}/static/extends/images/order.jpg" alt="" />
                </c:if>
            </p>
            <p>
            	<span>订购管理</span>
               
            </p>
        </div>
        <div class="left">
            <p>
            	<c:if test="${permissionMap['300003'] == 1}">
	                <!-- <a class="gf_js_iframe" href="${ctx}/page/userBill" target="gf_ici" id="bill"> -->
	                <a class="gf_js_iframe" href="javascript:;" target="gf_ici" id="bill">
	                    <img src="${ctx}/static/extends/images/gf_ci_2.jpg" alt="" />
	                </a>
                </c:if>
            	<c:if test="${permissionMap['300003'] == 0}">
	                    <img src="${ctx}/static/extends/images/bill.jpg" alt="" />
                </c:if>
            </p>
            <p>
            	<span>账单管理</span>
          
            </p>
        </div>
        <div class="left">
            <p>
	         	<!-- <a class="gf_js_iframe" href="${ctx}/page/transaction/findTsatList" target="gf_ici" id="deal"> -->
	         	<a class="gf_js_iframe" href="javascript:;" target="gf_ici" id="deal">
	             	<img src="${ctx}/static/extends/images/gf_ci_4.jpg" alt="" />
	           	</a>
            </p>
            <p>
            	<span>交易管理</span>
              
            </p>
        </div>
        <div class="left">
            <p>
	         	<!-- <a class="gf_js_iframe" href="${ctx}/page/cloudservice/" target="gf_ici" id="ss"> -->
	         	<a class="gf_js_iframe" href="javascript:;" target="gf_ici" id="ss">
	            	<img src="${ctx}/static/extends/images/gf_ci_3.jpg" alt="" />
	          	</a>
            </p>
            <p>
            	<span>服务管理</span>
          
            </p>
        </div>
        <div class="left">
            <p>
            	<c:if test="${permissionMap['300001'] == 1}">
	                <!-- <a class="gf_js_iframe" href="${ctx}/page/org/spacepage" target="gf_ici" id="org"> -->
	                <a class="gf_js_iframe" href="javascript:;" target="gf_ici" id="org">
	                    <img src="${ctx}/static/extends/images/gf_ci_5.jpg" alt="" />
	                </a>
                </c:if>
            	<c:if test="${permissionMap['300001'] == 0}">
	                    <img src="${ctx}/static/extends/images/organization.jpg" alt="" />
                </c:if>
            </p>
            <p>
            	<span>机构管理</span>
                
            </p>
        </div>
    </div>
</div>
<dl class="gf_ci_order gf_ci_pw gf_ci_mt24 gf_ci_bg gf_ci_mng">
    <dt class="cf">
        <span class="left">订购管理</span>
        <i class="gf_sprite right on js_ci_dt" id="close-ordertable"></i>
    </dt>
    <dd>
        <dl class="gf_cd_dddt" id="ordertable">
            <dt class="cf">
                <span id="service_order" class="first left">云服务</span>
                <span id="app_order" class="left off">云应用</span>
            </dt>
            <dd class="gf_ci_sorder">
             <table class="gf_tab_100" id="service_order_list">
                </table>
            </dd>
            <dd class="gf_ci_aorder off">
               <table class="gf_tab_100" id="application_order_list">
                </table>
            </dd>
        </dl>
    </dd>
</dl>
<dl class="gf_ci_bill gf_ci_pw gf_ci_mt24 gf_ci_bg gf_ci_mng">
    <dt class="cf">
        <span class="left">账单管理</span>
        <i class="gf_sprite right on  js_ci_dt"></i>
    </dt>
    <dd>
        <dl class="gf_cd_dddt">
            <dt class="cf">
                <span id="service_bill" class="left first">云服务</span>
                <span id="app_bill" class="left off">云应用</span>
            </dt>
            <dd class="gf_ci_sorder">
            <table class="gf_tab_100" id="service_bill_list">
                </table>
              
            </dd>
            <dd class="gf_ci_aorder off">
            <table class="gf_tab_100" id="application_bill_list">
                </table>
            </dd>
        </dl>
    </dd>
</dl>
<!-- <dl class="gf_ci_app gf_ci_pw gf_ci_mt24 gf_ci_bg gf_ci_mng">
    <dt class="cf">
        <span>推荐应用</span>
        <i class="gf_sprite right off js_ci_dt"></i>
    </dt>
</dl> -->
<dl class="gf_ci_app gf_ci_pw gf_ci_mt24 gf_ci_bg gf_ci_mng js_ifr_bread" id='aa'>
    <dt class="cf">
        <span></span>
    </dt>
</dl>

<!-- 跨域情况下iframe高度无法自适应，换成自己页面即可 -->
<div class="gf_ci_ifr gf_ci_pw">
    <iframe id="frametemp" class="gf_iframe" width="100%" style="min-height:300px;" frameborder="0" name="gf_ici" src="" scrolling="no"></iframe>
</div>
<div class="gf_footer gf_ci_mt24">
<%@ include file="../../../static/include/buttom.jsp" %>
</div>


<!-- 修改个人资料 -->
<div class="modal fade" id="modifyUserInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width:900px;">
    <div class="modal-content">
		<form id="userInfoForm" action="${ctx}/rest/userconsole/updateUserInfo/"   method="post">
		 <input  name="userId" type="hidden" value="${user.userId}" />
		    <dl>
		        <dt class="gf_info_dt1" >基本资料</dt>
		        <dd class="gf_info_dd1">
		            <dl>
		                <dt><font color="#444444">基本信息</font></dt>
		                <dd>
		                    <p>
		                        <span><font color="#444444">真实姓名：</font></span>
		                        <input class="gf_inp_txt" id="realName" name="realName" type="text" value="${user.realName}"  placeholder="例如：张三"
		                         data-rule-required="true"  data-msg-required="请输入用户名"  data-rule-maxlength="10"  data-msg-maxlength="用户名不能超过10位字符" />
		                    </p>
		                    <p>
		                        <span><font color="#444444">单位名称：</font></span>
		                        <input class="gf_inp_txt" id="companyName" name="companyName" type="text" value="${user.companyName}" placeholder="例如：北京xx公司" data-rule-required="true"  data-msg-required="请输入单位名称" />
		                    </p>
		                </dd>
		            </dl>
		        </dd>
		        <dd class="gf_info_dd1">
		            <dl>
		                <dt><font color="#444444">企业信息</font></dt>
		                <dd>
		                    <p class="gf_is_wrap">
		                        <span><font color="#444444">所在地区：</font></span>
		                        <input class="gf_inp_txt" id="areaName" name="areaName" type="text" value="${user.areaName}" placeholder="例如：北京" data-rule-required="true"  data-msg-required="请输入所在地区"/>
		                    </p>
		                    <p>
		                        <span><font color="#444444">街道地址：</font></span>
		                        <input class="gf_inp_txt" id="streatName" name="streatName" type="text" value="${user.streatName}" placeholder="例如：xx区xx路xx号" data-rule-required="true"  data-msg-required="请输入街道地址"/>
		                    </p>
		                    <p>
		                        <span><font color="#444444">固定电话：</font></span>
		                        <input class="gf_inp_txt" id="fixedPhone"  name="fixedPhone" type="text" value="${user.fixedPhone}" placeholder="例如：010-88888888" data-rule-required="true"  data-msg-required="请输入固定电话" data-rule-phone="true"  data-msg-phone="请输入正确的固定电话号码"/>
		                       
		                    </p>
		                    <p>&nbsp;</p>
		                    <p>
		                    <input type="submit" id="userInfoSub" class="gf_info_btn" value="保存" />
		                    <button type="button" class="gf_info_btn"  id="close-user-info">关闭</button>
		                    </p>
		                    <p>&nbsp;</p>
		                </dd>
		            </dl>
		        </dd>
		    </dl>
		</form>
    </div>
  </div>
</div>
<input id="currentPage" type="text" name="currentPage"/>
<!-- 修改安全信息 -->
<div class="modal fade" id="modifySafeInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width:900px;">
    <div class="modal-content">
    
		<dl class="gf_safe">
		    <dt class="cf">
		        <span>您当前的账号安全程度</span>
		        <span class="gf_safe_bar"><em></em></span>
		        <span>安全级别</span>
		        <span class="gf_safe_green">低</span>
		        <span>建议开启安全产品</span>
		    </dt>
		    <dd class="cf gf_safe_dd">
		    
		        <h6 class="left">登陆密码</h6>
		        <p class="left gf_safe_p">安全性高的密码可以使账号更安全。建议您定期更换密码，设置一个包含字母，符号或数字中至少两项且长度超过6位的密码。</p>
		        <div class="left gf_safe_mod_wrap">
		            <span class="gf_safe_green"><i class="gf_sprite gf_safe_true"></i><strong>已设置</strong></span>
		            <a class="gf_safe_mod" href="javascript:;">修改</a>
		        </div>
		        <form  id="mmform"  class="gf_safe_form" action="${ctx}/rest/userconsole/updateUserPassword/"   method="post">
		       
		         <input  name="userId" type="hidden" value="${user.userId}" />
		            <p class="gf_safe_form_title">修改密码</p>
		            <p class="gf_safe_ipt cf"><span><font color="#444444">原密码</font></span><input id="oldPassword" type="password" data-rule-required="true"  data-msg-required="请输入原密码"/>  <span id="msgPass"></span></p>
		            <p class="gf_safe_ipt cf"><span><font color="#444444">新密码</font></span><input id="password1"  name="password" type="password" data-rule-required="true"  data-msg-required="请输入新密码" data-rule-required="true"  data-msg-required="请输入新密码"/></p>
		            <p class="gf_safe_ipt cf"><span><font color="#444444">再次输入新密码</font></span><input id="password2"  name="password2" type="password"  data-rule-required="true"  data-msg-required="请再次输入新密码" data-rule-required="true"  data-msg-required="请输入新密码"/>
		            	<span id="msg0"></span>
		            </p>
		            <p class="gf_safe_btn"><input type="submit" id="mmformSub" value="保存" />
		            <button type="button" class="gf_info_btn" id="gbmm" data-dismiss="modal">关闭</button></p>
		        </form>
		    </dd>
		    <dd class="cf gf_safe_dd">
		        <h6 class="left">手机绑定</h6>
		        <p class="left gf_safe_p">您已绑定了手机<span class="gf_safe_orange">${user.mobilePhone}</span>。[您的手机为安全手机，可以找回密码，但不能用于登陆]</p>
		        <div class="left gf_safe_mod_wrap">
		        <c:if test="${empty user.mobilePhone }">
		         <span class="gf_safe_unset"><strong>未绑定</strong></span>
		       </c:if>
		        <c:if test="${! empty user.mobilePhone  }">
				<span class="gf_safe_green"><i class="gf_sprite gf_safe_true"></i><strong>已绑定</strong></span>
				</c:if>
		            
		            <a class="gf_safe_mod" href="javascript:;">更换</a>
		        </div>
		        <form  id="sjform"  class="gf_safe_form" action="${ctx}/rest/userconsole/updateUserMobelFhone/"  method="post">
		         <input  name="userId" type="hidden" value="${user.userId}" />
		            <p class="gf_safe_form_title">绑定手机</p>
		            <p class="gf_safe_ipt cf"><span>&nbsp;</span><span id="msg1"></span></p>
		            <p class="gf_safe_ipt cf"><span><font color="#444444">手机号</font></span>
		            	<input id="mobilePhone" name="mobilePhone" type="text"  placeholder="例如：13888888888" data-rule-required="true"  data-msg-required="请输入新手机号" data-rule-mobile="true"  data-msg-mobile="请输入合法的手机"/>
		            	<a class="gf_safe_mod" href="javascript:void(0);" id="fssjyzf">发送验证码</a>
		            </p>
		            <p class="gf_safe_ipt cf"> <span><font color="#444444">验证码</font></span> <input name="authCode" type="text" data-rule-required="true"  data-msg-required="请输入验证码"/></p>
		            <p class="gf_safe_btn"><input type="submit" id="sjformSub" value="保存" />
		            <button type="button" class="gf_info_btn" id="gbsj" data-dismiss="modal">关闭</button></p>
		        </form>
		    </dd>
		    <dd class="cf gf_safe_dd">
		        <h6 class="left">绑定邮箱</h6>
		        <p class="left gf_safe_p">绑定邮箱后，您可以通过它来找回密码。建议您设置一个容易记且常用的邮箱。</p>
		        <div class="left gf_safe_mod_wrap" >
		        
		       <c:if test="${user.userEmail=='' }">
		         <span class="gf_safe_unset"><strong>未绑定</strong></span>
		       </c:if>
		        <c:if test="${user.userEmail !=''}">
				<span class="gf_safe_green"><i class="gf_sprite gf_safe_true"></i><strong>已绑定</strong></span>
				</c:if>
		            <a class="gf_safe_mod" href="javascript:;">绑定</a>
		        </div>
		        <form id="yxform" class="gf_safe_form" action="${ctx}/rest/userconsole/updateUserEmail/"  method="post">
		         <input  name="userId" type="hidden" value="${user.userId}" />
		            <p class="gf_safe_form_title">绑定邮箱</p>
		            <p class="gf_safe_ipt cf"><span><font color="#444444">邮箱</font></span>
		            <input id="userEmail"  name="userEmail" type="text" value="" placeholder="例如：xx@xx.xx" data-rule-required="true" data-rule-email="true" data-msg-required="请输入邮箱" data-msg-email="请输入合法的邮箱"/>
		            <a class="gf_safe_mod" href="javascript:void(0);" id="fsyxyzf">发送验证码</a>
		            </p>
		            <p class="gf_safe_ipt cf"> <span><font color="#444444">验证码</font></span> <input name="authCode" type="text" value="" placeholder="" data-rule-required="true"  data-msg-required="请输入验证码"/></p>
		            <p class="gf_safe_btn"><input type="submit" id="yxformSub" value="保存" />
		            <button type="button" class="gf_info_btn" id="gbyx" data-dismiss="modal">关闭</button></p>
		        </form>
		    </dd>
		</dl>
     	<div class="modal-footer">
        	<button type="button" class="gf_info_btn" data-dismiss="modal">关闭</button>
      	</div>
    </div>
  </div>
</div>
<!--修改安全信息-->

<!--个人消息列表-->
<div class="modal fade" id="msg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width:900px;">
    <div class="modal-content" style="min-height:500px;">
    	<div class="msg_top">
        	<h2>我的消息</h2>
        	   
            <p><input id="selectAll" type="checkbox" onclick="selectAll()"/>&nbsp;&nbsp;全选&nbsp;&nbsp;&nbsp;&nbsp;<input id="deid"  class="msg_btn1" type="button" value="删 除" onclick="messageDelete()"/><input id="duid" class="msg_btn2" type="button" value="标记为已读" onclick="markMessageRead()"/><input id="shuaid" class="msg_btn1" type="button" value="刷 新" onclick="resfush()"/>
      <input type="hidden" id="allmessage" name="allmessage"/>
        <select onchange="changeState()" id="seleChange">
        <option value="unmessage">--未读消息--</option>
        <option value="allmessage">--全部消息--</option>
        </select>
        </div>
        <div class="model_height">
          <div style="min-height: 280px;" id="divId">
            <table class="msg_table" id="mymessageTab" >
                <colgroup>
                    <col width="6%"/>
                    <col width="16%"/>
                    <col width="52%"/>
                    <col width="16%"/>
                    <col width="10%"/>
                </colgroup>
                <tbody>
                    
                    
                </tbody>
            </table> 
       </div>
            <div class="inform_page" id="message_pagination">
            
            </div>
        </div>
        <div class="modal-footer">
        	<button type="button" class="gf_info_btn" data-dismiss="modal" onclick="closeMsg()">关闭</button>
      	</div>
    </div>
  </div>
</div>
<!--个人消息列表-->

<!--个人消息内容-->
<div class="modal fade" id="msg_2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width:900px;">
    <div class="modal-content" style="min-height:500px;">
    	<div class="msg_top">
        	<h2>我的消息</h2>
            <p><input class="msg_btn3" type="button" value="&lt;返回消息列表"  data-dismiss="modal" /><span id="messagetitle"></span></p>
        </div>
        <div class="msg_nr">
          <div id="messageContext"></div>
        	<!-- <p>尊敬的用户您好：</p>
            <p>您的账户信息不完整，请将用户信息补充完整，不仅可以提高账户的等级，还可以获得更多特权。</p>
            <p>特权包括以下内容：</p>
            <p>专属礼包：分为每周礼包和每月礼包两种，绿钻用户享有总值100Q币以上，年费享有双倍价值200Q币礼包，</p> 
            <p>其中每月礼包则根据在炫舞的等级来按需领取。</p>
            
            <p>背景音乐：可将炫舞歌曲直接免费设为空间背景音乐；</p>
            <p>点歌特权：绿钻用户无限次点歌 ，而非绿钻每天只能免费使用一次点歌，多次点歌需要购买。</p>
            <p>新歌抢鲜：在炫舞的音乐列表中尊享绿钻新歌抢鲜特权，试听最新最炫的歌曲并且设为自己的背景音乐！</p>
            <p>社区点歌插播：在炫舞游戏内的休闲社区，可以尊享免费插播特权，并且不限次，处处彰显您尊贵的音乐特权身份</p> -->

        </div>
        
    </div>
  </div>
</div>
<!--个人消息内容-->

<!-- 弹层 -->
<script src="${ctx}/static/extends/js/individual_center.js"></script>
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

<!--满意度调查悬浮图标-->
<div class="hide_text">
    <a class="top_icon" title="返回顶部" href="javascript:void(0);"></a>
    <a class="survey_icon" title="满意度调查" href="survey" target="_blank"></a>
</div>

</body>
<script src="${ctx}/static/extends/js/dateUtils.js"></script>
<script src="${ctx}/static/extends/js/in_center.js"></script>
<script src="${ctx}/static/extends/js/individual_center.js"></script>
<script>

//回到顶部
$(function(){
	$(window).on('scroll',function(){
		if($(window).scrollTop()>0)
		{
			$('.top_icon').stop().animate({'opacity':1});
		}
		else
		{
			$('.top_icon').stop().animate({'opacity':0});
		}
	});
	$('.top_icon').off().on('click',function(){
		$('body,html').animate({'scrollTop':0},500);
	});
	
})
</script>

</html>

