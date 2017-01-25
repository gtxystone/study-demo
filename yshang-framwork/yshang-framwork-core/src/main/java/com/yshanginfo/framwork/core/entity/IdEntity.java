/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.yshanginfo.framwork.core.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@JsonIgnoreProperties( value={"hibernateLazyInitializer","handler"})//序列化成json是不序列化这两个属性否则会报错
public abstract class IdEntity {

	protected String id;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid" , strategy = "uuid")
	@Column(name="id",length=32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
