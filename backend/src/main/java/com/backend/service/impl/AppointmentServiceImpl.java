package com.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.pojo.Appointment;
import com.backend.service.AppointmentService;
import com.backend.mapper.AppointmentMapper;
import org.springframework.stereotype.Service;

/**
* @author charlie
* @description 针对表【appointment】的数据库操作Service实现
* @createDate 2024-04-23 21:40:42
*/
@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment>
    implements AppointmentService{

}




