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
	
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta charset="UTF-8">
    <title>修改密码</title>
    <link href="layui/css/layui.css" rel="stylesheet" />
    <script src="layui/layui.js"></script>
    <script src="js/jquery-1.11.3.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript" ></script>	

  </head>
  
  <body>
  <%	User user=(User)request.getSession().getAttribute("user");
  
   %>
<div style="margin-top: 50px;margin-left:400px;">
    <form class="layui-form" action="updatePsd" id="form_psw">
        <div class="layui-form-item">
            <label class="layui-form-label">原密码</label>
            <div class="layui-input-inline">
                <input  id="oldpsw" type="password" name="oldpassword" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                <input id="Password" type="hidden" value="<%=user.getPassword()%>">
            </div>
            <div class="layui-form-mid layui-word-aux">辅助文字</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新密码</label>
            <div class="layui-input-inline">
                <input id="newpsw" type="password" name="newpassword" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">辅助文字</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-inline">
                <input id="psw" type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">辅助文字</div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn"  id="submit" >确认</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

</body>
<script>
    layui.use('form', function(){
        var form = layui.form;
    });
    
    $(function(){
    	$('#submit').click(function(){
    		var Password=$('#Password').val();
    		var oldpsw=$('#oldpsw').val();
    		var newpsw=$('#newpsw').val();
    		var psw=$('#psw').val();
    		alert(newpsw+"  "+psw+"  "+Password);
    		if(oldpsw == ''|oldpsw.length==0){
	         layer.msg("请输入原密码...", {icon: 5,anim: 6});
	        	return false;
	         }
	         else if(newpsw == ''|newpsw.length==0){
	         layer.msg("请输入新密码...", {icon: 5,anim: 6});
	        	return false;
	         }
	         else if(psw == ''|psw.length==0){
	         layer.msg("请确认新密码...", {icon: 5,anim: 6});
	        	return false;
	         }
	         else if(newpsw != psw){
	         layer.msg("确认密码和新密码不一致...",{icon: 5,anim: 6});
	        	return false;
	         }
	         else if(Password != oldpsw){
	         layer.msg("原密码不对...", {icon: 5,anim: 6});
	        	return false;
	         }
	        else{
		        
		    	$('#form_psw').submit();
		    	/* var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index); */
	        }
    	});
    	
    
    
    })
</script>
</html>
