<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../../../static/include/default.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>工作空间</title>
<link type="text/css" href="${ctx}/static/extends/css/error_hint.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${ctx}/static/lib/js/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx}/static/lib/js/jquery.validationEngine.min.js"></script>
<script type="text/javascript"
	src="${ctx}/static/lib/js/jquery.placeholder.js"></script>
<script type="text/javascript">
	$(function() {
		$("#form").validationEngine({
			onValidationComplete : function(form, valid) {
				if (valid == true) {
					document.form.submit();
				}
				return false;

			},//表单验证后的回调函数，form为提交的表单，valid为结果，验证通过则为true,验证失败则为false
			scroll : false,
			focusFirstField : false,
			maxErrorsPerField:1
		});
	})
</script>
<style type="text/css">
::-webkit-input-placeholder { /* WebKit browsers */
color: #999;
}
:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
color: #999;
}
::-moz-placeholder { /* Mozilla Firefox 19+ */
color: #999;
}
:-ms-input-placeholder { /* Internet Explorer 10+ */
color: #999;
} 
</style>
</head>
<body>
	<form id="form" method="post" action="${ctx}/page/org/savespace">
    <dl>
        <dd class="gf_info_dd1">
            <dl>
                <dd>
                    <p>
                        <span>工作空间名称：</span>
                        <input type="text" name="spaceChname" class="gf_info_comname validate[required]" placeholder="请输入工作空间" data-prompt-target='handle_spaceChname' data-prompt-position='inline'>
                        <span id="handle_spaceChname" class="import_tip"></span>
                    </p>
                    <p>
                        <span>工作空间描述：</span>
                        <input name="spaceDescription" type="text" class="gf_info_comname" placeholder="工作空间描述">
                    </p>
                    <p>&nbsp;</p>
                    <p class="gf_ib_wrap">
                    <button type="submit" class="gf_info_btn">提交</button>
                    <button type="button" class="gf_info_btn" onclick="window.history.back()">返回</button>
                    </p>
                </dd>
            </dl>
        </dd>
    </dl>
	<!-- 
		<div class="form-group">
			<label >工作空间名称</label> <input type="text" name="spaceChname"
				class="validate[required] form-control" placeholder="请输入工作空间">
		</div>
		<div class="form-group">
			<label >工作空间描述</label> <input name="spaceDescription"
				type="text" class="form-control" 
				placeholder="工作空间描述">
		</div>
		<button type="submit" class="btn btn-default">提交</button>
	-->
	</form>
</body>
</html>
