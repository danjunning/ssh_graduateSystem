<%@page import="cn.nsu.entity.Product"%>
<%@page import="cn.nsu.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.nsu.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>"/>
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/style.css"/>
    <link rel="stylesheet" href="assets/css/ace.min.css" />
    <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
    <link href="assets/css/codemirror.css" rel="stylesheet"/>
    <link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
	<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
   
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<!--[if IE 7]>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->
        <!--[if lte IE 8]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css"/>
    <![endif]-->
		<script src="assets/js/ace-extra.min.js"></script>
		<!--[if lt IE 9]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->
        		<!--[if !IE]> -->
		<script src="assets/js/jquery.min.js"></script>
		<!-- <![endif]-->
        <script src="assets/dist/echarts.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
  </head>
  
<body>
<%  int usercount=0,procount=0,wsocount=0,wrocount=0,rocount=0;
	List<User> userlist=(List<User>)request.getSession().getAttribute("userlist");
	if(userlist!=null){
		usercount=userlist.size();
	}
	List<Product> productlist=(List<Product>)request.getSession().getAttribute("productlist");
	if(productlist!=null){
		procount=productlist.size();
	}
	ArrayList<HashMap>  waitsendorderlist=(ArrayList<HashMap>)request.getSession().getAttribute("waitreceiorderlist");
	if(waitsendorderlist!=null){
		wsocount=waitsendorderlist.size();
	}
	ArrayList<HashMap>  waitreceiorderlist=(ArrayList<HashMap>)request.getSession().getAttribute("waitreceiorderlist");
	if(waitreceiorderlist!=null){
		wrocount=waitreceiorderlist.size();
	}
	ArrayList<HashMap>  receivedorderlist=(ArrayList<HashMap>)request.getSession().getAttribute("receivedorderlist");
	if(receivedorderlist!=null){
		rocount=receivedorderlist.size();
	}
	
 %>
<div class="page-content clearfix">
 <div class="state-overview clearfix">
      <div class="col-lg-3 col-sm-6">
          <div class="panel" >
          <a href="#" title="商城会员">
              <div class="symbol terques" >
                 <i class="icon-user"></i>
              </div>
              <div class="value">
                  <h1><%= userlist.size()%></h1>
                  <p>商城用户</p>
              </div>
              </a>
          </div>
      </div>
      <div class="col-lg-3 col-sm-6">
          <div class="panel">
              <div class="symbol red">
                  <i class="icon-tags"></i>
              </div>
              <div class="value">
                  <h1><%=productlist.size() %></h1>
                  <p>商品数量</p>
              </div>
          </div>
      </div>
      <div class="col-lg-3 col-sm-6">
          <div class="panel">
              <div class="symbol yellow">
                  <i class="icon-shopping-cart"></i>
              </div>
              <div class="value">
              <%if(request.getSession().getAttribute("countorder")!=null){ %>
                  <h1><%=request.getSession().getAttribute("countorder") %></h1>
                  <%}else{ %>
                  <h1>0</h1>
                  <%} %>
                  <p>商城订单</p>
              </div>
          </div>
      </div>
      <div class="col-lg-3 col-sm-6">
          <div class="panel">
              <div class="symbol blue">
                  <i class="icon-bar-chart"></i>
              </div>
              <div class="value">
                  <h1><%=rocount %></h1>
                  <p>交易记录</p>
              </div>
          </div>
      </div>
 </div>
       <!--实时交易记录-->
   <div class="clearfix">

     <div class="Order_Statistics ">
      <div class="title_name"style="text-align: center">订单统计信息</div>
       <table class="table table-bordered" style="margin:0 auto;">
           <tbody>
               <tr><td class="name">商品总数：</td><td class="munber"><a href="#"><%=procount %></a>&nbsp;个</td></tr>
               <tr><td class="name">待发货订单：</td><td class="munber"><a href="#"><%=wsocount %></a>&nbsp;个</td></tr>
               <tr><td class="name">等待收货订单：</td><td class="munber"><a href="#"><%=wrocount %></a>&nbsp;个</td></tr>
               <tr><td class="name">待结算订单：</td><td class="munber"><a href="#"><%=request.getSession().getAttribute("countwaitpayorder") %></a>&nbsp;个</td></tr>
               <tr><td class="name">已成交订单：</td><td class="munber"><a href="#"><%=rocount %></a>&nbsp;个</td></tr>

           </tbody>
      </table>
     </div>

   </div>
</div>
</body>
</html>
  