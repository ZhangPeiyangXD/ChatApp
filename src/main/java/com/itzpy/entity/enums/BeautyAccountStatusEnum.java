package com.itzpy.entity.enums;

public enum BeautyAccountStatusEnum {

    NO_USE(0, "未使用"),
    USED(1, "已使用");

    private Integer status;
    private String desc;

    private BeautyAccountStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static BeautyAccountStatusEnum getByStatus(Integer status) {
        for (BeautyAccountStatusEnum value : BeautyAccountStatusEnum.values()) {
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
