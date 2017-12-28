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
import com.thinkgem.jeesite.modules.finbridge.entity.manage.FbArticle;
import com.thinkgem.jeesite.modules.finbridge.service.manage.FbArticleService;

/**
 * 文章表Controller
 * @author xz
 * @version 2017-12-20
 */
@Controller
@RequestMapping(value = "${adminPath}/finbridge/manage/fbArticle")
public class FbArticleController extends BaseController {

	@Autowired
	private FbArticleService fbArticleService;
	
	@ModelAttribute
	public FbArticle get(@RequestParam(required=false) String id) {
		FbArticle entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fbArticleService.get(id);
		}
		if (entity == null){
			entity = new FbArticle();
		}
		return entity;
	}
	
	@RequiresPermissions("finbridge:manage:fbArticle:view")
	@RequestMapping(value = {"list", ""})
	public String list(FbArticle fbArticle, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FbArticle> page = fbArticleService.findPage(new Page<FbArticle>(request, response), fbArticle); 
		model.addAttribute("page", page);
		return "modules/finbridge/manage/fbArticleList";
	}

	@RequiresPermissions("finbridge:manage:fbArticle:view")
	@RequestMapping(value = "form")
	public String form(FbArticle fbArticle, Model model) {
		model.addAttribute("fbArticle", fbArticle);
		return "modules/finbridge/manage/fbArticleForm";
	}

	@RequiresPermissions("finbridge:manage:fbArticle:edit")
	@RequestMapping(value = "save")
	public String save(FbArticle fbArticle, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, fbArticle)){
			return form(fbArticle, model);
		}
		String baseImage = fbArticle.getImage();
		String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/finbridge_manage/operate/article/";

		fbArticle.setImage(baseUrl + baseImage);
		fbArticleService.save(fbArticle);
		addMessage(redirectAttributes, "保存文章表成功");
		return "redirect:"+Global.getAdminPath()+"/finbridge/manage/fbArticle/?repage";
	}
	
	@RequiresPermissions("finbridge:manage:fbArticle:edit")
	@RequestMapping(value = "delete")
	public String delete(FbArticle fbArticle, RedirectAttributes redirectAttributes) {
		fbArticleService.delete(fbArticle);
		addMessage(redirectAttributes, "删除文章表成功");
		return "redirect:"+Global.getAdminPath()+"/finbridge/manage/fbArticle/?repage";
	}

	//isDelete字段修改
	@RequiresPermissions("finbridge:manage:fbArticle:edit")
	@RequestMapping(value = "isDelete")
	public String isDelete(FbArticle fbArticle, RedirectAttributes redirectAttributes) {
		fbArticle.setIsDelete(1);
		fbArticleService.save(fbArticle);
		addMessage(redirectAttributes, "删除文章表成功");
		return "redirect:"+Global.getAdminPath()+"/finbridge/manage/fbArticle/?repage";
	}

}