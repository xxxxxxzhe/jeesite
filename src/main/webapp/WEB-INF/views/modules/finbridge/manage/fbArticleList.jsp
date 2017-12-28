<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章表管理</title>
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
		<li class="active"><a href="${ctx}/finbridge/manage/fbArticle/">行业动态列表</a></li>
		<shiro:hasPermission name="finbridge:manage:fbArticle:edit"><li><a href="${ctx}/finbridge/manage/fbArticle/form">行业动态添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fbArticle" action="${ctx}/finbridge/manage/fbArticle/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>缩略图</th>
				<th>文章链接</th>
				<th>摘要</th>
				<th>权重</th>
				<th>是否删除</th>
				<th>创建时间</th>
				<shiro:hasPermission name="finbridge:manage:fbArticle:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fbArticle">
			<tr>
				<td>${fbArticle.title}</td>
				<td>${fbArticle.image}</td>
				<td>${fbArticle.link}</td>
				<td>${fbArticle.description}</td>
				<td>${fbArticle.weight}</td>
				<td>${fns:getFbDictLabel(fbArticle.isDelete,'yesOrNo',' ')}</td>
				<td><fmt:formatDate value="${fbArticle.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<shiro:hasPermission name="finbridge:manage:fbArticle:edit"><td>
    				<a href="${ctx}/finbridge/manage/fbArticle/form?id=${fbArticle.id}">修改</a>
					<a href="${ctx}/finbridge/manage/fbArticle/isDelete?id=${fbArticle.id}" onclick="return confirmx('确认要删除该文章表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>