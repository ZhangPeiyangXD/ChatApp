package com.itzpy.entity.vo;

import java.io.Serializable;
import java.util.Date;

public class UserInfoVo implements Serializable {
    private String userId;

    private String email;

    private String nickname;

    /**
     * 0:允许直接加好友 1:需要验证
     */
    private Integer joinType;

    private Integer sex;

    private String password;

    private String personalSignature;

    private Integer contactStatus;

    private Date createTime;

    private String token;

    private boolean admin;

    public String getUserId() {
        return userId;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getJoinType() {
        return joinType;
    }

    public void setJoinType(Integer joinType) {
        this.joinType = joinType;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonalSignature() {
        return personalSignature;
    }

    public void setPersonalSignature(String personalSignature) {
        this.personalSignature = personalSignature;
    }

    public Integer getContactStatus() {
        return contactStatus;
    }

    public void setContactStatus(Integer contactStatus) {
        this.contactStatus = contactStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfoVo(String token, String userId, String email, String nickname, Integer joinType, Integer sex, String password, String personalSignature, Integer contactStatus, Date createTime, boolean admin) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.joinType = joinType;
        this.sex = sex;
        this.password = password;
        this.personalSignature = personalSignature;
        this.contactStatus = contactStatus;
        this.createTime = createTime;
        this.admin = admin;
    }

    public UserInfoVo() {}
}
