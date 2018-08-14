package cn.nsu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

/**
 * Shopcart entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shopcart", catalog = "managersystem")
@Repository("shopcart")
public class Shopcart implements java.io.Serializable {

	// Fields

	private Integer shopcartId;
	private String proColor;
	private String proStyle;
	private Integer proAmount;
	private Integer productId;
	private String cartDate;
	private Integer userId;

	// Constructors

	/** default constructor */
	public Shopcart() {
	}

	/** full constructor */
	public Shopcart(String proColor, String proStyle, Integer proAmount, Integer productId, String cartDate,Integer userId) {
		this.proColor = proColor;
		this.proStyle = proStyle;
		this.proAmount = proAmount;
		this.productId = productId;
		this.cartDate=cartDate;
		this.userId = userId;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "shopcartId", unique = true, nullable = false)

	public Integer getShopcartId() {
		return this.shopcartId;
	}

	public void setShopcartId(Integer shopcartId) {
		this.shopcartId = shopcartId;
	}

	@Column(name = "proColor", length = 50)

	public String getProColor() {
		return this.proColor;
	}

	public void setProColor(String proColor) {
		this.proColor = proColor;
	}

	@Column(name = "proStyle", length = 50)

	public String getProStyle() {
		return this.proStyle;
	}

	public void setProStyle(String proStyle) {
		this.proStyle = proStyle;
	}

	@Column(name = "proAmount")

	public Integer getProAmount() {
		return this.proAmount;
	}

	public void setProAmount(Integer proAmount) {
		this.proAmount = proAmount;
	}

	@Column(name = "product_id")

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Column(name = "user_id")

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "cartDate")
	public String getCartDate() {
		return cartDate;
	}

	public void setCartDate(String cartDate) {
		this.cartDate = cartDate;
	}

}