<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<meta charset="utf-8" />
	<title>网站后台管理系统  </title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />

	<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="assets/css/font-awesome.min.css" />

	<link rel="stylesheet" href="assets/css/ace.min.css" />
	<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
	<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
    <link rel="stylesheet" href="css/style.css"/>
    <script src="js/included.js" type="text/javascript"></script>

  </head>
  
 <body>
 
		<div class="navbar navbar-default" id="navbar">
        <script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>
			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>					
						<img src="" width="">
						</small>
					</a><!-- /.brand -->
			</div><!-- /.navbar-header -->
			<div class="navbar-header operating pull-left">
			</div>

		    <div class="navbar-header pull-right" role="navigation">
                <ul class="nav ace-nav">
                 <li class="light-blue">
				  <a data-toggle="dropdown" href="#" class="dropdown-toggle">
				   <span  class="time"><em id="time"></em></span>
                   <span class="user-info">管理员</span>
				 <i class="icon-caret-down"></i>
				 </a>

				 <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
				   <li><a href="javascript:void(0)" id="Exit_system"><i class="icon-off"></i>退出</a></li>
				 </ul>

			    </li> <!-- class="light-blue"> -->
			   </ul>
              </div>

			</div>
		</div>

		<div class="main-container" id="main-container">
            <script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
				<div class="sidebar" id="sidebar"><!--233行-->
                <script type="text/javascript">
					try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
				</script>
				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<a class="btn btn-success"><i class="icon-signal"></i></a>
						<a class="btn btn-info"><i class="icon-pencil"></i></a>
						<a class="btn btn-warning"><i class="icon-group"></i></a>
						<a class="btn btn-danger"><i class="icon-cogs"></i></a>
				</div>

				<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
					<span class="btn btn-success"></span>
					<span class="btn btn-info"></span>
					<span class="btn btn-warning"></span>
					<span class="btn btn-danger"></span>
				</div>
	</div><!-- #sidebar-shortcuts -->

    <script type="text/javascript">
        $("#menu_style").niceScroll({
            cursorcolor:"#888888",
            cursoropacitymax:1,
            touchbehavior:false,
            cursorwidth:"5px",
            cursorborder:"0",
            cursorborderradius:"5px"
        });
    </script>
            <div id="menu_style" class="menu_style">

              <ul class="nav nav-list" id="nav_list">
               <li class="home">
                   <a href="javascript:void(0)" name="home.jsp" class="iframeurl" title="系统首页">
                    <i class="icon-home"></i><span class="menu-text"> 系统首页 </span>
                   </a>
               </li>
               <li>
                   <a href="#" class="dropdown-toggle">
                      <i class="icon-desktop"></i><span class="menu-text"> 产品管理 </span><b class="arrow icon-angle-down"></b>
                   </a>
                 <ul class="submenu">
                  <li class="home"><a  href="javascript:void(0)" name="getAllType"  title="一级类别" class="iframeurl">
                      <i class="icon-double-angle-right"></i>一级类别</a></li>
                  <li class="home"><a  href="javascript:void(0)" name="getAllSubType" title="二级类别"  class="iframeurl">
                      <i class="icon-double-angle-right"></i>二级类别</a></li>
                  <li class="home"><a href="javascript:void(0)" name="getAllPro" title="分类管理"  class="iframeurl">
                      <i class="icon-double-angle-right"></i>商品管理</a></li>
                 </ul>
               </li>

              <li>
                  <a href="#" class="dropdown-toggle"><i class="icon-picture "></i><span class="menu-text"> 广告管理 </span><b class="arrow icon-angle-down"></b></a>
                  <ul class="submenu">
                      <li class="home"><a href="javascript:void(0)" name="getAllAdver" title="分类管理"  class="iframeurl">
                          <i class="icon-double-angle-right"></i>广告管理</a></li>
                  </ul>
              </li>

               <li>
                <a href="#" class="dropdown-toggle"><i class="icon-list"></i><span class="menu-text"> 交易管理 </span><b class="arrow icon-angle-down"></b></a>
                 <ul class="submenu">
                 	<li class="home">
                    	<a href="javascript:void(0)" name="getWsendOrList" title="订单处理"  class="iframeurl">
                        <i class="icon-double-angle-right"></i>待处理订单</a>
                    </li>
                    <li class="home"><!-- name="getReceicingOrList" -->
                    	<a href="javascript:void(0)" name="getReceicingOrList" title="送货中订单"  class="iframeurl">
                        <i class="icon-double-angle-right"></i>送货中订单</a>
                    </li>
                    <li class="home"> <!--name="getReceivedOrList"  --> 
                 		<a href="javascript:void(0)" name="getReceivedOrList" title="已完成订单"  class="iframeurl">
                    	<i class="icon-double-angle-right"></i>已完成订单</a>
                    </li>
                 	<li class="home"><!--name="getRefundOrList"  -->
                   		<a href="javascript:void(0)" name="getRefundOrList" title="退款管理"  class="iframeurl">
                        <i class="icon-double-angle-right"></i>退款管理</a>
                    </li>
                 </ul>
               </li>
               <li>
                <a href="#" class="dropdown-toggle">
                  <i class="icon-user"></i><span class="menu-text"> 会员管理 </span><b class="arrow icon-angle-down"></b>
                </a>
                <ul class="submenu">
                 <li class="home"><a href="javascript:void(0)" name="userList.jsp" title="会员列表"  class="iframeurl">
                    <i class="icon-double-angle-right"></i>会员列表</a></li>
                
                </ul>
              </li>

             <li>
               <a href="#" class="dropdown-toggle">
                     <i class="icon-comments-alt"></i><span class="menu-text"> 评论管理 </span><b class="arrow icon-angle-down"></b>
               </a>
               <ul class="submenu">
                    <li class="home"><a href="javascript:void(0)" name="getAllEva" title="留言列表" class="iframeurl">
                        <i class="icon-double-angle-right"></i>查看列表</a></li>
               </ul>
             </li>

             <!-- <li><a href="#" class="dropdown-toggle"><i class="icon-group"></i><span class="menu-text"> 管理员管理 </span><b class="arrow icon-angle-down"></b></a>
                <ul class="submenu">
                  <li class="home"><a href="javascript:void(0)" name="adminRootManage.jsp" title="权限管理"  class="iframeurl">
                      <i class="icon-double-angle-right"></i>权限管理</a></li>
                  <li class="home"><a href="javascript:void(0)" name="administratorList.jsp" title="管理员列表" class="iframeurl">
                      <i class="icon-double-angle-right"></i>管理员列表</a></li>
                  <li class="home"><a href="javascript:void(0)" name="admin_info.jsp" title="个人信息" class="iframeurl">
                      <i class="icon-double-angle-right"></i>个人信息</a></li>
                </ul>
             </li> -->
            </ul><!--class="nav nav-list" id="nav_list"-->

            </div><!--id="menu_style" class="menu_style" -->


			<div class="sidebar-collapse" id="sidebar-collapse">
				<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
			</div>
            <script type="text/javascript">
				try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
			</script>
	   </div>                                                                                                                    <!--  -->

		<div class="main-content">
               <script type="text/javascript">
					try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
		 </script>
			<div class="breadcrumbs" id="breadcrumbs">
				<ul class="breadcrumb">
					<li>
						<i class="icon-home home-icon"></i>
						<a href="index.jsp">首页
                              </a>
					</li>
					<li class="active"><span class="Current_page iframeurl"></span></li>
                          <li class="active" id="parentIframe"><span class="parentIframe iframeurl"></span></li>
					<li class="active" id="parentIfour"><span class="parentIfour iframeurl"></span></li>
				</ul>
			</div>
                  
               <iframe id="iframe" style="border:0; width:100%; background-color:#FFF;"name="iframe" frameborder="0" src="home.jsp">  </iframe>
                           <!-- /.page-content -->
		</div><!-- /.main-content -->
       </div><!-- /.main-container-inner -->
		</div><!--class="main-container" id="main-container" -->

         <!--底部样式-->
         <div class="footer_style" id="footerstyle">  
		 <script type="text/javascript">
             try{ ace.settings.check('footerstyle' , 'fixed') } catch(e){}
         </script>

          <p class="l_f"></p>
          <p class="r_f"></p>
         </div>

         <!--修改密码样式-->
         <div class="change_Pass_style" id="change_Pass">
            <ul class="xg_style">
             <li><label class="label_name">原&nbsp;&nbsp;密&nbsp;码</label>
                 <input name="原密码" type="password" class="" id="password"></li>
             <li><label class="label_name">新&nbsp;&nbsp;密&nbsp;码</label>
                 <input name="新密码" type="password" class="" id="Nes_pas"></li>
             <li><label class="label_name">确认密码</label>
                 <input name="再次确认密码" type="password" class="" id="c_mew_pas"></li>
            </ul>          
         </div>
        <!-- /.main-container -->
		<!-- basic scripts -->
    <script type="text/javascript">
        $('.change_Password').on('click', function(){
            layer.open({
                type: 1,
                title:'修改密码',
                area: ['300px','300px'],
                shadeClose: true,
                content: $('#change_Pass'),
                btn:['确认修改'],
                yes:function(index, layero){
                    if ($("#password").val()==""){
                        layer.alert('原密码不能为空!',{
                            title: '提示框',
                            icon:0

                        });
                        return false;
                    }
                    if ($("#Nes_pas").val()==""){
                        layer.alert('新密码不能为空!',{
                            title: '提示框',
                            icon:0

                        });
                        return false;
                    }

                    if ($("#c_mew_pas").val()==""){
                        layer.alert('确认新密码不能为空!',{
                            title: '提示框',
                            icon:0,

                        });
                        return false;
                    }
                    if(!$("#c_mew_pas").val || $("#c_mew_pas").val() != $("#Nes_pas").val() )
                    {
                        layer.alert('密码不一致!',{
                            title: '提示框',
                            icon:0

                        });
                        return false;
                    }
                    else{
                        layer.alert('修改成功！',{
                            title: '提示框',
                            icon:1
                        });
                        layer.close(index);
                    }
                }
            });
        });
    </script>

    <script type="text/javascript">
            $(function(){
                var cid = $('#nav_list > li> .submenu');//>是一种选择器
                cid.each(function(i){
                    $(this).attr('id',"Sort_link_"+i);

                })
            })

            jQuery(document).ready(function(){
                $.each($(".submenu"),function(){//遍历一个数组通常用$.each()来处理
                    var $aobjs=$(this).children("li");//children() 方法返回返回被选元素的所有直接子元素。
                    var rowCount=$aobjs.size();
                    var divHeigth=$(this).height();
                    $aobjs.height(divHeigth/rowCount);
                });
                /*
                 * 有10个submenu,分别是：产品管理、图片管理......管理员管理
                 * */
                //初始化宽度、高度
                $("#main-container").height($(window).height()-76);
                //获取匹配元素集合中的第一个元素的当前计算高度值 或 设置每一个匹配元素的高度值（带一个参数)
                $("#iframe").height($(window).height()-140);

                $(".sidebar").height($(window).height()-99);
                var thisHeight = $("#nav_list").height($(window).outerHeight()-173);
                $(".submenu").height();
                $("#nav_list").children(".submenu").css("height",thisHeight);

                //当文档窗口发生改变时 触发
                $(window).resize(function(){
                    $("#main-container").height($(window).height()-76);
                    $("#iframe").height($(window).height()-140);
                    $(".sidebar").height($(window).height()-99);

                    var thisHeight = $("#nav_list").height($(window).outerHeight()-173);
                    $(".submenu").height();
                    $("#nav_list").children(".submenu").css("height",thisHeight);
                });
                $(document).on('click','.iframeurl',function(){
                    var cid = $(this).attr("name");
                    var cname = $(this).attr("title");
                    $("#iframe").attr("src",cid).ready();
                    $("#Bcrumbs").attr("href",cid).ready();
                    $(".Current_page a").attr('href',cid).ready();
                    $(".Current_page").attr('name',cid);
                    $(".Current_page").html(cname).css({"color":"#333333","cursor":"default"}).ready();
                    $("#parentIframe").html('<span class="parentIframe iframeurl"> </span>').css("display","none").ready();
                    $("#parentIfour").html(''). css("display","none").ready();
                });
            });
            /******/
            $(document).on('click','.link_cz > li',function(){
                $('.link_cz > li').removeClass('active');
                $(this).addClass('active');
            });


            /*********************点击事件*********************/
            $( document).ready(function(){
                $('#nav_list,.link_cz').find('li.home').on('click',function(){
                    $('#nav_list,.link_cz').find('li.home').removeClass('active');
                    $(this).addClass('active');
                });
                //时间设置
                function currentTime(){
                    var d=new Date(),str='';
                    str+=d.getFullYear()+'年';
                    str+=d.getMonth() + 1+'月';
                    str+=d.getDate()+'日';
                    str+=d.getHours()+'时';
                    str+=d.getMinutes()+'分';
                    str+= d.getSeconds()+'秒';
                    return str;
                }

                setInterval(function(){$('#time').html(currentTime)},1000);



                $('#Exit_system').on('click', function(){
                    layer.confirm('是否确定退出系统？', {
                                btn: ['是','否'] ,//按钮
                                icon:2
                            },
                            function(){
                                location.href="login.jsp";

                            });
                });
            });

            function link_operating(name,title){
                var cid = $(this).name;
                var cname = $(this).title;
                $("#iframe").attr("src",cid).ready();
                $("#Bcrumbs").attr("href",cid).ready();
                $(".Current_page a").attr('href',cid).ready();
                $(".Current_page").attr('name',cid);
                $(".Current_page").html(cname).css({"color":"#333333","cursor":"default"}).ready();
                $("#parentIframe").html('<span class="parentIframe iframeurl"> </span>').css("display","none").ready();
                $("#parentIfour").html(''). css("display","none").ready();
            }
        </script>
		
</body>
</html>

