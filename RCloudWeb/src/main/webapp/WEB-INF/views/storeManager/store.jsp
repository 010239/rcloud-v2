<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="theme" value="blue" />
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>云商店</title>
<%@ include file="../../../static/include/top.jsp" %>
<link rel="stylesheet" href="${ctx}/static/lib/easyui/1.3.6/themes/default/easyui.css"> 
 <script type="text/javascript" src="${ctx}/static/extends/js/common/common.js"></script> <!-- smh add 弹出框 -->
<script type="text/javascript">

var currID_t ="case_2";
	var pageSize = 10;
	$(function(){
		outPageNum();
	});
	var aType='100001';

	function getApplicationList(t,appType){
		aType = appType;
		$(".gf_store_left a").removeClass("on");
		$(t).addClass("on");
		$.ajax({
		   type: "POST",
		   url: "${ctx}/rest/application/getApplicationListByType",
		   data: "applicationType=" + appType +"&currentPage=1&pageSize=" + pageSize,
		   success: function(result){
			   outApps(result,1);
		   }
		});
	}
	//输出页码
	function outPageNum(t,c){
		var total = "${pagination.total}";
		if(t){
			total = t;
		}
		if(total){
			var totalPage = Math.ceil((total*1)/pageSize);
			var pageNums = "";
			var currPage = "${param.currentPage}";
			if(c){
				currPage = c;
			}
			if(total = 1){
				for(var i = 0;i < totalPage; i++){
					if((i+1)== currPage){
						pageNums += "<a class='gf_js_page on' href='javascript:;'>" + (i+1) +"</a>";
					}else{
						pageNums += "<a class='gf_js_page' href='javascript:;' onclick='turnPage(" + (i+1) +");'>" + (i+1) +"</a>";
					}
				}
			}
			pageNums += "<span>共" + totalPage +"页</span>";
			$(".gf_sr_page").html("");
			$(".gf_sr_page").append(pageNums);
		}
	}
	//翻页
	function turnPage(pageNum){
		$.ajax({
		   type: "POST",
		   url: "${ctx}/rest/application/getApplicationListByType",
		   data: "applicationType=" + aType +"&currentPage=" + pageNum + "&pageSize=10",
		   success: function(result){
			   outApps(result,pageNum);
		   }
		});
	}
	//输出应用列表
	function outApps(data,pageNum){
		var divs = "";
		if(data){
			var rows = data.entity.rows;
			if(rows.length > 0){
				for (var i = 0; i < rows.length; i++) {
					var row = rows[i];
					if (row.subDomain != null && row.subDomain != "") {
					divs += "<li class='cf'>"
							+ "<a class='gf_sr_pic' href='javascript:;'>"
							+ "<img src='${ctx}/" + row.logoPath + "' alt='' />"
							+ "</a>"
							+ "<div class='gf_sr_txt'>"
							+ "<h2><a href='javascript:;'>"+ row.chname +"</a></h2>"
							+ "<p class='gf_sr_tp'>"+ row.briefDescription +"</p>"
							+ "<p class='gf_err_p2'>"
							+ "<span class='blue_color'>月服务费:" + row.maintenanceCosts + "</span>" 
							+ "<a href='javascript:;' class='try-button' address='" + row.subDomain + "'>试用</a>&nbsp;" 						
							+ "<a href='javascript:void(0)' onclick='buyBefore(\"" + row.applicationId + "\")'>购买</a></p>"
							+ "</div>" + "<i class='gf_corner_1'></i>"
							+ "<i class='gf_corner_2'></i>"
							+ "<i class='gf_corner_3'></i>"
							+ "<i class='gf_corner_4'></i>" + "</li>";
					} else {
						divs += "<li class='cf'>"
							+ "<a class='gf_sr_pic' href='javascript:;'>"
							+ "<img src='${ctx}/" + row.logoPath + "' alt='' />"
							+ "</a>"
							+ "<div class='gf_sr_txt'>"
							+ "<h2><a href='javascript:;'>"+ row.chname +"</a></h2>"
							+ "<p class='gf_sr_tp'>"+ row.briefDescription +"</p>"
							+ "<p class='gf_err_p2'>"
							+ "<span class='blue_color'>月服务费:" + row.maintenanceCosts + "</span>" 						
							+ "<a href='javascript:void(0)' onclick='buyBefore(\"" + row.applicationId + "\")'>购买</a></p>"
							+ "</div>" + "<i class='gf_corner_1'></i>"
							+ "<i class='gf_corner_2'></i>"
							+ "<i class='gf_corner_3'></i>"
							+ "<i class='gf_corner_4'></i>" + "</li>";
					}
				}
			}
			
			outPageNum(data.entity.total,pageNum);
		}else{
			outPageNum();
		}
		$(".gf_sr_list").html("");
		$(".gf_sr_list").append(divs);
		$(".try-button").click(function() {
			var address = $(this).attr("address");
			window.open(address);
		});
	}
	function buyBefore(applicationId){
		window.location.href="${ctx}/page/pruchase/" + applicationId ;
		
		/* $.ajax({
			url:ctx + "rest/applicationpublish/buyappcheck/"+applicationId,
			success:function(data) {
				if (data.status == '200') {
					if(data.entity){
						showOperMsg("此应用已经购买,可以直接去使用！");
					}else{
						window.location.href="${ctx}/page/pruchase/" + applicationId ;
					}
				} else {
					if(data.errorMessage != null){
					    $("#errorInfo").html(data.errorMessage);
					}
					$("#errorModal").modal("show");
				}
				
			}
		}); */ 
	}
	
</script>

<style>
   	.gf_sr_txt{position:relative;min-height:158px;padding-bottom:40px;}
   	.gf_sr_txt p.gf_err_p2{text-align:right;position:absolute;bottom:0;width:100%;}
   	.gf_sr_txt p.gf_err_p2 a{text-align:center;width:60px;}
   	.gf_sr_txt p.gf_err_p2 span.blue_color{color:#149dd4;position:absolute;top:0;left:0;}
</style>
</head>
<body>

<%@ include file="../../../static/include/home_top.jsp" %>
<div class="gf_idx_pwn cf">
    <dl class="gf_store_left left">
        <!--  <dt>云应用商店</dt>-->
        <dt class="sub_title">云应用商店</dt>
        <c:forEach items="${typeList }" var="type" varStatus="status">
        	<dd>
        		<c:if test="${status.count == 1 }">
		            <a class="on" href="javascript:;" onclick="getApplicationList(this,'${type.typeId}');">
		                <i class="gf_sprite gf_sl_1 gf_sl_left"></i>
		                <span>${type.typeName }</span>
		                <i class="gf_sprite gf_sl_on"></i>
		            </a>
	            </c:if>
	            <c:if test="${status.count > 1 }">
		            <a href="javascript:;" onclick="getApplicationList(this,'${type.typeId}');">
		                <i class="gf_sprite gf_sl_1 gf_sl_left"></i>
		                <span>${type.typeName }</span>
		                <i class="gf_sprite gf_sl_on"></i>
		            </a>
	            </c:if>
	        </dd>
        </c:forEach>
        
        <dd class="gf_sl_jiangyou"></dd>
    </dl>
    
    <div class="gf_store_right left">
        <ul class="gf_sr_list">
        	<c:forEach items="${pagination.rows }" var="app">
        		<li class="cf">
	                <a class="gf_sr_pic" href="javascript:;">
	                    <img src="${ctx }/${app.logoPath}" alt="" />
	                </a>
	                <div class="gf_sr_txt">
	                    <h2><a href="javascript:;">${app.chname }</a></h2>
	                    <p class="gf_sr_tp">${app.briefDescription}</p>
	                    <p class="gf_err_p2">
	                    	<span class="blue_color">月服务费:${app.maintenanceCosts}</span>
	                    	<c:if test="${not empty app.subDomain}">
	                    		 <a href="javascript:;" class="try-button" address="${app.subDomain}">试用</a>&nbsp; 
	                    
	                    	</c:if>
	                    	<a href="javascript:void(0)" onclick="buyBefore('${app.applicationId}')">购买</a>
	                    </p>
	                </div>
	                <i class="gf_corner_1"></i>
	                <i class="gf_corner_2"></i>
	                <i class="gf_corner_3"></i>
	                <i class="gf_corner_4"></i>
	            </li>
        	</c:forEach>
        </ul>
        <form class="gf_sr_page" action="">
            
        </form>
    </div>
</div>
<div class="gf_footer">
<%@ include file="../../../static/include/home_buttom.jsp" %>
<%@ include file="../../../static/include/buttom.jsp" %>
</div>
<script type="text/javascript" src="${ctx }/static/extends/js/application_list.js"></script>
<script type="text/javascript" src="${ctx }/static/extends/js/jquery.cycle.lite.js"></script>
<script type="text/javascript" src="${ctx }/static/extends/js/jquery.cycle.all.js"></script>
<script src="${ctx }/static/extends/js/index.js"></script>
<script src="${ctx }/static/extends/js/common.js"></script>
</body>
</html>

