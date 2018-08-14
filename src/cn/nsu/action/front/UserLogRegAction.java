package cn.nsu.action.front;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.nsu.entity.User;
import cn.nsu.serviceIml.UserManagmentIml;

@Controller("userLogRegAction")
public class UserLogRegAction implements SessionAware,ServletRequestAware{
	@Resource(name="usermagiml")
	UserManagmentIml umiml;
	
	private String uname;
	private String password;
	private String rpassword;
	public String register(){
		if(umiml.IsExist(uname)){
			request.setAttribute("reg_messagetip", "该用户名已经被注册，请重新输入！");
		}else{
			if(true==umiml.register(uname, password)){
				request.setAttribute("messagetip", "注册成功！");
			}else{
				request.setAttribute("messagetip", "注册失败！");
			}
		}
		return "ok";
	}
	
	public String login(){
		if(!umiml.IsExist(uname)){
			request.setAttribute("log_msg_name", "登录名不对，请重新输入！");
			return "fail";
		}else if(1==umiml.login(uname, password)){	
			User user=umiml.selectUser(uname);
			session.put("userId", user.getUserId());
			session.put("user", user);
			session.put("addresslist", umiml.addressList(user.getUserId()));
			session.put("creditList", umiml.creditList(user.getUserId()));
			return "ok";
		}else{
			request.setAttribute("log_msg_psw", "登录密码不对！");
			return "fail";
		}
		
	}
	
	public String logout(){
		session.remove("user");
		session.remove("addresslist");
		session.remove("creditList");
		return "ok";
	}
	
	
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRpassword() {
		return rpassword;
	}
	public void setRpassword(String rpassword) {
		this.rpassword = rpassword;
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
