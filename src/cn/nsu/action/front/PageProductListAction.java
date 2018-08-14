package cn.nsu.action.front;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.nsu.serviceIml.CategoryManageIml;
import cn.nsu.serviceIml.ProductMangeIml;

@Controller("PageProductListAction")
public class PageProductListAction implements SessionAware,ServletRequestAware{
	@Resource(name="pmiml")
	ProductMangeIml pmiml;
	@Resource(name="cgiml")
	CategoryManageIml cmiml;
	
	private String param_key;
	private String flag;
	private String param;
	private int subtypeId;
	private int pageIndex;
	private String method;
	
	public String pagePro(){
		if(method.equals("me_default")){
			System.out.println("==============================me_default");
			if(flag.equals("typeId")){
				request.setAttribute("productlist", pmiml.selectProListByTyId(Integer.valueOf(param),pageIndex));		
			}
			if(flag.equals("subtypeId")){
				request.setAttribute("productlist", pmiml.selectProListBySubId(Integer.valueOf(param),pageIndex));
			}
			if(flag.equals("param")){
				request.setAttribute("productlist", pmiml.selectProListByParam(param_key, pageIndex));
			}		
		}else if(method.equals("sale")){
			if(flag.equals("typeId")){
				request.setAttribute("productlist", pmiml.sortProListBySale(Integer.valueOf(param),pageIndex));		
			}
			if(flag.equals("subtypeId")){
				request.setAttribute("productlist", pmiml.sortProListBySaleAndSub(Integer.valueOf(param),pageIndex));
			}
			if(flag.equals("param")){
				request.setAttribute("productlist", pmiml.sortProListBySaleAndPar(param_key, pageIndex));
			}
		}else if(method.equals("upprice")){
			if(flag.equals("typeId")){
				request.setAttribute("productlist",pmiml.sortProListByupPrice(Integer.valueOf(param),pageIndex));	
			}
			if(flag.equals("subtypeId")){
				request.setAttribute("productlist",pmiml.sortProListByPriceUpAndSub(Integer.valueOf(param),pageIndex));
			}
			if(flag.equals("param")){
				request.setAttribute("productlist",pmiml.sortProListByPriceUpAndPar(param_key, pageIndex));
			}
		}else{
			if(flag.equals("typeId")){
				request.setAttribute("productlist",pmiml.sortProListByonPrice(Integer.valueOf(param),pageIndex));
			}
			if(flag.equals("subtypeId")){
				request.setAttribute("productlist",pmiml.sortProListByPriceOnAndSub(Integer.valueOf(param),pageIndex));
			}
			if(flag.equals("param")){
				request.setAttribute("productlist",pmiml.sortProListByPriceOnAndPar(param_key, pageIndex));
			}
		}
		
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
	public int getSubtypeId() {
		return subtypeId;
	}
	public void setSubtypeId(int subtypeId) {
		this.subtypeId = subtypeId;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getParam_key() {
		return param_key;
	}
	public void setParam_key(String param_key) {
		this.param_key = param_key;
	}
	
	

}
