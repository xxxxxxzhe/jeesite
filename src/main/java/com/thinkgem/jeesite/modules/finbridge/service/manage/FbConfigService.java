/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.finbridge.service.manage;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.finbridge.entity.manage.FbConfig;
import com.thinkgem.jeesite.modules.finbridge.dao.manage.FbConfigDao;

/**
 * 相关属性配置表Service
 * @author xz
 * @version 2017-12-18
 */
@Service
@Transactional(readOnly = true)
public class FbConfigService extends CrudService<FbConfigDao, FbConfig> {

	public FbConfig get(String id) {
		return super.get(id);
	}
	
	public List<FbConfig> findList(FbConfig fbConfig) {
		return super.findList(fbConfig);
	}
	
	public Page<FbConfig> findPage(Page<FbConfig> page, FbConfig fbConfig) {
		return super.findPage(page, fbConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(FbConfig fbConfig) {
		super.save(fbConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(FbConfig fbConfig) {
		super.delete(fbConfig);
	}
	
}