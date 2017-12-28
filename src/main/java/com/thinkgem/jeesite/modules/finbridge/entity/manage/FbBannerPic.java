/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.finbridge.entity.manage;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 轮播图表Entity
 * @author xz
 * @version 2017-12-15
 */
public class FbBannerPic extends DataEntity<FbBannerPic> {
	
	private static final long serialVersionUID = 1L;
	private String picName;		// 轮播图名称
	private String summary;		// 简介
	private String linkUrl;		// 链接地址
	private String picUrl;		// 图片地址
	private String creator;		// 创建人
	private String operator;		// 操作者
	private Integer isDelete;       //是否删除
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	public FbBannerPic() {
		super();
	}

	public FbBannerPic(String id){
		super(id);
	}

	@Length(min=0, max=45, message="轮播图名称长度必须介于 0 和 45 之间")
	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}
	
	@Length(min=0, max=45, message="简介长度必须介于 0 和 45 之间")
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@Length(min=0, max=45, message="链接地址长度必须介于 0 和 45 之间")
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	@Length(min=0, max=45, message="图片地址长度必须介于 0 和 45 之间")
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	@Length(min=0, max=45, message="创建人长度必须介于 0 和 45 之间")
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	@Length(min=0, max=45, message="操作者长度必须介于 0 和 45 之间")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
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