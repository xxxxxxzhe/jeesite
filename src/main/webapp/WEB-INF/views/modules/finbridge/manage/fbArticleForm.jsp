<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章表管理</title>
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
                url: '${ctxTwo}/operate/uploadArticleImage',
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
		<li><a href="${ctx}/finbridge/manage/fbArticle/">行业动态列表</a></li>
		<li class="active"><a href="${ctx}/finbridge/manage/fbArticle/form?id=${fbArticle.id}">行业动态<shiro:hasPermission name="finbridge:manage:fbArticle:edit">${not empty fbArticle.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="finbridge:manage:fbArticle:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>

	<div class="control-group">
		<div class="controls" >
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传缩略图：
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input onchange="submit()" id="upfile" type="file" name="upfile" accept="image/png,image/jpg,image/jpeg" style="padding-left: 200px"/>
		</div>
	</div>

	<form:form id="inputForm" modelAttribute="fbArticle" action="${ctx}/finbridge/manage/fbArticle/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文章链接：</label>
			<div class="controls">
				<form:input path="link" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline">公众号文章地址。</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文章图片(缩略图)：</label>
			<div class="controls">
				<form:input path="image" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline">与上传的文件名称相同。</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关键字：</label>
			<div class="controls">
				<form:input path="keywords" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述、摘要：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">权重：</label>
			<div class="controls">
				<form:input path="weight" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
				<span class="help-inline">数值越大排序越靠前。</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文章内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" class="input-xxlarge "/>
				<sys:ckeditor replace="content" uploadPath="/cms/article" />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="finbridge:manage:fbArticle:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>