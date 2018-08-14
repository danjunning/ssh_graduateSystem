package cn.nsu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

/**
 * Orderitem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "orderitem", catalog = "managersystem")
@Repository("orderitem")
public class Orderitem implements java.io.Serializable {

	// Fields

	private Integer itemorderId;
	private String productName;
	private String productImage;
	private String productColor;
	private String productStyle;
	private Integer productQuantity;
	private Double totalPrice;
	private String address;
	private String orderId;

	// Constructors

	/** default constructor */
	public Orderitem() {
	}

	/** full constructor */
	public Orderitem(String productName, String productImage, String productColor, String productStyle,
			Integer productQuantity, Double totalPrice, String address, String orderId) {
		this.productName = productName;
		this.productImage = productImage;
		this.productColor = productColor;
		this.productStyle = productStyle;
		this.productQuantity = productQuantity;
		this.totalPrice = totalPrice;
		this.address = address;
		this.orderId = orderId;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "itemorderId", unique = true, nullable = false)

	public Integer getItemorderId() {
		return this.itemorderId;
	}

	public void setItemorderId(Integer itemorderId) {
		this.itemorderId = itemorderId;
	}

	@Column(name = "productName", nullable = false)

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "productImage", nullable = false)

	public String getProductImage() {
		return this.productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	@Column(name = "productColor", nullable = false, length = 10)

	public String getProductColor() {
		return this.productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	@Column(name = "productStyle", nullable = false, length = 10)

	public String getProductStyle() {
		return this.productStyle;
	}

	public void setProductStyle(String productStyle) {
		this.productStyle = productStyle;
	}

	@Column(name = "productQuantity", nullable = false)

	public Integer getProductQuantity() {
		return this.productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	@Column(name = "totalPrice", nullable = false, precision = 10, scale = 1)

	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "address", nullable = false)

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "order_id", nullable = false)

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}