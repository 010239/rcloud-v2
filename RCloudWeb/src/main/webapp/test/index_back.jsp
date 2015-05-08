<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Bootstrap plugins</title>
<link rel="stylesheet" href="static/lib/css/bootstrap.min.css">
<link rel="stylesheet" href="static/lib/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="static/extends/css/index/style.css">
<script type="text/javascript" src="static/extends/js/index/jquery.js"></script>

<script type="text/javascript">

function fomatHtml(data){
	var html = '<div class="tab-pannel">';
	$.each(data,function(index,value){ //遍历单个菜单的分类
		  html += '<div class="product cell">';
		  html += '<a href="javascript:;">';
		  html += '<div class="box">';
		  html += '<span class="icon-cdn-big"><img src="'+value.imageurl+'" width="117" height="135"></span>';
		  html += '<h2 class="title"><span class="red">'+value.title+'</span></h2>';
		  html += '<p class="text">按量付费：<span class="red">'+value.price+'</span>元/小时<br>包年包月：<span class="red">'+value.price+'</span>元/小时</p>';
		  html += '</div>';
		  html += "</a></div>";
	});
	html += "</div>";
	html = $(html);
	$('.product:last',html).addClass('last_box');
	$('.product:first a',html).addClass('hover');
	return html;
}

$(function () { 
	var data = [ //菜单数据(包括"云应用、云定价")
		[ //单个菜单数据
		[ {
			'title' : '云应用',//子菜单分类一
			data : [ {
				'url' : '#',
				'name' : 'R-Cloud引擎（RAE）'
			} ]
		}, {
			'title' : '云应用开发平台服务',
			data : [ {
				'url' : '#',
				'name' : '测试1'
			}, {
				'url' : '#',
				'name' : '测试2'
			} ]
		} ], [ { //子菜单分类二
			'title' : '数据服务',
			data : [ {
				'url' : '#',
				'name' : 'mysql服务'
			}, {
				'url' : '#',
				'name' : 'Redis服务'
			} ]
		}, {
			'title' : '云数据交换服务',
			data : [ {
				'url' : '#',
				'name' : 'mysql服务'
			} ]
		} ] ], [ [ {
			'title' : '定价',
			data : [ {
				'url' : '#',
				'name' : '测试数据'
			} ]
		}, {
			'title' : '内容',
			data : [ {
				'url' : '#',
				'name' : '内容展示'
			} ]
		} ] ] ];

		$.each(data, function(menu_index, menudata) { //遍历所有菜单
			var html = '<div class="sub_nav">';
			$.each(menudata, function(index, value) { //遍历单个菜单的分类
				html += "<div>";
				$.each(value, function(n, v) {       //子菜单的数据分类
					html += '<dl>';
					html += '<dt class="tit">' + v.title + '</dt>';
					$.each(v.data, function(i, y) {
						html += '<dd><a href="'+y.url+'">' + y.name
								+ '</a></dd>';
					});
					html += '</dl>';
				})
				html += "</div>";
			});
			$(".nav li").each(function(i, n) {
				if (i == menu_index + 1) {
					$(this).append(html);
				}
			});

		});
		$(".sub_nav div:last-child").attr("class","no_border");
		
		var data_ = [{
			'imageurl':'static/extends/images/index/icons/app_1.png',
			'title':'云应用',
			'subtitle':'运行引擎',
			'price':'0.18'
		},{
			'imageurl':'static/extends/images/index/icons/app_2.png',
			'title':'云测试1',
			'subtitle':'引擎',
			'price':'182'
		},{
			'imageurl':'static/extends/images/index/icons/app_3.png',
			'title':'云测试2',
			'subtitle':'引擎',
			'price':'182'
		},{
			'imageurl':'static/extends/images/index/icons/app_4.png',
			'title':'云测试3',
			'subtitle':'引擎',
			'price':'182'
		},{
			'imageurl':'static/extends/images/index/icons/app_1.png',
			'title':'云测试4',
			'subtitle':'引擎',
			'price':'182'
		},{
			'imageurl':'static/extends/images/index/icons/app_2.png',
			'title':'云测试5',
			'subtitle':'引擎',
			'price':'182'
		}]
		
		var len = data_.length;
		var PAGENUM = 4;
		var group = Math.ceil(len/PAGENUM);
		for(var i=1; i<=group; i++){
		  var start = (i-1)*PAGENUM;
		  var d = [];
		  if(i == group){
		  	d = data_.slice(start);
		  }else{
		    var end = i*PAGENUM;
		  	d = data_.slice(start,end);
		  }
		  var html = fomatHtml(d);
		  $(".div_box").append(html);
		}
		
		var shopdata = [{
			'url':'#',
			'img':'static/extends/images/index/icons/shop_1.png',
			'title':'突发事件预警发布系统'
		},{
			'url':'#',
			'img':'static/extends/images/index/icons/shop_2.png',
			'title':'突发事件预警发布系统'
		},{
			'url':'#',
			'img':'static/extends/images/index/icons/shop_3.png',
			'title':'突发事件预警发布系统'
		},{
			'url':'#',
			'img':'static/extends/images/index/icons/shop_4.png',
			'title':'突发事件预警发布系统'
		}];
		
		var html = ''
		$.each(shopdata,function(index,value){
			html += '<li><a href="'+value.url+'"><img src="'+value.img+'" width="98" height="102"></a><p>'+value.title+'</p></li>';
		});
		
		$(".shop-wrap ul").append(html);
		
		var successcasedata = [{
			'url':'#',
			'img':'static/extends/images/index/case_1.png'
		},{
			'url':'#',
			'img':'static/extends/images/index/case_2.png'
		},{
			'url':'#',
			'img':'static/extends/images/index/case_3.png'
		},{
			'url':'#',
			'img':'static/extends/images/index/case_4.png'
		}];
		
		var html = ''
		$.each(successcasedata,function(index,value){
			html += '<li><a href="'+value.url+'"><img src="'+value.img+'"></a><a href="javascript:;" class="search"><img src="static/extends/images/index/case_search.png"></a></li>';
		});
		$(".case_detail ul").append(html);
	});
</script>

<script type="text/javascript" src="static/extends/js/index/index.js"></script>
</head>
<body>
    <!--header start-->
    <div class="top_wrapper">
        <div class="top cf">
            <div class="top_r">
                <!--未登录-->
                <div class="log_nr">
                    <a class="on" href="javascript:;">登录</a><a href="javascript:;">注册</a><a href="javascript:;">续费</a>
                </div>
                <!--已登录-->
                <div class="log_nr" style="display:none;">
                    <a href="javascript:;">我的RCloud</a>
                    <a href="javascript:;">欢迎您，test1@163.com</a>
                    <a href="javascript:;">注销</a>
                </div>
            </div>
            
        </div>
    </div>
    <!--header end-->
    
    <!--nav start-->
	<div class="nav_wrapper">
        <ul class="nav cf">
            <li><a class="logo" href="index.html"><img src="static/extends/images/index/logo.png" /></a></li>
            <li>
            	<a href="javascript:;">云服务</a>
            	<!--  
                <div class="sub_nav">
                	<div>
                    	<dl>
                            <dt class="tit">云应用引擎</dt>
                            <dd><a href="javascript:;">R-Cloud引擎（RAE）</a></dd>
                        </dl>
                        <dl>
                            <dt class="tit">云应用开发平台服务</dt>
                            <dd><a href="javascript:;">R-Cloud快速开发平台</a></dd>
                        </dl>
                        <dl>
                            <dt class="tit">业务流程服务</dt>
                            <dd><a href="javascript:;">R-Cloud业务流程服务</a></dd>
                        </dl>
                    </div>
                    <div>
                    	<dl>
                            <dt class="tit">数据服务</dt>
                            <dd><a href="javascript:;">mysql服务</a></dd>
                            <dd><a href="javascript:;"> Redis服务</a></dd>
                        </dl>
                        <dl>
                            <dt class="tit">云数据交换服务</dt>
                            <dd><a href="javascript:;">R-Cloud云数据交换服务 </a></dd>
                        </dl>
                        <dl>
                            <dt class="tit">云ESB服务</dt>
                            <dd><a href="javascript:;">R-Cloud云ESB服务</a></dd>
                        </dl>
                    </div>
                    <div class="no_border">
                    	<dl class="no_border">
                            <dt class="tit">安全服务</dt>
                            <dd><a href="javascript:;">RCloud认证鉴权服务</a></dd>
                            <dd><a href="javascript:;">RCloud单点登录服务</a></dd>
                        </dl>
                    </div>
                </div>
                -->
            </li>
            <li><a href="javascript:;">服务定价</a></li>
            <li><a href="javascript:;">云应用商店</a></li>
            <li><a href="javascript:;">案例</a></li>
            <li><a href="javascript:;">我的RCloud</a></li>
            <li><a href="javascript:;">文档中心</a></li>
            <li class="last">
            	<div class="search">
                    <p class="cf">
                        <span class="search_font">搜索</span>
                        <input class="search_txt" type="text" value=""> 
                        <button></button>
                    </p>
                </div>
            </li>
        </ul>
    </div>
    <!--nav end-->
    
    <!--焦点图 start-->
    <div class="banner_wrapper">
    	<div id="id" class="all">
          <div>
            <ul class="cf">
              	  <li class="current"><a href="javascript:;"><img src="static/extends/images/index/banner01.png"></a></li>
                  <li><a href="javascript:;"><img src="static/extends/images/index/banner02.jpg"></a></li>
                  <li><a href="javascript:;"><img src="static/extends/images/index/banner03.jpg"></a></li>
            </ul>
            <ol>
                  <li class="current"></li>
                  <li></li>
                  <li></li>
              </ol>
          </div>
        </div>
	</div>

    <!--焦点图 end-->
	
    <!--商品 start-->
	<div id="slide-products" class="home-products-wrap"> 
    	  <div class="index_title">
          		<h2><span class="blue">云</span>服务</h2>
                <div class="line-title">
                	<h3>R-cloud Service</h3>
                </div>
          </div>
          <a class="btn-prev" href="javascript:;"><i class="left icon-arrow-left"></i></a>
          <a class="btn-next" href="javascript:;"><i class="right icon-arrow-right"></i></a>
          <div class="slides_container tab-content">
                <div class="cf">
                	<div class="div_box">
                	
                		  <!--
                          <div class="tab-pannel">
                                <div class="product cell"> 
                                      <a href="javascript:;" class="hover">
                                      	<div class="box">
                                            <span class="icon-cdn-big"><img src="static/extends/images/index/icons/app_1.png" width="117" height="135"></span>
                                            <h2 class="title"><span class="red">云应用</span>运行引擎</h2>
                                            <p class="text">
                                            	按量付费：<span class="red">0.18</span>元/小时<br>
												包年包月：<span class="red">0.18</span>元/小时
                                          </p>
                                        </div>
                                      </a>
                                </div>
                                  
                                <div class="product cell"> 
                                      <a href="javascript:;">
                                      	<div class="box">
                                            <span class="icon-cdn-big"><img src="static/extends/images/index/icons/app_2.png" width="117" height="135"></span>
                                            <h2 class="title"><span class="blue">云应用</span>运行引擎</h2>
                                            <p class="text">
                                            	按量付费：<span class="blue">0.18</span>元/小时<br>
												包年包月：<span class="blue">0.18</span>元/小时
                                          </p>
                                        </div>
                                      </a>
                                </div>
                                <div class="product cell"> 
                                      <a href="javascript:;">
                                      	<div class="box">
                                            <span class="icon-cdn-big"><img src="static/extends/images/index/icons/app_3.png" width="117" height="135"></span>
                                            <h2 class="title"><span class="green">云应用</span>运行引擎</h2>
                                            <p class="text">
                                            	按量付费：<span class="green">0.18</span>元/小时<br>
												包年包月：<span class="green">0.18</span>元/小时
                                          </p>
                                        </div>
                                      </a>
                                </div>
                                
                                <div class="product cell last_box"> 
                                      <a href="javascript:;">
                                      	<div class="box">
                                            <span class="icon-cdn-big"><img src="static/extends/images/index/icons/app_4.png" width="117" height="135"></span>
                                            <h2 class="title"><span class="cyan">云应用</span>运行引擎</h2>
                                            <p class="text">
                                            	按量付费：<span class="cyan">0.18</span>元/小时<br>
												包年包月：<span class="cyan">0.18</span>元/小时
                                          </p>
                                        </div>
                                      </a>
                                </div>
                                
                          </div>
                          -->        
                            <!--     
                           <div class="tab-pannel">
                                <div class="product cell"> 
                                      <a href="javascript:;" class="hover">
                                      	<div class="box">
                                            <span class="icon-cdn-big"><img src="static/extends/images/index/icons/app_1.png" width="117" height="135"></span>
                                            <h2 class="title"><span class="red">云应用</span>运行引擎</h2>
                                            <p class="text">
                                            	按量付费：<span class="red">0.18</span>元/小时<br>
												包年包月：<span class="red">0.18</span>元/小时
                                          </p>
                                        </div>
                                      </a>
                                </div>
                                <div class="product cell"> 
                                      <a href="javascript:;">
                                      	<div class="box">
                                            <span class="icon-cdn-big"><img src="static/extends/images/index/icons/app_2.png" width="117" height="135"></span>
                                            <h2 class="title"><span class="blue">云应用</span>运行引擎</h2>
                                            <p class="text">
                                            	按量付费：<span class="blue">0.18</span>元/小时<br>
												包年包月：<span class="blue">0.18</span>元/小时
                                          </p>
                                        </div>
                                      </a>
                                </div>
                                <div class="product cell"> 
                                      <a href="javascript:;">
                                      	<div class="box">
                                            <span class="icon-cdn-big"><img src="static/extends/images/index/icons/app_3.png" width="117" height="135"></span>
                                            <h2 class="title"><span class="green">云应用</span>运行引擎</h2>
                                            <p class="text">
                                            	按量付费：<span class="green">0.18</span>元/小时<br>
												包年包月：<span class="green">0.18</span>元/小时
                                          </p>
                                        </div>
                                      </a>
                                </div>
                                <div class="product cell last_box"> 
                                      <a href="javascript:;">
                                      	<div class="box">
                                            <span class="icon-cdn-big"><img src="static/extends/images/index/icons/app_4.png" width="117" height="135"></span>
                                            <h2 class="title"><span class="cyan">云应用</span>运行引擎</h2>
                                            <p class="text">
                                            	按量付费：<span class="cyan">0.18</span>元/小时<br>
												包年包月：<span class="cyan">0.18</span>元/小时
                                          </p>
                                        </div>
                                      </a>
                                </div>
                          </div>
                                 
                          <div class="tab-pannel">
                                <div class="product cell"> 
                                      <a href="javascript:;" class="hover">
                                      	<div class="box">
                                            <span class="icon-cdn-big"><img src="static/extends/images/index/icons/app_1.png" width="117" height="135"></span>
                                            <h2 class="title"><span class="red">云应用</span>运行引擎</h2>
                                            <p class="text">
                                            	按量付费：<span class="red">0.18</span>元/小时<br>
												包年包月：<span class="red">0.18</span>元/小时
                                          </p>
                                        </div>
                                      </a>
                                </div>
                                <div class="product cell"> 
                                      <a href="javascript:;">
                                      	<div class="box">
                                            <span class="icon-cdn-big"><img src="static/extends/images/index/icons/app_2.png" width="117" height="135"></span>
                                            <h2 class="title"><span class="blue">云应用</span>运行引擎</h2>
                                            <p class="text">
                                            	按量付费：<span class="blue">0.18</span>元/小时<br>
												包年包月：<span class="blue">0.18</span>元/小时
                                          </p>
                                        </div>
                                      </a>
                                </div>
                                <div class="product cell"> 
                                      <a href="javascript:;">
                                      	<div class="box">
                                            <span class="icon-cdn-big"><img src="static/extends/images/index/icons/app_3.png" width="117" height="135"></span>
                                            <h2 class="title"><span class="green">云应用</span>运行引擎</h2>
                                            <p class="text">
                                            	按量付费：<span class="green">0.18</span>元/小时<br>
												包年包月：<span class="green">0.18</span>元/小时
                                          </p>
                                        </div>
                                      </a>
                                </div>
                                <div class="product cell last_box"> 
                                      <a href="javascript:;">
                                      	<div class="box">
                                            <span class="icon-cdn-big"><img src="static/extends/images/index/icons/app_4.png" width="117" height="135"></span>
                                            <h2 class="title"><span class="cyan">云应用</span>运行引擎</h2>
                                            <p class="text">
                                            	按量付费：<span class="cyan">0.18</span>元/小时<br>
												包年包月：<span class="cyan">0.18</span>元/小时
                                          </p>
                                        </div>
                                      </a>
                                </div>
                          </div>
                          -->
                    </div>
                </div>
          </div>
     </div>
    <!--商品 end-->
    
    <!--商店 start-->
	<div class="shop">
        <div class="index_title">
          		<h2><span class="blue">云</span>商店</h2>
                <div class="line-title">
                	<h3 class="white">R-cloud Store</h3>
                </div>
        </div>
        <div class="shop-wrap">
            <ul class="cf">
            	<!--  class="hover" -->
            	<!--  
            	<li><a href="javascript:;"><img src="static/extends/images/index/icons/shop_1.png" width="98" height="102"></a><p>突发事件预警发布系统</p></li>
                <li><a href="javascript:;"><img src="static/extends/images/index/icons/shop_2.png" width="98" height="102"></a><p>突发事件预警发布系统</p></li>
                <li><a href="javascript:;"><img src="static/extends/images/index/icons/shop_3.png" width="98" height="102"></a><p>突发事件预警发布系统</p></li>
                <li><a href="javascript:;"><img src="static/extends/images/index/icons/shop_4.png" width="98" height="102"></a><p>突发事件预警发布系统</p></li>
                -->
            </ul>
            <a class="more">MORE</a>
        </div>
    </div>
    <!--商店 end-->
    
    <!--案例 start-->
	<div class="case">
    	<div class="index_title">
          		<h2 class="case_title">成功<span class="yellow">案例</span></h2>
                <div class="line-title">
                	<h3 class="white">Success Case</h3>
                </div>
        </div>
      <div class="case_detail">
          <p>RCloud是面向行业公有PaaS云平台，提供数量丰富和安全可靠的云服务，为行业用户和开发者创造商业价值。</p>
       	  <ul class="cf">
       	  	  <!--  
       		  <li>
                    <a href="javascript:;"><img src="static/extends/images/index/case_1.png"></a>
                    <a href="javascript:;" class="search"><img src="static/extends/images/index/case_search.png"></a>
              </li>
              <li>
              		<a href="javascript:;"><img src="static/extends/images/index/case_2.png"></a>
                    <a href="javascript:;" class="search"><img src="static/extends/images/index/case_search.png"></a>
              </li>
              <li>
              		<a href="javascript:;"><img src="static/extends/images/index/case_3.png"></a>
                    <a href="javascript:;" class="search"><img src="static/extends/images/index/case_search.png"></a>
              </li>
              <li>
              		<a href="javascript:;"><img src="static/extends/images/index/case_4.png"></a>
                    <a href="javascript:;" class="search"><img src="static/extends/images/index/case_search.png"></a>
              </li>
              <li>
                    <a href="javascript:;"><img src="static/extends/images/index/case_1.png"></a>
                    <a href="javascript:;" class="search"><img src="static/extends/images/index/case_search.png"></a>
              </li>
              <li>
              		<a href="javascript:;"><img src="static/extends/images/index/case_2.png"></a>
                    <a href="javascript:;" class="search"><img src="static/extends/images/index/case_search.png"></a>
              </li>
              <li>
              		<a href="javascript:;"><img src="static/extends/images/index/case_3.png"></a>
                    <a href="javascript:;" class="search"><img src="static/extends/images/index/case_search.png"></a>
              </li>
              <li>
              		<a href="javascript:;"><img src="static/extends/images/index/case_4.png"></a>
                    <a href="javascript:;" class="search"><img src="static/extends/images/index/case_search.png"></a>
              </li>
              -->
       	  </ul>
          <a href="javascript:;" class="more">MORE</a>
        </div>
    </div>
    <!--案例 end-->
    
    <!--合作伙伴 start-->
	<div class="partner">
    	<div class="index_title">
          		<h2><span class="blue">合作</span>伙伴</h2>
                <div class="line-title">
                	<h3 class="white">Partner</h3>
                </div>
        </div>
        <ul class="cf">
        	<li>
            	<span><img src="static/extends/images/index/icons/part_1.png"></span>
                <p><a href="javascript:;">阿里云计算</a></p>
            </li>
            <li>
            	<span><img src="static/extends/images/index/icons/part_2.png"></span>
                <p><a href="javascript:;">21世纪不动产</a></p>
            </li>
            <li>
            	<span><img src="static/extends/images/index/icons/part_3.png"></span>
                <p><a href="javascript:;">华数在线</a></p>
            </li>
            <li>
            	<span><img src="static/extends/images/index/icons/part_4.png"></span>
                <p><a href="javascript:;">绿城集团</a></p>
            </li>
        </ul>
    </div>
    <!--合作伙伴 end-->
    

    <!--footer start-->     
    <div class="footer">
    	<div class="friendly_link cf">
    		<ul>
    			<li class="tit">新手指南</li>
                <li><a href="javascript:;">新手专区</a></li>
                <li><a href="javascript:;">新手专区</a></li>
                <li><a href="javascript:;">新手专区</a></li>
                <li><a href="javascript:;">新手专区</a></li>
    		</ul>
            <ul>
    			<li class="tit">服务与支持</li>
                <li><a href="javascript:;">新手专区</a></li>
                <li><a href="javascript:;">新手专区</a></li>
                <li><a href="javascript:;">新手专区</a></li>
                <li><a href="javascript:;">新手专区</a></li>
    		</ul>
            <ul>
    			<li class="tit">常见问题</li>
                <li><a href="javascript:;">新手专区</a></li>
                <li><a href="javascript:;">新手专区</a></li>
                <li><a href="javascript:;">新手专区</a></li>
                <li><a href="javascript:;">新手专区</a></li>
    		</ul>
            <ul>
    			<li class="tit">用户中心</li>
                <li><a href="javascript:;">新手专区</a></li>
                <li><a href="javascript:;">新手专区</a></li>
                <li><a href="javascript:;">新手专区</a></li>
                <li><a href="javascript:;">新手专区</a></li>
    		</ul>
            <ul>
    			<li class="tit">其他</li>
                <li><a href="javascript:;">新手专区</a></li>
                <li><a href="javascript:;">新手专区</a></li>
                <li><a href="javascript:;">新手专区</a></li>
                <li><a href="javascript:;">新手专区</a></li>
                <li><a href="javascript:;">新手专区</a></li>
    		</ul>
            <ul>
    			<li class="tit">合作伙伴</li>
                <li><a href="javascript:;">新手专区</a></li>
                <li><a href="javascript:;">新手专区</a></li>
                <li><a href="javascript:;">新手专区</a></li>
    		</ul>
    	</div>
        <div class="boxs">
            <p>
            	版权所有 © 2013-2014 中软国际公司  
            	<a href="http://www.miibeian.gov.cn" target="_blank">京ICP备14011895号-1</a>
            	<br>
            	<span>电话：+86 010 888482388 转 6326 传真：+86 010 8286 2809 邮箱：wubin@chinasofti.com</span>
            </p>
        </div>
    </div>
    <!--footer end-->
</body>
</html>
