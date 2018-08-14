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
    
    <title>欢迎登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css"  href="css/loginfront.css"/>
    <link href="layui/css/layui.css" rel="stylesheet" />
    <script src="layui/layui.js"></script>
    <script src="js/jquery-1.11.3.js"></script>
  </head>
  
  <script type="text/javascript">

    function showDiv1(){
        var  Div1=document.getElementById('login_go');
        var  Div2=document.getElementById('register_go');
        var t1=document.getElementById('tab1');
        var t2=document.getElementById('tab2');
        t1.style.backgroundColor="#009688";
        t1.style.cssText = "background-color:#009688;color:white";
        Div1.style.display="block";

        t2.style.backgroundColor="#DDDDDD";
        t2.style.cssText = "background-color:#DDDDDD;color:black";
        Div2.style.display="none";
    }
    function showDiv2(){
        var  Div1=document.getElementById('login_go');
        var  Div2=document.getElementById('register_go');
        var t1=document.getElementById('tab1');
        var t2=document.getElementById('tab2');

        t2.style.backgroundColor="#009688";
        t2.style.cssText = "background-color:#009688;color:white";
        Div2.style.display="block";

        t1.style.backgroundColor="#DDDDDD";
        t1.style.cssText = "background-color:#DDDDDD;color:black";
        Div1.style.display="none";
    }
    
    $(function(){
    	var result=$("#re_msg").val();
    	if(result!=""){
    		showDiv2();
    	}
    })

</script>

<body style="background-color: #FAFAFA">
    <div class="web-logo">
        <div class="imgae-wrap"><img src="images/web-logo.png" style="border: 1px solid #DDDDDD"></div>
    </div>
    <hr style="border: 0.3px solid #DDDDDD">
	<div id="contain" >
    <div class="login-wrap" style="border-top:1px solid #ddd">
    <img src="images/back.png">
    <div id="login" style="">
    <span class="msg" style="font-size: 18px;color:#CCC;margin-bottom: 5px;">${Islogin }${messagetip }</span>
        <div class='tabs' id="tabs">
        	<input id="re_msg" type="hidden" value="${reg_messagetip }">
            <ul class='horizontal'>
                <li id="tab1" onclick="showDiv1()" class="selectActive">登录</li>
                <li id="tab2" onclick="showDiv2()">注册</li>
            </ul>
        </div>

        <form class="layui-form" action="login.action" id="form_login">
        <div id="login_go" style="">
            <div id="name1">
                <input class="layui-input" type="text" id="pp1" name="uname" required lay-verify="uname" autocomplete="off" placeholder="请输入用户名" />
                ${log_msg_name }
            </div>
            <div id="psw1">
                <input class="layui-input" type="password" id="pp2" name="password" required lay-verify="password" autocomplete="off" placeholder="请输入密码" />      
                ${log_msg_psw }
            </div>
            <input type="button" id="dl" lay-submit lay-filter="form_login" value="登   录">
        </div>
        </form>

        <form class="layui-form" action="register.action" id="form_register">
        <div id="register_go" style="display: none">
            <div id="name2">
                <input class="layui-input" type="text" name="uname" required lay-verify="uname" autocomplete="off" placeholder="用户名" />
                ${reg_messagetip }
            </div>
            <div id="psw2">
                <input class="layui-input" id="p1" type="password" name="password" required lay-verify="password" autocomplete="off" placeholder="密码" />
            </div>
            <div id="psw22">
                <input class="layui-input" id="p2" type="password" name="rpassword" autocomplete="off" required lay-verify="rpassword" placeholder="确认密码" />
            	<input type="button" id="zc" lay-submit value="注   册" lay-filter="form_register">
            </div>
        </div>
        </form>
    </div>
    </div>
</div>
<script type="text/javascript">
/* 	$("#zc").on("click",function(){
		if($('p1').val()!=$('p2').val()){
			layer.alert('两次密码！',{
               title: '提示框',
			   icon:1
			  });
		}
	}) */
</script>

<div id="footer">
    <div id="s1" class="word"><span>首页</span> | <span>关于</span> | <span>联系我们</span></div>
    <div id="q1" class="word"><span>qq客服:110</span> |<span> 客服邮箱:</span>yeedee@yeah.net</div>
    <div id="c1" class="word">Copyright @2012-2016, yeedee.com.cn All Rights Reserved.</div>
</div>
</body>
<script>
    layui.use(['layer', 'form'], function(){
        var form = layui.form;
        var layer=layui.layer;
        
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
			  password: function(value, item){  
                if (value.length == 0) {  
                    return '请输入密码';  
                }  
 				if(value.length < 1){  
	                return "密码长度不能小于6位";  
	            } 
            },    
	        rpassword : function(value, item){ 
	        	if (value.length == 0) {  
                    return '请输入密码';  
                } 
	            if(!new RegExp($("#p1").val()).test(value)){  
	                return "两次输入密码不一致，请重新输入！";  
	            }  
	        }
			  
			});
        
        //监听提交
        form.on('submit(form_register)', function(data){
            //layer.msg(JSON.stringify(data.field));     
			$('#form_register').submit();		
        });
        
        //监听提交
        form.on('submit(form_login)', function(data){
            //layer.msg(JSON.stringify(data.field));
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
			  password: function(value, item){  
                if (value.length == 0) {  
                    return '请输入密码';  
                }  
 				if(value.length < 1){  
	                return "密码长度不能小于6位";  
	            } 
            },    		  
			});
			
			$('#form_login').submit();

        });
        
    });
    
    
</script>
</html>
