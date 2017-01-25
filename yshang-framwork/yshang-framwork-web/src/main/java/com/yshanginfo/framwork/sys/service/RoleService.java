package com.yshanginfo.framwork.sys.service;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yshanginfo.framwork.core.repository.BaseJapRepository;
import com.yshanginfo.framwork.core.service.BaseService;
import com.yshanginfo.framwork.sys.entity.Role;
import com.yshanginfo.framwork.sys.entity.RoleAuth;
import com.yshanginfo.framwork.sys.repository.RoleAuthRepository;
import com.yshanginfo.framwork.sys.repository.RoleRepository;


@Component
@Transactional
public class RoleService extends BaseService<Role, String>{
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleAuthRepository roleAuthRepository;
	
	/**
	 * 给角色授权
	 * @param role 从前台传进来的只有Role的ID和roleAuth，没有Role基本信息
	 * @return
	 */
	public boolean authRole(Role role){
		Role dbRole = this.roleRepository.findOne(role.getId());
		List<RoleAuth> ras = dbRole.getRoleAuths();
		this.roleAuthRepository.deleteInBatch(ras);
		dbRole.setRoleAuths(role.getRoleAuths());
		this.roleRepository.save(dbRole);
		RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils
				.getSecurityManager();
		ShiroDbRealm shiroDbRealm = (ShiroDbRealm) securityManager.getRealms()
				.iterator().next();
		//清空所有的缓存
		shiroDbRealm.clearAllCachedAuthorizationInfo();
		
		return true;
	}
	


	@Override
	public BaseJapRepository<Role, String> getRepository() {
		return this.roleRepository;
	}

}
