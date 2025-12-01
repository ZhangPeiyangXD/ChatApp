package com.itzpy.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;


/**
 * 
 */
public class UserInfoBeauty implements Serializable {


	/**
	 * 
	 */
	private Integer id;

	/**
	 * 
	 */
	private String userId;

	/**
	 * 
	 */
	private String email;

	/**
	 * 0:未使用 1:已使用
	 */
	private Integer status;


	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

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

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	@Override
	public String toString (){
		return "id:"+(id == null ? "空" : id)+"，userId:"+(userId == null ? "空" : userId)+"，email:"+(email == null ? "空" : email)+"，0:未使用 1:已使用:"+(status == null ? "空" : status);
	}
}
