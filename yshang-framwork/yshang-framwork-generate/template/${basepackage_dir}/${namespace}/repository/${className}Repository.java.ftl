<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.${namespace}.repository;

import com.yshanginfo.framwork.core.repository.BaseJapRepository;
import ${basepackage}.${namespace}.entity.${className};

<#include "/java_imports.include">

<#assign pkType="${table.pkColumn.javaType}" />

public interface ${className}Repository  extends BaseJapRepository<${className}, ${pkType}> {
	
}
