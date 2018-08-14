package cn.nsu.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.nsu.dao.OrdersDAO;
import cn.nsu.tools.GetOrderNum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class testOrder extends AbstractJUnit4SpringContextTests {
	@Resource(name="getOrderNum")
	GetOrderNum gon;
	@Test
	public void test() {
		/*System.out.println(gon.getOrderNo());
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		for(int i=0;i<5;i++){
			HashMap<String,String> map=new HashMap<String,String>();
			map.put("orderId", "1");
			map.put("orderNum", "133344");
			map.put("proName", "商品名");
			map.put("color", "金色");
			map.put("style", "款式1");
			map.put("count", "20");
			map.put("price", "1005.5");
			map.put("ordestatus", "已付款");
			aList.add(map);
		}		
		for(int i=0;i<aList.size();i++){
			HashMap map=(HashMap)aList.get(i);
			System.out.println(map.get("orderId")+" "+map.get("orderNum")+" "+map.get("proName")+" "
					+map.get("color")+" "+map.get("style")+" "+map.get("count")+" "
					+map.get("price")+" "+map.get("ordestatus"));
		}*/
	}
	
	@Resource(name="ordersdao")
	OrdersDAO odao;
	@Test
	public void test1(){
		System.out.println(odao.selectByorderNum("02817201803211310",1).getOrderNumber());
	}
	
	
	
		

}
