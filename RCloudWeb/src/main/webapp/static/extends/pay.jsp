<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>付款界面</title>
</head>
<body class="gf_ci">
<div class="gf_idx_topbar">
    <div class="gf_ci_pw gf_idx_ar gf_idx_pr">
            <a class="gf_ic_user" href="javascript:;">你好，<span class="gf_idx_pr gf_js_lmd"><strong class="gf_for_yy">于洋</strong><em class="gf_i_tri"></em></span></a>
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
            <a class="on" href="individual_center.html">首页</a>
            <a href="cloud_service.html">云服务</a>
            <a href="wind_shop.html">云商店</a>
        </p>
        <form class="gf_ci_sch gf_idx_pr right">
            <input type="text" value="搜索" />
            <a class="gf_idx_pa gf_sprite" href="javascript:;"></a>
        </form>
    </div>
</div>
<div class="gf_ci_pw gf_pay_h2"><h2>选择支付方式</h2></div>
<form class="gf_pay_form gf_ci_pw" action="">
    <h3 class="gf_ci_mt24 gf_ci_bg gf_ci_mng">平台支付</h3>
    <ul class="cf gf_pay_ul gf_pay_bor">
        <li>
            <label>
                <i class="left gf_sprite on"></i>
                <input type="radio" name="gf_pay" value="cai_fu_tong" />
                <img class="gf_pay_pic" src="images/upload/cft.png" alt="" />
            </label>
        </li>
        <li>
            <label>
                <i class="left gf_sprite"></i>
                <input type="radio" name="gf_pay" value="zhi_fu_bao" />
                <img class="gf_pay_pic" src="images/upload/zfb.png" alt="" />
            </label>
        </li>
        <li>
            <label>
                <i class="left gf_sprite"></i>
                <input type="radio" name="gf_pay" value="yin_lian" />
                <img class="gf_pay_pic" src="images/upload/yl.png" alt="" />
            </label>
        </li>
    </ul>
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
                        <img class="gf_pay_pic" src="images/upload/zgny.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="gong_shang" />
                        <img class="gf_pay_pic" src="images/upload/gsyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="zhao_shang" />
                        <img class="gf_pay_pic" src="images/upload/zsyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="zhong_guo" />
                        <img class="gf_pay_pic" src="images/upload/zgyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="min_sheng" />
                        <img class="gf_pay_pic" src="images/upload/msyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="xing_ye" />
                        <img class="gf_pay_pic" src="images/upload/xyyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="jiao_tong" />
                        <img class="gf_pay_pic" src="images/upload/jtyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="guang_fa" />
                        <img class="gf_pay_pic" src="images/upload/gfyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="pu_fa" />
                        <img class="gf_pay_pic" src="images/upload/pfyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="ping_an" />
                        <img class="gf_pay_pic" src="images/upload/zgpa.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="wen_zhou" />
                        <img class="gf_pay_pic" src="images/upload/wzyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="fu_dian" />
                        <img class="gf_pay_pic" src="images/upload/fdyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="ning_bo" />
                        <img class="gf_pay_pic" src="images/upload/nbyh.png" alt="" />
                    </label>
                </li>
                <li>
                    <label>
                        <i class="left gf_sprite"></i>
                        <input type="radio" name="gf_pay" value="hang_zhou" />
                        <img class="gf_pay_pic" src="images/upload/hzyn.png" alt="" />
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
<script src="js/individual_center.js"></script>
<script src="js/pay.js"></script>
</body>
</html>

