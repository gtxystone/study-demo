/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.yshanginfo.framwork.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.yshanginfo.framwork.core.persistence.DynamicSpecifications;
import com.yshanginfo.framwork.core.persistence.SearchFilter;
import com.yshanginfo.framwork.core.repository.BaseJapRepository;
import com.yshanginfo.framwork.core.utils.Reflections;

/**
 * <p>
 * 抽象service层基类 提供一些简便方法
 * <p/>
 * <p>
 * 泛型 ： T 表示实体类型；ID表示主键类型
 * <p/>
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 13-1-12 下午4:43
 * <p>
 * Version: 1.0
 */
public abstract class BaseService<T, ID extends Serializable> {

	@PersistenceContext
	protected EntityManager entityManager;

	protected Class<T> entityClass = Reflections.getClassGenricType(getClass());

	public abstract BaseJapRepository<T, ID> getRepository();

	/**
	 * 保存单个实体
	 * 
	 * @param m
	 *            实体
	 * @return 返回保存的实体
	 */
	public T save(T entity) {
		return getRepository().save(entity);
	}

	public T saveAndFlush(T entity) {
		return getRepository().saveAndFlush(entity);
	}

	/**
	 * 根据主键删除相应实体
	 * 
	 * @param id
	 *            主键
	 */
	public void delete(ID id) {
		getRepository().delete(id);
	}

	/**
	 * 根据ID删除
	 * 
	 * @param m
	 *            实体
	 */
	public void delete(ID[] ids) {
		for (ID id : ids) {
			getRepository().delete(id);
		}
	}

	/**
	 * 根据ID删除
	 * 
	 * @param m
	 *            实体
	 */
	public void delete(Iterable<ID> ids) {
		for (ID id : ids) {
			getRepository().delete(id);
		}
	}

	/**
	 * 删除实体
	 * 
	 * @param m
	 *            实体
	 */
	public void delete(T entity) {
		getRepository().delete(entity);
	}

	/**
	 * 根据主键删除相应实体
	 * 
	 * @param ids
	 *            实体
	 */
	public void deleteInBatch(Iterable<T> entities) {
		getRepository().deleteInBatch(entities);
	}

	/**
	 * 按照主键查询
	 * 
	 * @param id
	 *            主键
	 * @return 返回id对应的实体
	 */
	public T findOne(ID id) {
		return getRepository().findOne(id);
	}

	/**
	 * 实体是否存在
	 * 
	 * @param id
	 *            主键
	 * @return 存在 返回true，否则false
	 */
	public boolean exists(ID id) {
		return getRepository().exists(id);
	}

	/**
	 * 统计实体总数
	 * 
	 * @return 实体总数
	 */
	public long count() {
		return getRepository().count();
	}

	/**
	 * 查询所有实体
	 * 
	 * @return
	 */
	public List<T> findAll() {
		return getRepository().findAll();
	}

	/**
	 * 按照顺序查询所有实体
	 * 
	 * @param sort
	 * @return
	 */
	public List<T> findAll(Sort sort) {
		return getRepository().findAll(sort);
	}

	/**
	 * 分页及排序查询实体
	 * 
	 * @param pageable
	 *            分页及排序数据
	 * @return
	 */
	public Page<T> findAll(Pageable pageable) {
		return getRepository().findAll(pageable);
	}

	/**
	 * 按页面传来的查询条件查询.
	 */
	public Page<T> searchByPage(Map<String, Object> searchParams,
			Pageable pageable) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<T> spec = DynamicSpecifications.bySearchFilter(
				filters.values(), this.entityClass);
		Page<T> page = this.getRepository().findAll(spec, pageable);
		return page;
	}

	/**
	 * 按页面传来的查询条件查询.
	 */
	public List<T> search(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<T> spec = DynamicSpecifications.bySearchFilter(
				filters.values(), this.entityClass);
		List<T> list = this.getRepository().findAll(spec);
		return list;
	}

	/**
	 * 按页面传来的查询条件查询.
	 */
	public List<T> search(Map<String, Object> searchParams, Sort sort) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<T> spec = DynamicSpecifications.bySearchFilter(
				filters.values(), this.entityClass);
		List<T> list = this.getRepository().findAll(spec, sort);
		return list;
	}

	
	/**
	 * Returns a single entity matching the given {@link Specification}.
	 * 
	 * @param spec
	 * @return
	 */
	public T findOne(Specification<T> spec){
		return this.getRepository().findOne(spec);
	}

	/**
	 * Returns all entities matching the given {@link Specification}.
	 * 
	 * @param spec
	 * @return
	 */
	public List<T> findAll(Specification<T> spec){
		return this.getRepository().findAll(spec);
	}

	/**
	 * Returns a {@link Page} of entities matching the given {@link Specification}.
	 * 
	 * @param spec
	 * @param pageable
	 * @return
	 */
	public Page<T> findAll(Specification<T> spec, Pageable pageable){
		return this.getRepository().findAll(spec, pageable);
	}

	/**
	 * Returns all entities matching the given {@link Specification} and {@link Sort}.
	 * 
	 * @param spec
	 * @param sort
	 * @return
	 */
	public List<T> findAll(Specification<T> spec, Sort sort){
		return this.getRepository().findAll(spec, sort);
	}

	/**
	 * Returns the number of instances that the given {@link Specification} will return.
	 * 
	 * @param spec the {@link Specification} to count instances for
	 * @return the number of instances
	 */
	public long count(Specification<T> spec){
		return this.getRepository().count();
	}
}
