package cn.nsu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

/**
 * Image entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "image", catalog = "managersystem")
@Repository("image")
public class Image implements java.io.Serializable {

	// Fields

	private Integer imageId;
	private String image;
	private Integer productId;

	// Constructors

	/** default constructor */
	public Image() {
	}

	/** full constructor */
	public Image(String image, Integer productId) {
		this.image = image;
		this.productId = productId;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "imageId", unique = true, nullable = false)

	public Integer getImageId() {
		return this.imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	@Column(name = "image")

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "product_id")

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

}