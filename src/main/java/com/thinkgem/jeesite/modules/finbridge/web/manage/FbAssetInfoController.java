/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.finbridge.web.manage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.finbridge.entity.manage.FbAssetInfo;
import com.thinkgem.jeesite.modules.finbridge.service.manage.FbAssetInfoService;

/**
 * 资产信息表Controller
 * @author xz
 * @version 2017-12-12
 */
@Controller
@RequestMapping(value = "${adminPath}/finbridge/manage/fbAssetInfo")
public class FbAssetInfoController extends BaseController {

	@Autowired
	private FbAssetInfoService fbAssetInfoService;
	
	@ModelAttribute
	public FbAssetInfo get(@RequestParam(required=false) String id) {
		FbAssetInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fbAssetInfoService.get(id);
		}
		if (entity == null){
			entity = new FbAssetInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("finbridge:manage:fbAssetInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(FbAssetInfo fbAssetInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		fbAssetInfo.setAduitStatus(1);
		Page<FbAssetInfo> page = fbAssetInfoService.findPage(new Page<FbAssetInfo>(request, response), fbAssetInfo); 
		model.addAttribute("page", page);
		return "modules/finbridge/manage/fbAssetInfoList";
	}

	//完成审核列表
	@RequiresPermissions("finbridge:manage:fbAssetInfo:view")
	@RequestMapping(value = {"listFinish"})
	public String listFinish(FbAssetInfo fbAssetInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		fbAssetInfo.setAduitStatus(4);
		Page<FbAssetInfo> page = fbAssetInfoService.findPage(new Page<FbAssetInfo>(request, response), fbAssetInfo);
		model.addAttribute("page", page);
		return "modules/finbridge/manage/fbAssetInfoFinishList";
	}

	//推荐列表
	@RequiresPermissions("finbridge:manage:fbAssetInfo:view")
	@RequestMapping(value = {"recommendList"})
	public String recommendList(FbAssetInfo fbAssetInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		fbAssetInfo.setListStatus(2);
		Page<FbAssetInfo> page = fbAssetInfoService.findPage(new Page<FbAssetInfo>(request, response), fbAssetInfo);
		model.addAttribute("page", page);
		return "modules/finbridge/manage/fbAssetInfoRecommendList";
	}

	@RequiresPermissions("finbridge:manage:fbAssetInfo:view")
	@RequestMapping(value = "form")
	public String form(FbAssetInfo fbAssetInfo, Model model) {
		model.addAttribute("fbAssetInfo", fbAssetInfo);
		return "modules/finbridge/manage/fbAssetInfoForm";
	}

	@RequiresPermissions("finbridge:manage:fbAssetInfo:view")
	@RequestMapping(value = "formFinish")
	public String formFinish(FbAssetInfo fbAssetInfo, Model model) {
		model.addAttribute("fbAssetInfo", fbAssetInfo);
		return "modules/finbridge/manage/fbAssetInfoFinishForm";
	}

	@RequiresPermissions("finbridge:manage:fbAssetInfo:edit")
	@RequestMapping(value = "save")
	public String save(FbAssetInfo fbAssetInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fbAssetInfo)){
			return form(fbAssetInfo, model);
		}
		fbAssetInfoService.save(fbAssetInfo);
		addMessage(redirectAttributes, "保存资产信息表成功");
		return "redirect:"+Global.getAdminPath()+"/finbridge/manage/fbAssetInfo/?repage";
	}
	
	@RequiresPermissions("finbridge:manage:fbAssetInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(FbAssetInfo fbAssetInfo, RedirectAttributes redirectAttributes) {
		fbAssetInfoService.delete(fbAssetInfo);
		addMessage(redirectAttributes, "删除资产信息表成功");
		return "redirect:"+Global.getAdminPath()+"/finbridge/manage/fbAssetInfo/?repage";
	}

	//推荐按钮
	@RequiresPermissions("finbridge:manage:fbAssetInfo:edit")
	@RequestMapping(value = "recommend")
	public String recommend(FbAssetInfo fbAssetInfo, RedirectAttributes redirectAttributes) {
		fbAssetInfo.setIsreCommend(1);
		fbAssetInfoService.save(fbAssetInfo);
		addMessage(redirectAttributes, "推荐资产信息表成功");
		return "redirect:"+Global.getAdminPath()+"/finbridge/manage/fbAssetInfo/recommendList";
	}

	//取消推荐按钮
	@RequiresPermissions("finbridge:manage:fbAssetInfo:edit")
	@RequestMapping(value = "unRecommend")
	public String unRecommend(FbAssetInfo fbAssetInfo, RedirectAttributes redirectAttributes) {
		fbAssetInfo.setIsreCommend(0);
		fbAssetInfoService.save(fbAssetInfo);
		addMessage(redirectAttributes, "取消推荐资产信息表成功");
		return "redirect:"+Global.getAdminPath()+"/finbridge/manage/fbAssetInfo/recommendList";
	}

}