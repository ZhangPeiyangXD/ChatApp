package com.itzpy.entity.enums;

import com.itzpy.utils.StringTools;

public enum JoinTypeEnum {
    JOIN(0, "直接加入"),
    APPLY(1, "需要申请");

    private Integer type;
    private String desc;

    private JoinTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 根据名称获取枚举实例
     * @param name 枚举名称，比如APPLY
     * @return 枚举实例
     */
    public static JoinTypeEnum getByName(String  name){
        try{
            if(StringTools.isEmpty( name)){
                return null;
            }
            return JoinTypeEnum.valueOf(name.toUpperCase());
        }catch (Exception e){
            return null;
        }
    }


    /**
     * 根据类型获取枚举实例
     * @param type 枚举类型，比如0
     * @return 枚举实例
     */
    public static JoinTypeEnum getByType(Integer type){
        for (JoinTypeEnum value : JoinTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        return null;
    }
}
