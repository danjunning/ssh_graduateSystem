package cn.nsu.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.nsu.dao.OrderitemDAO;
import cn.nsu.dao.OrdersDAO;
import cn.nsu.dao.ProductDAO;
import cn.nsu.dao.ShopcartDAO;
import cn.nsu.dao.StyleDAO;
import cn.nsu.dao.SubtypeDAO;
import cn.nsu.dao.TypeDAO;
import cn.nsu.dao.UserDAO;
import cn.nsu.entity.Orderitem;
import cn.nsu.entity.Orders;
import cn.nsu.entity.Product;
import cn.nsu.entity.Shopcart;
import cn.nsu.entity.Style;
import cn.nsu.entity.Subtype;
import cn.nsu.entity.Type;
import cn.nsu.entity.User;
import cn.nsu.serviceIml.AdvertiseManageIml;
import cn.nsu.serviceIml.ProductMangeIml;
import cn.nsu.serviceIml.UserManagmentIml;
import cn.nsu.tools.GetAdvertiseNum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class test4 extends AbstractJUnit4SpringContextTests {
	@Resource(name="productdao")
	ProductDAO pdao;
	@Resource(name="product")
	Product p;
	@Resource(name="pmiml")
	ProductMangeIml pmiml;
	@Resource(name="adveriml")
	AdvertiseManageIml amiml;
	@Test
	public void testProduct(){
		/*List<Product> products=pdao.findTopeight(8);
		for(Product p:products){
			System.out.println(p.getProductName()+":"+p.getSaleCount());
		}*/
		//List<Product> products=pdao.orderBypriceAscAndParm("电", 1);
		List<Product> products=pdao.orderBypriceDeAndParam("电", 1);
		
		for(Product p:products){
			System.out.println(p.getProductName()+":"+p.getSaleCount()+"--"+p.getProductPrice());
		}
		
	}
	
	@Test
	public void testProductList(){
		//ArrayList<HashMap> list= pmiml.Phonelist();
		//ArrayList<HashMap> list= pmiml.Computerlist();
		 //ArrayList<HashMap> list= pmiml.Camarolist();
		 ArrayList<HashMap> list= pmiml.Wearlist();
		for(HashMap<Object, Object> map:list){
			System.out.println(map.get("productId")+"--"+map.get("productImg")+"---"+map.get("productName")+"--"+map.get("productPrice"));
		}
	}
	@Resource
	GetAdvertiseNum getan;
	@Test
	public void testGetAdNum(){
		System.out.print(getan.getAdvNumId());
		
	}
	@Test
	public void testAdver(){
		amiml.Adverlist();
	}
	@Resource(name="usermagiml")
	UserManagmentIml umiml;
	@Test
	public void testUser(){
		umiml.uploadPhoto(4, "upload/智能手环4.jpg");
	}
	
	@Resource(name="ordersdao")
	OrdersDAO od;
	@Test
	public void testPayOrder(){
		od.selectByorderNum("2018041216340001", 4);
	}
	
}
