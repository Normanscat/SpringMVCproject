<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="pagination/pagination.css" />
<script src="pagination/jquery.pagination.js"></script>
<body>
	<div id="Pagination" class="pagination">
		<!-- 这里显示分页 -->
	</div>
</body>

<script type="text/javascript">
	$(function() {
		var $rightContentPanell = $("body").find(".right");
		var Path = "${pageContext.request.contextPath }";
		var a=${ pages.totalPage};
		// 创建分页
		$("#Pagination").pagination(a, {
			num_edge_entries : 1, //边缘页数
			num_display_entries : 4, //主体页数
			callback : pageselectCallback,
			items_per_page : 1, //每页显示1项
			prev_text : "前一页",
			next_text : "后一页"
		});

		function pageselectCallback(page_index, jq) {
			var url = "/User/list";
			var currentpage = page_index + 1;
				$rightContentPanell.load(Path + url, {
					currentpage : currentpage
				});
		}
	});
		</script>
</html>