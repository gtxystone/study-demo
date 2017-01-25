<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.${namespace}.web;


import javax.servlet.ServletRequest;
import javax.validation.Valid;

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

import com.yshanginfo.framwork.core.service.BaseService;
import com.yshanginfo.framwork.core.web.BaseCRUDController;
import ${basepackage}.${namespace}.entity.${className};
import ${basepackage}.${namespace}.service.${className}Service;

<#include "/java_imports.include">


<#assign pkType="${table.pkColumn.javaType}" />


@Controller
@RequestMapping(value = "/admin/${namespace}/${classNameLower}")
public class ${className}Controller extends BaseCRUDController<${className}, ${pkType}> {
	@Autowired
	private ${className}Service ${classNameLower}Service;
	
	@RequestMapping(value = "")
	public String index() {
		return "/admin/${namespace}/${classNameLower}/index";
	}
	
	@RequiresPermissions("/admin/${namespace}/${classNameLower}:list")
	@RequestMapping(value = "list")
	@ResponseBody
	@Override
	public String list(ServletRequest request) {
		return super.list(request);
	}
	
	@RequiresPermissions("/admin/${namespace}/${classNameLower}:update")
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String editFrom(@PathVariable("id") ${pkType} id) {
		${className} ${classNameLower} = this.${classNameLower}Service.findOne(id);
		return mapper.toJson(${classNameLower});
	}
	
	@RequiresPermissions("/admin/${namespace}/${classNameLower}:add")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public String add(@Valid ${className} ${classNameLower}){
		this.${classNameLower}Service.save(${classNameLower});
		return mapper.toJson(successResult);
	}
	
	@RequiresPermissions("/admin/${namespace}/${classNameLower}:update")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid @ModelAttribute("${classNameLower}") ${className} ${classNameLower}){
		this.${classNameLower}Service.save(${classNameLower});
		return mapper.toJson(successResult);
	}
	
	@RequiresPermissions("/admin/${namespace}/${classNameLower}:del")
	@RequestMapping(value = "del")
	@ResponseBody
	public String del(@RequestParam(value = "ids[]") ${pkType}[] ids){
		this.${classNameLower}Service.delete(ids);
		return mapper.toJson(successResult);
	}
	

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void get${classNameLower}(@RequestParam(value = "id", defaultValue = "-1") ${pkType} id, Model model) {
	<#if table.pkColumn.isIntegerNumber>
		if (id != -1) {
	<#else>
		if (org.apache.commons.lang3.StringUtils.isNotBlank(id)) {
	</#if>
			model.addAttribute("permission", this.${classNameLower}Service.findOne(id));
		}
	}

	
	@Override
	protected BaseService<${className}, ${pkType}> getServcie() {
		return this.${classNameLower}Service;
	}
}
