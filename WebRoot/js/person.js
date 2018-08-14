/**
 * 
 */
document.write('<script src="layui/layui.js"></script>');
document.write('<script src="js/jquery-1.11.3.js"></script>');
$(function(){
	layui.use(['layer', 'form'], function(){
		var form = layui.form;
        var layer=layui.layer;
	})
	
	$('.text-info').attr("disabled", true);
	$('.text-info').addClass("text-border");
	$('.text-border').css({'border':'none'});
	
	$('#set').on('click',function(){
		$('.text-info').attr("disabled", false);
		$('#save').css({'display':'inline-block'});
		$('.text-info').css("border","1px solid #E6E6E6");
	})	
	
	/*$('#save').on('click',function(){
		$('.text-info').attr("disabled", true);
		$('#save').css({'display':'none'});
		$('.text-info').css("border","none");
		
	})*/
	
	
	
	
})

 