/**
 * @author 
 * @version 1.0
 * @since  2015-11-16 14:22:32
 * @desc Ou
 */

package com.yshanginfo.framwork.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.yshanginfo.framwork.core.repository.BaseJapRepository;
import com.yshanginfo.framwork.sys.entity.Ou;



public interface OuRepository  extends BaseJapRepository<Ou, java.lang.String> {
	
	@Query("from Ou o order by o.code asc")
	public List<Ou> findTree();
}
