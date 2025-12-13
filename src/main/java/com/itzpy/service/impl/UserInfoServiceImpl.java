package com.itzpy.service.impl;

import java.util.Date;
import java.util.List;


import com.itzpy.constant.Constants;
import com.itzpy.entity.config.AppConfig;
import com.itzpy.entity.dto.TokenUserInfoDto;
import com.itzpy.entity.enums.*;
import com.itzpy.entity.po.UserInfoBeauty;
import com.itzpy.entity.vo.UserInfoVo;
import com.itzpy.exception.BusinessException;
import com.itzpy.mappers.UserInfoBeautyMapper;
import com.itzpy.redis.RedisComponent;
import com.itzpy.utils.CopyTools;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itzpy.entity.query.UserInfoQuery;
import com.itzpy.entity.po.UserInfo;
import com.itzpy.entity.vo.PaginationResultVO;
import com.itzpy.entity.query.SimplePage;
import com.itzpy.mappers.UserInfoMapper;
import com.itzpy.service.UserInfoService;
import com.itzpy.utils.StringTools;
import org.springframework.transaction.annotation.Transactional;


/**
 *  业务接口实现
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper<UserInfo, UserInfoQuery> userInfoMapper;
    @Autowired
    private UserInfoBeautyMapper<UserInfoBeauty, UserInfoQuery> userInfoBeautyMapper;
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private RedisComponent redisComponent;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<UserInfo> findListByParam(UserInfoQuery param) {
		return this.userInfoMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(UserInfoQuery param) {
		return this.userInfoMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<UserInfo> findListByPage(UserInfoQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<UserInfo> list = this.findListByParam(param);
		PaginationResultVO<UserInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(UserInfo bean) {
		return this.userInfoMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<UserInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userInfoMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<UserInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userInfoMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(UserInfo bean, UserInfoQuery param) {
		StringTools.checkParam(param);
		return this.userInfoMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(UserInfoQuery param) {
		StringTools.checkParam(param);
		return this.userInfoMapper.deleteByParam(param);
	}

	/**
	 * 根据UserId获取对象
	 */
	@Override
	public UserInfo getUserInfoByUserId(String userId) {
		return this.userInfoMapper.selectByUserId(userId);
	}

	/**
	 * 根据UserId修改
	 */
	@Override
	public Integer updateUserInfoByUserId(UserInfo bean, String userId) {
		return this.userInfoMapper.updateByUserId(bean, userId);
	}

	/**
	 * 根据UserId删除
	 */
	@Override
	public Integer deleteUserInfoByUserId(String userId) {
		return this.userInfoMapper.deleteByUserId(userId);
	}

	/**
	 * 根据Email获取对象
	 */
	@Override
	public UserInfo getUserInfoByEmail(String email) {
		return this.userInfoMapper.selectByEmail(email);
	}

	/**
	 * 根据Email修改
	 */
	@Override
	public Integer updateUserInfoByEmail(UserInfo bean, String email) {
		return this.userInfoMapper.updateByEmail(bean, email);
	}

	/**
	 * 根据Email删除
	 */
	@Override
	public Integer deleteUserInfoByEmail(String email) {
		return this.userInfoMapper.deleteByEmail(email);
	}


    /**
     * 用户注册方法
     * 实现用户注册的核心业务逻辑，包括邮箱唯一性校验、靓号检查、用户信息创建等步骤
     * 
     * @param email 用户邮箱，用于登录和接收系统通知，需保证唯一性
     * @param nickName 用户昵称，用于展示和识别用户身份
     * @param password 用户密码，将以MD5加密形式存储在数据库中
     * @throws BusinessException 当用户邮箱已存在时抛出"用户已存在"异常
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(String email, String nickName, String password) {
        // 检查用户邮箱是否已存在
        UserInfo userInfo = userInfoMapper.selectByEmail(email);

        if (userInfo != null) {
            throw new BusinessException("用户已存在");
        }

        //生成用户ID
        String userId = StringTools.getUserId();

        //判断该邮箱是否有靓号权益，如果有则使用靓号，否则用刚才的随机用户ID
        UserInfoBeauty beautyAccount = this.userInfoBeautyMapper.selectByEmail(email);
        Boolean useBeautyAccount = null != beautyAccount && BeautyAccountStatusEnum.NO_USE.getStatus().equals(beautyAccount.getStatus());

        if(useBeautyAccount){
            // 使用靓号作为用户ID
            userId = UserContactTypeEnum.USER.getPrefix() + beautyAccount.getUserId();
            // 更新靓号状态为已使用
            UserInfoBeauty updateBeauty = new UserInfoBeauty();
            updateBeauty.setStatus(BeautyAccountStatusEnum.USED.getStatus());
            this.userInfoBeautyMapper.updateById(updateBeauty, beautyAccount.getId());
        }

        // 构造用户信息对象
        userInfo = new UserInfo();
        Date curDate = new Date();

        userInfo.setUserId(userId);
        userInfo.setEmail(email);
        userInfo.setNickname(nickName);
        userInfo.setPassword(StringTools.encodeMd5( password));
        userInfo.setCreateTime(curDate);
        userInfo.setStatus(UserStatusEnum.ENABLE.getStatus());
        userInfo.setLastOffTime(curDate.getTime());
        userInfo.setJoinType(JoinTypeEnum.APPLY.getType());

        this.userInfoMapper.insert(userInfo);

        //TODO: 创建机器人好友
    }


    /**
     * 用户登录验证方法
     * 根据用户邮箱和密码进行身份验证，验证成功后返回用户信息DTO
     * 
     * @param email 用户邮箱
     * @param password 用户密码（已MD5加密）
     * @return TokenUserInfoDto 包含用户基本信息和权限标识的DTO对象
     * @throws BusinessException 当用户不存在、密码错误或用户被禁用时抛出相应异常
     */
    @Override
    public UserInfoVo login(String email, String password) {
        UserInfo userInfo = userInfoMapper.selectByEmail(email);
        
        if (userInfo == null) {
            throw new BusinessException("用户不存在");
        }

        /* 检查密码是否已经为MD5格式（32位十六进制字符串）
        boolean isMd5Format = password.matches("^[a-fA-F0-9]{32}$");
        String encodedPassword;

        if (isMd5Format) {
            // 如果前端已经传入MD5加密的密码，则直接使用
            encodedPassword = password;
        } else {
            // 如果前端传入的是明文密码，则进行MD5加密
            encodedPassword = StringTools.encodeMd5(password);
        }*/

        // 经过调试，发现前端发来的是MD5加密过后的密码
        if (!userInfo.getPassword().equals(password)) {
            throw new BusinessException("密码错误");
        }

        if(UserStatusEnum.DISABLE.getStatus().equals(userInfo.getStatus())){
            throw new BusinessException("用户被禁用");
        }

        //TODO 查询我的群组

        //TODO 查询我的联系人


        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto(userInfo);

        Long lastHeartbeat = redisComponent.getUserHeartbeat(userInfo.getUserId());
        if(lastHeartbeat != null){
            throw new BusinessException("用户已在别处登录,请退出再登陆");
        }

        // 模拟jwt，自己生成一个32位Token, 用户ID + 随机字符串。存储tokenUserInfo到redis中
        String token = StringTools.encodeMd5(userInfo.getUserId() + StringTools.getRandomString(Constants.LENGTH_20));
        tokenUserInfoDto.setToken(token);
        redisComponent.saveTokenUSerInfoDto(tokenUserInfoDto);

        // 封装Vo
        UserInfoVo userInfoVo = CopyTools.copy(userInfo, UserInfoVo.class);
        userInfoVo.setToken(tokenUserInfoDto.getToken());
        userInfoVo.setAdmin(tokenUserInfoDto.isAdmin());

        return userInfoVo;
    }


    /**
     * 根据用户信息生成Token用户信息DTO。
     * 该方法会判断用户是否为管理员，并相应设置admin标志位。
     * 
     * @param userInfoDto 用户信息实体
     * @return 包含用户ID、昵称和管理员标识的Token用户信息DTO
     */
    private TokenUserInfoDto getTokenUserInfoDto(UserInfo userInfoDto){
        TokenUserInfoDto tokenUserInfoDto = new TokenUserInfoDto();

        tokenUserInfoDto.setUserId(userInfoDto.getUserId());
        tokenUserInfoDto.setNickName(userInfoDto.getNickname());

        String adminEmails = appConfig.getAdminEmails();
        if(!StringTools.isEmpty(adminEmails) && ArrayUtils.contains(adminEmails.split(","), userInfoDto.getEmail())){
            tokenUserInfoDto.setAdmin(true);
        }else{
            tokenUserInfoDto.setAdmin(false);
        }

        return tokenUserInfoDto;
    }
}