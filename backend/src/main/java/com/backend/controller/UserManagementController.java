package com.backend.controller;

import com.backend.service.AuthorityService;
import com.backend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userManagement")
@CrossOrigin
public class UserManagementController {


    private AuthorityService authorityService;

    @Autowired
    public void setAuthorityService(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

//    @PostMapping("/login")
//
//    public Result UserLogin(@RequestBody Authority authority) {
//
//        Result result = authorityService.login(authority);
//
//        return result;
//    }

    @GetMapping("/getUserInfoList")
    public Result getUserInfoList(@RequestHeader String token) {

        Result result = authorityService.getUserInfoList();

        return result;
    }

}
