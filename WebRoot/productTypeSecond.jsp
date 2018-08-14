<%@page import="cn.nsu.entity.Subtype"%>
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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/style.css"/>
    <link rel="stylesheet" href="assets/css/ace.min.css" />
    <link rel="stylesheet" href="assets/css/font-awesome.min.css" />

    <link href="Widget/icheck/icheck.css" rel="stylesheet" type="text/css" />
    <!--[if IE 7]>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css"/>
    <![endif]-->
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/typeahead-bs2.min.js"></script>
    <!-- page specific plugin scripts -->
    <script src="assets/js/jquery.dataTables.min.js"></script>
    <script src="assets/js/jquery.dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="js/H-ui.js"></script>
    <script type="text/javascript" src="js/H-ui.admin.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript" ></script>
    <script src="assets/laydate/laydate.js" type="text/javascript"></script>

    <script src="js/lrtk.js" type="text/javascript" ></script>
    <title>产品列表</title>
</head>
  
<body>
<%
	List<Type> fisrttypelist=(List<Type>)request.getSession().getAttribute("fisrttypelist");
	List<Subtype> secondtypelist=(List<Subtype>)request.getSession().getAttribute("secondtypelist");
 %>
<div class=" page-content clearfix">
    <div id="products_style">
        <div class="search_style">
          <form action="searchSubType" method="post" id="formid">
            <ul class="search_content clearfix">
                <li><label class="l_f">选择二级分类</label>&nbsp;
                <!-- <input name="subtypeName" type="text"  class="text_add" placeholder="输入关键字"  style="width:250px"/> -->
                <%if(fisrttypelist!=null){%>
                <select name="typeId" style="width: 250px">
                	<%for(int i=0;i<fisrttypelist.size();i++){ %>  
                	<option value="<%=fisrttypelist.get(i).getTypeId()%>"><%=fisrttypelist.get(i).getTypeName() %></option>
                      <%} %>            
                </select>                
                 <%}else{%>
                 <input name="subtypeName" type="text"  class="text_add" placeholder="输入关键字"  style="width:250px"/>
                 <%} %>
                </li>
                <li style="width:90px;"><button type="submit" class="btn_search"><i class="icon-search"></i>查询</button></li>
            </ul>
           </form> 
        </div>
       <div class="border clearfix">
	       <span class="l_f">
	        <a href="Add_secendType.jsp" title="添加分类" class="btn btn-warning Order_form"><i class="icon-plus"></i>添加分类</a>
	       </span>
	       <%if(secondtypelist!=null){ %> 
	       <span class="r_f">共：<b><%=secondtypelist.size() %></b>个二级分类</span> 
	       <%}else{ %>  
	       <span class="r_f">共：<b>0</b>个二级分类<</span>
	       <%} %>
       </div>
        <div class="table_menu_list" id="testIframe">
            <table class="table table-striped table-bordered table-hover" id="sample-table">
                <thead>
                <tr>
                    <th width="80px">序号</th>
                    <th width="150px">类型名称</th>
                    <th width="200px">操作</th>
                </tr>
                </thead>
                <tbody>
                <%if(secondtypelist!=null){
                	for(int i=0;i<secondtypelist.size();i++){
                %>
                <tr>
                    <td width="80px"><%=i+1 %></td>
                    <td width="150px">
                        <u class="text-primary" ><%=secondtypelist.get(i).getSubtypeName()%></u>        
                    </td>
                    <td class="td-manage"><!-- href="deleteSubType.action?subtypeId=<%=secondtypelist.get(i).getSubtypeId()%>" -->                  
                      <a title="删除" onclick="member_del(this,'<%=secondtypelist.get(i).getSubtypeId()%>')"   class="btn btn-xs btn-warning" ><i class="icon-trash  bigger-120"></i></a>
                    </td>
                </tr>	
                 <%}}else{%>
                 	<p>没有相关记录!!!</p>
                 <%} %>

                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>

<script type="text/javascript">

    /*产品-编辑*/
    function member_edit(title,url,id,w,h){
        layer_show(title,url,w,h);
        location.href="Add_Brand.jsp"
    }

    /*产品-删除*/
    function member_del(obj,subtypeId){
        layer.confirm('确认要删除吗？',function(index){
            //$(obj).parents("tr").remove();
           location.href="deleteSubType.action?subtypeId="+subtypeId;
            layer.msg('已删除!',{icon:1,time:1000});
        });
    }

</script>
