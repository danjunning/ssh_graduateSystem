package cn.nsu.test;

import static org.junit.Assert.*;

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
import cn.nsu.interfaces.CategoryManage;
import cn.nsu.serviceIml.CategoryManageIml;
import cn.nsu.serviceIml.UserManagmentIml;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class testservice extends AbstractJUnit4SpringContextTests {
	@Resource(name="usermagiml")
	UserManagmentIml umiml;
	@Resource(name="user")
	User user;
	@Test
	public void test(){
		//umiml.login("单俊宁", "22");
		//umiml.register("单俊宁", "22");
		user.setUname("danjunning");
		user.setBirthday("1995-08-14");
		//umiml.userinfo(3, user);
		//umiml.uploadPhoto(3, "c://file/photo");
		//umiml.updatePsw(3, "22", "123");
		/*for(int i=0;i<11;i++){
			Address address=new Address("四川省", "成都东软", "","单俊宁", "18408248294",3);
			umiml.addAddress(3, address);
		}*/
		Address address=new Address("四川省", "成都东软许愿", "232311","单俊宁", "110",3);
		//umiml.updateAddress(2, address);
		//umiml.deleteAddress(1);
		
		/*for(int i=0;i<5;i++){
			Credit credit=new Credit("601482319994428991953", "中国银行", "991953", 100f, 1);
			umiml.addCredit(1,credit);
		}*/
		/*for(int i=0;i<5;i++){
			umiml.unbund("601482319994428991953");
		}*/
		//umiml.save(500f, "601482319994428991953");
		umiml.getbalance("0287812348715009827");
		umiml.save(500f, "0287812348715009827");
		
	}
	
	@Resource(name="cgiml")
	CategoryManageIml cgiml;
	@Test
	public void testCateManage(){
		//Type type=new Type("测试一级类别");
		//cgiml.addFirstType(type);
		/*for(int i=0;i<5;i++){
			Subtype subtype=new Subtype("测试二级类别"+i, "", 12);
			cgiml.addSecondtype(subtype);
		}*/
		//cgiml.deleteSecondtypeById(16);
		//cgiml.deleteFirstTypeById(12);

		List<String> typelist=cgiml.showFirstType();
		for(int i=0;i<typelist.size();i++){
			System.out.println(typelist.get(i));
		}
		List<String> subtypelist=cgiml.showSecondType(9);
		for(int i=0;i<subtypelist.size();i++){
			System.out.println(subtypelist.get(i));
		}
		List<Product> prolist=cgiml.showProductsStype(5);
		for(int i=0;i<prolist.size();i++){
			System.out.println(prolist.get(i).getProductName());
		}
	}
	

	
		

}
