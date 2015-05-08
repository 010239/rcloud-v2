<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>支付中心</title>
<script type="text/javascript">
	var ctx = "${ctx}";
	var currID = '<%=request.getSession().getAttribute("userPay.menu")%>'; 
</script>
<%@ include file="../../static/include/default.jsp" %>
<script type="text/javascript" src="${ctx}/static/extends/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/static/lib/js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/lib/js/jquery.validationEngine.min.js"></script>
<script type="text/javascript" src="${ctx}/static/lib/js/jquery.placeholder.js"></script>
</head>
<body class="gf_ci">
<%@ include file="include/top.jsp" %>
<%@ include file="include/user_top.jsp" %>
<form class="gf_pay_form gf_ci_pw gf_pay_mount" id="rechargeMoneyFormIDzz">
 <input id="applicationId" type="hidden" name="applicationId" value="${applicationId}"/> 
 <input id="amountmoneyid" type="hidden"  value="${moneyAmount}"/> 
<input id="maintenanceCosts" type="hidden" />
    <h3 class="gf_ci_mt24 gf_ci_bg gf_ci_mng">
        <span>支付金额</span>
        <input class="easyui-numberbox" type="text"  name="moneyAmount" id="moneyAmount" value="${maintenanceCosts}" readonly="readonly" data-options="required:true,min:0,precision:0,novalidate:true" style="border: 1px solid #f00;border-radius: 5px;height: 32px;margin: 0 10px;text-indent: 5px; width: 200px;"/>
        
        
        <span>元</span>&nbsp;&nbsp;<span id="msg2"></span>
    </h3>

<div class="gf_ci_pw gf_pay_h2"><h2>选择支付方式</h2></div>

    <dl class="gf_ci_bg gf_ci_mng">
        <dt class="cf js_ci_dt gf_pay_bank">
            <span>网银支付</span>
            <i class="gf_pay_i gf_sprite right off"></i>
        </dt>
        <dd>
            <ul class="cf gf_pay_ul">
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="nong_ye" />
                        <img class="gf_pay_pic" src="${ctx}/static/extends/images/upload/zgny.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="gong_shang" />
                        <img class="gf_pay_pic" src="${ctx}/static/extends/images/upload/gsyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="zhao_shang" />
                        <img class="gf_pay_pic" src="${ctx}/static/extends/images/upload/zsyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="zhong_guo" />
                        <img class="gf_pay_pic" src="${ctx}/static/extends/images/upload/zgyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="min_sheng" />
                        <img class="gf_pay_pic" src="${ctx}/static/extends/images/upload/msyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="xing_ye" />
                        <img class="gf_pay_pic" src="${ctx}/static/extends/images/upload/xyyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="jiao_tong" />
                        <img class="gf_pay_pic" src="${ctx}/static/extends/images/upload/jtyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="guang_fa" />
                        <img class="gf_pay_pic" src="${ctx}/static/extends/images/upload/gfyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="pu_fa" />
                        <img class="gf_pay_pic" src="${ctx}/static/extends/images/upload/pfyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="ping_an" />
                        <img class="gf_pay_pic" src="${ctx}/static/extends/images/upload/zgpa.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="wen_zhou" />
                        <img class="gf_pay_pic" src="${ctx}/static/extends/images/upload/wzyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="fu_dian" />
                        <img class="gf_pay_pic" src="${ctx}/static/extends/images/upload/fdyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="ning_bo" />
                        <img class="gf_pay_pic" src="${ctx}/static/extends/images/upload/nbyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="hang_zhou" />
                        <img class="gf_pay_pic" src="${ctx}/static/extends/images/upload/hzyn.png" alt="" />
                    </label>
                </li>
            </ul>
        </dd>
    </dl>
    <dl class="gf_ci_mt24 gf_ci_bg gf_ci_mng">
        <dt class="cf gf_idx_pr">
            <input class="gf_pay_next gf_idx_pa" type="button" value="下一步" id="submitzhifuid"/>
        </dt>
    </dl>
</form>
 
 
 <%@ include file="../../static/include/raeDeployment.jsp" %>
   
<div class="gf_footer gf_ci_mt24">
    <div class="gf_company_imform">
        <p>版权所有 © 2013-2014 中软国际公司   京ICP备14011895号-1</p>
        <p>电话：+86 010 888482388 转 6326 传真：+86 010 8286 2809 邮箱：rcloud_support@chinasofti.com</p>
    </div>
</div>
<%-- <script src="${ctx }/static/extends/js/payment.js"></script> --%>
<script src="${ctx }/static/extends/js/individual_center.js"></script>
<script src="${ctx }/static/extends/js/defray.js"></script>

</body>
</html> 

