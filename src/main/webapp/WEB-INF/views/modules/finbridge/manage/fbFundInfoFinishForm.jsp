<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资金信息表管理</title>
	<link href="${ctxStatic}/finbridge/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${ctxStatic}/finbridge/css/style.css" rel="stylesheet"type="text/css" />
	<link rel="stylesheet" href="${ctxStatic}/finbridge/css/font-awesome.min.css">
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>

	<%----------------%>
	<div class="widget" id="vue-content">
		<div class="widget-content">
			<div class="row stacked">
				<div class="col-sm-12">
					<!-- start customer detail -->
					<%--<div class="widget widget-tabbed">--%>
						<!-- Tab panes -->
						<div class="tab-content">
							<!-- Tab start 基本信息 -->
							<div class="tab-pane animated active fadeInRight" id="risk-info">
								<div class="user-profile-content" style="margin-top:0px">
									<h5>
										<strong>资金挂牌申请单</strong>

										<font color ="red"><strong>【项目编号:${fbFundInfo.fundId}】</strong> </font>

									</h5>
									<hr />
									<div class="row">
										<div class="col-sm-6">
											<h5>
												<strong>资金信息</strong>
											</h5>
											<br />
											<address>
												<strong>资金名称:</strong>${fbFundInfo.projectName}
											</address>

											<address>
												<strong>申请时间:</strong><fmt:formatDate value="${fbFundInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
											</address>
											<address>
												<strong>青睐资产类型:</strong>${fbFundInfo.findAssettype}
											</address>

											<br />
											<h5>
												<strong>公司信息</strong>
											</h5>
											<br />

											<address>
												<strong>公司名称: </strong>${fbFundInfo.companyName}
											</address>


											<br />
											<h5>
												<strong>联系方式</strong>
											</h5>
											<br />

											<address>
												<strong>姓名: </strong> ${fbFundInfo.contactPerson}
											</address>
											<address>
												<strong>微信号:</strong>${fbFundInfo.contactPhone}
											</address>

											<br />
											<h5>
												<strong>审批人信息</strong>
											</h5>
											<br />

											<address>
												<strong>姓名: </strong> ${fbFundInfo.aduitPerson}
											</address>
											<address>
												<strong>审批意见:</strong>${fbFundInfo.aduitContent}
											</address>

										</div>

										<div class="col-sm-6">
											<br /> <br /> <br />
											<address>
												<strong>资金类型:</strong>${fns:getFbDictLabel(fbFundInfo.fundType,'fundType',' ')}
											</address>
											<address>
												<strong>资金量(千万): </strong>
												${fbFundInfo.fundAnmount}
											</address>
											<address>
												<strong>资金成本区间(%): </strong>
												${fbFundInfo.fundCostregionfrom}  -  ${fbFundInfo.fundCostregionto}
											</address>

											<br /> <br /> <br /><br />



											<br /> <br /> <br /><br /><br /><br />

											<address>
												<strong>手机号: </strong>
												${fbFundInfo.contactPhone}
											</address>
											<address>
												<strong>QQ号: </strong>
												${fbFundInfo.contactQQ}
											</address>

										</div>
									</div>
								</div>
							</div>
							<!-- End Tab 基本信息 -->
						</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<%----------------%>
	<form:form id="inputForm" modelAttribute="fbFundInfo" action="${ctx}/finbridge/manage/fbFundInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="form-actions">
			<%--<shiro:hasPermission name="finbridge:manage:fbFundInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>--%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>