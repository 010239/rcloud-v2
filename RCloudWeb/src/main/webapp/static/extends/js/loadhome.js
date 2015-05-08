$(document).ready(function(){
	$.ajax({
		url: ctx + 'rest/portal/index',
		async:false,
		success: function(data){
			var entity = data.entity;
			
			//加载slide区域--slide
			//var slide = "";
			//for(var i = 0; i < entity.slide.length; i ++){
			//	slide += '<div class="gf_cycle_main"><img src="'+ entity.slide[i].url +'" href="'+ entity.slide[i].href +'" /></div>';
			//}
			//$(".gf_idx_slide").html(slide);
			
			//加载云服务--service
			var service = "";
			for(var i = 0; i < entity.service.length; i ++){
				service += '<li class="gf_serv_3 gf_serv_hover left gf_idx_pr"><a href="' + ctx + entity.service[i].href +'">';
				service += '<p class="gf_serv_bg1 gf_sprite gf_idx_pa gf_serv_bg"><img src="'+ entity.service[i].url +'" style="width:64px;height:64px;" alt=""></p>';
				service += '<p class="gf_fs_26 align_c"><span>云服务</span>'+ entity.service[i].name +'</p>';
				service += '<p class="gf_fs_14 align_c">按量付费：<span>'+ entity.service[i].price1 +'</span>元/小时</p>';
				service += '<p class="gf_fs_14 align_c">包年包月：<span>'+ entity.service[i].price2 +'</span>元/小时</p></a>';
				service += '</li>';
			}
			$(".gf_sul_wrap ul").html(service);
			
			
			
			//加载云商店-- store
			var store = "";
			for(var i = 0; i < entity.store.length; i ++){
	            store += '<li class="gf_store_1 left">'
	            store += '<a href="'+ entity.store[i].href +'" class="gf_store_bg1 gf_sprite gf_store_hover">'
	            store += '<p class="gf_sprite gf_idx_pa"></p>'
	            store += '</a>'
	            store += '<p class="gf_fs_18 align_c">'+ entity.store[i].name +'</p>'
	            store += '</li>'
			}
			$(".gf_store_wrap ul").html(store);
			
			//include("static/extends/js/jquery.cycle.lite.js");
			//include("static/extends/js/jquery.cycle.all.js");
			//include("static/extends/js/index.js");
			//include("static/extends/js/common.js");
			
		}
	})
});

function include(jssrc) {
	$.ajax({
		type : 'GET',
		url : jssrc,
		async : false,
		dataType : 'script'
	});
}