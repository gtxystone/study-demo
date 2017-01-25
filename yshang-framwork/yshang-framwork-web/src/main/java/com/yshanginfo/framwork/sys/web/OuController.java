/**
 * @author 
 * @version 1.0
 * @since  2015-11-16 14:22:32
 * @desc Ou
 */

package com.yshanginfo.framwork.sys.web;


import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yshanginfo.framwork.core.annotation.LogAnnotation;
import com.yshanginfo.framwork.core.service.BaseService;
import com.yshanginfo.framwork.core.web.BaseCRUDController;
import com.yshanginfo.framwork.sys.entity.Ou;
import com.yshanginfo.framwork.sys.service.OuService;





@Controller
@RequestMapping(value = "/admin/sys/ou")
public class OuController extends BaseCRUDController<Ou, java.lang.String> {
	@Autowired
	private OuService ouService;
	
	@RequestMapping(value = "")
	public String index() {
		return "/admin/sys/ou/index";
	}
	
	@RequiresPermissions("/admin/sys/ou:list")
	@RequestMapping(value = "list")
	@ResponseBody
	@Override
	public String list(ServletRequest request) {
		return super.list(request);
	}
	
	@RequiresPermissions("/admin/sys/ou:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String editFrom(@PathVariable("id") java.lang.String id) {
		Ou ou = this.ouService.findOne(id);
		return mapper.toJson(ou);
	}
	
	@RequiresPermissions("/admin/sys/ou:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public String add(@Valid Ou ou){
		this.ouService.save(ou);
		return mapper.toJson(successResult);
	}
	
	@RequiresPermissions("/admin/sys/ou:update")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid @ModelAttribute("ou") Ou ou){
		this.ouService.save(ou);
		return mapper.toJson(successResult);
	}
	
	@RequiresPermissions("/admin/sys/ou:del")
	@RequestMapping(value = "del")
	@ResponseBody
	public String del(@RequestParam(value = "ids[]") java.lang.String[] ids){
		this.ouService.delete(ids);
		return mapper.toJson(successResult);
	}
	

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getou(@RequestParam(value = "id", defaultValue = "-1") java.lang.String id, Model model) {
		if (org.apache.commons.lang3.StringUtils.isNotBlank(id)) {
			model.addAttribute("permission", this.ouService.findOne(id));
		}
	}
	
	@RequiresPermissions(value={"/admin/sys/ou:list","/admin/sys/ou:update"}, logical = Logical.OR)
	@RequestMapping(value = "ouTree")
	@ResponseBody
	@LogAnnotation(value = true, writeRespBody = false)// 写日志但是不打印请求的params,但不打印ResponseBody的内容
	public String ouTree() {
		return this.mapper.toJson(this.ouService.getOuList());
	}

	
	@Override
	protected BaseService<Ou, java.lang.String> getServcie() {
		return this.ouService;
	}
}
