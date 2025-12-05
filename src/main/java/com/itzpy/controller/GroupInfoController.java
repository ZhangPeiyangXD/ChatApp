package com.itzpy.controller;

import java.util.List;

import com.itzpy.entity.query.GroupInfoQuery;
import com.itzpy.entity.po.GroupInfo;
import com.itzpy.entity.vo.ResponseVO;
import com.itzpy.service.GroupInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *  Controller
 */
@RestController("groupInfoController")
@RequestMapping("/groupInfo")
public class GroupInfoController extends ABaseController{

	@Resource
	private GroupInfoService groupInfoService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("/loadDataList")
	public ResponseVO loadDataList(GroupInfoQuery query){
		return getSuccessResponseVO(groupInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public ResponseVO add(GroupInfo bean) {
		groupInfoService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("/addBatch")
	public ResponseVO addBatch(@RequestBody List<GroupInfo> listBean) {
		groupInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<GroupInfo> listBean) {
		groupInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据GroupId查询对象
	 */
	@RequestMapping("/getGroupInfoByGroupId")
	public ResponseVO getGroupInfoByGroupId(String groupId) {
		return getSuccessResponseVO(groupInfoService.getGroupInfoByGroupId(groupId));
	}

	/**
	 * 根据GroupId修改对象
	 */
	@RequestMapping("/updateGroupInfoByGroupId")
	public ResponseVO updateGroupInfoByGroupId(GroupInfo bean,String groupId) {
		groupInfoService.updateGroupInfoByGroupId(bean,groupId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据GroupId删除
	 */
	@RequestMapping("/deleteGroupInfoByGroupId")
	public ResponseVO deleteGroupInfoByGroupId(String groupId) {
		groupInfoService.deleteGroupInfoByGroupId(groupId);
		return getSuccessResponseVO(null);
	}
}