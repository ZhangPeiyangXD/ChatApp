package com.itzpy.entity.po;

import java.util.Date;
import com.itzpy.entity.enums.DateTimePatternEnum;
import com.itzpy.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * 
 */
public class UserInfo implements Serializable {


	/**
	 * 
	 */
	private String userId;

	/**
	 * 
	 */
	private String email;

	/**
	 * 
	 */
	private String nickname;

	/**
	 * 0:允许直接加好友 1:需要验证
	 */
	private Integer joinType;

	/**
	 * 0: 男 1:女
	 */
	private Integer sex;

	/**
	 * 
	 */
	private String password;

	/**
	 * 
	 */
	private String personalSignature;

	/**
	 * 
	 */
	private Integer status;

	/**
	 * 
	 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;

	/**
	 * 
	 */
	private String areaName;

	/**
	 * 
	 */
	private String areaCode;

	/**
	 * 
	 */
	private Long lastOffTime;


	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return this.email;
	}

	public void setNickname(String nickname){
		this.nickname = nickname;
	}

	public String getNickname(){
		return this.nickname;
	}

	public void setJoinType(Integer joinType){
		this.joinType = joinType;
	}

	public Integer getJoinType(){
		return this.joinType;
	}

	public void setSex(Integer sex){
		this.sex = sex;
	}

	public Integer getSex(){
		return this.sex;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return this.password;
	}

	public void setPersonalSignature(String personalSignature){
		this.personalSignature = personalSignature;
	}

	public String getPersonalSignature(){
		return this.personalSignature;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setLastLoginTime(Date lastLoginTime){
		this.lastLoginTime = lastLoginTime;
	}

	public Date getLastLoginTime(){
		return this.lastLoginTime;
	}

	public void setAreaName(String areaName){
		this.areaName = areaName;
	}

	public String getAreaName(){
		return this.areaName;
	}

	public void setAreaCode(String areaCode){
		this.areaCode = areaCode;
	}

	public String getAreaCode(){
		return this.areaCode;
	}

	public void setLastOffTime(Long lastOffTime){
		this.lastOffTime = lastOffTime;
	}

	public Long getLastOffTime(){
		return this.lastOffTime;
	}

	@Override
	public String toString (){
		return "userId:"+(userId == null ? "空" : userId)+"，email:"+(email == null ? "空" : email)+"，nickname:"+(nickname == null ? "空" : nickname)+"，0:允许直接加好友 1:需要验证:"+(joinType == null ? "空" : joinType)+"，sex:"+(sex == null ? "空" : sex)+"，password:"+(password == null ? "空" : password)+"，personalSignature:"+(personalSignature == null ? "空" : personalSignature)+"，status:"+(status == null ? "空" : status)+"，createTime:"+(createTime == null ? "空" : createTime)+"，lastLoginTime:"+(lastLoginTime == null ? "空" : DateUtil.format(lastLoginTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，areaName:"+(areaName == null ? "空" : areaName)+"，areaCode:"+(areaCode == null ? "空" : areaCode)+"，lastOffTime:"+(lastOffTime == null ? "空" : lastOffTime);
	}
}
