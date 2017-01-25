package com.yshanginfo.framwork.sys.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.QueryHints;

import com.yshanginfo.framwork.core.repository.BaseJapRepository;
import com.yshanginfo.framwork.sys.entity.Permission;

public interface PermissionRepository extends BaseJapRepository<Permission, String>{

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") }) 
	public List<Permission> findAll(Iterable<String> ids);
	
}
