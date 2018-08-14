package cn.nsu.action.back;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;
import cn.nsu.serviceIml.AdminManageIml;
import cn.nsu.serviceIml.CategoryManageIml;
import cn.nsu.serviceIml.ProductMangeIml;
import cn.nsu.serviceIml.TransactionServiceIml;
import cn.nsu.serviceIml.UserManagmentIml;

@Controller("adminlogin")
public class AdminLogin implements SessionAware,ServletRequestAware{
	@Resource(name="adminiml")
	private AdminManageIml  amiml;
	@Resource(name="usermagiml")
	private UserManagmentIml umiml;
	@Resource(name="pmiml")
	private ProductMangeIml pmiml;
	@Resource(name="tranmaniml")
	private TransactionServiceIml tsiml;
	@Resource(name="cgiml")
	private CategoryManageIml cmiml;
	
	
	private String adminName;
    private String adminPassword;
	
    public String adminlogin(){
    	System.out.println(adminName+adminPassword);
    	if(1==amiml.login(adminName, adminPassword)){	
    		session.put("admin_status", "true");
    		session.put("userlist", umiml.UserList());
    		session.put("productlist", pmiml.showAllProducts());
    		session.put("countorder", String.valueOf(tsiml.countOrders()));
    		session.put("countwaitpayorder", String.valueOf(tsiml.countWaitPayOrder()));
    		session.put("waitsendorderlist", tsiml.waitSendOrders());
    		session.put("waitreceiorderlist", tsiml.waitReceiveOrders());
    		session.put("receivedorderlist", tsiml.ReceivedOrder()); 		
    		return "ok";
    	}else{
    		request.setAttribute("adminlogin_result", "登录失败，用户名或密码不对！");
    		return "fail";
    	}	
    }
	
	
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}
	HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;		
	}

}
