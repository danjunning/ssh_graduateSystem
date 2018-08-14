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
    <link rel="stylesheet" href="css/style.css"/>
    <link href="assets/css/codemirror.css" rel="stylesheet"/>
    <link rel="stylesheet" href="assets/css/ace.min.css" />
    <link rel="stylesheet" href="font/css/font-awesome.min.css" />
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css"/>
    <![endif]-->
	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="js/H-ui.js" type="text/javascript"></script>
    <script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/typeahead-bs2.min.js"></script>
	<script src="assets/js/jquery.dataTables.min.js"></script>
	<script src="assets/js/jquery.dataTables.bootstrap.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript" ></script>
    <script src="assets/laydate/laydate.js" type="text/javascript"></script>
    <script src="js/lrtk.js" type="text/javascript" ></script>
    <title>送货中订单</title>

  </head>
  
<body>
<%	ArrayList<HashMap> areceivingorderlist=(ArrayList<HashMap>)request.getSession().getAttribute("areceivingorderlist");

 %>
<div class="margin clearfix">
 <div id="amounts_style">
 	<div class="search_style">
 	<form action="getReceivingOrByNum">
      <ul class="search_content clearfix">
       <li><label class="l_f">订单编号</label><input name="orderNumber" type="text" class="text_add" placeholder="输入订单编号" style=" width:250px"></li>
       <li style="width:90px;"><button type="submit" class="btn_search"><i class="fa fa-search"></i>查询</button></li>
      </ul>
     </form>
    </div>
    <div class="border clearfix">
    <%if(null!=areceivingorderlist){ %>
       <span class="l_f">共：<b><%=areceivingorderlist.size() %></b>笔</span>
       <%}else{ %>
       <span class="l_f">共：<b>0</b>笔</span>
       <%} %>
     </div>
     <!--订单列表-->
     <div class="Record_list">
        <table class="table table-striped table-bordered table-hover" id="sample-table">
		<thead>
		 <tr>
			<th width="25px"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
			<th width="120px">订单编号</th>
			<th width="200px">商品</th>
			<th width="80px">购买数量</th>
		    <th width="100px">交易金额</th>			
	        <th width="120px">下单时间</th>				
			<th width="100px">订单状态</th>
			<th width="80px">操作</th>
		</tr>
		</thead>
        <tbody>
         <%if(areceivingorderlist!=null){
         	for(int i=0;i<areceivingorderlist.size();i++){ %>
         	<tr>
             <td><label><input type="checkbox" class="ace"><span class="lbl"></span></label></td>
             <td><%=areceivingorderlist.get(i).get("orderNum") %></td>
             <td class="order_product_name"><%=areceivingorderlist.get(i).get("proName") %></td>
             <td><%=areceivingorderlist.get(i).get("count") %></td>
             <td><%=areceivingorderlist.get(i).get("totalprice") %></td>
             <td><%=areceivingorderlist.get(i).get("orderDate") %></td>
             <td class="td-status"><span class="label label-success radius">送货中</span></td>
             <td>
             <a title="订单详细"  href="showOrderDetai.action?orderId=<%=areceivingorderlist.get(i).get("orderId")%>"  class="btn btn-xs btn-info order_detailed" ><i class="fa fa-list bigger-120"></i></a> 
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
</body>
</html>
<script>
	$(function() {
		var oTable1 = $('#sample-table').dataTable({
		"aaSorting": [],//默认第几个排序
		"bStateSave": false,//状态保存
		//"dom": 't',
		"bFilter":false,
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0,1,2,3,6,7]}// 制定列不参与排序
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


</script>