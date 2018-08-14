<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.nsu.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="renderer" content="webkit|ie-comp|ie-stand"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="css/style.css"/>       
		<link href="assets/css/codemirror.css" rel="stylesheet"/>
		<link rel="stylesheet" href="assets/css/ace.min.css" />
		<link rel="stylesheet" href="font/css/font-awesome.min.css" />
		<!--[if lte IE 8]>
		<link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->
		<script src="js/jquery-1.9.1.min.js"></script>
		<script src="assets/js/bootstrap.min.js"></script>
		<script src="assets/js/typeahead-bs2.min.js"></script> 
		<script src="js/H-ui.js" type="text/javascript"></script>          	
		<script src="assets/layer/layer.js" type="text/javascript" ></script>          
		<title>订单详细</title>
	</head>
  
<body>
<% 	HashMap<String, String> map=(HashMap<String, String>)request.getSession().getAttribute("orederDetail");

 %>
<div class="margin clearfix">
 <div class="Refund_detailed">
    <div class="Numbering">订单编号:<b><%=map.get("orderNum") %></b></div>
    <div class="detailed_style">
     <!--订单信息-->
     <div class="Receiver_style">
     <div class="title_name">订单信息</div>
     <div class="Info_style clearfix">
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2">买家： </label>
         <div class="o_content"><%=map.get("userName") %></div>
        </div>
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 支付方式：</label>
         <div class="o_content">银联</div>
        </div>
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 付款账号：</label>
         <div class="o_content"><%=map.get("payBank") %></div>
        </div>
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2">订单状态：</label>
         <div class="o_content"><%=map.get("orderStatus") %></div>
        </div>    
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 数量：</label>
         <div class="o_content"><%=map.get("count") %>件</div>
        </div>       
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 支付金额：</label>
         <div class="o_content"><%=map.get("totalprice") %>元</div>
        </div>
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 下单日期：</label>
         <div class="o_content"><%=map.get("ordedate") %></div>
        </div>
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 处理日期：</label>
         <%if(map.get("ordedealdate")!=null){ %>
         <div class="o_content"><%=map.get("ordedealdate") %></div>
         <%}else{ %>
         <div class="o_content">等待处理</div>
         <%} %>
        </div>
        
     </div>
    </div>
    <div class="Receiver_style">
    <div class="title_name">送货地址</div>
    <div class="reund_content">
      <%=map.get("orderAddress") %>  
    </div>
    </div>
    
    <!--商品信息-->
    <div class="product_style">
    <div class="title_name">商品信息</div>
    <div class="Info_style clearfix">
      <div class="product_info clearfix">
      <a href="#" class="img_link"><img src="<%=map.get("proImg") %>"/></a>
      <div>
      <a class=""><%=map.get("proName") %></a><br/>
      <p>颜色：<%=map.get("color") %></p>
      <p>款式：<%=map.get("style") %></p>
      <p>数量：<%=map.get("count") %>件</p>
      <p>价格：<b class="price"><i>￥</i><%=map.get("totalprice") %></b></p>  
      <p class="status"><%=map.get("orderStatus") %></p>
      </div>
      </div>
    </div>
    </div>
    </div>    
 </div>
</div>
</body>
</html>

