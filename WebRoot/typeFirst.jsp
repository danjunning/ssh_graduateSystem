<%@page import="cn.nsu.entity.Type"%>
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
    <link href="assets/css/codemirror.css" rel="stylesheet"/>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
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
    
    <title>一级分类</title>

  </head>
  
<body>
<%
  	List<Type> fisttypelist=(List<Type>)request.getSession().getAttribute("fisrttypelist");
  
%>
<div class="margin clearfix">
 <div class="amounts_style">
 <div class="search_style">
     
    </div>
    <div class="border clearfix">
       <span class="l_f">
        <a href="Add_firstType.jsp" title="添加分类" class="btn btn-warning Order_form"><i class="icon-plus"></i>添加分类</a>
       </span>
       <%if(fisttypelist!=null){ %>
       <span class="r_f">共：<b><%=fisttypelist.size() %></b>个分类</span>
       <%}else{ %>
       <span class="r_f">共：<b>0</b>个分类</span>
       <%} %>
     </div>
   <div class="Record_list">
    <table class="table table-striped table-bordered table-hover" id="sample-table">
       <thead>
		 <tr>
			<th width="25px"><label><input type="checkbox" class="ace"/><span class="lbl"></span></label></th>
			<th width="80px">一级分类ID</th>
			<th width="150px">类型名称</th>
			<th width="150px">备注</th>
			<th width="100px">操作</th>
		</tr>
		</thead>
        <tbody> 
		     <%
	     	if(fisttypelist!=null){
	     		for(int i=0;i<fisttypelist.size();i++){
	          %>
	     	<tr>
		     <td ><label><input type="checkbox" class="ace" /><span class="lbl"></span></label></td>
		     <td><%=i+1 %></td>               
		     <td ><%=fisttypelist.get(i).getTypeName() %></td>
		     <%if(fisttypelist.get(i).getTypeRemark()!=null){ %>
		     <td><%=fisttypelist.get(i).getTypeRemark()%></td> 
		     <%}else{ %>
		     <td>无特殊说明</td> 
		     <%} %>
		     <td class="td-manage"><!-- href="deleteType.action?typeId=<%=fisttypelist.get(i).getTypeId() %>" -->
		      <a title="删除" onclick="member_del(this,<%=fisttypelist.get(i).getTypeId() %>)" class="btn btn-xs btn-warning" ><i class="icon-trash  bigger-150"></i></a>
		     </td>
		    </tr>
	     <% } }%>
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
	/*广告图片-删除*/
	function member_del(obj,typeId){
		layer.confirm('确认要删除吗？',{icon:0,},function(index){	
			//location.href="deleteType.action?typeId="+typeId;
			//$(obj).parents("tr").remove();
			$.ajax({
             url:"deleteType.action?typeId="+typeId,
             type: "post",
             success: function (returnValue) {
                $(obj).parents("tr").remove();
                 layer.msg('已删除!',{icon:1,time:1000});
             },
             error: function (returnValue) {
                 alert("删除失败！");
             }
         })
		});
	}
	
	
	


</script>
