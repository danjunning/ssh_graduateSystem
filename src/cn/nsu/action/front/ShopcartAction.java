package cn.nsu.action.front;


import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.nsu.entity.Shopcart;
import cn.nsu.serviceIml.ShopCartServiceIml;
import cn.nsu.tools.GetCurrentDate;
@Controller("ShopcartAction")
public class ShopcartAction implements SessionAware{
	@Resource(name="getTime")
	GetCurrentDate getTime;
	@Resource(name="sciml")
	ShopCartServiceIml sciml;
	
	private int productId;
	private String proColor;
	private String proStyle;
	private int proAmount;
	private int shopcartId;
	
	public String getCart(){
		int user_id=(int) session.get("userId");
		session.put("ucartlist", sciml.showShopCartlist(user_id));
		return "ok";
	}	
	public String addCart(){
		int user_id=(int) session.get("userId");
		Shopcart shopcart=new Shopcart();
		shopcart.setCartDate(getTime.getCurrentDate());
		shopcart.setProAmount(proAmount);
		shopcart.setProColor(proColor);
		shopcart.setProStyle(proStyle);
		shopcart.setProductId(productId);
		shopcart.setUserId(user_id);
		sciml.addShopCart(shopcart);
		session.put("ucartlist", sciml.showShopCartlist(user_id));
		return "ok";
	}
	public String  changeAmount(){
		sciml.updateCartCount(shopcartId, proAmount);
		return "ok";
	}
	public String deleteCart(){
		sciml.delelteById(shopcartId);
		return "ok";
	}

	
	Map<String, Object> session; 
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProColor() {
		return proColor;
	}
	public void setProColor(String proColor) {
		this.proColor = proColor;
	}
	public String getProStyle() {
		return proStyle;
	}
	public void setProStyle(String proStyle) {
		this.proStyle = proStyle;
	}
	public int getProAmount() {
		return proAmount;
	}
	public void setProAmount(int proAmount) {
		this.proAmount = proAmount;
	}
	public int getShopcartId() {
		return shopcartId;
	}
	public void setShopcartId(int shopcartId) {
		this.shopcartId = shopcartId;
	}
	
	

}
