<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>账单详情</title>
</head>
<body class="gf_ci">
<div class="gf_idx_topbar">
    <div class="gf_ci_pw gf_idx_ar gf_idx_pr">
        	<a class="gf_ic_user" href="javascript:;">你好，<b><span class="gf_js_lmd gf_idx_pr"><strong class="gf_for_yy">于洋</strong><em class="gf_i_tri"></em></span></b></a>
            <div class="gf_idx_pa gf_user_det gf_user_lmx">
                <div class="gf_det_top cf ">
                    <div class="left">
                        <img src="images/upload/gf_ci_photo.jpg" alt="" />
                    </div>
                    <div class="right">
                        <p>cnknife_adm@126.com</p>
                        <p>
                            <a class="gf_js_set" href="javascript:;">账户设置</a>
                            <span>|</span>
                            <a href="javascript:;">交易记录</a>
                        </p>
                    </div>
                </div>
                <div class="gf_det_mid">账户余额：<span>9999.99</span>元<a href="javascript:;">充值</a></div>
                <div class="gf_det_bot">
                    <a href="javascript:;">云服务中心</a>
                    <span>|</span>
                    <a href="javascript:;">订单管理</a>
                    <span>|</span>
                    <a href="javascript:;">其他链接</a>
                </div>
            </div>
        <a href="javascript:;">续费<i class="gf_sprite"></i></a>
        <a href="register.html">退出<i class="gf_sprite"></i></a>
    </div>
</div>
<div class="gf_cn_wrap">
    <div class="gf_ci_nav gf_ci_pw cf">
        <h1 class="left"><a class="left" href="individual_center.html">
            <img src="images/upload/yhzx.jpg" alt="用户中心" />
        </a></h1>
        <p class="gf_ci_link left">
            <a href="individual_center.html">首页</a>
            <a href="cloud_service.html">云服务</a>
            <a class="on" href="wind_shop.html">云商店</a>
        </p>
        <form class="gf_ci_sch gf_idx_pr right">
            <input type="text" value="搜索" />
            <a class="gf_idx_pa gf_sprite" href="javascript:;"></a>
        </form>
    </div>
</div>

<dl class="gf_ci_order gf_ci_pw gf_ci_mt24 gf_ci_bg gf_ci_mng">
    <dt class="cf">
        <span class="left">账单详情</span>
    </dt>
    <dd>
        <dl class="gf_cd_dddt cf">
            <dd class="gf_ci_sorder">
            	<ul class="cf gf_bill_detail">
                	<li class="cf left">
                    	<h3>账单号：</h3>
                        <div>111111</div>
                    </li>
                    <li class="cf left">
                    	<h3>涉及服务：</h3>
                        <div>RAE,BPMS</div>
                    </li>
                    <li class="cf left">
                    	<h3>账单日期：</h3>
                        <div>2014年5月4日</div>
                    </li>
                    <li class="cf left">
                    	<h3>账单费用(人民币)：</h3>
                        <div>2000</div>
                    </li>
                    <li class="cf left">
                    	<h3>账单周期起：</h3>
                        <div>2014年3月3日</div>
                    </li>
                    <li class="cf left">
                    	<h3>账单周期止：</h3>
                        <div>2014年4月25日</div>
                    </li>
                </ul>
                <h2 class="gf_bill_service">服务费用明细列表</h2>
                <ul class="cf gf_bill_detail gf_bill_detail2">
                	<li class="cf left">
                    	<h3>RAE(经济版)：</h3>
                        <div>1000</div>
                    </li>
                    <li class="cf left">
                    	<h3>BPMS(经济版)：</h3>
                        <div>1000</div>
                    </li>
                 </ul>
            </dd>
			<a class="gf_pub_newapp gf_bill_down" href="javascript:;">下载</a>
        </dl>
    </dd>
</dl>

<div class="gf_ci_ifr gf_ci_pw gf_ci_mt24">
    <iframe class="gf_iframe" width="100%" frameborder="0" name="gf_ici" src="" scrolling="no"></iframe>
</div>
<div class="gf_footer gf_ci_mt24">
<%@ include file="../../static/include/buttom.jsp" %>
</div>
<!-- 弹层 -->
<div class="gf_ci_mask"></div>
<form class="gf_mod_info gf_ci_pw" action="">
    <dl>
        <dt class="gf_info_dt1">基本资料</dt>
        <dd class="gf_info_dd1">
            <dl>
                <dt>基本信息</dt>
                <dd>
                    <p>
                        <span>会员身份：</span>
                        <label>个人<input type="radio" name="dentity" value="个人"></label>
                        <label>企业<input type="radio" name="dentity" value="企业"></label>
                    </p>
                    <p>
                        <span>真实姓名：</span>
                        <input class="gf_info_realname" type="text" value="" />
                    </p>
                    <p>
                        <span>单位名称：</span>
                        <input class="gf_info_comname" type="text" value="" />
                    </p>
                </dd>
            </dl>
        </dd>
        <dd class="gf_info_dd1">
            <dl>
                <dt>企业信息</dt>
                <dd>
                    <p class="gf_is_wrap">
                        <span>所在地区：</span>
                        <em class="gf_idx_pr"><span>北京</span><i class="gf_sprite gf_idx_pa"></i></em>
                        <select class="gf_info_sel">
                            <option>北京</option>
                            <option>上海</option>
                            <option>广州</option>
                            <option>深圳</option>
                        </select>
                    </p>
                    <p>
                        <span>街道地址：</span>
                        <input class="gf_inp_txt" type="text" value="" />
                    </p>
                    <p>
                        <span>固定电话：</span>
                        <input class="gf_inp_txt" type="text" value="" />
                    </p>
                    <p>
                        <span>传<em></em>真：</span>
                        <input class="gf_inp_txt" type="text" value="" />
                    </p>
                    <p class="gf_ib_wrap"><input class="gf_info_btn" type="button" value="保存" /></p>
                </dd>
            </dl>
        </dd>
    </dl>
</form>
<!-- 弹层 -->
<script src="js/individual_center.js"></script>
</body>
</html>