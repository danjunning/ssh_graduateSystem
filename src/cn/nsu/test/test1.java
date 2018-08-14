package cn.nsu.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class test1 extends AbstractJUnit4SpringContextTests {
	@Resource(name="userdao")
	UserDAO udao;
	

	@Test
	public void test() {
		User user=new User();
		user.setUname("王麻子");
		user.setPassword("lisi123");
		//udao.save(user);
		/*
		List<User> list=udao.findAll();
		for(User u:list){
			System.out.println(u.getUname());
		}
		System.out.println(udao.findById(2).getUname());
		*/
		
		//udao.updatePsw(1, "asdf");
		//udao.updateUser(1, user);
		//udao.uploadPhoto(1, "图片");
		//System.out.println(udao.IsUser("张三"));
		int re=(int) Math.ceil(43d/43);
		System.out.println(re);
		
	}
	@Resource(name="typedao")
	TypeDAO tdao;
	@Test
	public void testType(){
		Type type=new Type();
		type.setTypeName("手机");
		tdao.findById(1);
		tdao.save(type);
		//tdao.deleteById(2);
		//tdao.deleteByName("修改分类");
		//tdao.updateType(1, "修改分类");
		tdao.deleteByName("你");
	}
	
	@Resource(name="subtypedao")
	SubtypeDAO sdao;
	@Test
	public void testSubType(){
		Subtype stype=new Subtype();
		stype.setTypeId(1);
		stype.setSubtypeName("sanxing系列");
		sdao.findByTypeId(1);
		//sdao.save(stype);
		sdao.deleteByName("sanxing系列");
		//sdao.deleteById(1);
		//sdao.deleteBytypeId(1);
		for(Subtype s:sdao.findByTypeId(1)){
			System.out.println(s.getSubtypeName());
		}
		//sdao.save(stype);
	}
	
	@Resource(name="styledao")
	StyleDAO styledao;
	@Test
	public void testStyle(){
		styledao.insertById_prd(1, "kushi1");
		//styledao.deleteById_prd(1);
		
		for(Style s:styledao.findByProductId(1)){
			System.out.println(s.getStyle());
		}
	}
	
	@Resource(name="shopcartdao")
	ShopcartDAO scdao;
	@Resource(name="shopcart")
	Shopcart shopcart;
	@Test
	public void testShopcart(){
		shopcart.setUserId(1);
		shopcart.setProductId(1);
		shopcart.setProStyle("标准1");
		shopcart.setProColor("白色2");
		shopcart.setProAmount(2);
		scdao.save(shopcart);
		for(Shopcart s:scdao.findByUserId(1)){
			System.out.println(s.getProductId());
		}
		//scdao.update(2, shopcart);
		//scdao.deleteByshopcartId(3);
		//scdao.deleteByuserid(1);
	}
	
	@Resource(name="productdao")
	ProductDAO pdao;
	@Resource(name="product")
	Product p;
	@Test
	public void testProduct(){
		/*for(Product p:pdao.findBySubtypeId(2)){
			System.out.println(p.getProductName());
		}*/
		p.setProductName("新增1");
		p.setProductDetail("刚刚新增的商品");
		p.setProductStock(100);
		p.setSaleCount(30);
		p.setSubtypeId(2);
		p.setProductPrice(300.0);
		//pdao.save(p);
		
		//pdao.update(1, p);
		//pdao.updateCommentCount(1, 11);
		//pdao.updateSaleCount(1, 22);
		//pdao.updateStock(1, 33);
		//System.out.println(pdao.countAll());
		//System.out.println(pdao.countBysubtype(1));
		/*for(Product p:pdao.selectByParm("w是")){
			p.getProductName();
		}*/
		/*for(Product p:pdao.orderBycomment(2)){
			System.out.println(p.getCommentCount());
		}*/
		/*for(Product p:pdao.orderBysaleCount(2)){
			System.out.println(p.getSaleCount());
		}*/
		/*for(Product p:pdao.orderBypriceAsc(2)){
			System.out.println(p.getProductPrice());
		}
		for(Product p:pdao.orderBypriceDe(2)){
			System.out.println(p.getProductPrice());
		}*/
		/*for(Product p:pdao.orderBydate(2)){
			System.out.println(p.getProductDate());
		}*/
		System.out.println(pdao.findById(68));
		
	}
	
	@Resource(name="orderitemdao")
	OrderitemDAO oidao;
	@Resource(name="orderitem")
	Orderitem oitem;
	@Test
	public void testOrderitem(){
		oitem.setProductColor("红色");
		oitem.setProductImage("图片1");
		oitem.setProductName("新名字");
		oitem.setProductStyle("风格1");
		oitem.setProductQuantity(100);
		oitem.setTotalPrice(3000.00);
		oitem.setAddress("金锣");
		//oitem.setOrderId(11);	
		//oidao.save(oitem);
		for(Orderitem oi:oidao.findByOrderId(1)){
			System.out.println(oi.getAddress());
		}
		//oidao.deleteByorderId(11);
	}
	
	@Resource(name="ordersdao")
	OrdersDAO odao;
	@Resource(name="orders")
	Orders o;
	@Test
	public void testOrder(){
		o.setOrderNumber("02918031212272345");
		o.setPayBank("中国建设银行");
		o.setOrderDate("2018-03-12");
		o.setOrderStatus("未付款");
		o.setTotalPrice(500.0);
		o.setUserId(2);
		
		//odao.save(o);
		for(Orders o:odao.findByUserId(2)){
			System.out.println(o.getOrderNumber());
		}
		//odao.updateStatusByorderId("待发货", 4);
		
		odao.selectByorderNum("02918031212272342",1).getOrderNumber();
		
		for(Orders o:odao.findByOrderNumber("02918031212272342")){
			System.out.println(o.getOrderNumber());
		}
	}
	
	
		

}
