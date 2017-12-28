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
 * 资产信息表Entity
 * @author xz
 * @version 2017-12-12
 */
public class FbAssetInfo extends DataEntity<FbAssetInfo> {
	
	private static final long serialVersionUID = 1L;
	private String assetId;         //资产id
	private String projectName;		// 产品名称
	private Integer listStatus;		// 挂牌状态0 已删除1 审核中2 挂牌中3 审核失败4 已失效
	private Date listTime;          //挂牌时间
	private String belongTo;		// 归属用户id
	private Integer isreCommend;		// 是否推荐 1是0否
	private String productName;		// 资产名称
	private Integer productType;		// 产品类型
	private Double perAmount;		// 件均额度
	private String productFeature;		// 产品特色
	private Double perPeriod;		// 单笔期限(天)
	private Double fundCostRegionFrom;		// 资金成本区间 起
	private Double fundCostRegionTo;		// 资金成本区间 终
	private Double dailyPayAmount;		// 日放款规模(百万)
	private Double totalPayAmount;		// 总放款规模(千万)
	private Double debtRate;		// 坏账率(%)
	private String companyName;		// 公司名
	private Double operationTime;		// 运营时间(月)
	private String companyAddress;		// 公司地址
	private String companyBackground;		// 公司（团队股东）背景（选填）：
	private String fundOrigin;		// 目前资金来源(多选)
	private String contactPerson;		// 联系人
	private String contactPhone;		// 联系电话
	private String contactQQ;		// QQ
	private String contactWechat;		// 微信
	private String aduitContent;		// 审核意见
	private Integer aduitStatus;		// 审批状态1 审核中2 审核通过3 审核不通过
	private String aduitPerson;		// aduitperson
	private Date createTime;		// createtime
	private Date updateTime;		// updatetime

	//日期比较字段
	private Date beginDate;		// 开始日期
	private Date endDate;		// 结束日期

	public FbAssetInfo() {
		super();
	}

	public FbAssetInfo(String id){
		super(id);
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	@Length(min=1, max=45, message="产品名称长度必须介于 1 和 45 之间")
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
	public Integer getIsreCommend() {
		return isreCommend;
	}

	public void setIsreCommend(Integer isreCommend) {
		this.isreCommend = isreCommend;
	}
	
	@Length(min=0, max=45, message="资产名称长度必须介于 0 和 45 之间")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@NotNull(message="产品类型不能为空")
	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}
	
	@NotNull(message="件均额度不能为空")
	public Double getPerAmount() {
		return perAmount;
	}

	public void setPerAmount(Double perAmount) {
		this.perAmount = perAmount;
	}
	
	@Length(min=1, max=200, message="产品特色长度必须介于 1 和 200 之间")
	public String getProductFeature() {
		return productFeature;
	}

	public void setProductFeature(String productFeature) {
		this.productFeature = productFeature;
	}
	
	@NotNull(message="单笔期限(天)不能为空")
	public Double getPerPeriod() {
		return perPeriod;
	}

	public void setPerPeriod(Double perPeriod) {
		this.perPeriod = perPeriod;
	}
	
	@NotNull(message="资金成本区间 起不能为空")
	public Double getFundCostRegionFrom() {
		return fundCostRegionFrom;
	}

	public void setFundCostRegionFrom(Double fundCostRegionFrom) {
		this.fundCostRegionFrom = fundCostRegionFrom;
	}
	
	@NotNull(message="资金成本区间 终不能为空")
	public Double getFundCostRegionTo() {
		return fundCostRegionTo;
	}

	public void setFundCostRegionTo(Double fundCostRegionTo) {
		this.fundCostRegionTo = fundCostRegionTo;
	}
	
	@NotNull(message="日放款规模(百万)不能为空")
	public Double getDailyPayAmount() {
		return dailyPayAmount;
	}

	public void setDailyPayAmount(Double dailyPayAmount) {
		this.dailyPayAmount = dailyPayAmount;
	}
	
	@NotNull(message="总放款规模(千万)不能为空")
	public Double getTotalPayAmount() {
		return totalPayAmount;
	}

	public void setTotalPayAmount(Double totalPayAmount) {
		this.totalPayAmount = totalPayAmount;
	}
	
	@NotNull(message="坏账率(%)不能为空")
	public Double getDebtRate() {
		return debtRate;
	}

	public void setDebtRate(Double debtRate) {
		this.debtRate = debtRate;
	}
	
	@Length(min=0, max=45, message="公司名长度必须介于 0 和 45 之间")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@NotNull(message="运营时间(月)不能为空")
	public Double getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Double operationTime) {
		this.operationTime = operationTime;
	}
	
	@Length(min=1, max=200, message="公司地址长度必须介于 1 和 200 之间")
	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	
	@Length(min=0, max=200, message="公司（团队股东）背景（选填）：长度必须介于 0 和 200 之间")
	public String getCompanyBackground() {
		return companyBackground;
	}

	public void setCompanyBackground(String companyBackground) {
		this.companyBackground = companyBackground;
	}
	
	@Length(min=1, max=45, message="目前资金来源(多选)长度必须介于 1 和 45 之间")
	public String getFundOrigin() {
		return fundOrigin;
	}

	public void setFundOrigin(String fundOrigin) {
		this.fundOrigin = fundOrigin;
	}
	
	@Length(min=0, max=45, message="联系人长度必须介于 0 和 45 之间")
	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	
	@Length(min=0, max=45, message="联系电话长度必须介于 0 和 45 之间")
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	@Length(min=0, max=45, message="QQ长度必须介于 0 和 45 之间")
	public String getContactQQ() {
		return contactQQ;
	}

	public void setContactQQ(String contactQQ) {
		this.contactQQ = contactQQ;
	}
	
	@Length(min=0, max=45, message="微信长度必须介于 0 和 45 之间")
	public String getContactWechat() {
		return contactWechat;
	}

	public void setContactWechat(String contactWechat) {
		this.contactWechat = contactWechat;
	}
	
	@Length(min=0, max=200, message="审核意见长度必须介于 0 和 200 之间")
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
	
	@Length(min=0, max=45, message="aduitperson长度必须介于 0 和 45 之间")
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