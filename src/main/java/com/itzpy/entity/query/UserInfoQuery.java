package com.itzpy.entity.query;


/**
 * 参数
 */
public class UserInfoQuery extends BaseParam {


	/**
	 * 
	 */
	private String userId;

	private String userIdFuzzy;

	/**
	 * 
	 */
	private String email;

	private String emailFuzzy;

	/**
	 * 
	 */
	private String nickname;

	private String nicknameFuzzy;

	/**
	 * 0:允许直接加好友 1:需要验证
	 */
	private Integer joinType;

	/**
	 * 
	 */
	private Integer sex;

	/**
	 * 
	 */
	private String password;

	private String passwordFuzzy;

	/**
	 * 
	 */
	private String personalSignature;

	private String personalSignatureFuzzy;

	/**
	 * 
	 */
	private Integer status;

	/**
	 * 
	 */
	private String createTime;

	private String createTimeFuzzy;

	/**
	 * 
	 */
	private String lastLoginTime;

	private String lastLoginTimeStart;

	private String lastLoginTimeEnd;

	/**
	 * 
	 */
	private String areaName;

	private String areaNameFuzzy;

	/**
	 * 
	 */
	private String areaCode;

	private String areaCodeFuzzy;

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

	public void setUserIdFuzzy(String userIdFuzzy){
		this.userIdFuzzy = userIdFuzzy;
	}

	public String getUserIdFuzzy(){
		return this.userIdFuzzy;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return this.email;
	}

	public void setEmailFuzzy(String emailFuzzy){
		this.emailFuzzy = emailFuzzy;
	}

	public String getEmailFuzzy(){
		return this.emailFuzzy;
	}

	public void setNickname(String nickname){
		this.nickname = nickname;
	}

	public String getNickname(){
		return this.nickname;
	}

	public void setNicknameFuzzy(String nicknameFuzzy){
		this.nicknameFuzzy = nicknameFuzzy;
	}

	public String getNicknameFuzzy(){
		return this.nicknameFuzzy;
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

	public void setPasswordFuzzy(String passwordFuzzy){
		this.passwordFuzzy = passwordFuzzy;
	}

	public String getPasswordFuzzy(){
		return this.passwordFuzzy;
	}

	public void setPersonalSignature(String personalSignature){
		this.personalSignature = personalSignature;
	}

	public String getPersonalSignature(){
		return this.personalSignature;
	}

	public void setPersonalSignatureFuzzy(String personalSignatureFuzzy){
		this.personalSignatureFuzzy = personalSignatureFuzzy;
	}

	public String getPersonalSignatureFuzzy(){
		return this.personalSignatureFuzzy;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setCreateTimeFuzzy(String createTimeFuzzy){
		this.createTimeFuzzy = createTimeFuzzy;
	}

	public String getCreateTimeFuzzy(){
		return this.createTimeFuzzy;
	}

	public void setLastLoginTime(String lastLoginTime){
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginTime(){
		return this.lastLoginTime;
	}

	public void setLastLoginTimeStart(String lastLoginTimeStart){
		this.lastLoginTimeStart = lastLoginTimeStart;
	}

	public String getLastLoginTimeStart(){
		return this.lastLoginTimeStart;
	}
	public void setLastLoginTimeEnd(String lastLoginTimeEnd){
		this.lastLoginTimeEnd = lastLoginTimeEnd;
	}

	public String getLastLoginTimeEnd(){
		return this.lastLoginTimeEnd;
	}

	public void setAreaName(String areaName){
		this.areaName = areaName;
	}

	public String getAreaName(){
		return this.areaName;
	}

	public void setAreaNameFuzzy(String areaNameFuzzy){
		this.areaNameFuzzy = areaNameFuzzy;
	}

	public String getAreaNameFuzzy(){
		return this.areaNameFuzzy;
	}

	public void setAreaCode(String areaCode){
		this.areaCode = areaCode;
	}

	public String getAreaCode(){
		return this.areaCode;
	}

	public void setAreaCodeFuzzy(String areaCodeFuzzy){
		this.areaCodeFuzzy = areaCodeFuzzy;
	}

	public String getAreaCodeFuzzy(){
		return this.areaCodeFuzzy;
	}

	public void setLastOffTime(Long lastOffTime){
		this.lastOffTime = lastOffTime;
	}

	public Long getLastOffTime(){
		return this.lastOffTime;
	}

}
