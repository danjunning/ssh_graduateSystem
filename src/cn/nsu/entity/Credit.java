package cn.nsu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

/**
 * Credit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "credit", catalog = "managersystem")
@Repository("credit")
public class Credit implements java.io.Serializable {

	// Fields

	private Integer creditId;
	private String creditNumber;
	private String creditType;
	private String payPassword;
	private Float balance;
	private Integer userId;

	// Constructors

	/** default constructor */
	public Credit() {
	}

	/** minimal constructor */
	public Credit(String creditNumber, String creditType, String payPassword, Integer userId) {
		this.creditNumber = creditNumber;
		this.creditType = creditType;
		this.payPassword = payPassword;
		this.userId = userId;
	}

	/** full constructor */
	public Credit(String creditNumber, String creditType, String payPassword, Float balance, Integer userId) {
		this.creditNumber = creditNumber;
		this.creditType = creditType;
		this.payPassword = payPassword;
		this.balance = balance;
		this.userId = userId;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "creditId", unique = true, nullable = false)

	public Integer getCreditId() {
		return this.creditId;
	}

	public void setCreditId(Integer creditId) {
		this.creditId = creditId;
	}

	@Column(name = "creditNumber", nullable = false)

	public String getCreditNumber() {
		return this.creditNumber;
	}

	public void setCreditNumber(String creditNumber) {
		this.creditNumber = creditNumber;
	}

	@Column(name = "creditType", nullable = false, length = 50)

	public String getCreditType() {
		return this.creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	@Column(name = "payPassword", nullable = false, length = 50)

	public String getPayPassword() {
		return this.payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	@Column(name = "balance", precision = 12, scale = 0)

	public Float getBalance() {
		return this.balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	@Column(name = "user_id", nullable = false)

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}