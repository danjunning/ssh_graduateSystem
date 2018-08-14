<%@page import="cn.nsu.entity.User"%>
<%@page import="cn.nsu.entity.Credit"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta charset="UTF-8">
    <title>订单支付</title>
    <link href="css/orderpay.css" rel="stylesheet">
    <link href="layui/css/layui.css" rel="stylesheet" />
    <script src="js/jquery-1.11.3.js"></script>
    <script src="layui/layui.js"></script>
    

  </head>
<style>
    hr{margin-left: 100px;width: 1100px}
</style>
<body>
<%	List<Credit> creditList=(List<Credit>)request.getSession().getAttribute("creditList");
	System.out.println(creditList);
	HashMap<String,Object> map=(HashMap<String,Object>)request.getSession().getAttribute("ordermap");
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
        <div class="imgae-wrap"><img src="images/web-logo.png" style=""></div>
    </div>



    <div class="orderpay-wrap">
        <div class="order-msg">
            <p class="msg-reminder">订单已提交，请在 72小时 内完成付款（逾期自动取消）<b>￥<%=map.get("price") %></b></p>
            <p class="msg-order-num">您的订单号：<%=map.get("orderNumber") %> &nbsp;&nbsp;&nbsp;&nbsp;
                商品名称：<%=map.get("productName") %></p>
            <p class="msg-order-addr">收货人人信息：<%=map.get("address") %></p>
        </div>
       <form class="" >
        <div class="order-pay-operate">
            <div class="layui-form-item">
                <label class="layui-form-label">选择银行</label>
                <%if(creditList!=null&&creditList.size()>0){ %>
                <div class="layui-input-block">
                <%for(int i=0;i<creditList.size();i++){ %> 
                    <input type="radio" name="bank" value="<%=creditList.get(i).getCreditNumber()%>"  onchange="my(this,<%=creditList.get(i).getPayPassword()%>)">
                    <%=creditList.get(i).getCreditType()%>  <%=creditList.get(i).getCreditNumber()%><br>   
                    <%System.out.println(creditList.get(i).getCreditNumber()+"---"+creditList.get(i).getPayPassword());     %> 
                    <%} %>    
                </div>
                <%}else{ %>
                <div class="layui-input-block"><p style="font-size: 20px;color:red;">您还没有添加银行卡,无法完成付款!!!</p></div>
                <%} %>
            </div>
            
            <div class="pay-input">
                <p>支付密码：</p>
                <div class="layui-input-inline">
                <%if(creditList!=null){ %>
                	<input type="hidden" id="password" value="<%=creditList.get(0).getPayPassword()%>">
                <%} %>	
                    <input type="password" id="paypsw" name="payPassword" autocomplete="off" class="layui-input">
                </div><br>
                <button class="pay-btn" type="button" id="order_pay">确认付款</button>
               	 ${paymsg }
            </div>
        </div>
        </form>
        <div style="display:none;">
         	<form action="payOrder" id="form_order">
         		<input type="text" name="orderNumber" value="<%=map.get("orderNumber")%>">
         		<input type="text" id="payBank" name="payBank" value="">
         		<input type="text" name="price" value="<%=map.get("price")%>">
         		<input type="text" id="payPassword" name="payPassword">
         	</form>
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
    });
    
    function my(obj,param){
    	alert(param);
    	$('#password').val(param);//改变那个隐藏的input的密码，用于实际输入的判断
    	$("#payBank").val($('input[type="radio"][name="bank"]:checked').val());
		}
    $(document).ready(function(){
    	$("input[name=bank]:eq(0)").attr("checked",'checked');
    	$("#payBank").val($('input[type="radio"][name="bank"]:checked').val());
    	$('#password').val()
    	$("#order_pay").click(function(){ 	
    		var bank=$('#payBank').val();    
		    var payPsw=$('#password').val();//取出隐藏input的用于判断的密码
		    
		    var psw=$('#paypsw').val();//当前输入的密码
			var re=/^\d{6}$/;
			if(bank==""){
	         layer.msg("请选择银行卡...", {
	            icon: 5,
	            anim: 6
	        	});
	        	return false;
	         }
	         if(!(re.test(psw))){
	         layer.msg("请输入6位数字密码...", {
	            icon: 5,
	            anim: 6
	        	});
	        	return false;
	         }
	         if(payPsw != psw){
	         layer.msg("密码错误...", {
	            icon: 5,
	            anim: 6
	        	});
	        	return false;
	         }	
	        $('#payPassword').val($('#paypsw').val());
	        $('#form_order').submit();  	
	        
		});
		
		
    	
    });	
    

</script>
</html>