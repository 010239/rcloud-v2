<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../../../static/include/default.jsp" %>
<script type="text/javascript" src="${ctx}/static/lib/js/jquery.placeholder.js"></script>
<script type="text/javascript" src="${ctx}/static/extends/js/common/common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>应用发布</title>
<%-- <script src="${ctx}/static/extends/js/jquery_v1.10.2_min.js"></script> --%>
<script type="text/javascript" src="${ctx}/static/extends/js/common/ajaxfileupload.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
	var currID = "store";
	
</script>
</head>
<body>
<%@ include file="../include/top.jsp" %>
<%@ include file="../include/user_top.jsp" %>
<!--content start-->
<div class="gf_regist_warp">
	<div class="gf_reg_content">
    	<h3 class="gf_yyfb_t">应用发布</h3>
    	<form action="" id="uri-form">
    		<div id="firs-step" class="gf_yyfb_stepsbox gf_stepsbox_show">
	            <div class="gf_reg_img">
	                <img src="${ctx}/static/extends/images/bg/reg_img1.png"/>
	                <ul class="gf_reg_num cf">
	                    <li class="gf_on">部署测试应用</li>
	                    <li>填写发布信息</li>
	                    <li>审批</li>
	                </ul>
	            </div>
	            <div class="gf_regis_imform gf_yyfb_steps">
	            <dl class="cf">
	                    <dt>部署类型：</dt>
	                    <dd> 
	                     <select class="app_sel" id="publicType" onchange="publishTypeChange()" name="deployType">
		                      <option value="1" selected="selected">后部署</option>
		                      <option value="0">预部署</option>
		                      <option value="3">外部应用</option>
		                    </select>
	                   <input id="uri-input" type="hidden" placeholder="" name="subDomain"/>
	                    </dd>
	                </dl>
	            
	                <div id="divbefore" style="display: none;">
	                <dl class="cf">
	                    <dt>测试应用访问地址：</dt>
	                    <dd>
	                    <input id="uri-inputbefore" type="text" placeholder="例如：http://www.baidu.com" name="subDomain" data-rule-required="true" data-msg-required="请输入访问地址" data-rule-url="true" data-msg-url="请输入正确的访问地址"/></dd>
	                </dl>
	                </div>
	                 <div id="divout" style="display: none;">
	                <dl class="cf">
	                    <dt>外部访问地址：</dt>
	                    <dd><input id="uri-inputout" type="text" placeholder="例如：http://www.baidu.com" name="subDomain" data-rule-required="true" data-msg-required="请输入访问地址" data-rule-url="true" data-msg-url="请输入正确的访问地址"/></dd>
	                </dl>
	                </div>
	                    <div id="divafter">
	                       <dl class="cf">
	                   <dd class='one'>
	                    <span>
		                     <input type="checkbox" id="isallowDownload" onchange="allowDownloadAPP()"/>
		                                                                         允许下载应用包
		                    <input type="hidden" id="isallow" value="0"/>
	                    </span>
	                    <span class="last">
		                   
	                    </span>
	                    </dd>
	                </dl>
	               <dl class="cf">
	                    <dt>测试应用访问地址：</dt>
	                    <dd><input id="uri-inputafter" type="text" placeholder="例如：http://www.baidu.com" name="subDomain" data-rule-url="true" data-msg-url="请输入正确的访问地址"/></dd>
	                </dl>
	                    
	                 <dl class="cf">
	                 <form id="warformid" name="warformid" enctype="multipart/form-data" method="post">
	                 
	                    <dt>上传应用包：</dt>
	                    <dd class="up_dd">
	                    	<!--<input type="file" placeholder="上传。war包" id="file" name="file" data-rule-required="true"/> -->
	                    	<input id="fileurl" type="text" readonly="readonly"/>
	                    	<span class="file_bg" >浏览<input type="file" class="file" id="file" name="file"  onchange="retvalue()"/></span><a href="javascript:uploadsubwar()" class="up_link" id="submitwarid">上传 </a>
	                    	
	                    	<input type="hidden" id="filetypes"/>
	                   
	                    </dd>
	                    <input type="hidden" id="isWarSu" value="ss"/>
	                    
	                    
	              </form>
	                </dl>
	                </div>
	                <dl class="cf gf_reg_btn">
	                    <dt></dt>
	                    <dd><input id="first-button" class="gf_yyfb_next1" type="button" value="下一步"/></dd>
	                </dl>
	            </div>
	        </div>
	    </form>
	    <form id="publish_application_form" action="${ctx}/rest/applicationpublish/insertApplication">
	    	<div id="second-step" class="gf_yyfb_stepsbox">
	            <div class="gf_reg_img">
	                <img src="${ctx}/static/extends/images/bg/reg_img2.png"/>
	                <ul class="gf_reg_num cf">
	                    <li class="gf_on">部署测试应用</li>
	                    <li class="gf_on">填写发布信息</li>
	                    <li>审批</li>
	                </ul>
	            </div>
	            <div class="gf_regis_imform gf_yyfb_steps">
	            
	            	
	            	
	                <dl class="cf">
	                    <dt>应用名称：</dt>
	                    <dd><input type="text" placeholder="例如：政务大厅" name="chname" data-rule-required="true" data-msg-required="请输入应用名称" maxlength="8"/>
	                    	<input id="uri-value" type="hidden" name="subDomain" >
	                    <input type="hidden" id="isWebs" name="allowdownload" />
	            	<input type="hidden" id="deployTypes" name="deployType"/>
	            	<input type="hidden" id="filewarTypes" name="filewarTypes"/>
	                    
	                    </dd>
	                </dl>
	                <dl class="cf">
	                    <dt>应用简述：</dt>
	                    <dd><textarea placeholder="例如：政府办事门户" class="gf_yyfb_tarea" name="briefDescription"></textarea></dd>
	                </dl>
	                <dl class="cf">
	                    <dt>应用类型：</dt>
	                    <dd><select id="application-type" class="order_sel" name="applicationType"></select></dd>
	                </dl>
	                <dl class="cf"> 
	                    <dt>应用描述：</dt>
	                    <dd><textarea placeholder="例如：政府办事门户" class="gf_yyfb_tarea" name="detailDescription"></textarea></dd>
	                </dl>
	                <dl class="cf">
	                    <dt>服务费：</dt>
	                    <dd><input type="text" placeholder="例如：5000" name="maintenanceCosts" data-rule-required="true" data-msg-required="请输入服务费" data-rule-money="true" data-msg-money="请输入正确的服务费【小数点前8位后2位】"/></dd>
	                </dl>
	                <dl class="cf">
	                    <dt>联系人：</dt>
	                    <dd><input type="text" placeholder="例如：张三" name="contactPerson" data-rule-required="true" data-msg-required="请输入联系人" maxlength="15"/></dd>
	                </dl>
	                <dl class="cf">
	                    <dt>联系电话：</dt>
	                    <dd><input type="text" placeholder="例如：010-123456789" name="contactPhone" data-rule-required="true" data-msg-required="请输入联系电话" data-rule-phone="true"  data-msg-phone="请输入正确的联系电话"/></dd>
	                </dl>
	               <dl class="cf"> 
	                    <dt>申请上架描述：</dt>
	                    <dd><textarea placeholder="例如：上架描述" class="gf_yyfb_tarea" name="applyExplanation" data-rule-required="true" data-msg-required="请输入申请信息"></textarea></dd>
	                </dl>
	                <dl class="cf gf_reg_btn">
	                    <dt></dt>
	                    <dd><input id="submit_button" class="gf_yyfb_next1" type="button" value="保存"/></dd>
	                </dl>
	            </div>
	        </div>
	        <div id="third-step" class="gf_yyfb_stepsbox">
	            <div class="gf_reg_img">
	                <img src="${ctx}/static/extends/images/bg/reg_img3.png"/>
	                <ul class="gf_reg_num cf">
	                    <li class="gf_on">部署测试应用</li>
	                    <li class="gf_on">填写发布信息</li>
	                    <li class="gf_on">审批</li>
	                </ul>
	            </div>
	            <div class="gf_regis_imform gf_yyfb_steps">
	                <p class="gf_yyfb_3sp">已提交运营管理员审批，请耐心等待！</p>
	            </div>
	        </div>
    	</form>
        
    </div>
</div>
<div class="gf_footer">
    <div class="gf_company_imform">
    	<p>版权所有 © 2013-2014 中软国际公司   京ICP备14011895号-1</p>
        <p>电话：+86 010 888482388 转 6326 传真：+86 010 8286 2809 邮箱：rcloud_support@chinasofti.com</p>
    </div>
</div>
<!--content end-->
<script type="text/javascript" src="${ctx}/static/extends/js/apply_publish.js"></script>
</body>
</html>
