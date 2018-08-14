<%@page import="cn.nsu.entity.User"%>
<%@page import="cn.nsu.entity.Style"%>
<%@page import="cn.nsu.entity.Color"%>
<%@page import="cn.nsu.entity.Image"%>
<%@page import="cn.nsu.entity.Product"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<meta charset="UTF-8">
    <title>商品详细</title>
    <link rel="stylesheet" href="css/shop_cart.css"/>
    <link href="css/prdDet.css" rel="stylesheet">
    <link href="layui/css/layui.css" rel="stylesheet" />
    <script src="layui/layui.js"></script>
    <script src="js/jquery-1.11.3.js"></script>
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/shop.js"></script>
	<style type="text/css">
	.add{border:2px solid red;}
	</style>
  </head>
  
  <body>
 <%	Product product=(Product)request.getSession().getAttribute("product");
 	List<Image> imglist=(List<Image>)request.getSession().getAttribute("imglist");
 	List<Color> colorlist=(List<Color>) request.getSession().getAttribute("colorlist");
 	List<Style> stylelist=(List<Style>)request.getSession().getAttribute("stylelist");
 	ArrayList<HashMap> evaprolist=(ArrayList<HashMap>)request.getSession().getAttribute("evaprolist");
 	User user=(User)request.getSession().getAttribute("user");
  %> 
  
<div class="layui-header" style="">
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav" style="background: #F5F5F5">
        <li class="layui-nav-item"><a href="front/home.jsp" style="color: #000000">商城首页</a></li>
        <li class="layui-nav-item"><a href="manageList" style="color: #000000">个人中心<span class="layui-badge-dot"></span></a></li>

    </ul>
    <ul class="layui-nav layui-layout-right" style="background: #F5F5F5">
        <li class="layui-nav-item" ><a href="getCart" style="color: #000000">我购物车<i class="layui-badge"></i></a></li>
        <li class="layui-nav-item" ><a href="OrderList" style="color: #000000">我的订单</a></li>
        <li class="layui-nav-item"> 
        <%if(user!=null){ %>    	
            <a href="javascript:;" style="color: #000000">
            <%if(user.getPhoto()!=null){ %>
            	<img src="<%=user.getPhoto() %>" class="layui-nav-img"><%=user.getUname() %>
            	<%}else{ %>
            	<img src="http://t.cn/RCzsdCq" class="layui-nav-img">请登录
            	<%} %>
            </a>
            <dl class="layui-nav-child">  
                <dd><a href="logout" style="color: #000000">退出账号</a></dd>
            </dl>
            <%}else{ %>
            <a href="javascript:;" style="color: #000000">
            	<img src="http://t.cn/RCzsdCq" class="layui-nav-img">
            </a>
            <dl class="layui-nav-child">  
                <dd><a href="front/login.jsp" style="color: #000000">登录</a></dd>
            </dl>
            <%} %>
        </li>

    </ul>
</div>
<div id="search-div">
    <div class="search-left">
        <img src="images/web-logo.png" style="width: 250px;height: 100px">
    </div>
    <div class="search-center">
        <form action="#" style="align-content: center">
            <div class="form-text"><input id="text" type="text" style="height:34px;"></div>
            <div class="form-sub"><input id="submit" type="submit" value="搜索"></div>
        </form>
    </div>
    <div class="search-right">
        <img src="images/mob.png">
    </div>
</div>
<hr color="#37A0DB">

<div class="prd-container">
    <div class="prd-left">
        <div class="big-picture">
        <%if(imglist!=null){%>
        <img src="<%=imglist.get(0).getImage() %>" id="big" width="450px" height="430px">
        <%}else{ %>
        <img alt="暂无图片" src="<%=imglist.get(0).getImage() %>" id="big" width="450px" height="450px">
        <%} %>
        </div>
        <div class="picture-list" id="Bigimgs">
            <ul>
            <%if(!imglist.isEmpty()){for(int i=0;i<imglist.size();i++){ %>
                <li><a><img src="<%=imglist.get(i).getImage()%>" width="60" height="60"></a></li>
                <%}}else{%>
                
                <%}%>
            </ul>
        </div>
    </div>
    <div class="prd-right">
        <div class="prd-title">
            <p><%=product.getProductName() %></p>
        </div>
        <div class="prd-price"><br>
            <div class="prd-price-left"><span>价格：</span><b class="price">￥ <%=product.getProductPrice() %></b></div>
            <div class="prd-price-right"><p>销量：<b><%=product.getSaleCount() %></b></p><p class="">评价：<b><%=product.getCommentCount() %></b></p></div>
        </div>

        <div class="prd-info" style="">
            <table>
                <tr>
                    <td class="prd-info-name">评价销量</td>
                    <td class="sale-eva">
                        <span>销量: <%=product.getSaleCount() %></span>
                        <span>评价: <%=product.getCommentCount() %></span>
                    </td>
                </tr>
                <tr>
                    <td class="prd-info-name">颜&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色:</td>
                    <td class="prd-info-data prd-data" id="color">
                        <%if(colorlist!=null){for(int i=0;i<colorlist.size();i++){ %>
                        <span><%=colorlist.get(i).getColor()%></span>
                        <%}}else{ %>
                        <%} %>
                    </td>
                </tr>
                <tr>
                    <td class="prd-info-name">款&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;式:</td>
                    <td class="prd-info-data prd-data" id="style">
                        <%if(stylelist!=null){for(int i=0;i<stylelist.size();i++){ %>
                        <span style=""><%=stylelist.get(i).getStyle() %></span>
                        <%}}else{ %>
                        <%} %>
                    </td>
                </tr>
                <tr>
                    <td class="prd-info-name">数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量:</td>
                    <td class="prd-info-data">
                        <span class="num-dec">－</span>
                        <input class="prd-num" type="text" id="Amount" name="proAmount" value="1"  maxlength="3">
                        <span class="num-inc">＋</span><input id="stock" type="hidden" value="<%=product.getProductStock() %>"/>
                        <span style="margin-top:5px;border:none;">(库存：<%=product.getProductStock() %>)</span>
                        
                    </td>
                </tr>
                <tr>
                    <td class="prd-info-opr" colspan="2">
                        <button class="opr-buy" id="opr-buy">购买</button>
                        <button class="opr-cart" id="opr-cart">计入购物车</button>
                    </td>

                </tr>
            </table>
            <div style="display: none">
            <input type="text" id="price" value="<%=product.getProductPrice() %>">
            	<form action="confirmOrder" id="form_order">
	            	<input type="text" id="oproductId" name="productId" value="<%=product.getProductId() %>">
	            	<input type="text" id="oproductName" name="productName" value="<%=product.getProductName()%>">
	            	<input type="text" id="oproductImage" name="productImage" value="<%=imglist.get(0).getImage() %>">
	            	<input type="text" id="productPrice" name="productPrice" value="<%=product.getProductPrice() %>">
	            	<input type="text" id="totalPrice" name="totalPrice">
	            	<input type="text" id="oproColor" name="productColor" >
	            	<input type="text" id="oproStyle" name="productStyle" >
	            	<input type="text" id="oproAmount" name="productQuantity" >
            	</form>
            	<form action="addCart" id="form_cart">
	            	<input type="text" id="cproductId" name="productId" value="<%=product.getProductId() %>">
	            	<input type="text" id="cproColor" name="proColor" value="">
	            	<input type="text" id="cproStyle" name="proStyle" value="">
	            	<input type="text" id="cproAmount" name="proAmount" value="">
            	</form>
            </div>
        </div>

    </div>

</div>

<div style="clear: both;border: 1px solid white;" >
    <div class="layui-tab" lay-filter="demo" style="margin-top: 100px;margin-left: 50px;" >
        <ul class="layui-tab-title">
            <li class="layui-this">商品详情</li>
            <li>评价</li>

        </ul>
        <div class="layui-tab-content" >
            <div class="layui-tab-item layui-show">
            	<div class="product-detail" style="width: 500px;">
            	<%=product.getProductDetail() %>
            	</div>
            </div>
            <div class="layui-tab-item ">
	        <%if(evaprolist!=null){for(int i=0;i<evaprolist.size();i++){ %>
            	<div class="com-single">
	            	<div class="eval-content">
	            	<span class="sp-content"><%=evaprolist.get(i).get("content") %></span>
	            </div>
	            <div class="eval-time"><p class="p-time"><%=evaprolist.get(i).get("evaDate") %></p></div>
	              <div class="eval-user"><p><%=evaprolist.get(i).get("userName") %></p></div>
	          </div>
	          <%}}if(evaprolist.isEmpty()){ %>	
	          	<div class="com-single">
	            	<div class="eval-content">
	            	<span class="sp-content">还没有评价！！！</span>
	            </div>
	            <div class="eval-time"><p class="p-time"></p></div>
	              <div class="eval-user"><p></p></div>
	          </div>
	          <%}%>
            </div>
        </div>
    </div>
</div>



<div class="footer">
    <div class="foot-on">
        <div><img src="images/foot.png" alt="" style="margin-left: 40px"/></div>
        <div class="foot-item">
            <p>购物指南</p>
            <ul>
                <li><a>账号注册</a></li>
                <li><a>购物流程</a></li>
                <li><a>付款方式</a></li>
            </ul>
        </div>
        <div class="foot-item">
            <p>商城保障</p>
            <ul>
                <li><a>正品保障</a></li>
                <li><a>物流配送</a></li>
                <li><a>24小时送货</a></li>
            </ul>
        </div>
        <div class="foot-item">
            <p>售后服务</p>
            <ul>
                <li><a>先行赔付</a></li>
                <li><a>退款退货流程</a></li>
                <li><a>投诉举报</a></li>
            </ul>
        </div>
        <div class="foot-item">
            <p>关于我们</p>
            <ul>
                <li><a>网站故障报告</a></li>
                <li><a>选机咨询</a></li>
                <li><a>投诉与建议</a></li>
                <li><a>平台与礼品</a></li>
            </ul>
        </div>
        <div class="foot-item" style="width: 300px;border: none">
            <p style="font-weight: bold;color: red;font-size: 25px;width: 250px">400-188-1999</p>
            <p style="font-weight: normal;font-size: 13px;width: 250px">周一至周五：08：00-18:00</p>
            <p style="font-weight: normal;font-size: 13px;width: 250px">24小在线客服</p>
        </div>


    </div>
    <div class="foot-up">
        <div style="text-align: center">
        <span class="layui-breadcrumb" lay-separator="|" >
          <a href="">关于我们</a>
          <a href="">联系我们</a>
          <a href="">产品</a>
          <a href="">论坛</a>
          <a href="">在线答疑</a>
          <a href="">团购</a>
          <a href="">会员俱乐部</a>
        </span>
        </div>
        <p style="text-align: center;color: #999999">
            版权所有 ssh数码商城 © Copyright 2007 - 2016 www.yaya.cn All Rights Reserved.   滇ICP备11005817号-7    滇公网安备53011102000207号
        </p>
        <p style="text-align: center;color: #999999">增值电信业务经营许可证：滇B2-20160011</p>
    </div>
</div>

</body>

<script>
    //注意：导航 依赖 element 模块，否则无法进行功能性操作
    layui.use('element', function(){
        var element = layui.element;

        //…
    });
    //点击小图显示大图
    window.onload=function(){
        var Bigimgs=document.getElementById("Bigimgs");
        var imgs=Bigimgs.getElementsByTagName("img");
        var imgBig=document.getElementById("big");
        for(var i=0;i<imgs.length;i++){
            imgs[i].onclick=function(){
            imgBig.src=this.src;
            }
        }
    }
   
    $(document).ready(function(){
    
  	  $("#color>:first").css("border","1px solid red");
  	  $("#style>:first").css("border","1px solid red");
  	  $("#oproColor").attr("value",$("#color>:first").text());
	  $("#cproColor").attr("value",$("#color>:first").text());
	  $("#oproStyle").attr("value",$("#style>:first").text());
	  $("#cproStyle").attr("value",$("#style>:first").text());
	  $("#color span").click(function(){
			//$("#color span").removeClass("click_add");
			$("#color span").css("border","1px solid #D9D9D9")
				//$(this).addClass("click_add");
				$(this).css("border","1px solid red")
				//alert($(this).html());
				//alert($(this).text());
				$("#oproColor").attr("value",$(this).text());
				$("#cproColor").attr("value",$(this).text());


	             });
	 $("#style span").click(function(){
		//$("#style span").removeClass("click_add");
		$("#style span").css("border","1px solid #D9D9D9")
	  //$(this).addClass("click_add");
	    $(this).css("border","1px solid red");
	    $("#oproStyle").attr("value",$(this).text());
		$("#cproStyle").attr("value",$(this).text());
             }); 
	             
    $("#opr-buy").click(function(){	
 		$("#oproAmount").attr("value",$("#Amount").val());
 		var amount=Number($("#oproAmount").val());
 		var price=parseFloat($("#price").val());
 		/* var id=$('#oproductId').val();
 		var name=$('#oproductName').val();
 		var img=$('#oproductImage').val();		
 		var color=$('#oproColor').val();
 		var style=$('#oproStyle').val(); */
 		var totalprice=amount*price;
 		$("#totalPrice").attr("value",totalprice);
 		//alert(id+","+name+","+img+","+amount+","+price+","+color+","+style+","+totalprice);
 		$('#form_order').submit();

	}); 
	$("#opr-cart").click(function(){
		$("#cproAmount").attr("value",$("#Amount").val());
		//alert($("#cproAmount").val());
 		$('#form_cart').submit();
	});   
	
	             
       });
</script>
</html>
