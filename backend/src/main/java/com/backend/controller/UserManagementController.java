package com.backend.controller;

import com.backend.pojo.AuthorAndEmployess;
import com.backend.service.AuthorityService;
import com.backend.utils.Result;
import com.backend.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

        return authorityService.getUserInfoList();
    }

    @PostMapping("/updateUserInfo")
    public Result updateUserInfo(@RequestHeader String token, @RequestBody AuthorAndEmployess authorAndEmployess) {


        return authorityService.updateUserInfo(authorAndEmployess);
    }

    @PostMapping("/insertUserInfo")
    public Result insertUserInfo(@RequestHeader String token, @RequestBody AuthorAndEmployess authorAndEmployess) {
        return authorityService.insertUserInfo(authorAndEmployess);
    }

    @PostMapping("/deleteUser")
    public Result deleteUser(@RequestHeader String token, @RequestBody Map<String, List<Integer>> idList) {
        if (idList.get("idList") == null) {
            System.out.println("null");
            return Result.build("delete parameter error", ResultCodeEnum.USERINPUT_ERROR);
        }
        return authorityService.deleteUser(idList);
    }


    @GetMapping("/queryUserInfo")
    public Result queryStocks(@RequestHeader String token, @RequestParam("type") String type, @RequestParam("value") String value) {
        if (!(type.isEmpty() || value.isEmpty())) {
            return authorityService.queryUserInfo(type, value);
        }
        return Result.build("One parameter is empty", ResultCodeEnum.USERINPUT_ERROR);
    }


}
