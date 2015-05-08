<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../../../static/include/default.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>售后支持</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/lib/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/lib/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/extends/css/style.css">
<script type="text/javascript" src="${ctx}/static/lib/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
var currID = "servsupport";
</script>
<script type="text/javascript">
$(function(){
	$('.support_list li').off().on('click',function(){
		var index=$(this).index();
		$('.support_list li').removeClass('on');
		$(this).addClass('on');
		$('.support_tab').hide();
		$('.support_tab').eq(index).show();

	});
});
function listMyTickets(){
	var user_name = "${user_info.userName}";
	var user_pas = "${user_info.password}";
	var otrs_address = "${otrs_address}";
	if(user_name == null || user_name == ""){
		window.location.href="${ctx}";
	}
	var list_ticket_src = otrs_address+"?Action=Login&User="+user_name+"&accessKey="+user_pas+"&accessTime="+new Date().getTime();
	$("#list_ticket_frame").attr("src", list_ticket_src);
}

function addTickets(){
	var user_name = "${user_info.userName}";
	var user_pas = "${user_info.password}";
	var otrs_address = "${otrs_address}";
	if(user_name == null || user_name == ""){
		window.location.href="${ctx}";
	}
	var add_ticket_src = otrs_address+"?Action=CustomerTicketMessage&User="+user_name+"&accessKey="+user_pas+"&accessTime="+new Date().getTime();
	$("#add_ticket_frame").attr("src",add_ticket_src);
}


function subChange(){
	$("#addTicket_id").removeClass('on');
	$("#listTicket_id").addClass('on');
	$("#addTicket_div").css("display","none");
	$("#listTicket_div").css("display","block");
	listMyTickets();
}

</script>
</head>
<body>
<%@ include file="../include/top.jsp" %>
<%@ include file="../include/user_top.jsp" %>
<div class="support_box cf">
	<div class="support_left">
    	<ul class="support_list">
        	<li class="on" id="listTicket_id"><a  style="cursor:pointer" onclick="javascript:listMyTickets();">我的工单</a></li>
            <li id="addTicket_id"><a style="cursor:pointer" onclick="javascript:addTickets();">提交工单</a></li>
        </ul>
    </div>
    <div class="support_right" id="orts_id">
    	<div class="support_tab" id="listTicket_div">
    		<h2 class="support_title">工单列表</h2>
            <div class="support_nr">
                <iframe width="100%" height="100%"  frameborder=0 scrolling=auto 
                id="list_ticket_frame">
                </iframe>
            </div>
        </div>
        <div class="support_tab" style="display:none;" id="addTicket_div">
    		<h2 class="support_title">填写工单</h2>
            <div class="support_nr">
                <iframe width="100%" height="100%" frameborder=0 scrolling=auto 
                 id="add_ticket_frame">
                </iframe>
            </div>
        </div>
        
    </div>
</div>
<div class="gf_footer gf_ci_mt24">
<%@ include file="../../../static/include/buttom.jsp" %>
</div>
<script src="${ctx}/static/extends/js/individual_center.js"></script>
<script type="text/javascript">
$(function(){
	var user_name = "${user_info.userName}";
	var user_pas = "${user_info.password}";
	var otrs_address = "${otrs_address}";
	if(user_name == null || user_name == ""){
		window.location.href="${ctx}";
	}
	var list_ticket_src = otrs_address+"?Action=Login&User="+user_name+"&accessKey="+user_pas+"&accessTime="+new Date().getTime();
	$("#list_ticket_frame").attr("src", list_ticket_src);
	var add_ticket_src = otrs_address+"?Action=CustomerTicketMessage&User="+user_name+"&accessKey="+user_pas+"&accessTime="+new Date().getTime();
	//setTimeout(function(){$("#add_ticket_frame").attr("src",add_ticket_src);},5000);
	
});
</script>
</body>
</html>
