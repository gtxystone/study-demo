<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${namespace}.entity;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.format.annotation.DateTimeFormat;

<#include "/java_imports.include">

@Entity
@Table(name = "${table.sqlName}")
@JsonIgnoreProperties( value={"hibernateLazyInitializer","handler"})//序列化成json是不序列化这两个属性否则会报错
public class ${className} implements java.io.Serializable{
	private static final long serialVersionUID = -1L;
	
	//alias
	public static final String TABLE_ALIAS = "${table.tableAlias}";
	<#list table.columns as column>
	public static final String ALIAS_${column.constantName} = "${column.columnAlias}";
	</#list>
	
	
	<@generateFields/>
	<@generateCompositeIdConstructorIfis/>
	<@generatePkProperties/>
	<@generateNotPkProperties/>
	<@generateJavaOneToMany/>
	<@generateJavaManyToOne/>

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
		<#list table.columns as column>
			<#if !table.compositeId>
			.append("${column.columnName}",get${column.columnName}())
			</#if>
		</#list>
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
		<#list table.pkColumns as column>
			<#if !table.compositeId>
			.append(get${column.columnName}())
			</#if>
		</#list>
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ${className} == false) return false;
		if(this == obj) return true;
		${className} other = (${className})obj;
		return new EqualsBuilder()
			<#list table.pkColumns as column>
				<#if !table.compositeId>
			.append(get${column.columnName}(),other.get${column.columnName}())
				</#if>
			</#list>
			.isEquals();
	}
}

<#macro generateFields>

<#if table.compositeId>
	private ${className}Id id;
	<#list table.columns as column>
		<#if !column.pk>
	private ${column.javaType} ${column.columnNameLower};
		</#if>
	</#list>
<#else>
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	<#list table.columns as column>
	${column.hibernateValidatorExprssion}
	private ${column.javaType} ${column.columnNameLower};
	</#list>
	//columns END
</#if>

</#macro>

<#macro generateCompositeIdConstructorIfis>

	<#if table.compositeId>
	public ${className}(){
	}
	public ${className}(${className}Id id) {
		this.id = id;
	}
	<#else>
	<@generateConstructor className/>
	</#if>
	
</#macro>

<#macro generatePkProperties>
	<#if table.compositeId>
	@EmbeddedId
	public ${className}Id getId() {
		return this.id;
	}
	
	public void setId(${className}Id id) {
		this.id = id;
	}
	<#else>
		<#list table.columns as column>
			<#if column.pk>

	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	
	<#if column.isIntegerNumber>
	
	@Id
	@Column(name = "${column.sqlName}", unique = true, nullable = false, length = ${column.size})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "table")
	@GenericGenerator(name = "table", strategy = "org.hibernate.id.MultipleHiLoPerTableGenerator", parameters = {
			@Parameter(name = "max_lo", value = "5") })//增长级别为5，可根据并发级别适当调整
	<#else>
	@Id
	@GenericGenerator(name = "paymentableGenerator", strategy = "assigned")  
	@Column(name = "${column.sqlName}", unique = true, nullable = false, length = ${column.size})
	</#if>
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
			</#if>
		</#list>
	</#if>
	
</#macro>

<#macro generateNotPkProperties>
	<#list table.columns as column>
		<#if !column.pk>
			<#if column.isDateTimeColumn>

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
			</#if>
	@Column(name = "${column.sqlName}", unique = ${column.unique?string}, nullable = ${column.nullable?string}, insertable = true, updatable = true, length = ${column.size})
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	
		</#if>
	</#list>
</#macro>

<#macro generateJavaOneToMany>
	<#list table.exportedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	@JsonIgnore
	private List<${fkPojoClass}> ${fkPojoClassVar}s = new ArrayList<${fkPojoClass}>(0);
	public void set${fkPojoClass}s(List<${fkPojoClass}> ${fkPojoClassVar}){
		this.${fkPojoClassVar}s = ${fkPojoClassVar};
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "${classNameLower}")
	public List<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private ${fkPojoClass} ${fkPojoClassVar};
	
	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	<#list foreignKey.parentColumns?values as fkColumn>
	@JoinColumn(name = "${fkColumn}",nullable = false, insertable = false, updatable = false)
    </#list>
	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>

