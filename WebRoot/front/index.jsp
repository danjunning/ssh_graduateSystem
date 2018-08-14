<%@page import="javax.jws.soap.SOAPBinding.Use"%>
<%@page import="cn.nsu.entity.Subtype"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.nsu.entity.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>数码商城商品</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">	
    <link href="layui/css/layui.css" rel="stylesheet" />    
    <link href="css/index-cn.css" rel="stylesheet"/>
    <script src="js/jquery-1.11.3.js"></script>
    <script src="layui/layui.js"></script>
	<style type="text/css">
		.unknown_left{clear:both;;float:left;margin-left:40px;width:50px;margin-top:15px;margin-bottom:10px;line-height: 20px;font-size: 15px;text-align: center;}
		.unknown_right{float:right;margin-right: 80px;}
		.unknown_right b{color:red;}
	</style>
  </head>
  
<body>
<%	User user=(User)request.getSession().getAttribute("user"); 
	List<Subtype> subtypelist=(List<Subtype>)request.getSession().getAttribute("subtypelist");
	ArrayList<HashMap> productlist=(ArrayList<HashMap>)request.getAttribute("productlist");
	ArrayList<HashMap> top8list=(ArrayList<HashMap>)request.getSession().getAttribute("top8list");
	Integer Count=(Integer)request.getSession().getAttribute("proCount");
	Double proCount=Count.doubleValue();
 %>

<div class="layui-header" style="border-bottom: #E6E6E6;">
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav" style="background: #F5F5F5">
        <li class="layui-nav-item"><a href="front/home.jsp" style="color: #000000">商城首页</a></li>
        <li class="layui-nav-item">
            <a href="manageList" style="color: #000000">个人中心</a>
        </li>
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
        <img src="images/web-logo.png" style="width: 200px;height: 100px;">
    </div>
    <div class="search-center">
        <form action="searchPro.action" style="align-content: center">
            <div class="form-text"><input id="text" type="text" name="param_key" style="height:34px" placeholder="请输入要搜索的关键字"></div>
            <div class="form-sub"><input id="submit" type="submit" value="搜索" style="param"></div>
        </form>
    </div>
    <div class="search-right">
        <img src="images/mob.png" width="200px">
    </div>
</div>
<div class="unknow">
<span class="unknown_left" style=""></span>
<%if(productlist!=null){ %>
<span class="unknown_right">共:<b>${proCount }</b>&nbsp;件商品</span>
<%}else{ %>
<span class="unknown_right">共:<b>0</b>&nbsp;件商品</span>
<%} %>
</div>
<hr color="#37A0DB">

<div class="category-list" style="margin-left: 40px;">
    <ul class="items">
    <%if(subtypelist!=null){for(int i=0;i<subtypelist.size();i++){ %>
        <li class="category-item">
            <a href="searchProBySubtyId.action?subtypeId=<%=subtypelist.get(i).getSubtypeId()%>"><%=subtypelist.get(i).getSubtypeName() %></a>
        </li>
        <%}}else{ %>
        <%} %>
    </ul>
</div>
<div class="pro-container">
 <div class="pro-container-left">
 <%if(productlist!=null && !productlist.isEmpty()){%>
    <div class="sort-select">
        <ul>
            <li><a href="saleSort?param=<%=request.getAttribute("param")%>&&flag=<%=request.getAttribute("flag")%>">销量</a></li>
            <li><a href="onPriceSort?param=<%=request.getAttribute("param")%>&&flag=<%=request.getAttribute("flag")%>">价格↑</a></li>
            <li><a href="upPriceSort?param=<%=request.getAttribute("param")%>&&flag=<%=request.getAttribute("flag")%>">价格↓</a></li>
        </ul>
    </div>
    
    <div class="product-list" style="border:none;">
    <%for(int i=0;i<productlist.size();i++){ %>
      <dl>
	      <dt><a href="showDetail?productId=<%=productlist.get(i).get("productId")%>"><img src="<%=productlist.get(i).get("productImg") %>" /></a></dt>
	      <dd><a href="showDetail?productId=<%=productlist.get(i).get("productId")%>"><%=productlist.get(i).get("productName") %></a></dd>
	      <dd><span class="price">￥<%=productlist.get(i).get("productPrice") %></span><span class="sale">销量：<%=productlist.get(i).get("productSale") %></span></dd>
      </dl>
        <%}%>     
    </div>
    <div class="product-pages" >
    	<div class="pages" >
    		<a href="pagePro?pageIndex=1&&param=<%=request.getAttribute("param")%>&&param_key=<%=request.getAttribute("param_key") %>&&flag=<%=request.getAttribute("flag")%>&&method=<%=request.getAttribute("method")%>">首页</a>
    		<%for(int i=0;i<Math.ceil((Double)proCount/32);i++){ %>
    		<a href="pagePro?pageIndex=<%=i+1 %>&param=<%=request.getAttribute("param")%>&&param_key=<%=request.getAttribute("param_key")%>&&flag=<%=request.getAttribute("flag")%>&&method=<%=request.getAttribute("method")%>"><%=i+1 %></a>
    		<%} %>
    		<input id="pageSize" type="hidden" value="<%=Math.ceil((Double)proCount/32)%>">
    		<span>跳转到</span>&nbsp;<input name="pageIndex" id="pageIndex" class="pageIndex" onkeyup="value=value.replace(/[^\d]/g,'')">&nbsp;<span>页</span>
    		<a id="link-page-submit" onclick="page('<%=request.getAttribute("param")%>','<%=request.getAttribute("flag")%>','<%=request.getAttribute("method")%>')">确定</a>
    		<a href="pagePro?pageIndex=<%=Math.ceil((Double)proCount/32) %>&param=<%=request.getAttribute("param")%>&&param_key=<%=request.getAttribute("param_key")%>&&flag=<%=request.getAttribute("flag")%>&&method=<%=request.getAttribute("method")%>">尾页</a>
    	</div>
         <input id="keyparam" type="hidden" value="<%=request.getAttribute("param_key") %>">     
   	</div>
   	<%}else{ %> 
       <center style="height: 150px;">抱歉，没有找到相关产品，试试其实他关键字搜索</center>
    <%} %>
 
</div> 
<div class="pro-container-right">
<%if(top8list!=null&&!top8list.isEmpty()){ %>
	<div class="advertise-select"><span>最新上架</span></div>
	<div class="advertise-list">
	
	<%for(int i=0;i<top8list.size();i++){ %>
	<dl>
		<dt><img src="<%=top8list.get(i).get("productImg") %>" /></dt>
        <dd class="adv-name"><a href="showDetail?productId=<%=top8list.get(i).get("productId")%>"><%=top8list.get(i).get("productName") %></a></dd>
        <dd class="adv-price"><span class="price">￥<%=top8list.get(i).get("productPrice") %></span>
            <span class="sale">销量：<%=top8list.get(i).get("productSale") %></span>
        </dd>
	</dl>
	<%} %>
	
	<%}else{ %>
	<%} %>
  </div>
</div>
</div>

<div class="footer" style="">
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
    });
    
    function page(param,flag,method){
    	var pageNum=$("#pageIndex").val();
    	var pageCount=$("#pageSize").val();
    	var param_key=$("#keyparam").val();
    	if(Number(pageNum)>Number(pageCount)){
    		return false;
    	}
    	if(pageNum==''){
    		return false;
    	}
    	if(param_key==""){
    		location.href="pagePro?pageIndex="+pageNum+"&param="+param+"&flag="+flag+"&method="+method;
    	}else{
    		location.href="pagePro?pageIndex="+pageNum+"&param="+param+"&param_key="+param_key+"&flag="+flag+"&method="+method;
    	}
    	
    }

</script>
</html>