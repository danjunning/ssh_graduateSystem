package cn.nsu.inteceptors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import cn.nsu.entity.User;

public class LoginInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation action) throws Exception {
		Map<String, Object> session=action.getInvocationContext().getSession();
		HttpServletRequest request = (HttpServletRequest) action.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		User user=(User)session.get("user");
		if(user!=null){
			System.out.println("--------------------经过拦截器,已经登录了");
			return action.invoke();
		}else{
			System.out.println("--------------------经过拦截器,没有登录");
			request.setAttribute("Islogin", "请先登录!");
			return "failure";
		}
	}
	

}
