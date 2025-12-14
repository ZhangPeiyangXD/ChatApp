package com.itzpy.controller;


import com.itzpy.annotation.GlobalInterceptor;
import com.itzpy.constant.Constants;
import com.itzpy.entity.vo.ResponseVO;
import com.itzpy.entity.vo.UserInfoVo;
import com.itzpy.entity.vo.CheckCodeVo;
import com.itzpy.exception.BusinessException;
import com.itzpy.redis.RedisComponent;
import com.itzpy.redis.RedisUtils;
import com.itzpy.service.UserInfoService;
import com.wf.captcha.ArithmeticCaptcha;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@RestController("accountController")
@RequestMapping("/account")
@Validated
@Logger
public class AccountController extends ABaseController {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RedisComponent redisComponent;

    /**
     * 获取算术验证码图片
     * 生成一个算术验证码，包括图片和答案，并将其存储在Redis中
     * 
     * @return ResponseVO 包含验证码图片(base64编码)和验证码key的响应对象
     */
    @RequestMapping("/checkCode")
    public ResponseVO checkCode() {
        //生成验证码图片以及答案，放入到map中并返回
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(100, 42);
        String code = captcha.text();

        String checkCodeKey = UUID.randomUUID().toString();

        //存入redis
        redisUtils.setex(Constants.REDIS_KEY_CHECK_CODE + checkCodeKey, code, Constants.REDIS_TIME_1MIN);

        String checkCodeBase64 = captcha.toBase64();

        //封装返回结果(验证码图片和验证码图片答案)
        CheckCodeVo result = new CheckCodeVo();
        result.setCheckCode(checkCodeBase64);
        result.setCheckCodeKey(checkCodeKey);

        return getSuccessResponseVO(result);
    }


    /**
     * 用户注册接口
     * 验证验证码正确性后，调用用户服务进行注册操作
     * 
     * @param checkCodeKey 验证码key，用于从Redis中获取正确的验证码答案
     * @param email        用户邮箱，必须是有效的邮箱格式且不能为空
     * @param password     用户密码，不能为空
     * @param nickName     用户昵称，不能为空
     * @param checkCode    用户输入的验证码，不能为空
     * @return ResponseVO  注册成功或失败的响应对象
     */
    @RequestMapping("/register")
    public ResponseVO register(@NotEmpty String checkCodeKey,
                               @NotEmpty @Email String email,
                               @NotEmpty String password,
                               @NotEmpty String nickName,
                               @NotEmpty String checkCode) {
        try {
            //验证码校验
            if (!checkCode.equalsIgnoreCase((String) redisUtils.get(Constants.REDIS_KEY_CHECK_CODE + checkCodeKey) )) {
                throw new BusinessException("图片验证码错误");
            }

            userInfoService.register(email, nickName, password);

            return getSuccessResponseVO(null);
        } finally {
            //删除验证码的缓存
            redisUtils.delete(Constants.REDIS_KEY_CHECK_CODE + checkCodeKey);
        }
    }


    /**
     * 用户登录接口
     * 验证验证码正确性后，调用用户服务进行登录操作
     * 
     * @param checkCodeKey 验证码key，用于从Redis中获取正确的验证码答案
     * @param email        用户邮箱，必须是有效的邮箱格式且不能为空
     * @param password     用户密码，不能为空
     * @param checkCode    用户输入的验证码，不能为空
     * @return ResponseVO  登录成功或失败的响应对象，登录成功时包含用户信息
     */
    @RequestMapping("/login")
    public ResponseVO login(@NotEmpty String checkCodeKey,
                            @NotEmpty @Email String email,
                            @NotEmpty String password,
                            @NotEmpty String checkCode) {
        //登录
        try {
            //验证码校验
            if (!checkCode.equalsIgnoreCase((String) redisUtils.get(Constants.REDIS_KEY_CHECK_CODE + checkCodeKey))) {
                throw new BusinessException("图片验证码错误");
            }

            //获取并返回vo给前端。
            UserInfoVo userInfoVo = userInfoService.login(email, password);

            return getSuccessResponseVO(userInfoVo);
        } finally {
            redisUtils.delete(Constants.REDIS_KEY_CHECK_CODE + checkCodeKey);
        }
    }


    /**
     * 获取系统设置信息接口。
     * 获取系统设置信息（SysSettingDto中的信息），并返回给前端。
     *
     * @return ResponseVO  系统设置信息的响应对象
     */
    @GlobalInterceptor
    @RequestMapping("/getSysSetting")
    public ResponseVO getSysSetting() {
        return getSuccessResponseVO(redisComponent.getSysSetting());
    }
}