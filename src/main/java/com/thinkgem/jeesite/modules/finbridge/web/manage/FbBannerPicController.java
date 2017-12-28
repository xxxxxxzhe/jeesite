/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.finbridge.web.manage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
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
import com.thinkgem.jeesite.modules.finbridge.entity.manage.FbBannerPic;
import com.thinkgem.jeesite.modules.finbridge.service.manage.FbBannerPicService;

/**
 * 轮播图表Controller
 * @author xz
 * @version 2017-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/finbridge/manage/fbBannerPic")
public class FbBannerPicController extends BaseController {

	@Autowired
	private FbBannerPicService fbBannerPicService;
	
	@ModelAttribute
	public FbBannerPic get(@RequestParam(required=false) String id) {
		FbBannerPic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fbBannerPicService.get(id);
		}
		if (entity == null){
			entity = new FbBannerPic();
		}
		return entity;
	}
	
	@RequiresPermissions("finbridge:manage:fbBannerPic:view")
	@RequestMapping(value = {"list", ""})
	public String list(FbBannerPic fbBannerPic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FbBannerPic> page = fbBannerPicService.findPage(new Page<FbBannerPic>(request, response), fbBannerPic); 
		model.addAttribute("page", page);
		return "modules/finbridge/manage/fbBannerPicList";
	}

	@RequiresPermissions("finbridge:manage:fbBannerPic:view")
	@RequestMapping(value = "form")
	public String form(FbBannerPic fbBannerPic, Model model) {
		model.addAttribute("fbBannerPic", fbBannerPic);
		return "modules/finbridge/manage/fbBannerPicForm";
	}

	@RequiresPermissions("finbridge:manage:fbBannerPic:edit")
	@RequestMapping(value = "save")
	public String save(FbBannerPic fbBannerPic, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, fbBannerPic)){
			return form(fbBannerPic, model);
		}
		String basefbBannerPic = fbBannerPic.getPicUrl();
		String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/finbridge_manage/operate/banner/";

		fbBannerPic.setPicUrl(baseUrl + basefbBannerPic);
		fbBannerPic.setOperator(UserUtils.getUser().getLoginName());
		fbBannerPicService.save(fbBannerPic);
		addMessage(redirectAttributes, "保存轮播图表成功");
		return "redirect:"+Global.getAdminPath()+"/finbridge/manage/fbBannerPic/?repage";
	}
	
	@RequiresPermissions("finbridge:manage:fbBannerPic:edit")
	@RequestMapping(value = "delete")
	public String delete(FbBannerPic fbBannerPic, RedirectAttributes redirectAttributes) {
		fbBannerPicService.delete(fbBannerPic);
		addMessage(redirectAttributes, "删除轮播图表成功");
		return "redirect:"+Global.getAdminPath()+"/finbridge/manage/fbBannerPic/?repage";
	}

	@RequiresPermissions("finbridge:manage:fbBannerPic:edit")
	@RequestMapping(value = "isDelete")
	public String isDelete(FbBannerPic fbBannerPic, RedirectAttributes redirectAttributes) {
		fbBannerPic.setIsDelete(1);
		fbBannerPicService.save(fbBannerPic);
		addMessage(redirectAttributes, "删除轮播图表成功");
		return "redirect:"+Global.getAdminPath()+"/finbridge/manage/fbBannerPic/?repage";
	}

}