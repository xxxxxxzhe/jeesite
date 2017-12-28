/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.finbridge.dao.manage;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.finbridge.entity.manage.FbArticle;

/**
 * 文章表DAO接口
 * @author xz
 * @version 2017-12-20
 */
@MyBatisDao
public interface FbArticleDao extends CrudDao<FbArticle> {
	
}