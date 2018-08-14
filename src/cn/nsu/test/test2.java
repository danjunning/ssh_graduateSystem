package cn.nsu.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
import cn.nsu.entity.Color;
import cn.nsu.entity.Credit;
import cn.nsu.entity.Evaluate;
import cn.nsu.entity.Image;
import cn.nsu.entity.Orderitem;
import cn.nsu.entity.Orders;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class test2 extends AbstractJUnit4SpringContextTests {
	@Resource(name="ordersdao")
	OrdersDAO osdao;
	
	@Resource(name="orders")
	Orders os;
	@Test
	public void testOrders(){
		os.setOrderNumber("02817201803211310");
		os.setOrderStatus("待付款");
		os.setUserId(1);
		//osdao.save(os);
		/*
		for(Orders o:osdao.selectByorderNum("02817201803211313")){
			System.out.println(o.getOrderId());
		}
		for(Orders o:osdao.selectOrderByStatus("未付款")){
			System.out.println(o.getOrderId());
		}*/
		for(Orders o:osdao.selectOrderByUidandStatus(2, "未付款")){
			System.out.println(o.getOrderNumber());
		}
		System.out.println(osdao.countAll());
		//osdao.deleteById(5);
		System.out.println(osdao.countAll());
		System.out.println(osdao.countByStatus("未付款"));
		osdao.updateStatusByorderId("退款","2018-03-26",6);
	}
	
	@Resource(name="orderitemdao")
	OrderitemDAO oiDao;
	@Resource(name="orderitem")
	Orderitem oi;
	@Test
	public void testOrderitem(){
		oi.setOrderId("5");
		oi.setProductName("321新家");
		oi.setAddress("四川");
		oi.setProductColor("红");
		oi.setProductColor("联想");
		oi.setProductImage("主图");
		oi.setProductQuantity(20);
		oi.setProductStyle("款式也");
		oi.setTotalPrice(2000.0);
		//oiDao.save(oi);
		oiDao.findById(1).getProductName();
	}
	
	@Resource(name="imagedao")
	ImageDAO idao;
	@Resource(name="image")
	Image image;
	@Test
	public void testImage(){
		//image.setImage("新图片");
		//image.setProductId(2);
		idao.insertByPid(2, "新图片");
		for(Image i:idao.findAll()){
			System.out.println(i.getImageId()+" "+i.getImage());
		}
		System.out.println(idao.findByProductId(2));
	}
	@Resource(name="evaluatedao")
	EvaluateDAO edao;
	@Resource(name="evaluate")
	Evaluate e;
	
	@Test
	public void testEvaluate(){
		e.setContent("+3:今天很好");
		e.setProductId(1);
		e.setUserId(2);
		e.setEvaluateDate("2018-03-21-15:06");
		//edao.save(e);
		//edao.updateByevaId(1, "今天是3月21，天气不好");
		System.out.println(edao.findById(1));
		for(Evaluate e:edao.findByProductId(2)){
			System.out.println("商品："+e.getContent());
		}
		for(Evaluate e:edao.findByUserId(2)){
			System.out.println("用户："+e.getContent());
		}
		System.out.println("商品的评论数"+edao.countByprId(2));
		System.out.println("用户的评论："+edao.countByuId(2));
	}
	@Resource(name="credit")
	Credit c;
	@Resource(name="creditdao")
	CreditDAO cdao;
	
	@Test
	public void testCredit(){
		c.setBalance(200f);
		c.setCreditNumber("1277827289872898278");
		c.setCreditType("中国银行");
		c.setPayPassword("336666");
		c.setUserId(2);
		//cdao.save(c);
		
		System.out.println(cdao.findByCreditNumber("0287812348715009827"));
		for(Credit c:cdao.findByUserId(1)){
			System.out.println(c.getCreditType()+":"+c.getCreditNumber());
		}
		cdao.updateBalanceBycreNum(3200f, "1277827289872898278");		
	}
	
	@Resource(name="colordao")
	ColorDAO cDao;
	@Resource(name="color")
	Color color;
	
	@Test
	public void testColor(){
		color.setColor("黑色");
		color.setProductId(1);
		cDao.save(color);
		cDao.insertBycolorId(2, "荧光绿");
		for(Color c:cDao.findByProductId(1)){
			System.out.println("颜色"+c.getColor());
		}
	}

	
	
		

}
