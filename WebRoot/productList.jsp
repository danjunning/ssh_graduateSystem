<%@page import="cn.nsu.entity.Product"%>
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
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="renderer" content="webkit|ie-comp|ie-stand"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="assets/css/codemirror.css" rel="stylesheet"/>
    <link rel="stylesheet" href="css/style.css"/>
    <link rel="stylesheet" href="assets/css/ace.min.css" />
    <link rel="stylesheet" href="assets/css/font-awesome.min.css" />

    <link href="Widget/icheck/icheck.css" rel="stylesheet" type="text/css" />
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css"/>
    <![endif]-->
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script> 
	<script src="assets/js/typeahead-bs2.min.js"></script>
	<script src="assets/js/jquery.dataTables.min.js"></script>
	<script src="assets/js/jquery.dataTables.bootstrap.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript" ></script>
    <script src="assets/laydate/laydate.js" type="text/javascript"></script>
    <script src="assets/dist/echarts.js"></script>
    <link href="layui/css/layui.css" rel="stylesheet" />
    <script src="layui/layui.js"></script>
    <title>商品列表</title>

  </head>
  
<body>
<%  List<Product> productlist=(List<Product>)request.getSession().getAttribute("productlist");
 %>
<div class="margin clearfix">
 <div class="amounts_style">
 	<div class="search_style">
         <form action="getProByName">
            <ul class="search_content clearfix">
                <li><label class="l_f">关键字</label><input name="productName" type="text"  class="text_add" placeholder="输入商品名称"  style=" width:250px"/></li>
                <li style="width:90px;"><button type="submit" class="btn_search"><i class="icon-search"></i>查询</button></li>
            </ul>
          </form>
    </div>
    <div class="border clearfix">
	       <span class="l_f">
	        <a href="getFType.action" title="添加商品" class="btn btn-warning Order_form"><i class="icon-plus"></i>添加商品</a>
	       </span>
	       <%if(productlist!=null){ %>  
	       		<span class="r_f">共：<b><%=productlist.size() %></b>件商品</span>
	       <%}else{ %>  
	       		<span class="r_f">共：<b>0</b>件商品</span>
	       <%} %> 
      </div>
   <div class="Record_list">
    <table class="table table-striped table-bordered table-hover" id="sample-table">
       <thead>
		 <tr>
	         <th width="25px"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
	         <th width="80px">序号</th>
	         <th width="150px">商品</th>
	         <th width="80px">价格</th>
	         <th width="80px">库存</th>
	         <th width="80px">销量</th>
	         <th width="100px">加入时间</th>
	         <th width="80px">操作</th>
         </tr>
		</thead>
        <tbody> 
        	<%if(productlist!=null){ 
                	for(int i=0;i<productlist.size();i++){ %>
                <tr>
                    <td width="25px"><label><input type="checkbox" class="ace" ><span class="lbl"></span></label></td>
                    <td width="80px"><%=i+1 %></td>
                    <td width="150px">
                       <u style="cursor:pointer" class="text-primary" onclick="Update_iew('12')"><%=productlist.get(i).getProductName() %></u>
                    </td>
                    <td width="80px"><%=productlist.get(i).getProductPrice() %></td>
                    <td width="80px"><%=productlist.get(i).getProductStock() %></td>
                    <td width="80px"><%=productlist.get(i).getSaleCount() %></td>
                    <td width="80px"><%=productlist.get(i).getProductDate() %></td>
                    <td class="td-manage">
                     <a title="编辑" href="getProById?productId=<%=productlist.get(i).getProductId()%>" class="btn btn-xs btn-info" ><i class="icon-edit bigger-120"></i></a>
                     <a title="删除"  onclick="member_del(this,<%=productlist.get(i).getProductId()%>)" class="btn btn-xs btn-warning" ><i class="icon-trash  bigger-120"></i></a>
                    </td>
                </tr>
                <%}}else{ %>
                  	<p>没有相关记录!!!</p>
                <%} %>
        </tbody>
        </table> 
    
   </div>
 </div>
</div>
<div id="Statistics" style="display:none">
 <div id="main" style="height:400px; overflow:hidden; width:1000px; overflow:auto" ></div>
</div>
</body>
</html>
<script type="text/javascript">
	$(function() {
		var oTable1 = $('#sample-table').dataTable({
		"aaSorting": [],//默认第几个排序
		"bStateSave": false,//状态保存
		//"dom": 't',
		"bFilter":true,
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0,1,2,3,4]}// 制定列不参与排序
		] } );
		
		$('table th input:checkbox').on('click' , function(){
			var that = this;
			$(this).closest('table').find('tr > td:first-child input:checkbox')
			.each(function(){
				this.checked = that.checked;
				$(this).closest('tr').toggleClass('selected');
			});			
		});
	});
	
	/*-编辑*/
    function member_edit(title,url,id,w,h){
        layer_show(title,url,w,h);
        //location.href="Add_Brand.jsp"
    }

    /*-删除*/
    function member_del(obj,productId){
        layer.confirm('确认要删除吗？',function(index){
            //$(obj).parents("tr").remove();
            //location.href="deletePro.action?productId="+productId;
            $.ajax({
             url:"deletePro.action?productId="+productId,
             type: "post",
             success: function (returnValue) {
                $(obj).parents("tr").remove();
                //$("#cId").val(returnValue);
                 layer.msg('已删除!',{icon:1,time:1000});
             },
             error: function (returnValue) {
                 alert("删除失败！");
             }
         })   
        });
    }
    

	
	
	


</script>
