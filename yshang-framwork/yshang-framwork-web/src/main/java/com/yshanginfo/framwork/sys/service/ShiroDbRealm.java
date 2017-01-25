/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.yshanginfo.framwork.sys.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.yshanginfo.framwork.core.entity.ShiroUser;
import com.yshanginfo.framwork.core.utils.Encodes;
import com.yshanginfo.framwork.sys.entity.Role;
import com.yshanginfo.framwork.sys.entity.User;

public class ShiroDbRealm extends AuthorizingRealm {

	protected UserService userService;

	protected RoleService roleService;

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = userService.findByLoginName(token.getUsername());
		if (user != null) {
			if ("disabled".equals(user.getStatus())) {
				throw new DisabledAccountException();
			}

			byte[] salt = Encodes.decodeHex(user.getSalt());
			return new SimpleAuthenticationInfo(new ShiroUser(
					user.getLoginName(), user.getName(), user.getId()),
					user.getPassword(), ByteSource.Util.bytes(salt), getName());
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		// User user = userService.findByLoginName(shiroUser.getLoginName());
		List<Role> roles = this.userService
				.findRolesByUserId(shiroUser.getId());

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		for (Role role : roles) {
			// 基于Role的权限信息
			info.addRole(role.getCode());
		}
		info.addStringPermissions(this.userService
				.findUserPermissions(shiroUser.getId()));
		return info;
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(
				userService.HASH_ALGORITHM);
		matcher.setHashIterations(userService.HASH_INTERATIONS);

		setCredentialsMatcher(matcher);
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
