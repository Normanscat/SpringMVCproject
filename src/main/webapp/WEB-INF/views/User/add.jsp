<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 引入日期选择器插件 -->
<link href="plugins/air-datepicker/css/datepicker.min.css"
	rel="stylesheet" type="text/css">
<script src="plugins/air-datepicker/js/datepicker.min.js"></script>
<!-- 日期选择器引入中文语言 -->
<script src="plugins/air-datepicker/js/i18n/datepicker.zh.js"></script>
<section class="main-panel">
	<h4 class="form-title">新增&编辑用户信息</h4>
	<form name="userForm"
		action="${pageContext.request.contextPath }/User/add" method="post"
		class="form" enctype="multipart/form-data">
		<div class="form-input">
			<div class="form-left">
				<div class="input-group ">
					<label class="input-label">用户名称：</label> <input id="name"
						name="name" type="text" class="input" minlength="6" maxlength="20"
						placeholder="请输入您的用户名！" autofocus>
				</div>
				<div class="input-group ">
					<label class="input-label">登录密码：</label> <input id="password"
						name="password" type="password" class="input" minlength="6"
						maxlength="20" placeholder="请输入您的密码！">
				</div>
				<div class="input-group ">
					<label class="input-label">重复密码：</label> <input id="repeatPassword"
						name="repeatPassword" type="password" class="input" minlength="6"
						maxlength="20" placeholder="请输入您的确认密码！">
				</div>
				<div class="input-group ">
					<label class="input-label">电子邮箱：</label> <input id="email"
						name="email" type="email" class="input" minlength="6"
						maxlength="20" placeholder="请输入您的邮箱！">
				</div>
				<div class="input-group ">
					<label class="input-label">出生日期：</label> <input id="birthday"
						name="birthday" type="text" class="input" data-language="zh"
						placeholder="请选择您的出生日期！" />
				</div>
				<div class="input-group">
					<label class="radio-label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
					<div class="radio-group">
						<input type="radio" name="sex" id="male" value="男"
							checked="checked" required><label for="male">男</label> <input
							type="radio" name="sex" id="female" value="女" required><label
							for="female">女</label>
					</div>
				</div>
				<div class="input-group clr">
					<label class="">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：</label>
					<select class="select" name="profession">
						<option value="student">学生</option>
						<option value="teacher">老师</option>
						<option value="coder">程序猿</option>
						<option value="engineer">攻城狮</option>
						<option value="ui">UI设计</option>
					</select>
				</div>
				<div class="input-group">
					<label class="input-label">爱&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;好：</label>
					<select class="multiple-select" name="hobby" multiple="multiple"
						size="4">
						<option value="书法">书法</option>
						<option value="乐器">乐器</option>
						<option value="运动">运动</option>
						<option value="编程">编程</option>
					</select>
				</div>
			</div>
			<div class="form-right">
				<div class="upload-avatar ">
					<img src="images/avatar.png" id="myimage" name="avatar" width="200px"
						height="200px" />
					<p>
						<button type="button" class="upload-avatar-btn"
							onclick="document.getElementById('image').click();" name="image">上传头像</button>
						<input type="file" name="imageFile" id="image"  style="display:none" onchange="changImg(event)"/>
					</p>
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

	//图片回显
	function changImg(e){
        for (var i = 0; i < e.target.files.length; i++) {  
            var file = e.target.files.item(i);  
            if (!(/^image\/.*$/i.test(file.type))) {  
                continue; //不是图片 就跳出这一次循环  
            }  
            //实例化FileReader API  
            var freader = new FileReader();  
            freader.readAsDataURL(file);  
            freader.onload = function(e) {  
                $("#myimage").attr("src",e.target.result);  
            };
        }
    }
	
	$(function() {
		//初始化日期生日选择
		$("#birthday").datepicker({
			dateFormat : "yyyy-mm-dd",
			autoClose : true
		});

		var $userform = $("#right").find("form");

		//初始化表單提交
			$userform.on("submit",function(e) {
			//阻止表单默认提交事件
				e.preventDefault();
				var $this = $(this);
				$this.ajaxSubmit({
					beforeSubmit : function(data, $form) {
						var name = $form.find("input[name='name']");
						if ($.trim(name.val()) === "") {
							alert("请输入名称")
							return false;
					}
					var password = $form.find("input[name='password']");
					if ($.trim(password.val()) === "") {
						alert("请输入密码")
						return false;
					}
					var repeatPassword = $form.find("input[name='repeatPassword']");
					if ($.trim(repeatPassword.val()) === "") {
						alert("请再次输入密码")
						return false;
					}
					if ($.trim(repeatPassword.val()) !== $.trim(password.val())) {
						alert("密码不一致请重新输入")
						return false;
					}
					var email = $form.find("input[name='email']");
					if ($.trim(email.val()) === "") {
						alert("请输入邮箱")
						return false;
					}
					var hobby = $form.find("select[name='hobby']");
					if ($.trim(hobby.val()) === "") {
						alert("请选择爱好")
						return false;
					}
					
					var image=$form.find("input[name='imageFile']");
					if ($.trim(image.val()) === "") {
						alert("请选择头像")
						return false;
					}
				},
					success : function() {
						// 刷新页面
						$("body").find(".active").trigger("click");
				}
			});
		});
	});
</script>
