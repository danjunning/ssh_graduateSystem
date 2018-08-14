<%@page import="cn.nsu.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.nsu.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	
	<title>确认订单</title>
    <link href="css/orderconfirm.css" rel="stylesheet"/>
    <link href="layui/css/layui.css" rel="stylesheet" />
    <script src="layui/layui.js"></script>
    <script src="js/jquery-1.11.3.js"></script>
	<script src="assets/layer/layer.js" type="text/javascript" ></script>	
  </head>
 <style>
    hr{margin-left: 100px;width: 1100px}
</style>
<body>
<%	User user=(User)request.getSession().getAttribute("user");
    HashMap<String, Object> map=(HashMap<String, Object>)request.getSession().getAttribute("orderinfo");
    List<String> list=(List<String>)request.getSession().getAttribute("addrelist");
 %>
<div class="layui-header" style="">
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav" style="background: #F5F5F5">
        <li class="layui-nav-item"><a href="front/home.jsp" style="color: #000000">商城首页</a></li>

        <li class="layui-nav-item"><a href="manageList" style="color: #000000">个人中心<span class="layui-badge-dot"></span></a></li>
    </ul>
    <ul class="layui-nav layui-layout-right" style="background: #F5F5F5">
        <li class="layui-nav-item" ><a href="getCart" style="color: #000000">我的购物车<i class="layui-badge"></i></a></li>
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

<div class="web-container">
    <div class="web-logo">
        <div class="imgae-wrap"><img src="images/web-logo.png" style="border: 1px solid #DDDDDD"></div>
    </div>

    <form class="layui-form" >
    <div class="order-address-wrap">
        <span class="order-title">收货地址</span>
        <hr>
        <div class="addr-select">
        <%if(list!=null&&list.size()!=0){ System.out.println("-----"+list.size());%>
            <div class="layui-form-item">
                <label class="layui-form-label">选择地址</label>       
                <div class="layui-input-block">
                <%for(int i=0;i<list.size();i++){ %>
                    <input type="radio" class="address" name="address" value="<%=list.get(i) %>" title="<%=list.get(i) %>" width="500px" lay-filter="checked"><br>
                    <%}%>
                </div>         
            </div>
            <%}else{ %>
            <div class="layui-form-item">
	            <label class="layui-form-label"></label>
	            <div class="layui-input-block"><span style="color:red;font-size: 20px;">您还没有添加过收货地址！！请先去管理中心设置收货地址！！</span></div>
            </div>
                <%} %>
        </div>
    </div>
    </form>

    <div class="order-prd-wrap">
        <span class="order-title">确认订单信息</span>
        <hr>
        <div class="order-prd">
            <div class="or-single" style="">
                <div class="or-header">
                 &nbsp; &nbsp; &nbsp; <span class="sp-num">订单号： <%=map.get("orderNumber") %></span>
                </div>
                <div class="or-img">
                    <a href=""  title="<%=map.get("productName") %>" target="_blank">
                        <img src="<%=map.get("productImage")%>">
                    </a>
                </div>
                <div class="or-name">
                    <h2 ><a class="link-detail"><%=map.get("productName") %></a></h2>
                    <span class="ui-number">颜色：<%=map.get("productColor") %>&nbsp;分类：<%=map.get("productStyle") %></span>
                </div>
                <div class="or-type">
                    <br><span>数量：<%=map.get("productQuantity") %></span><br>
                </div>
                <div class="or-price">
                    <br><p class="count-price">单价： ￥<%=map.get("productPrice") %></p>
                </div>
                <div class="or-detail">
                    <br><a href="">订单详情</a>
                </div>
                <div class="or-operate">
                    <br><p class="count-price">小计： ￥<%=map.get("totalPrice") %></p>
                </div>
            </div>
        </div>
    </div>
	<div style="display:none;">
		<form action="placeOrder" id="ordered">
			<input type="text" name="orderNumber" value="<%=map.get("orderNumber") %>">
			<input type="text" name="totalPrice" value="<%=map.get("totalPrice") %>">
			<input type="text" name="productId" value="<%=map.get("productId") %>">
			<input type="text" name="productName" value="<%=map.get("productName") %>">
			<input type="text" name="productImage" value="<%=map.get("productImage") %>">
			<input type="text" name="productColor" value="<%=map.get("productColor") %>">
			<input type="text" name="productStyle" value="<%=map.get("productStyle") %>">
			<input type="text" name="productQuantity" value="<%=map.get("productQuantity") %>">
			<input type="text" name="address" id="param_address" value="">
		</form>
	</div>
    <div class="submit-wrap">
        <div class="sub-content">
            <div class="sub-address">
               	 <p>寄送至: <span id="show-address"></span></p>
            </div><br>
            <div class="sub-button" >
                <button class="order-sub" id="order_submit">提交订单</button><span>¥<%=map.get("totalPrice") %></span>
            </div>
        </div>
        <hr>
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
        form.on('radio(checked)',function(data){
    	$('#show-address').text($(this).val());
   		 });
        
    });
    $(document).ready(function(){
    	$("input[name=address]:eq(0)").attr("checked",'checked');
    	$('#show-address').text($('input[type="radio"][name="address"]:checked ').val()); 
    	$("#order_submit").click(function(){
			$("#param_address").attr("value",$('input[type="radio"][name="address"]:checked ').val());
			if($("#param_address").val()==''|$("#param_address").val()==undefined){
	         layer.msg("地址不能为空...", {
	            icon: 5,
	            anim: 6
	        	});
	        	return false;
	         }
	 		$('#ordered').submit();
		});
    });  
    

</script>
</html>