package cn.nsu.action.back;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.nsu.entity.Subtype;
import cn.nsu.serviceIml.CategoryManageIml;
@Controller("subtypeAction")
public class SubTypeAction implements SessionAware{
	@Resource(name="cgiml")
	CategoryManageIml cgiml;
	
	private Integer subtypeId;
	private Integer typeId;
	private String subtypeName;
	private String subtypeRemark;
	
	public String getAllSubType(){
		session.put("secondtypelist", cgiml.secondTypeList());
		return "ok";
	}
	public String searchSubType(){
		System.out.println("查找这个一级分类向的二级："+typeId);
		session.put("secondtypelist", cgiml.findSecondTypeList(typeId));
		return "ok";
	}
	public String deleteSubType(){
		if(1==cgiml.deleteSecondtypeById(subtypeId)){			
			session.put("secondtypelist", cgiml.secondTypeList());
			return "ok";
		}else{
			return "fail";
		}	
	}
	public String addSubType(){
		Subtype subtype=new Subtype();
		subtype.setTypeId(typeId);
		subtype.setSubtypeName(subtypeName);
		subtype.setSubtypeRemark(subtypeRemark);
		if(1==cgiml.addSecondtype(subtype)){
			session.put("secondtypelist", cgiml.secondTypeList());
			return "ok";
		}else{
			return "fail";
		}
			
	}
	
		
	
	public String getSubtypeRemark() {
		return subtypeRemark;
	}
	public void setSubtypeRemark(String subtypeRemark) {
		this.subtypeRemark = subtypeRemark;
	}
	public String getSubtypeName() {
		return subtypeName;
	}
	public void setSubtypeName(String subtypeName) {
		this.subtypeName = subtypeName;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getSubtypeId() {
		return subtypeId;
	}
	public void setSubtypeId(Integer subtypeId) {
		this.subtypeId = subtypeId;
	}
	
	Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}

}
