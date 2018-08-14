package cn.nsu.action.front;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.nsu.entity.Credit;
import cn.nsu.serviceIml.UserManagmentIml;

@Controller("CreditManageAction")
public class CreditManageAction implements SessionAware,ServletRequestAware{
	@Resource(name="usermagiml")
	UserManagmentIml umiml;
	
	private String creditNumber;
	private String creditType;
	private String payPassword;
	private Float balance;
	private Integer userId;
	private int creditId;
	public String addCredit(){
		userId=(Integer) session.get("userId");
		Credit credit=new Credit();
		credit.setUserId(userId);
		credit.setBalance(balance);
		credit.setCreditNumber(creditNumber);
		credit.setCreditType(creditType);
		credit.setPayPassword(payPassword);
		if(1!=umiml.addCredit(userId, credit)){
			request.setAttribute("creditmsg", "该卡号已经存在！");
		}else{
			session.put("creditList", umiml.creditList(userId));
		}
		return "ok";
	}
	
	public String  deleteCredit(){
		userId=(Integer) session.get("userId");
		if(1==umiml.unbund(creditId)){
			session.put("creditList", umiml.creditList(userId));
		}
		return "ok";
	}
	public String save(){
		userId=(Integer) session.get("userId");
		if(1==umiml.save(balance, creditNumber)){
			session.put("creditList", umiml.creditList(userId));
		}else{
			System.out.println("---------------------该卡不存在");
			request.setAttribute("savemoney_msg", "该卡号不存在");
		}
		
		return "ok";
	}
	
	
	Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}
	public String getCreditNumber() {
		return creditNumber;
	}
	public void setCreditNumber(String creditNumber) {
		this.creditNumber = creditNumber;
	}
	public String getCreditType() {
		return creditType;
	}
	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public int getCreditId() {
		return creditId;
	}

	public void setCreditId(int creditId) {
		this.creditId = creditId;
	}
	HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	
	

}
