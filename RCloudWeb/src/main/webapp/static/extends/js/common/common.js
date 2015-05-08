/**
 * ---------------------------------- 功能说明：系统JS方法公用类 
 * 功能说明： 
 * showWindow(options) ：弹出提示框 
 * closeWindow() ：关闭提示框 
 * showMsg(msg) ：信息弹出框(自动) 自动弹出 自动消失 
 * showMsgInfo(msg) ：信息弹出框 带遮罩背景的提示信息框
 */

/**
 * 弹出提示框 window
 * 
 * @since 1.0
 */
function showWindow(options) {
	options.href = webBasePath + options.href;
	options.minimizable = false;
	options.maximizable = false;
	options.collapsible = false;
	options.loadingMessage = I18N.msg_loading;
	$("#MyPopWindow").window(options);
}
/**
 * 关闭弹出框 window
 * 
 * @since 1.0
 */
function closeWindow() {
	$("#MyPopWindow").window('close');
}
/**
 * 打开弹出框 dialog
 * 
 * @since 1.0
 */
function showDialog(options) {
	//options.href = webBasePath + options.href;
	$('#MyDialog').dialog(options);
	setTimeout(function(){
		var $parent = $('#MyDialog').parent();
		var top = parseInt($parent.css("top"));
		var left = parseInt($parent.css("left")); 
		if(top < 0){
			$parent.css("top",0);
		}
		if(left < 0){
			$parent.css("left",0);
		}
	},200);
}
/**
 * 关闭弹出框 dialog
 * 
 * @since 1.0
 */
function closeDialog() {
	$('#MyDialog').dialog("close");
}

/**
 * 信息弹出框(自动) 自动弹出 自动消失
 * 
 * @param msg
 *            提示消息
 * @since 1.0
 */
function showMsg(msg) {
	if (msg == 'success') {
		msg = "操作成功！";
	} else if (msg == 'failure') {
		msg ="操作失败！";
	}
	$.messager.show({
		title : "提示信息",
		msg : msg,
		shadow : true,
		timeout : 1000,
		style : {
			// top: ($(window).height() - 180) * 0.5,
			// left: ($(window).width() - 480) * 0.5
		}
	});
}
/**
 * 信息弹出框 带遮罩背景的提示信息框
 * 
 * @param msg
 *            提示消息
 * 
 */
function showMsgInfo(msg) {
	$.messager.alert("提示信息", msg, "info");
}
/**
 * 打开错误信息弹出框 
 * 
 * @param data 提示消息
 * 
 */
function showErrorWindow(data) {
	$("#errorWindow").dialog({
		title : "提示信息",
		width : 515,
		iconCls : 'icon-error',
		content : data.responseText,
		height : 340
	});
}
/**
 * 关闭错误信息弹出框 
 */
function closeErrorDialog() {
	$('#errorWindow').dialog("close");
}

/**
 * 信息弹出框(自动) 自动弹出 自动消失
 * 
 * @param msg
 *            提示消息
 * @since 1.0
 */
function showOperMsg(msg) {
	var showMsgInfo = msg;
	$.messager.show({
		title : "&nbsp;提示信息",
		msg : showMsgInfo,
		shadow : true,
		timeout : 2000,
		style : {
		// top: ($(window).height() - 180) * 0.5,
		// left: ($(window).width() - 480) * 0.5
		}
	});
}

function Load(msg) {  
    $("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: "100%" }).appendTo("body");  
//    $("<div class=\"datagrid-mask-msg\" style=\"padding:10px 5px 30px 30px\"></div>").html("正在"+msg+"，请稍候。。。").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });}    
    $("<div class=\"datagrid-mask-msg\" style=\"padding:10px 5px 30px 30px;left:50%;margin-left:-104px;\"></div>").html("正在"+msg+"，请稍候。。。").appendTo("body").css( "display", "block");
    }


//hidden Load   
function dispalyLoad() {  
    $(".datagrid-mask").remove();  
    $(".datagrid-mask-msg").remove();  
} 


//提交时按钮不可点
function fnWait(oBtn){
	$("#"+oBtn).css({'backgroundColor':'#ccc','cursor':'default'}).val('提交中');
}
//提交完毕按钮
function fnSucc(oBtn,oTxt){
	$("#"+oBtn).css({'backgroundColor':'','cursor':'pointer'}).val(oTxt);
}


 







