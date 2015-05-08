<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<%@ include file="../../../static/include/default.jsp" %>
<script type="text/javascript" src="${ctx}/static/lib/js/jquery.placeholder.js"></script>
<script type="text/javascript" src="${ctx}/static/extends/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/static/extends/js/common/ajaxfileupload.js"></script>
<script type="text/javascript">
var currID = "store";
</script>
<title>个人中心</title>
<script type="text/javascript">
	var ctx = "${ctx}";
</script>

<style type="text/css">
	.dg-button {
		margin-right: 10px;
	}
	
	.app_mod_info, .update_app_mod_info {
		position: fixed;
		background: #fff;
		font-size: 14px;
		padding-bottom: 30px;
		display: none;
	}
	
	.gf_yyfb_tarea {
		margin-top: 10px;
	}
	
	.app_input {
		height: 30px;
		line-height: 30px;
		width: 298px;
		border: 1px solid #7b8793;
		border-radius: 4px;
		background: none;
		padding: 0 4px;
	}
	/*
	.gf_ci_pw{
		width:100%;
		padding:0 20px;
	}
	*/
	.app_buy_order_info,.app_sale_order_info, .ser_order_info {
			display: block !important;
			background: #fff;
			font-size: 14px;
	}
	.app_buy_order_info dl, .app_sale_order_info dl, .ser_order_info dl {
			border:0px;
		}
	.gf_ci_bg{border:solid 1px #ccc;padding-bottom:10px;}
</style>
</head>
<body>
<%@ include file="../include/top.jsp" %>
<%@ include file="../include/user_top.jsp" %>
<dl class="gf_ci_order gf_ci_pw gf_ci_mt24 gf_ci_bg gf_ci_mng">
    <dt class="cf">
        <span class="left">我的应用</span>
        <a class="gf_pub_newapp" style="margin-top:10px;margin-left:625px" href="${ctx}/page/application/publish">应用上架</a>
        <a class="gf_pub_newapp" style="margin-top:10px;margin-left:10px" href="javascript:applicationOff();">应用下架</a>
        <i class="gf_sprite right on js_ci_dt"></i>
    </dt>
    <dd>
        <dl class="gf_cd_dddt cf">
            <dt class="cf">
                <span class="first left">已发布应用</span>
                <span class="left off">已经购买应用</span>
            </dt>
            <dd class="gf_ci_sorder">
                <table id="publish-application-dg"></table>
            </dd>
            <dd class="panel-noscroll" id="buy" style="display:none;">
                <table id="buy-application-dg"></table>
            </dd>
        </dl>
    </dd>
</dl>

<div class="gf_ci_ifr gf_ci_pw gf_ci_mt24">
    <iframe class="gf_iframe" width="100%" frameborder="0" name="gf_ici" src="" scrolling="no"></iframe>
</div>
<div class="gf_footer gf_ci_mt24">
<%@ include file="../../../static/include/buttom.jsp" %>
</div>
<!-- 弹层 -->
<div class="gf_ci_mask"></div>
<!-- 应用详情 -->
<form class="app_mod_info gf_ci_pw" action="">
    <dl>
        <dt class="gf_info_dt1">应用详情</dt>
        <dd class="gf_info_dd1">
            <dl>
                <dd>
                    <p>
                        <span>应用名称：</span>
                        <span id="app-name"></span>
                    </p>
                    <p>
                        <span>应用描述：</span>
                        <span id="app-description"></span>
                    </p>
                    <p>
                        <span>试用地址：</span>
                        <span id="app-address"></span>
                    </p>
                    <p>
                        <span>运维费用：</span>
                        <span id="app-maintenance"></span>
                    </p>
                    <p>
                        <span>联系人：</span>
                        <span id="app-concat-person"></span>
                    </p>
                    <p>
                        <span>联系电话：</span>
                        <span id="app-phone"></span>
                    </p>
                    <p id="off-shevlve-description">
                    </p>
                    <p class="gf_ib_wrap"><input id="app-detail-button" class="gf_info_btn" type="button" value="关闭" /></p>
                </dd>
            </dl>
        </dd>
        
    </dl>
</form>
<!-- 修改应用 -->

<form id="update_app_form" class="update_app_mod_info gf_ci_pw" action="">
    <dl>
        <dt class="gf_info_dt1">应用详情</dt>
        <dd class="gf_info_dd1 gf_info_pd_0">
            <dl>
                <dd class="cf">
                    <p>
                        <span>应用名称：</span>
                        <em class="check_e"><input type="text" name="chname" class="app_input" id="applicationName"  data-rule-required="true" data-msg-required="请输入应用名称"/></em>
                        <input type="hidden" name="applicationId" id="applicationId"/>
                        <input id="uri-value" type="hidden" name="subDomain" >
                             <input type="hidden" id="isWarSu" value="ss" name="isWarSu"/>
                    </p>
                    <p>  
                   <!--    <span>试用地址：</span>
                        <input type="text"  class="app_input" id="subDomain"/>  -->
                      <span>应用版本：</span>
                      <em class="check_e">  <input type="text"  class="app_input" id="appVersion" readonly="readonly"/> </em>
                            
                    </p>
                 </dd>  
                 <dd class="cf">
                    <p>
                        <span>应用简述：</span>
                     <em class="check_e">   <textarea class="gf_yyfb_tarea" name="briefDescription" id="briefDescription" placeholder="例如：应用简述"></textarea></em>
                    </p>
                    <p>
                        <span>应用描述：</span>
                   <em class="check_e">     <textarea class="gf_yyfb_tarea" name="detailDescription" id="detailDescription" placeholder="例如：应用描述"></textarea></em>
                    </p>
                  </dd>
                  <dd class="cf">
                    <p>
                        <span>运维费用：</span>
                   <em class="check_e">     <input type="text" name="maintenanceCosts" class="app_input" id="maintenanceCosts" data-rule-required="true" data-msg-required="请输入运维费用" data-rule-money="true" data-msg-money="请输入正确的运维费用[小数点前8位后2位]"/></em>
                    </p>
                    <p>
                        <span>联系人：</span>
                   <em class="check_e">     <input type="text" name="contactPerson" class="app_input" id="contactPerson" placeholder="例如：张三" data-rule-required="true" data-msg-required="请输入联系人"/></em>
                    </p>
                   </dd>
                   <dd class="cf">
                    <p>
                        <span>联系电话：</span>
                 <em class="check_e">       <input type="text" name="contactPhone" class="app_input" id="contactPhone" placeholder="例如：010-88888888" data-rule-required="true"  data-msg-required="请输入固定电话" data-rule-phone="true"  data-msg-phone="请输入正确的固定电话号码"/></em>
                    </p>
                    <p>
                        <span>部署类型：</span>
                     <em class="check_e">   <select class="app_input" id="publicType" onchange="publishTypeChange()" name="deployType">
		                      <option value="1">后部署</option>
		                      <option value="0">预部署</option>
		                      <option value="3">外部应用</option>
		                </select></em>
	                   <input id="uri-input" type="hidden" />
	                   <input id="beforepublicType" type="hidden"  name="beforepublicType"/>
	                   <input id="applicationstatus" type="hidden"/>
	                   
                    </p>
                   </dd>
               
                    <dd class="cf">
                      <div id="divbefore" style="display: none;">
                      <p>
	                    <span>测试应用访问地址： </span>
	                 <em class="check_e">   <input id="uri-inputbefore" class="app_input" type="text" placeholder="例如：http://www.baidu.com" data-rule-required="true" data-msg-required="请输入访问地址" data-rule-url="true" data-msg-url="请输入正确的访问地址"/></em>
	               </p>
	                </div>
	                </dd>
	                 <dd class="cf">
	                 <div id="divout" style="display: none;">
	                 <p>
	                  <span>外部访问地址：</span>
	                 <em class="check_e">  <input id="uri-inputout" class="app_input" type="text" placeholder="例如：http://www.baidu.com" data-rule-required="true" data-msg-required="请输入访问地址" data-rule-url="true" data-msg-url="请输入正确的访问地址"/></em>
	             </p>
	                </div>
	                </dd>
                  
                 <dd class="cf">
	                <div id="divafter"  >
	                    <p>    
	                      <span>测试应用访问地址： </span>
	                 <em class="check_e">	  <input class="app_input" id="uri-inputafter" type="text" placeholder="例如：http://www.baidu.com" data-rule-url="true" data-msg-url="请输入正确的访问地址"/></em>
	                 	</p>
	                 	<p>
	                 	    <em class="tag_e">
			                     <input type="checkbox" id="isallowDownload" onchange="allowDownloadAPP()"/>
			                                                                         允许下载应用包
			                    <input type="hidden" id="isallow"  name="allowdownload"/>
		                    </em>
	                    </p>
	                    
	                   <!--  <form id="warformid" name="warformid" enctype="multipart/form-data" method="post" > -->
	                  		<dl>
		                    <dd class="up_dd">
		                    <p class="w_100">
		                        <span>上传应用包： </span>
		                    	<input class="app_input" id="fileurl" type="text"  readonly="readonly"/>
		                    	<input class="app_input" id="baseApp" type="hidden" name="baseApp"/>
		                    	<span class="file_bg" >浏览<input type="file" class="file" id="file" name="file"  onchange="retvalue()"/></span><a href="javascript:uploadsubwar()" class="up_link" id="submitwarid">上传 </a>
		                   </p>
		                    </dd>
		                    </dl>
		               
	                    
	              		<!-- </form> -->
	                    
	                 </div> 
	                </dd>
	                 <dd class="cf"> 
						     <input id="applydescapp" type="hidden" name="applyDesc"/>
						 <div id="shenhe" style="display: none">
						 <p >
	                        <span>审核描述：</span>
	                       <em class="check_e"> <textarea id="auditExplanation" class="gf_yyfb_tarea"  readonly="readonly" disabled="disabled"></textarea></em>
	                       </p>
	                        <p >
	                        <span>申请说明：</span>
	                    <em class="check_e">    <textarea id="applyExplanation" class="gf_yyfb_tarea"  data-rule-required="true"  data-msg-required="请输入申请说明"></textarea></em>
	                       </p>
	                    </div>
	                     <div id="xiajia" style="display: none">
	                     <p>
	                        <span>下架描述：</span>
	                         <em class="check_e"> <textarea id="offshdesc" class="gf_yyfb_tarea"   readonly="readonly" disabled="disabled"></textarea></em>
	                      </p>
	                         <p >
	                        <span>申请说明：</span>
	                    <em class="check_e">    <textarea id="applyexpid" class="gf_yyfb_tarea"  data-rule-required="true"  data-msg-required="请输入申请说明"></textarea></em>
	                       </p>
	                    </div>
	                
                    </dd>
                    
                    <p class="gf_ib_wrap"><input id="app-update-button" class="gf_info_btn" type="button" value="提交" />&nbsp;&nbsp;&nbsp;
                    <input id="app-close-button" class="gf_info_btn" type="button" value="关闭" /></p>
                </dd>
            </dl>
     
</form>
<!-- 购买应用详情 -->
<div class="modal fade" id="buyAppInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="width:900px;">
    <div class="modal-content">
		<div class="app_buy_order_info  gf_ci_pw" >
			<dl class="gf_ci_app gf_ci_pw  gf_ci_bg gf_ci_mng">
		    <dt>
		        <span class="left">云应用订单详情-购买应用</span>
		    </dt>
		    <dd class="order_dd">
		    	<div class="import">
		            <div class="w_50">
		                <span>订单号：</span>
		                <span id ="app-orderNumber"></span>
		            </div>
		            <div class="w_50">
		                <span>云应用：</span>
		                <span id ="app-applicationName"></span>
		            </div>
		        </div>
		        <div class="import">
		            <div class="w_50">
		                <span>卖方：</span>
		                <span id ="app-userName"></span>
		            </div>
		            <div class="w_50">
		                <span>状态：</span>
		                <span id ="app-orderStatus"></span>
		            </div>
		        </div>
		        <div class="import">
		            <div class="w_50">
		                <span>服务费：</span>
		                <span id ="app-maintenanceCosts"></span>
		            </div>
		            <div class="w_50">
		                <span>截止日期：</span>
		                <span id ="app-endTime"></span>
		            </div>
		        </div>
		        <div class="import">
		            <div class="w_50">
		                <span>签单日期：</span>
		                <span id ="app-createdTime"></span>
		            </div>
		        </div>      
		        <div class="import cf">
		            <span>订单说明：</span>
		            <span id ="app-orderdescription" class="describe"></span>
		        </div> 
		         <p class="refer" align="center"><input id="order-info-close" class="detail-button gf_info_btn" type="button" value="关闭" /></p>   
		    </dd>
		</dl>
		</div>
    </div>
  </div>
</div>

<%@ include file="../../../static/include/raeDeployment.jsp" %>


<!-- 弹层 -->
<script src="${ctx}/static/extends/js/dateUtils.js"></script>
<script src="${ctx}/static/extends/js/individual_center.js"></script>
<script src="${ctx}/static/extends/js/wind_shop.js"></script>
</body>
</html>


