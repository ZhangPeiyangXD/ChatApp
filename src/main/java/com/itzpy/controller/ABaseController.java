package com.itzpy.controller;

import com.itzpy.constant.Constants;
import com.itzpy.entity.dto.TokenUserInfoDto;
import com.itzpy.entity.enums.ResponseCodeEnum;
import com.itzpy.entity.vo.ResponseVO;
import com.itzpy.exception.BusinessException;
import com.itzpy.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器基类，提供通用的响应处理方法和用户身份验证功能
 * 所有控制器应继承此类以复用通用功能
 */
public class ABaseController {

    /**
     * 成功状态标识
     */
    protected static final String STATUC_SUCCESS = "success";

    /**
     * 错误状态标识
     */
    protected static final String STATUC_ERROR = "error";

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 构造成功的响应结果对象
     *
     * @param t   响应数据
     * @param <T> 数据类型
     * @return 包含成功状态、状态码、信息和数据的响应对象
     */
    protected <T> ResponseVO getSuccessResponseVO(T t) {
        ResponseVO<T> responseVO = new ResponseVO<>();
        responseVO.setStatus(STATUC_SUCCESS);
        responseVO.setCode(ResponseCodeEnum.CODE_200.getCode());
        responseVO.setInfo(ResponseCodeEnum.CODE_200.getMsg());
        responseVO.setData(t);
        return responseVO;
    }

    /**
     * 构造业务异常的响应结果对象
     *
     * @param e   业务异常对象
     * @param t   响应数据
     * @param <T> 数据类型
     * @return 包含错误状态、状态码、异常信息和数据的响应对象
     */
    protected <T> ResponseVO getBusinessErrorResponseVO(BusinessException e, T t) {
        ResponseVO vo = new ResponseVO();
        vo.setStatus(STATUC_ERROR);
        if (e.getCode() == null) {
            vo.setCode(ResponseCodeEnum.CODE_600.getCode());
        } else {
            vo.setCode(e.getCode());
        }
        vo.setInfo(e.getMessage());
        vo.setData(t);
        return vo;
    }

    /**
     * 构造服务器内部错误的响应结果对象
     *
     * @param t   响应数据
     * @param <T> 数据类型
     * @return 包含错误状态、状态码、服务器错误信息和数据的响应对象
     */
    protected <T> ResponseVO getServerErrorResponseVO(T t) {
        ResponseVO vo = new ResponseVO();
        vo.setStatus(STATUC_ERROR);
        vo.setCode(ResponseCodeEnum.CODE_500.getCode());
        vo.setInfo(ResponseCodeEnum.CODE_500.getMsg());
        vo.setData(t);
        return vo;
    }

    /**
     * 从请求头中获取用户令牌并解析用户信息
     *
     * @param request HTTP 请求对象
     * @return 用户信息数据传输对象，如果令牌无效则返回null
     */
    protected TokenUserInfoDto getTokenUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");

        TokenUserInfoDto tokenUserInfoDto = (TokenUserInfoDto) redisUtils.get(Constants.REDIS_KEY_WS_TOKEN + token);

        return tokenUserInfoDto;
    }
}