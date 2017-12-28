<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>相关属性配置表管理</title>
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
		<li class="active"><a href="${ctx}/finbridge/manage/fbConfig/">相关属性配置列表</a></li>
		<%--<shiro:hasPermission name="finbridge:manage:fbConfig:edit"><li><a href="${ctx}/finbridge/manage/fbConfig/form">相关属性配置表添加</a></li></shiro:hasPermission>--%>
	</ul>
	<%--<form:form id="searchForm" modelAttribute="fbConfig" action="${ctx}/finbridge/manage/fbConfig/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>--%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>系统值</th>
				<shiro:hasPermission name="finbridge:manage:fbConfig:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fbConfig">
			<tr>
				<td>${fbConfig.desc}</td>
				<td>${fbConfig.value}</td>
				<shiro:hasPermission name="finbridge:manage:fbConfig:edit"><td>
    				<a href="${ctx}/finbridge/manage/fbConfig/form?id=${fbConfig.id}">修改</a>
					<%--<a href="${ctx}/finbridge/manage/fbConfig/delete?id=${fbConfig.id}" onclick="return confirmx('确认要删除该相关属性配置表吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>