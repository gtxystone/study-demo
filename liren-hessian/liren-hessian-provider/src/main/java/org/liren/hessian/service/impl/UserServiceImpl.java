package org.liren.hessian.service.impl;

import org.liren.hessian.bean.User;
import org.liren.hessian.service.UserService;

public class UserServiceImpl implements UserService{

	@Override
	public User getUser(Long id) {
		User user = new User();
		user.setId(id);
		user.setName("lessian");
		user.setPassword("123456");
		return user;
	}

}
