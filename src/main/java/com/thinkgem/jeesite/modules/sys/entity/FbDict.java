/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 字典表Entity
 * @author xz
 * @version 2017-12-13
 */
public class FbDict extends DataEntity<FbDict> {
	
	private static final long serialVersionUID = 1L;
	private Integer key;		// key
	private String label;		// label
	private String dictname;		// 字典名
	private String dictdesc;		// 字典描述
	private Integer isdelete;		// 是否删除  1是0否
	
	public FbDict() {
		super();
	}

	public FbDict(String id){
		super(id);
	}

	@NotNull(message="key不能为空")
	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}
	
	@Length(min=1, max=500, message="label长度必须介于 1 和 500 之间")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@Length(min=1, max=45, message="字典名长度必须介于 1 和 45 之间")
	public String getDictname() {
		return dictname;
	}

	public void setDictname(String dictname) {
		this.dictname = dictname;
	}
	
	@Length(min=0, max=500, message="字典描述长度必须介于 0 和 500 之间")
	public String getDictdesc() {
		return dictdesc;
	}

	public void setDictdesc(String dictdesc) {
		this.dictdesc = dictdesc;
	}
	
	@NotNull(message="是否删除  1是0否不能为空")
	public Integer getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	
}