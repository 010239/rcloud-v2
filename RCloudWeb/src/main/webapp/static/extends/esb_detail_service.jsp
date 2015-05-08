<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<script type="text/javascript">
var currID = "esbfw";
var currID_t ="case_1";
</script>
<title>服务总线</title>
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
                <h1>云企业服务总线</h1><br>
                <p>RESB (RCloud Enterprise Service Bus ) 服务是 RCloud SOA Suit 技术支撑能力的基 础设施，是面向服务（SOA）集成中的企业服务总线（ESB）的实现产品，提供 企业服务总线所需的全部功能。包括服务中介、服务动态路由、服务转换等核心 能力，能帮助企业实现供应链中各环节涉及的商业伙伴间的灵活的业务协同与交 互，帮助政府构建敏捷的、服务型的政务 IT 系统。</p>
                <div id="servicebuy"><a href="${ctx}/page/cloudservicePlatform/cloudServiceBuyForPage/8a8be9a4475dcaef01475dcaef7e0004"><input type="button" value="立即开通"/></a><em>实例<i>9000元</i>/月，不限流量!</em></div>
            </div>
            <div class="gf_list_tab cf">
            	<dl class="cf left">
                    <dt><img src="images/bg/list_tab1.png"/></dt>
                    <dd><a href="#gf_list_title1" >如何使用</a></dd>
                </dl>
                <dl class="cf left">
                    <dt><img src="images/bg/list_tab2.png"/></dt>
                    <dd><a href="#gf_list_title2" >功能指标</a></dd>
                </dl>
                <dl class="cf left">
                    <dt><img src="images/bg/list_tab3.png"/></dt>
                    <dd><a href="#gf_list_title3" >性能指标</a></dd>
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
                    <dd>进入ESB产品介绍页购买ESB服务号</dd>
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
            <div class="gf_list_ti1" id="gf_list_title2"><img src="images/bg/resbFun.png"/></div>
            
            <h3 class="gf_list_ti1_head">八大服务功能指标为您指引！</h3>
            <div class="gf_list_use2 cf">
           
                <dl>
                    <dt>服务中介代理服务</dt>
                    <dd>服务中介代理使应用能够屏蔽对后端服务的直接访问，实现应用系统间的松耦合集成</dd>
                </dl>
          
                <dl>
                    <dt>服务交互与路由</dt>
                    <dd>为服务请求和响应提供分发和聚合功能，支持服务的动态和静态路由，提供对WebService标准的完整支持，通过对WS-I服务互操作规范的支持，建立标准的服务交互能力</dd>
                	
                </dl>
         <br>
                <dl class="marginR_none">
                    <dt>消息格式及协议转换</dt>
                    <dd>提供消息格式转换功能和可视化设计建模工具，对应用间交互数据加工计算和格式转换进行设计和建模。</dd>
                </dl>
                 
                <dl>
                    <dt>服务注册</dt>
                    <dd>提供服务资源的注册、发现和变更等全生命周期管理。</dd>
                </dl>
                <dl>
                    <dt>服务管理</dt>
                    <dd>提供服务资源分类管理，支持中文服务目录及服务资源的中文描述，支持系统内部以及系统之间的服务调用监控和服务安全管理。</dd>
                </dl>
                <dl class="marginR_none">
                    <dt>基于Web的图形化管理与监控</dt>
                    <dd>提供全局统一的图形化的配置管理、总线运行状态监控、异常/错误查看与恢复机制、全过程的消息跟踪。</dd>
                </dl>
                <dl>
                    <dt>集成设计开发环境</dt>
                    <dd>提供代理服务接口定义和服务封装设计功能，并提供Web服务资源的设计开发时的资源管理功能。</dd>
                </dl>
                 <dl class="marginR_none">
                    <dt>开发API和SDK</dt>
                    <dd>提供SOA服务集成平台的完整开发API和SDK。</dd>
                </dl>
                
            </div>
            <div class="gf_list_ti1" id="gf_list_title3"><img src="images/bg/resb1.png"/></div>
            <ul class="gf_list_use3 cf">
                <li class="gf_list_bg1">支持每秒交易120笔，并支持基于阿里云基础平台的按需弹性伸缩</li>
                <li class="gf_list_bg2">服务响应时间在1秒以内</li>
                <li class="gf_list_bg3">服务查找响应时间在毫秒级以内</li>    
            </ul>
        
        </div>
    </div>  
</div>
<input type="hidden" name="serviceId" id="serviceId" value="8a8be9a4475dcaef01475dcaef7e0004">
<!--content end-->
<div class="gf_footer">
<%@ include file="../../static/include/home_buttom.jsp" %>
<%@ include file="../../static/include/buttom.jsp" %>
</div>
<script src="js/common.js"></script>
<script src="js/loadservice.js"></script>
</body>
</html>

