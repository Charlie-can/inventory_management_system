package com.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.pojo.Employee;
import com.backend.service.EmployeeService;
import com.backend.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

/**
* @author charlie
* @description 针对表【employee】的数据库操作Service实现
* @createDate 2024-04-23 21:40:43
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{

}




