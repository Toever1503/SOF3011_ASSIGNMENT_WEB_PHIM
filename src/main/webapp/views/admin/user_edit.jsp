<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Profile</title>
</head>
<body>
	<jsp:include page="layout/header.jsp"></jsp:include>

	<div class="body-content">
		<jsp:include page="layout/aside.jsp"></jsp:include>
		<div class="main-content">
			<span class="add-new-film"><a
				href="/SOF3011_ASSIGNMENT/admin/user">Quay Lại</a></span>
			<style>
.add-new-film {
	border: 1px solid gray;
	padding: 5px;
	border-radius: 5px;
	background-color: white;
}
</style>

			<div class="login">
				<form action="" onsubmit='return formValidate(this)' method="post">
					<h1 style="text-align: center;">Update user</h1>
					<input name="id" style="display: none" value="${user.id }">
					<div>
						<label for="username">Tài khoản:</label> <input type="text"
							value="${user.username }" readonly name="username" id="username"
							placeholder="Tên đăng nhâp">
						<div class="hasError">
							<span></span>
						</div>
					</div>
					<div>
						<label for="name">Họ Và Tên:</label> <input type="text"
							value="${user.fullname }" name="fullname" id="name"
							placeholder="................" onkeyup='handleInputChange(this)'>
						<div class="hasError">
							<span></span>
						</div>
					</div>
					<div>
						 <label for="password">Mật Khẩu:</label> <input
							type="password" name="password" id="password"
							placeholder="................." onkeyup='handleInputChange(this)'>
						<div class="hasError">
							<span></span>
						</div>
					</div>

					<div>
						<label for="email">Email:</label> <input type="email" name="email"
							value="${user.email }" id="email" placeholder="................"
							onkeyup='handleInputChange(this)'>
						<div class="hasError">
							<span></span>
						</div>
					</div>

					<div>
						<label for="role">Role:</label> <input type="checkbox"
							${user.admin ==1? 'checked' :'' } name="admin" value="1"
							style="margin-left: unset;"> Admin?
						<div class="hasError">
							<span></span>
						</div>
					</div>

					<c:if test="${result !=null }">
						<p
							style="text-align: center; background: antiquewhite; padding: 5px; display: block;">
							${message }</p>
					</c:if>

					<input type="submit" value="Thêm">
				</form>

				<script>
					function handleInputChange(e) {
						e.nextElementSibling.getElementsByTagName('span')[0].innerHTML = null;
					}

					function formValidate(e) {
						let children = e.children;
						let username = document.getElementById('username');
						let password = document.getElementById('password');
						let fullname = document.getElementById('name');
						let email = document.getElementById('email');

						let check = 0;
						if (username.value.length <= 0)
							username.nextElementSibling
									.getElementsByTagName('span')[0].innerHTML = 'Username cannot empty!';
						else if (fullname.value.length <= 0)
							fullname.nextElementSibling
									.getElementsByTagName('span')[0].innerHTML = 'Name cannot empty!';
						else if (password.value.length <= 0)
							password.nextElementSibling
									.getElementsByTagName('span')[0].innerHTML = 'Password cannot empty!';
						else if (password.value.indexOf(' ') != -1)
							password.nextElementSibling
									.getElementsByTagName('span')[0].innerHTML = 'Password cannot contains space!';
						else if (email.value.length <= 0)
							email.nextElementSibling
									.getElementsByTagName('span')[0].innerHTML = 'Email cannot empty!';
						else if (email.value.indexOf(' ') != -1)
							email.nextElementSibling
									.getElementsByTagName('span')[0].innerHTML = 'Email cannot contains space!';
						else {
							children[4].setAttribute('disabled', false);
							return true;
						}

						children[4].setAttribute('disabled', true);
						return false;
					}
				</script>
			</div>
		</div>

	</div>

	<jsp:include page="layout/footer.jsp"></jsp:include>
</body>
</html>
<style>
body {
	background-color: #f1ededba;
}

.body-container {
	width: 1000px;
	margin: 0 auto;
}

.login {
	margin-top: 10px;
}

.login form {
	padding: 15px;
	background-color: white;
	width: 500px;
	margin: 0 auto;
	border-radius: 5px;
}

.login form>div {
	position: relative;
	margin: 15px 0;
}

.login form>div>label {
	width: 30%;
	display: inline-block;
	font-size: 15px;
}

.login form input[type='checkbox'] {
	margin-left: 30%;
	cursor: pointer;
}

.login form>div>span {
	margin-left: 30%;
	width: 50%;
}

.login form input[type='text'], input[type='password'], input[type='email'],
	textarea {
	padding: 8px;
	width: 63%;
	border: 1px solid gainsboro;
}

.login form input[type='submit'] {
	display: block;
	margin: 5px auto;
	padding: 6px;
	transition: 0.3s ease-in-out;
	border: 1px solid gainsboro;
	cursor: pointer;
	border-radius: 3px;
}

.login form input[type='submit']:hover {
	background-color: seashell;
}

.login .hasError {
	margin-left: 30%;
	padding: 5px;
	color: cornflowerblue;
	font-size: 14px;
}
</style>