package com.backend.utils;

/**
 * 统一返回结果状态信息类
 */
public enum ResultCodeEnum {

    SUCCESS(200, "success"),
    USERNAME_ERROR(501, "usernameError"),
    PERMISSIONS_INSUFFICIENT(502,"permissionsInsufficient"),
    PASSWORD_ERROR(503, "passwordError"),
    NOTLOGIN(504, "notLogin"),
    USERNAME_USED(505, "userNameUsed"),
    USERINPUT_ERROR(506, "parameterError"),
    INSERT_ERROR(507, "InsertParameterError"),
    DELETE_EMPTY(508, "DeleteEmpty"),
    DELETE_NOT_ALL(509, "DeleteNotAll"),
    UPDATE_ERROR(511, "UpdateEmpty"),
    QUERY_EMPTY(512, "QueryEmpty");






    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
