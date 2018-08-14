package cn.nsu.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.hibernate.ejb.TransactionImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.nsu.dao.EvaluateDAO;
import cn.nsu.entity.Evaluate;
import cn.nsu.entity.Orderitem;
import cn.nsu.entity.Orders;
import cn.nsu.entity.Shopcart;
import cn.nsu.serviceIml.BuyGoodsServiceIml;
import cn.nsu.serviceIml.EvaluateManageIml;
import cn.nsu.serviceIml.ShopCartServiceIml;
import cn.nsu.serviceIml.UserSellerRefundServiceIml;
import cn.nsu.tools.GetCurrentDate;
import cn.nsu.tools.GetOrderNum;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class testShopCart extends AbstractJUnit4SpringContextTests {
	@Resource(name="getOrderNum")
	GetOrderNum gon;
	@Resource(name="getTime")
	GetCurrentDate gcd;
	@Resource(name="sciml")
	ShopCartServiceIml scimi;
	@Resource(name="bgiml")
	BuyGoodsServiceIml bgiml;
	@Test
	public void testSC() {
		Shopcart shopcart=new Shopcart("颜色2", "风格2", 2, 24, gcd.getCurrentDate(), 1);
		//scimi.addShopCart(shopcart);
		/*ArrayList<HashMap> maps=scimi.showShopCartlist(1);
		for(int i=0;i<maps.size();i++){
			System.out.println(maps.get(i).get("shopcartId")+" "+maps.get(i).get("productId")+" "
			+maps.get(i).get("proImg")+" "+maps.get(i).get("proName")+""+maps.get(i).get("proColor")+" "
					+maps.get(i).get("proStyle")+" "+maps.get(i).get("proAmount"));
		}*/
		//scimi.delelteById(4);
		//scimi.updateCartCount(11, 5);
		Orders order=new Orders(gon.getOrderNo(), "0277827489852898278", gcd.getCurrentDate(), null, 
				"待付款", 6400.0, 2);
		System.out.println(order.getOrderNumber());
		Orderitem orderitem=new Orderitem("小米20180327", "小米图片", "白色", "标配", 
				2, 6400.0, "四川省，成都青城山，李四，18787239802", order.getOrderNumber());		
		bgiml.placeOrderFromCart(6, order, orderitem);
		
	}
	
	@Resource(name="evaiml")
	EvaluateManageIml evaiml;
	@Resource(name="evaluatedao")
	EvaluateDAO edao;
	@Test
	public void testEvaluate(){
		Evaluate evaluate=new Evaluate("发货很快，商品很不错呢！", gcd.getCurrentDate(),
				1, 24, 9);
		//evaiml.addEvaluate(1, evaluate);
		//evaiml.deleteEvaluate(2);
		//evaiml.updateEvaluate(3, "今天是3月27号，天气很晴朗，小伤感");
		
		//ArrayList<HashMap> maps=evaiml.showEvaluatelistByUser(1);	
		//ArrayList<HashMap> maps=evaiml.showEvaluateByProId(24);
		ArrayList<HashMap> maps=evaiml.showAllEvaluate();
		for(int i=0;i<maps.size();i++){
			System.out.println(maps.get(i).get("proId")+" "+maps.get(i).get("userName")+" "
			+maps.get(i).get("proName")+" "+maps.get(i).get("content")+" "
					+maps.get(i).get("evaDate"));
		}
		/*List<Evaluate> eList=edao.selectAll();
		for(Evaluate e:eList){
			System.out.println(e.getEvaluateDate()+" "+e.getContent()+" "
					+e.getProductId()+" "+e.getUserId());
		}*/
	}
	@Resource(name="usrefuiml")
	UserSellerRefundServiceIml usiml;
	@Test
	public void testRefund(){
		usiml.refund(11, "0277827489852898278", gcd.getCurrentDate());
	}
	
	
	
	
		

}
