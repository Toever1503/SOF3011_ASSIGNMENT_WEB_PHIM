<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="layout/header.jsp"></jsp:include>
	<div class="body-content">
		<jsp:include page="layout/aside.jsp"></jsp:include>

		<div class="main-content">
			<jsp:include page="layout/videoForm.jsp"></jsp:include>
		</div>
	</div>

	<jsp:include page="layout/footer.jsp"></jsp:include>
</body>
</html>
<style>
.body-content {
	width: 100%;
	min-height: 800px;
	display: flex;
}

.body-content aside {
	width: 15%;
	background-color: white;
	border-right: 1px solid black;
}

.body-content .main-content {
	width: 85%;
	background-color: skyblue;
	padding: 10px;
}

aside .action-container {
	
}

aside .action-item {
	padding: 10px;
	border-top: 1px solid black;
	font-weight: bold;
}

aside .action-item:hover {
	background-color: powderblue;
}
</style>
<style>
.item_checked {
	background: bisque !important;
}

.section {
	border: 1px solid gray;
	background-color: white;
	margin: 10px 0;
}

.route-action {
	
}

.route-action .route-item {
	border: 1px solid gray;
	padding: 5px;
	border-radius: 5px;
	background-color: white;
}

.login form {
	width: unset;
}

.login form>div>label {
	display: block;
	font-size: 20px;
}

.login form input[type='text'], input[type='password'], input[type='email'],
	textarea {
	width: 95%;
}

.login .hasError {
	margin-left: 0;
}

.container-box {
	position: relative;
	margin-top: 10px;
}

.container-box .container-item {
	display: inline-block;
	background: gainsboro;
	padding: 5px;
	border-radius: 4px;
	margin: 2px 0;
	cursor: pointer;
}
</style>