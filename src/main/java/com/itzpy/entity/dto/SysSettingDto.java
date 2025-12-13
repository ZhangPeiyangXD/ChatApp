package com.itzpy.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itzpy.constant.Constants;

import java.io.Serializable;

// 在 SysSettingDto 类中使用这个注解意味着当我们从 JSON 数据创建该对象时，
// 如果有任何不属于该类的字段，Jackson 会安全地忽略它们而不是抛出 UnrecognizedPropertyException 异常。
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysSettingDto implements Serializable {
    private static final long serialVersionUID = 1L;

    // 个人最大创建的群组数量
    private Integer maxGroupAccount = 50;
    // 每个群组最大创建的群组成员数量
    private Integer maxGroupMemberAccount = 500;
    // 图片文件上传最大限制
    private Integer maxImageSize = 2;
    // 视频文件上传最大限制
    private Integer maxVideoSize = 5;
    // 文件上传最大限制
    private Integer maxFileSize = 5;
    // 机器人的UID
    private String robotUid = Constants.ROBOT_UID;
    // 机器人昵称
    private String robotNickName = "easychat";
    // 机器人欢迎语
    private String robotWelcome = "欢迎使用easychat！";

    public Integer getMaxGroupAccount() {
        return maxGroupAccount;
    }

    public void setMaxGroupAccount(Integer maxGroupAccount) {
        this.maxGroupAccount = maxGroupAccount;
    }

    public Integer getMaxGroupMemberAccount() {
        return maxGroupMemberAccount;
    }

    public void setMaxGroupMemberAccount(Integer maxGroupMemberAccount) {
        this.maxGroupMemberAccount = maxGroupMemberAccount;
    }

    public Integer getMaxImageSize() {
        return maxImageSize;
    }

    public void setMaxImageSize(Integer maxImageSize) {
        this.maxImageSize = maxImageSize;
    }

    public Integer getMaxVideoSize() {
        return maxVideoSize;
    }

    public void setMaxVideoSize(Integer maxVideoSize) {
        this.maxVideoSize = maxVideoSize;
    }

    public Integer getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(Integer maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public String getRobotUid() {
        return robotUid;
    }

    public void setRobotUid(String robotUid) {
        this.robotUid = robotUid;
    }

    public String getRobotNickName() {
        return robotNickName;
    }

    public void setRobotNickName(String robotNickName) {
        this.robotNickName = robotNickName;
    }

    public String getRobotWelcome() {
        return robotWelcome;
    }

    public void setRobotWelcome(String robotWelcome) {
        this.robotWelcome = robotWelcome;
    }
}
