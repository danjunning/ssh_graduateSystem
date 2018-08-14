<%@page import="cn.nsu.entity.Subtype"%>
<%@page import="cn.nsu.entity.User"%>
<%@page import="cn.nsu.entity.Advertise"%>
<%@page import="cn.nsu.entity.Product"%>
<%@page import="cn.nsu.entity.Type"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.nsu.*" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>首页</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="layui/css/layui.css" rel="stylesheet" type="text/css"/>
    <script src="layui/layui.js"></script>
    <script src="js/jquery-1.11.3.js"></script>
    <link href="css/homefront.css" rel="stylesheet" type="text/css">
  </head>
 <style>
 .classify-div ul li:hover{background:white;color: black;}
  .menu-categorylist{
  		display:none;
 	    position:absolute;
 	    z-index:999;
 	    top:207px;
 	    left:254px;
 		width:600px;
 		height:400px;
 	    background-color: white; 
 	    /* background: rgba(00,22,33,0);  */
 	    -webkit-box-shadow: #999 0px 0px 8px;
        -moz-box-shadow: #999 0px 0px 8px;
        box-shadow: #999 0px 0px 8px; 
 	}
 	.meli{margin:15px;}
 	.meli a{line-height:25px;}
 	.meli a:hover{font-style:oblique; color:black;text-decoration:underline;}

	.floor-nav-a{margin-left:250px;;font-size: 14px;color: black;}
	.floor-nav-a a{margin-left:30px;font-size: 20px;}
	.floor-nav-a a:HOVER {color:red;}
</style>
<body>
<%	User user=(User)request.getSession().getAttribute("user");
	ArrayList<HashMap> advertiselist=(ArrayList<HashMap>)request.getSession().getAttribute("front_advertiselist");
	List<Type> Firtypelist=(List<Type>)request.getSession().getAttribute("Firtypelist");
	List<Subtype> Subtypelist=(List<Subtype>)request.getSession().getAttribute("Subtypelist");
	ArrayList<HashMap> phonelist=(ArrayList<HashMap>)request.getSession().getAttribute("phonelist");
	ArrayList<HashMap> computerlist=(ArrayList<HashMap>)request.getSession().getAttribute("computerlist");
	ArrayList<HashMap> camerolist=(ArrayList<HashMap>)request.getSession().getAttribute("camerolist");
	ArrayList<HashMap> wearlist=(ArrayList<HashMap>)request.getSession().getAttribute("wearlist");
	
 %>


<div class="layui-header" style="">
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav" style="background: #F5F5F5">
        <li class="layui-nav-item"><a href="front/home.jsp" style="color: #000000">商城首页</a></li>
        <li class="layui-nav-item">
            <a href="manageList" style="color: #000000">个人中心</a>
        </li>
    </ul>
    
    <ul class="layui-nav layui-layout-right" style="background: #F5F5F5">	
        <li class="layui-nav-item" ><a href="getCart" style="color: #000000">购物车<i class="layui-badge">12</i></a></li>
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
                <dd><a href="front/login.jsp" style="color: #000000">退出账号</a></dd>
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
<div id="search-div">
    <div class="search-left">
        <img src="images/web-logo.png" style="width: 200px;height: 100px">
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
<div class="floor-nav" id="home">
	<span class="layui-breadcrumb floor-nav-a" lay-separator="|">
	      <a href="javascript:void(0)" onclick="document.getElementById('home').scrollIntoView();" style="color:black;">首页</a>
	      <a href="javascript:void(0)" onclick="document.getElementById('phone').scrollIntoView();">手机</a>
		  <a href="javascript:void(0)" onclick="document.getElementById('computer').scrollIntoView();">电脑</a>
		  <a href="javascript:void(0)" onclick="document.getElementById('camaro').scrollIntoView();">摄影</a>
		  <a href="javascript:void(0)" onclick="document.getElementById('wearsmart').scrollIntoView();">穿戴智能</a>
	</span>		  
</div>
<div class="carousel-div">
    <div class="classify-div" onmouseover="" onmouseout="" id="test">
        <ul class="first-type">
    <%	if(null!=Firtypelist){
    		for(int i=0;i<Firtypelist.size();i++){
    %>
    		<li class="first-type-li">
    		<a href="getProByTyId.action?typeId=<%=Firtypelist.get(i).getTypeId() %>" ><%=Firtypelist.get(i).getTypeName() %><i> > </i></a>
    		
    		</li>
    		<%}}else{ %>
    		
    		<%} %>
        </ul>
    </div>
    <div id="test2" class="menu-categorylist" >
		<div class="layui-breadcrumb meli" lay-separator="|" >
		<%if(!Subtypelist.isEmpty()){for(int i=0;i<Subtypelist.size();i++){ %>
		  <a href="searchProBySubtyId.action?subtypeId=<%=Subtypelist.get(i).getSubtypeId()%>"><%=Subtypelist.get(i).getSubtypeName() %></a>
		<%}} %>
		  
		</div>
    </div>
    <div id="test1" class="layui-carousel" >
        <div carousel-item >
       <%if(advertiselist!=null){for(int i=0;i<advertiselist.size();i++){ %> 
       		<a ><img src="<%=advertiselist.get(i).get("productImg") %>" width="1090" height="400"></a>
         <%System.out.println(""+advertiselist.get(i).get("productImg")); %>
       <%}}else{ %>
            <a href=""><img src="images/lunbo1.png" width="1090" height="400"></a>
            <a href=""><img src="images/lunbo2.png" width="1090" height="400"></a>
            <a href=""><img src="images/lunbo3.png" width="1090" height="400"></a>
            <a href=""><img src="images/lunbo1.png" width="1090" height="400"></a>
            <a href=""><img src="images/3.jpg" width="1090" height="400"></a>
            <%} %>
        </div>
    </div>
    
    
  
    
    
</div>

<div class="floor" style="">
    <div class="floor-week" style="">
        <p id="phone" class="title">手机</p>
        <div class="floor-left" style="">
            <div class="floor-left-top" style="">
                <img src="images/fphone.png" width="240" height="310">
            </div>
            <div class="floor-left-bottom" style="">
            	<!-- 分类 -->
            </div>
        </div>
        <div class="floor-right" style="">
        <%if(phonelist!=null){for(int i=0;i<phonelist.size();i++){ %>
            <dl>
                <dt><a href="showDetail?productId=<%=phonelist.get(i).get("productId")%>"><img  src="<%=phonelist.get(i).get("productImg") %>" /></a></dt>
                <dd class=""><a href="showDetail?productId=<%=phonelist.get(i).get("productId")%>" title="<%=phonelist.get(i).get("productName")%>"><%=phonelist.get(i).get("productName") %></a><p class="price">￥<%=phonelist.get(i).get("productPrice") %></p></dd>
            </dl>
            <%}}else{ %>
            <%} %>
            
        </div>
    </div>

    <div class="floor-week" style="">
        <p id="computer" class="title">电脑</p>
        <div class="floor-left" style="background-color: #DDE7FF">
            <div class="floor-left-top" style="">
                <img src="images/fcomputer.jpg" width="240" height="310">
            </div>
            <div class="floor-left-bottom" style="">
                <!-- 分类 -->
            </div>
        </div>
        <div class="floor-right" style="">
            <%if(computerlist!=null){for(int i=0;i<computerlist.size();i++){ %>
            <dl>
                <dt><a href="showDetail?productId=<%=computerlist.get(i).get("productId")%>"><img  src="<%=computerlist.get(i).get("productImg") %>" /></a></dt>
                <dd><a href="showDetail?productId=<%=computerlist.get(i).get("productId")%>"><%=computerlist.get(i).get("productName") %></a><p class="price">￥<%=computerlist.get(i).get("productPrice") %></p></dd>
            </dl>
            <%}}else{ %>
            <%} %>
        </div>
    </div>

    <div class="floor-week" style="">
        <p id="camaro" class="title">摄影摄像</p>
        <div class="floor-left" style="background-color: #FFF9E1">
            <div class="floor-left-top" style="">
                <img src="images/fcamaro.jpg" width="240" height="310">
            </div>
            <div class="floor-left-bottom" style="">
                         <!-- 分类 -->
            </div>
        </div>
        <div class="floor-right" style="">
            <%if(camerolist!=null){for(int i=0;i<camerolist.size();i++){ %>
            <dl>
                <dt><a href="showDetail?productId=<%=camerolist.get(i).get("productId")%>"><img  src="<%=camerolist.get(i).get("productImg") %>" /></a></dt>
                <dd><a href="showDetail?productId=<%=camerolist.get(i).get("productId")%>" title="<%=camerolist.get(i).get("productName") %>"><%=camerolist.get(i).get("productName") %></a><p class="price">￥<%=camerolist.get(i).get("productPrice") %></p></dd>
            </dl>
            <%}}else{ %>
            <%} %>

        </div>
    </div>

    <div class="floor-week" style="">
        <p id="wearsmart" class="title">穿戴智能</p>
        <div class="floor-left" style="background: #E7DCFC">
            <div class="floor-left-top" style="">
                <img src="images/fother.jpg" width="240" height="310">
            </div>
            <div class="floor-left-bottom" style="">
                       <!-- 分类 -->
            </div>
        </div>
        <div class="floor-right" style="">
            <%if(wearlist!=null){for(int i=0;i<wearlist.size();i++){ %>
            <dl>
                <dt><a href="showDetail?productId=<%=wearlist.get(i).get("productId")%>"><img  src="<%=wearlist.get(i).get("productImg") %>" /></a></dt>
                <dd><a href="showDetail?productId=<%=wearlist.get(i).get("productId")%>" title="<%=wearlist.get(i).get("productName") %>"><%=wearlist.get(i).get("productName") %></a><p class="price">￥<%=wearlist.get(i).get("productPrice") %></p></dd>
            </dl>
            <%}}else{ %>
            <%} %>

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
<style>

</style>
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
    
   
     $(function () {
           $(".classify-div ul li").hover(function () {
                $(this).children("a").css("color", "black");
                $("#test2").show(); 
            }, function () {
                $(this).children("a").css("color", "white");
                $("#test2").hide();
                
            })
     
            // 鼠标移动到list的div上的时候list div不会被隐藏
            $("#test2").hover(function () {
                $("#test2").show();
            }, function () {
                $("#test2").hide();
            })
        }); 

</script>
</html>
