package cn.nsu.serviceIml;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nsu.dao.AdminDAO;
import cn.nsu.interfaces.AdminManage;
@Transactional
@Service("adminiml")
public class AdminManageIml implements AdminManage {
   @Resource(name="admindao")
   AdminDAO adao;
	@Override
	public int login(String adminname, String adminpassword) {
		System.out.println(adminname+""+adminpassword);	
		if(1==adao.IsExist(adminname, adminpassword)){
			
			return 1;
		}else{
			return 0;
		}
		
	}

}
