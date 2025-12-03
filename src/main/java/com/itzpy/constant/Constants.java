package com.itzpy.constant;

import com.itzpy.entity.enums.UserContactTypeEnum;

public class Constants {

    // 验证码
    public static final String REDIS_KEY_CHECK_CODE = "easychat:checkCode:";
    // 用户心跳
    public static final String REDIS_KEY_WS_USER_HEART_BEAT = "easychat:ws:user:heartbeat";
    // 用户token
    public static final String REDIS_KEY_WS_TOKEN = "easychat:ws:token:";
    // 用户token对应的用户id
    public static final String REDIS_KEY_WS_TOKEN_USERID = "easychat:ws:token:userid";
    // 1分钟
    public static final int REDIS_TIME_1MIN = 60;
    // 1天的令牌过期时间
    public static final int REDIS_KEY_EXPIRES_DAY = REDIS_TIME_1MIN *60 * 24;

    // 用户id长度
    public static final int LENGTH_11 = 11;
    //生成jwt的密钥长度（用户id + 这个）
    public static final int LENGTH_20 = 20;

    // 机器人id
    public static final String ROBOT_UID = UserContactTypeEnum.USER.getPrefix() + "robot" ;
    // 系统设置
    public static final String REDIS_KEY_SYS_SETTING = "easychat:sysssetting:";
}
