<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/typeahead-bs2.min.js"></script>
	<script src="assets/js/jquery.dataTables.min.js"></script>
	<script src="assets/js/jquery.dataTables.bootstrap.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript" ></script>
    <script src="assets/laydate/laydate.js" type="text/javascript"></script>
	<title>商品评论</title>
</head>

<body>
<%	ArrayList<HashMap> evaluatelist=(ArrayList<HashMap>)request.getSession().getAttribute("evaluatelist");
 %>
<div class="margin clearfix">
 <div class="" id="Other_Management">
    <div class="border clearfix">
    <%if(evaluatelist!=null){ %>
       &nbsp;<span class="l_f">共：<b><%=evaluatelist.size() %></b>条</span>
    <%}else{ %>  
    	&nbsp;<span class="l_f">共：<b>0</b>条</span>
    <%} %> 
     </div>
     <div class="list_style">
     <table class="table table-striped table-bordered table-hover" id="sample-table">
     <thead>
		 <tr>
		 	<th width="25"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
			<th width="150">商品</th>
			<th width="200">评论内容</th>
			<th width="120">评论日期</th>  
			<th width="80">用户</th>			 			
		 </tr>
	 </thead>
	<tbody>
	<%if(evaluatelist!=null){for(int i=0;i<evaluatelist.size();i++){ %>
		<tr>
        <td><label><input type="checkbox" class="ace"><span class="lbl"></label></td>
        <td><%=evaluatelist.get(i).get("proName") %></td>
        <td><%=evaluatelist.get(i).get("content") %>好商品</td>
        <td><%=evaluatelist.get(i).get("evaDate") %>2018-03-31</td>
        <td><%=evaluatelist.get(i).get("userName") %></td>
        </tr>
    <%}}else{ %> 
    	<p>没有评论！！！</p>
    <%} %>   
	</tbody>    
     </table>     
     </div>
     
 </div>
</div>

</body>
</html>
<script>
jQuery(function($) {
	var oTable1 = $('#sample-table').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0,2,4]}// 制定列不参与排序
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
