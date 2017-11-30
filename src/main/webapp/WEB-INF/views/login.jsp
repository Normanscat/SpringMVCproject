<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<style>
* {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 8px;
	color: white;
}

body {
	background: url(images/bg.jpg) repeat-x;
	min-height: 500px;
	position: relative;
}

.name, .pwd {
	width: 310px;
	height: 44px;
	line-height: 44px;
	padding-left: 15px;
	outline: none;
	border-radius: 20px;
	background-color: #0A3C78;
	border: 1px solid #329BE0;
}

.name:hover, .pwd:hover, .name:focus, .pwd:focus {
	background-color: #052b64;
}

#login {
	background: #3fb9ff;
	font-size: 18px;
	width: 310px;
	height: 44px;
	outline: none;
	color: white;
	background: linear-gradient(#3fb9ff, #099be7, #229de3);
	border-radius: 20px;
}

#login:hover {
	background: linear-gradient(#229de3, #099be7, #3fb9ff);
}

#middle {
	margin-top: 180px;
}

#middle ul {
	text-align: center;
}

#middle .li {
	font-size: 30px;
}

#middle li {
	list-style-type: none;
	margin-top: 15px;
}

#cloud {
	background: url(images/cloud.png) repeat;
	width: 100%;
	height: 336px;
	position: absolute;
	bottom: 0px;
	animation: cloud 60s linear infinite alternate;
	z-index: -10;
}
    @keyframes cloud {
        0% {
            background-position: top left
        }
        100% {
           background-position: top right
        }
    }
#err {
	display: none;
	color: red;
	display: inline-block;
}

#bg {
	background: url(images/sky.png) repeat;
	width: 100%;
	z-index: -20;
	height: 452px;
	position: absolute;
	top: 0px;
}

#changeimg {
	margin-left: 15px;
	cursor: pointer;
}
</style>
<body>
	<div id="bg"></div>
	<div id="middle">
		<form action="${pageContext.request.contextPath }/vail" method="post" onsubmit="change()">
			<ul>
				<li class="li">用户登入</li>
				<li><input class="name" name="username" id="username"
					placeholder="请输入用户名"></li>
				<li><input type="password" name="password" id="pwd" class="pwd"
					placeholder="请输入密码"></li>
				<li>
					<button id="login">立即登录</button>
				</li>
				<li><span id="err"><font color="red">${message }</font></span></li>
			</ul>
		</form>
	</div>
	<div id="cloud"></div>
	<script src="plugins/jquery-2.1.3.min.js" type="text/javascript"></script>
	<script>
		function change() {
			var name = document.getElementById("username").value
			var pwd = document.getElementById("pwd").value
			if (name == "") {
				alert("请输入用户名！");
				return;
			}
			if (pwd == "") {
				alert("请输入密码！");
				return;
			}
		}
	</script>
</body>
</html>