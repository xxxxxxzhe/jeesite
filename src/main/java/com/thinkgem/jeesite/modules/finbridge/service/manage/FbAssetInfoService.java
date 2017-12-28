/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.finbridge.service.manage;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.finbridge.entity.manage.FbAssetInfo;
import com.thinkgem.jeesite.modules.finbridge.dao.manage.FbAssetInfoDao;

/**
 * 资产信息表Service
 * @author xz
 * @version 2017-12-12
 */
@Service
@Transactional(readOnly = true)
public class FbAssetInfoService extends CrudService<FbAssetInfoDao, FbAssetInfo> {

	public FbAssetInfo get(String id) {
		return super.get(id);
	}
	
	public List<FbAssetInfo> findList(FbAssetInfo fbAssetInfo) {
		return super.findList(fbAssetInfo);
	}
	
	public Page<FbAssetInfo> findPage(Page<FbAssetInfo> page, FbAssetInfo fbAssetInfo) {
		return super.findPage(page, fbAssetInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(FbAssetInfo fbAssetInfo) {
		super.save(fbAssetInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(FbAssetInfo fbAssetInfo) {
		super.delete(fbAssetInfo);
	}
	
}