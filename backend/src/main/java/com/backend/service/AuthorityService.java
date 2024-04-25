package com.backend.service;

import com.backend.pojo.Authority;
import com.backend.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author charlie
* @description 针对表【authority】的数据库操作Service
* @createDate 2024-04-23 21:40:43
*/
public interface AuthorityService extends IService<Authority> {

    Result login(Authority authority);

    Result getUserInfo(String token);
}
