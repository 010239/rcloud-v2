<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="theme" value="blue" />
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>导航</title>
<link rel="stylesheet" href="${ctx}/static/lib/themes/style.css">
<link rel="stylesheet" href="${ctx}/static/lib/themes/${theme}/theme.css">
<script type="text/javascript" src="${ctx}/static/lib/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/extends/js/main.js"></script>
</head>
<body>
<div class="header">
  	<h1 class="left">ICS&amp;S管理应用平台</h1>
    <div class="header_R right">
        <p>
            <input type="text" placeholder="请输入搜索内容">
            <a href="javascript:;"><img src="${ctx}/static/lib/themes/icons/search.png"></a>
        </p>
    	
        <a href="javascript:;">
            <img src="${ctx}/static/lib/themes/icons/mail.png">
            <em>2</em>
        </a>
        <a href="javascript:;">
            <img src="${ctx}/static/lib/themes/icons/infor.png">
            <em>4</em>
        </a>
        <a href="javascript:;">
            <img src="${ctx}/static/lib/themes/icons/admin.png" width="26" height="26">
        </a>
        <div class="info">
            <a href="javascript:;">Cherry Lee</a>
            <div class="sub_list">
                <span></span>
                <ul class="cf">
                    <li><a href="javascript:;">个人中心</a></li>
                    <li><a href="javascript:;" class="sub_2">主页配置</a></li>
                    <li><a href="javascript:;" class="sub_3">退出登录</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="main">
<!--左侧导航 start-->
  <div class="aside cf">
    	<div class="aside_top cf">
        	<a class="pack_up" href="javascript:;"><img src="${ctx}/static/lib/themes/icons/txt.png"></a>
            <a class="home" href="javascript:;"><img src="${ctx}/static/lib/themes/icons/home.png"></a>
      	</div>
      	<div class="aside_nav">
      		<ul class="ul1">
      			<li class="li1">
                	<span><i class="nav_01"></i>用户中心<em></em></span>
                	<ol>
                    	<li>
                        	<span class="close">用户中心</span>
                            <ul>
                            	<li><a href="${ctx}/apApplication/toList" target="my_iframe">个人信息管理</a></li>
                                <li><a href="javascript:;">订单管理</a></li>
                                <li><a href="javascript:;">账单管理</a></li>
                                <li><a href="javascript:;">账户管理</a></li>
                                <li><a href="${ctx}/cloudservice/" target="my_iframe">服务管理</a></li>
                                <li><a href="${ctx}/cloudservicePlatform/" target="my_iframe">服务控制台</a></li>
                                <li class="last"><a href="javascript:;">机构管理</a></li>
                            </ul>
                        </li>
                    </ol>
                </li>
                <li class="li1">
                	<span><i class="nav_03"></i>应用商店<em></em></span>
                    <ol>
                    	<li>
                        	<span class="close">应用商店</span>
                            <ul>
                            	<li><a href="${ctx }/ucDept/deptManager" target="my_iframe">发布应用</a></li>
                                <li class="last"><a href="javascript:;">购买应用</a></li>
                            </ul>
                        </li>
                    </ol>
                </li>
      		</ul>
      	</div>
  </div> 
  <div class="packUp cf">
    	<div class="aside_top cf">
        	<a class="pack_close" href="javascript:;"><img src="${ctx}/static/lib/themes/icons/txt.png"></a>
            <a class="home" href="javascript:;"><img src="${ctx}/static/lib/themes/icons/home.png"></a>
      	</div>
      	<div class="aside_nav">
      		<ul class="ul1">
      			<li class="li1">
                	<span><i class="nav_01"></i>应用配置<em></em></span>
                	<ol>
                    	<li>
                        	<span class="close">应用配置</span>
                            <ul>
                            	<li><a href="${ctx}/apApplication/toList" target="my_iframe">应用管理</a></li>
                                <li><a href="javascript:;">菜单配置</a></li>
                                <li class="last"><a href="javascript:;">参数设置</a></li>
                            </ul>
                        </li>
                    </ol>
                </li>
                <li class="li1">
                	<span><i class="nav_03"></i>组织用户<em></em></span>
                    <ol>
                    	<li>
                        	<span class="close">组织用户</span>
                            <ul>
                            	<li><a href="${ctx }/ucDept/deptManager" target="my_iframe">组织管理</a></li>
                                <li><a href="javascript:;">用户管理</a></li>
                                <li><a href="javascript:;">岗位管理</a></li>
                                <li class="last"><a href="${ctx }/ucLevel/levelManager" target="my_iframe">分级管理</a></li>
                            </ul>
                        </li>
                    </ol>
                </li>
                <li class="li1">
                	<span><i class="nav_04"></i>角色授权<em></em></span>
                	<ol>
                    	<li>
                        	<span class="close">角色授权</span>
                            <ul>
                            	<li><a href="javascript:;">角色管理</a></li>
                                <li class="last"><a href="javascript:;">授权管理</a></li>
                            </ul>
                        </li>
                    </ol>
                </li>
      		</ul>
      	</div>
  </div>
<!--左侧导航 end-->  

<!--右侧 start--> 
<div class="right_wrapper">  
	<iframe class="my_iframe" width="100%" frameborder="0" name="my_iframe" src="userManager" scrolling="auto"></iframe>
</div>
<!--右侧 end-->
</div>
</body>
</html>
