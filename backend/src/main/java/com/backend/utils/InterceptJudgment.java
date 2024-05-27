package com.backend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class InterceptJudgment {

    private JwtHelper jwtHelper;

    private ObjectMapper objectMapper;


    @Autowired
    private void setJwtHelper(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;

    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public Map<String, Object> authorityJudgment(HttpServletRequest request, HttpServletResponse response, AuthorityEnum authorityEnum) throws IOException {


        HashMap<String, Object> returnMap = new HashMap<>();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ServletOutputStream outputStream = response.getOutputStream();

        String token = request.getHeader("token");
//
//        if (token==null){
//            returnMap.put("return", false);
//            return returnMap;
//        }
        //登录过期
        if (token==null||jwtHelper.isExpiration(token)) {
            Result<String> stringResult = Result.build("notLogin", ResultCodeEnum.NOTLOGIN);

            String JSBody = objectMapper.writeValueAsString(stringResult);

            outputStream.print(JSBody);

            returnMap.put("return", false);
            returnMap.put("response",response);

            return returnMap;
        }

        if (
                authorityEnum == AuthorityEnum.ADMIN && jwtHelper.isAdmin(token) ||
                authorityEnum == AuthorityEnum.SALESPERSON && jwtHelper.isSalesperson(token) ||
                authorityEnum == AuthorityEnum.STOCKMANAGER && jwtHelper.isStockManager(token) ||
                authorityEnum == AuthorityEnum.INVENTORYPERSON && jwtHelper.isInventoryPerson(token) ||
                authorityEnum == AuthorityEnum.STORAGEPERSON && jwtHelper.isStoragePerson(token)) {

            returnMap.put("return", true);
            return returnMap;
        }

        returnMap.put("return", false);

        Result<String> stringResult = Result.build("Insufficient user permissions", ResultCodeEnum.PERMISSIONS_INSUFFICIENT);
        String JSBody = objectMapper.writeValueAsString(stringResult);

        outputStream.print(JSBody);

//        outputStream.write(JSBody);
        returnMap.put("response",response);

        return returnMap;
    }


    public enum AuthorityEnum {
        ADMIN,
        STOCKMANAGER,
        STORAGEPERSON,
        INVENTORYPERSON,
        SALESPERSON
    }
}
