package cn.nsu.interfaces;

import cn.nsu.entity.Address;
import cn.nsu.entity.Credit;
import cn.nsu.entity.User;

public interface UserManager {
	//用户登录的逻辑方法
	public int login(String uname,String password);
	//用户注册的逻辑
	public boolean register(String uname,String password);
	//完善个人信息
	public int userinfo(int userId,User user);
	//上传头像
	public int uploadPhoto(int userId,String photo);
	//修改密码
	public int updatePsw(int userId,String oldpassword,String password);
	
	//添加收货地址
	public int addAddress(int userId,Address address);
	//修改收货地址
	public int updateAddress(int addressId,Address address);
	//删除收货地址
	public int deleteAddress(int addressId);
	
	//添加银行卡
	public int addCredit(int userId,Credit c);
	//充值存款
	public int save(Float money,String creditNumber);
	//查询余额
	public float getbalance(String creditNumber);
	//解除卡
	public int unbund(String creditNumber);
	//消费金额
	public int consume(Float price,String creditNumber);

}
