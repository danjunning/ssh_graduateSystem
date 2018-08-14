<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.nsu.entity.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>购物车</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta name="aplus-terminal" content="1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="renderer" content="webkit|ie-comp|ie-stand"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
    
    <link rel="stylesheet" href="css/shop_cart.css"/>
    <script src="js/jquery-1.11.3.js"></script>
    <script src="js/confirm.js"></script>
    <script src="js/shop_cart.js"></script>
    <link href="layui/css/layui.css" rel="stylesheet" />
    <script src="layui/layui.js"></script>
	<!-- <script src="js/jquery-1.9.1.min.js"></script> -->
	<script src="assets/js/typeahead-bs2.min.js"></script>
    <script src="js/lrtk.js" type="text/javascript" ></script>
    <script src="assets/js/jquery.dataTables.min.js"></script>
    <script src="assets/js/jquery.dataTables.bootstrap.js"></script>
  </head>
  
  <body>
  <% ArrayList<HashMap> ucartlist=(ArrayList<HashMap>)request.getSession().getAttribute("ucartlist");
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
                <dd><a href="logout" style="color: #000000"><i class="icon-off"></i>退出账号</a></dd>
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
        <img src="images/web-logo.png" style="border: 1px solid white;width: 200px;height: 100px">
    </div>
    <div class="search-center">
        <form action="searchPro.action" style="align-content: center">
            <div class="form-text"><input id="text" name="param_key" type="text" style="height: 34px;" placeholder="请输入要搜索的关键字"></div>
            <div class="form-sub"><input id="submit" type="submit" value="搜索" style="cursor: pointer;"></div>
        </form>
    </div>
    <div class="search-right">
        <img src="images/mob.png">
    </div>
</div>
<div class="hr-line"></div>
<div style="display: none;">
	<form action="balance" id="cart_form">
	</form>
</div>
<div style="margin-left: 120px;margin-top:40px;border: 1px solid #ddd;width: 1000px">
<%if(ucartlist!=null  && ucartlist.size()>0){%>
    <form id="R_cartForm" action="balance"  method="post" target="_self" >    
        <div id="R_Main" >
            <div class="order-list">
                <div class="grid-shop" id="shop725677994" >
                    <div class="grid-shop-hd">
                        <div class="total-shop">
                            <a class="order-select shop-select" ><div class="icon"></div><span class="title">全选</span></a>
                            <div class="total-right"><p class="empty_product">清空</p><p class="hide clear_product" >删除</p> </div>
                        </div>
                    </div>
                    <div  class="grid-bundle grid-bundle-SM_725677994 grid-bundle-shop">
                        <div class="grid-main">
                        <%for(int i=0;i<ucartlist.size();i++){ %>   
                         <div  class="grid-order grid-order-selected" > 
                            <div class="order-select" > <div class="icon"></div></div>
                            <a class="order-link" href="showDetail?productId=<%=ucartlist.get(i).get("productId")%>" title="<%=ucartlist.get(i).get("proName") %>" target="_blank">
                                <img src="<%=ucartlist.get(i).get("proImg") %>">
                            </a>
                            <div class="order-des" >
                                <h2><a href="showDetail?productId=<%=ucartlist.get(i).get("productId")%>"><%=ucartlist.get(i).get("proName") %></a></h2>
                                <input type="hidden" value="<%=ucartlist.get(i).get("shopcartId") %>"/>
                                    <span class="ui-number" >
                                        <a class="decrease " >-</a>
                                        <input type="number" disabled="disabled" class="num" min="1" pattern="[0-9]*" autocomplete="off" value="<%=ucartlist.get(i).get("proAmount") %>"  maxlength="3" />
                                        <a class="increase" >+</a>
                                        <input type="hidden" value="<%=ucartlist.get(i).get("proPrice") %>"/>
                                    </span>
                                <div class="pro-style"><span>颜色：<%=ucartlist.get(i).get("proColor") %></span>&nbsp;&nbsp;<span>款式：<%=ucartlist.get(i).get("proStyle") %></span></div>
                            </div>
                            <% Double price=(Double)ucartlist.get(i).get("proPrice");
                               String num=(String)ucartlist.get(i).get("proAmount");
                               double tolpri=price*Integer.parseInt(num);
                             %>
                            <div class="order-opt" ><div class="opt-num">数量 x<%=ucartlist.get(i).get("proAmount") %></div>
                                <p class="price"><span class="ui-price-icon">￥</span><span class="ui-price-iconleft"> <%=tolpri%></span></p>
                                <a class="opt-delete"></a>
                                <input type="hidden" name="cartId" class="cartId" value="<%=ucartlist.get(i).get("shopcartId") %>"/>
                            </div>
                        </div>
                        <%}%>
                        </div>
                        </div>
                </div>
            </div>
            

            <div class="float-box">
                <div class="float-go">
                    <button type="button" class="go-btn">结算(<span class="total_prices">0</span>)</button>
                </div>
                <div class="float-sum">
                    <p class="fee">
                        <span class="total-title">总价：</span>
                        <span class="total-fee-box"><span class="tc-rmb">￥</span><span class="total-fee">0.00</span></span>
                    </p>
                </div>
            </div>

        </div>
    </form>
    <%}else{ %>
        <center style="border:none;font-size: 25px;color:red;">您的购物车还没有商品！！！</center>
    <%} %>
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
    layui.use('carousel', function(){
        var carousel = layui.carousel;
        //建造实例
        carousel.render({
            elem: '#test1'
            ,width: '1060px' //设置容器宽度
            ,height:'400px'
            ,arrow: 'always' //始终显示箭头
            ,anim: 'fade' //切换动画方式
            ,interval:2000
        });
    });

$(function() {
		var oTable1 = $('#sample-table').dataTable({
		"aaSorting": [],//默认第几个排序
		"bStateSave": false,//状态保存
		//"dom": 't',
		"bFilter":false,
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0,1,2,3,4]}// 制定列不参与排序
		] } );
		
		/* $('table th input:checkbox').on('click' , function(){
			var that = this;
			$(this).closest('table').find('tr > td:first-child input:checkbox')
			.each(function(){
				this.checked = that.checked;
				$(this).closest('tr').toggleClass('selected');
			});			
		}); */
	})
</script>
</html>