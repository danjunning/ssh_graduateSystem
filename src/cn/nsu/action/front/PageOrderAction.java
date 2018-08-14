package cn.nsu.action.front;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.nsu.serviceIml.OrderPlaceIml;
import cn.nsu.serviceIml.UserManagmentIml;
import cn.nsu.serviceIml.UserSellerRefundServiceIml;
@Controller("PageOrderAction")
public class PageOrderAction implements ServletRequestAware,SessionAware{
	@Resource(name="usermagiml")
	UserManagmentIml umiml;
	@Resource(name="opiml")
	OrderPlaceIml opiml;
	@Resource(name="usrefuiml")
	UserSellerRefundServiceIml usrsIml;
	
	private int pageIndex;
	public String  pageAllorder(){
		int userId=(int)session.get("userId");
		session.put("allorderlistByuser", opiml.UserallOrder(userId,pageIndex));
		request.setAttribute("order1_go", "yes");
		return "ok";
	}
	public String  pageUnPayorder(){
		int userId=(int)session.get("userId");
		session.put("unpayorderlistByuer", opiml.showUserUnpayOrder(userId,pageIndex));
		request.setAttribute("order2_go", "yes");
		return "ok";
	}
	public String  pageWaitSendorder(){
		int userId=(int)session.get("userId");
		session.put("waitsendorderlistByuer", opiml.UserwaitSendOrder(userId,pageIndex));
		request.setAttribute("order3_go", "yes");
		return "ok";
	}
	public String  pageWaitReceiveorder(){
		int userId=(int)session.get("userId");
		session.put("waitsendorderlistByuer", opiml.showUserWaitReceiveOrder(userId,pageIndex));
		request.setAttribute("order4_go", "yes");
		return "ok";
	}
	public String  pageReceivedorder(){
		int userId=(int)session.get("userId");
		session.put("receivedorderlistByuser", opiml.showSucessReceiveOrder(userId,pageIndex));
		request.setAttribute("order5_go", "yes");
		return "ok";
	}
	
	HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;	
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	

}
