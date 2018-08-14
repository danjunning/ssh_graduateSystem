package cn.nsu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

/**
 * Orders entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "orders", catalog = "managersystem")
@Repository("orders")
public class Orders implements java.io.Serializable {

	// Fields

	private Integer orderId;
	private String orderNumber;
	private String payBank;
	private String orderDate;
	private String dealDate;
	private String orderStatus;
	private Double totalPrice;
	private Integer userId;
	private Integer productId;

	// Constructors

	/** default constructor */
	public Orders() {
	}

	/** full constructor */
	public Orders(String orderNumber, String payBank, String orderDate,String dealDate,String orderStatus, Double totalPrice,
			Integer userId) {
		this.orderNumber = orderNumber;
		this.payBank = payBank;
		this.orderDate = orderDate;
		this.dealDate = dealDate;
		this.orderStatus = orderStatus;
		this.totalPrice = totalPrice;
		this.userId = userId;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "orderId", unique = true, nullable = false)

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Column(name = "orderNumber")

	public String getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Column(name = "payBank")

	public String getPayBank() {
		return this.payBank;
	}

	public void setPayBank(String payBank) {
		this.payBank = payBank;
	}

	@Column(name = "orderDate")

	public String getOrderDate() {
		return this.orderDate;
	}
	@Column(name = "dealDate")
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	public String getDealDate() {
		return dealDate;
	}

	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}

	@Column(name = "orderStatus", length = 50)

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Column(name = "totalPrice", precision = 10)

	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "user_id")

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "product_id")
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

}