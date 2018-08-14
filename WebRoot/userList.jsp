<%@page import="cn.nsu.entity.User"%>
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
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/style.css"/>
    <link href="assets/css/codemirror.css" rel="stylesheet"/>
    <link rel="stylesheet" href="assets/css/ace.min.css" />
    <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
	<script src="assets/js/jquery.min.js"></script>
	<script type="text/javascript">
		if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
	</script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/typeahead-bs2.min.js"></script>
	<!-- page specific plugin scripts -->
	<script src="assets/js/jquery.dataTables.min.js"></script>
	<script src="assets/js/jquery.dataTables.bootstrap.js"></script>
       <script type="text/javascript" src="js/H-ui.js"></script>
       <script type="text/javascript" src="js/H-ui.admin.js"></script>
       <script src="assets/layer/layer.js" type="text/javascript" ></script>
       <script src="assets/laydate/laydate.js" type="text/javascript"></script>
       <title>用户列表</title>

  </head> 
<body>
<%
	List<User> list=(List<User>)request.getSession().getAttribute("userlist");
 %>
<div class="page-content clearfix">
    <div id="Member_Ratings">
      <div class="d_Confirm_Order_style">
    <div class="search_style">
    </div>
     <!---->
     <div class="border clearfix">
     <%if(list!=null){ %>
       &nbsp;<span class="l_f">共：<b><%=list.size() %></b>条</span>
     <%}else{ %>
       &nbsp;<span class="l_f">共：<b>0</b>条</span>
     <%} %>
     </div>
     <!---->
     <div class="table_menu_list">
       <table class="table table-striped table-bordered table-hover" id="sample-table">
		<thead>
		 <tr>		
			<th width="80">序号</th>
			<th width="100">用户名</th>
			<th width="80">性别</th>
			<th width="120">手机</th>
			<th width="150">邮箱</th>
			<th width="200">地址</th>
		 </tr>
		</thead>
	<tbody>
	<%if(list!=null){
		for(int i=0;i<list.size();i++){
	 %>
		<tr>     
          <td><%=i+1 %></td>
          <td><%=list.get(i).getUname() %></td>
          <%if(null==list.get(i).getSex()){ %>
          <td>***</td>
          <%}else{ %>
          <td><%=list.get(i).getSex() %></td><%} %>
          
          <%if(null==list.get(i).getMobile()){ %>
          <td>***</td>
          <%}else{ %>
          <td><%=list.get(i).getMobile() %></td><%} %>
          
          <%if(null==list.get(i).getMobile()){ %>
          <td>***</td>
          <%}else{ %>
          <td><%=list.get(i).getMobile() %></td><%} %>
                   
          <td>***</td>
      
		</tr>
		<%}}else{}%>
		
      </tbody>
	</table>
   </div>
  </div>
 </div>
</div>

</body>
</html>
<script>
jQuery(function($) {
	var oTable1 = $('#sample-table').dataTable( {
		"aaSorting": [],//默认第几个排序
		"bStateSave": true,//状态保存
		"bFilter":false,
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
			
			
				$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('table')
					var off1 = $parent.offset();
					var w1 = $parent.width();
			
					var off2 = $source.offset();
					var w2 = $source.width();
			
					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
					return 'left';
				}
			})



</script>