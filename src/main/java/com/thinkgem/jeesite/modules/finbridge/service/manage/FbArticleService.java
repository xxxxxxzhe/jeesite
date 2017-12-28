/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.finbridge.service.manage;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.finbridge.entity.manage.FbArticle;
import com.thinkgem.jeesite.modules.finbridge.dao.manage.FbArticleDao;

/**
 * 文章表Service
 * @author xz
 * @version 2017-12-20
 */
@Service
@Transactional(readOnly = true)
public class FbArticleService extends CrudService<FbArticleDao, FbArticle> {

	public FbArticle get(String id) {
		return super.get(id);
	}
	
	public List<FbArticle> findList(FbArticle fbArticle) {
		return super.findList(fbArticle);
	}
	
	public Page<FbArticle> findPage(Page<FbArticle> page, FbArticle fbArticle) {
		return super.findPage(page, fbArticle);
	}
	
	@Transactional(readOnly = false)
	public void save(FbArticle fbArticle) {
		super.save(fbArticle);
	}
	
	@Transactional(readOnly = false)
	public void delete(FbArticle fbArticle) {
		super.delete(fbArticle);
	}
	
}