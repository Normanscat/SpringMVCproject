<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="pagination/pagination.css" />
<script src="pagination/jquery.pagination.js"></script>
<div id="mainPanel" class="main-panel">
	<section class="toolbar">
		<button type="button" data-url="/profession/add" class="search">新增职业</button>
		<span id="err"><font color="red">${message }</font></span>
	</section>
	<section>
		<form method="post">
			<table class="table">
				<thead>
					<tr>
						<td><input type="checkbox" id="selectAll" />序号</td>
						<td>名称</td>
						<td>创建时间</td>
						<td>操作</td>
					</tr>
				</thead>
				<c:forEach items="${profession }" var="item" varStatus="status">
					<tr>
						<td><input type="checkbox" name="ids" value="${item.id }">${status.count }</td>
						<td>${item.name }</td>
						<td>${item.creattime }</td>
						<td><a data-url="/profession/edit/${item.id }"
							class="edit" style="cursor: pointer">修改</a> <a
							data-url="/profession/delete/${item.id }" class="delete"
							style="cursor: pointer">删除</a></td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</section>
</div>
<script type="text/javascript">
	$(function() {
		var $rightContentPanell = $("body").find(".right");
		var Path = "${pageContext.request.contextPath }";

		//删除
		$(".delete").on("click", function() {
			if (!confirm("确定删除该职业？")) {
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
		//新增
		$(".search").on("click", function() {
			var $this = $(this);
			var url = $this.data("url");
			$rightContentPanell.load(Path + url);
		})

	})
</script>
</html>