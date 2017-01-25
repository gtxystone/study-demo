<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<!DOCTYPE HTML>
 <%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%@include file="../../common/import-tags.jsp"%>
<html>
 <head>
  <title><spring:eval expression="@webConf['admin.title']" /></title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <%@include file="../../common/import-static.jsp"%>
 </head>
 <body>

  
  <div class="container">
 <!-- 查询 -->
 	<@generateSearchForm/>
    <!-- 修改新增 -->
   <div id="addOrUpdate" class="hide">
      <@generateAddOrUpdateForm/>
    </div>
    <div class="search-grid-container">
      <div id="grid"></div>
    </div>
  </div>
<script type="text/javascript">

BUI.use(['bui/ux/crudgrid'],function (CrudGrid) {
	
	//定义页面权限
	var add=false,update=false,del=false,list=false;
	//"framwork:crudPermission"会根据用户的权限给add，update，del,list赋值
	<framwork:crudPermission resource="/admin/${namespace}/${classNameLower}"/>

    var columns = [
	<#list table.columns as column>
		 {
		 	title:'${column.columnAlias}',dataIndex:'${column.columnNameLower}',width:'10%'
		 ${column.isDateTimeColumn?string(",renderer:BUI.Grid.Format.datetimeRenderer","")}}<#if column_has_next>,</#if>
		 
	</#list>
        ];
    
    var crudGrid = new CrudGrid({
    	entityName : '${table.tableAlias}',
      	storeUrl : '${r"${ctx}"}/admin/${namespace}/${classNameLower}/list',
        addUrl : '${r"${ctx}"}/admin/${namespace}/${classNameLower}/add',
        updateUrl : '${r"${ctx}"}/admin/${namespace}/${classNameLower}/update',
        removeUrl : '${r"${ctx}"}/admin/${namespace}/${classNameLower}/del',
        columns : columns,
        showAddBtn : add,
        showUpdateBtn : update,
        showRemoveBtn : del,
        addOrUpdateFormId : 'addOrUpdateForm',
        dialogContentId : 'addOrUpdate',
        storeCfg:{//定义store的排序，如果是复合主键一定要修改
        	sortInfo : {
        		field : '${table.pkColumn.columnNameLower}',//排序字段
        		direction : 'DESC' //升序ASC，降序DESC
        	},
        }
    });
});
 
</script>
 
</body>
</html>


<#macro generateAddOrUpdateForm>
      <form id="addOrUpdateForm" class="form-inline">
    <#--一行两个表单-->
    <#list table.notPkColumns?chunk(2) as columns>
        <div class="row">
        <#list columns as column>
          <div class="control-group span8">
            <label class="control-label">
            	${column.nullable?string("<s>*</s>","")}${column.columnAlias}:
            </label>
            <div class="controls">
            
              <input name="${column.columnNameLower}" type="text" 
              data-rules="{required:${column.nullable?string("true","false")},${column.isNumberColumn?string("number:true","")}}"
               class="${column.isDateTimeColumn?string("calendar calendar-time","input-normal control-text")}">
               <#--如果是dataTime类型则使用日期+时间选择框，如果不要时间选择就去掉calendar-time-->
            </div>
          </div>
	   </#list>
	   </div>
	 </#list>
        <input type="hidden" name="id" value="">
      </form>
</#macro>


<#macro generateSearchForm>
    <form id="searchForm" class="form-horizontal search-form">
    <div class="row">
    <#list table.notPkColumns as column>
        <div class="control-group span7">
          <label class="control-label">${column.columnAlias}:</label>
          <div class="controls">
            <input type="text" class="input-normal control-text" name="search_LIKE_${column.columnNameLower}">
          </div>
        </div>
     </#list>
        <div class="span3 offset2">
          <button  type="button" id="btnSearch" class="button button-primary">搜索</button>
        </div>
      </div>
    </form>
</#macro>
