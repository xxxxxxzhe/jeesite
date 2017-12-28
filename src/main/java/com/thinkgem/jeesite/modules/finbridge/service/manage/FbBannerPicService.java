/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.finbridge.service.manage;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.finbridge.entity.manage.FbBannerPic;
import com.thinkgem.jeesite.modules.finbridge.dao.manage.FbBannerPicDao;

/**
 * 轮播图表Service
 * @author xz
 * @version 2017-12-15
 */
@Service
@Transactional(readOnly = true)
public class FbBannerPicService extends CrudService<FbBannerPicDao, FbBannerPic> {

	public FbBannerPic get(String id) {
		return super.get(id);
	}
	
	public List<FbBannerPic> findList(FbBannerPic fbBannerPic) {
		return super.findList(fbBannerPic);
	}
	
	public Page<FbBannerPic> findPage(Page<FbBannerPic> page, FbBannerPic fbBannerPic) {
		return super.findPage(page, fbBannerPic);
	}
	
	@Transactional(readOnly = false)
	public void save(FbBannerPic fbBannerPic) {
		super.save(fbBannerPic);
	}
	
	@Transactional(readOnly = false)
	public void delete(FbBannerPic fbBannerPic) {
		super.delete(fbBannerPic);
	}
	
}