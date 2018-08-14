<%@page import="cn.nsu.entity.Type"%>
<%@page import="cn.nsu.entity.Subtype"%>
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
    <title>添加产品</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<meta charset="UTF-8"/>
    <link href="layui/css/layui.css" rel="stylesheet" />
    <script src="layui/layui.js"></script>
    <script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/jquery-1.11.3.js"></script>
    <script src="assets/js/ace-elements.min.js"></script>
    <style type="text/css">
	.single-file {float:left;position:relative;white-space:nowrap;}
	.single-file input{width: 170px;}
	.single-file img { position:absolute; left:0px; top:30px;width: 80px;height: 80px;border:1px solid #DDDDDD; }
	.wrapper-img .text {
		cursor:pointer;
		position:relative;
		top:5px;
		font-size:12px;
		visibility:hidden;
		}
		.wrapper-img:hover .text {
		visibility:visible;
		background: #8EB9F5;
		color:#FFFFFF;
		width:82px; 
			}
</style>
  </head>
  
<body>
<div class="refresh" style="display:none;">
<%	List<Type> fisrttypelist=(List<Type>)request.getSession().getAttribute("fisrttypelist");
	List<Subtype> secondtypelist=(List<Subtype>)request.getSession().getAttribute("secondtypelist");

 %>
 </div>
<div class="border clearfix">
<div style="margin-top: 50px">
    <form action="addPro" class="layui-form" enctype="multipart/form-data" method="post"  id="">
        <div class="layui-form-item">
        	<div class="layui-inline">
        	 <label class="layui-form-label">一级分类:</label>
             <div class="layui-input-inline">    	
                <select id="selectF" name="typeId" required lay-verify="required"  lay-filter="myselect">
                   <option value="">下拉选择</option>
                   <%if(fisrttypelist!=null){
                   		for(int i=0;i<fisrttypelist.size();i++){ %>
                   		<option value="<%=fisrttypelist.get(i).getTypeId()%>"><%=fisrttypelist.get(i).getTypeName() %></option>
                   		<%}}else{ %>
                   		<%} %>
                </select>
            </div>
<script type="text/javascript">
	
</script>            
            
           </div>
           <div class="layui-inline" >
             <label class="layui-form-label">二级分类:</label>
             <div class="layui-input-inline refresh">	
                <select name="subtypeId" required lay-verify="required" lay-search="" lay-filter="myseselect">
                   <option value="">点击选择</option>
                   <%if(secondtypelist!=null){
                   		for(int i=0;i<secondtypelist.size();i++){ %>
                   		<option value="<%=secondtypelist.get(i).getSubtypeId()%>"><%=secondtypelist.get(i).getSubtypeName() %></option>
                   		<%}}else{ %>
                   		<%} %>
                </select>
               </div>
            </div>

        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">商品名:</label>
                <div class="layui-input-inline">
                    <input type="text" name="productName" lay-verify="productName" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">单价:</label>
                <div class="layui-input-inline">
                    <input name="productPrice" lay-verify="required|" autocomplete="off" class="layui-input" onkeyup="checknum(this)">
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
            <script type="text/javascript">
			   function checknum(obj)
			   {   
			     if(/^\d+\.?\d{0,2}$/.test(obj.value)){
			        obj.value = obj.value;
			     }else{
			        obj.value = obj.value.substring(0,obj.value.length-1);
			     }     
			   }
			</script>
        </div>
        <div class="layui-form-item">
        	<div class="layui-inline">
                <label class="layui-form-label">库存:</label>
                <div class="layui-input-inline">
                    <input type="number" name="productStock" lay-verify="required|number" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">销量:</label>
                <div class="layui-input-inline">
                    <input type="number" name="saleCount" value="0" autocomplete="off" class="layui-input" style="border:none;">
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
        </div>
        <div class="layui-form-item" style="height:110px">
            <label class="layui-form-label">图片:</label>
            <div class="layui-input-block" style="width:2000px" id="more">
                <div class="list-file" id="filewrap">   	
   					<div class="single-file">
   						<input type="file" name="file" lay-verify="required" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg" onchange="upImg(this)" id="firstfile"/> <!-- onchange="upImg(this)" -->
   						<div class="wrapper-img">
    						 <img src="http://placehold.it/300x200" class="hover" width="80px";height="80px"/>
   			 				<p class="text" onclick="removeF(this)" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</p>
   						</div>
   					</div>
   					<div class="imgOnloadWrap"></div>
   				 </div>
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>
        
    
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">颜色:</label>
                <div class="layui-input-inline">
                    <input type="text" name="color" lay-verify="color" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">款式:</label>
                <div class="layui-input-inline">
                    <input type="text" name="style" lay-verify="style" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
        	<p style="margin-left: 110px;color:red;font-size: 13px;font-weight: bolder;">
          	 *你可以添加多种颜色和款式，添加多个时，用逗号隔开,如：红色，黑色
          	 </p>        
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">详细介绍:</label>
            <div class="layui-input-block" style="width: 500px">
                <textarea placeholder="请输入内容" name="productDetail" lay-verify="productDetail" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo" >确认</button>
                <button class="layui-btn" lay-reset >取消</button>
            </div>
        </div>
    </form>
</div>
</div>
</body>

<script type="text/javascript">
    layui.use('form', function(){
        var form = layui.form;
        form.on('select(myselect)', function(data){
        	var typeId=data.value;
        	$.ajax({
			 url:"getSubypeByTid.action?typeId="+typeId,
			 type: "post",
			 success: function (returnValue) {
		 	     //alert(returnValue);//随便的显示一下传回的数据
                 var backdata=JSON.parse(returnValue);
		 	     form.render('myseselect');
		 	     
			 },
			 error: function (returnValue) {
			     alert("数据加载失败！");
			 }
		    })
        })
        
        //自定义验证规则  
        form.verify({   
        	productName: function(value){ 
        	 if(value.length < 0 || value==""){  
        	 	return '商品名不能为空';  
		        } 
		     if(/(^\_)|(\__)|(\_+$)/.test(value)){
      			return '商品名首尾不能出现下划线\'_\'';
   			 }
   			 if(/^\d+\d+\d$/.test(value)){
    			return '商品名不能全为数字';
  		     } 
		     }, 
		    color: function(value){  
		         if(value.length < 0 || value==""){  
		           return '颜色输入不能为空';  
		         }
		         if(/^\d+\d+\d$/.test(value)){
    				return '颜色不能全为数字';
  		         } 
  		         if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s，,]+$").test(value)){
     	     	return '颜色不能有特殊字符';
   				 } 
		       }, 
		    style: function(value){  
		         if(value.length < 0 || value==""){  
		           return '款式输入不能为空';  
		         } 
		         if(/^\d+\d+\d$/.test(value)){
    				return '款式不能全为数字';
  		     } 
  		     if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s，,]+$").test(value)){
     	     	return '款式不能有特殊字符';
   				 }
		       },
		       productDetail:function(value){
		       	if(value.length < 0 || value==""){  
        	 	return '详细介绍栏不能为空';  
		        }
		       } 

		 });         
    });
          
	function removeF(obj){ 
	 $(obj).prev().attr("src",""); 
	 $("#firstfile").val(""); 
	 }  
	  function removeN(obj){  
	       $(obj).parent().parent().remove();  
	 }  

	function upImg(obj){  
    var imgFile = obj.files[0];  
    console.log(imgFile);  
    var img = new Image();  
    var fr = new FileReader();  
    fr.onload = function(){
      $(obj).attr("value",fr.result);
   	  $(obj).next().children("img").attr("src",fr.result);  
      if($('#filewrap input').length<7){
        var htmlStr = '<div class="single-file">';  
        htmlStr += '<input type="file" name="file" lay-verify="required" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg" onchange="upImg(this)"/>';   
        htmlStr += '<div class="wrapper-img">';  
        htmlStr += '<img alt="" src="http://placehold.it/300x200" class=" hover"/>';  
        htmlStr += '<p class="text" onclick="removeN(this)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</p>'; 
        htmlStr += '</div>';  
        htmlStr += '</div>';   
        $('.imgOnloadWrap').append(htmlStr);  
        
        }
    }  
    fr.readAsDataURL(imgFile);  
} 
/*  $(document).on('click','.deleteImg',function(){  
    //处理未来事件  
    $(this).parent().parent().remove();  
})	 */

 
 $('.id-input-file-2').ace_file_input({
	no_file:'选择上传图标 ...',
	btn_choose:'选择',
	btn_change:'更改',
	droppable:false,
	onchange:null,
	thumbnail:false, //| true | large
	whitelist:'gif|png|jpg|jpeg'
	//blacklist:'exe|php'
	//onchange:''
	//
});
	 

</script>

</html>