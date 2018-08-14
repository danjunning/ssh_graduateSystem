package cn.nsu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

/**
 * Advertise entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "advertise", catalog = "managersystem")
@Repository("advertise")
public class Advertise {

	// Fields

	private Integer adId;
	private Integer productId;
	private String productName;
	private Integer subtypeId;
	private String productDetail;
	private Integer saleCount;
	private Integer commentCount;
	private Double productPrice;
	private Integer productStock;
	private String productRemark;
	private String productDate;

	// Constructors

	/** default constructor */
	public Advertise() {
	}

	/** full constructor */
	public Advertise(String productName, Integer subtypeId, String productDetail, Integer saleCount,
			Integer commentCount, Double productPrice, Integer productStock, String productRemark) {
		this.productName = productName;
		this.subtypeId = subtypeId;
		this.productDetail = productDetail;
		this.saleCount = saleCount;
		this.commentCount = commentCount;
		this.productPrice = productPrice;
		this.productStock = productStock;
		this.productRemark = productRemark;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "adId", unique = true, nullable = false)

	public Integer getAdId() {
		return this.adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	@Column(name = "productName")

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "subtypeId")

	public Integer getSubtypeId() {
		return this.subtypeId;
	}

	public void setSubtypeId(Integer subtypeId) {
		this.subtypeId = subtypeId;
	}

	@Column(name = "productDetail")

	public String getProductDetail() {
		return this.productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	@Column(name = "saleCount")

	public Integer getSaleCount() {
		return this.saleCount;
	}

	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}

	@Column(name = "commentCount")

	public Integer getCommentCount() {
		return this.commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	@Column(name = "productPrice", precision = 10)

	public Double getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	@Column(name = "productStock")

	public Integer getProductStock() {
		return this.productStock;
	}

	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

	@Column(name = "product_remark")

	public String getProductRemark() {
		return this.productRemark;
	}

	public void setProductRemark(String productRemark) {
		this.productRemark = productRemark;
	}
	@Column(name = "productDate")
	public String getProductDate() {
		return productDate;
	}

	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
	@Column(name = "productId")
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	
	
	

}