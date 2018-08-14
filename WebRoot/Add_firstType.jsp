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
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<meta charset="utf-8"/>
    <meta name="renderer" content="webkit|ie-comp|ie-stand"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="js/html5.js"></script>
    <script type="text/javascript" src="js/respond.min.js"></script>
    <![endif]-->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/style.css"/>
    <link href="assets/css/codemirror.css" rel="stylesheet"/>
    <link rel="stylesheet" href="assets/css/ace.min.css" />
    <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
    <link href="layui/css/layui.css" rel="stylesheet" />
    <!--[if IE 7]>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->
    <link href="Widget/icheck/icheck.css" rel="stylesheet" type="text/css" />

    <title>新增一级分类</title>

  </head>
<body>  
 <div class="clearfix" id="add_picture">

    <div class="page_right_style">
        <div class="type_title">添加一级分类</div>
        <form action="addType"  method="post" class="form form-horizontal" id="form-article-add">
            <div class="clearfix cl">
                <label class="form-label col-2"><span class="c-red">*</span>一级分类名：</label>
                <div class="formControls col-10"><input type="text" required lay-verify="required" class="input-text" id="tyname" name="typeName"></div>
            </div>
            <div class="clearfix cl" style="height: 80px">
                <label class="form-label col-2">分类描述：</label>
                <div class="formControls col-10" >
                    <textarea name=""  rows="" class="textarea" placeholder="说点什么?" style="height: 100px" onkeyup="checkLength(this);"></textarea>
                    <span class="wordage">剩余字数：<span id="sy" style="color:Red;">200</span>字</span>
                </div>
            </div>

            <div class="clearfix cl">
                <div class="Button_operation">
                    <input id="submit" type="submit"class="btn btn-primary radius" >
                </div>
            </div>
        </form>
    </div>
</div>

<script src="js/jquery-1.9.1.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/typeahead-bs2.min.js"></script>
<script src="assets/layer/layer.js" type="text/javascript" ></script>
<script src="assets/laydate/laydate.js" type="text/javascript"></script>

<script src="layui/layui.js"></script>
<script src="js/lrtk.js" type="text/javascript" ></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>

<script type="text/javascript">
	layui.use('form', function(){
        var form = layui.form;
        });
/* 	$("#submit").on("click",function(){
		$('form-article-add').submit(); 
	}); */
	
	/*字数限制*/
	function checkLength(which) {
		var maxChars = 200; //
		if(which.value.length > maxChars){
		   layer.open({
		   icon:2,
		   title:'提示框',
		   content:'您输入的字数超过限制!',	
	    });
			// 超过限制的字数了就将 文本框中的内容按规定的字数 截取
			which.value = which.value.substring(0,maxChars);
			return false;
		}else{
			var curr = maxChars - which.value.length; //250 减去 当前输入的
			document.getElementById("sy").innerHTML = curr.toString();
			return true;
		}
	};
</script>

</body>
</html>