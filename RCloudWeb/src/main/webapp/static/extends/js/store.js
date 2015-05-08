(function (){

    //左侧切换
    $('.gf_store_left dd a').on('click',function (){
        $('.gf_store_left dd a').removeClass('on');
        $(this).addClass('on').parents('dd').css('border-color','#d7d7d7');
        $(this).parents('dd').nextAll('dd').css('border-color','#d7d7d7');
        $(this).parents('dd').prevAll('dd').css('border-color','#149dd4');
    });

    $('.gf_store_left dd').each(function (){
        var $on=$(this).children('a').hasClass('on');
        if($on){
            $(this).prevAll('dd').css('border-color','#149dd4');
            $(this).nextAll('dd').css('border-color','#d7d7d7');
        }
    });

    //翻页
    $('.gf_js_page').on('click',function (){
        $(this).addClass('on').siblings('.gf_js_page').removeClass('on');
    });

})();
