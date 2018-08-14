package cn.nsu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

/**
 * Type entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "type", catalog = "managersystem")
@Repository("type")
public class Type implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private String typeName;
	private String typeRemark;

	// Constructors

	/** default constructor */
	public Type() {
	}

	/** minimal constructor */
	public Type(String typeName) {
		this.typeName = typeName;
	}

	/** full constructor */
	public Type(String typeName, String typeRemark) {
		this.typeName = typeName;
		this.typeRemark = typeRemark;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "typeId", unique = true, nullable = false)

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Column(name = "typeName", nullable = false, length = 50)

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "typeRemark")

	public String getTypeRemark() {
		return this.typeRemark;
	}

	public void setTypeRemark(String typeRemark) {
		this.typeRemark = typeRemark;
	}

}