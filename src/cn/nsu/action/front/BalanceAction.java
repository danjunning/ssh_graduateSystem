package cn.nsu.action.front;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.nsu.entity.Address;
import cn.nsu.entity.Orderitem;
import cn.nsu.entity.Orders;
import cn.nsu.entity.Product;
import cn.nsu.entity.Shopcart;
import cn.nsu.serviceIml.BuyGoodsServiceIml;
import cn.nsu.serviceIml.OrderPlaceIml;
import cn.nsu.serviceIml.ProductMangeIml;
import cn.nsu.serviceIml.ShopCartServiceIml;
import cn.nsu.serviceIml.UserManagmentIml;
import cn.nsu.tools.GetCurrentDate;
import cn.nsu.tools.GetOrderNum;
@Controller("BalanceAction")
public class BalanceAction implements SessionAware,ServletRequestAware{
	@Resource(name="getTime")
	GetCurrentDate getTime;
	@Resource(name="sciml")
	ShopCartServiceIml sciml;
	@Resource(name="usermagiml")
	UserManagmentIml umiml;
	@Resource(name="bgiml")
	BuyGoodsServiceIml bgiml;
	@Resource(name="pmiml")
	ProductMangeIml pmiml;
	@Resource(name="opiml")
	OrderPlaceIml opiml;
	
	private List<Integer> shopcartId;
	public String balance(){
		int userId=(int)session.get("userId");	
		List<Address> list=umiml.addressList(userId);
		List<String> aList=new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			String address;
			address=list.get(i).getProvince()+" "+list.get(i).getAddress()+" "+list.get(i).getReceiver()+" "+list.get(i).getReceiveTel();
			aList.add(address);
		}
		session.put("addrelist", aList);
		session.put("preorderlist", sciml.preorderlist(shopcartId));
		return "ok";
	}
	private String address;
	public String placeOrders(){
		List<String> OrNumlist=new ArrayList<String>();
		Double payPrice=0d;
		int userId=(int)session.get("userId");	
		for(int i=0;i<shopcartId.size();i++){
			//Shopcart cart=scdao.findById(shopcartId.get(i));
			Shopcart cart=sciml.selectById(shopcartId.get(i));
			//Product p=pdao.findById(cart.getProductId());
			Product p=pmiml.selectById(cart.getProductId());
			//String productImage=idao.findByProductId(p.getProductId()).get(0).getImage();
			String productImage=pmiml.image(p.getProductId());
			Orders orders=new Orders();
			Orderitem oi=new Orderitem();
			String orderNumber=GetOrderNum.getOrderNo();
			orders.setOrderDate(getTime.getCurrentDate());
			orders.setOrderNumber(orderNumber);
			orders.setOrderStatus("待付款");
			orders.setUserId(userId);
			orders.setProductId(cart.getProductId());
			orders.setTotalPrice(p.getProductPrice()*cart.getProAmount());
			oi.setTotalPrice(p.getProductPrice()*cart.getProAmount());
			oi.setOrderId(orderNumber);
			oi.setAddress(address);
			oi.setProductName(p.getProductName());
			oi.setProductStyle(cart.getProStyle());
			oi.setProductColor(cart.getProColor());
			oi.setProductImage(productImage);
			oi.setProductQuantity(cart.getProAmount());
			bgiml.placeOrderFromCart(shopcartId.get(i), orders, oi);
			OrNumlist.add(orderNumber);
			payPrice+=p.getProductPrice()*cart.getProAmount();
		}	
		session.put("orderNumlist", OrNumlist);
		session.put("payMoney", payPrice);
		session.put("address", address);
		session.put("unpayorderlistByuer", opiml.showUserUnpayOrder(userId,1));//更新用户的未支付订单
		return "ok";
	}
	
	private List<String> orderNumber;
	private String payBank;
	private String payPassword;
	private float price;
	public String payOrders(){
		int userId=(int)session.get("userId");	
		int result=opiml.payOrders(orderNumber, userId, payBank, payPassword, price);
		if(result==1){
			session.put("waitsendorderlistByuser", opiml.UserwaitSendOrder(userId,1));
			session.put("paymoney", price);
			return "ok";
		}else if(0==result){
			request.setAttribute("payresult", "支付密码错误");
			return "fail";
		}else{
			request.setAttribute("payresult", "余额不够，请先充值");
			return "fail";
		}		
	}
	
	
	Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;	
	}
	public List<Integer> getShopcartId() {
		return shopcartId;
	}
	public void setShopcartId(List<Integer> shopcartId) {
		this.shopcartId = shopcartId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<String> getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(List<String> orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getPayBank() {
		return payBank;
	}
	public void setPayBank(String payBank) {
		this.payBank = payBank;
	}
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}
	
	

}
