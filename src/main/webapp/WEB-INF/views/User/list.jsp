<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="pagination/pagination.css" />
<script src="pagination/jquery.pagination.js"></script>
<div id="mainPanel" class="main-panel">
	<section class="toolbar">
		<label for="">用户名称：</label> <input type="text" name="userName" />
		<button type="button" class="search">查询</button>
	</section>
	<section>
		<form action="${pageContext.request.contextPath }/User/delete"
			method="post">
			<table class="table">
				<thead>
					<tr>
						<td><input type="checkbox" id="selectAll" />序号</td>
						<td>名称</td>
						<td>职业</td>
						<td>性别</td>
						<td>爱好</td>
						<td>邮箱</td>
						<td>操作</td>
					</tr>
				</thead>
				<c:forEach items="${Users }" var="item" varStatus="status">
					<tr>
						<td><input type="checkbox" name="ids" value="${item.id }">${status.count }</td>
						<td>${item.name }</td>
						<td>${item.profession }</td>
						<td>${item.sex }</td>
						<td>${item.hobby }</td>
						<td>${item.email }</td>
						<td><a data-url="/User/edit/${item.id }" class="edit"
							style="cursor: pointer">修改</a> <a
							data-url="/User/delete/${item.id }" class="delete"
							style="cursor: pointer">删除</a></td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</section>
</div>

<script type="text/javascript">
	$(function() {
		var $rightContentPanell = $("body").find("form");
		var Path = "${pageContext.request.contextPath }";

		//删除
		$(".delete").on("click", function() {
			if (!confirm("确定删除该用戶？")) {
				return false;
			}
			var $this = $(this);
			var url = $this.data("url");
			$rightContentPanell.load(Path + url);
		})
		//编辑
		$(".edit").on("click", function() {
			var $this = $(this);
			var url = $this.data("url");
			$rightContentPanell.load(Path + url);
		});
		//搜索
		$(".search").on("click", function() {
			alert("功能待开发s")
		})

	})
</script>
<style>
</style>
</html>