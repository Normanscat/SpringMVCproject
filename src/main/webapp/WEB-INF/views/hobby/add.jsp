<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section class="main-panel">
	<h4 class="form-title">新增&编辑角色信息</h4>
	<form name="userForm"
		action="${pageContext.request.contextPath }/hobby/add" method="post"
		class="form">
		<div class="form-input">
			<div class="form-left">
				<div class="input-group ">
					<input type="hidden" name="id" value="${hobby.id }">
					<label class="input-label">角色名称：</label> <input id="name"
						name="name" type="text" class="input" value="${hobby.name }" maxlength="20"
						placeholder="请输入爱好名称！" autofocus>
				</div>
			</div>
		</div>
		<div class="form-control">
			<hr>
			<button type="submit" id="saveUserBtn" class="button-green">保存</button>
			<button type="reset" class="button-blue">重置</button>
		</div>
	</form>
</section>
<script src="js/common.js"></script>
<script>
	$(function() {
		var $userform = $("#right").find("form");
		//初始化表單提交
		$userform.on("submit", function(e) {
			//阻止表单默认提交事件
			e.preventDefault();
			var $this = $(this);
			$this.ajaxSubmit({
				beforeSubmit : function(data, $form) {
					var name = $form.find("input[name='name']");
					if ($.trim(name.val()) === "") {
						alert("请输入爱好名称")
						return false;
					}
				},
				success : function() {
					// 刷新页面
					$("body").find(".hobby").trigger("click");
				}
			})
		});
	});
</script>




