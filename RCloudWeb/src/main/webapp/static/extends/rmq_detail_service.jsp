<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<script type="text/javascript">
var currID = "sjfw2";
var currID_t ="case_1";
</script>
<title>消息队列</title>
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
                <h1>云消息队列服务</h1><br>
                <p>RCloud MQ是一个基于网格、点对点JMS消息平台，具有强大且独特的功能，包括动态路由、消息流的分布式调试、JMS客户端应用程序的动态部署、无限的可扩展性、直接援引标准的JCA组件以及无与伦比的JMX标准易管理功能。在封闭的基准测试中超过每秒100,000条的消息，RCloud MQ还是最快的独立的标准消息服务器。</p>
                <div id="servicebuy"><a href="${ctx}/page/cloudservicePlatform/cloudServiceBuyForPage/8a8be9a4475dcaef01475dcaef7e0005"><input type="button" value="立即开通"/></a><em>实例<i>1800元</i>/月，不限流量!</em></div>
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
                    <dt>稳定</dt>
                    <dd>支持集群模式，个别应用实例故障不影响整体对外服务健康检查机制，宕机自动重启设备故障，应用实例自动漂移</dd>
                </dl>
                <dl>
                    <dt>弹性</dt>
                    <dd>根据负载情况自动伸缩应用使用的资源采用热伸缩方式，服务不中断</dd>
                </dl>
                <dl class="marginR_none">
                    <dt>安全</dt>
                    <dd>多用户隔离防DDoS系统</dd>
                </dl>
                <dl>
                    <dt>高效</dt>
                    <dd>路由优化，对动、静资源访问分离处理对外服务对静态资源访问进行了加速处理</dd>
                </dl>
                <dl>
                    <dt>多语言</dt>
                    <dd>Java、Python、Ruby、node.js、php多种语言环境</dd>
                </dl>
                <dl class="marginR_none">
                    <dt>离线调试</dt>
                    <dd>提供离线运行容器，支持离线调试 . 本地模拟各项扩展服务，离线调试不影响线上运行</dd>
                </dl>
                
            </div>
            <div class="gf_list_ti1" id="gf_list_title3"><img src="images/bg/list_ti2.png"/></div>
            <ul class="gf_list_use3 cf">
                <li class="gf_list_bg1">RCloud MQ的企业高速链路可与不同的IT系统无缝集成，提高了操作效率、业务灵活性以及效率</li>
                <li class="gf_list_bg2">通过全面支持标准通讯、连通性以及可变化性，RCloud MQ在提高功能强大的通讯高速链路的同时最小化资源的使用，</li>
                <li class="gf_list_bg3">同时高速链路可以很轻松地扩展、允许跨越多个应用程序、平台、伙伴以及客户实时传输数据</li>
                <li class="gf_list_bg4">RCloud MQ是支持临界操作的全球领先的消息中间件</li>
               <!-- <li class="gf_list_bg5">提供运行日志的查询和下载</li>
                <li class="gf_list_bg6">提供扩展功能：共享型MySQL数据库、分布式缓存服务、BPM服务EIC服务 、ESB服务</li>-->
            </ul> 
        </div>
    </div>  
</div>
<input type="hidden" name="serviceId" id="serviceId" value="8a8be9a4475dcaef01475dcaef7e0005">
<!--content end-->
<div class="gf_footer">
<%@ include file="../../static/include/home_buttom.jsp" %>
<%@ include file="../../static/include/buttom.jsp" %>
</div>
<script src="js/common.js"></script>
<script src="js/loadservice.js"></script>
</body>
</html>

