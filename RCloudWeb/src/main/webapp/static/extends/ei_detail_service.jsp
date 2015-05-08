<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<script type="text/javascript">
var currID = "sjjh";
var currID_t ="case_1";
</script>
<title>数据交换</title>
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
                <h1>云数据交换服务</h1><br>
                <p>REXP (RCloud  Data Exchange Platform) 是一个分布式的集成交换中间件软件平台，是构建企业数据中枢和服务总线的支撑系统，其总体结构如下：</p>
                <br><img alt="" src="images/ei2.png">
                <div id="servicebuy"><a href="${ctx}/page/cloudservicePlatform/cloudServiceBuyForPage/8a8be9a4475dcaef01475dcaef7e0003"><input type="button" value="立即开通"/></a><em>实例<i>9000元</i>/月，不限流量!</em></div>
            </div>
            <div class="gf_list_tab cf">
            	<dl class="cf left">
                    <dt><img src="images/bg/list_tab1.png"/></dt>
                    <dd><a href="#gf_list_title1" >如何使用</a></dd>
                </dl>
                <dl class="cf left">
                    <dt><img src="images/bg/list_tab2.png"/></dt>
                    <dd><a href="#gf_list_title2" >产品内容</a></dd>
                </dl>
                <dl class="cf left">
                    <dt><img src="images/bg/list_tab3.png"/></dt>
                    <dd><a href="#gf_list_title3" >应用场景</a></dd>
                </dl>
            </div>
            <div class="gf_list_ti1" id="gf_list_title1"><img src="images/bg/list_ti1.png"/></div>
            <div class="gf_list_use cf" >
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
            <div class="gf_list_ti1" id="gf_list_title2"><img src="images/bg/rexpCon.png"/></div>
            
            <h3 class="gf_list_ti1_head">REXP分为四个主要部分</h3>
            <div class="gf_list_use2 cf">
                <dl>
                    <dt>REXP服务器</dt>
                    <dd> 是服务总线运行时实体，负责消息的映射转换、服务流的调度运行、消息路由传输、查找调用已注册服务等</dd>
                </dl>
                <dl>
                    <dt>REXP服务连接器</dt>
                    <dd>负责客户应用系统接入RCloud 云EI服务总线；支持多种接入方式：DEAgent、SOAP/WS、HTTP(S)、FTP、IBM MQ格式化的文件如Excel等。</dd>
                </dl>
                <dl class="marginR_none">
                    <dt>REXP服务设计工具</dt>
                    <dd>集成在RCloud Stuido(RCloud集成开发工具)中，负责服务流的设计、消息Schema设计、消息转换(映射)设计；发布、部署等</dd>
                </dl>
                <dl>
                    <dt>REXP服务管理监控工具</dt>
                    <dd>基于RCloud Management Console规范实现，采用B/S结构，集成在RCloud MC中；负责企业服务总线的配置、服务流消息监控管理、总线拓扑结构配置部署、业务系统与服务流绑定；资源监控、统计。</dd>
                </dl>
                <!-- <dl>
                    <dt>运维更方便</dt>
                    <dd>企业所有的业务流程都放到云上，便于集中管理运维</dd>
                </dl>
                <dl class="marginR_none">
                    <dt>传输能力强</dt>
                    <dd>支持多种传输协议，同时兼容市场上优秀的传输中间件如 IBM  MQ等；支持高并发的、并行的消息传输架构，拥有高效的传输效率</dd>
                </dl> -->
                <br>
            </div>
            <div class="gf_list_ti1" id="gf_list_title3"><img src="images/bg/rexp1.png"/></div>
            <ul class="gf_list_use3 cf">
               <li class="gf_list_bg1">基于SOA的服务集成调度与业务协同</li>
                <li class="gf_list_bg2">信息交换</li>
                <li class="gf_list_bg3">信息资源共享</li>
               <!--  <li class="gf_list_bg4">流程管理</li>
                <li class="gf_list_bg5">流程分析预警</li>
                <li class="gf_list_bg6">国情化人工协同</li> -->
            </ul>
        </div>
    </div>  
</div>
<input type="hidden" name="serviceId" id="serviceId" value="8a8be9a4475dcaef01475dcaef7e0003">
<!--content end-->
<div class="gf_footer">
<%@ include file="../../static/include/home_buttom.jsp" %>
<%@ include file="../../static/include/buttom.jsp" %>
</div>
<script src="js/common.js"></script>
<script src="js/loadservice.js"></script>
</body>
</html>

