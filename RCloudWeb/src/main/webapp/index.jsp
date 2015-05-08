<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>R-Cloud首页</title>
<link rel="stylesheet" href="static/lib/css/bootstrap.min.css">
<link rel="stylesheet" href="static/lib/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="static/extends/css/style.css">
<script src="static/extends/js/jquery_v1.10.2_min.js"></script>
<style type="text/css">
  
  </style>
<script type="text/javascript">
var currID_t = "";
</script>
</head>
<body class="gf_idx">
<%@ include file="../../../static/include/top.jsp" %>
<%@ include file="../../../static/include/home_top.jsp" %>
<div class="gf_idx_cycle gf_idx_pw gf_idx_pr"><div class="gf_idx_slide">
    <div class="gf_cycle_main">
        <img src="static/extends/images/upload/idx_banner.jpg" alt="banner" />
        <!--
        <p class="gf_cycle_notice gf_idx_pa cf">
            <a href="javascript:;">[07-07] R-CLOUD OCS升级公告</a>
            <a href="javascript:;">[07-07] R-CLOUD OCS升级公告</a>
            <a href="javascript:;">[07-07] R-CLOUD OCS升级公告</a>
        </p>
        -->
    </div>
    <div class="gf_cycle_main">
        <img src="static/extends/images/idx_banner2.jpg" alt="banner" />
    </div>
    <div class="gf_cycle_main">
        <img src="static/extends/images/idx_banner3.jpg" alt="banner" />
    </div>
    <!-- div class="gf_cycle_main">
        <img src="static/extends/images/idx_banner3.jpg" alt="banner" />
    </div>
    <div class="gf_cycle_main">
        <img src="static/extends/images/upload/idx_banner.jpg" alt="banner" />
    </div--></div>
    <p class="gf_cycle_switch gf_idx_pa cf"></p>
</div>
<dl class="gf_cld_serv gf_idx_pw cf">
    <dt><h2><span>云</span>服务</h2></dt>
    <dd class="gf_idx_pr">
        <a class="gf_to_left gf_idx_pa" href="javascript:;">&lt;</a>
        <a class="gf_to_right gf_idx_pa" href="javascript:;">&gt;</a>
        <div class="gf_sul_wrap">
            <ul class="cf">
                <li class="gf_serv_3 gf_serv_hover left gf_idx_pr"><a href="static/extends/rae_detail_service.jsp">
                    <p class="gf_serv_bg1 gf_sprite gf_idx_pa gf_serv_bg">
                        <img src="static/extends/images/upload/Rae.png" alt="云引擎" />
                    </p>
                    <p class="gf_fs_26 align_c">云应用引擎</p>
                    <p class="gf_fs_14 align_c">按量付费：<span>2000</span>元/月</p>
                    <!-- p class="gf_fs_14 align_c">包年包月：<span>49.5</span>元/小时</p --></a>
                </li>
                <li class="gf_serv_1 gf_serv_hover left gf_idx_pr"><a href="static/extends/appdev_detail_service.jsp">
                    <p class="gf_serv_bg1 gf_sprite gf_idx_pa gf_serv_bg">
                        <img src="static/extends/images/upload/Rportal.png" alt="云门户" />
                    </p>
                    <p class="gf_fs_26 align_c">云门户服务</p>
                    <p class="gf_fs_14 align_c">按量付费：<span>18000</span>元/月</p>
                    <!--p class="gf_fs_14 align_c">包年包月：<span>49.5</span>元/小时</p--></a>
                </li>
                <li class="gf_serv_2 gf_serv_hover left gf_idx_pr"><a href="static/extends/bpms_detail_service.jsp">
                    <p class="gf_serv_bg1 gf_sprite gf_idx_pa gf_serv_bg">
                        <img src="static/extends/images/upload/BPM.png" alt="云业务流程" />
                    </p>
                    <p class="gf_fs_26 align_c">云业务流程服务</p>
                    <p class="gf_fs_14 align_c">按量付费：<span>8000</span>元/月</p>
                    <!--p class="gf_fs_14 align_c">包年包月：<span>49.5</span>元/小时</p--></a>
                </li>
                <!-- <li class="gf_serv_3 gf_serv_hover left gf_idx_pr"><a href="static/extends/ei_detail_service.jsp">
                    <p class="gf_serv_bg1 gf_sprite gf_idx_pa gf_serv_bg">
                        <img src="static/extends/images/upload/REXP.png" alt="云数据交换" />
                    </p>
                    <p class="gf_fs_26 align_c">云数据交换服务</p>
                    <p class="gf_fs_14 align_c">按量付费：<span>9000</span>元/月</p>
                    <!--p class="gf_fs_14 align_c">包年包月：<span>49.5</span>元/小时</p--></a>
                </li> -->
                <li class="gf_serv_4 gf_serv_hover left gf_idx_pr"><a href="static/extends/esb_detail_service.jsp">
                    <p class="gf_serv_bg1 gf_sprite gf_idx_pa gf_serv_bg">
                        <img src="static/extends/images/upload/ESB.png" alt="云企业服务总线" />
                    </p>
                    <p class="gf_fs_26 align_c">云企业服务总线</p>
                    <p class="gf_fs_14 align_c">按量付费：<span>9000</span>元/月</p>
                    <!--p class="gf_fs_14 align_c">包年包月：<span>49.5</span>元/小时</p--></a>
                </li>
                <li class="gf_serv_2 gf_serv_hover left gf_idx_pr"><a href="static/extends/rmq_detail_service.jsp">
                    <p class="gf_serv_bg1 gf_sprite gf_idx_pa gf_serv_bg">
                        <img src="static/extends/images/upload/RMQ.png" alt="云消息队列" />
                    </p>
                    <p class="gf_fs_26 align_c">云消息队列服务</p>
                    <p class="gf_fs_14 align_c">按量付费：<span>1800</span>元/月</p>
                    <!--p class="gf_fs_14 align_c">包年包月：<span>49.5</span>元/小时</p--></a>
                </li>                
                <li class="gf_serv_2 gf_serv_hover left gf_idx_pr"><a href="static/extends/authentication_detail_service.jsp">
                    <p class="gf_serv_bg1 gf_sprite gf_idx_pa gf_serv_bg">
                        <img src="static/extends/images/upload/RAA.png" alt="云认证授权" />
                    </p>
                    <p class="gf_fs_26 align_c">云认证鉴权服务</p>
                    <p class="gf_fs_14 align_c">按量付费：<span>3000</span>元/月</p>
                    <!--p class="gf_fs_14 align_c">包年包月：<span>49.5</span>元/小时</p--></a>
                </li>
            </ul>
        </div>
    </dd>
</dl>
<dl class="gf_cld_store gf_idx_pw">
    <dt><h2><span>云</span>商店</h2></dt>
    <dd class="gf_store_wrap gf_idx_pr">
        <ul class="cf">
            <li class="gf_store_1 left">
                <a href="page/application/findList" class="gf_store_bg1 gf_sprite gf_store_hover">
                    <p class="gf_sprite gf_idx_pa"></p>
                </a>
                <p class="gf_fs_18 align_c">突发事件预警信息发布系统</p>
            </li>
            <li class="gf_store_2 left">
                <a href="page/application/findList" class="gf_store_bg2 gf_sprite gf_store_hover">
                    <p class="gf_sprite gf_idx_pa"></p>
                </a>
                <p class="gf_fs_18 align_c">网上大药房</p>
            </li>
            <li class="gf_store_3 left">
                <a href="page/application/findList" class="gf_store_bg3 gf_sprite gf_store_hover">
                    <p class="gf_sprite gf_idx_pa"></p>
                </a>
                <p class="gf_fs_18 align_c">网上打车服务</p>
            </li>
            <li class="gf_store_4 left">
                <a href="page/application/findList" class="gf_store_bg4 gf_sprite gf_store_hover">
                    <p class="gf_sprite gf_idx_pa"></p>
                </a>
                <p class="gf_fs_18 align_c">早教服务</p>
            </li>
        </ul>
        <div id="more"><a class="gf_store_more gf_idx_pa gf_sprite" href="${ctx}/page/application/findList" ></a></div>
    </dd>
</dl>
<!--dl class="gf_suc_case gf_idx_pw">
    <dt>
        <h2>成功<span>案例</span></h2>
        <p>
     RCloud是面向行业公有PaaS云平台，提供数量丰富和安全可靠的云服务，为行业用户和开发者创造商业价值。
        </p>
    </dt>
    <dd class="gf_idx_pr">
        <ul class="cf">
            <li class="gf_idx_pr left on">
                <img src="static/extends/images/upload/idx_suc-case.jpg" alt="" />
                <a href="${ctx}/static/extends/case.jsp"><i class="gf_idx_pa"></i></a>
            </li>
            <li class="gf_idx_pr left">
                <img src="static/extends/images/upload/idx_suc-case.jpg" alt="" />
                <a href="${ctx}/static/extends/case.jsp"><i class="gf_idx_pa"></i></a>
            </li>
            <li class="gf_idx_pr left">
                <img src="static/extends/images/upload/idx_suc-case.jpg" alt="" />
                <a href="${ctx}/static/extends/case.jsp"><i class="gf_idx_pa"></i></a>
            </li>
            <li class="gf_idx_pr left gf_idx_4th">
                <img src="static/extends/images/upload/idx_suc-case.jpg" alt="" />
                <a href="${ctx}/static/extends/case.jsp"><i class="gf_idx_pa"></i></a>
            </li>
            <li class="gf_idx_pr left">
                <img src="static/extends/images/upload/idx_suc-case.jpg" alt="" />
                <a href="${ctx}/static/extends/case.jsp"><i class="gf_idx_pa"></i></a>
            </li>
            <li class="gf_idx_pr left">
                <img src="static/extends/images/upload/idx_suc-case.jpg" alt="" />
                <a href="${ctx}/static/extends/case.jsp"><i class="gf_idx_pa"></i></a>
            </li>
            <li class="gf_idx_pr left">
                <img src="static/extends/images/upload/idx_suc-case.jpg" alt="" />
                <a href="${ctx}/static/extends/case.jsp"><i class="gf_idx_pa"></i></a>
            </li>
            <li class="gf_idx_pr left gf_idx_4th">
                <img src="static/extends/images/upload/idx_suc-case.jpg" alt="" />
                <a href="${ctx}/static/extends/case.jsp"><i class="gf_idx_pa"></i></a>
            </li>
        </ul>
        <a class="gf_suc_more gf_sprite gf_idx_pa" href="${ctx}/static/extends/case.jsp"></a>
    </dd>
</dl-->
<dl class="gf_idx_partner gf_idx_pw">
    <dt><h2><span>合作</span>伙伴</h2></dt>
    <dd><ul class="cf">
        <li>
            <!--div class="gf_ptn_logo"><a href="javascript:;">
                <img src="" alt="" />
            </a></div-->
            <p><a href="http://www.aliyun.com" target="_blank">阿里云</a></p>
        </li>
        <li>
            <p><a href="#">中信21世纪</a></p>
        </li>
        <li>
            <p><a href="http://www.wasu.com.cn" target="_blank">华数</a></p>
        </li>
        <li>
            <p><a href="http://www.chinagreentown.com" target="_blank">绿城房地产</a></p>
        </li>
    </ul></dd>
</dl>
<div class="gf_footer">
<%@ include file="static/include/home_buttom.jsp" %>
<%@ include file="static/include/buttom.jsp" %>
</div>
<!--公告弹窗-->
<div class="mask"></div>
<div class="notice_layer l_w1">
	<h2 class="layer_h2">
    	公告
        <a href="javascript:;" class="close_icon"></a>
    </h2>
    <div id="box" class="layer_box"></div>
</div>
<script type="text/javascript" src="static/extends/js/jquery.cycle.lite.js"></script>
<script type="text/javascript" src="static/extends/js/jquery.cycle.all.js"></script>
<script src="static/extends/js/index.js"></script>
<script src="static/extends/js/common.js"></script>
<!-- 
<script src="static/extends/js/loadhome.js"></script>
 -->
</body>
</html>
