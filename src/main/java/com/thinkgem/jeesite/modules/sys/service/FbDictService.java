/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.FbDict;
import com.thinkgem.jeesite.modules.sys.dao.FbDictDao;

/**
 * 字典表Service
 * @author xz
 * @version 2017-12-13
 */
@Service
@Transactional(readOnly = true)
public class FbDictService extends CrudService<FbDictDao, FbDict> {

	public FbDict get(String id) {
		return super.get(id);
	}
	
	public List<FbDict> findList(FbDict fbDict) {
		return super.findList(fbDict);
	}
	
	public Page<FbDict> findPage(Page<FbDict> page, FbDict fbDict) {
		return super.findPage(page, fbDict);
	}
	
	@Transactional(readOnly = false)
	public void save(FbDict fbDict) {
		super.save(fbDict);
	}
	
	@Transactional(readOnly = false)
	public void delete(FbDict fbDict) {
		super.delete(fbDict);
	}
	
}