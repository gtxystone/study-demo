package com.yshanginfo.framwork.sys.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.yshanginfo.framwork.core.repository.BaseJapRepository;
import com.yshanginfo.framwork.sys.entity.Dictionary;

public interface DictionaryRepository extends
		BaseJapRepository<Dictionary, String> {

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	@Override
	public List<Dictionary> findAll(Specification<Dictionary> spec, Sort sort);
	
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	@Query("select d from Dictionary d left join d.dictionaryType t where t.code=?"
			+ " order by d.orderId")
	public List<Dictionary> findByDictionaryTypeCode(String dictionaryTypeCode);

}
