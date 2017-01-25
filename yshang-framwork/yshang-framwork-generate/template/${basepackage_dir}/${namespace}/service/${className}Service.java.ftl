<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.${namespace}.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yshanginfo.framwork.core.repository.BaseJapRepository;
import com.yshanginfo.framwork.core.service.BaseService;
import ${basepackage}.${namespace}.entity.${className};
import ${basepackage}.${namespace}.repository.${className}Repository;

<#include "/java_imports.include">

<#assign pkType="${table.pkColumn.javaType}" />
@Component
@Transactional
public class ${className}Service extends BaseService<${className}, ${pkType}> {
	@Autowired
	private ${className}Repository ${classNameLower}Repository;
	
	@Override
	public BaseJapRepository<${className}, ${pkType}> getRepository() {
		return this.${classNameLower}Repository;
	}
}
