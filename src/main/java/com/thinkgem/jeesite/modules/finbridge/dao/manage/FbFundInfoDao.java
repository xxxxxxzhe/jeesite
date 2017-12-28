/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.finbridge.dao.manage;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.finbridge.entity.manage.FbFundInfo;

/**
 * 资金信息表DAO接口
 * @author xz
 * @version 2017-12-12
 */
@MyBatisDao
public interface FbFundInfoDao extends CrudDao<FbFundInfo> {
	
}