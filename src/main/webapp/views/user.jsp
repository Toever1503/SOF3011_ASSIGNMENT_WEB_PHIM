<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<jsp:include page="/views/layout/header.jsp"></jsp:include>
	</header>

	<div class="body-container">
		<c:choose>
			<c:when test="${actionType== 'login' || actionType == 'logout'}">
				<jsp:include page="${'/views/layout/'+actionType}+'.jsp'"></jsp:include>
			</c:when>
			<c:otherwise>
				<aside>
					<div class="user-action-item">
						<a href="">Cập Nhật Tài Khoản</a>
					</div>
					<div class="user-action">
						<div class="user-action-item">
							<a href="">Đổi Mật Khẩu</a>
						</div>
					</div>
				</aside>

				<div class="main-content">
					<jsp:include page="/views/layout/user/${actionType}.jsp"></jsp:include>
				</div>
			</c:otherwise>
		</c:choose>
		<style>
.body-container .main-content {
	width: 800px;
}
</style>
		<style>
.body-container {
	display: flex;
	min-height: 600px;
}

.body-container aside {
	width: 200px;
	background: whitesmoke;
	border: 1px solid gray;
}

aside .user-action .user-action-item {
	border-bottom: 1px solid gainsboro;
	padding: 5px;
	transition: 0.3s ease-in-out;
	cursor: pointer;
}

aside .user-action .user-action-item a {
	display: block;
}

aside .user-action .user-action-item:hover {
	background-color: beige;
}
</style>
	</div>

	<footer>
		<jsp:include page="/views/layout/footer.jsp"></jsp:include>
	</footer>

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

.login form input[type='text'], input[type='password'], input[type='email']
	{
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
</body>
</html>