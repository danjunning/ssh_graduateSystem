package cn.nsu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "managersystem")
@Repository("user")
public class User  {

	// Fields

	private Integer userId;
	private String uname;
	private String password;
	private String photo;
	private String sex;
	private String birthday;
	private String email;
	private String mobile;
	private String address;
	

	private Integer type;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String uname) {
		this.uname = uname;
	}

	/** full constructor */
	public User(String uname, String password, String photo, String sex, String birthday, String email, String mobile,
			Integer type) {
		this.uname = uname;
		this.password = password;
		this.photo = photo;
		this.sex = sex;
		this.birthday = birthday;
		this.email = email;
		this.mobile = mobile;
		this.type = type;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "userId", unique = true, nullable = false)

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "uname", nullable = false)

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Column(name = "password", length = 50)

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "photo")

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column(name = "sex", length = 10)

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "birthday")

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Column(name = "email")

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "mobile")

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "type")

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}