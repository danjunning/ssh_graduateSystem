package cn.nsu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

/**
 * Address entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "address", catalog = "managersystem")
@Repository("address")
public class Address implements java.io.Serializable {

	// Fields

	private Integer addressId;
	private String province;
	private String address;
	private String zipcode;
	private String receiver;
	private String receiveTel;
	private Integer userId;

	// Constructors

	/** default constructor */
	public Address() {
	}

	/** minimal constructor */
	public Address(String province, String address, String receiver, String receiveTel) {
		this.province = province;
		this.address = address;
		this.receiver = receiver;
		this.receiveTel = receiveTel;
	}

	/** full constructor */
	public Address(String province, String address, String zipcode, String receiver, String receiveTel,
			Integer userId) {
		this.province = province;
		this.address = address;
		this.zipcode = zipcode;
		this.receiver = receiver;
		this.receiveTel = receiveTel;
		this.userId = userId;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "addressId", unique = true, nullable = false)

	public Integer getAddressId() {
		return this.addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	@Column(name = "province", nullable = false)

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "address", nullable = false)

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "zipcode")

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "receiver", nullable = false)

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Column(name = "receiveTel", nullable = false)

	public String getReceiveTel() {
		return this.receiveTel;
	}

	public void setReceiveTel(String receiveTel) {
		this.receiveTel = receiveTel;
	}

	@Column(name = "user_id")

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}