package com.yshanginfo.framwork.commom.bui.vo;

import java.util.List;



public class TreeItem {
	private String id;
	private String text;
	private List<TreeItem> children;
	private String parentId;
	private Integer level;
	private String cls;//图标样式
	private boolean expanded = true;
	private String value;//用于存放其他值
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<TreeItem> getChildren() {
		return children;
	}
	public void setChildren(List<TreeItem> children) {
		this.children = children;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	

}
