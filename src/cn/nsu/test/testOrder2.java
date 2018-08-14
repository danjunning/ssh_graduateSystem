package cn.nsu.test;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.hibernate.ejb.TransactionImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.nsu.dao.CreditDAO;
import cn.nsu.dao.OrderitemDAO;
import cn.nsu.entity.Credit;
import cn.nsu.entity.Orderitem;
import cn.nsu.entity.Orders;
import cn.nsu.serviceIml.BuyGoodsServiceIml;
import cn.nsu.serviceIml.OrderPlaceIml;
import cn.nsu.serviceIml.TransactionServiceIml;
import cn.nsu.serviceIml.UserSellerRefundServiceIml;
import cn.nsu.tools.GetCurrentDate;
import cn.nsu.tools.GetOrderNum;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class testOrder2 extends AbstractJUnit4SpringContextTests {
	@Resource(name="getOrderNum")
	GetOrderNum gon;
	@Resource(name="getTime")
	GetCurrentDate gcd;
	@Resource(name="opiml")
	OrderPlaceIml oriml;
	@Resource(name="tranmaniml")
	TransactionServiceIml tsiml;
	
	@Test
	public void test() {
		Orders order=new Orders(gon.getOrderNo(), "0277827489852898278", gcd.getCurrentDate(), null, 
				"待付款", 6400.0, 2);
		System.out.println(order.getOrderNumber());
		Orderitem orderitem=new Orderitem("小米20180327", "小米图片", "白色", "标配", 
				2, 6400.0, "四川省，成都青城山，李四，18787239802", order.getOrderNumber());
		//System.out.println(oidao.selectByOrderId("2018032710500001").getOrderId());
		
		/*ArrayList<HashMap> maplist=oriml.showUserUnpayOrder(2);
		for(int i=0;i<maplist.size();i++){
			System.out.println(maplist.get(i).get("orderDate")+" "+maplist.get(i).get("orderNum")
					+" "+maplist.get(i).get("ordestatus")+" "+maplist.get(i).get("proImg")
					+" "+maplist.get(i).get("proName")+" "+maplist.get(i).get("totalprice"));
		}*/
		/*oriml.showSucessReceiveOrder(1);
		oriml.showUserWaitReceiveOrder(1);
		oriml.UserwaitSendOrder(1);
		tsiml.waitReceiveOrders();
		tsiml.waitSendOrders();*/
		//oriml.cancelOrder("2018032710500001", 1);//取消订单
		//oriml.payOrder("2018032711190001", 2, "0277827489852848245", "336666");//支付
		//tsiml.sendGood("2018032711190001", gcd.getCurrentDate());//发货
		//oriml.UserconfirmOrder(1, "2018032710510001");//用户确认收货
		//ArrayList<HashMap> maplist=oriml.UserwaitSendOrder(1);//用户查看待发货
		//ArrayList<HashMap> maplist=tsiml.waitSendOrders();//商家看待发货
		//ArrayList<HashMap> maplist=tsiml.waitReceiveOrders();//商家查看待收货
		//ArrayList<HashMap> maplist=oriml.showUserWaitReceiveOrder(1);//用户查看待收货
		//ArrayList<HashMap> maplist=tsiml.ReceivedOrder();//用户查看已收货
	
		
		//oriml.cancelOrder("2018032711190001", 2);//取消订单
		
		//查看订单详情
		HashMap<String,String> map=oriml.OrderDetail(9);
		System.out.println(map.get("orderId")+" "+map.get("orderNum")+" "+map.get("orderDate")+" "+map.get("proImg")
		                   +" "+map.get("proName")+" "+map.get("color")+" "+map.get("style")+" "+map.get("count")
		                   +" "+map.get("ordestatus")+" "+map.get("ordedate")+" "+map.get("ordedealdate")+
		                   " "+map.get("paybank")+" "+map.get("totalprice"));	
	}
	
	@Resource(name="usrefuiml")
	UserSellerRefundServiceIml usrefuiml;
	@Resource(name="creditdao")
	CreditDAO cdao;
	
	@Test
	public void testRefund(){
		//usrefuiml.applyRefund("2018032710510001");
		
		//ArrayList<HashMap> maplist=usrefuiml.showRefundlist();//用户查看已收货
		//ArrayList<HashMap> maplist=usrefuiml.showSuccRefundlist();//商家查看已退款货
		ArrayList<HashMap> maplist=usrefuiml.showUserSuccRefundlist(2);//用户查看成功退款的订单
		for(int i=0;i<maplist.size();i++){
			System.out.println(maplist.get(i).get("orderId")+" "+maplist.get(i).get("orderDate")
					+" "+maplist.get(i).get("orderNum")+" "+maplist.get(i).get("ordestatus")
					+" "+maplist.get(i).get("proImg")+" "+maplist.get(i).get("proName")
					+" "+maplist.get(i).get("color")+" "+maplist.get(i).get("style")
					+" "+maplist.get(i).get("count")+" "+maplist.get(i).get("totalprice"));
		}
		/*Credit credit=cdao.selectByCreNumAndUid("0287817289827689872", 1);
		System.out.println(credit.getBalance());*/
		//usrefuiml.refund(10, "0287817289827689872", gcd.getCurrentDate());
	}
	
	
	
	
	
		

}
