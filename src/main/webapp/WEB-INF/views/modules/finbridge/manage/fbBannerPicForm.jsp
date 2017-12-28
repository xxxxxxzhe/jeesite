<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>轮播图表管理</title>
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
        function submit() {
            var fd = new FormData();
            fd.append('file',$("#upfile").get(0).files[0])
            $.ajax({
                type: 'POST',
                url: '${ctxTwo}/operate/uploadImage',
                processData: false,
                contentType: false,
                data: fd,
                success: function(res){
                    var res = JSON.parse(res)
                    if (res.code == 200){
                        alert("上传成功");
                    }else {
                        alert("上传失败");
                    }
                    console.log(res);
                }
            });

        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/finbridge/manage/fbBannerPic/">轮播图表列表</a></li>
		<li class="active"><a href="${ctx}/finbridge/manage/fbBannerPic/form?id=${fbBannerPic.id}">轮播图表<shiro:hasPermission name="finbridge:manage:fbBannerPic:edit">${not empty fbBannerPic.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="finbridge:manage:fbBannerPic:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>

	<div class="control-group">
		<div class="controls" >
			<input onchange="submit()" id="upfile" type="file" name="upfile" accept="image/png,image/jpg,image/jpeg" style="padding-left: 200px"/>
		</div>
	</div>

	<form:form id="inputForm" modelAttribute="fbBannerPic" action="${ctx}/finbridge/manage/fbBannerPic/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="picName" htmlEscape="false" maxlength="45" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">简介：</label>
			<div class="controls">
				<form:textarea path="summary" htmlEscape="false" maxlength="45" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容链接：</label>
			<div class="controls">
				<form:input path="linkUrl" htmlEscape="false" maxlength="45" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片地址：</label>
			<div class="controls">
				<form:input path="picUrl" htmlEscape="false" maxlength="45" class="input-xlarge "/>
                <span class="help-inline"><font color="red">*与上传文件名称相同</font> </span>
			</div>
		</div>
        <div class="control-group">
            <label class="control-label">创建者：</label>
            <div class="controls">
                <form:input path="creator" htmlEscape="false" maxlength="45" class="input-xlarge "/>
            </div>
        </div>
        <%--<div class="control-group">
            <label class="control-label">最后操作者：</label>
            <div class="controls">
                <form:input path="operator" htmlEscape="false" maxlength="45" class="input-xlarge "/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">最后操作时间：</label>
            <div class="controls">
                <input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                    value="<fmt:formatDate value="${fbBannerPic.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">更新时间：</label>
            <div class="controls">
                <input name="updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                    value="<fmt:formatDate value="${fbBannerPic.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
            </div>
        </div>--%>
		<div class="form-actions">
			<shiro:hasPermission name="finbridge:manage:fbBannerPic:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>