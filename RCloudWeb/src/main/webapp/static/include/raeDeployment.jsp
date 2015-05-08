<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div id="dlg_createApplication" class="easyui-dialog"  title="&nbsp;部署到RAE" style="width:556px;height:501px;padding:5px;overflow:auto" data-options="modal:true,closed:true,
                buttons: [{
                    text:'提交',
                    handler:function(){
                     submit_createApplication();
                    }
                },{
                    text:'关闭',
                    handler:function(){
                    close_createApplication();
                        
                    }
                }]" >
                
        <div style="margin:15px 0;"></div>         
        <form id="form_createApplicatioin" action="<%=request.getContextPath() %>/saveApp" method="post" enctype ="multipart/form-data" style="margin-bottom:0px;">
	        <input type="hidden" id="orderNum" name="orderNum"/>
	        <input type="hidden" id="checkUrl"/>
	        <input type="hidden" id="developId" name="developId"/>
	        <input type="hidden" id="appId" name="appliId"/>
	        <table class="form_table" style="vertical-align: middle;">
	        	<tr> 
	        		<td align="right">应用名称：</td>
	        		<td><div style="float:left;"><input  type="text" name="applicationName" id="appName"   style="width:230px;"></input><span id="domainNameapp"></span></div><div style="float:left;" id="errorTextapp"></div></td>
	        	</tr>

	        	<tr>
	        		<td align="right">二级域名：</td>
	        		<td>
	        		<div style="float:left;">
	        		<input class="easyui-validatebox textbox" type="text" name="domainNam" id="domainNameaa" style="width:230px;"data-options="required:true" onblur="checkdomain(this)" onfocus="">
	         <span id="domainNameStat"></span>
	         </div>
	         
	         <div id="errorTextdom" style="float:left;"></div></td>
	        	</tr>
	        	<tr>
	        		<td align="right">预置硬盘：</td>
	        		<td style="height:25px;padding:0 0 0 15">
	        			<!--  
					    <select class="easyui-combobox" name="disk" style="width:200px;">
					        <option value="512">512 MB</option>
					        <option value="1024">1 G</option>
					        <option value="2048">2 G</option>
					   	</select>
					   	-->
	        			<input id="diskName" type="hidden" name="hiskSize">
	        			<input id="diskValue" class="easyui-slider" style="width:420px;" data-options="
	        				min:1024,
	        				max:2048,
	        				step:512,
			                rule: ['1G','1.5G','2G']
			            ">
					</td>
	        	</tr>
	        	<tr>
	        		<td align="right">预置内存：</td>
	        		<td style="height:70px;padding:0 0 0 15">
	        			<!--  
					    <select class="easyui-combobox" name="ram" style="width:200px;">
					        <option value="512">512 MB</option>
					        <option value="1024">1 G</option>
					        <option value="1536">1.5 G</option>
					        <option value="2048">2 G</option>
					   	</select>
					   	-->
	        			<input id="ramName" type="hidden" name="meorySize">
	        			<input id="ramValue" name="ram" class="easyui-slider" style="width:420px;" data-options="
	        				min:512,
	        				max:2048,
	        				step:512,
			                rule: ['512MB','1G','1.5G','2G']
			            ">
					</td>
	        	</tr>
	        	
	        	<tr>
	        		<td align="right">应用版本：</td>
	        		<td><input class="easyui-validatebox textbox" type="text" name="appVersion" id="version" data-options="required:true" style="width:230px;"></input></td>
	        	</tr>
	        	<tr>
	        		<td align="right">命名空间：</td>
	        		<td><select id="spaceid" name="spaceName" style="width:230px;" class="createapplicationselect">
	        	
	        		</select></td>
	        	</tr>
	        
	        	<tr>
	        		<td align="right">应用说明：</td>
	        		<td><textarea name="detailDescription" rows="5" cols="5" id="remark" style="width:450px;height:60px;"></textarea></td>
	        	</tr>
	        	
	        	<tr>
	        		<td></td>
	        		<td><div id="errorText"></div></td>
	        	</tr>
	        </table>
		</form>
    </div>
<script src="${ctx }/static/extends/js/raeDeploymentApp.js"></script>

