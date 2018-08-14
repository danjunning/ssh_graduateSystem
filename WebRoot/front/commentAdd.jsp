<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.nsu.*" %>
<%@page import="cn.nsu.entity.User"%>
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
    <title>评价</title>
    <link href="css/payresult.css" rel="stylesheet">
    <link href="layui/css/layui.css" rel="stylesheet" />
    <script src="js/jquery-1.11.3.js"></script> <!-- 引入jQuery1.11.3 -->
    <script src="layui/layui.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript" ></script>

  </head>
 <style>
    hr{margin-left: 100px;width: 1100px}
    .comment-wrap{
        margin-top: 50px;height: 400px;
        background-color: #FAFAFA;
        margin-left: 100px;
        width: 1100px;
        -webkit-box-shadow: #999 0px 0px 8px;
        -moz-box-shadow: #999 0px 0px 8px;
        box-shadow: #999 0px 0px 8px;
    }
    textarea{
        background-color: white;
        margin-left: 100px;
        margin-top: 60px;
        width: 600px;
        height:200px;
        border: 1px solid #ABADB3;
    }
    .comment-wrap p{margin-left: 100px}
    .comment-wrap p input.com-sub{
        border: 1px solid #C82121;
        border-radius: 4px;
        margin-top: 10px;
        margin-left: 230px;
        width: 80px;
        height: 28px;
        color: white;
        font-size: 12px;
        background-color: #C82121;
        letter-spacing: 4px;
        outline:none;
        cursor:pointer;
    }

</style>
<body>
<%	User user=(User)request.getSession().getAttribute("user");

 %>
<div class="layui-header" style="">
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav" style="background: #F5F5F5">
        <li class="layui-nav-item"><a href="front/home.jsp" style="color: #000000">商城首页</a></li>
        <li class="layui-nav-item"><a href="front/personManage.jsp" style="color: #000000">个人中心<span class="layui-badge-dot"></span></a></li>
    </ul>
    <ul class="layui-nav layui-layout-right" style="background: #F5F5F5">
        <li class="layui-nav-item" ><a href="front/shopcart.jsp" style="color: #000000">我购物车<i class="layui-badge"></i></a></li>
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
                <dd><a href="manageList" style="color: #000000">我的管理</a></dd>
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

<div class="web-container">
    <div class="web-logo" style="border:none;">
        <div class="imgae-wrap" style="border:none;"><img src="images/web-logo.png" style="border: 1px solid #DDDDDD"></div>
    </div>
    <form action="" id="form_id">
    	<input type="hidden" id="pro_id" value="<%=request.getParameter("productId")%>">
    	<input type="hidden" id="or_id" value="<%=request.getParameter("orderId")%>">
    <div class="comment-wrap">
        <p>快来评价吧！</p>

        <textarea class="comment-content"  name="comment" id="com_content">${commentContent }</textarea>

        <p><input type="button" class="com-sub" value="立即评价" onclick="addcom()"></p>
    </div>
    </form>
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
    });
    
    function addcom(){
		var content=$('#com_content').val();
		var orderid=$('#or_id').val();
		var proid=$('#pro_id').val();
		//alert(content+"  "+orderid+"     "+proid);
         if(content==''|content.length==0){
         layer.msg("请输入评价的内容...", {
            icon: 5,
            anim: 6
        	});
        	return false;
         }
         $.ajax({
             url:"addEvaluate.action?productId="+proid+"&orderId="+orderid+"&content="+content,
             type: "post",
             success: function (returnValue) {
                 layer.msg('评价成功!',{icon:1,time:1000});
                 location.href="front/personManage.jsp";
             },
             error: function (returnValue) {
                 alert("操作失败！");
             }
         }) 
         
	}

</script>
</html>