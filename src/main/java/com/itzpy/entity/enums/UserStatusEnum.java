package com.itzpy.entity.enums;

public enum UserStatusEnum {
    DISABLE(0, "禁用"),
    ENABLE(1, "启用");

    private Integer status;
    private String desc;

    private UserStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static UserStatusEnum getMessageByStatus(Integer status) {
        for (UserStatusEnum value : values()) {
            if (value.getStatus().equals(status)) {
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

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
