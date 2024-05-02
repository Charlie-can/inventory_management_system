package com.backend.controller;

import com.backend.pojo.Authority;
import com.backend.service.AuthorityService;
import com.backend.utils.Result;
import jakarta.annotation.Resource;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Resource
    private AuthorityService authorityService;


    @PostMapping("/login")

    public Result UserLogin(@RequestBody Authority authority) {

        Result result = authorityService.login(authority);

        return result;
    }

    @GetMapping("/getUserInfo")
    public Result getUserToken(@RequestHeader String token) {

//        System.out.println(token);
        Result result = authorityService.getUserInfo(token);

        return result;
    }

}
