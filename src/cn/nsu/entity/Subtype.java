package cn.nsu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

/**
 * Subtype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "subtype", catalog = "managersystem")
@Repository("substyle")
public class Subtype implements java.io.Serializable {

	// Fields

	private Integer subtypeId;
	private String subtypeName;
	private String subtypeRemark;
	private Integer typeId;

	// Constructors

	/** default constructor */
	public Subtype() {
	}

	/** full constructor */
	public Subtype(String subtypeName, String subtypeRemark, Integer typeId) {
		this.subtypeName = subtypeName;
		this.subtypeRemark = subtypeRemark;
		this.typeId = typeId;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "subtypeId", unique = true, nullable = false)

	public Integer getSubtypeId() {
		return this.subtypeId;
	}

	public void setSubtypeId(Integer subtypeId) {
		this.subtypeId = subtypeId;
	}

	@Column(name = "subtypeName", length = 50)

	public String getSubtypeName() {
		return this.subtypeName;
	}

	public void setSubtypeName(String subtypeName) {
		this.subtypeName = subtypeName;
	}

	@Column(name = "subtypeRemark")

	public String getSubtypeRemark() {
		return this.subtypeRemark;
	}

	public void setSubtypeRemark(String subtypeRemark) {
		this.subtypeRemark = subtypeRemark;
	}

	@Column(name = "type_id")

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

}