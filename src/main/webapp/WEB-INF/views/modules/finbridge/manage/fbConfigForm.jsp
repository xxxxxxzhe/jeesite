<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>相关属性配置表管理</title>
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
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/finbridge/manage/fbConfig/">相关属性配置列表</a></li>
		<li class="active"><a href="${ctx}/finbridge/manage/fbConfig/form?id=${fbConfig.id}">相关属性配置<shiro:hasPermission name="finbridge:manage:fbConfig:edit">${not empty fbConfig.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="finbridge:manage:fbConfig:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="fbConfig" action="${ctx}/finbridge/manage/fbConfig/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">配置关键字：</label>
			<div class="controls">
				<form:input path="key" htmlEscape="false" maxlength="500" class="input-xlarge required"/>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">配置值：</label>
			<div class="controls">
				<form:input path="value" htmlEscape="false" maxlength="500" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*仅此字段需要修改</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">配置名称：</label>
			<div class="controls">
				<form:input path="desc" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="finbridge:manage:fbConfig:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>