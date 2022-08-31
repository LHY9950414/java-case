package com.lhy.enums;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author liheyan
 */

@Slf4j
public enum StatusCodeEnum {

    /**
     * 200
     */
    OK(200,"Succes"),
    /**
     * 400
     */
    VALID_ERROR(400,"Request parameter exception"),
    /**
     * 403
     */
    PERMISSION_ERROR(403,"Permission exception"),
    /**
     * 500
     */
    ERROR(500,"Internal server error"),
    /**
     * 10000
     */
    UNKNOWN(10000,"Unknown exception"),
    ;

    StatusCodeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Getter
    @Setter
    private Integer code;
    @Getter
    @Setter
    private String name;

    /**
     * 编码获取名称
     * @param code
     * @return
     */
    public static String getNameByCode(Integer code) {
        String result="";
        try {
            result= Arrays.asList(StatusCodeEnum.values()).stream().filter(e->e.getCode().equals(code)).findFirst().get().getName();
        }catch (Exception e) {
            log.error("枚举类StatusCode操作Exception -> e", e);
        }
        return result;
    }

}
