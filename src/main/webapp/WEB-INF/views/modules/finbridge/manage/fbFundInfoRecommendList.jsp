<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资金信息表管理</title>
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
		<li class="active"><a href="${ctx}/finbridge/manage/fbFundInfo/">挂牌资金信息表列表</a></li>
		<%--<shiro:hasPermission name="finbridge:manage:fbFundInfo:edit"><li><a href="${ctx}/finbridge/manage/fbFundInfo/form">资金信息表添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="fbFundInfo" action="${ctx}/finbridge/manage/fbFundInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">

			<li><label>项目名称：</label>
				<form:input path="projectName" htmlEscape="false" maxlength="50" class="input-medium" style="width:85px;"/>
			</li>
			<li><label>申请人：</label>
				<form:input path="contactPerson" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>所属公司：</label>
				<form:input path="companyName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>申请时间：&nbsp;</label>
				<input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					   value="<fmt:formatDate value="${fbFundInfo.beginDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					<%--<label>&nbsp;--&nbsp;</label>--%>&nbsp;--&nbsp;
				<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					   value="<fmt:formatDate value="${fbFundInfo.endDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目编号</th>
				<th>项目名称</th>
				<th>所属公司</th>
				<th>申请人</th>
				<th>申请时间</th>
				<th>是否推荐</th>
				<th>挂牌状态</th>
				<th>审批状态</th>
				<shiro:hasPermission name="finbridge:manage:fbFundInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fbFundInfo">
			<tr>
				<td>${fbFundInfo.fundId}</td>
				<td>${fbFundInfo.projectName}</td>
				<td>${fbFundInfo.companyName}</td>
				<td>${fbFundInfo.contactPerson}</td>
				<td><fmt:formatDate value="${fbFundInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${fns:getFbDictLabel(fbFundInfo.isRecommend,'yesOrNo',' ')}</td>
				<td>${fns:getFbDictLabel(fbFundInfo.listStatus,'listStatus',' ')}</td>
				<td>${fns:getFbDictLabel(fbFundInfo.aduitStatus,'aduitStatus',' ')}</td>
				<shiro:hasPermission name="finbridge:manage:fbFundInfo:edit"><td>
    				<a href="${ctx}/finbridge/manage/fbFundInfo/formFinish?id=${fbFundInfo.id}">查看</a>
					<a href="${ctx}/finbridge/manage/fbFundInfo/recommend?id=${fbFundInfo.id}" onclick="return confirmx('确认要推荐该资金信息表吗？', this.href)">推荐</a>
					<a href="${ctx}/finbridge/manage/fbFundInfo/unRecommend?id=${fbFundInfo.id}" onclick="return confirmx('确认要取消推荐该资金信息表吗？', this.href)">取消</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>