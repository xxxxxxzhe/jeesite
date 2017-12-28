<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>轮播图表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/finbridge/manage/fbBannerPic/">轮播图表列表</a></li>
		<shiro:hasPermission name="finbridge:manage:fbBannerPic:edit"><li><a href="${ctx}/finbridge/manage/fbBannerPic/form">轮播图表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fbBannerPic" action="${ctx}/finbridge/manage/fbBannerPic/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">

			<li><label>图片名称：</label>
				<form:input path="picName" htmlEscape="false" maxlength="50" class="input-medium" style="width:95px;"/>
			</li>
			<li><label>创建人：</label>
				<form:input path="creator" htmlEscape="false" maxlength="50" class="input-medium" style="width:95px;"/>
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>

				<th>名称</th>
				<th>简介</th>
				<th>内容链接(Url)</th>
				<th>图片地址</th>
				<th>创建者</th>
				<th>最后操作时间</th>
				<th>最后操作者</th>
				<th>是否删除</th>

				<shiro:hasPermission name="finbridge:manage:fbBannerPic:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fbBannerPic">
			<tr>

				<td>${fbBannerPic.picName}</td>
				<td>${fbBannerPic.summary}</td>
				<td>${fbBannerPic.linkUrl}</td>
				<td>${fbBannerPic.picUrl}</td>
				<td>${fbBannerPic.creator}</td>
				<td><fmt:formatDate value="${fbBannerPic.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${fbBannerPic.operator}</td>
				<td>${fns:getFbDictLabel(fbBannerPic.isDelete,'yesOrNo',' ')}</td>

				<shiro:hasPermission name="finbridge:manage:fbBannerPic:edit"><td>
    				<a href="${ctx}/finbridge/manage/fbBannerPic/form?id=${fbBannerPic.id}">编辑</a>
					<a href="${ctx}/finbridge/manage/fbBannerPic/isDelete?id=${fbBannerPic.id}" onclick="return confirmx('确认要删除该轮播图表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>