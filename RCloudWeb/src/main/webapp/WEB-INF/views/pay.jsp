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
</head>
<body class="gf_ci">
<%@ include file="include/top.jsp" %>
<%@ include file="include/user_top.jsp" %>
<form class="gf_pay_form gf_ci_pw gf_pay_mount" action="${ctx}/page/userconsole/rechargeMoney" method="post">
    <h3 class="gf_ci_mt24 gf_ci_bg gf_ci_mng">
        <span>支付金额</span>
        <input class="easyui-numberbox" type="text"  name="moneyAmount" id="moneyAmount" data-options="required:true,min:0,precision:0,novalidate:true" style="border: 1px solid #f00;border-radius: 5px;height: 32px;margin: 0 10px;text-indent: 5px; width: 200px;"/>
        
        
        <span>元</span>
    </h3>
<!-- </form> -->
<div class="gf_ci_pw gf_pay_h2"><h2>选择支付方式</h2></div>
<!-- <form class="gf_pay_form gf_ci_pw" action="" method="post"> -->
    <!-- h3 class="gf_ci_mt24 gf_ci_bg gf_ci_mng">平台支付</h3>
    <ul class="cf gf_pay_ul gf_pay_bor">
        <li>
            <label>
                <i class="left gf_sprite on"></i>
                <input type="radio" name="gf_pay" value="cai_fu_tong" />
                <img class="gf_pay_pic" src="${ctx}/static/extends/images/upload/cft.png" alt="" />
            </label>
        </li>
        <li>
            <label>
                <i class="left gf_sprite"></i>
                <input type="radio" name="gf_pay" value="zhi_fu_bao" />
                <img class="gf_pay_pic" src="${ctx}/static/extends/images/upload/zfb.png" alt="" />
            </label>
        </li>
        <li>
            <label>
                <i class="left gf_sprite"></i>
                <input type="radio" name="gf_pay" value="yin_lian" />
                <img class="gf_pay_pic" src="${ctx}/static/extends/images/upload/yl.png" alt="" />
            </label>
        </li>
    </ul -->
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
            <input class="gf_pay_next gf_idx_pa" type="submit" value="下一步" />
        </dt>
    </dl>
</form>
<div class="gf_footer gf_ci_mt24">
    <div class="gf_company_imform">
        <p>版权所有 © 2013-2014 中软国际公司   京ICP备14011895号-1</p>
        <p>电话：+86 010 888482388 转 6326 传真：+86 010 8286 2809 邮箱：rcloud_support@chinasofti.com</p>
    </div>
</div>
<script src="${ctx }/static/extends/js/individual_center.js"></script>
<script src="${ctx }/static/extends/js/pay.js"></script>
</body>
</html>
