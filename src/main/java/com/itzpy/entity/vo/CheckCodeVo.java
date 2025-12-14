package com.itzpy.entity.vo;

import lombok.Data;

@Data
public class CheckCodeVo {
    // 验证码
    String checkCode;
    // 验证码key
    String checkCodeKey;
}
