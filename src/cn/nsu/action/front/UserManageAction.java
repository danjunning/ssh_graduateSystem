package cn.nsu.action.front;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.nsu.entity.User;
import cn.nsu.serviceIml.EvaluateManageIml;
import cn.nsu.serviceIml.OrderPlaceIml;
import cn.nsu.serviceIml.UserManagmentIml;
import cn.nsu.serviceIml.UserSellerRefundServiceIml;

@Controller("userManageAction")
public class UserManageAction implements SessionAware,ServletRequestAware{
	@Resource(name="usermagiml")
	UserManagmentIml umiml;
	@Resource(name="opiml")
	OrderPlaceIml opiml;
	@Resource(name="usrefuiml")
	UserSellerRefundServiceIml usrsIml;
	@Resource(name="evaiml")
	EvaluateManageIml emiml;

	private String uname;
	private String sex;
	private String birthday;
	private String email;
	private String mobile;
	private String address;
	private int userId;
	
	public String manageList(){
		int userId=(int)session.get("userId");
		session.put("allorderlistByuser", opiml.UserallOrder(userId,1));
		session.put("unpayorderlistByuer", opiml.showUserUnpayOrder(userId,1));//待支付
		session.put("waitsendorderlistByuer", opiml.UserwaitSendOrder(userId,1));//待发货
		session.put("waitreceiveorderlistByuer", opiml.showUserWaitReceiveOrder(userId,1));//待收货
		session.put("receivedorderlistByuser", opiml.showSucessReceiveOrder(userId,1));//已收货没有评价的
		session.put("refundlist", usrsIml.showAllRefund(userId,1));//退款订单
		session.put("evaluatelist", emiml.showEvaluatelistByUser(userId,1));//全部评价
		//统计总数
		session.put("allorderlistByuserSize", opiml.countUserAllOrder(userId));
		session.put("unpayorderlistByuerSize", opiml.CountUserUnpayOrder(userId));//待支付
		session.put("waitsendorderlistByuerSize", opiml.CountUserwaitSendOrder(userId));//待发货
		session.put("waitreceiveorderlistByuerSize", opiml.countUserWaitReceiveOrder(userId));//待收货
		session.put("receivedorderlistByuserSize", opiml.CountSucessReceiveOrder(userId));//已收货没有评价的
		session.put("refundlistSize", usrsIml.CountAllRefund(userId));//退款订单
		session.put("evaluatelistSize", emiml.CountEvaluatelistByUser(userId));//全部评价
		return "ok";	
	}
	public String OrderList(){
		int userId=(int)session.get("userId");
		session.put("allorderlistByuser", opiml.UserallOrder(userId,1));
		session.put("unpayorderlistByuer", opiml.showUserUnpayOrder(userId,1));//待支付
		session.put("waitsendorderlistByuer", opiml.UserwaitSendOrder(userId,1));//待发货
		session.put("waitreceiveorderlistByuer", opiml.showUserWaitReceiveOrder(userId,1));//待收货
		session.put("receivedorderlistByuser", opiml.showSucessReceiveOrder(userId,1));//已收货没有评价的
		session.put("refundlist", usrsIml.showAllRefund(userId,1));//退款订单
		session.put("evaluatelist", emiml.showEvaluatelistByUser(userId,1));//全部评价
		request.setAttribute("order1_go", "yes");
		
		//统计总数
		session.put("allorderlistByuserSize", opiml.countUserAllOrder(userId));
		session.put("unpayorderlistByuerSize", opiml.CountUserUnpayOrder(userId));//待支付
		session.put("waitsendorderlistByuerSize", opiml.CountUserwaitSendOrder(userId));//待发货
		session.put("waitreceiveorderlistByuerSize", opiml.countUserWaitReceiveOrder(userId));//待收货
		session.put("receivedorderlistByuserSize", opiml.CountSucessReceiveOrder(userId));//已收货没有评价的
		session.put("refundlistSize", usrsIml.CountAllRefund(userId));//退款订单
		session.put("evaluatelistSize", emiml.CountEvaluatelistByUser(userId));//全部评价
		return "ok";
	}
	
	public String saveUserinfo(){
		int userId=(int)session.get("userId");
		setUserId(userId);
		User user=new User();
		user.setBirthday(birthday);
		user.setAddress(address);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setUname(uname);
		user.setSex(sex);
		umiml.userinfo(userId, user);
		session.put("user", umiml.selectUser(uname));
		return "ok";
	}
	
	private File file;
	private String savePath;
    private String fileFileName;
    private String fileContentType;
    public String upload() throws IOException {
    	//ArrayList<String> imagePath=new ArrayList<String>();
		InputStream is = new FileInputStream(file);
	    //File destFile = new File(path+imgpath, this.getFileFileName().get(i));
		File destFile = new File(getSavePath(), this.getFileFileName());
	    OutputStream os = new FileOutputStream(destFile);
		byte[] buffer = new byte[400];
		int length = 0;
		try {
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		is.close();
		os.close();
		int userId=(int)session.get("userId");
		String img="upload/"+this.getFileFileName();
		System.out.println("照片是："+img);
		umiml.uploadPhoto(userId,img);
		return "ok";
	}
    private String oldpassword;
    private String newpassword;
    public String updatePsd(){
    	int userId=(int)session.get("userId");
    	if(1==umiml.updatePsw(userId, oldpassword, newpassword)){
    		return "ok";
    	}
    	else{
    		session.put("updatefailmsg", "fail");
    		return "fail";
    	}
    }
	
	Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getSavePath() {
		String str=ServletActionContext.getServletContext().getRealPath(savePath);
		return str;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	
	

}
