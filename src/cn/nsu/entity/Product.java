package cn.nsu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "product", catalog = "managersystem")
@DynamicInsert(true)
@Repository("product")
public class Product implements java.io.Serializable {

	// Fields

	private Integer productId;
	private String productName;
	private String productDetail;
	private Integer productStock;
	private Integer saleCount;
	private Integer commentCount;
	private Double productPrice;
	private String productRemark;
	private String productDate;
	private Integer subtypeId;
	private Integer typeId;

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** minimal constructor */
	public Product(String productName, Integer productStock, Integer saleCount, Integer commentCount,
			Double productPrice, String productDate, Integer subtypeId, Integer typeId) {
		this.productName = productName;
		this.productStock = productStock;
		this.saleCount = saleCount;
		this.commentCount = commentCount;
		this.productPrice = productPrice;
		this.productDate = productDate;
		this.subtypeId = subtypeId;
		this.typeId = typeId;
	}

	/** full constructor */
	public Product(String productName, String productDetail, Integer productStock, Integer saleCount,
			Integer commentCount, Double productPrice, String productRemark, String productDate, Integer subtypeId,
			Integer typeId) {
		this.productName = productName;
		this.productDetail = productDetail;
		this.productStock = productStock;
		this.saleCount = saleCount;
		this.commentCount = commentCount;
		this.productPrice = productPrice;
		this.productRemark = productRemark;
		this.productDate = productDate;
		this.subtypeId = subtypeId;
		this.typeId = typeId;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "productId", unique = true, nullable = false)

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Column(name = "productName", nullable = false)

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "productDetail", length = 800)

	public String getProductDetail() {
		return this.productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	@Column(name = "productStock", nullable = false)

	public Integer getProductStock() {
		return this.productStock;
	}

	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

	@Column(name = "saleCount", nullable = false,columnDefinition = "int default 0")

	public Integer getSaleCount() {
		return this.saleCount;
	}

	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}

	@Column(name = "commentCount", nullable = false,columnDefinition = "int default 0")

	public Integer getCommentCount() {
		return this.commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	@Column(name = "productPrice", nullable = false, precision = 10, scale = 1)

	public Double getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	@Column(name = "productRemark")

	public String getProductRemark() {
		return this.productRemark;
	}

	public void setProductRemark(String productRemark) {
		this.productRemark = productRemark;
	}

	@Column(name = "productDate", nullable = false)

	public String getProductDate() {
		return this.productDate;
	}

	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}

	@Column(name = "subtype_id", nullable = false)

	public Integer getSubtypeId() {
		return this.subtypeId;
	}

	public void setSubtypeId(Integer subtypeId) {
		this.subtypeId = subtypeId;
	}

	@Column(name = "type_id", nullable = false)

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

}