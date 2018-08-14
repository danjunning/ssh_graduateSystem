package cn.nsu.action.front;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.nsu.entity.Product;
import cn.nsu.serviceIml.CategoryManageIml;
import cn.nsu.serviceIml.ProductMangeIml;

@Controller("IndexAction")
public class IndexAction implements SessionAware,ServletRequestAware{
	@Resource(name="pmiml")
	ProductMangeIml pmiml;
	@Resource(name="cgiml")
	CategoryManageIml cmiml;
	
	private String param_key;
	private String flag;
	private String param;
	private int subtypeId;
	public String searchProBySubtyId(){
		
		request.setAttribute("productlist", pmiml.selectProListBySubId(subtypeId,1));
		session.put("top8list", pmiml.getTopeightBysub(subtypeId));
		request.setAttribute("param", subtypeId);
		request.setAttribute("flag", "subtypeId");
		session.put("proCount", pmiml.countBySubId(subtypeId));
		request.setAttribute("method", "me_default");
		return "ok";
	}
	public String searchPro(){
		if(param_key.equals("")){
			request.setAttribute("productlist", null);
			session.put("top8list", null);
			request.setAttribute("param", "param");
			request.setAttribute("flag", "param");
			session.put("proCount", pmiml.countByParam(param_key));
			request.setAttribute("method", "me_default");
			request.setAttribute("param_key", param_key);
		}else{
			request.setAttribute("productlist", pmiml.selectProListByParam(param_key, 1));
			session.put("top8list", pmiml.getTopeightByParam(param_key));
			request.setAttribute("param", "param");
			request.setAttribute("flag", "param");
			session.put("proCount", pmiml.countByParam(param_key));
			request.setAttribute("method", "me_default");
			request.setAttribute("param_key", param_key);
		}
		
		return "ok";
	}
	
	public String saleSort(){
		if(flag.equals("typeId")){
			request.setAttribute("productlist", pmiml.sortProListBySale(Integer.valueOf(param),1));		
		}
		if(flag.equals("subtypeId")){
			request.setAttribute("productlist", pmiml.sortProListBySaleAndSub(Integer.valueOf(param),1));
		}
		if(flag.equals("param")){
			request.setAttribute("productlist", pmiml.sortProListBySaleAndPar(param_key, 1));
		}
		request.setAttribute("method", "sale");
		return "ok";
	}
	public String upPriceSort(){
		if(flag.equals("typeId")){
			request.setAttribute("productlist",pmiml.sortProListByupPrice(Integer.valueOf(param),1));	
		}
		if(flag.equals("subtypeId")){
			request.setAttribute("productlist",pmiml.sortProListByPriceUpAndSub(Integer.valueOf(param),1));
		}
		if(flag.equals("param")){
			request.setAttribute("productlist",pmiml.sortProListByPriceUpAndPar(param_key, 1));
		}
		request.setAttribute("method", "upprice");
		return "ok";
		}
	public String onPriceSort(){
		if(flag.equals("typeId")){
			request.setAttribute("productlist",pmiml.sortProListByonPrice(Integer.valueOf(param),1));
		}
		if(flag.equals("subtypeId")){
			request.setAttribute("productlist",pmiml.sortProListByPriceOnAndSub(Integer.valueOf(param),1));
		}
		if(flag.equals("param")){
			request.setAttribute("productlist",pmiml.sortProListByPriceOnAndPar(param_key, 1));
		}
		request.setAttribute("method", "onprice");
		return "ok";
	}
	
	
	
	HttpServletRequest request;
	Map<String, Object> session;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;	
	}
	public int getSubtypeId() {
		return subtypeId;
	}
	public void setSubtypeId(int subtypeId) {
		this.subtypeId = subtypeId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getParam_key() {
		return param_key;
	}

	public void setParam_key(String param_key) {
		this.param_key = param_key;
	}
	

}
