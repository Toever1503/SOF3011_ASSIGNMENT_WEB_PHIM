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
			123...
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