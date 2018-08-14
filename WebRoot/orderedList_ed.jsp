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
    <script src="assets/js/bootstrap.min.js"></script> 
	<script src="assets/js/typeahead-bs2.min.js"></script>
	<script src="assets/js/jquery.dataTables.min.js"></script>
	<script src="assets/js/jquery.dataTables.bootstrap.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript" ></script>
    <script src="assets/laydate/laydate.js" type="text/javascript"></script>
    <script src="assets/dist/echarts.js"></script>
    <title>已完成订单</title>

  </head>
  
<body>
<%	ArrayList<HashMap> areceivedorderlist=(ArrayList<HashMap>)request.getSession().getAttribute("areceivedorderlist");

 %>
<div class="margin clearfix">
 <div class="amounts_style">
    <div class="border clearfix">
       <%if(areceivedorderlist!=null){ %>
       <span class="l_f">共：<b><%=areceivedorderlist.size() %></b>笔</span>
       <%}else{ %>
       <span class="l_f">共：<b>0</b>笔</span>
       <%} %>
     </div>
   <div class="Record_list">
    <table class="table table-striped table-bordered table-hover" id="sample-table">
       <thead>
		 <tr>
            <th width="150px">订单编号</th>
            <th width="180px">商品名称</th>
            <th width="180px">成交时间</th>
            <th width="120px">成交金额(元)</th>
            <th width="180px">状态</th>
            <th width="100px">查看</th>              
			</tr>
		</thead>
        <tbody> 
        <%if(areceivedorderlist!=null){ 
        	for(int i=0;i<areceivedorderlist.size();i++){
        %>
        <tr>
         <td><%=areceivedorderlist.get(i).get("orderNum") %></td>
         <td><%=areceivedorderlist.get(i).get("proImg") %></td>
         <td><%=areceivedorderlist.get(i).get("ordedealDate") %></td>
         <td><%=areceivedorderlist.get(i).get("totalprice") %></td>
         <td><%=areceivedorderlist.get(i).get("orderStatus") %></td>
         <td>
         	<a title="订单详细"  href="showOrderDetai.action?orderId=<%=areceivedorderlist.get(i).get("orderId")%>"  class="btn btn-xs btn-info order_detailed" ><i class="fa fa-list bigger-120"></i></a> 
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
		"bFilter":false,
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0,1,3]}// 制定列不参与排序
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
