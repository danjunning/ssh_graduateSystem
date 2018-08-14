package cn.nsu.action.front;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.nsu.entity.Evaluate;
import cn.nsu.serviceIml.EvaluateManageIml;
import cn.nsu.tools.GetCurrentDate;

@Controller("EvaluateAction")
public class EvaluateAction implements SessionAware,ServletRequestAware{
	@Resource(name="getTime")
	GetCurrentDate gcd;
	@Resource(name="evaiml")
	EvaluateManageIml emiml;
	
	private int orderId;
	private int productId;
	private String content;
	private int pageIndex;
	public String addEvaluate(){
		int userId=(int)session.get("userId");
		Evaluate evaluate=new Evaluate();
		evaluate.setContent(content);
		evaluate.setEvaluateDate(gcd.getCurrentDate());
		evaluate.setOrderId(orderId);
		evaluate.setProductId(productId);
		evaluate.setUserId(userId);
		emiml.addEvaluate(userId, evaluate);
		session.put("evaluatelist", emiml.showEvaluatelistByUser(userId,1));
		session.put("evaprolist", emiml.showEvaluateByProId(productId));
		request.setAttribute("evaluate_go", "yes");
		return "ok";
	}
	
	private int evaluateId;
	public String deleteEvaluate(){
		emiml.deleteEvaluate(evaluateId);
		return "ok";
	}
	
	public String pageEvaluate(){
		int userId=(int)session.get("userId");
		session.put("evaluatelist", emiml.showEvaluatelistByUser(userId,pageIndex));//全部评价
		request.setAttribute("evaluate_go", "yes");
		return "ok";
	}
	public String getEvaluate(){
		int userId=(int)session.get("userId");
		request.setAttribute("commentContent", emiml.selectByUidAndOId(userId, orderId).getContent());
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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getEvaluateId() {
		return evaluateId;
	}

	public void setEvaluateId(int evaluateId) {
		this.evaluateId = evaluateId;
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
