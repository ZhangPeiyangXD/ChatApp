package com.itzpy.controller;

import com.itzpy.annotation.GlobalInterceptor;
import com.itzpy.entity.dto.TokenUserInfoDto;
import com.itzpy.entity.po.GroupInfo;
import com.itzpy.entity.query.GroupInfoQuery;
import com.itzpy.entity.vo.ResponseVO;
import com.itzpy.service.GroupInfoService;
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
}