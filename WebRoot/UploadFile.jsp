<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'UploadFile.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action="myinsertpro" method="post" enctype="multipart/form-data">
    <table>
    
    <tr>
    <td>file</td>
    <td id="more">
    <input type="file" name="http://localhost:8080/BidSystem/UploadFile.jsp">
    <input type="button" value="Add More.." onclick="addMore()">
    </td>
    </tr>
    
    <tr><td><input type="submit" value="提交"></td></tr>
    </table>
    </form>
  </body>
  
 <script type="text/javascript">        
 function addMore()
 {
     var td = document.getElementById("more"); 
    // var br = document.createElement("br");
     var input = document.createElement("input");
     var button = document.createElement("input");
     
     input.type = "file";
     input.name = "file";
     
     button.type = "button";
     button.value = "Remove";
     
     button.onclick = function()
     {
        // td.removeChild(br);
         td.removeChild(input);
         td.removeChild(button);
     }
    // td.appendChild(br);
     td.appendChild(input);
     td.appendChild(button);
 }
</script>
</html>
