package cn.nsu.action.front;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.nsu.serviceIml.UserSellerRefundServiceIml;
@Controller("RefundAction")
public class RefundAction implements SessionAware,ServletRequestAware{
	@Resource(name="usrefuiml")
	UserSellerRefundServiceIml usrsIml;
	
	
	private int pageIndex;
	public String getAllRefund(){
		System.out.println("ajax删除无用订单");
		int userId=(int)session.get("userId");	
		session.put("refundlist", usrsIml.showAllRefund(userId,1));
		return "ok";
		
	}
	
	private int orderId;
	public String deleteOrder(){
		int userId=(int)session.get("userId");	
		usrsIml.deleteOrder(orderId);
		session.put("refundlist", usrsIml.showAllRefund(userId,1));
		return "ok";
	}
	public String pageRefund(){
		int userId=(int)session.get("userId");
		session.put("refundlist", usrsIml.showAllRefund(userId,pageIndex));//退款订单
		request.setAttribute("refund_go", "yes");
		return "ok";
	}

	
	Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	

}
