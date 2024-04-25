package com.backend.mapper;

import com.backend.pojo.AuthorAndEmployess;
import com.backend.pojo.Authority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


public interface AuthorityAndEmployeeMapper extends BaseMapper<AuthorAndEmployess> {
    AuthorAndEmployess SelectIdInAuthorityAndEmployee(int id);
}




