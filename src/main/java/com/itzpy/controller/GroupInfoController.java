package com.itzpy.controller;

import com.itzpy.annotation.GlobalInterceptor;
import com.itzpy.entity.dto.TokenUserInfoDto;
import com.itzpy.entity.vo.ResponseVO;
import com.itzpy.service.GroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController("groupController")
@RequestMapping("/group")
public class GroupInfoController extends ABaseController {
    @Autowired
    private GroupInfoService groupInfoService;


    @GlobalInterceptor
    @RequestMapping("/saveGroup")
    public ResponseVO saveGroup(
            HttpServletRequest request,
            String groupId,
            @NotEmpty String groupName,
            String groupNotice,
            @NotNull Integer joinType,
            //如果传进来的图片较大，前端会压缩为avatarCover
            MultipartFile avatarFile,
            MultipartFile avatarCover) {

        // 获取tokenUserInfo,得知当前用户信息，方便群组创建信息填入
        TokenUserInfoDto userTokenInfo = getTokenUserInfo(request);

        // TODO 保存群组信息

        return getSuccessResponseVO(null);
    }
}