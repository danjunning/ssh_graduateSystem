package cn.nsu.action.front;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.nsu.entity.Address;
import cn.nsu.serviceIml.UserManagmentIml;
@Controller("AddressManageAction")
public class AddressManageAction implements SessionAware{
	@Resource(name="usermagiml")
	UserManagmentIml umiml;
	
	private int addressId;
	private String province;
	private String address;
	private String zipcode;
	private String receiver;
	private String receiveTel;
	
	public String addAddress(){	
		int userId=(int)session.get("userId");
		Address a=new Address();
		a.setAddress(address);
		a.setProvince(province);
		a.setZipcode(zipcode);
		a.setReceiver(receiver);
		a.setReceiveTel(receiveTel);
		a.setUserId(userId);
		umiml.addAddress(userId, a);
		session.put("addresslist", umiml.addressList(userId));
		return "ok";
	}
	public String lessAddress(){
		int userId=(int)session.get("userId");
		umiml.deleteAddress(addressId);
		session.put("addresslist", umiml.addressList(userId));
		return "ok";
	}

	Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;	
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getReceiveTel() {
		return receiveTel;
	}
	public void setReceiveTel(String receiveTel) {
		this.receiveTel = receiveTel;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
	
}
