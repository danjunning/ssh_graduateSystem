
$(function (){

    $(".icon").addClass("icon-selected");  //初始全选
    // 总价
    function  totalPrices(){
    	var elem = document.getElementById("cart_form");  
        while(elem.hasChildNodes()) //当elem下还存在子节点时 循环继续  
        {  
            elem.removeChild(elem.firstChild);  
        }
        var Totalprices=0;  //总价
        var pricelen=0;     //商品数量
        $("div.order-select .icon.icon-selected").each(function (){
            var   $_this =$(this);
            var len=$_this.length;
            //find() 方法获得当前元素集合中每个元素的后代,通过选择器、jQuery 对象或元素来筛选。
            var price= Number($_this.parents(".grid-order").find(".ui-price-iconleft").text());
            Totalprices+=price;
            pricelen +=len;
            
            //alert($_this.parents(".grid-order").find(".cartId").val());
            var input = document.createElement("input");  //创建input节点
            input.setAttribute('type', 'text');//定义类型是文本输入
            input.setAttribute("name", "shopcartId");
            input.value=$_this.parents(".grid-order").find(".cartId").val();
            document.getElementById('cart_form').appendChild(input); //添加到form中显示    
            
        });
        if(pricelen==0){
            $("button.go-btn").attr("disabled",true)
        }else{
            $("button.go-btn").attr("disabled",false)
        }
        $("span.total_prices").text(pricelen);
        $("span.total-fee").text(Totalprices.toFixed(2));
        
        
    }
    totalPrices();

    //判断当前“减”按钮是否可用
    $("span.ui-number").each(function(){
        if( $(this).children("input").val()=="1"){
            $(this).children("a:first-child").addClass("disabled");
        }
    });

    //减
    $("a.decrease ").on("click",function(){
        var $num=$(this).next("input");
        if(parseInt($num.val())>1){
            $num.val( parseInt($num.val())-1);
            numProdcut($(this),$num.val());
            totalPrices();
        }
        if(parseInt($num.val())==1){
            $(this).addClass("disabled");
        }
        var proAmount=parseInt($num.val());
        var shopcartId=$(this).parent().prev("input").val();
        //alert("shopcartId:"+shopcartId+"  "+proAmount);
        $.ajax({
            url:"changeAmount.action?proAmount="+proAmount+"&shopcartId=?"+shopcartId,
            type: "post",
            success: function (returnValue) {
                //layer.msg('已增加!',{icon:1,time:1000});
            },
            error: function (returnValue) {
                //alert("操作失败！");
            }
        })

    });
    //加
    $("a.increase").on("click",function(){
        var $num=$(this).prev("input");
        $num.val(parseInt($num.val())+1);
        if($num.val()>1){//:jQuery siblings() 方法返回被选元素的所有同胞元素,并且可以使用可选参数来过滤对同胞元素的搜索
            $(this).siblings("a.decrease").removeClass("disabled");
        }
        numProdcut($(this),$num.val());
        totalPrices();
        var shopcartId=$(this).parent().prev("input").val();
        //alert("shopcartId:"+shopcartId);
        var proAmount=parseInt($num.val())
        $.ajax({
            url:"changeAmount.action?proAmount="+proAmount+"&shopcartId="+shopcartId,
            type: "post",
            success: function (returnValue) {
                
            },
            error: function (returnValue) {
                
            }
        })

    });
    //点击选择
    $(".order-select").click(function(){
    	
    	//var len=$(".grid-main").children().length;
        if($(this).hasClass("shop-select")){
            if($(this).children("div").hasClass("icon-selected")){
                $(this).children("div").removeClass("icon-selected");
                $(".icon").removeClass("icon-selected");
                $(".total-right p:first-child").addClass("hide");
                $(".total-right p:last-child").removeClass("hide");
                totalPrices();             
            }else{
                $(".icon").addClass("icon-selected");
                $(this).children("div").addClass("icon-selected");
                $(".total-right p:first-child").removeClass("hide");
                $(".total-right p:last-child").addClass("hide");
                totalPrices();
               
            }
        }
        else if($(this).children("div").hasClass("icon-selected")){
            $(this).children("div").removeClass("icon-selected");
            $("a.shop-select").children("div").removeClass("icon-selected");
            $(".total-right p:first-child").addClass("hide");
            $(".total-right p:last-child").removeClass("hide");
            totalPrices();
        }
        else if(!$(this).children("div").hasClass("icon-selected")){
            $(this).children("div").addClass("icon-selected");
            checkAll();
            totalPrices();
        }
    });

    // 点击清空按钮
    $("p.empty_product").on("click",function (){
        zdconfirm('','你确定清空吗',function(r){
            if(r)
            {
                $(" div.order-list").animate({"opacity":0},400,function (){
                    $(" div.order-list").remove();
                    checkAll();
                    totalPrices();
                });

            }
            else{
                return false;
            }
        });
    });

    //点击删除按钮
    $("p.clear_product").on("click", function () {
        if($("div.icon.icon-selected").length==0){
            alert_mt();
        }
        else{
            zdconfirm('','你确认删除数据吗',function(r){
                if(r)
                {
                    $(" div.icon-selected").parents("div.grid-order").animate({"opacity":0},400,function (){
                        $(" div.icon-selected").parents("div.grid-order").remove();
                        checkAll();
                        totalPrices();
                    });

                }
                else{
                    return false;
                }
            });
        }

    });


    //点击单个 删除按钮
    $("a.opt-delete").on("click", function () {  	
        var $_this=$(this);
        var shopcartId=$(this).next("input").val();
        if($(".grid-main div.icon-selected").length!=0){
            $_this.parents("div.grid-order").animate({"opacity":0},400,function(){
                $_this.parents("div.grid-order").remove();
                checkAll();
                totalPrices();
                $.ajax({
                    url:"deleteCart.action?shopcartId="+shopcartId,
                    type: "post",
                    success: function (returnValue) {
                        //layer.msg('已删除!',{icon:1,time:1000});
                    },
                    error: function (returnValue) {
                        //alert("操作失败！");
                    }
                })
            });
        }
        else{
            $(" div.order-list").animate({"opacity":0},400,function (){
                $(" div.order-list").remove();
                checkAll();
                totalPrices();
            });
        }
    });

    //全选 点击删除后做全选
    function checkAll(){
        var len=$(".grid-main").children().length;
        var checklen =$(".grid-main div.icon-selected").length;
        if(len ==checklen){
            $("a.shop-select").children("div").addClass("icon-selected");
            $(".total-right p:first-child").removeClass("hide");
            $(".total-right p:last-child").addClass("hide");
        }
        if(len == 0 ){
            var skip_home_page="<div class='mui-prompt-empty'>" +
                "</span><ins></ins><div><p>购物车还是空的，</p><p>去挑几件中意的商品吧</p></div><a class='mui-prompt-btn' href='front/home.jsp'>开始购物</a></div>";
            $("#R_Main").empty().append(skip_home_page);


        }
    }

    //单个商品数量的个数 和总价
    function numProdcut(_this,num){
        var numA=  _this.parents("div.order-des").next("div.order-opt").children("div.opt-num");
        numA.text(numA.text().split("x")[0]+"x"+num);
        var numprice = _this.siblings("input[type='hidden']").val();
        var numM= _this.parents("div.order-des").next("div.order-opt").find("span.ui-price-iconleft");
        numM.text(parseFloat(numprice*num).toFixed(2));
    }

    // 结算点击事件
    function Balance(){
        
    }
    $("button.go-btn").on("click",function (){
            $('#cart_form').submit();
            //$('#R_cartForm').submit();
        }
    );

    function alert_mt() {
        var htm ='<div class="alert_mt"><span>您没有选中要删除的商品</span></div>';
        $("body").append(htm);
        $(".alert_mt").fadeIn(100);
        setTimeout(function() {
            $(".alert_mt").fadeOut(100,function (){
                $("div.alert_mt").remove()
            });
        }, 1000);
    }
});