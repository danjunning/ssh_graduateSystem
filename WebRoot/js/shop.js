$(function(){
    //判断当前“减”按钮是否可用

        if( $("prd-num").val()=="1"){
            $("num-inc").addClass("disabled");
        }

    //减
    $("span.num-dec").on("click",function(){
        var $num=$(this).next("input");
        if(parseInt($num.val())>1){
            $num.val( parseInt($num.val())-1);
        }
        if(parseInt($num.val())==1){
            $(this).addClass("disabled");
            return false;
        }

    });
    //加
    $("span.num-inc").on("click",function(){
    	var stock=parseInt($(this).next("input").val());
        var $num=$(this).prev("input");
        $num.val(parseInt($num.val())+1);
        if($num.val()>1){//:jQuery siblings() 方法返回被选元素的所有同胞元素,并且可以使用可选参数来过滤对同胞元素的搜索
            $(this).siblings("span.num-dec").removeClass("disabled");
        }
        if(parseInt($num.val())==stock){
            $(this).addClass("disabled");
            //$(this).css("cursor","not-allowed");
        }
        if(parseInt($num.val())<stock){
            $(this).removeClass("disabled");
            $(this).css("cursor","pointer")
        }

    });

});