package com.yshanginfo.framwork.core.web;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.yshanginfo.framwork.core.entity.AjaxResult;
import com.yshanginfo.framwork.core.entity.ShiroUser;
import com.yshanginfo.framwork.core.mapper.JsonMapper;
import com.yshanginfo.framwork.core.service.BaseService;

public abstract class BaseCRUDController<T, ID extends Serializable> {
	
	protected JsonMapper mapper = JsonMapper.nonNullMapper();
	protected String requestMapping;
	
	protected abstract BaseService<T, ID> getServcie();
	
	public final static AjaxResult successResult = new AjaxResult(true, "操作成功！");

	public String list(ServletRequest request) {
		PageRequest pageRequest = PageRequestBulider.getPageRequest(request);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page<T> page = this.getServcie().searchByPage(searchParams,pageRequest);
		return mapper.toJson(page);
	}
	
	/**
	 * 取出Shiro中的当前用户Id.
	 */
	protected String getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.getId();
	}
	
	
}
