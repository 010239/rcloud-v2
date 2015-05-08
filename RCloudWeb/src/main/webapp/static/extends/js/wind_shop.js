var publishDatagrid;

$(document).ready(function(){
	publictab1();
	
	//buytab2();
	
});
function publictab1(){
	
	var nowTime = new Date().getTime(); //时间戳
	publishDatagrid = $('#publish-application-dg').datagrid({
	    url: ctx + 'rest/applicationpublish?date='+nowTime,
	    method:'get',
	    fit:true,
	    fitColumns: true,
	    loadFilter: function (data) {
	    	if (data.status == '200') {
	    		return data.entity;
	    	}else{
	    		if(data.errorMessage != null){
		    		$("#errorInfo").html(data.errorMessage);
	    		}
	    		$("#errorModal").modal("show");
	    	}
	    },
	    rownumbers: false,
	    columns:[[{ field:'ck',checkbox:true },
			{ field: 'chname', title: '应用名称',width:'150',align: 'center' },
			{ field: 'createdTime', title: '发布时间',width:'150' ,align: 'center',
	        	formatter: function(value, row, index) {
	        		if ( value != null ) {
	        			return getSmpFormatDateByLong(value, false);
	        		}
	        	}
	        },
	        { field: 'shelveTime', title: '上架时间',width:'150' ,align: 'center',
	        	formatter: function(value, row, index) {
	        		if ( value != null ) {
	        			return getSmpFormatDateByLong(value, false);
	        		}
	        	}
	        },
	        {title:'状态',field:'status',width:'150',align: 'center',
	        	formatter : function(val){
					  switch(val){
						case 0:
							return '待审核';break;
						case 1:
							return '审核通过';break;
						case 2:
							return '审核未通过';break;
						case 3:
							return '已下架';break;
						}
	        	}
	        },
			{ field: 'dealCount', title: '成交数量',width:'150',align: 'center' },
			{ field: 'applicationId', title: '操作',width:'160', align: 'center',
				formatter: function(value,row,index){
					var operationHtml = '<a href="javascript:void(0);" class="dg-button" onclick="viewApplication(\'' + value +'\', \'' +row.status+  '\')">查看</a>';
					
					if (row.status != 1) {
						operationHtml += '<a href="javascript:void(0);" class="dg-button" onclick="updateApplication(\'' + value + '\')">修改</a>';
					
					}
					return operationHtml;
				}
			}
			
	              ]],
		singleSelect: true,
	    selectOnCheck: false,
	    checkOnSelect: false,
		pagination:true
		
	});
}
function buytab2(){
	var buyDatagrid;
	var nowTime = new Date().getTime(); //时间戳
	buyDatagrid = $('#buy-application-dg').datagrid({
	    url: ctx + 'rest/cloudshop/applicationBuyOrder?date='+nowTime,
	    method:'get',
	    fitColumns: true,
	    fit:true,
	    loadFilter: function (data) {
	    	if (data.status == '200') {
	    		return data.entity;
	    	}else{
	    		if(data.errorMessage != null){
		    		$("#errorInfo").html(data.errorMessage);
	    		}
	    		$("#errorModal").modal("show");
	    	}
	    },
	    rownumbers: false,
	    columns:[[
			{ field: 'application', title: '应用名称',width:'200', align: 'center',
				formatter: function(value,row,index) {
					return value.chname;
				} 
			},
			{ field: 'createdTime', title: '购买时间',width:'150' ,align: 'center',
	        	formatter: function(value, row, index) {
	        		return getSmpFormatDateByLong(value, false);
	        	}
	        },
			{ field: 'provider', title: '卖方',width:'170' , align: 'center',
				formatter: function(value,row,index) {
					return row.user.userName;
				} 
			},
			{title:'状态',field:'orderStatus',width:'150',align: 'center',
	        	formatter : function(val){
					  switch(val){
					  	case 1 : return '服务中' ;  break; 
						case 2 : return '暂停'; break; 
						case 3 : return '结束'; break; 
						case 4 : return'撤销'; break; 
					  }
	        	}
	        },
			{ field: 'orderNumber', title: '操作',width:'120', align: 'center',
				formatter: function(value,row,index){
					var applicationId=row.application.applicationId;
					var operationHtml ='<a href="javascript:void(0);" class="dg-button" onclick="viewAppOrder(\'' + value+ '\')">订单详情</a>';
					if (row.deployTime ==null && row.deployType == 1)//部署条件  部署未成功   且是后部署
						operationHtml += '<a href="javascript:void(0);" class="dg-button" onclick="deployApp(\'' + applicationId + '\',\'' + value+ '\')">部署</a>';
					if(row.deployTime ==null && row.deployType == 1 && row.application.allowdownload==1){
						var baseApp=row.application.baseApp;
						operationHtml += '<a href="javascript:void(0);" class="dg-button"  onclick="downApp(\'' + applicationId + '\')">下载应用包</a>';}
					return operationHtml;
				}
			}
	              ]],
		singleSelect: true,
	    selectOnCheck: false,
	    checkOnSelect: false,
		pagination:true,
		onLoadSuccess: function() {
			//setTimeout(function(){$("#buy").addClass("off");},1000);
		}
		
	});
}
function deployApp(appId,orderNum){
	$.ajax({
		url:ctx + "rest/applicationpublish/toapplicationdeploy/" + appId,
		success:function(data) {
			var entity=data.entity.applicationEntity;
			var openRaePreFlag=data.entity.openRaePreFlag;
			if (data.status != "200" && data.errorMessage != null) {
				   $("#errorInfo").html(data.errorMessage);
				   $("#errorModal").modal("show");
					return;
				}
			if(openRaePreFlag){
				var lists=data.entity.lists;
					fillRaeValue(entity,orderNum,lists);
			}else{
				showOperMsg("请开通云引擎服务！");
			}
			
		}
	});
}
function downApp(appId){
     window.location.href=ctx + "uploadVersion/download/" + appId+"/";
	

}

/**
 *获取勾选的数据值 
 */
function getSelectedId() {
	var rows = $('#publish-application-dg').datagrid("getChecked");
	var ids = "";
	for ( var i = 0; i < rows.length; i++) {
		var row = rows[i];
		ids += row.applicationId;
		if(i<rows.length-1){
			ids += ",";
		}
	}
	return ids;
}
/**
 * 获取勾选的状态
 */
function getSelectedStatus() {
	var rows = $('#publish-application-dg').datagrid("getChecked");
	var sts = "";
	for ( var i = 0; i < rows.length; i++) {
		var row = rows[i];
		sts += row.status;
		if(i<rows.length-1){
			sts += ",";
		}
	}
	return sts;
}
/**
 * 1.应用下架
 */
function applicationOff() {
	var applicationId = getSelectedId();
	var appStatus = getSelectedStatus();
	if(applicationId != null && applicationId != '') {//是否确定应用下架
		if ( applicationId.length >32) {
			showOperMsg("您单次操作只能执行单个应用下架！");
		} else {
			if ( appStatus == 1 ) {
				applicationOffIs(applicationId);
			} else {
				showOperMsg("该应用还未上架，请重新选择已上架的应用！");
			}		
		}
		
	} else {
		showOperMsg("请至少选择一个应用！");
	}
}
/**
 * 2.应用下架
 * @param applicationId
 */
function applicationOffIs(applicationId) {
	$.messager.confirm('&nbsp;确认下架', '确定下架应用么?', function(r){
		if (r){
			var ul=ctx + "rest/applicationpublish/offApplication/"+applicationId;
			$.ajax({
				url: ul,
				method: 'get',
				success : function(data) {
					if (data.status != "200" && data.errorMessage != null) {
						
						$("#errorInfo").html(data.errorMessage);
		    		    $("#errorModal").modal("show");
					}
					
					publishDatagrid.datagrid("reload");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
			    		$("#errorInfo").html("与服务器连接失败！");
		    		    $("#errorModal").modal("show");
				}
			});
		}
	});
	
}






function viewApplication(appId,status) {
	$.ajax({
		
		url:ctx + "rest/applicationpublish/applicationPublishInfo/" + appId,
		success:function(data) {
			if (data.status == '200' && data.entity) {
				var application = data.entity.applicationEntity;
			
				$("#app-name").html(application.chname);
				$("#app-description").html(application.detailDescription);
				$("#app-address").html(application.subDomain);
				$("#app-maintenance").html(application.maintenanceCosts);
				$("#app-concat-person").html(application.contactPerson);
				$("#app-phone").html(application.contactPhone);
				if ( status == 3) {
					if (application.offShelveDescription != null){
						var detilStr = "<span>下架说明：</span><span>"+application.offShelveDescription+"</span>";
						$("#off-shevlve-description").html(detilStr);
					} else {
						var detilStr = "<span>下架说明：</span><span>&nbsp;</span>";
						$("#off-shevlve-description").html(detilStr);
					}
				}	else{
					$("#off-shevlve-description").html("");
				}			
				var $left=($(window).width()-$('.app_mod_info').width())/2;
			    var $top=($(window).height()-$('.app_mod_info').height())/2;
			    $('.app_mod_info').css({
			        "left":$left,
			        "top":$top
			    });
				$('.gf_ci_mask').show();
				$('.app_mod_info').show();
			}
			
		}
	});
}

$("#app-detail-button").click(function() {
	$('.gf_ci_mask').hide();
	$('.app_mod_info').hide();
});


function updateApplication(appId) {
	$.ajax({
		url:ctx + "rest/applicationpublish/applicationPublishInfo/" + appId,
		success:function(data) {
			if (data.status == '200' && data.entity) {
				var application = data.entity.applicationEntity;
				var auditExplanation = data.entity.auditExplanation;
				var applyExplanation = data.entity.applyExplanation;
				var bassapp=data.entity.bassApp;
				if(bassapp&&""!=bassapp){
					$("#fileurl").val(bassapp);
					
				}
				
				$("#applicationName").val(application.chname);
				$("#briefDescription").val(application.briefDescription);
				$("#detailDescription").val(application.detailDescription);
				
				$("#appVersion").val(application.appVersion);
				$("#maintenanceCosts").val(application.maintenanceCosts);
				$("#contactPerson").val(application.contactPerson);
				$("#contactPhone").val(application.contactPhone);
				$("#applicationId").val(application.applicationId);
				
				//域名
				if(application.subDomain!=""){
					
					$("#uri-inputbefore").val(application.subDomain);
					$("#uri-inputout").val(application.subDomain);
					$("#uri-inputafter").val(application.subDomain);
				}
				var deployTypes=application.deployType;
				$("#publicType").val(deployTypes);
				
				$("#beforepublicType").val(deployTypes);
				//是否允许下载应用包
				var all=application.allowdownload;
				if("1"==all){
					document.getElementById("isallowDownload").checked=true;
				}else{document.getElementById("isallowDownload").checked=false;}
				
				$("#isallow").val(all);
				
				//后部署一定存在  挂包
				$("#applicationId").val(application.applicationId);
				//审核说明
				$("#auditExplanation").val(auditExplanation);
				$("#applyExplanation").val(applyExplanation);//申请说明
				//下架
				$("#applyexpid").val(applyExplanation);//申请说明
				$("#offshdesc").val(application.offShelveDescription);//下架说明
				//文件原始名字
				//$("#fileurl").val(application.baseApp);
				//$("#baseApp").val(bassapp);
				
				//每次修改之前---初始化值
				$("#isWarSu").val("ss");
				
				var status=application.status;
				$("#applicationstatus").val(status);
				if("3"==status){
					document.getElementById("xiajia").style.display="block"; //、被下架应用打开修改界面不能带出下架描述信息
					document.getElementById("shenhe").style.display="none";
				}else{
					document.getElementById("shenhe").style.display="block";
					document.getElementById("xiajia").style.display="none";
				}
				publishTypeChange();//初始化页面东西
				
				var $left=($(window).width()-$('.update_app_mod_info').width())/2;
			    var $top=($(window).height()-$('.update_app_mod_info').height())/2;
			    $('.update_app_mod_info').css({
			        "left":$left,
			        "top":$top
			    });
				
				$('.gf_ci_mask').show();
				$('.update_app_mod_info').show();
			}
		}
	});
}
function uploadsubwar(){
	var fileurl=$("#file").val();
	var FileExtend = ".zip,.war";
	if(fileurl==""){
		showOperMsg("请选择要上传的war包！");
	}else if(FileExtend.indexOf(fileurl.substring(fileurl.lastIndexOf('.')).toLowerCase())==-1){
		showOperMsg("不能上传此类型的应用![支持应用格式为.zip/.war]！");
		
	}else{
		Load("上传应用包");
		 $.ajaxFileUpload({  
	         url:ctx + "uploadVersion/upload", 
	         data : {"key" : "file"}, 
	         secureuri:false,  
	         fileElementId:"file",  
	         dataType: 'json',
	         success: function (data, status){  
	        		if (data.status == '200') {
	        			if(data.errorMessage != null && ""!=data.errorMessage){
				     		$("#errorInfo").html(data.errorMessage);
				     		$("#errorModal").modal("show");
				     	}
						dispalyLoad();
						var str=data.entity;//上传架包成功后，返回的地址
						$("#isWarSu").val("success");
						$("#baseApp").val($("#fileurl").val());
						showOperMsg("war包上传成功！");
					} 
	         },  
	         error: function (data, status, e){
	         }  
	    	});
	}


 }
/**
 * 是否允许下载应用包
 */
function allowDownloadAPP(){
	var isallowDownload=document.getElementById("isallowDownload").checked;
	if(isallowDownload){//是否允许下载应用包(0-不允许，1-允许)
		$("#isallow").val("1");
	}else{
		$("#isallow").val("0");
		
	}
}
$("#app-close-button").click(function() {
	$('.gf_ci_mask').hide();
	$('.update_app_mod_info').hide();
});
/**
 * 修改应用信息   验证表单  修改应用信息 2
 * @returns {Boolean}
 */
function isvalidateupdateForm(){
	
	//update_app_form
		if ($('#update_app_form').valid()) {
		var type=$("#publicType").val();
		var beforepublicTyp=$("#beforepublicType").val();
		if("1"==type){//0:预部署    1：后部署
			$("#uri-input").val($("#uri-inputafter").val());
			
		}else if("0"==type){
			$("#uri-input").val($("#uri-inputbefore").val());
		}else{
			$("#uri-input").val($("#uri-inputout").val());
		}
		$("#uri-value").val($("#uri-input").val());
		if("0"==beforepublicTyp && "1"==type){//如果之前是 预部署，现在改变为后部署   必须上传war包
			var isWarSu=$("#isWarSu").val();
			if("success"!=isWarSu){//没有上传成功，提示上传架包
				showOperMsg("后部署类型，必须上传应用包，请上传！");
				return;
			}
		}
		return true;
	}else{
		return false;
	}
	
}
/**
 * 修改应用信息 1
 */
$("#app-update-button").click(function() {
  if(isvalidateupdateForm()){
		$('.gf_ci_mask').hide();
		$('.update_app_mod_info').hide();
		
		var status=$("#applicationstatus").val();
		if("3"==status){
			$("#applydescapp").val($("#applyexpid").val());
		}else{
			$("#applydescapp").val($("#applyExplanation").val());
		}
		
	$.ajax({
			url:ctx + "rest/applicationpublish/updateApplication",
			data:$("#update_app_form").serialize(),
			method:"post",
			success:function(data) {
				// 刷新
				publishDatagrid.datagrid("reload");
			}
		}); 
  }

});

function cancelApplication(appId) {
	$.ajax({
		url:ctx + "rest/applicationpublish/deleteApplication/" + appId,
		method:"post",
		success:function(data) {
			publishDatagrid.datagrid("reload");
		}
	});
}

function viewAppOrder(orderNumber) {
	$.ajax({
		url:ctx + "rest/userorder/applicationOrderInfo/" + orderNumber + "/2",
		success:function(data) {
			if (data.status == '200') {
				var app = data.entity;
				$("#app-orderNumber").html(app.orderNumber);
				$("#app-applicationName").html(app.application.chname);
				$("#app-userName").html(app.user.userName);
				switch (app.orderStatus){ 
				case 1 : $("#app-orderStatus").html('服务中');  break; 
				case 2 : $("#app-orderStatus").html('暂停'); break; 
				case 3 : $("#app-orderStatus").html('结束'); break; 
				case 4 : $("#app-orderStatus").html('撤销'); break; 
				} 
				$("#app-maintenanceCosts").html(app.maintenanceCosts);
				$("#app-endTime").html(getSmpFormatDateByLong(app.endTime, false));
				$("#app-createdTime").html(getSmpFormatDateByLong(app.createdTime, false));
				$("#app-orderdescription").html(app.orderDescription);

			    $("#buyAppInfoModal").modal("show");
				
			} else{
			    if(data.errorMessage != null){
			        $("#errorInfo").html(data.errorMessage);
			    }
			    $("#errorModal").modal("show");
			}
		}
	});
}

$("#order-info-close").click(function() {
	$("#buyAppInfoModal").modal("hide");
});

function retvalue(){
	$("#fileurl").val($("#file").val());
}
/**
 * 部署类型 改变事件
 */
function publishTypeChange(){
	var type=$("#publicType").val();

	if("0"==type){//0:预部署    1：后部署 3.外部访问
		document.getElementById("divbefore").style.display="block";
		document.getElementById("divout").style.display="none";
		document.getElementById("divafter").style.display="none";
	}else if("1"==type){
		document.getElementById("divbefore").style.display="none";
		document.getElementById("divout").style.display="none";
		document.getElementById("divafter").style.display="block";
	}else{
		document.getElementById("divbefore").style.display="none";
		document.getElementById("divout").style.display="block";
		document.getElementById("divafter").style.display="none";
	}

}


