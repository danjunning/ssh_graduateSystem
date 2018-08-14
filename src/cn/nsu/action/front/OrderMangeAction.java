package cn.nsu.action.front;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.Order;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.omg.CORBA.OMGVMCID;
import org.springframework.stereotype.Controller;

import cn.nsu.entity.Address;
import cn.nsu.entity.Orderitem;
import cn.nsu.entity.Orders;
import cn.nsu.serviceIml.OrderPlaceIml;
import cn.nsu.serviceIml.TransactionServiceIml;
import cn.nsu.serviceIml.UserManagmentIml;
import cn.nsu.tools.GetCurrentDate;
import cn.nsu.tools.GetOrderNum;

@Controller("OrderMangeAction")
public class OrderMangeAction implements SessionAware,ServletRequestAware{
	@Resource(name="opiml")
	OrderPlaceIml opiml;
	@Resource(name="getOrderNum")
	GetOrderNum get;
	@Resource(name="getTime")
	GetCurrentDate gettime;
	@Resource(name="usermagiml")
	UserManagmentIml umiml;
	@Resource(name="tranmaniml")
	TransactionServiceIml tsiml;
	
	private Integer productId;
	private String productName;
	private String productImage;
	private String productPrice;
	private Double totalPrice;
	private Integer productQuantity;
	private String productColor;
	private String productStyle;
	private String orderNumber;
	private String address;
	private Double price;
	private String payBank;
	private String payPassword;
	public String confirmOrder(){
		int userId=(int)session.get("userId");
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("productId", productId);
		map.put("productName", productName);
		map.put("productImage", productImage);
		map.put("productPrice", productPrice);
		map.put("totalPrice", totalPrice);
		map.put("productQuantity", productQuantity);
		map.put("productColor", productColor);
		map.put("productStyle", productStyle);
		map.put("orderNumber", GetOrderNum.getOrderNo());
		
		List<Address> list=umiml.addressList(userId);
		List<String> aList=new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			String address;
			address=list.get(i).getProvince()+" "+list.get(i).getAddress()+" "+list.get(i).getReceiver()+" "+list.get(i).getReceiveTel();
			aList.add(address);
		}
		session.put("orderinfo", map);
		session.put("addrelist", aList);
		return "ok";
	}
	
	
	public String placeOrder(){
		int userId=(int)session.get("userId");
		Orders order=new Orders();
		Orderitem orderitem=new Orderitem();
		order.setUserId(userId);
		order.setOrderDate(gettime.getCurrentDate());
		order.setOrderNumber(orderNumber);
		order.setOrderStatus("待付款");
		order.setProductId(productId);
		order.setTotalPrice(totalPrice);
		orderitem.setAddress(address);
		orderitem.setOrderId(orderNumber);
		orderitem.setProductColor(productColor);
		orderitem.setProductImage(productImage);
		orderitem.setProductName(productName);
		orderitem.setProductQuantity(productQuantity);
		orderitem.setProductStyle(productStyle);
		orderitem.setTotalPrice(totalPrice);
		opiml.placeOrder(order, orderitem);
		session.put("unpayorderlistByuer", opiml.showUserUnpayOrder(userId,1));//更新用户未支付订单
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("orderNumber", orderNumber);
		map.put("productName", productName);
		map.put("price", totalPrice);
		map.put("address", address);
		session.put("ordermap", map);
		return "ok";
	}
	public String confirm(){//从管理中心过来
		System.out.println("从管理中心过来");
		int userId=(int)session.get("userId");
		Orders orders=opiml.selectOrders(orderId);
		Orderitem oi=opiml.selectOrderitem(orders.getOrderNumber());	
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("orderNumber", orders.getOrderNumber());
		map.put("productName", oi.getProductName());
		map.put("price", orders.getTotalPrice());
		map.put("address", oi.getAddress());
		session.put("ordermap", map);
		session.put("creditList", umiml.creditList(userId));
		return "ok";
	}
	public String payOrder(){
		int userId=(int)session.get("userId");
		System.out.println(orderNumber+"--------"+userId);
		int result=opiml.payOrder(orderNumber, userId, payBank, payPassword);
		if(1==result){
			session.put("paymoney", price);
			session.put("orderNum", orderNumber);
			session.put("waitsendorderlistByuser", opiml.UserwaitSendOrder(userId,1));
			return "ok";
		}else if(0==result){
			request.setAttribute("paymsg", "支付密码错误");
			return "fail";
		}else{
			request.setAttribute("paymsg", "余额不足,请先去充值");
			return "fail";
		}
		
	}
	
	public String confirmReceived(){
		System.out.println("ajax执行确认收货");
		int userId=(int)session.get("userId");
		opiml.UserconfirmOrder(userId, orderNumber);
		return "ok";
	}
	
	public String preCancel(){
		System.out.println("ajax执行确认取消订单，还没付款");
		int userId=(int)session.get("userId");
		opiml.preCancel(orderNumber, userId);
		return "ok";
	}
	
	public String edCancel(){
		System.out.println("ajax执行确认取消订单，已经付款");
		int userId=(int)session.get("userId");
		opiml.edCancel(orderNumber, userId);
		return "ok";
	}
	public String deleteOrder(){
		int userId=(int)session.get("userId");
		opiml.deleteOrder(orderNumber);
		session.put("allorderlistByuser", opiml.UserallOrder(userId,1));
		return "ok";
	}
	public String detailOrder(){
		String orderNum=(String) session.get("orderNum");
		int orderId=opiml.orderId(orderNum);
		session.put("orderdetail",opiml.OrderDetail(orderId));
		return "ok";
	}
	private Integer orderId;
	public String orderDetail(){
		session.put("orderdetail",opiml.OrderDetail(orderId));
		return "ok";
	}
	
	Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}
	
	

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getProductColor() {
		return productColor;
	}
	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}
	public String getProductStyle() {
		return productStyle;
	}
	public void setProductStyle(String productStyle) {
		this.productStyle = productStyle;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}
	
	

}
