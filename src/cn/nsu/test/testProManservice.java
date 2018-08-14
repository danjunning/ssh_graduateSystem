package cn.nsu.test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.context.support.UiApplicationContextUtils;

import cn.nsu.dao.ColorDAO;
import cn.nsu.dao.CreditDAO;
import cn.nsu.dao.EvaluateDAO;
import cn.nsu.dao.ImageDAO;
import cn.nsu.dao.OrderitemDAO;
import cn.nsu.dao.OrdersDAO;
import cn.nsu.dao.ProductDAO;
import cn.nsu.dao.ShopcartDAO;
import cn.nsu.dao.StyleDAO;
import cn.nsu.dao.SubtypeDAO;
import cn.nsu.dao.TypeDAO;
import cn.nsu.dao.UserDAO;
import cn.nsu.entity.Address;
import cn.nsu.entity.Color;
import cn.nsu.entity.Credit;
import cn.nsu.entity.Evaluate;
import cn.nsu.entity.Image;
import cn.nsu.entity.Orderitem;
import cn.nsu.entity.Orders;
import cn.nsu.entity.Product;
import cn.nsu.entity.Shopcart;
import cn.nsu.entity.Style;
import cn.nsu.entity.Subtype;
import cn.nsu.entity.Type;
import cn.nsu.entity.User;
import cn.nsu.serviceIml.ProductMangeIml;
import cn.nsu.serviceIml.UserManagmentIml;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class testProManservice extends AbstractJUnit4SpringContextTests {
	@Resource(name="pmiml")
	ProductMangeIml pmiml;
	@Resource(name="product")
	Product p;
	@Test
	public void test(){
		
		Date date=new Date();
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String productDate=dateFormat.format(date);//将日期对象转化成日期格式的【字符串】 
		System.out.println(productDate);
		
		Product pro=new Product();
		pro.setProductDate(productDate);
		pro.setProductName("测试新商品");
		pro.setProductPrice(120.0);
		pro.setProductStock(200);
		pro.setSubtypeId(5);
		
		String style = "风格1，风格2，风格3";
        String[] s = style.split("，"); 
        ArrayList<String> slist = new ArrayList<String>();
        for(int i=0;i<s.length;i++)
        {
            slist.add(s[i]);
        }
        String color = "颜色1，颜色2，颜色3";
        String[] c = color.split("，"); 
        ArrayList<String> clist = new ArrayList<String>();
        for(int i=0;i<c.length;i++)
        {
            clist.add(c[i]);
        }
        String img = "图片1，图片2，图片3，图片3";
        String[] im = img.split("，"); 
        ArrayList<String> ilist = new ArrayList<String>();
        for(int i=0;i<im.length;i++)
        {
            ilist.add(im[i]);
        }
		
        //pmiml.deleteProduct(9);
        for(int i=0;i<1;i++){
        pmiml.addProduct(pro, clist, slist, ilist);
        }
	}
	@Test
	public void testsplit(){
		String str = "11111,21212,234234,23423424,234234";
        String[] strs = str.split(","); 
        List<String> list = new ArrayList<String>();
        for(int i=0;i<strs.length;i++)
        {
        	System.out.println(strs[i]);
            list.add(strs[i]);
        }
		
		
		
	}
	
	
	
}
