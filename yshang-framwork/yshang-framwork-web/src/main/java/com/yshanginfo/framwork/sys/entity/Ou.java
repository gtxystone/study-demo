/**
 * @author 
 * @version 1.0
 * @since  2015-11-16 14:22:32
 * @desc Ou
 */

package com.yshanginfo.framwork.sys.entity;

import javax.persistence.*;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import com.yshanginfo.framwork.core.entity.IdEntity;


@Entity
@Table(name = "sys_ou")
public class Ou extends IdEntity {
	
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=32)
	//private java.lang.String parentId;
	@Length(max=100)
	private java.lang.String name;
	@Length(max=100)
	private java.lang.String code;
	
	private java.util.Date createTime;
	@Length(max=20)
	private java.lang.String status;
	
	private Ou parent;
	//columns END


	public Ou(){
	}

	public Ou(
		java.lang.String id
	){
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public Ou getParent() {
		return parent;
	}

	public void setParent(Ou parent) {
		this.parent = parent;
	}

/*	@Column(name = "parent_id", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getParentId() {
		return this.parentId;
	}
	
	public void setParentId(java.lang.String value) {
		this.parentId = value;
	}*/
	
	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	@Column(name = "code", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public java.lang.String getCode() {
		return this.code;
	}
	
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_time", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	

	
}

