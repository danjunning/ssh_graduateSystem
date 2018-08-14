package cn.nsu.action.front;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.nsu.entity.Evaluate;
import cn.nsu.serviceIml.EvaluateManageIml;
import cn.nsu.serviceIml.ProductMangeIml;
@Controller("productAction")
public class ProductAction implements SessionAware{
	@Resource(name="pmiml")
	 ProductMangeIml pmiml;
	@Resource(name="evaiml")
	EvaluateManageIml emiml;
	private int productId;
	public String showDetail(){
		session.put("product", pmiml.selectById(productId));
		session.put("imglist", pmiml.selectImgByPId(productId));
		session.put("colorlist", pmiml.selectColorByPId(productId));
		session.put("stylelist", pmiml.selectStyleByPId(productId));
		session.put("evaprolist", emiml.showEvaluateByProId(productId));
		return "ok";
	}
	
	
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}




	Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

}
