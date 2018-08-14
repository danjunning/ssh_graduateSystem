package cn.nsu.action.back;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.nsu.serviceIml.EvaluateManageIml;
@Controller("EvaluateManage")
public class EvaluateManageAction implements SessionAware{
	@Resource(name="evaiml")
	EvaluateManageIml emiml;
	
	
	
	public String getAllEva(){
		session.put("evaluatelist", emiml.showAllEvaluate());
		return "ok";
	}
	
	
    Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}

}
