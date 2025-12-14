package com.itzpy.controller;

import com.itzpy.annotation.GlobalInterceptor;
import com.itzpy.entity.dto.TokenUserInfoDto;
import com.itzpy.entity.enums.GroupStatusEnum;
import com.itzpy.entity.enums.UserContactStatusEnum;
import com.itzpy.entity.po.GroupInfo;
import com.itzpy.entity.po.UserContact;
import com.itzpy.entity.query.GroupInfoQuery;
import com.itzpy.entity.query.UserContactQuery;
import com.itzpy.entity.vo.GroupInfoVo;
import com.itzpy.entity.vo.ResponseVO;
import com.itzpy.exception.BusinessException;
import com.itzpy.service.GroupInfoService;
import com.itzpy.service.UserContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RestController("groupController")
@RequestMapping("/group")
@Validated
public class GroupInfoController extends ABaseController {
    @Autowired
    private GroupInfoService groupInfoService;
    @Autowired
    private UserContactService userContactService;


    /**
     * 保存群组，用于创建群组或者修改群组信息
     * @param request 请求
     * @param groupId 群组id
     * @param groupName 群组名称
     * @param groupNotice 群组公告
     * @param joinType 加入方式
     * @param avatarFile 头像（点击群头像后查看到高清的图片）
     * @param avatarCover 头像（缩略图）
     * @return ResponseVO(null)
     */
    @GlobalInterceptor
    @RequestMapping("/saveGroup")
    public ResponseVO saveGroup(
            HttpServletRequest request,
            String groupId,
            @NotEmpty String groupName,
            String groupNotice,
            @NotNull Integer joinType,
            MultipartFile avatarFile,
            MultipartFile avatarCover) throws IOException {

        // 获取tokenUserInfo,得知当前用户信息，方便群组创建信息填入
        TokenUserInfoDto userTokenInfo = getTokenUserInfo(request);

        GroupInfo groupInfo = new GroupInfo();
        groupInfo.setGroupId(groupId);
        groupInfo.setGroupOwnerId(userTokenInfo.getUserId());
        groupInfo.setGroupName(groupName);
        groupInfo.setGroupNotice(groupNotice);
        groupInfo.setJoinType(joinType);
        groupInfoService.saveGroup(groupInfo, avatarFile, avatarCover);

        return getSuccessResponseVO(null);
    }


    /**
     * 加载当前用户创建的群组列表
     * @param request 请求
     * @return ResponseVO(List<GroupInfo>) 群组列表
     */
    @RequestMapping("/loadMyGroup")
    @GlobalInterceptor
    public ResponseVO loadMyGroup(HttpServletRequest request) {
        // 获取tokenUserInfo,得知当前用户信息
        TokenUserInfoDto userTokenInfo = getTokenUserInfo(request);
        // 创建群组查询参数，查询当前用户创建的群组的列表
        GroupInfoQuery groupInfoQuery = new GroupInfoQuery();
        groupInfoQuery.setGroupOwnerId(userTokenInfo.getUserId());
        groupInfoQuery.setOrderBy("create_time desc");

        List<GroupInfo> list = groupInfoService.findListByParam(groupInfoQuery);

        return getSuccessResponseVO(list);
    }


    /**
     * 获取群组详情信息
     * @param request 请求
     * @param groupId 群组id
     * @return GroupInfo 群组信息
     */
    private GroupInfo getGroupDetailCommon(HttpServletRequest request, String groupId) {
        TokenUserInfoDto userTokenInfo = getTokenUserInfo(request);

        // 查询当前用户是否是群组成员,以及群组的状态
        UserContact userContact = userContactService.getUserContactByUserIdAndContactId(userTokenInfo.getUserId(), groupId);
        if(userContact == null || !UserContactStatusEnum.FRIEND.getStatus().equals(userContact.getStatus())){
            throw new BusinessException("您不是群组成员或者群聊已解散");
        }

        // 查询群组信息
        GroupInfo groupInfo = groupInfoService.getGroupInfoByGroupId(groupId);
        if(groupInfo == null||!GroupStatusEnum.NORMAL.getStatus().equals(groupInfo.getStatus())){
            throw new BusinessException("群聊不存在或已解散");
        }

        return groupInfo;
    }


    /**
     * 加载群组信息
     * @param request 请求
     * @param groupId 群组id
     * @return ResponseVO(GroupInfo) 群组信息
     */
    @RequestMapping("/getGroupInfo")
    @GlobalInterceptor
    public ResponseVO loadMyGroup(HttpServletRequest request,@NotEmpty String groupId) {
        GroupInfo groupInfo = getGroupDetailCommon(request, groupId);

        // 获取群组成员数量
        UserContactQuery userContactQuery = new UserContactQuery();
        userContactQuery.setContactId(groupId);
        Integer memberCount = userContactService.findCountByParam(userContactQuery);

        groupInfo.setMemberCount(memberCount);

        return getSuccessResponseVO(groupInfo);
    }

    /**
     * 加载群组信息，用于聊天
     * @param request 请求
     * @param groupId 群组id
     * @return ResponseVO(GroupInfoVo) 群组信息
     */
    @RequestMapping("/getGroupInfo4chat")
    @GlobalInterceptor
    public ResponseVO getGroupInfo4chat(HttpServletRequest request,@NotEmpty String groupId) {
        GroupInfo groupInfo = getGroupDetailCommon(request, groupId);

        // 获取群组成员列表
        UserContactQuery userContactQuery = new UserContactQuery();
        userContactQuery.setContactId(groupId);
        userContactQuery.setQueryUserInfo(true);
        userContactQuery.setOrderBy("create_time asc");
        userContactQuery.setStatus(UserContactStatusEnum.FRIEND.getStatus());
        List<UserContact> userContactList = userContactService.findListByParam(userContactQuery);

        // 设置返回Vo对象，包含群组信息以及群组成员列表
        GroupInfoVo groupInfoVo = new GroupInfoVo();
        groupInfoVo.setGroupInfo(groupInfo);
        groupInfoVo.setUserContactList(userContactList);

        return getSuccessResponseVO(groupInfoVo);
    }

}