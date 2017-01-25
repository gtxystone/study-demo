/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.yshanginfo.framwork.sys.web;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.code.kaptcha.Constants;
import com.yshanginfo.framwork.core.annotation.LogAnnotation;
import com.yshanginfo.framwork.core.entity.AjaxResult;
import com.yshanginfo.framwork.core.mapper.JsonMapper;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 
 * 真正登录的POST请求由Filter完成,
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "admin")
public class LoginController {
	private static final Logger log = LoggerFactory
			.getLogger(LoginController.class);

	private JsonMapper mapper = new JsonMapper(Include.NON_NULL);

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {

		return "forward:/admin/login";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model) {
		
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated() || subject.isRemembered()) {
			return "redirect:/admin/home";
		} else {
			return "/admin/login";
		}

	}

	@RequestMapping(value = "loginForm", method = RequestMethod.POST)
	@ResponseBody
	@LogAnnotation(value = true, writeParams = false)
	// 写日志但是不打印请求的params,避免把密码也打印出来
	public String loginForm(@RequestParam String username,
			@RequestParam String password, @RequestParam String code,
			@RequestParam(value = "rememberMe", defaultValue = "false") boolean rememberMe,
			HttpSession session) {

		String msg = null;
		boolean result = false;

		String sessionCode = (String) session
				.getAttribute(Constants.KAPTCHA_SESSION_KEY);

		// 判断图形验证码
		boolean codeFlag = false;
		if (StringUtils.isNotBlank(sessionCode)
				&& StringUtils.isNotBlank(code)) {
			sessionCode = sessionCode.toLowerCase();
			code = code.toLowerCase();
			if (sessionCode.equals(code)) {
				codeFlag = true;
			}
		}

		if (!codeFlag) {
			msg = "验证码超时或错误";
		} else {
			AuthenticationToken token = new UsernamePasswordToken(username,
					password, rememberMe);
			Subject currentUser = SecurityUtils.getSubject();
			

			try {
				currentUser.login(token);
				result = true;
				msg = "登录成功";
			} catch (UnknownAccountException uae) {
				msg = "用户不存在！";
				log.info("username wasn't in the system.");
			} catch (IncorrectCredentialsException ice) {
				msg = "用户名或密码错误！";
				log.info("password didn't match.");
			} catch (LockedAccountException lae) {
				msg = "用户为锁定状态！";
				log.info("account for that username is locked - can't login.");
			} catch (AuthenticationException ae) {
				msg = "登录失败！";
				log.info("unexpected condition." + ae.getMessage());
			}
		}
		// 只要调用了登录方法就清空SESSION中的验证码，防止暴力破解
		session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
		AjaxResult successResult = new AjaxResult(result, msg);
		return this.mapper.toJson(successResult);
	}

	@RequestMapping(value = "login/success")
	@ResponseBody
	public String success() {
		AjaxResult successResult = new AjaxResult(true, "");
		return this.mapper.toJson(successResult);
	}

	@RequestMapping(value = "login/unlogin")
	@ResponseBody
	public String unlogin() {
		// TODO
		AjaxResult successResult = new AjaxResult(true, "");
		return this.mapper.toJson(successResult);
	}

}
