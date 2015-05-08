/**
 * bootstrap datagrid plugin v1.0
 *
 *说明：此插件是为bootstrap表格动态加载数据使用的(含分页)
 *
 *使用方法如下：
 *		//加载表格数据
 *		$("#bsdatagrid").bsdatagrid("load", {
 *			//是否需要分页
 *			//pagination: "#bspagination",
 *			//page: 5,
 *			//获取表格数据
 *			url: "rest/application/pagination",
 *			cols: [
 *			      	{"applicationId":"applicationId"},
 *			      	{"applicationName":"applicationName"},
 *			      	{"createdTime":"createdTime"},
 *			      	{"shelveTime":"shelveTime"},
 *			      	{"logoPath":"logoPath"},
 *			      	{"status":"status"},
 *			      	{"providerId":"providerId"},
 *			      	{"markDelete":"markDelete"},
 *			      	{"maintenanceCosts":"maintenanceCosts"},
 *			      	{"subDomain":"subDomain"},
 *			      	{"applicationType":"applicationType"},
 *			      	{"baseApp":"baseApp"},
 *			      	{"appVersion":"appVersion"},
 *			      	{"publisher.userName":"publisher.userName"}
 *			      ]
 *		});
 *
 *作者:孙志雷  Email:sunzhilei8@163.com
 *
 */
(function($){
	
	function createTable(thisObj, options, data){
        if(options.cols && data.status == 200){
        	thisObj.empty();

        	var htmlHeaderStr = "<tr>";
        	if(options.cols){
	        	for( var i = 0 ; i < options.cols.length ; i ++ ){
		        	for( var key in options.cols[i] ){
		        		if(key != "formatter"){
			        		htmlHeaderStr += "<th>" + options.cols[i][key] + "</th>";
		        		}
		        	}
	        	}
        	}
        	htmlHeaderStr += "</tr>";
        	thisObj.append(htmlHeaderStr);

        	var htmlContentStr = "";
        	if(data.entity.rows){
            	for( var i = 0 ; i < data.entity.rows.length ; i ++ ){
            		htmlContentStr += "<tr>";
            		
            		for(var c = 0 ; c < options.cols.length ; c ++){
            			for(var colKey in options.cols[c]){
    		        		
            				if(colKey.split(".").length == 2){
    		        			htmlContentStr += "<td>" + data.entity.rows[i][colKey.split(".")[0]][colKey.split(".")[1]] + "</td>";
            				}else{
        		        		if(options.cols[c].formatter){
        		        			if(data.entity.rows[i][colKey]){
            		        			var s = options.cols[c].formatter.call(data.entity.rows[i].formatter, data.entity.rows[i][colKey]);
            		        			htmlContentStr += "<td>" + s + "</td>";
        		        			}
        		        		}else{
        		        			htmlContentStr += "<td>" + data.entity.rows[i][colKey] + "</td>";
        		        		}
            				}
            			}
            		}
            		htmlContentStr += "</tr>";
            	}
        	}
        	thisObj.append(htmlContentStr);
     	}else{
     		alert("未获取到数据");
     	}
	}
	
	function createPagination(thisTableObj, thisObj, options, data){
        if(data.status == 200){
        	thisObj.empty();
        	var pageTotal = parseInt(data.entity.total / options.rowTotal);
        	if(data.entity.total % options.rowTotal != 0){
        		pageTotal = pageTotal + 1;
        	}
        	
        	//判断是否禁用向前翻
        	var htmlPageStr = "";
        	if(parseInt(options.page) <= 10){
        		htmlPageStr += "<li class='disabled'><a href='javascript:void(0);'>&laquo;</a></li>";
        		if(parseInt(options.page) - 1 <= 0){
            		htmlPageStr += "<li class='disabled'><a href='javascript:void(0);'>&lt;</a></li>";
        		}else{
            		htmlPageStr += "<li pid='" + (parseInt(options.page) - 1) + "' ><a href='javascript:void(0);'>&lt;</a></li>";
        		}
        	}else{
        		htmlPageStr += "<li pid='" + 1 + "' ><a href='javascript:void(0);'>&laquo;</a></li>";
            	htmlPageStr += "<li pid='" + (parseInt(options.page) - 1) + "' ><a href='javascript:void(0);'>&lt;</a></li>";
        		
        	}
        	
        	var startPage = 0;
        	var endPage = 0;
        	
        	
        	if(parseInt(options.page) <= 10){
        		startPage = 1;
        		if(pageTotal < 10){
        			endPage = pageTotal;
        		}else{
            		endPage = 10;
        		}
        	}else{
        		if(parseInt(options.page) % 10 == 0){
        			startPage = parseInt(options.page) - 9;
        		}else{
            		startPage = parseInt(parseInt(options.page) / 10) * 10 + 1;
        		}
        		if(startPage + 10 < pageTotal){
        			endPage = startPage + 9;
        		}else{
                	endPage = pageTotal;
        		}
        	}
        	
        	for( var i = startPage ; i <= endPage ; i ++ ){

        		if(i == parseInt(options.page)){
		        	htmlPageStr += "<li class='active'><a href='javascript:void(0);'>" + i + "</a></li>";
        		}else{
		        	htmlPageStr += "<li pid='" + i + "' ><a href='javascript:void(0);'>" + i + "</a></li>";
        		}
        	}
        	
        	//判断是否禁用向前翻
        	if(parseInt(options.page) < pageTotal){
        		if(parseInt(options.page) + 1 > pageTotal){
            		htmlPageStr += "<li class='disabled'><a href='javascript:void(0);'>&gt;</a></li>";
        		}else{
            		htmlPageStr += "<li pid='" + (parseInt(options.page) + 1) + "' ><a href='javascript:void(0);'>&gt;</a></li>";
        		}
        		htmlPageStr += "<li pid='" + pageTotal + "'><a href='javascript:void(0);'>&raquo;</a></li>";
        	}else{
        		htmlPageStr += "<li class='disabled'><a href='javascript:void(0);'>&gt;</a></li>";
        		htmlPageStr += "<li class='disabled'><a href='javascript:void(0);'>&raquo;</a></li>";
        	}
        	thisObj.append(htmlPageStr);
    		//注册点击页码事件
        	thisObj.find("li").each(function(){
    	    	$(this).bind("click",function(){
    	    		if($(this).attr("pid")){
    	    			if($(options.pagination)){
        	    			//加载表格数据
        	    			thisTableObj.bsdatagrid("load", {
        	    				//是否需要分页
        	    				pagination: options.pagination,
        	    				page: $(this).attr("pid"),
        	    				rowTotal: options.rowTotal,
        	    				//获取表格数据
        	    				url: options.url,
        	    				cols: options.cols
        	    			});
    	    			}else{
        	    			//加载表格数据
        	    			thisTableObj.bsdatagrid("load", {
        	    				//获取表格数据
        	    				url: options.url,
        	    				cols: options.cols
        	    			});
    	    			}
    	    		}
    	    	});
    		});
     	};
	}
	
	/* 所有可调用放在的声明与实现 */
    var methods = {
        /* 加载表格数据的方法 */
		load: function(options){
            return this.each(function() {
            	var thisTableObj = $(this);
				var thisPaginObj = $(options.pagination);
    			//请求表格与分页数据
				if(!options.page){
					options.page = 1;
				}
    			$.ajax({
    				url:options.url + "/" + options.page + "/" + options.rowTotal,
    				success:function(data){
    					createTable(thisTableObj, options, data);
    					if(thisPaginObj){
        					createPagination(thisTableObj, thisPaginObj, options, data);
    					}
    				}
    			});
            });
		},
	    /* 加载表格数据的方法 */
		reload: function(options){
	        return this.each(function() {
	        	alert("还未完成");
	        });
		}
    };
    /* 插件方法入口 */
    $.fn.bsdatagrid = function() {
        var method = arguments[0];
        if(methods[method]) {
            method = methods[method];
            // 我们的方法是作为参数传入的，把它从参数列表中删除，因为调用方法时并不需要它
            arguments = Array.prototype.slice.call(arguments, 1);
        } else if( typeof(method) == 'object' || !method ) {
            method = methods.init;
        } else {
            $.error( 'bsdatagrid ' +  method + ' does not exist on jQuery.pluginName' );
            return this;
        }
        // 用apply方法来调用我们的方法并传入参数
        return method.apply(this, arguments);
    };
})(jQuery);
