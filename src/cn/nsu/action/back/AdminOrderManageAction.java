package cn.nsu.action.back;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.nsu.serviceIml.TransactionServiceIml;
import cn.nsu.tools.GetCurrentDate;
@Controller("AdminOrderManage")
public class AdminOrderManageAction implements SessionAware{
	@Resource(name="getTime")
	GetCurrentDate gettime;
	@Resource(name="tranmaniml")
	TransactionServiceIml tsiml;
	
	private String orderNumber;
	private Integer orderId;
	public String getWsendOrList(){
		 session.put("awaitsendorderlist", tsiml.waitSendOrders());
		return "ok";
	}
	public String getReceicingOrList(){
		session.put("areceivingorderlist", tsiml.waitReceiveOrders());
		return "ok";
	}
	public String getReceivedOrList(){
		session.put("areceivedorderlist", tsiml.ReceivedOrder());
		return "ok";
	}
	
	public String getWsendOrByNum(){
		ArrayList<HashMap> awaitsendorderlist=tsiml.selectOrderByNumAndStatus(orderNumber, "待发货");
		session.put("awaitsendorderlist", awaitsendorderlist);
		return "ok";
	}	
	public String getReceivingOrByNum(){
		ArrayList<HashMap> areceivingorderlist=tsiml.selectOrderByNumAndStatus(orderNumber, "待收货");
		session.put("areceivingorderlist", areceivingorderlist);
		return "ok";
	}

	public String sendGood(){
		tsiml.sendGood(orderNumber, gettime.getCurrentDate());
		session.put("awaitsendorderlist", tsiml.waitSendOrders());
		return "ok";
	}	
	
	public String showOrderDetai(){	
		session.put("orederDetail",tsiml.OrderDetail(orderId));
		return "ok";
	}
	
	
	
	
	
	
	
    public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}







	Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}

}
