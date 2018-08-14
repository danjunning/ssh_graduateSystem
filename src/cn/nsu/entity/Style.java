package cn.nsu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

/**
 * Style entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "style", catalog = "managersystem")
@Repository("style")
public class Style implements java.io.Serializable {

	// Fields

	private Integer styleId;
	private String style;
	private Integer productId;

	// Constructors

	/** default constructor */
	public Style() {
	}

	/** full constructor */
	public Style(String style, Integer productId) {
		this.style = style;
		this.productId = productId;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "styleId", unique = true, nullable = false)

	public Integer getStyleId() {
		return this.styleId;
	}

	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}

	@Column(name = "style", length = 50)

	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Column(name = "product_id")

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

}