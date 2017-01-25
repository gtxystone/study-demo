package com.yshanginfo.framwork.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yshanginfo.framwork.core.repository.BaseJapRepository;
import com.yshanginfo.framwork.core.service.BaseService;
import com.yshanginfo.framwork.sys.entity.RoleAuth;
import com.yshanginfo.framwork.sys.repository.RoleAuthRepository;


@Component
@Transactional
public class RoleAuthService extends BaseService<RoleAuth, String>{
	
	@Autowired
	private RoleAuthRepository roleAuthRepository;

	@Override
	public BaseJapRepository<RoleAuth, String> getRepository() {
		return this.roleAuthRepository;
	}

}
