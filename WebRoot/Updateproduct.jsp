<%@page import="cn.nsu.entity.Product"%>
<%@page import="cn.nsu.entity.Style"%>
<%@page import="cn.nsu.entity.Color"%>
<%@page import="cn.nsu.entity.Image"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>"/>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<meta charset="UTF-8"/>
    <title>添加产品</title>
    <link href="layui/css/layui.css" rel="stylesheet" />
    <script src="layui/layui.js"></script>
    <script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="assets/layer/layer.js" type="text/javascript" ></script>
    <script src="assets/js/ace-elements.min.js"></script>
    
<style type="text/css">
	.single-file {float:left;position:relative;white-space:nowrap;}
	.single-file input{width: 170px;}
	.single-file img { left:0px; top:30px;width: 80px;height: 80px;border:1px solid #DDDDDD; }
	.wrapper-img .text {
		cursor:pointer;
		position:relative;
		top:0px;
		font-size:12px;
		visibility:hidden;
		}
		.wrapper-img:hover .text {
		visibility:visible;
		background: #8EB9F5;
		color:#FFFFFF;
		width:82px; }
		input[name=color]{float:left;margin-left:2px;}	
		input[name=style]{float:left;margin-left:2px;}	
</style>
  </head>
  
<body>
<%	Product product=(Product)request.getAttribute("behindproduct");
	List<Image> img=(List<Image>)request.getAttribute("behindimglist");
	List<Color> color=(List<Color>)request.getAttribute("behindcolorlist");
	List<Style> style=(List<Style>)request.getAttribute("behindstylelist");
 %>

<div class="border clearfix">
<div style="margin-top: 50px">
    <form class="layui-form" action="" id="">
        <div class="layui-form-item">
         <div class="layui-inline">
            <label class="layui-form-label" ">所属一级</label>
            <div class="layui-input-inline">
                <input type="text" name="productName" value="${behindtype }" readonly="readonly" class="layui-input" style="border:none;">
            </div>
          </div>  
         <div class="layui-inline">
            <label class="layui-form-label">所属二级</label>
            <div class="layui-input-inline">
                <input type="text" name="productName" value="${behindsubtype }" readonly="readonly" class="layui-input" style="border:none;">
            </div>
          </div>

        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">商品名</label>
                <div class="layui-input-inline">
                    <input type="text" name="productName" value="<%=product.getProductName() %>" lay-verify="required" autocomplete="off" class="layui-input"  onchange="">
                </div>
               
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">单价</label>
                <div class="layui-input-inline">
                    <input type="number" value="<%=product.getProductPrice() %>" id="price" class="layui-input" onchange="changePri('<%=product.getProductId()%>')">
                </div>
                
            </div>

        </div>
        <div class="layui-form-item">
        	<div class="layui-inline">
                <label class="layui-form-label">销量</label>
                <div class="layui-input-inline">
                    <input  readonly="readonly" value="<%=product.getSaleCount() %> 件" class="layui-input" style="border:none;">
                </div>
                
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">库存(件)</label>
                <div class="layui-input-inline">
                    <input type="number" id="stock" value="<%=product.getProductStock() %>" onchange="changeSto('<%=product.getProductId()%>')"  class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
        </div>

        <div class="layui-form-item" style="height:110px">
            <label class="layui-form-label">图片</label>
            <div class="layui-input-block" style="width:2000px" id="more">
                <div class="list-file" id="filewrap">  
                <%if(img!=null&&img.size()>0){for(int i=0;i<img.size();i++){ %> 	
   					<div class="single-file">
   						<div class="wrapper-img">
    						 <img src="<%=img.get(i).getImage() %>" class="hover" width="80px";height="80px"/>
   			 				<p class="text" onclick="removeF(this)" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</p>
   						</div>
   					</div>
   					<%}} %>
   					
   					<div class="imgOnloadWrap"></div>
   				 </div>
            </div>
            
        </div>
        
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">颜色</label>
                <div class="layui-input-block">
                <%if(color!=null&&color.size()>0){for(int i=0;i<color.size();i++){ %>
                    <input type="text" name="color" value="<%=color.get(i).getColor() %>" readonly class="layui-input" style="width:55px;">
                 <%}} %>  
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">款式</label>
                <div class="layui-input-block">
                <%if(style!=null&&style.size()>0){for(int i=0;i<style.size();i++){ %>
                    <input type="text"name="style" value="<%=style.get(i).getStyle() %>" readonly class="layui-input" style="width:55px;">
                <%}} %>    
                </div>
                <div class="layui-form-mid layui-word-aux"></div>
            </div>
        </div>
        
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">详细介绍</label>
            <div class="layui-input-block" style="width: 500px">
                <textarea placeholder="请输入内容" name="productDetail" class="layui-textarea" cols="50"><%=product.getProductDetail() %></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo" >确认</button>
                <button class="layui-btn" lay-reset lay-filter="formDemo" >取消</button>
            </div>
        </div>
    </form>
</div>
</div>
<script>
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            var name=document.getElementsByName("title").valueOf();
            var name1=document.getElementById("name").innerHTML;

            var name2=$("#tt").val();
            var name3=$("input[ name='name' ]").val();

            //alert(name+","+name1+","+name2+","+name3);
            return false;
        });
    });
    
    function changePri(productId){
    	var price=$("#price").val();
    	var isNum=/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
    	if(!(isNum.test(price))){
         layer.msg("请输入正确的价格...", {
            icon: 5,
            anim: 6
        	});
        	return false;
         }
    	$.ajax({
             url:"updatePrice.action?productId="+productId+"&productPrice="+price,
             type: "post",
             success: function (returnValue) {
                 layer.msg('已更新!',{icon:1,time:1000});
             },
             error: function (returnValue) {
                 alert("修改价格失败！");
             }
         }) 
    }  
     
    function changeSto(productId){
    	var stock=$("#stock").val();
    	var patrn = /^[0-9]*$/; 
    	if (!(patrn.test(stock))){
         layer.msg("请输入正确的数字...", {
            icon: 5,
            anim: 6
        	});
        	return false;
         }
    	if (!(/(^[0-9]*[1-9][0-9]*$)/.test(stock))){
         layer.msg("请输入正确的数字...", {
            icon: 5,
            anim: 6
        	});
        	return false;
         }
    	$.ajax({
             url:"updateStock.action?productId="+productId+"&productStock="+stock,
             type: "post",
             success: function (returnValue) {
                 layer.msg('已更新!',{icon:1,time:1000});
             },
             error: function (returnValue) {
                 alert("修改库存失败！");
             }
         }) 
    }      
 

</script>
</body>




</html>