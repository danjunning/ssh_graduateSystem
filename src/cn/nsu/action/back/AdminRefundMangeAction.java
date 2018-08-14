package cn.nsu.action.back;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.nsu.serviceIml.TransactionServiceIml;
import cn.nsu.serviceIml.UserSellerRefundServiceIml;
import cn.nsu.tools.GetCurrentDate;

@Controller("AdminRefManage")
public class AdminRefundMangeAction implements SessionAware{
	@Resource(name="getTime")
	GetCurrentDate getDate;
	@Resource(name="tranmaniml")
	TransactionServiceIml tsiml;
	@Resource(name="usrefuiml")
	UserSellerRefundServiceIml usrimlIml;
	
	private String payBank;
	private String orderNumber;
	private Integer orderId;
    Map<String, Object> session;
    
    public String getRefundOrList(){
		session.put("areforderlist", usrimlIml.selectOrderLikeStatus());
		return "ok";
	}
    public String  getAllUnrefundList(){
    	session.put("areforderlist", usrimlIml.showWaitRefundlist());
    	return "ok";
    }
	public String  getAllrefundedList(){
		session.put("areforderlist", usrimlIml.showSuccRefundlist());
		return "ok";
	}
    public String  getRefundOrByNum(){
    	
    	session.put("areforderlist", tsiml.selectOrderByNum(orderNumber));
		return "ok";
		
	}    
    public String showRefundDetai(){			
		session.put("orederDetail",tsiml.OrderDetail(orderId));
		return "ok";
	}
    
    public String refund(){
    	usrimlIml.refund(orderId, payBank, getDate.getCurrentDate());
    	session.put("areforderlist", usrimlIml.selectOrderLikeStatus());
    	return "ok";
    }
  
    
    
    
	public String getPayBank() {
		return payBank;
	}
	public void setPayBank(String payBank) {
		this.payBank = payBank;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}


	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}

}
