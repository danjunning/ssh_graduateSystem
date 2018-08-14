<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.nsu.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
    <title>退款管理</title>
</head>
  
<body>
<%
 	ArrayList<HashMap> areforderlist=(ArrayList<HashMap>)request.getSession().getAttribute("areforderlist");
  %>
<div class="margin clearfix">
 <div id="refund_style">
   <div class="search_style">
     <form action="getRefundOrByNum">
      <ul class="search_content clearfix">
       <li><label class="l_f">订单编号</label><input name="orderNumber" type="text" class="text_add" placeholder="输入订单号" style=" width:250px"></li>
       <li style="width:90px;"><button type="submit" class="btn_search"><i class="fa fa-search"></i>查询</button></li>
      </ul>
     </form>
    </div>
 <div class="border clearfix">
       <span class="l_f">
        <a href="getAllrefundedList.action" class="btn btn-success Order_form"><i class="fa fa-check-square-o"></i>&nbsp;已退款订单</a>
        <a href="getAllUnrefundList.action" class="btn btn-warning Order_form"><i class="fa fa-close"></i>&nbsp;未退款订单</a>
        <!-- <a href="javascript:ovid()" class="btn btn-danger"><i class="fa fa-trash"></i>&nbsp;批量删除</a> -->
       </span>
       <%if(areforderlist!=null){ %>
        <span class="r_f">共：<b><%=areforderlist.size() %></b>笔</span>
       <%}else{ %>
       <span class="r_f">共：<b>0</b>笔</span>
       <%} %>
     </div>
     <!--退款列表-->
     <div class="Record_list">
        <table class="table table-striped table-bordered table-hover" id="sample-table" style="width: 1336px">
		<thead>
		 <tr>
			<th width="25px"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
			<th width="120px">订单编号</th>
			<th width="250px">商品</th>			
	        <th width="120px">交易时间</th>				
			<th width="100px">退款金额</th>
	        <th width="80px">退款数量</th>
			<th width="70px">状态</th>
			<th width="200px">操作</th>
		 </tr>
		</thead>
        <tbody>
         <%if(areforderlist!=null){
         	for(int i=0;i<areforderlist.size();i++){        	
          %>
          <tr>
         	<td><label><input type="checkbox" class="ace"><span class="lbl"></span></label></td>
		    <td><%=areforderlist.get(i).get("orderNum") %></td>
		    <td class="order_product_name"><%=areforderlist.get(i).get("proName") %></td>  
		    <%if(areforderlist.get(i).get("dealDate")!=null){ %>
		    <td><%=areforderlist.get(i).get("dealDate") %></td>
		    <%}else{ %>
		    <td>待发货</td>
		    <%} %>
		    <td><%=areforderlist.get(i).get("totalprice") %></td>
		    <td><%=areforderlist.get(i).get("count") %></td>
		    <td class="td-status"><span class="label label-success radius"><%=areforderlist.get(i).get("orderStatus") %></span></td>
		    <td>
		     	<a title="退款订单详细"  href="showOrderDetai.action?orderId=<%=areforderlist.get(i).get("orderId")%>"  class="btn btn-xs btn-info Refund_detailed" >详细</a> 
		     	<%if(areforderlist.get(i).get("orderStatus").equals("已退款")){ %>
		     	<%}else{ %>
		     	<a onclick="Delivery_Refund(this,'<%=areforderlist.get(i).get("orderId") %>','<%=areforderlist.get(i).get("payBank") %>')"  href="javascript:;" title="退款"  class="btn btn-xs btn-success">退款</a>
		     	<%} %>
		     	    
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
		"bFilter":false,//过滤搜索
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0,1,2,4,5]}// 制定列不参与排序
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
	function Delivery_Refund(obj,orderId,payBank){
		layer.confirm('您是否确定该订单的退款申请？',function(index){
		//location.href="refund.action?orderId="+orderId+"&&payBank="+payBank;
		$.ajax({
             url:"refund.action?orderId="+orderId+"&&payBank="+payBank,
             type: "post",
             success: function (returnValue) {
                layer.msg('已退款!',{icon: 6,time:1000});
                $(obj).parents("tr").find(".td-manage").prepend('<a style=" display:none" class="btn btn-xs btn-success" onClick="member_stop(this,id)" href="javascript:;" title="已退款">退款</a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt  radius">已退款</span>');
				$(obj).remove();
             },
             error: function (returnValue) {
                 alert("操作失败！");
             }
         })
		
		
				});  		  
	};
	//面包屑返回值
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.iframeAuto(index);
	$('.Refund_detailed').on('click', function(){
		var cname = $(this).attr("title");
		var chref = $(this).attr("href");
		var cnames = parent.$('.Current_page').html();
		var herf = parent.$("#iframe").attr("src");
	    parent.$('#parentIframe').html(cname);
	    parent.$('#iframe').attr("src",chref).ready();;
		parent.$('#parentIframe').css("display","inline-block");
		parent.$('.Current_page').attr({"name":herf,"href":"javascript:void(0)"}).css({"color":"#4c8fbd","cursor":"pointer"});
		//parent.$('.Current_page').html("<a href='javascript:void(0)' name="+herf+" class='iframeurl'>" + cnames + "</a>");
	    parent.layer.close(index);
		
	});
</script>