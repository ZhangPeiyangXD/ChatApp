package com.itzpy.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import com.itzpy.entity.enums.DateTimePatternEnum;
import com.itzpy.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;



@Data
public class GroupInfo implements Serializable {


	/**
	 * 群ID
	 */
	private String groupId;

	/**
	 * 群组名
	 */
	private String groupName;

	/**
	 * 群主id
	 */
	private String groupOwnerId;

    /**
     * 状态  1:正常  0:解散
     */
    private int status;

    /**
     * 成员数量
     */
    private Integer memberCount;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 群公告
	 */
	private String groupNotice;

	/**
	 * 0:直接加入1:管理员同意后加入
	 */
	private Integer joinType;


    @Override
	public String toString (){
		return "群ID:"+(groupId == null ? "空" : groupId)+"，群组名:"+(groupName == null ? "空" : groupName)+"，群主id:"+(groupOwnerId == null ? "空" : groupOwnerId)+"，状态:"+(status == 0 ? "正常" : "解散")+"，创建时间:"+(createTime == null ? "空" : DateUtil.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，群公告:"+(groupNotice == null ? "空" : groupNotice)+"，0:直接加入1:管理员同意后加入:"+(joinType == null ? "空" : joinType);
	}
}
