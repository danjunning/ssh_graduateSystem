package cn.nsu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

/**
 * Evaluate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "evaluate", catalog = "managersystem")
@Repository("evaluate")
public class Evaluate implements java.io.Serializable {

	// Fields

	private Integer evaluateId;
	private String content;
	private String evaluateDate;
	private Integer userId;
	private Integer productId;
	private Integer orderId;

	// Constructors

	

	/** default constructor */
	public Evaluate() {
	}

	/** full constructor */
	public Evaluate(String content, String evaluateDate, Integer userId, Integer productId,Integer orderId) {
		this.content = content;
		this.evaluateDate = evaluateDate;
		this.userId = userId;
		this.productId = productId;
		this.orderId=orderId;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "evaluateId", unique = true, nullable = false)

	public Integer getEvaluateId() {
		return this.evaluateId;
	}

	public void setEvaluateId(Integer evaluateId) {
		this.evaluateId = evaluateId;
	}

	@Column(name = "content")

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "evaluateDate")

	public String getEvaluateDate() {
		return this.evaluateDate;
	}

	public void setEvaluateDate(String evaluateDate) {
		this.evaluateDate = evaluateDate;
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
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	@Column(name = "order_id")
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}