/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.FbDict;

/**
 * 字典表DAO接口
 * @author xz
 * @version 2017-12-13
 */
@MyBatisDao
public interface FbDictDao extends CrudDao<FbDict> {
	
}