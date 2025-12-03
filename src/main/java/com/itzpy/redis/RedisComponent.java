package com.itzpy.redis;


import com.itzpy.constant.Constants;
import com.itzpy.entity.dto.SysSettingDto;
import com.itzpy.entity.dto.TokenUserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("redisComponent")
public class RedisComponent {
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 获取用户心跳时间
     * @param userId 用户ID
     * @return Long用户心跳时间
     */
    public Long getUserHeartbeat(String userId){
        String key = "user:" + userId + ":heartbeat";
        return (Long) redisUtils.get(Constants.REDIS_KEY_WS_USER_HEART_BEAT + userId);
    }


    /**
     * 保存用户信息
     * @param tokenUserInfoDto 用户信息
     */
    public void saveTokenUSerInfoDto(TokenUserInfoDto tokenUserInfoDto){
        // 根据token存储用户信息
        redisUtils.setex(Constants.REDIS_KEY_WS_TOKEN + tokenUserInfoDto.getToken(), tokenUserInfoDto, Constants.REDIS_KEY_EXPIRES_DAY * 7);
        //根据用户id存储 token（方便后面根据id先取到token再取到用户信息）
        redisUtils.setex(Constants.REDIS_KEY_WS_TOKEN_USERID + tokenUserInfoDto.getToken(), tokenUserInfoDto.getToken(), Constants.REDIS_KEY_EXPIRES_DAY * 7);
    }


    /**
     * 获取系统设置
     * @return SysSettingDto 系统设置
     */
    public SysSettingDto getSysSetting(){
        SysSettingDto sysSettingDto = (SysSettingDto)redisUtils.get(Constants.REDIS_KEY_SYS_SETTING);
        sysSettingDto = sysSettingDto == null? new SysSettingDto():sysSettingDto;

        return sysSettingDto;
    }
}
