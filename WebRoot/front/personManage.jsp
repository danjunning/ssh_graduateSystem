<%@page import="cn.nsu.entity.Orders"%>
<%@page import="cn.nsu.entity.Credit"%>
<%@page import="cn.nsu.entity.Address"%>
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
    
    <title>个人管理中心</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<meta charset="UTF-8"/>
    <link href="layui/css/layui.css" rel="stylesheet" />
    <link href="css/orderlist.css" rel="stylesheet"/>
    <link href="css/pingjia.css" rel="stylesheet"/>
    <link rel="stylesheet" href="css/refundlist.css"/>
    <script src="layui/layui.all.js"></script>
    <script src="layui/layui.js"></script>
    <!--必须先引入jQuery1.8或以上版本 -->
    <script src="js/jquery-1.11.3.js"></script> <!-- 引入jQuery1.11.3 -->
    <script src="js/jquery.min.js"></script>
    <script src="js/person.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript" ></script>
  </head>
	
	
<body class="layui-layout-body">
<%	User user=(User)request.getSession().getAttribute("user");
	List<Address> addresslist=(List<Address>)request.getSession().getAttribute("addresslist");
	List<Credit> creditList=(List<Credit>)request.getSession().getAttribute("creditList");
 %>


<div class="layui-layout layui-layout-admin">
    <div class="layui-header" style="background-color: #F5F5F5;">
        <div class="layui-logo"><img src="images/web-logo.png" style="border: 1px solid white;width: 130px;height: 60px;"></div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left" style="background-color: #F5F5F5;">
            <li class="layui-nav-item" style="color: #000000"><a href="front/home.jsp" style="color: #000000">购物首页</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;" style="color: #000000">个人设置</a>
                <dl class="layui-nav-child">
                    <dd><a class="set_img" href="javascript:;">设置头像</a></dd>
                    <dd><a class="modif" href="front/pswModify.jsp">修改密码</a></dd>
                    <dd><a href="javascript:;" onclick="cut(2)">收货地址</a></dd>
                </dl>
            </li>
        </ul>
        
        <ul class="layui-nav layui-layout-right" style="background-color: #F5F5F5;">
       	    <li class="layui-nav-item" ><a href="getCart" style="color: #000000">购物车<i class="layui-badge"></i></a></li>
        	<li class="layui-nav-item" ><a onclick="cut(6)" style="color: #000000;cursor: pointer;">我的订单</a></li>
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
                <dd><a href="logout" style="color: #000000"> 退出账号</a></dd>
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

    <div class="layui-side layui-bg-black" >
        <div class="layui-side-scroll" style="background-color: #ffffff;">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree "  lay-filter="test" style="background-color: #A0CDEB;width: 150px;margin-top: 35px">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;" >基本资料</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;"  onclick="cut(1)">个人信息</a></dd>
                        <dd><a href="javascript:;"  onclick="cut(2)">收货地址</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;" style="color: #000000">银行管理</a>
                    <dl class="layui-nav-child">
                        <dd><a  href="javascript:;"  onclick="cut(3)">信用卡</a></dd>
                        <dd><a  href="javascript:;"  onclick="cut(4)">余额充值</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="getCart.action" style="color: #000000" >购物车<i class="layui-badge"></i></a></li>
                <li class="layui-nav-item"><a href="javascript:;" style="color: #000000" onclick="cut(6)">我的订单<i class="layui-badge"></i></a></li>
                <li class="layui-nav-item"><a href="javascript:;" style="color: #000000" onclick="cut(7)">评价管理</a></li>
                <li class="layui-nav-item"><a href="javascript:;" style="color: #000000" onclick="cut(8)">退款记录</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">

            <div id="d1" class="c1" style="">
                <div style="margin-top: 10px;margin-left: 20px">
                    <form class="layui-form"  id="form_userinfo">
                        <div class="layui-form-item">
                            <label class="layui-form-label">昵称</label>
                            <div class="layui-input-block" style="width: 300px">
                                <input type="text" name="uname" value="<%=user.getUname() %>" lay-verify="uname" placeholder="请输入"  autocomplete="off" class="text-info layui-input " disabled="disabled">
                            </div>

                        </div>
                        <div class="layui-form-item" >
                            <label class="layui-form-label">性别</label>
                            <div class="layui-input-block" style="width: 300px">
                            
                            <%if(user.getSex()!=null){
                             if(user.getSex().equals("男")){ %>
                                <input type="radio" name="sex" value="男" title="男" checked>
                                <input type="radio" name="sex" value="女" title="女" >
                                <%}else{ %>
                                <input type="radio" name="sex" value="男" title="男">
                                <input type="radio" name="sex" value="女" title="女" checked>
                                <%}}else{ %>
                                <input type="radio" name="sex" value="男" title="男">
                                <input type="radio" name="sex" value="女" title="女" checked>
                                <%} %>
                            </div>
                        </div>
                        <div class="layui-form-item" >
                            <label class="layui-form-label">生日</label>
                            <div class="layui-input-inline" style="width: 300px">
                            <%if(user.getBirthday()==null){ %>
                                <input type="text" name="birthday" value="" class="layui-input text-info" lay-verify="birthday" placeholder="yyyy-MM-dd" id="birth">
                                <%}else{ %>
                                <input type="text" name="birthday" value="<%=user.getBirthday() %>" class="layui-input text-info" lay-verify="birthday" placeholder="yyyy-MM-dd" id="birth">
                                
                                <%} %>
                            </div>
                        </div>
                        <div class="layui-form-item" >
                            <label class="layui-form-label">电话</label>
                            <div class="layui-input-inline" style="width: 300px">
                                <input type="number" name="phone" value="<%=user.getMobile()%>" lay-verify="phone" placeholder="请输入电话" autocomplete="off" class="layui-input text-info">
                            </div>
                            <div class="layui-form-mid layui-word-aux"></div>
                        </div>
                        <div class="layui-form-item" >
                            <label class="layui-form-label">邮箱</label>
                            <div class="layui-input-inline" style="width: 300px">
                            <%if(user.getEmail()==null){ %>
                                <input type="email" name="email" value=""  lay-verify="email" placeholder="请输入邮箱" autocomplete="off" class="layui-input text-info">
                                <%}else{ %>
                                <input type="email" name="email" value="<%=user.getEmail() %>"  lay-verify="email" placeholder="请输入邮箱" autocomplete="off" class="layui-input text-info">
                                <%} %>
                            </div>
                            <div class="layui-form-mid layui-word-aux"></div>
                        </div>

                        <div class="layui-form-item" >
                            <label class="layui-form-label">居住地</label>
                            <div class="layui-input-inline" style="width: 300px">
                            <%if(user.getAddress()!=null){ %>
                                <input type="text" name="address" value="<%=user.getAddress()%>" lay-verify="address" placeholder="请输入您的居住地" autocomplete="off" class="layui-input text-info">
                            <%}else{ %>
                              <input type="text" name="address" value="" lay-verify="address" placeholder="请输入您的居住地" autocomplete="off" class="layui-input text-info">
                            <%} %>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn btn-set" id="set" type="button">设置信息</button>
                                <button id="save" type="button" style="display:none;" lay-submit lay-filter="form_userinfo" class="layui-btn layui-btn-primary">保存</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div id="d2" class="c1" style="display:none;height: 1500px">
                <div style="margin-left: 10px;">
                    <form class="layui-form" id="form_address">
                        <div class="layui-form-item">
                            <label class="layui-form-label">所在地区<i style="color: red">*</i></label>
                            <div class="layui-input-block" style="width: 300px">
                                <select id="select" name="province" lay-verify="province" >
                                    <option value=""></option>
                                    <option value="北京市">北京市</option>
                                    <option value="天津市">天津市</option>
                                    <option value="上海市">上海市</option>
                                    <option value="重庆市">重庆市</option>
                                    <option value="河北省">河北省</option>
                                    <option value="山西省">山西省</option>
                                    <option value="辽宁省">辽宁省</option>
                                    <option value="吉林省">吉林省</option>
                                    <option value="云南省">云南省</option>
                                    <option value="贵州省">贵州省</option>
                                    <option value="陕西省">陕西省</option>
                                    <option value="黑龙江省">黑龙江省</option>
                                    <option value="江苏省">江苏省</option>
                                    <option value="浙江省">浙江省</option>
                                    <option value="安徽省">安徽省</option>
                                    <option value="福建省">福建省</option>
                                    <option value="江西省">江西省</option>
                                    <option value="湖南省">湖南省</option>
                                    <option value="四川省">四川省</option>
                                    <option value="山东省">山东省</option>
                                    <option value="广东省">广东省</option>
                                    <option value="河南省">河南省</option>
                                    <option value="甘肃省">甘肃省</option>
                                    <option value="青海省">青海省</option>
                                    <option value="台湾省">台湾省</option>
                                    <option value="内蒙古自治区">内蒙古自治区</option>
                                    <option value="广西壮族自治区">广西壮族自治区</option>
                                    <option value="西藏自治区">西藏自治区</option>
                                    <option value="宁夏回族自治区">宁夏回族自治区</option>
                                    <option value="新疆维吾尔自治区">新疆维吾尔自治区</option>
                                    <option value="香港特别行政区">香港特别行政区</option>
                                    <option value="澳门特别行政区">澳门特别行政区</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text" style="width: 410px">
                            <label class="layui-form-label">详细地址<i style="color: red">*</i></label>
                            <div class="layui-input-block" >
                                <textarea name="address"  placeholder="请输入内容" class="layui-textarea" style="height: 20px"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item" >
                            <label class="layui-form-label">邮编</label>
                            <div class="layui-input-inline" style="width: 300px">
                                <input type="text" name="zipcode"  placeholder="" autocomplete="off" class="layui-input">                             
                            </div>
                            <div class="layui-form-mid layui-word-aux">如不清楚，可以不填此项</div>
                        </div>
                        <div class="layui-form-item" >
                            <label class="layui-form-label">收货人姓名<i style="color: red">*</i></label>
                            <div class="layui-input-inline" style="width: 300px">
                                <input type="text" name="receiver"  placeholder="" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item" >
                            <label class="layui-form-label">电话<i style="color: red">*</i></label>
                            <div class="layui-input-inline" style="width: 300px">
                                <input type="text" name="receiveTel"  placeholder="请输入电话" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn"  type="button" onclick="addAddress()">保存</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div style="width: 800px;margin-top:50px;margin-left: 80px">
                    <table class="layui-table" lay-even  >
                        <colgroup>
                            <col width="80">
                            <col width="80">
                            <col width="200">
                            <col width="100">
                            <col width="80">
                        </colgroup>
                        <thead>
                        <tr>
                            <th>收货人</th>
                            <th>所在地区</th>
                            <th>详细地址</th>
                            <th>电话</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                       <%if(addresslist!=null){for(int i=0;i<addresslist.size();i++){ %>
                       <tr>
                          <td><%=addresslist.get(i).getReceiver() %></td>
                          <td><%=addresslist.get(i).getProvince() %></td>
                          <td><%=addresslist.get(i).getAddress() %></td>
                          <td><%=addresslist.get(i).getReceiveTel() %></td>
                          <td>
                              <span class="layui-breadcrumb" lay-separator="|">
                                <a onclick="deleteAddress(this,'<%=addresslist.get(i).getAddressId()%>')" style="cursor:pointer;">删除</a>
                              </span>
                          </td>
                        </tr>
                       <%}}else{ %>
                            <p>还没有收货地址！！！！</p>
                       <%} %>
                        </tbody>
                    </table>

                </div>
            </div>
            <div id="d3" class="c1" style="display:none;height: 600px" >
                <div style="width: 800px;margin-left: 20px">
                    <table lay-even class="layui-table"   >
                        <colgroup>
                          <col width="100">
                          <col width="100">
                          <col width="100">
                          <col width="50">
                        </colgroup>
                        <thead>
                        <tr>
                          <th>银行</th>
                          <th>卡号</th>
                          <th>余额</th>
                          <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%if(creditList!=null){for(int i=0;i<creditList.size();i++){ %>
                        <tr>
                          <td><%=creditList.get(i).getCreditType() %></td>
                          <td><%=creditList.get(i).getCreditNumber() %></td>
                          <td><%=creditList.get(i).getBalance() %></td>
                          <td><a onclick="unband(this,'<%=creditList.get(i).getCreditId() %>')" style="cursor:pointer;">解除</a><a href=""></a></td>
                        </tr>
                        <%}}else{ %>
                        <%} %>
                        </tbody>
                    </table>

                    <form class="layui-form" >
                        <div class="layui-form-item">
                            <label class="layui-form-label">选择银行</label>
                            <div class="layui-input-block">
                                <input type="radio" name="creditType" value="中国工商银行"><img src="images/black2.png" width="110px" height="45px">
                                <input type="radio" name="creditType" value="中国农业银行"><img src="images/black3.png" width="110px" height="45px">
                                <input type="radio" name="creditType" value="中国银行"><img src="images/black4.png" width="110px" height="45px">
                                <input type="radio" name="creditType" value="中国建设银行"><img src="images/black5.png" width="110px" height="45px">
                                <input type="radio" name="creditType" value="交通银行"><img src="images/black6.png" width="110px" height="45px">
                                <input type="radio" name="creditType" value="招商银行"><img src="images/black10.png" width="110px" height="45px">
                                <input type="radio" name="creditType" value="中国光大银行"><img src="images/black12.png" width="110px" height="45px">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">银行卡号</label>
                            <div class="layui-input-block" style="width: 400px">
                                <input  id="creditNum" type="text" name="creditNumber"  placeholder="请输入卡号" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">初始化金额</label>
                            <div class="layui-input-inline" >
                                <input  id="money" type="text" name="balance" placeholder="请输入金额" autocomplete="off" class="layui-input" >
                            </div>
                            <div class="layui-form-mid layui-word-aux"></div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">设置密码</label>
                            <div class="layui-input-inline" >
                                <input  id="payPsw" type="password" name="payPassword"  placeholder="请输入密码" autocomplete="off" class="layui-input" >
                            </div>
                            <div class="layui-form-mid layui-word-aux">${creditmsg }</div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" type="button" onclick="band()">确定</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div id="d4" class="c1" style="display:none;height: 600px">
                <div style="margin-top: 50px">
                    <form class="layui-form">
                        <div class="layui-form-item"><input id="savemoney_msg" type="hidden" value="${savemoney_msg }">
                            <label class="layui-form-label">输入你的卡</label>
                            <div class="layui-input-inline">
                                <input  type="number" id="cardNum" name="creditNumber" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">${savemoney_msg }</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">输入金额</label>
                            <div class="layui-input-inline">
                                <input type="text" id="cardmoney" name="balance"  autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux"></div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" type="button" onclick="save()">充值</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div id="d5" class="c1" style="display:none;border: 1px solid #000000;height: 600px">
              	 购物车
            </div>
             <div id="d6" class="c1" style="display:none;">
                <div class="layui-tab" lay-filter="mytab">
                    <ul class="layui-tab-title" >
                    	<li class="layui-this"  style="font-weight:bold;font-size: 15px" lay-id="111">全部订单</li>
                    	<li style="font-weight:bold;font-size: 15px" lay-id="222">待付款</li> 
                    	<li style="font-weight:bold;font-size: 15px" lay-id="333">待发货</li>                                  
                        <li style="font-weight:bold;font-size: 15px" lay-id="444">待收货</li>
                        <li style="font-weight:bold;font-size: 15px" lay-id="555">待评价</li>
                    </ul>
                    <div class="layui-tab-content">
                    	<div class="layui-tab-item layui-show"  style=""><input type="hidden" id="order1_msg" value="${order1_go }"/>
                        
<%	ArrayList<HashMap> allorderlistByuser=(ArrayList<HashMap>)request.getSession().getAttribute("allorderlistByuser");
 	Integer allorderlistByuserSize=(Integer)request.getSession().getAttribute("allorderlistByuserSize");
 	Double allorderlistByusersize=allorderlistByuserSize.doubleValue();
 %> 
 							<%if(!allorderlistByuser.isEmpty()){%>
                            <div class="or-list" style="background-color: white">
                            <%for(int i=0;i<allorderlistByuser.size();i++){ %>
                                <div class="or-single" style="">
                                    <div class="or-header">
                                        <span class="sp-date"><%=allorderlistByuser.get(i).get("orderDate") %></span>&nbsp; &nbsp; &nbsp; <span class="sp-num">订单号：<%=allorderlistByuser.get(i).get("orderNum")%></span>
                                    </div>
                                    <div class="or-img">
                                        <a href="showDetail?productId=<%=allorderlistByuser.get(i).get("proId")%>"  title="<%=allorderlistByuser.get(i).get("proName") %>" target="_blank">
                                            <img src="<%=allorderlistByuser.get(i).get("proImg") %>">
                                        </a>
                                    </div>
                                    <div class="or-name">
                                        <p><a class="link-detail" href="showDetail?productId=<%=allorderlistByuser.get(i).get("proId")%>"><%=allorderlistByuser.get(i).get("proName") %></a></p>
                                        <span class="ui-number">颜色：<%=allorderlistByuser.get(i).get("color")%>&nbsp;分类：<%=allorderlistByuser.get(i).get("style")%></span>
                                    </div>
                                    <div class="or-type">
                                        <br><span>数量：<%=allorderlistByuser.get(i).get("count")%></span><br>
                                    </div>
                                    <div class="or-price">
                                        <br><p class="count-price"> ￥<%=allorderlistByuser.get(i).get("totalPrice")%></p>
                                    </div>
                                    <div class="or-detail">
                                       <br><span class="order-state"><%=allorderlistByuser.get(i).get("orderStatus")%></span>
                                        <br><a href="orderDetail?orderId=<%=allorderlistByuser.get(i).get("orderId")%>">订单详情</a>
                                    </div>
                                    <div class="or-operate">                           
	                                    <%if(allorderlistByuser.get(i).get("orderStatus").equals("待付款")){ %>
	                                    <br><a class="pay" href="confirm?orderId=<%=allorderlistByuser.get(i).get("orderId")%>">立即付款</a>
	                                    <%}else if(allorderlistByuser.get(i).get("orderStatus").equals("订单已取消")){ %>
	                                    <br><a class="cancel" onclick="deleOrder(this,'<%=allorderlistByuser.get(i).get("orderNum")%>')">删除订单</a>
	                                    <%}else if(allorderlistByuser.get(i).get("orderStatus").equals("已评价")){ %>
	                                    <br><a class="cancel" onclick="deleOrder(this,'<%=allorderlistByuser.get(i).get("orderNum")%>')">删除订单</a>
	                                    <%}else if(allorderlistByuser.get(i).get("orderStatus").equals("已退款")){ %>
	                                    <br><a class="cancel" onclick="deleOrder(this,'<%=allorderlistByuser.get(i).get("orderNum")%>')">删除订单</a>
	                                    <% }else{if(allorderlistByuser.get(i).get("orderStatus").equals("待付款")){ %>
	                                    <br><a class="cancel" onclick="preCancel(this,orderNumber=<%=allorderlistByuser.get(i).get("orderNum")%>)">取消订单</a>
	                                    	<%}else if(allorderlistByuser.get(i).get("orderStatus").equals("退款申请中")){ %>
	                                    	<%}else{ %>
	                                    <br><a class="cancel" onclick="edCancel(this,orderNumber=<%=allorderlistByuser.get(i).get("orderNum")%>)">取消订单</a>
	                                    <%}} %>
                                    
                                    </div>
                                </div><!-- <div single -->
							<%}%>
                            </div><!-- <div class="or-list" -->
                            <div class="product-pages" style="border:none;">
                            	<div class="pages" style="border:none;">
                            	 <%for(int i=0;i<Math.ceil((Double)allorderlistByusersize/10);i++){ %>
                            		<a href="pageAllorder?pageIndex=<%=i+1%>"><%=i+1 %></a>
                            		<%} %> 
                            		<span>共&nbsp;<%=(int)Math.ceil((Double)allorderlistByusersize/10) %>&nbsp;页</span>
                            	</div>
                            </div>
                            <%}else{ %>
								<p>您暂时还没有暂时没有订单记录 !!!</p>
							<%} %>
                        </div><!-- <div class="layui-tab-item layui-show" style=""> -->
                        <%if(request.getAttribute("order2_go")!=null){ 
                        %>
                        <div class="layui-tab-item class="layui-tab-item layui-show" style="">
                        <%}else{ %>
                        <div class="layui-tab-item class="layui-tab-item" style="">
                        <%} %>
                        
<%	ArrayList<HashMap> unpayorderlistByuer=(ArrayList<HashMap>)request.getSession().getAttribute("unpayorderlistByuer");
	Integer unpayorderlistByuerSize=(Integer)request.getSession().getAttribute("unpayorderlistByuerSize");
 	Double unpayorderlistByuersize=unpayorderlistByuerSize.doubleValue();
 %>                          
 						<%if(!unpayorderlistByuer.isEmpty()){ 							
 						%>  <input type="hidden" id="order2_msg" value="${order2_go }"/>                                                                   
                            <div class="or-list" style="background-color: white">                            
                            	<%for(int i=0;i<unpayorderlistByuer.size();i++){ %>
                                <div class="or-single" style="">
                                    <div class="or-header">
                                        <span class="sp-date"><%=unpayorderlistByuer.get(i).get("orderDate") %></span>&nbsp; &nbsp; &nbsp; <span class="sp-num">订单号：<%=unpayorderlistByuer.get(i).get("orderNum") %></span>
                                    </div>
                                    <div class="or-img">
                                        <a href="showDetail?productId=<%=unpayorderlistByuer.get(i).get("proId")%>"  title="<%=unpayorderlistByuer.get(i).get("proName") %>" target="_blank">
                                            <img src="<%=unpayorderlistByuer.get(i).get("proImg")%>">
                                        </a>
                                    </div>
                                    <div class="or-name">
                                        <p><a class="link-detail" href="showDetail?productId=<%=unpayorderlistByuer.get(i).get("proId")%>"><%=unpayorderlistByuer.get(i).get("proName")%></a></p>
                                        <span class="ui-number">颜色：<%=unpayorderlistByuer.get(i).get("color")%>&nbsp;分类：<%=unpayorderlistByuer.get(i).get("style")%></span>
                                    </div>
                                    <div class="or-type">
                                        <br><span>数量：<%=unpayorderlistByuer.get(i).get("count")%></span><br>
                                    </div>
                                    <div class="or-price">
                                        <br><p class="count-price"> ￥<%=unpayorderlistByuer.get(i).get("totalprice")%></p>
                                    </div>
                                    <div class="or-detail">
                                      <br><span class="order-state"><%=unpayorderlistByuer.get(i).get("orderStatus")%></span>
                                        <br><a href="orderDetail?orderId=<%=unpayorderlistByuer.get(i).get("orderId")%>">订单详情</a>
                                    </div>
                                    <div class="or-operate">
                                        <a class="pay" href="confirm?orderId=<%=unpayorderlistByuer.get(i).get("orderId")%>" >立即付款</a><br>
                                        <a class="cancel" onclick="preCancel(this,orderNumber=<%=unpayorderlistByuer.get(i).get("orderNum")%>)">取消订单</a>
                                    </div>
                                </div><!-- <div class="or-single"  -->
							  <%}%>
                            </div><!-- <div class="or-list" -->
                            <div class="product-pages" style="border:none;">
                            	<div class="pages" style="border:none;">
                            	 <%for(int i=0;i<Math.ceil((Double)unpayorderlistByuersize/10);i++){ %>
                            		<a href="pageUnPayorder?pageIndex=<%=i+1%>"><%=i+1 %></a>
                            		<%} %> 
                            		<span>共&nbsp;<%=(int)Math.ceil((Double)unpayorderlistByuersize/10) %>&nbsp;页</span>
                            	</div>
                            </div>
                            <%}else{ %>
							 	<p> 您还目前没有待付款的订单！！！</p>
							  <%} %>
                        </div>
                        <div class="layui-tab-item class="layui-tab-item layui-show  " style="">            
                        <div><input id="order3_msg" type="hidden" value="${order3_go }">
<%	
	ArrayList<HashMap> waitsendorderlistByuer=(ArrayList<HashMap>)request.getSession().getAttribute("waitsendorderlistByuer");
	Integer waitsendorderlistByuerSize=(Integer)request.getSession().getAttribute("waitsendorderlistByuerSize");
 	Double waitsendorderlistByuersize=waitsendorderlistByuerSize.doubleValue();
 %>   
 						<%if(!waitsendorderlistByuer.isEmpty()){
 							System.out.println(waitsendorderlistByuer.isEmpty());
 						%>                          
                            <div class="or-list" style="background-color: white">
                            <%for(int i=0;i<waitsendorderlistByuer.size();i++){ %>
                                <div class="or-single" style="">
                                    <div class="or-header">
                                        <span class="sp-date"><%=waitsendorderlistByuer.get(i).get("orderDate") %></span>&nbsp; &nbsp; &nbsp; <span class="sp-num">订单号：<%=waitsendorderlistByuer.get(i).get("orderNum") %></span>
                                    </div>
                                    <div class="or-img">
                                        <a href="showDetail?productId=<%=waitsendorderlistByuer.get(i).get("proId")%>"  title="<%=waitsendorderlistByuer.get(i).get("proName") %>" target="_blank">
                                            <img src="<%=waitsendorderlistByuer.get(i).get("proImg")%>">
                                        </a>
                                    </div>
                                    <div class="or-name">
                                        <p><a class="link-detail" href="showDetail?productId=<%=waitsendorderlistByuer.get(i).get("proId")%>"><%=waitsendorderlistByuer.get(i).get("proName")%></a></p>
                                        <span class="ui-number">颜色：<%=waitsendorderlistByuer.get(i).get("color")%>&nbsp;分类：<%=waitsendorderlistByuer.get(i).get("style")%></span>
                                    </div>
                                    <div class="or-type">
                                        <br><span>数量：<%=waitsendorderlistByuer.get(i).get("count")%></span><br>
                                    </div>
                                    <div class="or-price">
                                        <br><p class="count-price"> ￥<%=waitsendorderlistByuer.get(i).get("totalPrice")%></p>
                                    </div>
                                    <div class="or-detail">
                                       	<br><span class="order-state"><%=waitsendorderlistByuer.get(i).get("orderStatus")%></span>
                                        <br><a href="orderDetail?orderId=<%=waitsendorderlistByuer.get(i).get("orderId")%>">订单详情</a>
                                    </div>
                                    <div class="or-operate">
                                        <br><a class="cancel" onclick="edCancel(this,orderNumber=<%=waitsendorderlistByuer.get(i).get("orderNum")%>)">取消订单</a>
                                    </div>
                                </div><!-- <div class="or-single" -->
                                
                              <%}%>
								                               
                            </div><!-- <div class="or-list" -->
                            <div class="product-pages" style="border:none;">
                            	<div class="pages" style="border:none;">
                            	 <%for(int i=0;i<Math.ceil((Double)waitsendorderlistByuersize/10);i++){ %>
                            		<a href="pageWaitSendorder?pageIndex=<%=i+1%>"><%=i+1 %></a>
                            		<%} %> 
                            		<span>共&nbsp;<%=(int)Math.ceil((Double)waitsendorderlistByuersize/10) %>&nbsp;页</span>
                            	</div>
                            </div> 
                            <%}else{ %>
                                 <p>您还没有等待发货的订单！！！</p>
                              <%} %>
                                          
                          </div> 
                            
                        </div>
                        <div class="layui-tab-item class="layui-tab-item layui-show  " style="">            
                        <div><input id="order4_msg" type="hidden" value="${order4_go }">
<%	
	ArrayList<HashMap> waitreceiveorderlistByuer=(ArrayList<HashMap>)request.getSession().getAttribute("waitreceiveorderlistByuer");
	Integer waitreceiveorderlistByuerSize=(Integer)request.getSession().getAttribute("waitreceiveorderlistByuerSize");
 	Double waitreceiveorderlistByuersize=waitreceiveorderlistByuerSize.doubleValue();
 %>   
 						<%if(!waitreceiveorderlistByuer.isEmpty()){
 							System.out.println(waitreceiveorderlistByuer.isEmpty());
 						%>                          
                            <div class="or-list" style="background-color: white">                          
								<% for(int i=0;i<waitreceiveorderlistByuer.size();i++){ %>
                              	<div class="or-single" style="">
                                    <div class="or-header">
                                        <span class="sp-date"><%=waitreceiveorderlistByuer.get(i).get("orderDate") %></span>&nbsp; &nbsp; &nbsp; <span class="sp-num">订单号：<%=waitreceiveorderlistByuer.get(i).get("orderNum") %></span>
                                    </div>
                                    <div class="or-img">
                                        <a href="showDetail?productId=<%=waitreceiveorderlistByuer.get(i).get("proId")%>"  title="<%=waitreceiveorderlistByuer.get(i).get("proName") %>" target="_blank">
                                            <img src="<%=waitreceiveorderlistByuer.get(i).get("proImg")%>">
                                        </a>
                                    </div>
                                    <div class="or-name">
                                        <p><a class="link-detail" href="showDetail?productId=<%=waitreceiveorderlistByuer.get(i).get("proId")%>"><%=waitreceiveorderlistByuer.get(i).get("proName")%></a></p>
                                        <span class="ui-number">颜色：<%=waitreceiveorderlistByuer.get(i).get("color")%>&nbsp;分类：<%=waitreceiveorderlistByuer.get(i).get("style")%></span>
                                    </div>
                                    <div class="or-type">
                                        <br><span>数量：<%=waitreceiveorderlistByuer.get(i).get("count")%></span><br>
                                    </div>
                                    <div class="or-price">
                                        <p class="count-price"> ￥<%=waitreceiveorderlistByuer.get(i).get("totalprice")%></p>
                                    </div>
                                    <div class="or-detail">
                                       	<br><span class="order-state"><%=waitreceiveorderlistByuer.get(i).get("orderStatus")%></span>
                                        <br><a href="orderDetail?orderId=<%=waitreceiveorderlistByuer.get(i).get("orderId")%>">订单详情</a>
                                    </div>
                                    <div class="or-operate">
                                   	 <a class="pay confirm" onclick="confirmReceived(this,<%=waitreceiveorderlistByuer.get(i).get("orderNum") %>)" >确认收货</a>
       	                                 <br><a class="cancel" onclick="edCancel(this,orderNumber=<%=waitreceiveorderlistByuer.get(i).get("orderNum")%>)">取消订单</a>
                                    </div>
                                </div><!-- <div class="or-single" -->
                              <%}%>                               
                            </div><!-- <div class="or-list" -->
                            <div class="product-pages" style="border:none;">
                            	<div class="pages" style="border:none;">
                            	 <%for(int i=0;i<Math.ceil((Double)waitreceiveorderlistByuersize/10);i++){ %>
                            		<a href="pageWaitReceiveorder?pageIndex=<%=i+1%>"><%=i+1 %></a>
                            		<%} %> 
                            		<span>共&nbsp;<%=(int)Math.ceil((Double)waitreceiveorderlistByuersize/10) %>&nbsp;页</span>
                            	</div>
                            </div> 
                            <%}else{ %>
                                 <p>您还没有等待收货的订单！！！</p>
                              <%} %>
                                          
                          </div> 
                            
                        </div>

                        <div class="layui-tab-item class="layui-tab-item" style="">	
                       <input id="order5_msg" type="hidden" value="${order5_go }">
<%	ArrayList<HashMap> receivedorderlistByuser=(ArrayList<HashMap>)request.getSession().getAttribute("receivedorderlistByuser");
 	Integer receivedorderlistByuserSize=(Integer)request.getSession().getAttribute("receivedorderlistByuserSize");
 	Double receivedorderlistByusersize=receivedorderlistByuserSize.doubleValue();
 %>                         <%if(!receivedorderlistByuser.isEmpty()){ System.out.println(receivedorderlistByuser.isEmpty());%> 
                            <div class="or-list" style="background-color: white">
                            <%for(int i=0;i<receivedorderlistByuser.size();i++){ %>
                                <div class="or-single" style="">
                                    <div class="or-header">
                                        <span class="sp-date"><%=receivedorderlistByuser.get(i).get("orderDate")%></span>&nbsp; &nbsp; &nbsp; <span class="sp-num">订单号：<%=receivedorderlistByuser.get(i).get("orderNum")%></span>
                                    </div>
                                    <div class="or-img">
                                        <a href="showDetail?productId=<%=receivedorderlistByuser.get(i).get("proId")%>"  title="<%=receivedorderlistByuser.get(i).get("proName")%>" target="_blank">
                                            <img src="<%=receivedorderlistByuser.get(i).get("proImg")%>">
                                        </a>
                                    </div>
                                    <div class="or-name">
                                        <p><a class="link-detail" href="showDetail?productId=<%=receivedorderlistByuser.get(i).get("proId")%>"><%=receivedorderlistByuser.get(i).get("proName")%></a></p>
                                        <span class="ui-number">颜色：<%=receivedorderlistByuser.get(i).get("color")%>&nbsp;分类：<%=receivedorderlistByuser.get(i).get("style")%></span>
                                    </div>
                                    <div class="or-type">
                                        <br><span>数量：<%=receivedorderlistByuser.get(i).get("count")%></span><br>
                                    </div>
                                    <div class="or-price">
                                        <br><p class="count-price"> ￥<%=receivedorderlistByuser.get(i).get("totalprice")%></p>
                                    </div>
                                    <div class="or-detail">
                                       	 <br><span class="order-state"><%=receivedorderlistByuser.get(i).get("orderStatus")%></span>
                                        <br><a href="orderDetail?orderId=<%=receivedorderlistByuser.get(i).get("orderId")%>">订单详情</a>
                                    </div>
                                    <div class="or-operate">
                                    <%if(receivedorderlistByuser.get(i).get("orderStatus").equals("已评价")){ %>
                                        <br><a class="common-look" href="getEvaluate.action?orderId=<%=receivedorderlistByuser.get(i).get("orderId")%>">查看评价</a>
                                     <%}else{ %>  
                                     	 <br><a class="pro-commont" href="front/commentAdd.jsp?productId=<%=receivedorderlistByuser.get(i).get("proId")%>&&orderId=<%=receivedorderlistByuser.get(i).get("orderId")%>">立即评价</a>
                                        <br><a class="common-look" href="getEvaluate.action?orderId=<%=receivedorderlistByuser.get(i).get("orderId")%>">查看评价</a>
                                        <br><a class="order-cancel" onclick="edCancel(this,orderNumber=<%=receivedorderlistByuser.get(i).get("orderNum")%>)" >取消订单</a>
                                     <%} %> 
                                    </div>
                                </div>
								<%}%>								
                            </div><!-- <div class="or-list" -->
                            <div class="product-pages" style="border:none;">
                            	<div class="pages" style="border:none;">
                            	 <%for(int i=0;i<Math.ceil((Double)receivedorderlistByusersize/10);i++){ %>
                            		<a href="pageReceivedorder?pageIndex=<%=i+1%>"><%=i+1 %></a>
                            		<%} %> 
                            		<span>共&nbsp;<%=(int)Math.ceil((Double)receivedorderlistByusersize/10) %>&nbsp;页</span>
                            	</div>
                            </div>
                            <%}else{ %>
							<div><p>您没有没有需要评价的订单！！！</p></div>
							<%} %>                                                   
                        </div>
                    </div><!-- <div class="layui-tab-content"> -->
                </div><!-- <div class="layui-tab" > -->
            </div><!-- div id=6 --> 
            <div id="d7" class="c1" style="display:none;height: 600px">
                <div class="com-list" style="margin-top: 20px;margin-left: 20px;width:1050px;height: 1000px ">
                    <div class="com-header">
                        <span class="header-con" style="border-bottom: 2px solid red">我 的 评 价</span>
                    </div><input id="evaluate_go" type="hidden" value="${evaluate_go }">
<%	ArrayList<HashMap> evaluatelist=(ArrayList<HashMap>)request.getSession().getAttribute("evaluatelist"); 
	Integer evaluatelistSize=(Integer)request.getSession().getAttribute("evaluatelistSize");
 	Double evalistsize=evaluatelistSize.doubleValue();
%>
                    <%if(!evaluatelist.isEmpty()){for(int i=0;i<evaluatelist.size();i++){ %>
                    <div class="com-single">
	                    <div class="eval-content">
		                    <span class="sp-content"><%=evaluatelist.get(i).get("content") %>
		                    </span>
	                    </div>
	                    <div class="eval-time"><p class="p-time"><%=evaluatelist.get(i).get("evaDate") %></p></div>
	                    <div class="eval-product">
	                        <a class="link-pro" href="showDetail?productId=<%=evaluatelist.get(i).get("proId")%>"><%=evaluatelist.get(i).get("proName") %></a>
	                    </div>
	                    <div class="eval-operate">
	                        <p>
	                            <a onclick="deleteeva(this,<%=evaluatelist.get(i).get("evaId") %>)" style="cursor: pointer;">删除</a>
	                            <a href="getEvaluate.action?orderId=<%=receivedorderlistByuser.get(i).get("orderId")%>">修改</a>
	                        </p>
	                    </div>
                    </div>
                <%} %>     
                <div class="product-pages" style="border:none;">
	              	<div class="pages" style="border:none;">
	              	 <%for(int i=0;i<Math.ceil((Double)evalistsize/10);i++){ %>
	              		<a href="pageEvaluate?pageIndex=<%=i+1%>"><%=i+1 %></a>
	              		<%} %> 
	              		<span>共&nbsp;<%=(int)Math.ceil((Double)evalistsize/10) %>&nbsp;页</span>
	              	</div>
                </div>
                <%}else{ %>
                <P>您还没有任何评价！！！</P>
                <%} %>
                </div>
                
            </div>
            
            <div id="d8" class="c1" style="display:none;height: 600px">
                <div class="re-list" style="background-color: white">
                    <div class="list-header">
                        <span class="header-title" style="border-bottom: 2px solid red">退 款 管 理</span>
                    </div>
<%	ArrayList<HashMap> refundlist=(ArrayList<HashMap>)request.getSession().getAttribute("refundlist");
    Integer refundlistSize=(Integer)request.getSession().getAttribute("refundlistSize");
 	Double refundlistsize=refundlistSize.doubleValue();                
               			if(!refundlist.isEmpty()){
               				for(int i=0;i<refundlist.size();i++){	     
                     %>
                    <div class="refund-single" style="">
                    <input id="refund_go" type="hidden" value="${refund_go }">
                        <div class="re-header">
                            <span class="sp-date"><%=refundlist.get(i).get("dealDate") %>
                            </span>&nbsp; &nbsp; &nbsp; <span class="sp-num">订单号：<%=refundlist.get(i).get("orderNum") %></span>
                        </div>
                        <div class="re-img">
                            <a href="showDetail?productId=<%=refundlist.get(i).get("proId")%>"  title="<%=refundlist.get(i).get("proName") %>" target="_blank">
                                <img src="<%=refundlist.get(i).get("proImg")%>">
                            </a>
                        </div>
                        <div class="re-name">
                            <p><a class="link-detail"><%=refundlist.get(i).get("proName")%></a></p>
                            <span class="ui-number">颜色：<%=refundlist.get(i).get("color")%>&nbsp; 分类：<%=refundlist.get(i).get("style")%></span>
                        </div>
                        <div class="re-price">
                            <br><span class="count-price"> ￥<%=refundlist.get(i).get("totalprice")%></span>
                        </div>
                        <div class="re-time">
                            <br><span>数量：<%=refundlist.get(i).get("count")%></span>
                        </div>
                        <div class="re-operate">
                            <br><span class="re-detail" ><%=refundlist.get(i).get("orderStatus")%></span>
                        </div>
                        <div class="re-operate">
                            <br><a class="re-detail" href="orderDetail?orderId=<%=refundlist.get(i).get("orderId")%>">详情</a>
                            <%if(refundlist.get(i).get("orderStatus").equals("已退款")){ %>
                            <br><a class="re-detail" onclick="deleteor(this,<%=refundlist.get(i).get("orderId")%>)">删除订单</a>
                            <%} %>
                        </div>
                    </div>
                    <%} %>
                    <div class="product-pages" style="border:none;">
	              	<div class="pages" style="border:none;">
	              	 <%for(int i=0;i<Math.ceil((Double)refundlistsize/10);i++){ %>
	              		<a href="pageRefund?pageIndex=<%=i+1%>"><%=i+1 %></a>
	              		<%} %> 
	              		<span>共&nbsp;<%=(int)Math.ceil((Double)refundlistsize/10) %>&nbsp;页</span>
	              	</div>
                </div>
               		 <%}else{ %>
               		 	<div><p>您没有退款的记录！！！</p></div>
               		 <%}%>
                </div><!-- <div class="re-list" -->
                

            </div><!-- <div id="d8" -->
            <div id="d9" class="c1" style="display:none;border: 1px solid #000000;height: 600px">
                9999999999999
            </div>
        </div>




    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->

    </div>
</div>
    <input type="hidden"  id="msg" value="${updatefailmsg }">
</div>

<script src="layui/layui.all.js"></script>
<script>
    layui.use('element', function(){
        var element = layui.element;
 		//获取hash来切换选项卡，假设当前地址的hash为lay-id对应的值
  		var layid = location.hash.replace(/^#mytab=/, '');
  		element.tabChange('mytab', layid); //假设当前地址为：http://a.com#test1=222，那么选项卡会自动切换到“发送消息”这一项
  		//监听Tab切换，以改变地址hash值
  		element.on('tab(mytab)', function(){
   		 location.hash = 'mytab='+ this.getAttribute('lay-id');
  });
    });
   
    
    layui.use('form', function(){
        var form = layui.form;
        
      form.verify({
	  uname: function(value, item){ //value：表单的值、item：表单的DOM对象
	  if(value.length<0|value==''){
	    return '请输入用户名';
	    }
	    if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
	      return '用户名不能有特殊字符';
	    }
	    if(/(^\_)|(\__)|(\_+$)/.test(value)){
	      return '用户名首尾不能出现下划线\'_\'';
	    }
	    if(/^\d+\d+\d$/.test(value)){
	      return '用户名不能全为数字';
	    }
	  },
	  birthday: function(value, item){  
              if (value.length == 0) {  
                  return '请输入生日';  
              }  
              },    
      phone: [/^1[34578]\d{9}$/, '手机必须11位，只能是数字！']  
      ,email: [/^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/, '邮箱格式不对'] 
      ,address : function(value, item){  
        if(value.length<0|value==''){
	    return '请输入地址';
	    } 
       }    		  
	});//form.verify
	
	form.on('submit(form_userinfo)', function(data){
		var uname=$('input[name="uname"]').val();
		var sex=$('input[name="sex"]:checked').val();
		var birthday=$('input[name="birthday"]').val();
		var mobile=$('input[name="phone"]').val();
		var email=$('input[name="email"]').val();
		var address=$('input[name="address"]').val();
		$.ajax({
             url:"saveUserinfo.action?uname="+uname+"&sex="+sex+"&mobile="+mobile+"&email="+email+"&address="+address+"&birthday="+birthday,
             type: "post",
             success: function (returnValue) {
                $('.text-info').attr("disabled", true);
				$('#save').css({'display':'none'});
				$('.text-info').css("border","none");
                 layer.msg('已保存!',{icon:1,time:1000});
             },
             error: function (returnValue) {
                 alert("操作失败！");
             }
         })   
    });//form_userinfo

    
        
    });//layui.use
    
	
    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#birth' //指定元素
        });
    });

    //捕获页
    $('.edit').on('click',function(){
        var index=layer.open({
            //type: 1,
            type:2,
            area: ['600px', '450px'],
            shadeClose: true, //点击遮罩关闭
            //shade: false,
            title: '地址修改',
            //content:$('#edit_page'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            content: ['地址修改.html', 'no'],
            success: function(){

               var index=parent.layer.getFrameIndex(window.name);
               layer.close(index);
            }

        });
        //var a=parent.parent.document.getElementById("applySourceType").value=${param.type};
    });
    $('.modify_psw').on('click',function(){
        var index=layer.open({
            //type: 1,
            type:2,
            area: ['500px', '400px'],
            shadeClose: true, //点击遮罩关闭
            //shade: false,
            title: '修改密码',
            content: ['front/pswModify.jsp', 'no'],
            success: function(){
            	
                var index=parent.layer.getFrameIndex(window.name);
                layer.close(index);
            }

        });        //var a=parent.parent.document.getElementById("applySourceType").value=${param.type};
    });
    $('.set_img').on('click',function(){
        var index=layer.open({
            //type: 1,
            type:2,
            area: ['500px', '400px'],
            shadeClose: true, //点击遮罩关闭
            //shade: false,
            title: '设置头像',
            content: ['front/photoUpload.jsp', 'no'],
            success: function(){
                var index=parent.layer.getFrameIndex(window.name);
                layer.close(index);
            }

        });        //var a=parent.parent.document.getElementById("applySourceType").value=${param.type};
    });
    
     

</script>
<script type="text/javascript">
    function cut(param){
        for(var i =1;i<=8;i++){
            var id =document.getElementById("d"+i);
            id.style.display="none";
            if(i===param)
            {
                id.style.display="block";
            }
        }
    }
</script>
</body>

<script type="text/javascript">
	layui.use(['layer', 'form'], function(){
		var form = layui.form;
        var layer=layui.layer;
	})
	function addAddress(){
    	var province=$('select[name="province"]').val();
		var address=$('textarea[name="address"]').val();
		var zipcode=$('input[name="zipcode"]').val();
		var receiver=$('input[name="receiver"]').val();
		var receiveTel=$('input[name="receiveTel"]').val();
		
    	if(province==''){
    		layer.msg("请输入省份", {
            icon: 5,
            anim: 6
        });
        return false;
    	}
    	if(address==0|address==''){
    		layer.msg("请输入收货详细地址...", {
            icon: 5,
            anim: 6
        });
        return false;
    	}
    	if(receiver==''|receiver.length==0){
    		layer.msg("收货人栏不能为空..", {
            icon: 5,
            anim: 6
        });return false;
        }
        if(/(^\_)|(\__)|(\_+$)/.test(receiver)){
	      layer.msg("收货人栏首尾不能出现下划线\'_\'", {
            icon: 5,
            anim: 6
        });return false;
	    }
	    if(/^[0-9]*$/.test(receiver)){
	      layer.msg("收货人栏不能全是数字", {
            icon: 5,
            anim: 6
        });return false;
	    }
        if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(receiver)){
    		layer.msg("收货人栏不能有特殊字符...", {
            icon: 5,
            anim: 6
        });return false;
    	}
    	if(receiveTel==''|receiveTel.length==0){
    		layer.msg("电话号码栏不能为空...", {
            icon: 5,
            anim: 6
        });
        return false;
        }
        var re=/^1[34578]\d{9}$/;
    	if(!re.test(receiveTel)){
    		layer.msg("请输入正确的电话号码...", {
            icon: 5,
            anim: 6
        });
        return false;
    	}
		
	    $.ajax({
             url:"addAddress.action?province="+province+"&address="+address+"&zipcode="+zipcode+"&receiver="+receiver+"&receiveTel="+receiveTel,
             type: "post",
             success: function (returnValue) {
                $('select[name="province"]').val('');
				$('textarea[name="address"]:checked').val('');
				$('input[name="zipcode"]').val('');
				$('input[name="receiver"]').val('');
				$('input[name="receiveTel"]').val('');
                 layer.msg('已保存!',{icon:1,time:1000});
                 //location.reload();
             },
             error: function (returnValue) {
                 layer.msg('操作失败!',{icon:1,time:1000});
             }
         }) 
    }//form_address
	function deleteAddress(abj,addressId){
		layer.confirm('确认要删除吗？',{icon:0,},function(index){		
		   //location.href="deleteAdver.action?adId="+adId;		
			$.ajax({
             url:"lessAddress.action?addressId="+addressId,
             type: "post",
             success: function (returnValue) {
                $(abj).parents("tr").remove();
                layer.msg('已删除!',{icon:1,time:1000});
             },
             error: function (returnValue) {
                 layer.msg('操作失败!',{icon:1,time:1000});
             }
           })
			
		});              
	}//deleteAddress
	
	function band(){
		var creditType=$('input:radio[name="creditType"]:checked').val();
		var creditNum=$('#creditNum').val();
		var money=$('#money').val();
		var payPsw=$('#payPsw').val();
		if(creditType==null){
	        layer.msg("请选择银行...", {
            icon: 5,
            anim: 6
        });
	        return false;
         }
         var re=/^[0-9]*$/;
         if(!re.test(creditNum)){
         layer.msg("请输入正确的卡号...", {
            icon: 5,
            anim: 6
        	});
        	return false;
         }
         if(creditNum.length!=19|creditNum==''){
         layer.msg("请输入正确的卡号...", {
            icon: 5,
            anim: 6
        	});
        	return false;
         }
         re=/^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/; 
         if(!(re.test(money))){
         layer.msg("请输入正确的金额...", {
            icon: 5,
            anim: 6
        	});
        	return false;
         }
         re=/^\d{6}$/;
         if(!(re.test(payPsw))){
         layer.msg("请输入6位数字密码...", {
            icon: 5,
            anim: 6
        	});
        	return false;
         }
         $.ajax({
             url:"addCredit.action?creditType="+creditType+"&creditNumber="+creditNum+"&balance="+money+"&payPassword="+payPsw,
             type: "post",
             success: function (returnValue) {
				$('input[name="creditType"]').val('');
				$('input[name="creditNumber"]').val('');
				$('input[name="balance"]').val('');
				$('input[name="payPassword"]').val('');
                 layer.msg('已添加!',{icon:1,time:1000});
             },
             error: function (returnValue) {
                 layer.msg('操作失败!',{icon:1,time:1000});
             }
         }) 
         
	}
	function unband(abj,creditId){
		layer.confirm('确认要删除吗？',{icon:0,},function(index){		
		   //location.href="deleteAdver.action?adId="+adId;		
			$.ajax({
             url:"deleteCredit.action?creditId="+creditId,
             type: "post",
             success: function (returnValue) {
                $(abj).parents("tr").remove();
                layer.msg('已删除!',{icon:1,time:1000});
             },
             error: function (returnValue) {
                 layer.msg('操作失败!',{icon:1,time:1000});
             }
           })
			
		});              
	}//deleteAddress
	
	function save(){
		var creditNum=$('#cardNum').val();
		var money=$('#cardmoney').val();
        var re=/^[0-9]*$/;
         if(!re.test(creditNum)){
         layer.msg("请输入正确的卡号...", {
            icon: 5,
            anim: 6
        	});
        	return false;
         }
         if(creditNum.length==0|creditNum==''){
         layer.msg("请输入正确的卡号...", {
            icon: 5,
            anim: 6
        	});
        	return false;
         }
         re=/^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/; 
         if(!(re.test(money))){
         layer.msg("请输入正确的金额...", {
            icon: 5,
            anim: 6
        	});
        	return false;
         }
         $.ajax({
             url:"save.action?creditNumber="+creditNum+"&balance="+money,
             type: "post",
             success: function (returnValue) {
				$('input[name="creditNumber"]').val('');
				$('input[name="balance"]').val('');
                 
             },
             error: function (returnValue) {
                 alert("操作失败！");
             }
         }) 
         
	}
	
	function confirmReceived(obj,orderNumber){
		$.ajax({
             url:"confirmReceived.action?orderNumber="+orderNumber,
             type: "post",
             success: function (returnValue) {
                $(obj).parent().prev().find('.order-state').text("已收货");
                $(obj).css("display","none");
                layer.msg('已确认收货!',{icon:1,time:1000});
             },
             error: function (returnValue) {
                 layer.msg('操作失败!',{icon:1,time:1000});
             }
           })
	}
	function preCancel(obj,orderNumber){
		$.ajax({
             url:"preCancel.action?orderNumber="+orderNumber,
             type: "post",
             success: function (returnValue) {
                $(obj).parent().parent().remove();
                layer.msg('已取消订单!',{icon:1,time:1000});
             },
             error: function (returnValue) {
                 layer.msg('操作失败!',{icon:1,time:1000});
             }
           })
	}
	function edCancel(obj,orderNumber){
		$.ajax({
             url:"edCancel.action?orderNumber="+orderNumber,
             type: "post",
             success: function (returnValue) {
                $(obj).parent().prev().find('.order-state').text("退款申请中");
                $(obj).css("display","none");
             },
             error: function (returnValue) {
                 layer.msg('操作失败!',{icon:1,time:1000});
             }
           })
	}
	
	function deleteor(obj,orderId){
		layer.confirm('确认要删除吗？',{icon:0,},function(index){			
			$.ajax({
             url:"deleteOrders.action?orderId="+orderId,
             type: "post",
             success: function (returnValue) {
                $(obj).parent().parent().remove();
                layer.msg('已删除!',{icon:1,time:1000});
             },
             error: function (returnValue) {
                 layer.msg('操作失败!',{icon:1,time:1000});
             }
           })		
		});
	}//deleteor
	function deleOrder(obj,orderNumber){
		layer.confirm('确认要删除吗？',{icon:0,},function(index){			
			$.ajax({
             url:"deleteOrder.action?orderNumber="+orderNumber,
             type: "post",
             success: function (returnValue) {
                $(obj).parent().parent().remove();
                layer.msg('已删除!',{icon:1,time:1000});
             },
             error: function (returnValue) {
                 layer.msg('操作失败!',{icon:1,time:1000});
             }
           })		
		});
	}
	function deleteeva(obj,evaId){
		layer.confirm('确认要删除吗？',{icon:0,},function(index){			
			$.ajax({
             url:"deleteEvaluate.action?evaluateId="+evaId,
             type: "post",
             success: function (returnValue) {
                $(obj).parent().parent().parent().remove();
                layer.msg('已删除!',{icon:1,time:1000});
             },
             error: function (returnValue) {
                 layer.msg('操作失败!',{icon:1,time:1000});
             }
           })
			
		});
	}
	
	$(function(){
		var smmsg= $("#savemoney_msg").val();
		if(smmsg!=""){
			cut(4);
		}
		var order1_msg=$("#order1_msg").val();
		if(order1_msg=="yes"){
			cut(6);
			}		
		var order2_msg=$("#order2_msg").val();
		if(order2_msg=="yes"){
			cut(6);
			}
		var order3_msg=$("#order3_msg").val();
		if(order3_msg=="yes"){
			cut(6);
			}
		var order4_msg=$("#order4_msg").val();
		if(order4_msg=="yes"){
			cut(6);
			}
		var order5_msg=$("#order5_msg").val();
		if(order5_msg=="yes"){
			cut(6);
			}
		var evaluate_go=$("#evaluate_go").val();
		if(evaluate_go=="yes"){
			cut(8);
			}
		var refund_go=$("#refund_go").val();
		if(refund_go=="yes"){
			cut(8);
			}	
			
	})

</script>
</html>
