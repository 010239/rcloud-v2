<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Bootstrap form</title>
<link href="../static/lib/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="../static/lib/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../static/extends/js/jquery.validate.js"></script>
</head>

<body>
	<br />
	<br />
	<br />
	<div class="container">
		<form id="form">
		
		  	<div class="form-group">
		    	<div class="input-group">
		      	<div class="input-group-addon">姓名 <span class="glyphicon glyphicon-asterisk"></span></div>
		      	<input type="text" id="name" name="name" class="form-control" placeholder="例如：高富帅" data-rule-required="true"/>
		    	</div>
		  	</div>
		  	<label for="name" class="alert alert-danger" style="display:none;"></label>

		  	<div class="form-group">
		    	<div class="input-group">
		      	<div class="input-group-addon">年龄 <span class="glyphicon glyphicon-asterisk"></span></div>
		      	<input type="text" id="age" name="age" class="form-control" placeholder="例如：18" data-rule-required="true" data-rule-range="[0,101]"/>
		    	</div>
		  	</div>
		  	<label for="age" class="alert alert-danger" style="display:none;"></label>
			
		  	<div class="form-group">
		    	<div class="input-group">
		      	<div class="input-group-addon">邮件 <span class="glyphicon glyphicon-asterisk"></span></div>
		      	<input type="text" id="email" name="email" class="form-control" placeholder="例如：xx@xx.xx" data-rule-required="true" data-rule-email="true" data-msg-required="扩展显示：请输入邮箱" data-msg-email="扩展显示：请输入合法的邮箱">
		    	</div>
		  	</div>
		  	<label for="email" class="alert alert-danger" style="display:none;"></label>
			
		  	<div class="form-group">
		    	<div class="input-group">
		      		<div class="input-group-addon">地址 <span class="glyphicon glyphicon-home"></span></div>
		      		<input type="text" name="address" class="form-control" placeholder="例如：北京市海淀区裕友大厦">
		    	</div>
		  	</div>
			
			<div class="btn-group">
				<button type="submit" class="btn btn-primary">Send</button>

				<button type="reset" class="btn btn-default">Reset</button>
			</div>
			
		</form>
		<br />
		<br />
<table>
<tr><th>属性</th><th>说明</th></tr>
<tr><td>data-rule-required:true</td><td>必须输入的字段。</td></tr>
<tr><td>data-rule-remote:"check.php"</td><td>使用 ajax 方法调用 check.php 验证输入值。</td></tr>
<tr><td>data-rule-email:true</td><td>必须输入正确格式的电子邮件。</td></tr>
<tr><td>data-rule-url:true</td><td>必须输入正确格式的网址。</td></tr>
<tr><td>data-rule-date:true</td><td>必须输入正确格式的日期。日期校验 ie6 出错，慎用。</td></tr>
<tr><td>data-rule-dateISO:true</td><td>必须输入正确格式的日期（ISO），例如：2009-06-23，1998/01/22。只验证格式，不验证有效性。</td></tr>
<tr><td>data-rule-number:true</td><td>必须输入合法的数字（负数，小数）。</td></tr>
<tr><td>data-rule-digits:true</td><td>必须输入整数。</td></tr>
<tr><td>data-rule-creditcard:</td><td>必须输入合法的信用卡号。</td></tr>
<tr><td>data-rule-equalTo:"#field"</td><td>输入值必须和 #field 相同。</td></tr>
<tr><td>data-rule-accept:</td><td>输入拥有合法后缀名的字符串（上传文件的后缀）。</td></tr>
<tr><td>data-rule-maxlength:5</td><td>输入长度最多是 5 的字符串（汉字算一个字符）。</td></tr>
<tr><td>data-rule-minlength:10</td><td>输入长度最小是 10 的字符串（汉字算一个字符）。</td></tr>
<tr><td>data-rule-rangelength:[5,10]</td><td>输入长度必须介于 5 和 10 之间的字符串（汉字算一个字符）。</td></tr>
<tr><td>data-rule-range:[5,10]</td><td>输入值必须介于 5 和 10 之间。</td></tr>
<tr><td>data-rule-max:5</td><td>输入值不能大于 5。</td></tr>
<tr><td>data-rule-min:10</td><td>输入值不能小于 10。</td></tr>
</table>
	</div>

	<script>
		$('form').validate({
			errorPlacement: function(error, element) {
				$(element).closest("form").find("label[for='" + element.attr( "id" ) + "']").show().append(error);
			},
			success: function(label) {
			    label.parent().hide();
			}
		});
	</script>
</body>
</html>