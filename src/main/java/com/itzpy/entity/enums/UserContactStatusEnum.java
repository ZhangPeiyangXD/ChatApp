package com.itzpy.entity.enums;

import com.itzpy.utils.StringTools;
import org.apache.commons.lang3.StringUtils;

public enum UserContactStatusEnum {
    NOT_FRIEND(0, "非好友"),
    FRIEND(1, "好友"),
    DEL(2, "已删除好友"),
    DEL_BE(3, "被删除好友"),
    BLACKLIST(4, "已拉黑好友"),
    BLACKLIST_BE(5, "被拉黑好友");

    private Integer status;
    private String desc;

    UserContactStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static UserContactStatusEnum getByStatus(String status) {
        try{
            if(StringTools.isEmpty(status)){
                return null;
            }

            return UserContactStatusEnum.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException  e){
            return null;
        }
    }

    public static UserContactStatusEnum getByStatus(Integer status) {
        for(UserContactStatusEnum value : UserContactStatusEnum.values()){
            if(value.status.equals(status)){
                return value;
            }
        }

        return null;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
