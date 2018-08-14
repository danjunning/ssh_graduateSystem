package cn.nsu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

/**
 * Color entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "color", catalog = "managersystem")
@Repository("color")
public class Color implements java.io.Serializable {

	// Fields

	private Integer colorId;
	private String color;
	private Integer productId;

	// Constructors

	/** default constructor */
	public Color() {
	}

	/** full constructor */
	public Color(String color, Integer productId) {
		this.color = color;
		this.productId = productId;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "colorId", unique = true, nullable = false)

	public Integer getColorId() {
		return this.colorId;
	}

	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}

	@Column(name = "color", length = 50)

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Column(name = "product_id")

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

}