package cn.nsu.action.front;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.nsu.entity.Advertise;
import cn.nsu.serviceIml.AdvertiseManageIml;
import cn.nsu.serviceIml.CategoryManageIml;
import cn.nsu.serviceIml.ProductMangeIml;
@Controller("homeAction")
public class HomeAction implements SessionAware,ServletRequestAware{
	@Resource(name="pmiml")
	ProductMangeIml pmiml;
	@Resource(name="cgiml")
	CategoryManageIml cmiml;
	@Resource(name="adveriml")
	AdvertiseManageIml adveriml;
	
	private Integer typeId;
	public String home(){
		//一级类别表
		session.put("Firtypelist", cmiml.AllFirstType());
		session.put("Subtypelist", cmiml.secondTypeList());
		//广告表
		session.put("front_advertiselist", adveriml.Adverlist());
		//手机
		session.put("phonelist", pmiml.Phonelist());
		//电脑
		session.put("computerlist", pmiml.Computerlist());
		//摄影摄像
		session.put("camerolist", pmiml.Camarolist());
		//穿戴智能设备
		session.put("wearlist", pmiml.Wearlist());
		
		return "ok";
	}
	
	public String getProByTyId(){	
		session.put("subtypelist", cmiml.findSecondTypeList(typeId));
		request.setAttribute("productlist", pmiml.selectProListByTyId(typeId,1));
		session.put("top8list", pmiml.getTopeightByty(typeId));
		request.setAttribute("param", typeId);
		request.setAttribute("flag", "typeId");
		request.setAttribute("method", "me_default");
		session.put("proCount", pmiml.countByTyId(typeId));
		return "ok";
	}
	
	
	
	
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	Map<String,Object> session;
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
