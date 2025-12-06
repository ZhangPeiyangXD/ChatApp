package com.itzpy.aspect;

import com.itzpy.annotation.GlobalInterceptor;
import com.itzpy.constant.Constants;
import com.itzpy.entity.dto.TokenUserInfoDto;
import com.itzpy.entity.enums.ResponseCodeEnum;
import com.itzpy.exception.BusinessException;
import com.itzpy.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


/**
 * 全局操作切面类
 * 用于处理带有@GlobalInterceptor注解的方法，进行权限验证和登录检查
 *
 * @author itzpy
 * @version 1.0
 */
@Slf4j
@Aspect
@Component
public class GlobalOperationAspect{
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 定义切入点，匹配所有被@GlobalInterceptor注解标记的方法
     */
    @Pointcut("@annotation(com.itzpy.annotation.GlobalInterceptor)")
    public void pt(){}

    /**
     * 前置通知，在被@GlobalInterceptor注解标记的方法执行前进行拦截处理
     *
     * @param joinPoint 连接点
     */
    @Before("pt()")
    public void interceptorDo(JoinPoint joinPoint){
        try {
            log.info("进入全局拦截器，方法：{}", joinPoint.getSignature().getName());
            
            // 获取方法注解
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            GlobalInterceptor globalInterceptor = method.getAnnotation(GlobalInterceptor.class);
            if(globalInterceptor ==  null){
                log.info("未找到GlobalInterceptor注解");
                return;
            }
            
            log.info("检测到GlobalInterceptor注解，checkLogin={}, checkAdmin={}", 
                globalInterceptor.checkLogin(), globalInterceptor.checkAdmin());
                
            if(globalInterceptor.checkLogin() || globalInterceptor.checkAdmin()){
                checkLogin(globalInterceptor.checkAdmin());
            }
        } catch (BusinessException e) {
            log.info("全局拦截器业务异常", e);
            throw e;
        } catch (Exception e){
            log.error("全局拦截器异常", e);
            throw new BusinessException(ResponseCodeEnum.CODE_500);
        } catch (Throwable e) {
            log.error("全局拦截器未知异常", e);
            throw new BusinessException(ResponseCodeEnum.CODE_500);
        }
    }

    
    /**
     * 检查用户登录状态和管理员权限
     *
     * @param checkAdmin 是否需要检查管理员权限
     * @throws BusinessException 当用户未登录或无管理员权限时抛出相应业务异常
     */
    private void checkLogin(boolean checkAdmin) {
        log.info("开始检查登录状态，checkAdmin={}", checkAdmin);
        
        // 获取请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            log.warn("无法获取请求上下文");
            throw new BusinessException(ResponseCodeEnum.CODE_500);
        }
        
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("token");
        if(StringUtils.isEmpty( token)){
            throw new BusinessException(ResponseCodeEnum.CODE_901);
        }

        log.info("获取到token: {}", token);

        // 获取用户
        TokenUserInfoDto tokenUserInfoDto = (TokenUserInfoDto) redisUtils.get(Constants.REDIS_KEY_WS_TOKEN + token);
        if(tokenUserInfoDto == null){
            log.info("用户未登录或token无效");
            throw new BusinessException(ResponseCodeEnum.CODE_901);
        }

        if(checkAdmin && !tokenUserInfoDto.isAdmin()){
            log.info("用户不是管理员，无权访问");
            throw new BusinessException(ResponseCodeEnum.CODE_404);
        }
        
        log.info("用户验证通过，userId: {}", tokenUserInfoDto.getUserId());
    }
}