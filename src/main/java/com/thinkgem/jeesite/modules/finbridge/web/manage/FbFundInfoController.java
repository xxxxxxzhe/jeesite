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
import com.thinkgem.jeesite.modules.finbridge.entity.manage.FbFundInfo;
import com.thinkgem.jeesite.modules.finbridge.service.manage.FbFundInfoService;

/**
 * 资金信息表Controller
 * @author xz
 * @version 2017-12-12
 */
@Controller
@RequestMapping(value = "${adminPath}/finbridge/manage/fbFundInfo")
public class FbFundInfoController extends BaseController {

	@Autowired
	private FbFundInfoService fbFundInfoService;
	
	@ModelAttribute
	public FbFundInfo get(@RequestParam(required=false) String id) {
		FbFundInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fbFundInfoService.get(id);
		}
		if (entity == null){
			entity = new FbFundInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("finbridge:manage:fbFundInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(FbFundInfo fbFundInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		fbFundInfo.setAduitStatus(1);
		Page<FbFundInfo> page = fbFundInfoService.findPage(new Page<FbFundInfo>(request, response), fbFundInfo); 
		model.addAttribute("page", page);
		return "modules/finbridge/manage/fbFundInfoList";
	}

	//完成审核列表
	@RequiresPermissions("finbridge:manage:fbFundInfo:view")
	@RequestMapping(value = {"listFinish"})
	public String listFinish(FbFundInfo fbFundInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		fbFundInfo.setAduitStatus(4);
		Page<FbFundInfo> page = fbFundInfoService.findPage(new Page<FbFundInfo>(request, response), fbFundInfo);
		model.addAttribute("page", page);
		return "modules/finbridge/manage/fbFundInfoFinishList";
	}

	//推荐列表
	@RequiresPermissions("finbridge:manage:fbFundInfo:view")
	@RequestMapping(value = {"recommendList"})
	public String recommendList(FbFundInfo fbFundInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		fbFundInfo.setListStatus(2);
		Page<FbFundInfo> page = fbFundInfoService.findPage(new Page<FbFundInfo>(request, response), fbFundInfo);
		model.addAttribute("page", page);
		return "modules/finbridge/manage/fbFundInfoRecommendList";
	}

	@RequiresPermissions("finbridge:manage:fbFundInfo:view")
	@RequestMapping(value = "form")
	public String form(FbFundInfo fbFundInfo, Model model) {
		model.addAttribute("fbFundInfo", fbFundInfo);
		return "modules/finbridge/manage/fbFundInfoForm";
	}

	@RequiresPermissions("finbridge:manage:fbFundInfo:view")
	@RequestMapping(value = "formFinish")
	public String formFinish(FbFundInfo fbFundInfo, Model model) {
		model.addAttribute("fbFundInfo", fbFundInfo);
		return "modules/finbridge/manage/fbFundInfoFinishForm";
	}

	@RequiresPermissions("finbridge:manage:fbFundInfo:edit")
	@RequestMapping(value = "save")
	public String save(FbFundInfo fbFundInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fbFundInfo)){
			return form(fbFundInfo, model);
		}
		fbFundInfoService.save(fbFundInfo);
		addMessage(redirectAttributes, "保存资金信息表成功");
		return "redirect:"+Global.getAdminPath()+"/finbridge/manage/fbFundInfo/?repage";
	}
	
	@RequiresPermissions("finbridge:manage:fbFundInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(FbFundInfo fbFundInfo, RedirectAttributes redirectAttributes) {
		fbFundInfoService.delete(fbFundInfo);
		addMessage(redirectAttributes, "删除资金信息表成功");
		return "redirect:"+Global.getAdminPath()+"/finbridge/manage/fbFundInfo/?repage";
	}

	//推荐按钮
	@RequiresPermissions("finbridge:manage:fbFundInfo:edit")
	@RequestMapping(value = "recommend")
	public String recommend(FbFundInfo fbFundInfo, RedirectAttributes redirectAttributes) {
		fbFundInfo.setIsRecommend(1);
		fbFundInfoService.save(fbFundInfo);
		addMessage(redirectAttributes, "推荐资金信息表成功");
		return "redirect:"+Global.getAdminPath()+"/finbridge/manage/fbFundInfo/recommendList";
	}

	//取消推荐按钮
	@RequiresPermissions("finbridge:manage:fbFundInfo:edit")
	@RequestMapping(value = "unRecommend")
	public String unRecommend(FbFundInfo fbFundInfo, RedirectAttributes redirectAttributes) {
		fbFundInfo.setIsRecommend(0);
		fbFundInfoService.save(fbFundInfo);
		addMessage(redirectAttributes, "取消推荐资金信息表成功");
		return "redirect:"+Global.getAdminPath()+"/finbridge/manage/fbFundInfo/recommendList";
	}
}