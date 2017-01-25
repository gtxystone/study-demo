package com.yshanginfo.framwork.sys.repository;

import com.yshanginfo.framwork.core.repository.BaseJapRepository;
import com.yshanginfo.framwork.sys.entity.User;

public interface UserRepository extends BaseJapRepository<User, String>{

	User findByName(String name);

	User findByLoginName(String loginName);
}
