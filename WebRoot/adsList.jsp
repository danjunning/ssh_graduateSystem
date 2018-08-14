<%@page import="cn.nsu.entity.Advertise"%>
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
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
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
	<script src="assets/js/typeahead-bs2.min.js"></script>
    <script src="js/lrtk.js" type="text/javascript" ></script>
    <script src="assets/js/jquery.dataTables.min.js"></script>
    <script src="assets/js/jquery.dataTables.bootstrap.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript" ></script>
	<title>分类管理</title>
</head>

<body>
<% List<Advertise> advertiselist=(List<Advertise>)request.getSession().getAttribute("advertiselist");
 %>
<div class="page-content clearfix">
 <div class="sort_style">
     <div class="border clearfix">
       <span class="l_f">
        <a href="javascript:void()" name="Addadvertise.jsp" id="sort_add" class="btn btn-warning"><i class="fa fa-plus"></i> 添加广告</a>
       </span>
       <%if(advertiselist!=null){ %>
       <span class="r_f">共：<b><%=advertiselist.size() %></b>条</span>
       <%}else{ %>
       <span class="r_f">共：<b>0</b>条</span>
       <%} %>
     </div>
  <div class="sort_list">
    <table class="table table-striped table-bordered table-hover" id="sample-table">
    <%if(advertiselist!=null){ %>
		<thead>
		 <tr>
            <th width="25px"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
            <th width="50px">序号</th>
            <th width="200px">名称</th>
            <th width="350px">描述</th>
            <th width="250px">备注</th>
            <th width="180px">加入时间</th>
            <th width="150px">操作</th>
		 </tr>
		</thead>
	<tbody>
      <%for(int i=0;i<advertiselist.size();i++){ %>
      <tr>
       <td><label><input type="checkbox" class="ace"><span class="lbl"></span></label></td>
       <td><%=i+1 %></td>
       <td><%=advertiselist.get(i).getProductName() %></td>
       <td><%=advertiselist.get(i).getProductDetail() %></td>
       <%if(advertiselist.get(i).getProductRemark() !=null){ %>
       <td><%=advertiselist.get(i).getProductRemark() %></td>
       <%}else{ %> 
       <td>无特别说明！！！</td>
       <%} %>
       <td><%=advertiselist.get(i).getProductDate() %></td>
       <td class="td-manage">
        <a title="删除"  onclick="member_del(this,<%=advertiselist.get(i).getAdId() %>)" class="btn btn-xs btn-warning" ><i class="fa fa-trash  bigger-120"></i></a>
       </td>
      </tr>
      <%} }else{%>
      	<p>暂时没有广告!!!</p>
      <%} %>
    </tbody>
    </table>
  </div>
 </div>
</div>
<!--添加分类-->
<div class="sort_style_add margin" id="sort_style_add" style="display:none">
  <div class="">
     <ul>
      <li><label class="label_name">分类名称</label><div class="col-sm-9"><input name="分类名称" type="text" id="form-field-1" placeholder="" class="col-xs-10 col-sm-5"></div></li>
      <li><label class="label_name">分类说明</label>
          <div class="col-sm-9">
              <textarea name="分类说明" class="form-control" id="form-field-8" ></textarea>
          <span class="wordage">剩余字数：<span id="sy" style="color:Red;">200</span>字</span></div></li>
      <li><label class="label_name">分类状态</label>
      <span class="add_content"> &nbsp;&nbsp;<label><input name="form-field-radio1" type="radio" checked="checked" class="ace"><span class="lbl">显示</span></label>&nbsp;&nbsp;&nbsp;
     <label><input name="form-field-radio1" type="radio" class="ace"><span class="lbl">隐藏</span></label></span>
     </li>
     </ul>
  </div>
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
	})
	
	 $('#sort_add').on('click', function(){
		  location.href="Addadvertise.jsp";
	}) 

	/*广告图片-删除*/
	function member_del(obj,adId){
		layer.confirm('确认要删除吗？',{icon:0,},function(index){		
		   //location.href="deleteAdver.action?adId="+adId;		
			$.ajax({
             url:"deleteAdver.action?adId="+adId,
             type: "post",
             success: function (returnValue) {
                $(obj).parents("tr").remove();
                $("#cId").val(returnValue);
                 layer.msg('已删除!',{icon:1,time:1000});
             },
             error: function (returnValue) {
                 alert("删除失败！");
             }
         })
			
		});
	}
	//面包屑返回值
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.iframeAuto(index);
	$('.Order_form ,.ads_link').on('click', function(){
		var cname = $(this).attr("title");
		var cnames = parent.$('.Current_page').html();
		var herf = parent.$("#iframe").attr("src");
	    parent.$('#parentIframe span').html(cname);
		parent.$('#parentIframe').css("display","inline-block");
	    parent.$('.Current_page').attr("name",herf).css({"color":"#4c8fbd","cursor":"pointer"});
		//parent.$('.Current_page').html("<a href='javascript:void(0)' name="+herf+">" + cnames + "</a>");
	    parent.layer.close(index);
		
	});

</script>
