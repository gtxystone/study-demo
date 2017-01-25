package org.liren.hessian.controller;

import javax.annotation.Resource;

import org.liren.hessian.bean.User;
import org.liren.hessian.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class HessianController {
	
	@Resource
	UserService userService;

	@RequestMapping(value = "/user")
	@ResponseBody
	public String findUser(Long id) {
		User user = userService.getUser(id);
		return user.getId() + ":" +user.getName();
	}

}
