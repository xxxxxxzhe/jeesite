/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.finbridge.service.manage;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.finbridge.entity.manage.FbFundInfo;
import com.thinkgem.jeesite.modules.finbridge.dao.manage.FbFundInfoDao;

/**
 * 资金信息表Service
 * @author xz
 * @version 2017-12-12
 */
@Service
@Transactional(readOnly = true)
public class FbFundInfoService extends CrudService<FbFundInfoDao, FbFundInfo> {

	public FbFundInfo get(String id) {
		return super.get(id);
	}
	
	public List<FbFundInfo> findList(FbFundInfo fbFundInfo) {
		return super.findList(fbFundInfo);
	}
	
	public Page<FbFundInfo> findPage(Page<FbFundInfo> page, FbFundInfo fbFundInfo) {
		return super.findPage(page, fbFundInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(FbFundInfo fbFundInfo) {
		super.save(fbFundInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(FbFundInfo fbFundInfo) {
		super.delete(fbFundInfo);
	}
	
}