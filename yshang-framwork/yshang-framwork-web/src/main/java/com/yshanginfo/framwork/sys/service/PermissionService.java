package com.yshanginfo.framwork.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yshanginfo.framwork.core.repository.BaseJapRepository;
import com.yshanginfo.framwork.core.service.BaseService;
import com.yshanginfo.framwork.sys.entity.Permission;
import com.yshanginfo.framwork.sys.repository.PermissionRepository;


@Component
@Transactional
public class PermissionService extends BaseService<Permission, String>{
	
	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public BaseJapRepository<Permission, String> getRepository() {
		return this.permissionRepository;
	}

}
