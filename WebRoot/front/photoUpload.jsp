<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta charset="UTF-8">
    <title></title>
    <link href="layui/css/layui.css" rel="stylesheet" />
    <script src="layui/layui.all.js"></script>
    <script type="text/javascript" src="js/jquery-1.11.3.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript" ></script>

  </head>
  
  <body>
<div style="margin-left: 50px">

    <div class="layui-upload" style="margin-top: 10px">
        <form action="" id="formid">
            <input type="file" name="file" id="photo" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg"  onchange="upImg(this)">
            <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;width: 350px">
                <div class="layui-upload-drag" id="test10">
                 <div id="pre">
                    <i class="layui-icon"></i>
                    <p>图片预览</p>
                  </div>
                 </div>
                <div class="layui-upload-list" id="demo2"></div>
            </blockquote>
            <input class="layui-btn" type="button" value="提交" onclick="upload()">
        </form>
    </div>


</div>

</body>

<script>
 	function upImg(obj){  
	    var imgFile = obj.files[0];  
	    console.log(imgFile);  
	    var img = new Image();  
	    var fr = new FileReader();  
	    fr.onload = function(){
	      $('#pre').children().remove();  
	　　　var img = document.createElement("img");
	　　　img.setAttribute("width", "80px");
	      img.setAttribute("height", "80px"); 
	　　　img.src = fr.result;
	　　　document.getElementById("pre").appendChild(img);
	      $(obj).attr("value",fr.result);
	   	  $(obj).next().children("img").attr("src",fr.result);  
	      
	    }  
    	fr.readAsDataURL(imgFile);  
 	} 
 	function upload(){
 		var photo=$('#photo').val();
 		if(photo==''){
    		layer.msg("请选择要上传的图片...", {
            icon: 5,
            anim: 6
        });
        return false;
    	}
			$.ajax({    
       		contentType:"multipart/form-data",  
       		url:"uploadPhoto",    
      		type:"POST",  
      	    data:new FormData($('#formid')[0]),
        	dataType:"text",  
       		processData: false,  // 告诉jQuery不要去处理发送的数据  
       	    contentType: false,   // 告诉jQuery不要去设置Content-Type请求头  
            success: function(data){          
	            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	            parent.layer.close(index); 
	            layer.msg('上传成功!',{icon:1,time:1000});
	            //window.parent.location.reload();
	            
              }  
           });
 	}  
</script>
</html>