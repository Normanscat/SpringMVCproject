<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="pagination/pagination.css" />
<script src="pagination/jquery.pagination.js"></script>
<style>
.searchs {
	magin-right: 10px;
}
</style>
<div id="mainPanel" class="main-panel">
	<section
		style="display: flex; flex-direction: row-reverse; justify-content: space-between;"
		class="toolbar">
		<div>
			<label for="">用户名称：</label> <input type="text" class="username"
				name="userName" placeholder="请输入关键字" />
			<button type="button" class="searchs" data-url="/User/search">搜索</button>
		</div>
		<button type="submit" class="deletes" data-url="/User/deletes" style="margin-right: 550px;">删除用戶</button>
		<button type="button" class="search" data-url="/User/add">新增用戶</button>
	</section>
	<section>
		<form action="${pageContext.request.contextPath }/User/delete"
			method="post">
			<table class="table">
				<thead>
					<tr>
						<td><input type="checkbox" id="selectAll" class="selectAll" />序号</td>
						<td>名称</td>
						<td>职业</td>
						<td>性别</td>
						<td>爱好</td>
						<td>邮箱</td>
						<td>创建时间</td>
						<td>操作</td>
					</tr>
				</thead>
				<c:forEach items="${Users }" var="item" varStatus="status">
					<tr>
						<td><input type="checkbox" name="ids" class="checkbox"
							value="${item.id }">${status.count }</td>
						<td>${item.name }</td>
						<td>${item.profession }</td>
						<td>${item.sex }</td>
						<td>${item.hobby }</td>
						<td>${item.email }</td>
						<td>${item.creattime }</td>
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
		var $rightContentPanell = $("body").find(".right");
		var $rightContentPanells = $("body").find(".rights");
		var Path = "${pageContext.request.contextPath }";

		//删除
		$(".delete").on("click", function() {
			if (!confirm("确定删除该用戶？")) {
				return false;
			}
			var $this = $(this);
			var url = $this.data("url");
			$rightContentPanell.load(Path + url);
			$(".active").trigger("click");
		})
		//编辑
		$(".edit").on("click", function() {
			$rightContentPanells.hide();
			var $this = $(this);
			var url = $this.data("url");
			$rightContentPanell.load(Path + url);
		});
		//新增
		$(".search").on("click", function() {
			$rightContentPanells.hide();
			var $this = $(this);
			var url = $this.data("url");
			$rightContentPanell.load(Path + url);
		})
		//模糊查询
		$(".searchs").on("click", function() {
			$rightContentPanells.hide();
			var search = $(".username").val();
			var $this = $(this);
			var url = $this.data("url");
			$rightContentPanell.load(Path + url, {
				search : search
			});
			alert(search)
		})

		//批量删除
		$(".deletes").on("click", function() {

			var $this = $(this);
			var url = $this.data("url");

			var checkedItems = $rightContentPanell.find(".checkbox:checked");

			// 非空判断
			if (checkedItems.length === 0) {
				alert("请选择待删除用户！");
				return false;
			}

			//确认操作
			if (!confirm("确定删除该用戶？")) {
				return false;
			}

			var Ids = new Array();
			var i = 0;
			checkedItems.each(function() {
				Ids[i] = $(this).val();
				i = i + 1;
			});
			$rightContentPanell.load(Path + url, {
				Ids : Ids
			});
			$(".active").trigger("click");
		})

		// 选中所有
		$rightContentPanell.find(".selectAll")
				.on(
						"click",
						function() {
							// 获取所有复选框数量
							var allItemNum = $rightContentPanell
									.find(".checkbox").length;

							// 获取被选中的复选框数量
							var checkedItemNum = $rightContentPanell
									.find(".checkbox:checked").length;

							// 对比复选框是否全部被选中
							var isChecked = allItemNum === checkedItemNum;

							// 如果全部被选中，则反选，否则全选
							$rightContentPanell.find(".checkbox").prop(
									"checked", !isChecked);
						});

	})
</script>
<style>
</style>
</html>