<%@page import="cn.nsu.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单详细</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="css/bootstrap.min.css" rel="stylesheet" />
	<link href="css/orderdetail.css" rel="stylesheet">
	<link href="layui/css/layui.css" rel="stylesheet" />
	<script src="layui/layui.js"></script>

  </head>
<body>
<%	User user=(User)request.getSession().getAttribute("user");
	HashMap<String, String> map=(HashMap<String, String>)request.getSession().getAttribute("orderdetail");

 %>
<div class="layui-header" style="border-bottom: 1px solid red;">
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
            	<img src="http://t.cn/RCzsdCq" class="layui-nav-img"><%=user.getUname() %>
            	<%} %>
            </a>
            <dl class="layui-nav-child">  
                <dd><a href="logout" style="color: #000000">退出账号</a></dd>
            </dl>
            <%}else{ %>
            <a href="javascript:;" style="color: #000000">
            	<img src="http://t.cn/RCzsdCq" class="layui-nav-img">请登录
            </a>
            <dl class="layui-nav-child">  
                <dd><a href="front/login.jsp" style="color: #000000">登录</a></dd>
            </dl>
            <%} %>
        </li>

    </ul>
</div>

<div class="web-logo">
    <div class="imgae-wrap"><img src="images/web-logo.png" style="border: 1px solid #DDDDDD"></div>
</div>

<div style="margin-left: 100px;margin-top: 40px;width:1000px;border:1px solid #DDDDDD">
    <div class="margin clearfix" style="width: 800px">
        <div class="Order_Details_style"><div class="Numbering">订单编号:<b><%=map.get("orderNum") %></b></div></div>
        <div class="detailed_style">
            <!--收件人信息-->
            <div class="Receiver_style">
                <div class="title_name">收件人信息</div>
                <div class="Info_style clearfix" style="height: 50px"><br/>
                    <%=map.get("address") %>
                </div>
            </div>
            <!--订单信息-->
            <div class="product_style">
                <div class="title_name">产品信息</div>
                <div class="Info_style clearfix">
                    <div class="product_info clearfix">
                        <a href="#" class="img_link"><img src="<%=map.get("proImg") %>" /></a>
                          <span>
                          <a href="#" class="name_link"><%=map.get("proName") %></a>
                          <p>款式：<%=map.get("style") %></p>
                          <p>颜色：<%=map.get("color") %></p>
                          <p>数量：<%=map.get("count") %></p>
                          <p>价格：<b class="price"><i>￥</i><%=map.get("totalprice") %></b></p>
                          </span>
                    </div>
                </div>
            </div>
            <!--总价-->
            <div class="Total_style">
                <div class="Info_style clearfix">

                    <div class="col-xs-3">
                        <label class="label_name" > 订单状态： </label>
                        <div class="o_content"><%=map.get("orderstatus") %></div>
                    </div>
                    <div class="col-xs-3">
                        <label class="label_name"> 下单日期： </label>
                        <div class="o_content"><%=map.get("orderdate") %></div>
                    </div>

                    <div class="col-xs-3">
                        <label class="label_name" > 处理日期： </label>
                        <%if(map.get("orderdealdate")!=null){ %>
                        <div class="o_content"><%=map.get("orderdealdate") %></div>
                        <%}else{ %>
                        <div class="o_content">待处理</div>
                        <%} %>
                    </div>
                </div>
                <div class="Total_m_style">
                    <span class="Total_price">总价：<b><%=map.get("totalprice") %></b>元</span>
                </div>
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
    layui.use(['form', 'element'], function(){
        var element = layui.element,form=layui.form;
        //…
    });

</script>
</html>