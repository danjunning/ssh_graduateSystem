package cn.nsu.serviceIml;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.nsu.dao.AddressDAO;
import cn.nsu.dao.CreditDAO;
import cn.nsu.dao.UserDAO;
import cn.nsu.entity.Address;
import cn.nsu.entity.Credit;
import cn.nsu.entity.User;
import cn.nsu.interfaces.UserManager;
@Transactional
@Service("usermagiml")
public class UserManagmentIml implements UserManager {
	@Resource(name="userdao")
	UserDAO userdao;
	@Resource(name="addressdao")
	AddressDAO addao;
	@Resource(name="creditdao")
	CreditDAO cdao;
	
	
    /**
     * 参数：String uname:登录名， String password：登录密码
     * 判断传递过来的登录名和密码是否和数据库中注册的一致
     * */
	@Override
	public int login(String uname, String password) {
		if(userdao.UserEquals(uname, password)){
			System.out.println("登陆成功！");
			return 1;
		}else{
			System.out.println("登陆失败！");
			return -1;
		}		
	}
    
	/**
     * 注册
     * uname：用户名，password：密码
     * */
	@Override
	public boolean register(String uname, String password) {
		if(userdao.IsUser(uname)){
			System.out.println("该用户名已经被使用");
			return false;
		}else{
			if(1==userdao.insertUser(uname, password)){
				System.out.println("注册成功！");
				return true;
			}else{
				System.out.println("注册失败！");
				return false;
			}		
		}
	}
	
	public boolean IsExist(String uname){
		if(userdao.IsUser(uname))
			return true;
		else {
			return false;
		}
	}
	
	/**
     * 完善或者修改用户的基本信息
     * */
	@Override
	public int userinfo(int userId, User user) {
		if(1==userdao.updateUser(userId, user)){
			System.out.println("完善成功！");
			return 1;
		}else{
			System.out.println("完善失败！");
			return 0;
		}
		
	}
	/**
     * 上传头像，
     * photo：表示图片的保存路径
     * */
	@Override
	public int uploadPhoto(int userId, String photo) {
		if(1==userdao.uploadPhoto(userId, photo)){
			System.out.println("上传成功");
			return 1;
		}else{
			return 0;
		}
	}
	
	/**修改密码
     * userId:表示用户唯一主键，oldpassword:原密码，password：新密码
     * */
	@Override
	public int updatePsw(int userId, String oldpassword,String password) {
		User user=userdao.findById(userId);
		System.out.println(user.getPassword());
		if(user.getPassword().equals(oldpassword)){
			userdao.updatePsw(userId, password);
			System.out.println("修改成功");
			return 1;
		}else{
			System.out.println("原密码不对");
			return 0;
		}
		
	}
    /**
     * 添加地址，userId:用户标识，address：一条地址信息
     */
	@Override
	public int addAddress(int userId, Address address) {
		if(addao.count(userId)>=10){
			System.out.println("已经有10条地址了");
			return 0;
		}else{
			addao.save(address);
			System.out.println("添加一条地址");
			return 1;
		}
		
	}
	/**
	 * 更改收货地址信息
	 */
	@Override
	public int updateAddress(int addressId, Address address) {
		if(1==addao.updateAddress(addressId, address)){
			System.out.println("修改一条地址成功");
			return 1;
		}else{
			System.out.println("修改一条地址失败");
			return 0;
		}		
	}
	/**
	 * 删除收货地址
	 */
	@Override
	public int deleteAddress(int addressId) {
		if(1==addao.deleteById(addressId)){
			System.out.println("删除一条地址");
			return 1;
		}else{
			System.out.println("地址删除失败");
			return 0;
		}
	}
	
	/**
	 * 添加银行卡,userId:用户标识，
	 * 不能重复绑定
	 */
	@Override
	public int addCredit(int userId, Credit credit) {
		int exist=cdao.findByCreditNumber(credit.getCreditNumber()).size();
		if(exist>0){
			System.err.println("该卡已经绑定了");
			return 0;
		}else{
			cdao.save(credit);
			return 1;
		}
		
	}

	/**
	 * 向银行卡里面充钱，balance：充值金额，creditNumber:卡号
	 */
	@Override
	public int save(Float money, String creditNumber) {
		if(cdao.isExistByNum(creditNumber) != null){
			float balance=getbalance(creditNumber)+money;
			if(1==cdao.updateBalanceBycreNum(balance, creditNumber)){
				return 1;
			}else{
				return 0;
			}
		}else{
			return -1;
		}
			
	}
	
	/**
	 * 取出该卡中的余额
	 */
	@Override
	public float getbalance(String creditNumber) {
		Credit credit=cdao.isExistByNum(creditNumber);
		if(credit!=null){
			System.out.println(credit.getBalance());
			return credit.getBalance();
		}else{
			System.out.println("该卡不存在，查不到余额");
			return -1;
		}
		
	}
	/**
	 * 删除一张银行卡
	 */
	@Override
	public int unbund(String creditNumber) {
		Credit credit=cdao.isExistByNum(creditNumber);
		if(credit!=null){
			cdao.delete(credit);
			System.out.println("删除一张银行卡");
			return 1;
		}else{
			System.out.println("删除失败一张银行卡");
			return -1;
		}
	}
	public int unbund(int creditId) {
		Credit credit=cdao.findById(creditId);
		if(credit!=null){
			cdao.delete(credit);
			System.out.println("删除一张银行卡");
			return 1;
		}else{
			System.out.println("删除失败一张银行卡");
			return -1;
		}
	}
	
	
    /**
     * 使用银行卡，购买东西后，扣除卡中金额
     */
	@Override
	public int consume(Float price,String creditNumber) {
		float balance=getbalance(creditNumber)-price;
		if(1==cdao.updateBalanceBycreNum(balance, creditNumber)){
			return 1;
		}else{
			return 0;
		}

	}
	
	public List<User> UserList(){
		return userdao.findAll();	
	}	
	public User selectUser(String uname){
		List<User> uList=userdao.findByUname(uname);
		return uList.get(0);
	}
	public List<Address> addressList(int userId){
		return addao.findByUserId(userId);
	}
	public List<Credit> creditList(int userId){
		List<Credit> list=cdao.selectById(userId);
		if(list!=null&&list.size()>0){
			return list;
		}else{
			return null;
		}
		
	}
	

}
