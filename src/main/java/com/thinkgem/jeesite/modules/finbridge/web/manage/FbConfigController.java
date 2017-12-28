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
import com.thinkgem.jeesite.modules.finbridge.entity.manage.FbConfig;
import com.thinkgem.jeesite.modules.finbridge.service.manage.FbConfigService;

/**
 * 相关属性配置表Controller
 * @author xz
 * @version 2017-12-18
 */
@Controller
@RequestMapping(value = "${adminPath}/finbridge/manage/fbConfig")
public class FbConfigController extends BaseController {

	@Autowired
	private FbConfigService fbConfigService;
	
	@ModelAttribute
	public FbConfig get(@RequestParam(required=false) String id) {
		FbConfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fbConfigService.get(id);
		}
		if (entity == null){
			entity = new FbConfig();
		}
		return entity;
	}
	
	@RequiresPermissions("finbridge:manage:fbConfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(FbConfig fbConfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		fbConfig.setKey("aboutUs");
		Page<FbConfig> page = fbConfigService.findPage(new Page<FbConfig>(request, response), fbConfig); 
		model.addAttribute("page", page);
		return "modules/finbridge/manage/fbConfigList";
	}

	@RequiresPermissions("finbridge:manage:fbConfig:view")
	@RequestMapping(value = {"unlistPeriod"})
	public String unlistPeriod(FbConfig fbConfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		fbConfig.setKey("unlistPeriod");
		Page<FbConfig> page = fbConfigService.findPage(new Page<FbConfig>(request, response), fbConfig);
		model.addAttribute("page", page);
		return "modules/finbridge/manage/fbConfigBasicConfigList";
	}

	@RequiresPermissions("finbridge:manage:fbConfig:view")
	@RequestMapping(value = "form")
	public String form(FbConfig fbConfig, Model model) {
		model.addAttribute("fbConfig", fbConfig);
		return "modules/finbridge/manage/fbConfigForm";
	}

	@RequiresPermissions("finbridge:manage:fbConfig:edit")
	@RequestMapping(value = "save")
	public String save(FbConfig fbConfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fbConfig)){
			return form(fbConfig, model);
		}
		fbConfigService.save(fbConfig);
		addMessage(redirectAttributes, "保存相关属性配置表成功");
		return "redirect:"+Global.getAdminPath()+"/finbridge/manage/fbConfig/?repage";
	}
	
	@RequiresPermissions("finbridge:manage:fbConfig:edit")
	@RequestMapping(value = "delete")
	public String delete(FbConfig fbConfig, RedirectAttributes redirectAttributes) {
		fbConfigService.delete(fbConfig);
		addMessage(redirectAttributes, "删除相关属性配置表成功");
		return "redirect:"+Global.getAdminPath()+"/finbridge/manage/fbConfig/?repage";
	}

}