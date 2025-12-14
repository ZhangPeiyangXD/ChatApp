package com.itzpy.entity.vo;

import com.itzpy.entity.po.GroupInfo;
import com.itzpy.entity.po.UserContact;
import lombok.Data;

import java.util.List;

@Data
public class GroupInfoVo {
    // 群组信息
    private GroupInfo groupInfo;
    // 群组成员列表
    private List<UserContact> userContactList;
}
