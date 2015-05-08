<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<script type="text/javascript">
var currID = "kfpt";
var currID_t ="case_1";
</script>
<title>云门户</title>
</head>
<body class="gf_idx">
<%@ include file="../../static/include/top.jsp" %>
<%@ include file="../../static/include/home_top.jsp" %>
<!--content star-->
<div class="gf_wrapper_t cf">
	<%@ include file="../../static/include/detail_service_menu.jsp" %>
    <div class="gf_list_right left">
    	<div class="gf_list_wrap">
        	
        	<div class="gf_list_top">
                <h1>云门户服务</h1><br>
                <p>RPoral （RCloud  Portal Platform）以云服务方式提供易用、快捷、省钱的Java应用开发工具及运行环境。成为开发者与R1Cloud云平台的最佳连接通道，封装云平台底层技术细节，使开发人员专注于业务设计实现，实现统一规范，提高业务的开发效率、降低业务开发成本，方便传统应用开发者轻松转换到云应用开发中来。</p>
                <div id="servicebuy"><a href="${ctx}/page/cloudservicePlatform/cloudServiceBuyForPage/8a8be9a4475dcaef01475dcaef7e0001"><input type="button" value="立即开通"/></a><em>实例<i>18000元</i>/月，不限流量!</em></div>
            </div>
            <div class="gf_list_tab cf">
            	<dl class="cf left">
                    <dt><img src="images/bg/list_tab1.png"/></dt>
                    <dd><a href="#gf_list_title1" >如何使用</a></dd>
                </dl>
                <dl class="cf left">
                    <dt><img src="images/bg/list_tab2.png"/></dt>
                    <dd><a href="#gf_list_title2" >产品优势</a></dd>
                </dl>
                <dl class="cf left">
                    <dt><img src="images/bg/list_tab3.png"/></dt>
                    <dd><a href="#gf_list_title3" >产品功能</a></dd>
                </dl>
            </div>
            <div class="gf_list_ti1" id="gf_list_title1"><img src="images/bg/list_ti1.png"/></div>
            <div class="gf_list_use cf">
                <dl>
                    <dt><img src="images/bg/num1.png"/></dt>
                    <dd>注册RCloud帐号</dd>
                </dl>
                <dl>
                    <dt><img src="images/bg/num2.png"/></dt>
                    <dd>进入RAE产品介绍页购买RAE服务号</dd>
                </dl>
                <dl class="marginR_none">
                    <dt><img src="images/bg/num3.png"/></dt>
                    <dd>通过控制台进行创建应用、上传代码部署应用等管理操作</dd>
                </dl>
                <dl>
                    <dt><img src="images/bg/num4.png"/></dt>
                    <dd>也可以在控制台中查询应用的性能数据（流量、内存等）、运行日志等</dd>
                </dl>
                <dl>
                    <dt><img src="images/bg/num5.png"/></dt>
                    <dd>还可以在控制台开通RAE扩展服务供应用程序调用</dd>
                </dl>
            </div>
            <div class="gf_list_ti1" id="gf_list_title2"><img src="images/bg/list_ti3.png"/></div>
            
            <h3 class="gf_list_ti1_head">六大优势帮您解决问题！</h3>
            <div class="gf_list_use2 cf">
                <dl>
                    <dt>应用安全隔离</dt>
                    <dd>支持集群模式，个别应用实例故障不影响整体对外服务健康检查机制，宕机自动重启设备故障，应用实例自动漂移</dd>
                </dl>
                <dl>
                    <dt>访问端口控制</dt>
                    <dd>根据负载情况自动伸缩应用使用的资源采用热伸缩方式，服务不中断</dd>
                </dl>
                <dl class="marginR_none">
                    <dt>高效路由</dt>
                    <dd>多用户隔离防DDoS系统</dd>
                </dl>
                <dl>
                    <dt>智能伸缩</dt>
                    <dd>路由优化，对动、静资源访问分离处理对外服务对静态资源访问进行了加速处理</dd>
                </dl>
                <dl>
                    <dt>云端访问</dt>
                    <dd>Java、Python、Ruby、node.js、php多种语言环境</dd>
                </dl>
                <dl class="marginR_none">
                    <dt>快速定位</dt>
                    <dd>提供离线运行容器，支持离线调试 . 本地模拟各项扩展服务，离线调试不影响线上运行</dd>
                </dl>
                
            </div>
            <div class="gf_list_ti1" id="gf_list_title3"><img src="images/bg/list_ti2.png"/></div>
            <ul class="gf_list_use3 cf">
                <li class="gf_list_bg1">RAE 通过智能的负载均,衡算保证应用运行.分担其它实例的压力。</li>
                <li class="gf_list_bg2">云日志是提供应用开发者用于系统问题定位，掌握线上运行状况和系统访问情况的日志服务。</li>
                <li class="gf_list_bg3">将硬盘，内存，CPU隔离保证安全</li>
            </ul>
        </div>
    </div>  
</div>
<input type="hidden" name="serviceId" id="serviceId" value="8a8be9a4475dcaef01475dcaef7e0001">
<!--content end-->
<div class="gf_footer">
<%@ include file="../../static/include/home_buttom.jsp" %>
<%@ include file="../../static/include/buttom.jsp" %>
</div>
<script src="js/common.js"></script>
<script src="js/loadservice.js"></script>
</body>
</html>

