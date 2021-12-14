<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/views/layout/head.jsp"></jsp:include>
<style type="text/css">
<%@include file='/static/css/login.css' %>
</style>
</head>
<body>
	<jsp:include page="/views/layout/modal_input.jsp"></jsp:include>
	<header>
		<jsp:include page="/views/layout/header.jsp"></jsp:include>
	</header>

	<div class="body-container">
		<aside>
			<div class="user-action">
				<div class="user-action-item">
					<a href="${pageContext.request.contextPath}/user">Dashboard</a>
				</div>
				<div class="user-action-item">
					<a href="${pageContext.request.contextPath}/user/edit_profile">Update profile</a>
				</div>
				<div class="user-action-item">
					<a href="${pageContext.request.contextPath}/user/changepassword">Change password</a>
				</div>
			</div>
		</aside>

		<div class="main-content">
			<c:if test="${actionType != null }">
				<jsp:include page="/views/layout/user/${actionType}.jsp"></jsp:include>
			</c:if>
		</div>
		<style>
.body-container .main-content {
	width: 800px;
}
</style>
		<style>
.body-container {
	display: flex;
}

.body-container aside {
	width: 200px;
	background: whitesmoke;
	border:1px solid #e1e1e1;
	padding: 5px;
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

</body>
</html>