/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.finbridge.entity.manage;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 资金信息表Entity
 * @author xz
 * @version 2017-12-12
 */
public class FbFundInfo extends DataEntity<FbFundInfo> {
	
	private static final long serialVersionUID = 1L;
	private String fundId;          //资金id
	private String projectName;		// 项目名称
	private Integer listStatus;		// 挂牌状态0 已删除1 审核中2 挂牌中3 审核失败4 已失效
	private Date listTime;          // 挂牌时间
	private String belongTo;		// 归属用户id
	private Integer isRecommend;		// 是否推荐 1是0否
	private Integer fundType;		// 资金类型 单选
	private Double fundAnmount;		// 资金量
	private Double fundCostregionfrom;		// 资金成本区间 起
	private Double fundCostregionto;		// 资金成本区间 终
	private String findAssettype;		// 青睐资产类型 多选
	private String companyName;		// 公司名称
	private String contactPerson;		// 联系人姓名
	private String contactPhone;		// 联系人电话
	private String contactQQ;		// 联系人qq
	private String contactWechat;		// 联系人微信
	private String aduitContent;		// 审批意见
	private Integer aduitStatus;		// 审批状态1 审核中2 审核通过3 审核不通过
	private String aduitPerson;		// 审核人
	private Date createTime;		// createtime
	private Date updateTime;		// updatetime

	//日期比较字段
	private Date beginDate;		// 开始日期
	private Date endDate;		// 结束日期

	public FbFundInfo() {
		super();
	}

	public FbFundInfo(String id){
		super(id);
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	@Length(min=1, max=45, message="项目名称长度必须介于 1 和 45 之间")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@NotNull(message="挂牌状态0 已删除1 审核中2 挂牌中3 审核失败4 已失效不能为空")
	public Integer getListStatus() {
		return listStatus;
	}

	public void setListStatus(Integer listStatus) {
		this.listStatus = listStatus;
	}

	public Date getListTime() {
		return listTime;
	}

	public void setListTime(Date listTime) {
		this.listTime = listTime;
	}

	public String getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}

	@NotNull(message="是否推荐 1是0否不能为空")
	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
	
	@NotNull(message="资金类型 单选不能为空")
	public Integer getFundType() {
		return fundType;
	}

	public void setFundType(Integer fundType) {
		this.fundType = fundType;
	}
	
	@NotNull(message="资金量不能为空")
	public Double getFundAnmount() {
		return fundAnmount;
	}

	public void setFundAnmount(Double fundAnmount) {
		this.fundAnmount = fundAnmount;
	}
	
	@NotNull(message="资金成本区间 起不能为空")
	public Double getFundCostregionfrom() {
		return fundCostregionfrom;
	}

	public void setFundCostregionfrom(Double fundCostregionfrom) {
		this.fundCostregionfrom = fundCostregionfrom;
	}
	
	@NotNull(message="资金成本区间 终不能为空")
	public Double getFundCostregionto() {
		return fundCostregionto;
	}

	public void setFundCostregionto(Double fundCostregionto) {
		this.fundCostregionto = fundCostregionto;
	}
	
	@Length(min=1, max=200, message="青睐资产类型 多选长度必须介于 1 和 200 之间")
	public String getFindAssettype() {
		return findAssettype;
	}

	public void setFindAssettype(String findAssettype) {
		this.findAssettype = findAssettype;
	}
	
	@Length(min=1, max=45, message="公司名称长度必须介于 1 和 45 之间")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Length(min=1, max=45, message="联系人姓名长度必须介于 1 和 45 之间")
	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	
	@Length(min=0, max=45, message="联系人电话长度必须介于 0 和 45 之间")
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	@Length(min=0, max=45, message="联系人qq长度必须介于 0 和 45 之间")
	public String getContactQQ() {
		return contactQQ;
	}

	public void setContactQQ(String contactQQ) {
		this.contactQQ = contactQQ;
	}
	
	@Length(min=0, max=45, message="联系人微信长度必须介于 0 和 45 之间")
	public String getContactWechat() {
		return contactWechat;
	}

	public void setContactWechat(String contactWechat) {
		this.contactWechat = contactWechat;
	}
	
	@Length(min=0, max=200, message="审批意见长度必须介于 0 和 200 之间")
	public String getAduitContent() {
		return aduitContent;
	}

	public void setAduitContent(String aduitContent) {
		this.aduitContent = aduitContent;
	}
	
	@NotNull(message="审批状态1 审核中2 审核通过3 审核不通过不能为空")
	public Integer getAduitStatus() {
		return aduitStatus;
	}

	public void setAduitStatus(Integer aduitStatus) {
		this.aduitStatus = aduitStatus;
	}
	
	@Length(min=0, max=45, message="审核人长度必须介于 0 和 45 之间")
	public String getAduitPerson() {
		return aduitPerson;
	}

	public void setAduitPerson(String aduitPerson) {
		this.aduitPerson = aduitPerson;
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

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}