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
import cn.nsu.serviceIml.BuyGoodsServiceIml;
import cn.nsu.serviceIml.OrderPlaceIml;
import cn.nsu.serviceIml.ProductMangeIml;
import cn.nsu.serviceIml.ShopCartServiceIml;
import cn.nsu.serviceIml.UserManagmentIml;
import cn.nsu.tools.GetAdvertiseNum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class test5 extends AbstractJUnit4SpringContextTests {
	@Resource(name="sciml")
	ShopCartServiceIml sciml;
	@Test
	public void testProduct(){
		List<Integer> ids=new ArrayList<Integer>();
		ids.add(15);
		ids.add(28);
		ids.add(29);
		ArrayList<HashMap> arrayList= sciml.preorderlist(ids);
		for(int i=0;i<arrayList.size();i++){
			System.out.println(arrayList.get(i).get("productId")+"  "
					+arrayList.get(i).get("productName")+"  "
					+arrayList.get(i).get("productImage")+"  "
					+arrayList.get(i).get("productPrice")+"  "
					+arrayList.get(i).get("productQuantity")+"  "
					+arrayList.get(i).get("totalPrice")+"  "
					+arrayList.get(i).get("productColor")+"  "
					+arrayList.get(i).get("productStyle")+"  "
					+arrayList.get(i).get("orderNumber")+"  ");
		}
		
	}
	@Resource(name="bgiml")
	BuyGoodsServiceIml bgiml;
	@Test
	public void testPlaceOrders(){
		List<Integer> cartidlist=new ArrayList<Integer>();
		cartidlist.add(15);
		bgiml.placeOrders(cartidlist, 3, "山西省 成股东点击按共和国看电视了 算法 13423236709");
	}
	
	@Resource(name="opiml")
	OrderPlaceIml opiml;
	@Test
	public void testConfirmReceive(){
		//opiml.UserconfirmOrder(3, "2018041400050003");
		opiml.deleteOrder("2018041320450003");
	}
	@Resource(name="ordersdao")
	OrdersDAO odao;
	@Test
	public void testRefund(){

		
	}
	

	
}
