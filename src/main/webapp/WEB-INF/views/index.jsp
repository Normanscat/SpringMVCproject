<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 引入日期选择器插件 -->
<link href="plugins/air-datepicker/css/datepicker.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" href="pagination/pagination.css" />
<script src="pagination/jquery.pagination.js"></script>
<script src="plugins/air-datepicker/js/datepicker.min.js"></script>
<!-- 日期选择器引入中文语言 -->
<script src="plugins/air-datepicker/js/i18n/datepicker.zh.js"></script>

</head>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<header>
		<h2>用户管理系统</h2>
		<nav>
			<a href="#">帮助</a> <a href="#">退出</a>
		</nav>
	</header>
	<div class="content">
		<aside id="left" class="left">
			<div class="menu">
				<a class="menu-item active" href="javascript:;"
					data-url="/User/list">用户查询</a><a data-url="/hobby/list"
					href="javascript:;" class="menu-item hobby">愛好管理</a> <a
					data-url="/profession/list" href="javascript:;"
					class="menu-item profession">职业管理</a>
			</div>
			<div class="head-avatar">
				<img src="${pageContext.request.contextPath }/images/avatar.png"
					alt="头像" />
				<h5>当前登录用户</h5>
			</div>
		</aside>
		<div id="right" class="right"></div>
	</div>
	<div style="position: absolute; right: 10px; bottom: 66px;" id="rights"
		class="rights"></div>
	<footer id="footer"> Copyright © 2017.轻实训版权所有 </footer>
</body>
<script
	src="${pageContext.request.contextPath }/plugins/jquery-2.1.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/plugins/jquery.form.min.js"></script>
<script type="text/javascript">
	$(function() {

		var $rightContentPanell = $("body").find(".right");
		var $rightContentPanells = $("body").find(".rights");
		var Path = "${pageContext.request.contextPath }";

		//查询
		$(".active").on("click", function() {
			$rightContentPanells.show();
			var $this = $(this);
			var url = $this.data("url");
			var urls = "/User/lists";
			$rightContentPanells.load(Path + urls);
			$rightContentPanell.load(Path + url, {
				currentpage : 1
			});
		}).first().click();

		//添加
		$(".add").on("click", function() {
			$rightContentPanells.hide();
			var $this = $(this);
			var url = $this.data("url");
			$rightContentPanell.load(Path + url);
		})

		//爱好
		$(".hobby").on("click", function() {
			$rightContentPanells.hide();
			var $this = $(this);
			var url = $this.data("url");
			alert(Path + url)
			$rightContentPanell.load(Path + url, {
				currentpage : 1
			});
		})

		//职业
		$(".profession").on("click", function() {
			$rightContentPanells.hide();
			var $this = $(this);
			var url = $this.data("url");
			alert(Path + url)
			$rightContentPanell.load(Path + url, {
				currentpage : 1
			});
		})
	});
</script>

</html>