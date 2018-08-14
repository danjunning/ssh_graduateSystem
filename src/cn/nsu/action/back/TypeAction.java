package cn.nsu.action.back;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.nsu.entity.Type;
import cn.nsu.serviceIml.CategoryManageIml;

@Controller("typeAction")
public class TypeAction implements SessionAware{
	
	@Resource(name="cgiml")
	CategoryManageIml cgiml;
	
	Map<String, Object> session;
	private String typeName;
	private int typeId;

   public String getAllType(){
	   session.put("fisrttypelist", cgiml.firstTypeList());
	   return "ok";
   }
	public String addType(){
		Type type=new Type();
		type.setTypeName(typeName);
		if(1==cgiml.addFirstType(type)){
			session.put("fisrttypelist", cgiml.firstTypeList());
			return "ok";
		}else{
			return "fail";
		}	
	}
	
	public String deleteType(){	
		System.out.println("---------删除"+typeId);
		if(1==cgiml.deleteFirstTypeById(typeId)){
			System.out.println("---------已经删除");
			session.put("fisrttypelist", cgiml.firstTypeList());
			return "ok";
		}else{
			return "fail";
		}	
	}
	
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}

}
