<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资产信息表管理</title>
	<link href="${ctxStatic}/finbridge/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${ctxStatic}/finbridge/css/style.css" rel="stylesheet"type="text/css" />
	<link rel="stylesheet" href="${ctxStatic}/finbridge/css/font-awesome.min.css">
	<meta name="decorator" content="default"/>
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
								<strong>资产挂牌申请单</strong>

								<font color ="red"><strong>【项目编号:${fbAssetInfo.assetId}】</strong> </font>

							</h5>
							<hr />
							<div class="row">
								<div class="col-sm-6">
									<h5>
										<strong>资金信息</strong>
									</h5>
									<br />
									<address>
										<strong>项目名称:</strong>${fbAssetInfo.projectName}
									</address>

									<address>
										<strong>申请时间:</strong><fmt:formatDate value="${fbAssetInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</address>
									<address>
										<strong>日放款规模(百万):</strong>${fbAssetInfo.dailyPayAmount}
									</address>
									<address>
										<strong>产品特色:</strong>${fbAssetInfo.productFeature}
									</address>
									<address>
										<strong>产品名:</strong>${fbAssetInfo.productName}
									</address>


									<br />
									<h5>
										<strong>公司信息</strong>
									</h5>
									<br />

									<address>
										<strong>公司名称: </strong>${fbAssetInfo.companyName}
									</address>
									<address>
										<strong>运营时间(月): </strong>${fbAssetInfo.operationTime}
									</address>


									<br />
									<h5>
										<strong>联系方式</strong>
									</h5>
									<br />

									<address>
										<strong>姓名: </strong> ${fbAssetInfo.contactPerson}
									</address>
									<address>
										<strong>微信号:</strong>${fbAssetInfo.contactPhone}
									</address>

								</div>

								<div class="col-sm-6">
									<br /> <br /> <br />
									<address>
										<strong>件均额度(元):</strong> ${fbAssetInfo.perAmount}
									</address>
									<address>
										<strong>总放款规模(千万): </strong> ${fbAssetInfo.totalPayAmount}
									</address>
									<address>
										<strong>坏账率(%): </strong> ${fbAssetInfo.debtRate}
									</address>
									<address>
										<strong>产品类型: </strong> ${fbAssetInfo.productType}
									</address>
									<address>
										<strong>单笔期限(天): </strong> ${fbAssetInfo.perPeriod}
									</address>

									<br /> <br /> <br /><br /><br /><br />

									<address>
										<strong>放款资金主要来源: </strong> ${fbAssetInfo.fundOrigin}
									</address>

									<br /> <br /> <br /><br />

									<address>
										<strong>手机号: </strong>${fbAssetInfo.contactPhone}
									</address>
									<address>
										<strong>QQ号: </strong>${fbAssetInfo.contactQQ}
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
	<textarea id="aduitContent" style="width:100%;height: 100px;" placeholder="请输入审批意见"></textarea>
</div>
<%----------------%>

	<form:form id="inputForm" modelAttribute="fbAssetInfo" action="${ctx}/finbridge/manage/fbAssetInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="form-actions">
			<shiro:hasPermission name="finbridge:manage:fbAssetInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="button" value="审核通过" onclick="fbAssetPass()"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="finbridge:manage:fbAssetInfo:edit"><input id="btnSubmit2" class="btn btn-primary" type="button" value="审核拒绝" onclick="fbAssetRefuse();"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>

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

    //审核通过
    function fbAssetPass() {
        $.ajax({
            type:"Post",
            url: "${ctxTwo}/operate/fbAssetPass",
            data:{id:'${fbAssetInfo.id}', aduitContent: $('#aduitContent').val(),belongTo:'${fbAssetInfo.belongTo}',assetId:'${fbAssetInfo.assetId}'},
            success: function(res){
                var res = JSON.parse(res)
                if (res.code== 200){
                    alert("审核成功");
                    location.replace(document.referrer);
                }
                if (res.code == 300){
                    alert("请填写审批意见");
                }
                if (res.code == -100){
                    alert("系统异常请联系管理员");
                }
            }
        });
    }

    //审核拒绝
    function fbAssetRefuse() {
        $.ajax({
            type:"Post",
            url: "${ctxTwo}/operate/fbAssetRefuse",
            data:{id:'${fbAssetInfo.id}', aduitContent: $('#aduitContent').val(),belongTo:'${fbAssetInfo.belongTo}',assetId:'${fbAssetInfo.assetId}'},
            success: function(res){
                var res = JSON.parse(res)
                if (res.code== 200){
                    alert("审核成功");
                    location.replace(document.referrer);
                }
                if (res.code == 300){
                    alert("请填写审批意见");
                }
                if (res.code == -100){
                    alert("系统异常请联系管理员");
                }
            }
        });
    }
</script>

</body>
</html>