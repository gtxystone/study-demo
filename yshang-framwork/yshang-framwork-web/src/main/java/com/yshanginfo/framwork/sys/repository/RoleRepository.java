package com.yshanginfo.framwork.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.yshanginfo.framwork.core.repository.BaseJapRepository;
import com.yshanginfo.framwork.sys.entity.Role;

public interface RoleRepository extends BaseJapRepository<Role, String>{

	@Query("select r from Role r inner join r.users u where u.id=?1")
	public List<Role> findByUsersId(String userId);
}
