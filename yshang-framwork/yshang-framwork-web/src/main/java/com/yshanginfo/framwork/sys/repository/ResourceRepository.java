package com.yshanginfo.framwork.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.yshanginfo.framwork.core.repository.BaseJapRepository;
import com.yshanginfo.framwork.sys.entity.Resource;

public interface ResourceRepository extends BaseJapRepository<Resource, String>{

	//用findByLevelOrderByOrderId 里面有Order关键字。。。
	@Query("from Resource r where r.level=?1 order by r.orderId")
	public List<Resource> findByLevel(Integer level);
}
