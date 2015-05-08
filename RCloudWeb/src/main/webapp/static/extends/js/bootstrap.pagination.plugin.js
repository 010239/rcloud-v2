/**
 * bootstrap pagination plugin v1.0
 *
 *说明：此插件是为bootstrap表格分页使用的
 *
 *使用方法如下：
 *		$("#bsdatagrid").bsdatagrid("load", {
 *			url:"bsdatagrid.json",
 *			cols:[
 *			      {"productid":"姓名"},
 *			      {"productname":"电话"},
 *			      {"unitcost":"年龄"}
 *			     ]
 *		});
 *
 *作者:孙志雷  Email:sunzhilei8@163.com
 *
 */
(function($){
	
	/* 所有可调用放在的声明与实现 */
    var methods = {
        /* 加载分页页面的方法 */
		load: function(options){
            return this.each(function() {
            	var thisObj = $(this);
		        if(options.data){
		        	thisObj.empty();
		        	var yu = options.data.total % options.data.rows.length;
		        	var pageTotal = parseInt(options.data.total / options.data.rows.length);
		        	if(yu != 0){
		        		pageTotal = pageTotal + 1;
		        	}
		        	
		        	//判断是否禁用向前翻
		        	var htmlPageStr = "";
		        	if(options.page < 4){
		        		htmlPageStr += "<li class='disabled'><a href='#'>&laquo;</a></li>";
		        	}else{
		        		htmlPageStr += "<li pid='" + 1 + "' ><a href='#'>&laquo;</a></li>";
		        	}
		        	
		        	var startPage = parseInt(options.page) - 2 < 1 ? 1 : parseInt(options.page) - 2;
		        	var endPage = parseInt(options.page) + 2 > pageTotal ? pageTotal : parseInt(options.page) + 2;

		        	if(endPage - startPage < 4){
		        		
		        		if(startPage < 5){
			        		if(endPage - startPage == 2){
				        		endPage = 5;
			        		}else if(endPage - startPage == 3){
				        		endPage = 5;
			        		}
		        		}else{
			        		if(endPage - startPage == 2){
				        		startPage = endPage - 4;
			        		}else if(endPage - startPage == 3){
			        			startPage = endPage - 4;
			        		}
		        		}
		        	}
		        	for( var i = startPage ; i <= endPage ; i ++ ){

		        		if(i == options.page){
				        	htmlPageStr += "<li class='active'><a href='#'>" + i + "</a></li>";
		        		}else{
				        	htmlPageStr += "<li pid='" + i + "' ><a href='javascript:void(0);'>" + i + "</a></li>";
		        		}
		        	}
		        	
		        	//判断是否禁用向前翻
		        	if((parseInt(options.page) + 3) > pageTotal){
		        		htmlPageStr += "<li class='disabled'><a href='#'>&raquo;</a></li>";
		        	}else{
		        		htmlPageStr += "<li pid='" + pageTotal + "'><a href='#'>&raquo;</a></li>";
		        	}
		        	thisObj.append(htmlPageStr);
		     	};
            });
		},
    };
    /* 插件方法入口 */
    $.fn.bspagination = function() {
        var method = arguments[0];
        if(methods[method]) {
            method = methods[method];
            // 我们的方法是作为参数传入的，把它从参数列表中删除，因为调用方法时并不需要它
            arguments = Array.prototype.slice.call(arguments, 1);
        } else if( typeof(method) == 'object' || !method ) {
            method = methods.init;
        } else {
            $.error( 'bspagination ' +  method + ' does not exist on jQuery.pluginName' );
            return this;
        }
        // 用apply方法来调用我们的方法并传入参数
        return method.apply(this, arguments);
    };
})(jQuery);
