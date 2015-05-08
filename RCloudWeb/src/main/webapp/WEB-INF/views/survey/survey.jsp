<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<%@ include file="../../../static/include/default.jsp" %>
<title>rcloud云平台试用满意度调查问卷</title>
<style type="text/css">
.survey_wrapper{width:980px;margin:0 auto;border:1px solid #149dd4;}
.survey_title{font-weight:bold;font-size:18pt;text-align:center;border:1px #ffffff solid;padding:10px;background-color:#149dd4;color:#fff;}
.intro{padding:10px 20px;}
.survey_dl{padding:10px 20px;}
.survey_dl dt{padding-bottom:5px;}
.survey_btn{min-width:95px; padding:0 10px; margin-bottom:20px;height:30px;background:#149dd4;border-radius:2px;-moz-border-radius:2px;-webkit-border-radius:2px;color:#fff;}
</style>
<script type="text/javascript">
	function windowclose() { 
		var browserName = navigator.appName; 
		if (browserName=="Netscape") { 
			window.open('', '_self', ''); 
			window.close(); 
		} else { 
			if (browserName == "Microsoft Internet Explorer"){ 
				window.opener = "whocares"; 
				window.opener = null; 
				window.open('', '_top'); 
				window.close(); 
			} 
		} 
	}
</script>
</head>
<body>
<div class="survey_wrapper">
	<h2 class="survey_title">rcloud云平台试用满意度调查问卷</h2>
    <p class="intro">为了给您提供更好的网上服务，请您协助我们完成以下调查。您的返馈对我们很重要，我们将致力于倾听、挖掘和满足您的需求，提供更安全，更人性化的用户体验。再次感谢您的参与。</p>
    <dl class="survey_dl">
    	<dt>1. 您对RCloud门户的注册流程、服务开通使用流程的体验如何（单选）</dt>
        <dd>
        	<p><input type="radio" name="Key_201" />&nbsp;&nbsp;<span>A、简单清晰</span></p>
            <p><input type="radio" name="Key_201" />&nbsp;&nbsp;<span>B、体验一般</span></p>
            <p><input type="radio" name="Key_201" />&nbsp;&nbsp;<span>C、复杂难用</span></p>
        </dd>
    </dl>
    <dl class="survey_dl">
    	<dt>2. 您对RCloud门户-用户中心的界面交互、功能设计的满意度：（单选）</dt>
        <dd>
        	<p><input type="radio" name="Key_202" />&nbsp;&nbsp;<span>A、很满意</span></p>
            <p><input type="radio" name="Key_202" />&nbsp;&nbsp;<span>B、满意</span></p>
            <p><input type="radio" name="Key_202" />&nbsp;&nbsp;<span>C、一般</span></p>
            <p><input type="radio" name="Key_202" />&nbsp;&nbsp;<span>D、不满意</span></p>
            <p><input type="radio" name="Key_202" />&nbsp;&nbsp;<span>E、极不满意</span></p>
        </dd>
    </dl>
    <dl class="survey_dl">
    	<dt>3. 您对RCloud门户-用户中心的稳定性、播放流畅性、清晰度的满意度：（单选）</dt>
        <dd>
        	<p><input type="radio" name="Key_203" />&nbsp;&nbsp;<span>A、很满意</span></p>
            <p><input type="radio" name="Key_203" />&nbsp;&nbsp;<span>B、满意</span></p>
            <p><input type="radio" name="Key_203" />&nbsp;&nbsp;<span>C、一般</span></p>
            <p><input type="radio" name="Key_203" />&nbsp;&nbsp;<span>D、不满意</span></p>
            <p><input type="radio" name="Key_203" />&nbsp;&nbsp;<span>E、极不满意</span></p>
        </dd>
        </dd>
    </dl>
    <dl class="survey_dl">
    	<dt>4. 您对RCloud门户-公共频道的界面交互、功能设计的满意度：（单选）</dt>
        <dd>
        	<p><input type="radio" name="Key_204" />&nbsp;&nbsp;<span>A、很满意</span></p>
            <p><input type="radio" name="Key_204" />&nbsp;&nbsp;<span>B、满意</span></p>
            <p><input type="radio" name="Key_204" />&nbsp;&nbsp;<span>C、一般</span></p>
            <p><input type="radio" name="Key_204" />&nbsp;&nbsp;<span>D、不满意</span></p>
            <p><input type="radio" name="Key_204" />&nbsp;&nbsp;<span>E、极不满意</span></p>
        </dd>
    </dl>
    <dl class="survey_dl">
    	<dt>5. 您对RCloud门户-公共频道的稳定性、播放流畅性、清晰度的满意度：（单选）</dt>
        <dd>
        	<p><input type="radio" name="Key_205" />&nbsp;&nbsp;<span>A、很满意</span></p>
            <p><input type="radio" name="Key_205" />&nbsp;&nbsp;<span>B、满意</span></p>
            <p><input type="radio" name="Key_205" />&nbsp;&nbsp;<span>C、一般</span></p>
            <p><input type="radio" name="Key_205" />&nbsp;&nbsp;<span>D、不满意</span></p>
            <p><input type="radio" name="Key_205" />&nbsp;&nbsp;<span>E、极不满意</span></p>
        </dd>
    </dl>
    <dl class="survey_dl">
    	<dt>6. 您对RCloud-rae（云引擎服务）的页面交互、功能流程设计的满意度：（单选）</dt>
        <dd>
        	<p><input type="radio" name="Key_206" />&nbsp;&nbsp;<span>A、很满意</span></p>
            <p><input type="radio" name="Key_206" />&nbsp;&nbsp;<span>B、满意</span></p>
            <p><input type="radio" name="Key_206" />&nbsp;&nbsp;<span>C、一般</span></p>
            <p><input type="radio" name="Key_206" />&nbsp;&nbsp;<span>D、不满意</span></p>
            <p><input type="radio" name="Key_206" />&nbsp;&nbsp;<span>E、极不满意</span></p>
        </dd>
    </dl>
    <dl class="survey_dl">
    	<dt>7. 您对RCloud-rae（云引擎服务）的系统稳定性、处理速度的满意度：（单选）</dt>
        <dd>
        	<p><input type="radio" name="Key_207" />&nbsp;&nbsp;<span>A、很满意</span></p>
            <p><input type="radio" name="Key_207" />&nbsp;&nbsp;<span>B、满意</span></p>
            <p><input type="radio" name="Key_207" />&nbsp;&nbsp;<span>C、一般</span></p>
            <p><input type="radio" name="Key_207" />&nbsp;&nbsp;<span>D、不满意</span></p>
            <p><input type="radio" name="Key_207" />&nbsp;&nbsp;<span>E、极不满意</span></p>
        </dd>
    </dl>
    <dl class="survey_dl">
    	<dt>8. 您对RCloud-rbpms（云业务流程服务）的页面交互、功能流程设计的满意度：（单选）</dt>
        <dd>
        	<p><input type="radio" name="Key_208" />&nbsp;&nbsp;<span>A、很满意</span></p>
            <p><input type="radio" name="Key_208" />&nbsp;&nbsp;<span>B、满意</span></p>
            <p><input type="radio" name="Key_208" />&nbsp;&nbsp;<span>C、一般</span></p>
            <p><input type="radio" name="Key_208" />&nbsp;&nbsp;<span>D、不满意</span></p>
            <p><input type="radio" name="Key_208" />&nbsp;&nbsp;<span>E、极不满意</span></p>
        </dd>
    </dl>
    <dl class="survey_dl">
    	<dt>9. 您对RCloud-rbpms（云业务流程服务）的系统稳定性、处理速度的满意度：（单选）</dt>
        <dd>
        	<p><input type="radio" name="Key_209" />&nbsp;&nbsp;<span>A、很满意</span></p>
            <p><input type="radio" name="Key_209" />&nbsp;&nbsp;<span>B、满意</span></p>
            <p><input type="radio" name="Key_209" />&nbsp;&nbsp;<span>C、一般</span></p>
            <p><input type="radio" name="Key_209" />&nbsp;&nbsp;<span>D、不满意</span></p>
            <p><input type="radio" name="Key_209" />&nbsp;&nbsp;<span>E、极不满意</span></p>
        </dd>
    </dl>
    
    <p class="refer"><input type="button" class="survey_btn" value="我已经完成了答卷" onClick="windowclose();"></p>
</div>
</body>
</html>