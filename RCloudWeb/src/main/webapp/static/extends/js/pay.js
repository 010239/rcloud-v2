//模拟radio
(function (){
    $('.gf_pay_ul').on('click','li',function (){
        $('.gf_pay_ul').find('i').removeClass('on');
        $(this).find('i').addClass('on');
    });
})();

