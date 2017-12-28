/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.finbridge.entity.manage;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 相关属性配置表Entity
 * @author xz
 * @version 2017-12-18
 */
public class FbConfig extends DataEntity<FbConfig> {
	
	private static final long serialVersionUID = 1L;
	private String key;		// 配置关键字
	private String value;		// 配置值
	private String desc;		// 配置时间
	private Date createTime;		// createtime
	private Date updateTime;		// updatetime
	
	public FbConfig() {
		super();
	}

	public FbConfig(String id){
		super(id);
	}

	@Length(min=1, max=500, message="配置关键字长度必须介于 1 和 500 之间")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	@Length(min=1, max=500, message="配置值长度必须介于 1 和 500 之间")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Length(min=0, max=500, message="配置时间长度必须介于 0 和 500 之间")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}