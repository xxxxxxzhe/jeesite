<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资金信息表管理</title>
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
		<textarea id="aduitContent" style="width:100%;height: 100px;" placeholder="请输入审批意见"></textarea>
	</div>
	<%----------------%>
	<form:form id="inputForm" modelAttribute="fbFundInfo" action="${ctx}/finbridge/manage/fbFundInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="form-actions">
			<shiro:hasPermission name="finbridge:manage:fbFundInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="button" value="审核通过" onclick="fbFundPass()"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="finbridge:manage:fbFundInfo:edit"><input id="btnSubmit2" class="btn btn-primary" type="button" value="审核拒绝" onclick="fbFundRefuse();"/>&nbsp;</shiro:hasPermission>
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
        function fbFundPass() {
            $.ajax({
                type:"Post",
                url: "${ctxTwo}/operate/fbFundPass",
                data:{id:'${fbFundInfo.id}', aduitContent: $('#aduitContent').val(),belongTo:'${fbFundInfo.belongTo}',fundId:'${fbFundInfo.fundId}'},
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
        function fbFundRefuse() {
            $.ajax({
                type:"Post",
                url: "${ctxTwo}/operate/fbFundRefuse",
                data:{id:'${fbFundInfo.id}', aduitContent: $('#aduitContent').val(),belongTo:'${fbFundInfo.belongTo}',fundId:'${fbFundInfo.fundId}'},
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